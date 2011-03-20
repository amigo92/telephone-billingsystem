package sg.edu.nus.iss.billsys.mgr;

import java.util.*;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.tools.*;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.*;
import sg.edu.nus.iss.billsys.vo.Bill.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class BillMgr {

	private static BillDao aBillDao = new BillDao();
	private static List<FeatureType> callTxnTypes;
	
	/**
	 * 
	 * @client GUI
	 */
	public BillPeriod[] getAllGeneratedBillPeriods(){
		Set<BillPeriod> set = aBillDao.getAllGeneratedBillPeriods();
		BillPeriod[] bps = new BillPeriod[set.size()];
		return set.toArray(bps);
	}
	
	/**
	 * 
	 * @client GUI
	 */
	public BillPeriod getNextBillPeriod(){
		return aBillDao.getNextBillPeriod();
	}
		
	public ArrayList<Bill> getBills(BillPeriod billPeriod){
		return aBillDao.getBills(billPeriod);
	}
	
	public Bill getBill(BillPeriod billPeriod, String customerId){
		for(Bill bill : getBills(billPeriod)){
			if(bill.getaCustomer().getCustomerId().equals(customerId)){
				return bill;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @throws BillingSystemException 
	 * @client GUI
	 */
	public void generate(BillPeriod billPeriod) throws BillingSystemException{
		if(!billPeriod.equals(getNextBillPeriod())){
			throw new BillingSystemException("The next bill generation period must be " + billPeriod.getBillDate());
		}
		
		ArrayList<Bill> list = new ArrayList<Bill>();
		
		ArrayList<Customer> customers = MgrFactory.getAccountMgr().getAllCustomers();
		for(Customer c : customers){
			Bill bill = generate(billPeriod, c.getCustomerId());
			if(bill != null){
				list.add(bill);
			}
		}
		
		aBillDao.add(billPeriod, list);
		aBillDao.setCurrBillPeriod(billPeriod);
		aBillDao.save();
	}
	
	private Bill generate(BillPeriod billPeriod, String customerId){
		Customer customer = MgrFactory.getAccountMgr().getCustomerDetailsById(customerId);
		Account acct = customer.getAcct();
		
		Bill bill = new Bill();
		bill.setPreviousBalance(acct.getBalance());
		bill.setBillDate(billPeriod.getBillDate());
		bill.setDueDate(billPeriod.getDueDate());
		bill.setaCustomer(customer);
		bill.setAcctNo(acct.getAcctNo());
		
		int paymentAmt = 0;
		for(PaymentHist ph : new PaymentHistDao().getPaymentHistByBillPeriodAcctNo(billPeriod, acct.getAcctNo())){
			bill.addPaymentReceived(ph.getPaymentDate(), ph.getPaymentAmt());
			paymentAmt += ph.getPaymentAmt();
		}
		bill.setTotalPaymentMade(paymentAmt);

		for(SubscriptionPlan plan : acct.getPlans()){
			if(billPeriod.isOverlapped(plan.getDateCommenced(), plan.getDateTerminated())){
				if(plan instanceof VoicePlan){
					processCallBasedPlan(bill, billPeriod, (VoicePlan)plan);
				}
				else{
					processNonCallBasedPlan(bill, billPeriod, (CableTvPlan)plan);
				}
			}
		}
		
		int chargesBefGST = calculateTotalCurrChargesBeforeGST(bill.getSummaryChargesList());
		bill.setTotalGST(FinanceUtils.calGST(chargesBefGST));
		bill.setTotalCurrCharges(chargesBefGST + bill.getTotalGST());
		bill.setCurrChargesDue(bill.getPreviousBalance() - bill.getTotalPaymentMade() + bill.getTotalCurrCharges());
		
		acct.setBalance(bill.getCurrChargesDue());
		acct.setBalanceUpdateDate(billPeriod.getEndTime());
		MgrFactory.getAccountMgr().update(acct);
		
		return bill;
	}
	
	private void processCallBasedPlan(Bill bill, BillPeriod billPeriod, VoicePlan plan){

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
		
		int total_use_charges = getTotalUsage(billPeriod, bill, detail, plan);
		detail.setTotalAmt(total_sub_charges + total_use_charges);
		
		bill.addDetailChargesList(detail);
		
		SummaryCharges sum = bill.new SummaryCharges();
		sum.setDesc(basicFeature.getName());
		sum.addEntry(bill.new Entry("Subscription charges", total_sub_charges));
		sum.addEntry(bill.new Entry("Usage charges", total_use_charges));
		sum.setTotalAmt(total_sub_charges + total_use_charges);
		
		bill.addSummaryCharges(sum);
	}
	
	private void processNonCallBasedPlan(Bill bill, BillPeriod billPeriod, CableTvPlan cableTvPlan){
		Feature basicFeature = cableTvPlan.getBasicFeature();
		
		int basicCharges = getSubscriptionCharges(basicFeature);
		int channels = 0;
		int additionCharges = 0;
		
		for(Feature channel : cableTvPlan.getOptionalFeatures()){
			if(billPeriod.isOverlapped(channel.getDateCommenced(), channel.getDateTerminated())){
				channels++;
				additionCharges += getSubscriptionCharges(channel);
			}
		}
		
		SummaryCharges sum = bill.new SummaryCharges();
		sum.setDesc(basicFeature.getName());
		sum.addEntry(bill.new Entry("Subscription charges", basicCharges));
		sum.addEntry(bill.new Entry("Additional Channel charges", additionCharges));
		sum.setTotalAmt(basicCharges + additionCharges);
		bill.addSummaryCharges(sum);
		
		DetailCharges detail = bill.new DetailCharges();
		detail.setDesc(cableTvPlan.getPlanDescription());
		detail.addEntry(bill.new Entry("Subscriptions charges", basicCharges));
		detail.addEntry(bill.new Entry("Additional " + channels + " Channel charge(s)" , additionCharges));
		bill.addDetailChargesList(detail);
	}
	
	private int getTotalUsage(BillPeriod billPeriod, Bill bill, DetailCharges detail, VoicePlan plan){
		detail.addEntry(bill.new Entry("Usage Charges", null));
		
		ArrayList<CallHist> calls = new CallHistDao().getCallHistByBillDateAcctNo(billPeriod, plan.getAcctNo());
		
		int total_use_charges = 0;
		for(FeatureType ct : getCallTxnTypes()){
			Entry entry = calculateUsageCharges(calls, bill, ct, plan);
			if(entry != null){
				detail.addEntry(entry);
				total_use_charges += entry.getAmt();
			}
		}
		
		detail.setTotalAmt(total_use_charges);
		
		return total_use_charges;
	}

	private Entry calculateUsageCharges(ArrayList<CallHist> calls, Bill bill, FeatureType ct, VoicePlan plan){		
		int total_duration = 0;
		for(CallHist ch : calls){
			if(ch.getTelNo().equals(plan.getAssignedTelNo()) && ch.getCallTxnTypeCd() == ct.getFeatureCd()){
				total_duration += ch.getCallDuration();
			}
		}
		
		if(total_duration != 0){
			int usage_per_number = total_duration * new CallRateDao().getRate(plan.getPlanType().getPlanCd(), ct.getFeatureCd());
			
			return bill.new Entry(ct.name, usage_per_number);
		}
		else{
			return null;
		}
	}
	
	private int getSubscriptionCharges(Feature feature){
		return MgrFactory.getSubscriptionMgr().getSubscriptionCharge(feature.getaFeatureType());
	}
	
	private int calculateTotalCurrChargesBeforeGST(ArrayList<SummaryCharges> sums){
		int amt = 0;
		for(SummaryCharges s : sums){
			amt += s.getTotalAmt();
		}
		
		return amt;
	}
	
	private static List<FeatureType> getCallTxnTypes(){
		if(callTxnTypes == null){
			callTxnTypes = new ArrayList<FeatureType>();
			for(FeatureType f : FeatureType.values()){
				if(f.usageCharge){
					callTxnTypes.add(f);
				}
			}
		}
		
		return callTxnTypes;
	}

}
