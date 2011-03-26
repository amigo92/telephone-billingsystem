package sg.edu.nus.iss.billsys.gui;


import sg.edu.nus.iss.billsys.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;

public class BillingWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private BillingSystem     manager;
    private JPanel contentPane;
    private BillingWindow window;
    private String role;

    private WindowListener windowListener = new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
            System.exit(0);
        }
    };

    public BillingWindow (BillingSystem manager) {
        super ("Billing System > Subscription");
       
        this.manager = manager;
        //role = manager.getRole();
        window = this;
        addWindowListener(windowListener);
        
        this.setJMenuBar(createMenuBar());
        contentPane = new JPanel(new BorderLayout());
        this.setContentPane(contentPane);
    }
    
    public BillingWindow () {
        super ("Billing System > Subscription");
       
        //role = manager.getRole();
        window = this;
        addWindowListener(windowListener);
        
        this.setJMenuBar(createMenuBar());
        contentPane = new JPanel(new BorderLayout());
        this.setContentPane(contentPane);
    }
    
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenuItem menuItem;
        JMenu menu;
        menuBar = new JMenuBar();

        menu = new JMenu("Account  ");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);   

        menuItem = new JMenuItem("item1");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	 
		     }
	    });
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menu = new JMenu("Billing  ");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);   

        menuItem = new JMenuItem("View Billing Report");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	   BillingReportView currentPanel = new BillingReportView (window);
		           
		           contentPane.revalidate();
		           contentPane = currentPanel;
		           window.setContentPane(contentPane);         	 
		     }
	    });
        menu.add(menuItem);

	        menuItem = new JMenuItem("Generate Billing Report");
	        menuItem.setMnemonic(KeyEvent.VK_D);
	        menuItem.addActionListener (new ActionListener () {
			     public void actionPerformed (ActionEvent e) {
			    	   BillingReportGenerator currentPanel = new BillingReportGenerator (window);
			           
			           contentPane.revalidate();
			           contentPane = currentPanel;
			           window.setContentPane(contentPane);  	 
			     }
		    });
	        menu.add(menuItem);
        
        
        menu.addSeparator();
        
        menu = new JMenu("Subscription  ");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);   

        menuItem = new JMenuItem("Introduction");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
			       SubscriptionIntroPanel currentPanel = new SubscriptionIntroPanel (window);
		           
		           contentPane.revalidate();
		           contentPane = currentPanel;
		           window.setContentPane(contentPane);	 
		     }
	    });
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Register");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	   SubscriptionRegistrationPanel currentPanel = new SubscriptionRegistrationPanel (window);
		           
		           contentPane.revalidate();
		           contentPane = currentPanel;
		           window.setContentPane(contentPane);     
		     }
	    });
        menu.add(menuItem);
        
        menuItem = new JMenuItem("De-Register");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	   SubscriptionDeRegistrationPanel currentPanel = new SubscriptionDeRegistrationPanel (window);
		           
		           contentPane.revalidate();
		           contentPane = currentPanel;
		           window.setContentPane(contentPane);     
		    	 
		     }
	    });
        menu.add(menuItem);

        menu.addSeparator();

        return menuBar;
    }

}