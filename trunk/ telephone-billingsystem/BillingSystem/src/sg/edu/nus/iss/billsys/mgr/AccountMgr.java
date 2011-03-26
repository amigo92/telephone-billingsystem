package sg.edu.nus.iss.billsys.mgr;

import java.util.*;
import sg.edu.nus.iss.billsys.vo.*;

public class AccountMgr {

	/**
	 * save the customer and the account object
	 * @param customer
	 * @return
	 */
	public Customer create(Customer customer){
		return null; //TODO
	}
	
	/**
	 * Make sure the customer object has a reference to account object, 
	 * and the account object refers to a list of available subscription plan objects
	 * @param nric
	 * @return
	 * @client BillMgr
	 */
	public Customer getCustomerDetailsById(String nric){
		return null; //TODO
	}
	
	//return empty list should be returned if NULL
	public ArrayList<Customer> getAllCustomers(){
		return new ArrayList<Customer>(); //TODO
	}
	
	/**
	 * update acct balance
	 * @client BillMgr
	 */
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
