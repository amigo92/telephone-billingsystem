package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */
import sg.edu.nus.iss.billsys.*;
import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.AccountMgr;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.GuiConfirmDialog;
import sg.edu.nus.iss.billsys.util.BillingUtil;
import sg.edu.nus.iss.billsys.vo.Account;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;



import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class SubscriptionRegistrationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow        window;
	
	private JTextField           customerID;
	private JPanel featurePanel;

	private PlanType[]       listOfPlanType;
	private SubscriptionMgr      manager;
	private String[] planNames;
	private PlanType planType;
	private String accountNo;
	private int selectedPlanIndex;
	private List<SubscriptionPlan> subscribedPlans;
	private AccountMgr accountMgr;
	private ArrayList<Customer>  customersList;
    
    public SubscriptionRegistrationPanel (BillingWindow window) {
		initialize(window);
    }
    public SubscriptionRegistrationPanel (BillingWindow window, String accountNo) {
		this.accountNo = accountNo;
		initialize(window);
    }
    private void initialize(BillingWindow window){
		this.window = window;
	    manager = window.getSubscriptionMgr();
	    accountMgr = window.getAccountMgr();
    	customersList =  accountMgr.getAllActiveCustomers();
	    
	    setLayout (new BorderLayout());
	    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	   
	    featurePanel = new JPanel(new BorderLayout());

	    add ("North", createFormPanel());      
	    add ("Center", featurePanel);   
  
    }

    private JPanel createFormPanel () {	
    	JLabel title = new JLabel("<html><center><h3>Registration</h3></center></html>");   
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
	        	   featurePanel.revalidate();
	        	   featurePanel.removeAll();
	               featurePanel.add ("North", registerFeaturePanel());
	               featurePanel.repaint();
	        }
	    });
	 
	    accountBox.setSelectedIndex(selectedIndex);
	    return accountBox;
    }

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
  
    private JPanel registerFeaturePanel () {
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
				    		JOptionPane.showMessageDialog(window, ex.getMessage());
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
	 private JScrollPane existingSubscriptionPanel (List<SubscriptionPlan> subscribedPlans) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
//		scrollPane.setSize(740, 500);
		//scrollPane.setLayout(new BorderLayout());
		
	
		JPanel sp = new JPanel (new GridLayout (0, 3));
			
		sp.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		JLabel titleLabel = new JLabel ("Existing Subscription Information:                     ");
		
		sp.add ( titleLabel);
		sp.add(new JLabel("                                                         "));
		sp.add(new JLabel("                                                         "));

		for(final SubscriptionPlan plan: subscribedPlans){
			String strDateInfo =  "     " + BillingUtil.getDateTimeStr(plan.getDateCommenced())
						+	(plan.getDateTerminated() == null? " ": "  -  " ) 
						+ BillingUtil.getDateTimeStr(plan.getDateTerminated());
		    
			sp.add (new JLabel (plan.getPlanDescription()));						
			sp.add (new JLabel(strDateInfo));
			
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
			    
//			    b.setPreferredSize(new Dimension(200, 30));
//			    JPanel btnp= new JPanel();
//			    btnp.add(b);    
			    sp.add(b);
			
			List<Feature> features = plan.getOptionalFeatures();	
			for(final Feature feature: features){
				strDateInfo =  "     " + BillingUtil.getDateTimeStr(feature.getDateCommenced())
					+ (feature.getDateTerminated() == null? " ": "  -  " ) 
					+ BillingUtil.getDateTimeStr(feature.getDateTerminated());
		
				sp.add ( new JLabel ("        " + feature.getName()));
				sp.add(new JLabel(strDateInfo));
				
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
   			    
//   			    b2.setPreferredSize(new Dimension(200, 30));
//   			    btnp= new JPanel();
//   			    btnp.add(b2);
//	   			p.add(btnp);
   			    sp.add(b2);
			}
		}
		
		scrollPane.getViewport().add(sp );
		return scrollPane;			    	
	 }
}