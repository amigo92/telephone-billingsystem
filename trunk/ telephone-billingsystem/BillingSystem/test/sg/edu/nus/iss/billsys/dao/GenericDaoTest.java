/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import static org.junit.Assert.*;

import java.util.logging.Level;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;

/**
 * @author Veera
 *
 */
public class GenericDaoTest {
	
	private final static String  CALL_HISTORY_DATA_FILE="data/CallHistory.txt";
	private final static String  CUSTOMER_DATA_FILE="data/Customer.txt";
	private final static String  FEATURE_DATA_FILE="data/Feature.txt";
	private final static String  FEATURE_RATES_DATA_FILE="data/FeatureRates.txt";
	private final static String  PAYMENT_HISTORY_DATA_FILE="data/PaymentHistory.txt";
	private final static String  PLANRATES_DATA_FILE="data/PlanRates.txt";
	private final static String  SUBSCRIPTION_PLAN_DATA_FILE="data/SubscriptionPlan.txt";
	private final static String  USER_DATA_FILE="data/User.txt";
	private final static String  COMPLAINTS_DATA_FILE="data/Complaints.txt";
	private final static String  MOBILE_NUMBERS_DATA_FILE="data/MobileNumbers.txt";
	private final static String  DIGIVOICE_NUMBERS_DATA_FILE="data/DigiVoiceNumbers.txt";
	
	private static String [][] callHistoryData=null;
	private static String [][] customerData=null;
	private static String [][] featureData=null;
	private static String [][] featureRatesData=null;
	private static String [][] paymentHistoryData=null;
	private static String [][] planRatesData=null;
	private static String [][] subscriptionPlanData=null;
	private static String [][] userData=null;
	private static String [][] complaintData=null;
	private static String [][] mobileNumbersData=null;
	private static String [][] digiVoiceNumbersData=null;
		
	private GenericDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		dao=new GenericDao(){
		@Override
		protected void objectDataMapping() throws BillingSystemException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		protected void saveObjectData() throws BillingSystemException {
			// TODO Auto-generated method stub
			
		}
		
		};
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dao=null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#validateData(java.lang.String[][], java.lang.String, int)}.
	 */
	@Test
	public void testValidateData() {
		try{
			
			dao.validateData(null, "Passing null", 3);
			fail("No Exception triggered in ValidateData when passing null");
	
		}catch (Exception e) {
			
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
		
		try{
			
			dao.validateData(new String[1][2], "Passing mismatching Array Column Size", 3);
			fail("No Exception triggered in ValidateData when passing mismatching array size");
	
		}catch (Exception e) {
			
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
		
		try{
			
			dao.validateData(new String[0][2], "Passing empty Array ", 3);
			fail("No Exception triggered in ValidateData when passing mismatching array size");
	
		}catch (Exception e) {
			
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}	
		
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getCallHistoryData()}.
	 */
	@Test
	public  void testGetCallHistoryData() {
		callHistoryData=dao.getDataAsArray(CALL_HISTORY_DATA_FILE);
		testArray(callHistoryData, "testGetCallHistoryData()");
		
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getCustomerData()}.
	 */
	@Test
	public void testGetCustomerData() {
		customerData=dao.getDataAsArray(CUSTOMER_DATA_FILE);
		testArray(customerData, "testGetCustomerData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getFeatureData()}.
	 */
	@Test
	public void testGetFeatureData() {
		featureData=dao.getDataAsArray(FEATURE_DATA_FILE);
		testArray(featureData, "testGetFeatureData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getFeatureRateData()}.
	 */
	@Test
	public void testGetFeatureRateData() {
		featureRatesData=dao.getDataAsArray(FEATURE_RATES_DATA_FILE);
		testArray(featureRatesData, "testGetFeatureRateData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getPaymentHistoryData()}.
	 */
	@Test
	public void testGetPaymentHistoryData() {
		paymentHistoryData=dao.getDataAsArray(PAYMENT_HISTORY_DATA_FILE);
		testArray(paymentHistoryData, "testGetPaymentHistoryData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getPlanRatesData()}.
	 */
	@Test
	public void testGetPlanRatesData() {
		planRatesData=dao.getDataAsArray(PLANRATES_DATA_FILE);
		testArray(planRatesData, "testGetPlanRatesData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getSubscriptionPlanData()}.
	 */
	@Test
	public void testGetSubscriptionPlanData() {
		subscriptionPlanData=dao.getDataAsArray(SUBSCRIPTION_PLAN_DATA_FILE);
		testArray(subscriptionPlanData, "testGetSubscriptionPlanData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getUserData()}.
	 */
	@Test
	public void testGetUserData() {
		userData=dao.getDataAsArray(USER_DATA_FILE);
		testArray(userData, "testGetUserData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getComplaintsData()}.
	 */
	@Test
	public void testGetComplaintsData() {
		complaintData=dao.getDataAsArray(COMPLAINTS_DATA_FILE);
		testArray(complaintData, "testGetComplaintsData()");
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getComplaintsData()}.
	 */
	@Test
	public void testGetMobileNumbersData() {
		mobileNumbersData=dao.getDataAsArray(MOBILE_NUMBERS_DATA_FILE);
		testArray(mobileNumbersData, "testGetMobileNumbersData()");
	}
	
	@Test
	public void testGetDigiVoiceNumbersData() {
		digiVoiceNumbersData=dao.getDataAsArray(DIGIVOICE_NUMBERS_DATA_FILE);
		testArray(digiVoiceNumbersData, "testGetDigiVoiceNumbersData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#saveCustomerData(java.lang.String[][])}.
	 */
	@Test
	public void testSaveCustomerData() {
	
		try{
			String[][] temp=customerData;
			
			if(temp!=null && temp.length>0){
				if(!dao.storeDataByArray(CUSTOMER_DATA_FILE, temp))
					fail("testSaveCustomerData , Save Failed !!");
			}else {				
				fail("saveCustomer Data to be saved is null or empty !!");				 
			}		
			
		}catch (Exception e) {
			fail("Exception while invoking saveCustomerData to save Data");	
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}	
		
		
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#saveFeatureData(java.lang.String[][])}.
	 */
	@Test
	public void testSaveFeatureData() {
		try{
			String[][] temp=featureData;
			
			if(temp!=null && temp.length>0){
				if(!dao.storeDataByArray(FEATURE_DATA_FILE, temp))
					fail("testSaveFeatureData , Save Failed !!");
			}else {				
				fail("saveFeature Data to be saved is null or empty !!");				 
			}		
			
		}catch (Exception e) {
			fail("Exception while invoking saveFeatureData to save Data");	
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}	
	}


	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#saveSubscriptionPlanData(java.lang.String[][])}.
	 */
	@Test
	public void testSaveSubscriptionPlanData() {
		try{
			String[][] temp=subscriptionPlanData;
			
			if(temp!=null && temp.length>0){
				if(!dao.storeDataByArray(SUBSCRIPTION_PLAN_DATA_FILE,temp))
					fail("testSaveSubscriptionPlanData , Save Failed !!");
			}else {				
				fail("saveSubscriptionPlan Data to be saved is null or empty !!");				 
			}	
			
		}catch (Exception e) {
			fail("Exception while invoking saveSubscriptionPlanData to save Data");	
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}	
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#saveComplaintsData(java.lang.String[][])}.
	 */
	@Test
	public void testSaveComplaintsData() {
		try{
			String[][] temp=complaintData;
			
			if(temp!=null && temp.length>0){
				if(!dao.storeDataByArray(COMPLAINTS_DATA_FILE, temp))
					fail("testSaveComplaintsData , Save Failed !!");
			}else {				
				fail("saveComplaints Data to be saved is null or empty !!");				 
			}
			
		}catch (Exception e) {
			fail("Exception while invoking saveComplaintsData to save Data");	
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}	
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#saveComplaintsData(java.lang.String[][])}.
	 */
	@Test
	public void testSaveMobileNumberData() {
		try{
			String[][] temp=mobileNumbersData;
			
			if(temp!=null && temp.length>0){
				if(!dao.storeDataByArray(MOBILE_NUMBERS_DATA_FILE, temp))
					fail("testSaveMobileNumberData , Save Failed !!");
			}else {				
				fail("SaveMobileNumberData Data to be saved is null or empty !!");				 
			}
			
		}catch (Exception e) {
			fail("Exception while invoking SaveMobileNumberData to save Data");	
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testSaveDigiNumberData() {
		try{
			String[][] temp=digiVoiceNumbersData;
			
			if(temp!=null && temp.length>0){
				if(!dao.storeDataByArray(DIGIVOICE_NUMBERS_DATA_FILE, temp))
					fail("testSaveDigiNumberData , Save Failed !!");
			}else {				
				fail("SaveDigiNumberData Data to be saved is null or empty !!");				 
			}
			
		}catch (Exception e) {
			fail("Exception while invoking SaveDigiNumberData to save Data");	
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}	
	}
	
	private static void testArray(String [][] temp,String dataName){
		
		try{	
			
			if(temp==null){
				
				fail(dataName+" Data is null");
				
			}else if(temp.length==0){
				
				fail(dataName+" Data is empty , Array Size is 0");
				
			}

		}catch (Exception e) {
			fail("Exception while invoking "+dataName);	
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}	
	}

}
