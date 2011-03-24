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
	private User currentUser;
	
	/*
	 * Default Constructor
	 */
	public UserMgr(){
		dao = new UserDao();
		currentUser = null;
	}
	
	/*public void setCurrentAuthUserId(String authUserId)
	{		
		currentLoginUserId = authUserId;
	}*/
	
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
	
	public Boolean isValidAuthUser(String userId, String pwd)
	{		 
		User user = dao.getUserByUsername(userId);
		if(user==null)
			return false; //username not found		
		else if(user.getPassword()!=pwd)
			return false; //password invalid
		else
		{
			currentUser = user;
			return true;
		}
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