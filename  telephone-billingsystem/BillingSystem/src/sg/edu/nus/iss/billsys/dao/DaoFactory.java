/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * @author Veera
 *
 */
public class DaoFactory {
	
	private static IUserDao userDao;
	private static ISubscriptionPlanDao subscriptionPlanDao;
	private static IComplaintsDao complaintsDao;
	private static IPaymentHistDao paymentHistDao;
	private static IFeatureRateDao featureRateDao;
	private static ICustomerDao customerDao;
	private static ICallHistDao callHistDao;
	private static IPhoneNumbersDao mobileNumbersDao;
	private static IPhoneNumbersDao digiVoiceNumbersDao;
	
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
