/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.Customer;

/**
 * @author Veera
 *
 */
public interface ICustomerDao {
	
	public Customer getCustomerByNric(String nric);
	public Customer getCustomerByName(String name) ;
	public Customer getCustomerByAcctId(String acctId);
	public Customer reactiveCustomer(String acctId, Date today);
	public void addCustomer(Customer customer);
	public ArrayList<Customer> getAllCustomers();
	public ArrayList<Customer> getAllActiveCustomers();
	public ArrayList<Customer> getCustomerListByName(String searchName);
	public ArrayList<Customer> getCustomerListByAcctId(String searchId);
	public boolean updateCust(Customer newCust);
	public boolean deleteCust(String accountId, Date today);
	public void saveObjectData() throws BillingSystemException;

}
