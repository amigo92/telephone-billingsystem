package sg.edu.nus.iss.billsys.mgr;

import org.junit.Test;

import sg.edu.nus.iss.billsys.vo.Customer;


public class AccountMgrTest {

	@Test
	public void testGetAllCustomers(){
		try{
		for(Customer c : MgrFactory.getAccountMgr().getAllCustomers()){
			System.out.println(c);
		}
		}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
}
