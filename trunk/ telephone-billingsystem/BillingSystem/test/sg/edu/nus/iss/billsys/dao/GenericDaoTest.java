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
	
	private GenericDao dao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		dao=new GenericDao(){
		@Override
		protected void objectDataMapping(String[][] data) throws BillingSystemException {
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
	public void testGetCallHistoryData() {
		testArray(dao.getCallHistoryData(), "getCallHistoryData()");
		
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getCustomerData()}.
	 */
	@Test
	public void testGetCustomerData() {
		testArray(dao.getCustomerData(), "getCustomerData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getFeatureData()}.
	 */
	@Test
	public void testGetFeatureData() {
		testArray(dao.getFeatureData(), "getFeatureData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getFeatureRateData()}.
	 */
	@Test
	public void testGetFeatureRateData() {
		testArray(dao.getFeatureRateData(), "getFeatureRateData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getPaymentHistoryData()}.
	 */
	@Test
	public void testGetPaymentHistoryData() {
		testArray(dao.getPaymentHistoryData(), "getPaymentHistoryData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getPlanRatesData()}.
	 */
	@Test
	public void testGetPlanRatesData() {
		testArray(dao.getPlanRatesData(), "getPlanRatesData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getSubscriptionPlanData()}.
	 */
	@Test
	public void testGetSubscriptionPlanData() {
		testArray(dao.getSubscriptionPlanData(), "getSubscriptionPlanData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getUserData()}.
	 */
	@Test
	public void testGetUserData() {
		testArray(dao.getUserData(), "getUserData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#getComplaintsData()}.
	 */
	@Test
	public void testGetComplaintsData() {
		testArray(dao.getComplaintsData(), "getComplaintsData()");
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.GenericDao#saveCustomerData(java.lang.String[][])}.
	 */
	@Test
	public void testSaveCustomerData() {
	
		try{
			String[][] temp=dao.getCustomerData();
			
			if(temp!=null && temp.length>0){
				if(!dao.saveCustomerData(temp))
					fail("saveCustomerData , Save Failed !!");
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
			String[][] temp=dao.getFeatureData();
			
			if(temp!=null && temp.length>0){
				if(!dao.saveFeatureData(temp))
					fail("saveFeatureData , Save Failed !!");
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
			String[][] temp=dao.getSubscriptionPlanData();
			
			if(temp!=null && temp.length>0){
				if(!dao.saveSubscriptionPlanData(temp))
					fail("saveSubscriptionPlanData , Save Failed !!");
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
			String[][] temp=dao.getComplaintsData();
			
			if(temp!=null && temp.length>0){
				if(!dao.saveComplaintsData(temp))
					fail("saveComplaintsData , Save Failed !!");
			}else {				
				fail("saveComplaints Data to be saved is null or empty !!");				 
			}
			
		}catch (Exception e) {
			fail("Exception while invoking saveComplaintsData to save Data");	
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
