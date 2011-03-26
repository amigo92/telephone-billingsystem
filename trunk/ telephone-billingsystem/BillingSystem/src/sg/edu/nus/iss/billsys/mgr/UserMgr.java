package sg.edu.nus.iss.billsys.mgr;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.tools.*;
import sg.edu.nus.iss.billsys.dao.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Yu Chui Chi
 *
 */

public class UserMgr {
		
	private UserDao dao;
	private static User currentUser;
	
	/*
	 * Default Constructor
	 */
	public UserMgr(){
		dao = new UserDao();
		currentUser = null;
	}
	
	public static String getCurrentAuthUserRole()
	{		
		return currentUser != null ? currentUser.getRole() : null;
	}
	
	public String getCurrentAuthUserId()
	{
		if(currentUser!=null)
			return currentUser.getUsername();
		else
			return null;
	}
	
	public String getAuthUserRole(String authUserId)
	{		
		if(currentUser!=null)
			return currentUser.getRole();
		else
			return null;		
	}
	
	public boolean isValidAuthUser(String userId, String pwd)
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
	public static void logout(){
		currentUser = null;
	}
	
	public Boolean changePassword(String pwd)
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
	}
	
	
}
