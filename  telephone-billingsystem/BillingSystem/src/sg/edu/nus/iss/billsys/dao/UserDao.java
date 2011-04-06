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
 * This class is a implementation class for accessing the data layer to get the user information
 * It extends the genericdao to inherit the functionality of accessing the physical file and to return a 
 * raw data of two dimensional string array , which will in turn mapped to the domain objects for easy 
 * manipulation , This class also provides implementation to save back the domain object to the data file.
 * 
 * This class will be inheriting the Interface class to implement the public methods which will be used by the 
 * manager classes for the data read / update / create functionalities.
 *
 */
public class UserDao extends GenericDao implements IUserDao{
	
	private final static String  USER_DATA_FILE="data/User.txt";//This Constant is to specify the file path to load the Authorised user information
	private static final int COL_LENGTH=3; //This constant would give the number of columns expected in the file 
	private List<User> listUser=new ArrayList<User>();//Instance variable to hold the data from the parsed data of the file
	
	/*
	 * This method will implement the logic to map the raw data of two dimensional array to the Domain objects which is then used by the public methods of the Dao.
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.GenericDao#objectDataMapping()
	 */
	@Override
	protected final void objectDataMapping() throws BillingSystemException{
		String[][] data=getDataAsArray(USER_DATA_FILE);
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
		//This method will not be implemented , since there is no save use case for this data,only read operation is required on the User Authentication
	}	
	/*
	 * The Constructor intialisation also invokes the call to map the raw data parsed from the file to domain object.
	 */
	protected UserDao() throws BillingSystemException{
	 this.objectDataMapping();
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
	
	/**
	 * get the user from the data file base on the username
	 * @param username the user name
	 * @return return the user object if found, return null if not found 
	 */
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
