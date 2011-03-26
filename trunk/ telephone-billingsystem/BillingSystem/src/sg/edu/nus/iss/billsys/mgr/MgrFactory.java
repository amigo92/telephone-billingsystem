package sg.edu.nus.iss.billsys.mgr;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * To ensure there is only one copy of each of the manager at runtime
 * @author Xu Guoneng
 *
 */
public class MgrFactory {

	private static UserMgr aUserMgr;
	private static AccountMgr aAccountMgr;
	private static ComplaintMgr aComplaintMgr;
	private static SubscriptionMgr aSubscriptionMgr;
	private static BillMgr aBillMgr;
	
	public static UserMgr getUserMgr() throws BillingSystemException{
		if(aUserMgr == null){
			aUserMgr = new UserMgr();
		}
		
		return aUserMgr;
	}
	
	public static AccountMgr getAccountMgr(){
		if(aAccountMgr == null){
			aAccountMgr = new AccountMgr();
		}
		
		return aAccountMgr;
	}
	
	public static ComplaintMgr getComplaintMgr() throws BillingSystemException{
		if(aComplaintMgr == null){
			aComplaintMgr = new ComplaintMgr();
		}
		
		return aComplaintMgr;
	}
	
	public static SubscriptionMgr getSubscriptionMgr() throws BillingSystemException{
		if(aSubscriptionMgr == null){
			aSubscriptionMgr = new SubscriptionMgr();
		}
		
		return aSubscriptionMgr;
	}
	
	public static BillMgr getBillMgr(){
		if(aBillMgr == null){
			aBillMgr = new BillMgr();
		}
		
		return aBillMgr;
	}
}
