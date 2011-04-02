package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */

import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;

import sg.edu.nus.iss.billsys.util.BillingUtil;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SubscriptionDeRegistrationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow        window;
	
	private JTextField           customerID;
	private SubscriptionMgr      manager;
	private String accountNo;
	private JPanel deRegisterPanel;
	private List<Feature> features;
	private ArrayList<Customer>	customersList;

	
    public SubscriptionDeRegistrationPanel (BillingWindow window) {   
    	 initialize(window);	
    }
    public SubscriptionDeRegistrationPanel (BillingWindow window, String accountNo) {
    	this.accountNo = accountNo;
   	 	initialize(window);	
    }
    public void initialize(BillingWindow window){
    	this.window = window;
		manager = window.getSubscriptionMgr();
		
    	customersList =  window.getAccountMgr().getAllActiveCustomers();
	
	    setLayout (new BorderLayout());
	    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	    deRegisterPanel = new JPanel(new BorderLayout());  
//	    if(accountNo != null){
//	    	deRegisterPanel.add("North", deRegisterScrollPanel());
//	    }

	    add ("North", createFormPanel());      
	    add ("Center", deRegisterPanel);
	      
    }
    
	

    private JPanel createFormPanel () {  	
    	JLabel title = new JLabel("<html><center><h3>De-Registration</h3></center></html>");   
    	title.setHorizontalAlignment(SwingConstants.CENTER );
    	
    	JPanel p = new JPanel (new GridLayout (0,2));
    	
        p.add ( new JLabel ("Please select a customer:   "));
        p.add ( new JLabel (""));

        p.add(createCustomerComboBox());
        p.add ( new JLabel (""));
  
        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", title);     
        bp.add ("South", p);     

        return bp;
    }
    private JComboBox createCustomerComboBox () {  	
	    JComboBox accountBox = new JComboBox();
	    int selectedIndex = 0;
	    
	    for(Customer c :customersList){
	    	accountBox.addItem(c.getName()+ "-" + c.getNric());
	    	
	    	 if(accountNo != null){  		 
	    		if( c.getAccountId().equals(accountNo))
	    			selectedIndex = customersList.indexOf(c);
	    	 }
	    }
	 
	    accountBox.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		   JComboBox cb = (JComboBox)e.getSource();
	    		   Customer  selectedCustomer = customersList.get(cb.getSelectedIndex());
	    		   accountNo = selectedCustomer.getAcct().getAcctNo();
	    		   deRegisterPanel.revalidate();
	    		   deRegisterPanel.removeAll();
	    		   deRegisterPanel.add("North", deRegisterScrollPanel());
	    		   deRegisterPanel.repaint();
	        }
	    });
	 
	    accountBox.setSelectedIndex(selectedIndex);
	    return accountBox;
    }
    private JScrollPane deRegisterScrollPanel () {
		JPanel p1 = new JPanel (new BorderLayout());
		JPanel p = new JPanel (new GridLayout (0, 3));

		try
		{
	    	if(accountNo != null){
	    		p1.add ("North", new JLabel ("Existing Subscripiton Information:"));
	    		
				List<SubscriptionPlan> subscribedPlans = manager.getAccountSubscriptions(accountNo);
				
				if(subscribedPlans != null)
				{
					 for (final SubscriptionPlan plan : subscribedPlans)
					 {
						 String strDateInfo =  "     " + BillingUtil.getDateTimeStr(plan.getDateCommenced())
							+	(plan.getDateTerminated() == null? " ": "  -  " ) 
							+ BillingUtil.getDateTimeStr(plan.getDateTerminated());
			   
						p.add ( new JLabel (plan.getPlanDescription()));
						p.add ( new JLabel (strDateInfo));	
						JButton b;
						if(plan.getDateTerminated() == null || plan.getDateTerminated().after(BillingUtil.getCurrentDate())) 
							b = new JButton ("De-Register");
						else
							b = new JButton ("Extend Subscription");
		   			    b.addActionListener (new ActionListener () {
		    			     public void actionPerformed (ActionEvent e) {		    			    	 	
		    			    	 	SubscriptionPlanDelDialog d = new SubscriptionPlanDelDialog (window, accountNo, plan, null);
		    		                d.pack();
		    		                d.setVisible (true);
		    			     }
					    });
		   			    
//		   			    b.setPreferredSize(new Dimension(200, 30));
//		   			    JPanel btnp= new JPanel();
//		   			    btnp.add(b);    
		   			    p.add(b);
		   			    
		   			    features = plan.getOptionalFeatures();	
		   			    

						for(final Feature feature: features){			
							 strDateInfo =  "     " + BillingUtil.getDateTimeStr(feature.getDateCommenced())
								+	(feature.getDateTerminated() == null? " ": "  -  " ) 
								+   BillingUtil.getDateTimeStr(feature.getDateTerminated());
				   
							p.add ( new JLabel ("       " + feature.getName()));
							p.add ( new JLabel (  strDateInfo));

							JButton b2;
							if(feature.getDateTerminated() == null || feature.getDateTerminated().after(BillingUtil.getCurrentDate())) 
								b2 = new JButton ("De-Register");
							else
								b2 = new JButton ("Extend Subscription");
			   			    b2.addActionListener (new ActionListener () {
			    			     public void actionPerformed (ActionEvent e) {
			    			    	 	SubscriptionPlanDelDialog d = new SubscriptionPlanDelDialog (window, accountNo, plan, feature);
			    			    	 	d.pack();
				    		            d.setVisible (true);
			    			     }
						    });
			   			    
//			   			    b2.setPreferredSize(new Dimension(200, 30));
//			   			    btnp= new JPanel();
//			   			    btnp.add(b2);
//				   			p.add(btnp);
			   			    p.add(b2);
						}				  
					 }
				}
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(window, ex.getMessage(),"" ,0);
		}
		p1.add("Center", p);

        JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add( p1 );
       
        return scrollPane;
    }
}
    
   
    	