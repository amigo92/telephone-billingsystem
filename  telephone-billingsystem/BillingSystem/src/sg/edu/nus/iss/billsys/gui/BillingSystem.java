package sg.edu.nus.iss.billsys.gui;

import sg.edu.nus.iss.billsys.*;

import java.util.*;

public class BillingSystem  {
	
	
	private  BillingWindow billingWindow;

	public BillingSystem () {
	  
	    }	
	public static void main(String[] args) {
		BillingSystem manager = new BillingSystem ();
	    manager.start ();
	}
	public void start() {
		startSubscriptonWindow();
	}
	public void startSubscriptonWindow() {
		billingWindow = new BillingWindow (this);
		billingWindow.pack ();
		billingWindow.setSize(600, 600);
		billingWindow.setVisible (true);	
	}
	
    public BillingWindow getSubscriptionWindow() {
	        return billingWindow;
	    }

	public void shutdown () {
	       System.exit(0);
	}

}
