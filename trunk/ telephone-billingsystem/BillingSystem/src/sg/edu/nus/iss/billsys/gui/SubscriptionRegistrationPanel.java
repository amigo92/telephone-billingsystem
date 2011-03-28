package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */
import sg.edu.nus.iss.billsys.*;
import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.GuiConfirmDialog;
import sg.edu.nus.iss.billsys.util.BillingUtil;
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
	private JComboBox featureBox;
	private int selectedPlanIndex;
	private List<SubscriptionPlan> subscribedPlans;
	private HashMap<String,ArrayList<JComboBox>> featureBoxList;
	
    public SubscriptionRegistrationPanel (BillingWindow window) {
    	try
    	{
			this.window = window;
		    manager = MgrFactory.getSubscriptionMgr();
		    setLayout (new BorderLayout());
		    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		    		    
		    add ("North", createFormPanel());      

		    featurePanel = new JPanel();
		    add ("South", featurePanel);     
		    
		    customerID.setText("S8481361F");
		    
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
	    	    accountNo = MgrFactory.getAccountMgr().getCustomerDetailsById(customerID.getText()).getAcct().getAcctNo();

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
	    add(planTypeBox, BorderLayout.PAGE_START);
	    return planTypeBox;
    }
    
    
    private JPanel registerFeaturePanel () {
    	
		JScrollPane scrollPane = new JScrollPane();

		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 2));
		
		p.add(new JLabel ("Register New Feature: "));
		p.add(new JLabel(""));
		   
		if(accountNo != null){

			try{
				subscribedPlans = manager.getAccountSubscriptions(accountNo);	
				
				if(subscribedPlans !=null)
				{	
					JButton b = new JButton ("Register new feature");
			        b.addActionListener (new ActionListener () {
			        public void actionPerformed (ActionEvent e) {
			        	try{		        		
			        		SubscriptionPlanAddFDialog d = new SubscriptionPlanAddFDialog(window,accountNo, subscribedPlans.get(selectedPlanIndex));
			        		d.pack();
			        		d.setVisible(true);
				    
			        	}
				    	catch(Exception ex)
				    	{
				    		JOptionPane.showMessageDialog(window, ex.getMessage());
				    	} 	
			    	}
			        });

					p.add(createRegisteredSubComboBox(subscribedPlans));
					p.add(b);

					JPanel sp = new JPanel (new GridLayout (0, 2));
					
					sp.add ( new JLabel ("Existing Subscription:"));
					sp.add(new JLabel(""));
					
					for(SubscriptionPlan plan: subscribedPlans){
						String strDateInfo =  "     " + BillingUtil.getDateTimeStr(plan.getDateCommenced())
											+ "  -  " + BillingUtil.getDateTimeStr(plan.getDateTerminated());
			
						sp.add ( new JLabel (plan.getPlanDescription()));						
						sp.add(new JLabel(strDateInfo));
						
						List<Feature> features = plan.getOptionalFeatures();	
						for(Feature feature: features){
							strDateInfo =  "     " + BillingUtil.getDateTimeStr(feature.getDateCommenced())
							+ "  -  " + BillingUtil.getDateTimeStr(feature.getDateTerminated());

							sp.add ( new JLabel ("        " + feature.getName()));
							sp.add(new JLabel(strDateInfo));
						}			
					}

					scrollPane.getViewport().add( sp );
					
					//p.add(scrollPane);

				}
			}catch(Exception ex) {			
	    		JOptionPane.showMessageDialog(window, ex.getMessage());
			}
		}
		
    	JPanel bp = new JPanel (new BorderLayout());
 
        if(accountNo != null){
        	bp.add ("North", p);
        	bp.add ("Center", scrollPane);
         }
     
        return bp;
    }

   
    private JComboBox createRegisteredSubComboBox (List<SubscriptionPlan> subscribedPlans) {
    	JComboBox	planBox = new JComboBox();	
    	
	    String[] planDescs = new String[subscribedPlans.size()];
	    for (int i = 0 ; i <subscribedPlans.size(); i ++ ) {
	    	planDescs[i] = subscribedPlans.get(i).getPlanDescription();
	    	planBox.addItem(planDescs[i]);
	    } 

	    planBox.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
    		 		JComboBox cb = (JComboBox)e.getSource();
    		 		selectedPlanIndex = cb.getSelectedIndex() ;
	            }
	    });
	 
	    planBox.setSelectedIndex(0);
	    add(planBox, BorderLayout.PAGE_START);
	    return planBox;
    }
}