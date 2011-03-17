package sg.edu.nus.iss.billsys.dao;

public class SubscriptionChargesDao extends GenericDao {

	public SubscriptionChargesDao(){
		super("C:/subscriptioncharges.txt");
	}
	
	public int getSubscriptionCharge(int featureTypeCd){
		return 0; //TODO
	}
}
