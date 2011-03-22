package sg.edu.nus.iss.billsys.dao;

import com.ibm.jvm.ClassLoader;

import sg.edu.nus.iss.billsys.constant.FeatureType;
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
		
		td.print("td.getCallHistoryData()",td.getCallHistoryData());
		td.print("td.getCustomerData()",td.getCustomerData());
		td.print("td.getFeatureData()",td.getFeatureData());
		td.print("td.getFeatureRateData()",td.getFeatureRateData());
		td.print("td.getPaymentHistoryData()",td.getPaymentHistoryData());
		td.print("td.getPlanRatesData()",td.getPlanRatesData());
		td.print("td.getSubscriptionPlanData()",td.getSubscriptionPlanData());
		td.print("td.getUserData()",td.getUserData());
		td.print("td.getComplaintsData()",td.getComplaintsData());
		
		
		
		
		
		UserDao usr=new UserDao();
		
		User user=new User();
		user.setUsername("Veera1");
		user.setPassword("pass1");
		user.setRole("test");
		
		usr.updatePassword(user);
		usr.saveObjectData();
		
		CallHistDao cdao= new CallHistDao();
		
		PaymentHistDao pdao =new PaymentHistDao();
		
		FeatureRateDao frdao=new FeatureRateDao();
		
		System.out.println(frdao.getPricebyFeatureCode(0));
		
		PlanRateDao prdao=new PlanRateDao();
		
		System.out.println(prdao.getPricebyPlanType("b"));	
		
		CustomerDao cudao=new CustomerDao();
		
		ComplaintsDao codao=new ComplaintsDao();
		
		
		SubscriptionPlanDao subplandao=new SubscriptionPlanDao();
		
		
		System.out.println(subplandao.getAccountbyAccountNo("acc_no1"));
		
		
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
