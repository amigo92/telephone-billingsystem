package sg.edu.nus.iss.billsys.gui;

import sg.edu.nus.iss.billsys.*;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;


import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
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
				frame.setExtendedState(Frame.MAXIMIZED_BOTH); 
				
				updateContentPane(new Login());
			}
			
		};
		
		EventQueue.invokeLater(runner);
	}
	
	private static void displayMenuBar(){
		if(MgrFactory.getUserMgr().getCurrentAuthUserId() != null){
			frame.setJMenuBar(new BillingMenu());
		}
		else{
			frame.setJMenuBar(null);
		}
	}
	
	public static void updateContentPane(JPanel newPanel){
		displayMenuBar();
		
		frame.setContentPane(newPanel);
		frame.validate();
        frame.setVisible(true);
	}
	
	public static void setMsg(String msg){
		JOptionPane.showMessageDialog(frame, msg);
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
