package sg.edu.nus.iss.billsys.mgr;

import java.util.*;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.tools.*;
import sg.edu.nus.iss.billsys.dao.*;
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
		
		int paymentAmt = 0;
		for(PaymentHist ph : new PaymentHistDao().getPaymentHistByBillDateAcctNo(billDate, acct.getAcctNo())){
			bill.addPaymentReceived(ph.getPaymentDate(), ph.getPaymentAmt());
			paymentAmt += ph.getPaymentAmt();
		}
		bill.setTotalPaymentMade(paymentAmt);

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
		
		int chargesBefGST = calculateTotalCurrChargesBeforeGST(bill.getSummaryChargesList());
		bill.setTotalGST(FinanceUtils.calGST(chargesBefGST));
		bill.setTotalCurrCharges(chargesBefGST + bill.getTotalGST());
		bill.setCurrChargesDue(bill.getPreviousBalance() - bill.getTotalPaymentMade() + bill.getTotalCurrCharges());
		
		return bill;
	}
	
	private boolean isInBillMonth(Date billDate, Date commencedDate, Date terminateDate){
		return true; //TODO
	}
	
	private void processCallBasedPlan(Bill bill, Date billDate, SubscriptionPlan plan){

		DetailCharges detail = bill.new DetailCharges();
		
		int total_sub_charges = 0;

		detail.setDesc(plan.getPlanDescription());
		detail.addEntry(bill.new Entry("Subscription Charges", null));

		Feature basicFeature = plan.getBasicFeature();
		int basicCharges = getSubscriptionCharges(basicFeature);
		total_sub_charges += basicCharges;
		detail.addEntry(bill.new Entry(basicFeature.getName(), basicCharges));
		
		for(Feature f : plan.getOptionalFeatures()){
			int amt = getSubscriptionCharges(f);
			total_sub_charges += amt;
			detail.addEntry(bill.new Entry(f.getName(), amt));
		}
		
		int total_use_charges = getTotalUsage(billDate, bill, detail, plan);
		detail.setTotalAmt(total_sub_charges + total_use_charges);
		
		bill.addDetailChargesList(detail);
		
		SummaryCharges sum = bill.new SummaryCharges();
		sum.setDesc(basicFeature.getName());
		sum.addEntry(bill.new Entry("Subscription charges", total_sub_charges));
		sum.addEntry(bill.new Entry("Usage charges", total_use_charges));
		sum.setTotalAmt(total_sub_charges + total_use_charges);
		
		bill.addSummaryCharges(sum);
	}
	
	private void processNonCallBasedPlan(Bill bill, Date billDate, SubscriptionPlan plan){
		CableTvPlan cableTvPlan = (CableTvPlan)plan;
		Feature basicFeature = plan.getBasicFeature();
		
		int basicCharges = getSubscriptionCharges(basicFeature);
		int channels = 0;
		int additionCharges = 0;
		
		for(Feature channel : cableTvPlan.getOptionalFeatures()){
			if(isInBillMonth(billDate, channel.getDateCommenced(), channel.getDateTerminated())){
				channels++;
				additionCharges += getSubscriptionCharges(channel);
			}
		}
		
		SummaryCharges sum = bill.new SummaryCharges();
		sum.setDesc(basicFeature.getaFeatureType().toString());
		sum.addEntry(bill.new Entry("Subscription charges", basicCharges));
		sum.addEntry(bill.new Entry("Additional Channel charges", additionCharges));
		sum.setTotalAmt(basicCharges + additionCharges);
		bill.addSummaryCharges(sum);
		
		DetailCharges detail = bill.new DetailCharges();
		detail.setDesc(plan.getPlanDescription());
		detail.addEntry(bill.new Entry("Subscriptions charges", basicCharges));
		detail.addEntry(bill.new Entry("Additional " + channels + " Channel charge(s)" , additionCharges));
		bill.addDetailChargesList(detail);
	}
	
	private int getTotalUsage(Date billDate, Bill bill, DetailCharges detail, SubscriptionPlan plan){
		detail.addEntry(bill.new Entry("Usage Charges", null));
		
		ArrayList<CallHist> calls = new CallHistDao().getCallHistByBillDateAcctNo(billDate, plan.getAcctNo());
		
		int total_use_charges = 0;
		for(CallTxnType ct : CallTxnType.values()){
			Entry entry = calculateUsageCharges(calls, bill, ct, plan);
			if(entry != null){
				detail.addEntry(entry);
				total_use_charges += entry.getAmt();
			}
		}
		
		detail.setTotalAmt(total_use_charges);
		
		return total_use_charges;
	}

	private Entry calculateUsageCharges(ArrayList<CallHist> calls, Bill bill, CallTxnType ct, SubscriptionPlan plan){		
		String telNo = getAssignedtelNo(plan);
		int total_duration = 0;
		for(CallHist ch : calls){
			if(ch.getTelNo().equals(telNo) && ch.getCallTxnTypeCd() == ct.typeCd){
				total_duration += ch.getCallDuration();
			}
		}
		
		int usage_per_number = total_duration * new CallRateDao().getRate(plan.getPlanType().planTypeCd, ct.typeCd);
		
		return bill.new Entry(ct.name, usage_per_number);
	}
	
	private String getAssignedtelNo(SubscriptionPlan plan){
		if(plan instanceof DigitalVoicePlan){
			return ((DigitalVoicePlan)plan).getAssignedTelNo();
		}
		else{
			return ((MobileVoicePlan)plan).getAssignedTelNo();
		}
	}
	
	private int getSubscriptionCharges(Feature feature){
		return MgrFactory.getSubscriptionMgr().getSubscriptionCharge(feature.getaFeatureType());
	}
	
	private Date calculateDueDate(Date billDate){
		return TimeUtils.addDays(billDate, 15);
	}
	
	private int calculateTotalCurrChargesBeforeGST(ArrayList<SummaryCharges> sums){
		int amt = 0;
		for(SummaryCharges s : sums){
			amt += s.getTotalAmt();
		}
		
		return amt;
	}

}
