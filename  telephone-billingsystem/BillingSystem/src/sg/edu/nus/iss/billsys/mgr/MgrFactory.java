package sg.edu.nus.iss.billsys.mgr;

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
	
	public static UserMgr getUserMgr(){
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
	
	public static ComplaintMgr getComplaintMgr(){
		if(aComplaintMgr == null){
			aComplaintMgr = new ComplaintMgr();
		}
		
		return aComplaintMgr;
	}
	
	public static SubscriptionMgr getSubscriptionMgr(){
		if(aSubscriptionMgr == null){
			aSubscriptionMgr = new SubscriptionMgr();
		}
		
		return aSubscriptionMgr;
	}
}
