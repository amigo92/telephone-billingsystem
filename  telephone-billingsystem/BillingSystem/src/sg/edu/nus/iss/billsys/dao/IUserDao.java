/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.User;

/**
 * @author Veera
 *
 */
public interface IUserDao {
	
	public User getUserByUsername(String username) throws BillingSystemException;
	

}
