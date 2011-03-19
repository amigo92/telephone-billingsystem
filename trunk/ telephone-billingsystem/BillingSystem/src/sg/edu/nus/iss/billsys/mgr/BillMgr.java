package sg.edu.nus.iss.billsys.mgr;

import java.util.*;

import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;
import sg.edu.nus.iss.billsys.vo.Bill.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class BillMgr {

	public ArrayList<Bill> generate(Date billDate){
		ArrayList<Bill> list = new ArrayList<Bill>();
		
		ArrayList<Customer> customers = MgrFactory.getAccountMgr().getAllCustomers();
		for(Customer c : customers){
			Bill bill = generate(billDate, c.getCustomerId());
			if(bill != null){
				list.add(bill);
			}
		}
		
		return list;
	}
	
	public Bill generate(Date billDate, String customerId){
		Customer customer = MgrFactory.getAccountMgr().getCustomerDetailsById(customerId);
		Account acct = customer.getAcct();
		
		Bill bill = new Bill();
		bill.setBillDate(billDate);
		bill.setDueDate(calculateDueDate(billDate));
		bill.setaCustomer(customer);
		bill.setaAccount(acct);
		
		for(SubscriptionPlan plan : acct.getPlans()){
			if(isInBillMonth(billDate, plan.getDateCommenced(), plan.getDateTerminated())){
				if(plan instanceof VoicePlan){
					processCallBasedPlan(bill, billDate, plan);
				}
				else{
					processNonCallBasedPlan(bill, billDate, plan);
				}
			}
		}
		
		//TODO
		
		return bill;
	}
	
	private boolean isInBillMonth(Date billDate, Date commencedDate, Date terminateDate){
		return true; //TODO
	}
	
	private void processCallBasedPlan(Bill bill, Date billDate, SubscriptionPlan plan){
		//TODO
	}
	
	private void processNonCallBasedPlan(Bill bill, Date billDate, SubscriptionPlan plan){
		CableTvPlan cableTvPlan = (CableTvPlan)plan;
		Feature basicFeature = plan.getBasicFeature();
		
		int basicCharges = MgrFactory.getSubscriptionMgr().getSubscriptionCharge(basicFeature.getaFeatureType());
		int channels = 0;
		int additionCharges = 0;
		
		for(Feature channel : cableTvPlan.getOptionalFeatures()){
			if(isInBillMonth(billDate, channel.getDateCommenced(), channel.getDateTerminated())){
				channels++;
				additionCharges += MgrFactory.getSubscriptionMgr().getSubscriptionCharge(channel.getaFeatureType());
			}
		}
		
		SummaryCharges sum = bill.new SummaryCharges();
		sum.setDesc(basicFeature.getaFeatureType().toString());
		sum.addEntry(bill.new Entry("Subscriptions charges", basicCharges));
		sum.addEntry(bill.new Entry("Additional Channel charges", additionCharges));
		sum.setTotalAmt(basicCharges + additionCharges);
		bill.addSummaryCharges(sum);
		
		DetailCharges detail = bill.new DetailCharges();
		detail.setDesc(plan.getPlanDescription());
		detail.addEntry(bill.new Entry("Subscriptions charges", basicCharges));
		detail.addEntry(bill.new Entry("Additional " + channels + " Channel charge(s)" , additionCharges));
		bill.addDetailChargesList(detail);
	}
	
	private Date calculateDueDate(Date billDate){
		return TimeUtils.addDays(billDate, 15);
	}

}
