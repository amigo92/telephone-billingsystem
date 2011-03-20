package sg.edu.nus.iss.billsys.mgr;

import java.util.*;
import sg.edu.nus.iss.billsys.vo.*;

public class AccountMgr {

	/**
	 * Make sure the customer object has a reference to account object, 
	 * and the account object refers to a list of available subscription plan objects
	 * @param customerId
	 * @return
	 * @client BillMgr
	 */
	public Customer getCustomerDetailsById(String customerId){
		return null; //TODO
	}
	
	public ArrayList<Customer> getAllCustomers(){
		return null; //TODO
	}
	
	/**
	 * update acct balance
	 * @client BillMgr
	 */
	public void update(Account acct){
		//TODO
	}
}
