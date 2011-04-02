package sg.edu.nus.iss.billsys.gui;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BillingWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private BillingSystem manager;
    private JPanel contentPane;
    private BillingWindow window;
    private SubscriptionMgr subscriptionMgr;
    private AccountMgr accountMgr;

    private SubscriptionRegistrationPanel subscriptionRegistrationPanel;
    private SubscriptionDeRegistrationPanel subscriptionDeRegistrationPanel;
    
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
			menuItem = new JMenuItem("Customer Registration");
			menuItem.setMnemonic(KeyEvent.VK_D);
	
				menuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddCustomer currentPanel = new AddCustomer(window);
						contentPane.revalidate();
						contentPane = currentPanel;
						window.setContentPane(contentPane);
						
					}
				});
				menu.add(menuItem);
				menu.addSeparator();
				
				
				menuItem = new JMenuItem("Update Customer Status");
				menuItem.setMnemonic(KeyEvent.VK_D);
				menuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						UpdateCustomerStatus currentPanel = new UpdateCustomerStatus(window);
						contentPane.revalidate();
						contentPane = currentPanel;
						window.setContentPane(contentPane);
					}
				});
				menu.add(menuItem);
				menu.addSeparator();
				
				
				menuItem = new JMenuItem("View Customer Details");
				menuItem.setMnemonic(KeyEvent.VK_D);
				menuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ViewCustomerDetails currentPanel = new ViewCustomerDetails(window);
						contentPane.revalidate();
						contentPane = currentPanel;
						window.setContentPane(contentPane);
					}
				});
				menu.add(menuItem);
				menu.addSeparator();
				
				menuItem = new JMenuItem("Search Customer");
				menuItem.setMnemonic(KeyEvent.VK_D);
				menuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						SearchCustomer currentPanel = new SearchCustomer(window);
						contentPane.revalidate();
						contentPane = currentPanel;
						window.setContentPane(contentPane);
					}
				});
				menu.add(menuItem);
		}
        
		
	
			menu = new JMenu("Subscription  ");
			menu.setMnemonic(KeyEvent.VK_A);
			menuBar.add(menu);
			menuItem = new JMenuItem("Introduction");
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SubscriptionIntroPanel currentPanel = new SubscriptionIntroPanel(
							window);
					contentPane.revalidate();
					contentPane = currentPanel;
					window.setContentPane(contentPane);
				}
			});
			menu.add(menuItem);
			menu.addSeparator();
			
			menuItem = new JMenuItem("Register");
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					SubscriptionRegistrationPanel currentPanel = new SubscriptionRegistrationPanel(
							window);

					contentPane.revalidate();
					contentPane = currentPanel;
					window.setContentPane(contentPane);
				}
			});
			menu.add(menuItem);
			menu.addSeparator();
			
			menuItem = new JMenuItem("De-Register");
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					subscriptionDeRegistrationPanel = new SubscriptionDeRegistrationPanel(
							window);

					contentPane.revalidate();
					contentPane = subscriptionDeRegistrationPanel;
					window.setContentPane(contentPane);

				}
			});
			menu.add(menuItem);
			
			//Billing
			menu = new JMenu("Bill  ");
			menu.setMnemonic(KeyEvent.VK_A);
			menuBar.add(menu);
			menuItem = new JMenuItem("View Bill Report");
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
			menu.addSeparator();
			
			menuItem = new JMenuItem("Generate Bill Report");
			menuItem.setMnemonic(KeyEvent.VK_D);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BillingReportGenerator currentPanel = new BillingReportGenerator(window);

					contentPane.revalidate();
					contentPane = currentPanel;
					window.setContentPane(contentPane);
				}
			});
			menu.add(menuItem);
			menu.addSeparator();

			menuItem = new JMenuItem("Purse Bill Report");
			menuItem.setMnemonic(KeyEvent.VK_P);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MgrFactory.getBillMgr().purge();
					JOptionPane.showMessageDialog(window, "Existing bills purged successfully.");
					
					BillingReportGenerator currentPanel = new BillingReportGenerator(window);

					contentPane.revalidate();
					contentPane = currentPanel;
					window.setContentPane(contentPane);
				}
			});
			menu.add(menuItem);
		
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
        menu.addSeparator();
        
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

        return menuBar;
    }

	public void setSubscriptionMgr() {
		try {
			this.subscriptionMgr = MgrFactory.getSubscriptionMgr();
		} catch (BillingSystemException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"Error" ,0);
		} catch (Exception e){
			JOptionPane.showMessageDialog(window, e.getMessage(),"Error" ,0);
		}
	}

	public SubscriptionMgr getSubscriptionMgr() {
		if(subscriptionMgr == null)
			setSubscriptionMgr();
		
		return subscriptionMgr;
	}
	
	public void setAccountMgr() {
		try {
			this.accountMgr = MgrFactory.getAccountMgr();
		} catch (BillingSystemException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"Error" ,0);
		}catch (Exception e){
			JOptionPane.showMessageDialog(window, e.getMessage(),"Error" ,0);
		}
	}

	public AccountMgr getAccountMgr() {
		if(accountMgr == null)
			setAccountMgr();
		return accountMgr;
	}
	
	public void refreshSubRegPanel(String accountNo){
		subscriptionRegistrationPanel = new SubscriptionRegistrationPanel(
				window, accountNo);

		contentPane.revalidate();
		contentPane = subscriptionRegistrationPanel;
		window.setContentPane(contentPane);
	}
	// for customer detail panel
	public void refreshSubRegPanel(String customerID, String accountNo){

		subscriptionRegistrationPanel = new SubscriptionRegistrationPanel(
				window, customerID, accountNo);

		contentPane.revalidate();
		contentPane = subscriptionRegistrationPanel;
		window.setContentPane(contentPane);
	}
	
	public void refreshSubDeRegPanel(String accountNo){
		subscriptionDeRegistrationPanel = new SubscriptionDeRegistrationPanel(
				window, accountNo);

		contentPane.revalidate();
		contentPane = subscriptionDeRegistrationPanel;
		window.setContentPane(contentPane);
	}
	public void refreshPanelForViewCust(String NRIC){
		ViewCustomerDetails  VCust= new ViewCustomerDetails(
				window, NRIC);

		contentPane.revalidate();
		contentPane = VCust;
		window.setContentPane(contentPane);
	}


}