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
 */

public class CustomerDao extends GenericDao implements ICustomerDao{
	
	private final static String  CUSTOMER_DATA_FILE="data/Customer.txt";
	private static final int COL_LENGTH=9;
	private List<Customer> listCustomer=new ArrayList<Customer>();
	
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
	
	protected CustomerDao() throws BillingSystemException{
	 this.objectDataMapping();
	}
	
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
	
	public void addCustomer(Customer customer){
		if(customer == null){
			return;
		}
		listCustomer.add(customer);
	}
	
	public ArrayList<Customer> getAllCustomers(){
		ArrayList<Customer> custList = new ArrayList<Customer>();
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			custList.add(element);
		}			
		return custList;
	}
	
	public ArrayList<Customer> getAllActiveCustomers(){
		ArrayList<Customer> custList = new ArrayList<Customer>();
		for (Iterator iter = listCustomer.iterator(); iter.hasNext();) {
			Customer element = (Customer) iter.next();
			if(element.getIsDeleted() == false)
			custList.add(element);
		}			
		return custList;
	} 
	
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
//	@Override
/*	protected boolean validateData(String[][] data) {
		// TODO Auto-generated method stub
		return false;
	}
*/	

}
