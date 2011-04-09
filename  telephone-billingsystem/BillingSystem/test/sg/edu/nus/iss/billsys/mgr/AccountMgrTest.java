package sg.edu.nus.iss.billsys.mgr;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import sg.edu.nus.iss.billsys.vo.Customer;


public class AccountMgrTest {

	@Test
	public void testGetAllCustomers(){
		try{
		for(Customer c : MgrFactory.getAccountMgr().getAllCustomers()){
			System.out.println(c);
		}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
	
	@Test
	public void testDeleteCustomer(){
		String acctId = "SA-2011-03-25-8481361";
		Calendar today = Calendar.getInstance();
		Date aDate = today.getTime();
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(acctId);
			Date createdDate = test.getAcct().getDateCreated();
			test.setIsDeleted(true, aDate);
			assertTrue(MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(acctId).getIsDeleted());
			test.reactiveCustomer(createdDate);
			assertFalse(MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(acctId).getIsDeleted());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerDetailsById(){
		String acctId = "S8481363F";
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsById(acctId);
			assertNotNull(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerDetailsByAccountId(){
		String acctId = "SA-2011-03-25-8481361";
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(acctId);
			assertNotNull(test);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		acctId = "test";
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(acctId);
			assertNull(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerDetailsByName(){
		String name = "Veera";
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsByName(name);
			assertNotNull(test);
		}catch(Exception e){
			e.printStackTrace();
		}
		name = "vee";
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsByName(name);
			assertNull(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerListByName(){
		String name = "vee";
		try{
			ArrayList<Customer> test = MgrFactory.getAccountMgr().getCustomerListByName(name);
			assertNotNull(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerListByAcctId(){
		String name = "S";
		try{
			ArrayList<Customer> test = MgrFactory.getAccountMgr().getCustomerListByAcctId(name);
			assertNotNull(test);
//			System.out.println(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerListByNric(){
		String name = "84813";
		try{
			ArrayList<Customer> test = MgrFactory.getAccountMgr().getCustomerListByNric(name);
			assertNotNull(test);
//			System.out.println(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAllActiveCustomers(){
		String acctId = "SA-2011-03-25-8481361";
		Calendar today = Calendar.getInstance();
		Date aDate = today.getTime();
		try{
			MgrFactory.getAccountMgr().deleteCustomer(acctId);
			ArrayList<Customer> test = MgrFactory.getAccountMgr().getAllActiveCustomers();
//			System.out.println(test);
			MgrFactory.getAccountMgr().reactiveCustomer(acctId, aDate);
			test = MgrFactory.getAccountMgr().getAllActiveCustomers();
			assertNotNull(test);
//			System.out.println(test);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
