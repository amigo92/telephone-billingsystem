package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.billsys.vo.User;
/**
 * 
 * @author Veera
 *
 */
public class UserDao extends GenericDao{
	
	private List<User> listUser=new ArrayList<User>();
	
	@Override
	protected void objectDataMapping(String[][] data) {
		
		List<User> listUser=new ArrayList<User>();
		
		for(int i=0;i<data.length;i++){
	    	
	    	User usr=new User();
	    		
	    	usr.setUsername(data[i][0]);
	    	usr.setPassword(data[i][1]);
	    	//usr.setRole(data[i][2]);
	    	
	    	listUser.add(usr);	
	    }

		
		this.listUser=listUser;
	}
	
	@Override
	protected void saveObjectData(){
	int cnt=0;
	
	String data[][]=new String[listUser.size()][3];
		
	for (Iterator<User> iter = listUser.iterator(); iter.hasNext();) {
	
		User element = (User) iter.next();			
			
			data[cnt][0]=element.getUsername();
			data[cnt][1]=element.getPassword();
			//data[cnt][2]=element.getRole();
			
			cnt++;				
		}
		saveUserData(data);
	}	
	
	public UserDao() {
	 this.objectDataMapping(getUserData());
	}
	
	
	public User getUserByUsername(String username){
		for(User user : listUser){
			if(user.getUsername().equals(username)){
				return user;
			}
		}
		
		return null;
	}
	
	/// chuichi: changed the return type to Boolean to tell whether the password is updated 
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
	
	@Override
	protected boolean validateData(String[][] data) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
