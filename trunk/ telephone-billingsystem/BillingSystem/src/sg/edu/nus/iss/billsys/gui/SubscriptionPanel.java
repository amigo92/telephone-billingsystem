package sg.edu.nus.iss.billsys.gui;

import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.mgr.AccountMgr;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**
 * @author Ma Huazhen
 *
 */
public class SubscriptionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow        window;
	private SubscriptionMgr      manager;
	private AccountMgr accountMgr;

	private JPanel bottomFormPanel;

	private PlanType[] listOfPlanType;
	private String[] planNames;
	private PlanType planType;
	private String accountNo;
	private int selectedPlanIndex;
	private List<SubscriptionPlan> subscribedPlans;
	private ArrayList<Customer>  customersList;
   
	
    public SubscriptionPanel (BillingWindow window) {
		initialize(window);
    }
    public SubscriptionPanel (BillingWindow window, String accountNo) {
		this.accountNo = accountNo;
		initialize(window);
    }
    /**
     * Initialize the Panel
     */
    private void initialize(BillingWindow window){
		this.window = window;
	    manager = window.getSubscriptionMgr();
	    accountMgr = window.getAccountMgr();
    	customersList =  accountMgr.getAllActiveCustomers();
    	
    	setLayout (new BorderLayout());
 	    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
 	 	
	    bottomFormPanel = new JPanel(new BorderLayout());
  
	    add ("North", createFormPanel());      
	    add ("Center", bottomFormPanel); 
    }
   /**
    *  Render title panel
    * Active customer list
    * Register subscription plan panel
    */
    private JPanel createFormPanel () {	
    	JLabel title = new JLabel("<html><center><h3>Manage Subscription</h3></center></html>");   
    	title.setHorizontalAlignment(SwingConstants.CENTER );

    	JPanel p = new JPanel (new GridLayout(0,2));
        p.add (new JLabel("Please select a customer:"));
        p.add (new JLabel(" "));

        p.add( createCustomerComboBox());
        p.add (new JLabel(" "));

        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", title);
        bp.add ("Center", p);
        bp.add("South", registerSubscriptionPlanPanel());
     
        return bp;
    }
    
    /**  
     * Render Active Customer List*/
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
	    		   bottomFormPanel.revalidate();
	    		   bottomFormPanel.removeAll();
	    		   bottomFormPanel.add ("North", createBottomFormPanel());
	    		   bottomFormPanel.repaint();
	        }
	    });
	 
	    accountBox.setSelectedIndex(selectedIndex);
	    return accountBox;
    }

    /**
     * Register Subscription Plan Panel*/
    private JPanel registerSubscriptionPlanPanel () {
    	JPanel p = new JPanel (new GridLayout (0,2));
    
    	p.add(createComboBox());
	    JButton b = new JButton ("Register new Plan");
         b.addActionListener (new ActionListener () {
             public void actionPerformed (ActionEvent e) {
            	SubscriptionPlanAddDialog d = new SubscriptionPlanAddDialog (window, planType, accountNo);
                d.pack();
                d.setVisible (true);
             }
         });
         p.add(b);

        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", new JLabel ("Register New Subscription Plan:   "));
        bp.add ("Center", p);
      
        return bp;
    }
    
    /**
     * ComboBox for Subscription Plans*/
    private JComboBox createComboBox () {  	
	    listOfPlanType = manager.getAllPlanType();
	   		
	    planNames = new String[listOfPlanType.length];
	    		
	    for (int i = 0 ; i <listOfPlanType.length; i ++ ) {
	    	planNames[i] = listOfPlanType[i].name;
	    }     
	    JComboBox planTypeBox = new JComboBox(planNames);

	    planTypeBox.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		   JComboBox cb = (JComboBox)e.getSource();
	    		   planType = listOfPlanType[cb.getSelectedIndex()];
	            }
	    });
	 
	    planTypeBox.setSelectedIndex(0);
	    return planTypeBox;
    }
    
    /** 
     * Register Feature Panel & existing subscription information */
    private JPanel createBottomFormPanel () {
    	JPanel bp = new JPanel (new BorderLayout());
		JPanel p = new JPanel (new GridLayout (0, 2));

		if(accountNo != null){
			subscribedPlans = manager.getAccountSubscriptions(accountNo);	
			
			if(subscribedPlans !=null)
			{	
				p.add(new JLabel ("Register New Feature: "));
				p.add(new JLabel(""));
				
				JButton b = new JButton ("Register new feature");
		        b.addActionListener (new ActionListener () {
		        public void actionPerformed (ActionEvent e) {
		        	try{		        		
		        		SubscriptionPlanAddFDialog d = new SubscriptionPlanAddFDialog(window,accountNo, subscribedPlans.get(selectedPlanIndex));
		        		d.pack();
		        		d.setVisible(true);
			    
			        	}catch(Exception ex){
				    		JOptionPane.showMessageDialog(window, ex.getMessage(),"Error", 0);
				    	} 	
			    	}
			        });

				p.add(createFeatureComboBox(subscribedPlans));
				p.add(b);
				
				bp.add ("North", p);
	        	bp.add ("Center", existingSubscriptionPanel (subscribedPlans));
			}
		}
        return bp;
    }
    
    /**
     *  ComboBox registered Subscription Plans , cater for feature subscription*/
    private JComboBox createFeatureComboBox (List<SubscriptionPlan> subscribedPlans) {
    	JComboBox	planBox = new JComboBox();
    	
    	final String[] planDescs = new String[subscribedPlans.size()];
	    for (int i = subscribedPlans.size()-1 ; i >=0 ; i -- ) {
	    	planDescs[i] = subscribedPlans.get(i).getPlanDescription();
	    	planBox.addItem(planDescs[i]);
	    } 

	    planBox.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
    		 		JComboBox cb = (JComboBox)e.getSource();
    		 		selectedPlanIndex = planDescs.length - cb.getSelectedIndex()-1 ;
	            }
	    });
	 
	    planBox.setSelectedIndex(0);
	    return planBox;
    }
    
   /** 
    * Existing Subscription information & de-register button for existing subscriptions*/
	 private JScrollPane existingSubscriptionPanel (List<SubscriptionPlan> subscribedPlans) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setPreferredSize(new Dimension(700,320));
	
		JPanel sp = new JPanel (new GridLayout (0, 3));
			
		sp.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		JLabel titleLabel = new JLabel ("Existing Subscription Information:");
		
		sp.add ( titleLabel);
		sp.add(new JLabel("               "));
		sp.add(new JLabel("                "));

		for(final SubscriptionPlan plan: subscribedPlans){
			String strDateInfo =  "     " + TimeUtils.getDisplayDateTimeStr(plan.getDateCommenced())
						+	(plan.getDateTerminated() == null? " ": "  -  " ) 
						+ TimeUtils.getDisplayDateTimeStr(plan.getDateTerminated());
		    
			sp.add (new JLabel (plan.getPlanDescription()));						
			sp.add (new JLabel(strDateInfo));
			
			
			if(plan.getDateTerminated() == null || plan.getDateTerminated().after(TimeUtils.getCurrentDate())) 
			{
				JButton b= new JButton ("De-Register");
			    b.addActionListener (new ActionListener () {
			     public void actionPerformed (ActionEvent e) {		    			    	 	
			    	 	SubscriptionPlanDelDialog d = new SubscriptionPlanDelDialog (window, accountNo, plan, null);
		                d.pack();
		                d.setVisible (true);
			     }
			    });
			    
			    sp.add(b);	
			}
			else
				sp.add(new JLabel(""));	
			
			List<Feature> features = plan.getOptionalFeatures();	
			for(final Feature feature: features){
				strDateInfo =  "     " + TimeUtils.getDisplayDateTimeStr(feature.getDateCommenced())
					+ (feature.getDateTerminated() == null? " ": "  -  " ) 
					+ TimeUtils.getDisplayDateTimeStr(feature.getDateTerminated());
		
				sp.add ( new JLabel ("        " + feature.getName()));
				sp.add(new JLabel(strDateInfo));
				
				
				if(feature.getDateTerminated() == null || feature.getDateTerminated().after(TimeUtils.getCurrentDate())) 	
				{
					JButton b2 = new JButton ("De-Register");
					b2.addActionListener (new ActionListener () {
    			     public void actionPerformed (ActionEvent e) {
    			    	 	SubscriptionPlanDelDialog d = new SubscriptionPlanDelDialog (window, accountNo, plan, feature);
    			    	 	d.pack();
	    		            d.setVisible (true);
    			     }
			    });
					sp.add(b2);	
				}
				else
					sp.add(new JLabel(""));	
				
			}
		}
		JPanel bp = new JPanel(new BorderLayout());
		bp.add("North", sp);
		scrollPane.getViewport().add(bp );
		
		return scrollPane;			    	
	 }
}