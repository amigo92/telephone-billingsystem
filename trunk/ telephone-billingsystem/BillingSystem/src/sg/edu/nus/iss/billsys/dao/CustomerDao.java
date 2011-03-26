package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.billsys.vo.Account;
import sg.edu.nus.iss.billsys.vo.Customer;

/**
 * 
 * @author Veera
 *
 */

public class CustomerDao extends GenericDao{
	
	private List<Customer> listCustomer=new ArrayList<Customer>();
	
	@Override
	protected void objectDataMapping(String[][] data) {
		
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
			customer.setDeleted(Boolean.valueOf(data[i][7]));
			customer.setName(data[i][1]);
			customer.setNric(data[i][8]);
			
	    	
	    	listCustomer.add(customer);	
	    }

		
		this.listCustomer=listCustomer;
		
	}
	
	@Override
	protected void saveObjectData() {
		int cnt=0;
		
		String data[][]=new String[listCustomer.size()][9];
			
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
		saveCustomerData(data);
		
	}
	
	public CustomerDao() {
	 this.objectDataMapping(getCustomerData());
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
	
	@Override
	protected boolean validateData(String[][] data) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
