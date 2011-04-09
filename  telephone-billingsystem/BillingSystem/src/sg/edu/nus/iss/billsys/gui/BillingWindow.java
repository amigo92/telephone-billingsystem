package sg.edu.nus.iss.billsys.gui;

import sg.edu.nus.iss.billsys.constant.*;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BillingWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
    private JPanel contentPane;
    private BillingWindow window;
    private SubscriptionMgr subscriptionMgr;
    private AccountMgr accountMgr;

    private SubscriptionPanel subscriptionPanel;
    
    private boolean isAdmin = false;

    
    public boolean isAdmin() {
		return isAdmin;
	}

	private WindowListener windowListener = new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
            System.exit(0);
        }
    };

    public BillingWindow (BillingSystem manager) {
        super ("Billing System");
       
        window = this;
        addWindowListener(windowListener);
        
        this.setJMenuBar(createMenuBar());
        contentPane = new JPanel(new BorderLayout());
        this.setContentPane(contentPane);
    }
    
    public BillingWindow () {
        super ("Billing System");

        window = this;
        addWindowListener(windowListener);
        
        this.setJMenuBar(createMenuBar());
        contentPane = new JPanel(new GridLayout(0,1));
        JLabel title = new JLabel("<html> <h2>Welcome to Billing System</h2>  </html>");   
    	title.setHorizontalAlignment(SwingConstants.CENTER );
    	
        JLabel teamInfo = new JLabel("<html> <h3> MTeh SE19 [Team 6]</h3> </html>");   
        teamInfo.setHorizontalAlignment(SwingConstants.CENTER );
        contentPane.add(title);
        contentPane.add(teamInfo);
        
        this.setContentPane(contentPane);
    }
    
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenuItem menuItem;
        JMenu menu;
        menuBar = new JMenuBar();
        
        UserRole role = (UserRole) SessionMgr.map.get(SessionKeys.USER_ROLE); 

        if (role != null && role.toString().equalsIgnoreCase(UserRole.ADMIN.toString())) {
        	isAdmin = true;
        }
        
      
			menu = new JMenu("Account  ");
			menuBar.add(menu);
			if (isAdmin) {
			menuItem = new JMenuItem("Customer Registration");
	
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
			  }
				
//				menuItem = new JMenuItem("Update Customer Status");
//				menuItem.setMnemonic(KeyEvent.VK_D);
//				menuItem.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						
//						UpdateCustomerStatus currentPanel = new UpdateCustomerStatus(window);
//						contentPane.revalidate();
//						contentPane = currentPanel;
//						window.setContentPane(contentPane);
//					}
//				});
//				menu.add(menuItem);
//				menu.addSeparator();
				
				
				menuItem = new JMenuItem("View Customer Details");
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
				menuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						SearchCustomer currentPanel = new SearchCustomer(window);
						contentPane.revalidate();
						contentPane = currentPanel;
						window.setContentPane(contentPane);
					}
				});
				menu.add(menuItem);
		
        
			if (isAdmin) {
	
			menu = new JMenu("Subscription  ");
			menuBar.add(menu);
			menuItem = new JMenuItem("Charges");
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
			
			menuItem = new JMenuItem("Manage Subscription");
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					SubscriptionPanel currentPanel = new SubscriptionPanel(
							window);

					contentPane.revalidate();
					contentPane = currentPanel;
					window.setContentPane(contentPane);
				}
			});
			menu.add(menuItem);
			}
			
			//Billing
			menu = new JMenu("Bill  ");
			menuBar.add(menu);
			menuItem = new JMenuItem("Bill Report");
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
		
		// Complaint
        menu = new JMenu("Complaint  ");
        menuBar.add(menu);   

        menuItem = new JMenuItem("Log Complaint");
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
        menuBar.add(menu);   

        menuItem = new JMenuItem("Logout");
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
			JOptionPane.showMessageDialog(window, e.getMessagebyException(),"Error :setSubscriptionMgr" ,0);
		} catch (Exception e){
			JOptionPane.showMessageDialog(window, e.getMessage(),"Error :setSubscriptionMgr" ,0);
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
			JOptionPane.showMessageDialog(window, e.getMessagebyException(),"Error: setAccountMgr" ,0);
		}catch (Exception e){
			JOptionPane.showMessageDialog(window, e.getMessage(),"Error: setAccountMgr" ,0);
		}
	}

	public AccountMgr getAccountMgr() {
		if(accountMgr == null)
			setAccountMgr();
		return accountMgr;
	}
	
	public void refreshSubRegPanel(String accountNo){
		subscriptionPanel = new SubscriptionPanel(
				window, accountNo);

		contentPane.revalidate();
		contentPane = subscriptionPanel;
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