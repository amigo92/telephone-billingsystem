package sg.edu.nus.iss.billsys.mgr;
import java.util.*;

import sg.edu.nus.iss.billsys.dao.DaoFactory;
import sg.edu.nus.iss.billsys.dao.ICustomerDao;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.*;

/*
 * Author: Wen Jing
 * Date: 30 Mar 2011
 */

public class AccountMgr {
	private ICustomerDao custDao;
	
	
	/*
	 * read the data from data file
	 */
	protected AccountMgr() throws BillingSystemException{
		custDao = DaoFactory.getInstanceOfCustomerDao();
	}
	
	/*
	 * Add customer to List
	 */
	public Customer createCustomer(Customer customer){
		custDao.addCustomer(customer);
		return customer;
	}
	
	/*
	 * Create and Add the customer, save data
	 */
	public Customer createCustomer(String name, String nric, String tel, String address1, String address2, String address3, String interest)throws BillingSystemException{
		if(nric == null || nric == ""){
	    	throw new BillingSystemException("Invalid NRIC!");
		}
		if( name == null || name == ""){
			throw new BillingSystemException("Invalid name!");
		}
		Customer newCust = new Customer(name, address1, address2, address3, tel, interest, nric);
		Calendar today = Calendar.getInstance();
		Date aDate = today.getTime();
		boolean isValidNric = nric.matches("S\\d{7}[A-Z]");
		if(!isValidNric) throw new BillingSystemException("Nric is not Valid !");
		int nextAccount = Integer.parseInt(nric.substring(1,nric.length()-1));
		Account account = new Account(aDate, nextAccount);
		account.setDateCreated(aDate);
		newCust.setAcct(account);
		custDao.addCustomer(newCust);
		custDao.saveObjectData();
		return newCust;
	}
	
	/*
	 * delete the customer by setting the isDeleted to true;
	 * date deleted is today;
	 */
	public boolean deleteCustomer(String acctId){
		Calendar today = Calendar.getInstance();
		Date aDate = today.getTime();
		return custDao.deleteCust(acctId, aDate);
	}
	
	/**
	 * Make sure the customer object has a reference to account object, 
	 * and the account object refers to a list of available subscription plan objects
	 * @param nric
	 * @return
	 * @client BillMgr
	 */
	
	public Customer getCustomerDetailsById(String nric){
		return custDao.getCustomerByNric(nric);
	}
	
	public Customer getCustomerDetailsByAccountId(String acctId){
		return custDao.getCustomerByAcctId(acctId);
	}
	
	public Customer getCustomerDetailsByName(String custName){
		return custDao.getCustomerByName(custName);
	}
	
	public ArrayList<Customer> getCustomerListByName(String searchName){
		return custDao.getCustomerListByName(searchName);
	}
	
	public ArrayList<Customer> getCustomerListByAcctId(String searchId){
		return custDao.getCustomerListByAcctId(searchId);
	}
	
	/*
	 * reactive customer account;
	 */
	public boolean reactiveCustomer(String accId, Date today) throws BillingSystemException{
		Customer cust = custDao.reactiveCustomer(accId, today);
		if(cust != null){
			custDao.saveObjectData();
			return true;
		}
		else{
			return false;
		}
	}
	
	public ArrayList<Customer> getAllCustomers(){
		return custDao.getAllCustomers();
	}
	
	public ArrayList<Customer> getAllActiveCustomers(){
		return custDao.getAllActiveCustomers();
	}
	
	public boolean updateCustomer(Customer newCust) throws BillingSystemException{
		boolean res = custDao.updateCust(newCust);
		custDao.saveObjectData();
		return res;
	}

}
