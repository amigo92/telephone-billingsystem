package sg.edu.nus.iss.billsys.dao;

import sg.edu.nus.iss.billsys.constant.FeatureType.FeatureCode;

public class SubscriptionChargesDao extends GenericDao {

	@Override
	protected void objectDataMapping(String[][] data) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void saveObjectData() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected boolean validateData(String[][] data) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public SubscriptionChargesDao(){
		//super("C:/subscriptioncharges.txt");
	}
	
	public int getSubscriptionCharge(FeatureCode featureCode){
		return 0; //TODO
	}
}
