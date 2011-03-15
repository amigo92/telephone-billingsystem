package sg.edu.nus.iss.billsys.mgr;

import java.util.*;

import sg.edu.nus.iss.billsys.vo.*;

public class BillMgr {

	public ArrayList<Bill> generate(Date billDate){
		ArrayList<Bill> list = new ArrayList<Bill>();
		
		ArrayList<Customer> customers = MgrFactory.getAccountMgr().getAllCustomers();
		for(Customer c : customers){
			Bill bill = generate(billDate, c.getCustomerId());
			if(bill != null){
				list.add(bill);
			}
		}
		
		return list;
	}
	
	public Bill generate(Date billDate, String customerId){
		return null; //TODO
	}
	

}
