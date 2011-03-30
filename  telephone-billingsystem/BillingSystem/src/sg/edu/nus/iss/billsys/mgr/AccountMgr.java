package sg.edu.nus.iss.billsys.mgr;
import sg.edu.nus.iss.billsys.*;

/*Modified by Wen Jing @ Mar 26
 * 
 * 
 */

import java.util.*;

import sg.edu.nus.iss.billsys.dao.CustomerDao;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.*;

public class AccountMgr {
	private CustomerDao custDao;
	
	
	public AccountMgr(){
		custDao = new CustomerDao();
	}

	public Customer createCustomer(Customer customer){
		custDao.addCustomer(customer);
		return customer;
	}
	
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
		String lastDigit = nric.substring(1, nric.length()-1);
		int nextAccount = Integer.parseInt(lastDigit);
		Account account = new Account(aDate, nextAccount);
		account.setDateCreated(aDate);
		newCust.setAcct(account);
		custDao.addCustomer(newCust);
		return newCust;
	}
	
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
	
	public Customer reactiveCustomer(String accId, Date today){
		return custDao.reactiveCustomer(accId, today);
	}
	
	public ArrayList<Customer> getAllCustomers(){
		return custDao.getAllCustomers();
	}
	
	public ArrayList<Customer> getAllActiveCustomers(){
		return custDao.getAllActiveCustomers();
	}

	public void update(Account acct){
		//TODO
	}
	
	/**
	 * Retrieve account obj by account no.
	 * @client SubscriptionMgr
	 */
	public Account getAccountObject(String accountNo){
		//TODO
		return null;
	}
}
