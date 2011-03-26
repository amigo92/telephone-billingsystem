package sg.edu.nus.iss.billsys.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class BillingMenu extends JMenuBar {

	public BillingMenu(){
	       JMenuItem menuItem;
	        JMenu menu;

	        menu = new JMenu("Customer  ");
	        menu.setMnemonic(KeyEvent.VK_A);
	        add(menu);   

	        menuItem = new JMenuItem("Customer Registration");
	        menuItem.setMnemonic(KeyEvent.VK_D);
	        menuItem.addActionListener (new ActionListener () {
			     public void actionPerformed (ActionEvent e) {
			    	 //new CustomerRegistration().setVisible(true);
			     }
		    });
	        menu.add(menuItem);
	        
	        menu.addSeparator();
	        
	        JMenuItem mntmActivationdeativiation = new JMenuItem("Activation/Deativiation");
	        menu.add(mntmActivationdeativiation);
	        
	        menu.addSeparator();
	        
	        JMenuItem mntmViewCustomerDetails = new JMenuItem("View Customer Details");
	        menu.add(mntmViewCustomerDetails);
	        
	        menu.addSeparator();
	        
	        JMenuItem mntmSearchCustomer = new JMenuItem("Search Customer");
	        menu.add(mntmSearchCustomer);
	        
	        menu = new JMenu("Billing  ");
	        menu.setMnemonic(KeyEvent.VK_A);
	        add(menu);   

	        menuItem = new JMenuItem("View Bill");
	        menuItem.setMnemonic(KeyEvent.VK_D);
	        menuItem.addActionListener (new ActionListener () {
			     public void actionPerformed (ActionEvent e) {
			    	 
			     }
		    });
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Generate Bill");
	        menuItem.setMnemonic(KeyEvent.VK_D);
	        menuItem.addActionListener (new ActionListener () {
			     public void actionPerformed (ActionEvent e) {
			    	 
			     }
		    });
	        menu.add(menuItem);
	        
	        menu.addSeparator();
	        
	        menu = new JMenu("Subscription  ");
	        menu.setMnemonic(KeyEvent.VK_A);
	        add(menu);   

	        menuItem = new JMenuItem("Introduction");
	        menuItem.setMnemonic(KeyEvent.VK_D);
	        menuItem.addActionListener (new ActionListener () {
			     public void actionPerformed (ActionEvent e) {
//				       SubscriptionIntroPanel regPanel = new SubscriptionIntroPanel (window);
//			           
//			           JPanel p = new JPanel ();
//			           p.setLayout (new GridLayout(0, 1));
//			           p.add (regPanel);
//			           
//			           contentPane.revalidate();
//			           contentPane = regPanel;
//			           window.setContentPane(contentPane);	 
			     }
		    });
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("Register");
	        menuItem.setMnemonic(KeyEvent.VK_D);
	        menuItem.addActionListener (new ActionListener () {
			     public void actionPerformed (ActionEvent e) {
			    	  /* SubscriptionRegistrationPanel regPanel = new SubscriptionRegistrationPanel (window);
			           
			           JPanel p = new JPanel ();
			           p.setLayout (new GridLayout(0, 1));
			           p.add (regPanel);
			           
			           contentPane.revalidate();
			           contentPane = regPanel;
			           window.setContentPane(contentPane);*/
			         
			     }
		    });
	        menu.add(menuItem);
	        
	        menuItem = new JMenuItem("De-Register");
	        menuItem.setMnemonic(KeyEvent.VK_D);
	        menuItem.addActionListener (new ActionListener () {
			     public void actionPerformed (ActionEvent e) {
			    	 
			     }
		    });
	        menu.add(menuItem);

	        menu.addSeparator();
	        
	        menu = new JMenu("Help  ");
	        menu.setMnemonic(KeyEvent.VK_H);
	        add(menu);   

	        menuItem = new JMenuItem("Log out");
	        menuItem.setMnemonic(KeyEvent.VK_L);
	        menuItem.addActionListener (new ActionListener () {
			     public void actionPerformed (ActionEvent e) {
			    	 BillingSystem.updateContentPane(new Login());
			     }
		    });
	        menu.add(menuItem);
	}
}
