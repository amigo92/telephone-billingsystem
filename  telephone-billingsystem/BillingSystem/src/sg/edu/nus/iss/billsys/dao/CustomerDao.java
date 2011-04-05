package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.Account;
import sg.edu.nus.iss.billsys.vo.Customer;

/**
 * 
 * @author Veera
 * 
 * This class is a implementation class for accessing the data layer to get the customers information
 * It extends the genericdao to inherit the functionality of accessing the physical file and to return a 
 * raw data of two dimensional string array , which will in turn mapped to the domain objects for easy 
 * manipulation , This class also provides implementation to save back the domain object to the data file.
 * 
 * This class will be inheriting the Interface class to implement the public methods which will be used by the 
 * manager classes for the data read / update / create functionalities.
 *
 */

public class CustomerDao extends GenericDao implements ICustomerDao{
	
	private final static String  CUSTOMER_DATA_FILE="data/Customer.txt";//This Constant is to specify the file path to load the Customer information
	private static final int COL_LENGTH=9; //This constant would give the number of columns expected in the file 
	private List<Customer> listCustomer=new ArrayList<Customer>();//Instance variable to hold the data from the parsed data of the file
	/*
	 * This method will implement the logic to map the raw data of two dimensional array to the Domain objects which is then used by the public methods of the Dao.
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.GenericDao#objectDataMapping()
	 */
	@Override
	protected final void objectDataMapping() throws BillingSystemException{
		String[][] data=getDataAsArray(CUSTOMER_DATA_FILE);
		if(validateData(data,"Customer",COL_LENGTH)){
		List<Customer> listCustomer=new ArrayList<Customer>();
		
		for(int i=0;i<data.length;i++){
	    	
			Customer customer=new Customer();
			
			customer.setAddress1(data[i][2]);
			customer.setAddress2(data[i][3]);
			customer.setAddress3(data[i][4]);
			customer.setContactTel(data[i][5]);
			
			Account acct=new Account();
			acct.setAcctNo(data[i][0]);
			
			customer.setAcct(acct);
			customer.setInterest(data[i][6]);
			customer.setIsDeleted(Boolean.valueOf(data[i][7]));
			customer.setName(data[i][1]);
			customer.setNric(data[i][8]);
			
	    	
	    	listCustomer.add(customer);	
	    }

		
		this.listCustomer=listCustomer;
		}
		
	}
	/*
	 * This method implements the data mapping back to the raw format of two dimensional array from the domain objects and 
	 * save it in to the file.
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.GenericDao#saveObjectData()
	 */
	@Override
	public final void saveObjectData() throws BillingSystemException{
		int cnt=0;
		
		String data[][]=new String[listCustomer.size()][COL_LENGTH];
			
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
		
			Customer element = (Customer) iter.next();			
				
				data[cnt][0]=element.getAcct().getAcctNo();
				data[cnt][1]=element.getName();
				data[cnt][2]=element.getAddress1();
				data[cnt][3]=element.getAddress2();
				data[cnt][4]=element.getAddress3();
				data[cnt][5]=element.getContactTel();
				data[cnt][6]=element.getInterest();
				data[cnt][7]=String.valueOf(element.isDeleted());
				data[cnt][8]=element.getNric();
				
				
				cnt++;				
			}
		if(validateData(data,"Customer",COL_LENGTH)){
		if(!storeDataByArray(CUSTOMER_DATA_FILE, data))throw new BillingSystemException("Saving to File Operation Failed for CustomerData");
		}
	}
	/*
	 * The Constructor intialisation also invokes the call to map the raw data parsed from the file to domain object.
	 */
	protected CustomerDao() throws BillingSystemException{
	 this.objectDataMapping();
	}
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#getCustomerByNric(java.lang.String)
	 */
	public Customer getCustomerByNric(String nric) {
		Customer cust=null;
		
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			if(element.getNric().equals(nric)){
				cust=element;
				break;
			}			
		}
		return cust;	
	}
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#getCustomerByName(java.lang.String)
	 */
	public Customer getCustomerByName(String name) {
		Customer cust=null;
		
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			if(element.getName().equals(name)){
				cust=element;
				break;
			}			
		}
		return cust;	
	}
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#getCustomerByAcctId(java.lang.String)
	 */
	public Customer getCustomerByAcctId(String acctId){
		Customer cust = null;
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			if(element.getAcct().getAcctNo().equals(acctId)){
				cust=element;
				break;
			}			
		}
		return cust;
	}
	
	
	public ArrayList<Customer> getCustomerListByNric(String nric){
		ArrayList<Customer> custList = new ArrayList<Customer>();
		for(Customer c : listCustomer){
			if(c.getNric().equals(nric)){
				custList.add(c);
			}
		}
		
		return custList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#getCustomerListByAcctId(java.lang.String)
	 * case insensitive, partial search by AcctId;
	 * return a list of customers if found;
	 */
	public ArrayList<Customer> getCustomerListByAcctId(String searchId){
		ArrayList<Customer> custList = new ArrayList<Customer>();
		String s;
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			s = element.getAccountId();
			if(s.indexOf(searchId)>= 0 ){
				custList.add(element);
			}
			else
			{
				s = s.toLowerCase();
				searchId = searchId.toLowerCase();
				if(s.indexOf(searchId) >= 0 ){
					custList.add(element);
				}
			}
		}			
		return custList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#getCustomerListByName(java.lang.String)
	 * case insensitive, partial search by AcctId;
	 * return a list of customers if found;
	 */
	public ArrayList<Customer> getCustomerListByName(String searchName){
		ArrayList<Customer> custList = new ArrayList<Customer>();
		String s;
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			s = element.getName();
			if(s.indexOf(searchName)>= 0 ){
				custList.add(element);
			}
			else
			{
				s = s.toLowerCase();
				searchName = searchName.toLowerCase();
				if(s.indexOf(searchName) >= 0 ){
					custList.add(element);
				}
			}
		}			
		return custList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#reactiveCustomer(java.lang.String, java.util.Date)
	 * reactivate customer account
	 */
	public Customer reactiveCustomer(String acctId, Date today){
		Customer cust = null;
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			if(element.getAcct().getAcctNo().equals(acctId)){
				cust=element;
				cust.reactiveCustomer(today);
				break;
			}			
		}
		return cust;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#addCustomer(sg.edu.nus.iss.billsys.vo.Customer)
	 * Add a customer to the list
	 */
	public void addCustomer(Customer customer){
		if(customer == null){
			return;
		}
		listCustomer.add(customer);
	}
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#getAllCustomers()
	 */
	public ArrayList<Customer> getAllCustomers(){
		ArrayList<Customer> custList = new ArrayList<Customer>();
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			custList.add(element);
		}			
		return custList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#getAllActiveCustomers()
	 */
	public ArrayList<Customer> getAllActiveCustomers(){
		ArrayList<Customer> custList = new ArrayList<Customer>();
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			if(element.getIsDeleted() == false)
			custList.add(element);
		}			
		return custList;
	} 
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#deleteCust(java.lang.String, java.util.Date)
	 */
	public boolean deleteCust(String accountId, Date today){
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			if(element.getAcct().getAcctNo().equals(accountId)){
				element.setIsDeleted(true, today);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.ICustomerDao#updateCust(sg.edu.nus.iss.billsys.vo.Customer)
	 * Update customer details by passing in a customer object
	 */
	public boolean updateCust(Customer newCust){
		if(newCust == null){
			return false;
		}
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			if(element.getAcct().getAcctNo().equals(newCust.getAccountId())){
				element = newCust;
				return true;
			}
		}
		return false;
	}

}
