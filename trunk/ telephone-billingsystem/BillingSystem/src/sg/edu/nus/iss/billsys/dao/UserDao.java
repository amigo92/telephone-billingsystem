package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.billsys.vo.User;

public class UserDao extends GenericDao{
	
	private List<User> listUser=new ArrayList<User>();
	
	@Override
	protected void objectDataMapping(String[][] data) {
		
		List<User> listUser=new ArrayList<User>();
		
		for(int i=0;i<data.length;i++){
	    	
	    	User usr=new User();
	    		
	    	usr.setUserId(data[i][0]);
	    	usr.setUsername(data[i][1]);
	    	usr.setPassword(data[i][2]);
	    	usr.setRole(data[i][3]);
	    	
	    	listUser.add(usr);	
	    }

		
		this.listUser=listUser;
	}
	
	@Override
	protected void saveObjectData(){
	int cnt=0;
	
	String data[][]=new String[listUser.size()][4];
		
	for (Iterator iter = listUser.iterator(); iter.hasNext();) {
	
		User element = (User) iter.next();			
			
			data[cnt][0]=element.getUserId();
			data[cnt][1]=element.getUsername();
			data[cnt][2]=element.getPassword();
			data[cnt][3]=element.getRole();
			
			cnt++;				
		}
		saveUserData(data);
	}	
	
	public UserDao() {
	 this.objectDataMapping(getUserData());
	}
	
	
	public User getUserByUsername(String username){
		User usr=null;
		
		for (Iterator iter = listUser.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
			if(element.getUsername().equals(username)){
				usr=element;
				break;
			}
				
		}
		
		return usr;
	}
	
	public void updatePassword(User usr){
		
		int cnt=0;
		for (Iterator iter = listUser.iterator(); iter.hasNext();) {
			User element = (User) iter.next();
			
			if(element.getUserId().equals(usr.getUserId())){
				listUser.set(cnt, usr);
				break;
			}
			cnt++;				
		}
			
		
	}
	
	@Override
	protected boolean validateData(String[][] data) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
