/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * @author Veera
 * 
 * This class is to return the instance of the dao interfaces defined for the manager classes to tap on the 
 * data layer . there is two reasons we have enforced this design .
 * 
 * To keep the data layer implementation decoupled from the manager layer hence we can easily switch the databases
 * from file to rdbms or any source by just creating a instance of that specific imeplementation. Manager classes would not 
 * be aware of the implementations classes and still they can get the data returned .So in a way we have enforced encapsulation
 * for the manager classes.
 *
 */
public class DaoFactory {
	/*
	 * Static references to the object is made to maintain one copy at any one time . and 
	 * we have enforced a singleton pattern not to create too many objects of the dao classes.
	 * as it doesnt serve a purpose for a standalone application.
	 * 
	 */
	private static IUserDao userDao;
	private static ISubscriptionPlanDao subscriptionPlanDao;
	private static IComplaintsDao complaintsDao;
	private static IPaymentHistDao paymentHistDao;
	private static IFeatureRateDao featureRateDao;
	private static ICustomerDao customerDao;
	private static ICallHistDao callHistDao;
	private static IPhoneNumbersDao mobileNumbersDao;
	private static IPhoneNumbersDao digiVoiceNumbersDao;
	
	/*
	 * The below public methods are to return the object references of a specific Interface 
	 * by hiding the actuall implementation from mgr classes.
	 * 
	 * This class can be enhanced in future to have a different implementation and to tap on a 
	 * different datasource by which creating instance of dao that is implementing specs of the datasource.
	 * 
	 */
	
	public static IUserDao getInstanceOfUserDao() throws BillingSystemException{
		
		if(userDao == null){
			userDao = new UserDao();
		}
		
		return userDao;
	}
	
	public static ISubscriptionPlanDao getInstanceOfSubscriptionPlanDao() throws BillingSystemException{
		
		if(subscriptionPlanDao == null){
			subscriptionPlanDao = new SubscriptionPlanDao();
		}
		
		return subscriptionPlanDao;
	}
	
	public static IComplaintsDao getInstanceOfComplaintsDao() throws BillingSystemException{
		
		if(complaintsDao == null){
			complaintsDao = new ComplaintsDao();
		}
		
		return complaintsDao;
	}
	
	public static IPaymentHistDao getInstanceOfPaymentHistDao() throws BillingSystemException{
		
		if(paymentHistDao == null){
			paymentHistDao = new PaymentHistDao();
		}
		
		return paymentHistDao;
	}

	public static IFeatureRateDao getInstanceOfFeatureRateDao() throws BillingSystemException{
		
		if(featureRateDao == null){
			featureRateDao = new FeatureRateDao();
		}
		
		return featureRateDao;
	}
	
	public static ICustomerDao getInstanceOfCustomerDao() throws BillingSystemException{
		
		if(customerDao == null){
			customerDao = new CustomerDao();
		}
		
		return customerDao;
	}
	
	public static ICallHistDao getInstanceOfCallHistDao() throws BillingSystemException{
		
		if(callHistDao == null){
			callHistDao = new CallHistDao();
		}
		
		return callHistDao;
	}
	
	public static IPhoneNumbersDao getInstanceOfMobileNumbersDao() throws BillingSystemException{
		
		if(mobileNumbersDao == null){
			mobileNumbersDao = new MobileNumbersDao();
		}
		
		return mobileNumbersDao;
	}
	
	public static IPhoneNumbersDao getInstanceOfDigiVoiceNumbersDao() throws BillingSystemException{
		
		if(digiVoiceNumbersDao == null){
			digiVoiceNumbersDao = new DigiVoiceNumbersDao();
		}
		
		return digiVoiceNumbersDao;
	}

}
