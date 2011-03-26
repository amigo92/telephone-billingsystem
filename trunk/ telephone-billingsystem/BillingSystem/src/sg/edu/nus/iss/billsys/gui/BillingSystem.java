package sg.edu.nus.iss.billsys.gui;

import sg.edu.nus.iss.billsys.*;

import java.util.*;

public class BillingSystem  {
	
	
	private  BillingWindow billingWindow;
	private  LoginForm loginForm;
	private String role;

	public BillingSystem () {
	  
	    }	
	public static void main(String[] args) {
		BillingSystem manager = new BillingSystem ();
	    manager.start ();
	}
	public void start() {
		
		login();
		//startBillingWindow();
	}
    public void login() {
    	loginForm = new LoginForm();
		loginForm.setVisible (true);		
	}

	
    public BillingWindow getSubscriptionWindow() {
	        return billingWindow;
	}



}
