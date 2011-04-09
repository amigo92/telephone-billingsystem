package sg.edu.nus.iss.billsys.mgr;

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
			System.out.println(test);
			test.reactiveCustomer(createdDate);
			System.out.println(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerDetailsById(){
		String acctId = "S8481363F";
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsById(acctId);
			System.out.println(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerDetailsByAccountId(){
		String acctId = "SA-2011-03-25-8481361";
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(acctId);
			System.out.println(test);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		acctId = "test";
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(acctId);
			System.out.println(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerDetailsByName(){
		String name = "Veera";
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsByName(name);
			System.out.println(test);
		}catch(Exception e){
			e.printStackTrace();
		}
		name = "vee";
		try{
			Customer test = MgrFactory.getAccountMgr().getCustomerDetailsByName(name);
			System.out.println(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerListByName(){
		String name = "vee";
		try{
			ArrayList<Customer> test = MgrFactory.getAccountMgr().getCustomerListByName(name);
			System.out.println(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerListByAcctId(){
		String name = "S";
		try{
			ArrayList<Customer> test = MgrFactory.getAccountMgr().getCustomerListByAcctId(name);
			System.out.println(test);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetCustomerListByNric(){
		String name = "84813";
		try{
			ArrayList<Customer> test = MgrFactory.getAccountMgr().getCustomerListByNric(name);
			System.out.println(test);
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
			System.out.println(test);
			MgrFactory.getAccountMgr().reactiveCustomer(acctId, aDate);
			test = MgrFactory.getAccountMgr().getAllActiveCustomers();
			System.out.println(test);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
