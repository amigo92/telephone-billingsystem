package sg.edu.nus.iss.billsys.vo;

import sg.edu.nus.iss.billsys.constant.UserRole;

public class User {
	
	private String username;
	private String password;
	private UserRole role;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	
	

}
