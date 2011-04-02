package sg.edu.nus.iss.billsys.mgr;

import java.io.*;
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
	private static final String DEFAULT_BILL_PATH = "bill/";
	
	protected BillMgr() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @client GUI
	 */
	public BillPeriod[] getAllGeneratedBillPeriods(){
		Set<BillPeriod> set = aBillDao.getAllGeneratedBillPeriods();
		BillPeriod[] bps = new BillPeriod[set.size()];
		BillPeriod[] arr = set.toArray(bps);
		Arrays.sort(arr);
		return arr;
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
	
	public Bill getBill(BillPeriod billPeriod, String accNo){
		for(Bill bill : getBills(billPeriod)){
			if(bill.getAcctNo().equals(accNo)){
				return bill;
			}
		}
		
		return null;
	}
	
	/**
	 * Empty the BillStore
	 * @client GUI
	 */
	public void purge(){
		aBillDao.purge();
		
		File dir = new File(DEFAULT_BILL_PATH);
		for(File f : dir.listFiles()){
			if(f.isFile()){
				f.delete();	//delete the text bills
			}
		}
	}
	
	public void writeBills(String filePath, BillPeriod billPeriod, ArrayList<Bill> bills) throws IOException{
		new File(filePath).mkdirs();
		File file = new File(filePath, "bills_" + billPeriod.toString() + ".txt");
		BufferedWriter out = null;

		try{
			out = new BufferedWriter(new FileWriter(file));
			
			String contents = "";
			for(Bill bill : bills){
				contents += bill.toString() 
						 + "\n\n\n\n================================================================================\n\n\n\n";
			}
			
			out.write(contents);
		}
		catch(IOException oe){
			oe.printStackTrace();
		}
		finally{
			if(out != null) out.close();
		}
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
			Bill bill = generate(billPeriod, c);
			if(bill != null){
				list.add(bill);
			}
		}
		
		aBillDao.add(billPeriod, list);
		aBillDao.setCurrBillPeriod(billPeriod);
		aBillDao.save();

		try {
			writeBills(DEFAULT_BILL_PATH, billPeriod, list);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private Bill generate(BillPeriod billPeriod, Customer customer) throws BillingSystemException{;
		Account acct = customer.getAcct();
		
		Bill bill = new TextBill();
		bill.setaCompanyProfile(SystemUtils.getCompanyProfile());
		bill.setPreviousBalance(getPreviousBalance(billPeriod, acct.getAcctNo()));
		bill.setBillDate(billPeriod.getBillDate());
		bill.setDueDate(billPeriod.getDueDate());
		bill.setaCustomer(customer);
		bill.setAcctNo(acct.getAcctNo());
		
		int paymentAmt = 0;
		for(PaymentHist ph : DaoFactory.getInstanceOfPaymentHistDao().getPaymentHistByBillPeriodAcctNo(billPeriod, acct.getAcctNo())){
			bill.addPaymentReceived(ph.getPaymentDate(), ph.getPaymentAmt());
			paymentAmt += ph.getPaymentAmt();
		}
		bill.setTotalPaymentMade(paymentAmt);

		List<SubscriptionPlan> plans = MgrFactory.getSubscriptionMgr().getAccountSubscriptions(acct.getAcctNo());
		if(plans != null){
			for(SubscriptionPlan plan : plans){
				if(billPeriod.isOverlapped(plan.getDateCommenced(), plan.getDateTerminated())){
					if(plan instanceof VoicePlan){
						processCallBasedPlan(bill, billPeriod, (VoicePlan)plan);
					}
					else{
						processNonCallBasedPlan(bill, billPeriod, (CableTvPlan)plan);
					}
				}
			}
		}
		
		int chargesBefGST = calculateTotalCurrChargesBeforeGST(bill.getSummaryChargesList());
		bill.setTotalGST(FinanceUtils.calGST(chargesBefGST));
		bill.setTotalCurrCharges(chargesBefGST + bill.getTotalGST());
		bill.setCurrChargesDue(bill.getPreviousBalance() - bill.getTotalPaymentMade() + bill.getTotalCurrCharges());
		
		if(bill.getPaymentReceived().size() == 0 && bill.getSummaryChargesList().size() == 0
				&& bill.getCurrChargesDue() == 0){	//empty bill
				return null;
		}
		else{
			return bill;
		}
	}
	
	private void processCallBasedPlan(Bill bill, BillPeriod billPeriod, VoicePlan plan) throws BillingSystemException{

		DetailCharges detail = bill.new DetailCharges();
		
		int total_sub_charges = 0;

		detail.setDesc(plan.getPlanDescription());
		detail.addEntry(bill.new Entry("Subscription Charges", null));

		Feature basicFeature = plan.getBasicFeature();
		int basicCharges = basicFeature.getSubscriptionCharges();
		total_sub_charges += basicCharges;
		detail.addEntry(bill.new Entry(basicFeature.getName(), basicCharges));
		
		for(Feature f : plan.getOptionalFeatures()){
			int amt = f.getSubscriptionCharges();
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
	
	private void processNonCallBasedPlan(Bill bill, BillPeriod billPeriod, CableTvPlan cableTvPlan) throws BillingSystemException {
		Feature basicFeature = cableTvPlan.getBasicFeature();
		
		int basicCharges = basicFeature.getSubscriptionCharges();
		int channels = 0;
		int additionCharges = 0;
		
		for(Feature channel : cableTvPlan.getOptionalFeatures()){
			if(billPeriod.isOverlapped(channel.getDateCommenced(), channel.getDateTerminated())){
				channels++;
				additionCharges += channel.getSubscriptionCharges();
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
		detail.addEntry(bill.new Entry("Additional " + channels + " Channel(s) charges" , additionCharges));
		detail.setTotalAmt(basicCharges + additionCharges);
		bill.addDetailChargesList(detail);
	}
	
	private int getTotalUsage(BillPeriod billPeriod, Bill bill, DetailCharges detail, VoicePlan plan) throws BillingSystemException{
		detail.addEntry(bill.new Entry("Usage Charges", null));
		
		ArrayList<CallHist> calls = DaoFactory.getInstanceOfCallHistDao().getCallHistByBillDateAcctNo(billPeriod, plan.getAcctNo());
		
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

	private Entry calculateUsageCharges(ArrayList<CallHist> calls, Bill bill, FeatureType ct, VoicePlan plan) throws BillingSystemException{		
		int total_duration = 0;
		for(CallHist ch : calls){
			if(ch.getTelNo().equals(plan.getAssignedTelNo()) && ch.getCallTxnTypeCd() == ct.getFeatureCd()){
				total_duration += ch.getCallDuration();
			}
		}
		
		if(total_duration != 0){
			int usage_per_number = total_duration * DaoFactory.getInstanceOfFeatureRateDao().getPricebyFeatureCode(ct.getFeatureCd()).getPrice();

			return bill.new Entry(ct.name, usage_per_number);
		}
		else{
			return null;
		}
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
	
	private int getPreviousBalance(BillPeriod currBillPeriod, String acctNo){
		Bill bill = getBill(currBillPeriod.getPrevBillPeriod(), acctNo);
		return bill != null ? bill.getCurrChargesDue() : 0;
	}

}
