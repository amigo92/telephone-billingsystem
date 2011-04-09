/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import static org.junit.Assert.*;

import java.util.logging.Level;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.vo.User;

/**
 * @author Veera
 *
 */
public class UserDaoTest {

	private UserDao userDao;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			userDao=new UserDao();
		}catch (Exception e) {
			fail("Exception while initialising UserDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		userDao=null;
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.UserDao#objectDataMapping(java.lang.String[][])}.
	 */
	@Test
	public void testObjectDataMapping() {
		
		if(userDao==null){
			fail("Exception while initialising UserDao , objectDataMapping have errors !!");
		}
		
	}
	
	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.UserDao#getUserByUsername(java.lang.String)}.
	 */
	@Test
	public void testGetUserByUsername() {
		
		try{
			userDao.getUserByUsername(null);
			fail("Code should not be reaching here and should have thrown a Exception for getUserByUsername(null) !!");
			
		}catch (Exception e) {
			
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
		
		try{
			User user=userDao.getUserByUsername("admin");
			
			if(user==null){
				fail("Failed to retrieve the user by Name getUserByUsername(admin) !!");
			}
			
			user=userDao.getUserByUsername("XXX");
			
			if(user!=null){
				fail("Failed to return null for a invalid user by Name getUserByUsername(XXX) !!");
			}
			
		}catch (Exception e) {
			fail("Exception while testing getUserByUsername in UserDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
		
		
	}

}
