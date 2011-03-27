package sg.edu.nus.iss.billsys.mgr;

import org.junit.Test;

import sg.edu.nus.iss.billsys.vo.Customer;


public class AccountMgrTest {

	@Test
	public void testGetAllCustomers(){
		for(Customer c : MgrFactory.getAccountMgr().getAllCustomers()){
			System.out.println(c);
		}
	}
}
