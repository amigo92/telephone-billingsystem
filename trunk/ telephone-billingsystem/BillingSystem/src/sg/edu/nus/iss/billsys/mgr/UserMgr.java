package sg.edu.nus.iss.billsys.mgr;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * This class is use by the GUI to authenticate the user
 * @author Yu Chui Chi
 */

public class UserMgr {
		
	private IUserDao dao;
	private static User currentUser;
	
	/*
	 * Default Constructor
	 */
	protected UserMgr() throws BillingSystemException{
		dao = DaoFactory.getInstanceOfUserDao();
		currentUser = null;
	}
	
	/**
	 * Get the current logged in user's user role
	 * @return the current user role or null if not logged in
	 */
	public UserRole getAuthUserRole()
	{		
		return currentUser != null ? currentUser.getRole() : null;
	}
	
	/**
	 * Get the current logged in user's name
	 * @return the current user name or null if not logged in
	 */
	public String getAuthUserName()
	{
		return currentUser != null ? currentUser.getUsername() : null;
	}
	
	/***
	 * To authenticate the user base on the user id and password
	 * if successful, the user will be logged in
	 * @param userId the user id
	 * @param pwd the password
	 * @return true for successful, false for failure
	 * @throws BillingSystemException
	 */
	public boolean isValidAuthUser(String userId, String pwd) throws BillingSystemException
	{	
		if(userId != null && pwd != null){
			User user = dao.getUserByUsername(userId);
			
			if(user != null && user.getPassword().equals(pwd)){
				currentUser = user;
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Call after the user signs off
	 */
	public void logout(){
		currentUser = null;
	}
	
	/*public Boolean changePassword(String pwd)
	{
		Boolean success = false;
		
		if(currentUser==null)
			return false; //should login first before changing password
		
		User user = currentUser;
		user.setPassword(pwd);		
		success = dao.updatePassword(user);
			
		if(success==true)
		{
			// if success, then update the current user's password
			currentUser.setPassword(pwd);			
		}
		
		return success;
	}*/
	
	
}
