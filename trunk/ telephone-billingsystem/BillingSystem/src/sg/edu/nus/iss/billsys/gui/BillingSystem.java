package sg.edu.nus.iss.billsys.gui;

import sg.edu.nus.iss.billsys.*;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;

import java.awt.EventQueue;
import java.util.*;

import javax.swing.*;

public class BillingSystem extends JFrame {
	
	private static BillingSystem frame;
	
	private  BillingWindow billingWindow;

	public BillingSystem () {
		super();
	}	
	
	public static void main(String[] args) {
		Runnable runner = new Runnable() {

			@Override
			public void run() {
				frame = new BillingSystem();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				updateContentPane(new Login());
			}
			
		};
		
		EventQueue.invokeLater(runner);
		
		
//		BillingSystem manager = new BillingSystem ();
//	    manager.start ();
	}
	
	private static void displayMenuBar(){
		frame.setJMenuBar(new BillingMenu()); //TODO
//		if(MgrFactory.getUserMgr().getCurrentAuthUserId() != null){
//			frame.setJMenuBar(new BillingMenu());
//		}
//		else{
//			frame.setJMenuBar(null);
//		}
	}
	
	public static void updateContentPane(JPanel newPanel){
		displayMenuBar();
		
		frame.setContentPane(newPanel);
		frame.pack();
        frame.setVisible(true);
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
