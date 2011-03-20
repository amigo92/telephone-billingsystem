package sg.edu.nus.iss.billsys.dao;

import sg.edu.nus.iss.billsys.vo.User;

/**
 * This class is temp used to test the read write errors and parsing errors ,
 * later to be deleted. Please note. 
 * @author Veera
 *
 */


public class TestDao extends GenericDao{
	@Override
	protected boolean validateData(String[][] data) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected void saveObjectData() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void objectDataMapping(String[][] data) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		TestDao td=new TestDao();
		
		td.print("td.getAccountData()",td.getAccountData());
		td.print("td.getCallHistoryData()",td.getCallHistoryData());
		td.print("td.getCustomerData()",td.getCustomerData());
		td.print("td.getFeatureData()",td.getFeatureData());
		td.print("td.getFeatureRateData()",td.getFeatureRateData());
		td.print("td.getPaymentHistoryData()",td.getPaymentHistoryData());
		td.print("td.getPlanRatesData()",td.getPlanRatesData());
		td.print("td.getSubscriptionPlanData()",td.getSubscriptionPlanData());
		td.print("td.getUserData()",td.getUserData());
		
		td.testWriteAccountData(td.getAccountData());
		
		td.print("td.getAccountData()",td.getAccountData());
		
		UserDao usr=new UserDao();
		
		User user=new User();
		user.setUserId("1");
		user.setUsername("Veera1");
		user.setPassword("pass1");
		
		usr.updatePassword(user);
		usr.saveObjectData();
		
		
	}
	
	public void testWriteAccountData(String[][] data){
		
		
		TestDao td=new TestDao();
		
		td.saveAccountData(td.getAccountData());
		
		
	}
	
	public void  print(String filename,String[][] data){
		System.out.print("Filename :"+filename);
		System.out.print("\n");
		
		for(int i=0;i<data.length;i++){
			
			for(int z=0;z<data[i].length;z++){
				
				System.out.print("cell ["+i+"]["+z+"]: "+data[i][z] +" ");
				
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

}