package sg.edu.nus.iss.billsys.gui;


import sg.edu.nus.iss.billsys.*;
import sg.edu.nus.iss.billsys.constant.SessionKeys;
import sg.edu.nus.iss.billsys.constant.UserRole;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;

public class BillingWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private BillingSystem     manager;
    private JPanel contentPane;
    private BillingWindow window;
    private SubscriptionMgr subscriptionMgr;
    
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
        super ("Billing System");
       
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
        
        boolean isAdmin = false;
        UserRole role = (UserRole) SessionMgr.map.get(SessionKeys.USER_ROLE); 

        if (role != null && role.toString().equalsIgnoreCase(UserRole.ADMIN.toString())) {
        	isAdmin = true;
        }
        
        if (isAdmin) {
			menu = new JMenu("Account  ");
			menu.setMnemonic(KeyEvent.VK_A);
			menuBar.add(menu);
			menuItem = new JMenuItem("item1");
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			menu.add(menuItem);
			menu.addSeparator();
		}
        
		if (isAdmin) {
			menu = new JMenu("Billing  ");
			menu.setMnemonic(KeyEvent.VK_A);
			menuBar.add(menu);
			menuItem = new JMenuItem("View Billing Report");
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BillingReportView currentPanel = new BillingReportView(
							window);

					contentPane.revalidate();
					contentPane = currentPanel;
					window.setContentPane(contentPane);
				}
			});
			menu.add(menuItem);
			menuItem = new JMenuItem("Generate Billing Report");
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BillingReportGenerator currentPanel = new BillingReportGenerator(
							window);

					contentPane.revalidate();
					contentPane = currentPanel;
					window.setContentPane(contentPane);
				}
			});
			menu.add(menuItem);
			menu.addSeparator();
		}
		
		if (isAdmin) {
			menu = new JMenu("Subscription  ");
			menu.setMnemonic(KeyEvent.VK_A);
			menuBar.add(menu);
			menuItem = new JMenuItem("Introduction");
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setSubscriptionMgr();
					SubscriptionIntroPanel currentPanel = new SubscriptionIntroPanel(
							window);
					contentPane.revalidate();
					contentPane = currentPanel;
					window.setContentPane(contentPane);
				}
			});
			menu.add(menuItem);
			menuItem = new JMenuItem("Register");
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setSubscriptionMgr();

					SubscriptionRegistrationPanel currentPanel = new SubscriptionRegistrationPanel(
							window);

					contentPane.revalidate();
					contentPane = currentPanel;
					window.setContentPane(contentPane);
				}
			});
			menu.add(menuItem);
			menuItem = new JMenuItem("De-Register");
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setSubscriptionMgr();
					SubscriptionDeRegistrationPanel currentPanel = new SubscriptionDeRegistrationPanel(
							window);

					contentPane.revalidate();
					contentPane = currentPanel;
					window.setContentPane(contentPane);

				}
			});
			menu.add(menuItem);
			menu.addSeparator();
		}
		
		// Complaint
        menu = new JMenu("Complaint  ");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);   

        menuItem = new JMenuItem("Log Complaint");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
			       LogComplaintPanel currentPanel = new LogComplaintPanel (window);
		           
		           contentPane.revalidate();
		           contentPane = currentPanel;
		           window.setContentPane(contentPane);	 
		     }
	    });
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Update Complaint");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	   UpdateComplaintPanel currentPanel = new UpdateComplaintPanel (window);
		           
		           contentPane.revalidate();
		           contentPane = currentPanel;
		           window.setContentPane(contentPane);     
		     }
	    });
        menu.add(menuItem);
        menu.addSeparator();

		// Logout
        menu = new JMenu("Logout  ");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);   

        menuItem = new JMenuItem("Logout");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	 // clear the session
		    	 SessionMgr.map.clear();
		    	
		    	 // open the login form
		 		LoginForm loginForm = new LoginForm();
				loginForm.setVisible (true);
				
				//	close the billing window
				BillingWindow.this.setVisible(false);
				BillingWindow.this.dispose();
		     }
	    });
        menu.add(menuItem);
        menu.addSeparator();

        return menuBar;
    }

	public void setSubscriptionMgr() {
		try {
			this.subscriptionMgr = MgrFactory.getSubscriptionMgr();
		} catch (BillingSystemException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"" ,0);
		}
	}

	public SubscriptionMgr getSubscriptionMgr() {
		return subscriptionMgr;
	}

}