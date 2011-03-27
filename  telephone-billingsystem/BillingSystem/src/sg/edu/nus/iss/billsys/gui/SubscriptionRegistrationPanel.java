package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */
import sg.edu.nus.iss.billsys.*;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.GuiConfirmDialog;
import sg.edu.nus.iss.billsys.vo.Account;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;



import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SubscriptionRegistrationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow        window;
	private JTextField           customerID;
	private PlanType[]       listOfPlanType;
	private SubscriptionMgr      manager;
	//private JComboBox planTypeBox;
	private String[] planNames;
	private PlanType planType;
	private String accountNo;
	private JPanel featurePanel;
	
    public SubscriptionRegistrationPanel (BillingWindow window) {
    	try
    	{
			this.window = window;
		    manager = MgrFactory.getSubscriptionMgr();
		    setLayout (new BorderLayout());
		    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		    
		    add ("North", createFormPanel());      

		    featurePanel = registerFeaturePanel();
		    add ("Center", featurePanel);
        
    	}
        catch(Exception e){
        	System.out.println(e.getMessage());
        }
    }

    public void refresh () {
    	JOptionPane.showMessageDialog(window, "refresh");

    }

    private JPanel createFormPanel () {
    	JPanel p = new JPanel (new GridLayout (0,2));
    
        customerID = new JTextField (1);
        p.add(customerID);

        JButton b = new JButton ("Validate & Get Subscription Information");
        b.addActionListener (new ActionListener () {
        public void actionPerformed (ActionEvent e) {
        	try{	
	    	   // accountNo = MgrFactory.getAccountMgr().getCustomerDetailsById(customerID.getText()).getAcct().getAcctNo();
        		accountNo = "acc_no1";        	
        		featurePanel.revalidate();
        		featurePanel = registerFeaturePanel();
        		add ("Center", featurePanel);
        	}
	    	catch(Exception ex)
	    	{
	    		JOptionPane.showMessageDialog(window, ex.getMessage());
	    	} 	
    	}
        });
        
        p.add (b);

        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", new JLabel ("Customer ID:   "));
        bp.add ("Center", p);
        bp.add("South", registerSubscriptionPlanPanel());
     
        return bp;
    }

    
    private JPanel registerFeaturePanel () {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 2));
		
		p.add(new JLabel ("Existing Subscripiton Information:"));
		p.add(new JLabel(""));
		   
		accountNo = "SA-2011-03-25-8481364";
		if(accountNo != null){

			try{
				List<SubscriptionPlan> subscribedPlans = manager.getAccountSubscriptions(accountNo);	
				
				if(subscribedPlans !=null)
				{
					
					for(SubscriptionPlan plan: subscribedPlans){
			
						p.add ( new JLabel (plan.getPlanDescription()));
						p.add(new JLabel(""));

						List<Feature> unregisteredFeatures = manager.getDeregisteredFeatures(accountNo, "PlanId");
						List<Feature> features = plan.getOptionalFeatures();	
						for(Feature feature: features){
							
							p.add ( new JLabel (feature.getName()));
							p.add(new JLabel(""));

						}	
						
						if(unregisteredFeatures != null && unregisteredFeatures.size() > 0 ){
			   				
							p.add(createFeatureComboBox(plan.getPlanId(),unregisteredFeatures));
							
							JButton b = new JButton ("Register new feature: ");
			   				
			   			    b.addActionListener (new ActionListener () {
			    			     public void actionPerformed (ActionEvent e) {
			    			    	 
			    			     }
						    });
			   			    p.add(b);
						}
					}
				}
			}catch(Exception ex) {
				
	    		JOptionPane.showMessageDialog(window, ex.getMessage());

			}
	 
		}
		
    	JPanel bp = new JPanel ();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        bp.setLayout (flowLayout);
    

        
        if(accountNo != null){
        	//bp.add ("North", new JLabel ("Existing Subscripiton Information:"));
        	//bp.add ("Center", p);
        	bp.add(p);
        }
     
        return bp;
    }
    
    private JPanel registerSubscriptionPlanPanel () {
    	JPanel p = new JPanel (new GridLayout (0,2));
    
    	p.add(createComboBox());
	    JButton b = new JButton ("Register");
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
	    		  // customerID.setText(planTypeName);
	    		  // SubscriptionPlanAddDialog d = new SubscriptionPlanAddDialog (window, planTypeName); 
	    		  // SubscriptionPlanAddDialog d = new SubscriptionPlanAddDialog (window, planTypeName); 
	              // d.pack();
	              // d.setVisible (true);
	            }
	    });
	 
	    planTypeBox.setSelectedIndex(0);
	    add(planTypeBox, BorderLayout.PAGE_START);
	    return planTypeBox;
    }
    private JComboBox createFeatureComboBox (String planId, List<Feature> features) {  	
    	JComboBox featureBox = new JComboBox();		
	    String[] featuresNames = new String[features.size()];
	    featuresNames[0] = "Please select:";
	    for (int i = 0 ; i <features.size(); i ++ ) {
	    	featuresNames[i+1] = features.get(i).getName();
	    } 
	    
	    featureBox = new JComboBox(featuresNames);
	    
	    featureBox.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		   JComboBox cb = (JComboBox)e.getSource();
	    		   if(cb.getSelectedIndex() != 0){
	    			   
	    		   }
	            }
	    });
	 
	    featureBox.setSelectedIndex(0);
	    add(featureBox, BorderLayout.PAGE_START);
	    return featureBox;
    }
}