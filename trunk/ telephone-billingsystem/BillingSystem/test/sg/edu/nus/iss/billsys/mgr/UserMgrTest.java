package sg.edu.nus.iss.billsys.mgr;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.constant.UserRole;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * 
 */

/**
 * @author Yu Chui Chi
 *
 */
public class UserMgrTest {

	String userId,userId2;
	String password, password2;
	UserMgr manager;
	UserRole role, role2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//this is valid password 
		userId = "Chuichi";
		password = "password$1";
		role = UserRole.AGENT;
		
		userId2 = "Veera"; 
		password2 = "password$1";
		role2 = UserRole.ADMIN;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.UserMgr#UserMgr()}.
	 */
	@Test
	public void testUserMgr() {
		try
		{
			manager = new UserMgr();
		}
		catch(BillingSystemException e)
		{
			System.out.println(e.getMessagebyException());
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.UserMgr#getAuthUserRole()}.
	 */
	@Test
	public void testGetAuthUserRole() {
		try
		{
			manager = new UserMgr();
			manager.isValidAuthUser(userId, password);
			if(manager.getAuthUserRole().equals(role)==false)
				fail("Unable to get the user role for user 1");
			
			UserMgr manager2 = new UserMgr();
			manager2.isValidAuthUser(userId2, password2);
			if(manager.getAuthUserRole().equals(UserRole.ADMIN)==false)
				fail("Unable to get the user role for user 2");
		}
		catch(BillingSystemException e)
		{
			System.out.println(e.getMessagebyException());
		}
		
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.UserMgr#getAuthUserName()}.
	 */
	@Test
	public void testGetAuthUserName() {
		try
		{
			manager = new UserMgr();
			manager.isValidAuthUser(userId, password);
			if(manager.getAuthUserName().equals(userId)==false)
				fail("Unable to get the user name");
		}
		catch(BillingSystemException e)
		{
			System.out.println(e.getMessagebyException());
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.UserMgr#isValidAuthUser(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testIsValidAuthUser() {
		
		try
		{
			manager = new UserMgr();
			
			// valid password		
			if(manager.isValidAuthUser(userId, password)!= true)
				fail("Unable to authenticate userId: " + userId);
			
			//test invalid password
			String invalidPwd = "thisIsInvalid"; 
			if(manager.isValidAuthUser(userId, invalidPwd)!= false)
				fail("Invalid password should fails authentication");
		}	
		catch(BillingSystemException e)
		{
			System.out.println(e.getMessagebyException());
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.mgr.UserMgr#logout()}.
	 * @throws  
	 */
	@Test
	public void testLogout(){
		
		try
		{
			manager = new UserMgr();
			
			//ensure it can login
			if(manager.isValidAuthUser(userId, password)!=true)
				fail("Unable to authenticate userId: " + userId);				
			
			//logout now
			manager.logout();
			
			if(manager.getAuthUserName()!=null)
				fail("Unable to logout");		
		}
		catch(BillingSystemException e)
		{
			System.out.println(e.getMessagebyException());
		}
	}

}
