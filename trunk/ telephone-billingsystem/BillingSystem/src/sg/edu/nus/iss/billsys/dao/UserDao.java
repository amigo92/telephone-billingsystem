package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.billsys.constant.UserRole;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.User;
/**
 * 
 * @author Veera
 *
 */
public class UserDao extends GenericDao implements IUserDao{
	
	private static final int COL_LENGTH=3;
	private List<User> listUser=new ArrayList<User>();
	
	@Override
	protected final void objectDataMapping(String[][] data) throws BillingSystemException{
		if(validateData(data,"Authorised User",COL_LENGTH)){
		List<User> listUser=new ArrayList<User>();
		
		for(int i=0;i<data.length;i++){
	    	
	    	User usr=new User();
	    		
	    	usr.setUsername(data[i][0]);
	    	usr.setPassword(data[i][1]);
	    	usr.setRole(getRoleByCode(data[i][2]));
	    	
	    	listUser.add(usr);	
	    }

		
		this.listUser=listUser;
		}
	}
	
	@Override
	public final void saveObjectData() throws BillingSystemException{
//		Veera : This is a method to give a idea of the dao design implementation and not used for any usecase.
	/*	int cnt=0;
	
	String data[][]=new String[listUser.size()][COL_LENGTH];
		
	for (Iterator<User> iter = listUser.iterator(); iter.hasNext();) {
	
		User element = (User) iter.next();			
			
			data[cnt][0]=element.getUsername();
			data[cnt][1]=element.getPassword();
			data[cnt][2]=String.valueOf(element.getRole().ordinal());
			
			cnt++;				
		}
	if(validateData(data,"Authorised User",COL_LENGTH)){
		saveUserData(data);
	}*/
	}	
	
	protected UserDao() throws BillingSystemException{
	 this.objectDataMapping(getUserData());
	}
	
	private UserRole getRoleByCode(String code){
		
		UserRole[] temp=UserRole.values();
		UserRole role=null;
			
			for (int i = 0; i < temp.length; i++) {
				if(String.valueOf(temp[i].ordinal()).equals(code))
					role=temp[i];
					
			}
			return role;
		
		
	}
	
	public User getUserByUsername(String username) throws BillingSystemException{
		User temp=null;
		
		if(username==null && username.length()==0){
			throw new BillingSystemException("Username argument is null");
		}
		
		for(User user : listUser){
			if(user.getUsername().equals(username)){
				temp= user;
			}
		}
		
		return temp;
	}
	
	/// chuichi: changed the return type to Boolean to tell whether the password is updated 
	//Veera : This is a method to give a idea of the dao design implementation and not used for any usecase.
	/*public Boolean updatePassword(User usr){
		
		Boolean updated = false;
		
		int cnt=0;
		for (Iterator<User> iter = listUser.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
			
			if(element.getUsername().equals(usr.getUsername())){
				listUser.set(cnt, usr);
				updated = true;
				break;
			}
			cnt++;				
		}
			
		return updated;
	}*/
	
}
