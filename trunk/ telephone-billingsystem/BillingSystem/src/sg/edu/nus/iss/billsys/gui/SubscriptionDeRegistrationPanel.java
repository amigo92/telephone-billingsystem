package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */

import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;

import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

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
	
	    setLayout (new BorderLayout());
	    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	    deRegisterPanel = new JPanel(new BorderLayout());   

	    add ("North", createFormPanel());      
	    add ("Center", deRegisterPanel);
	      
	    customerID.setText("S8481362F");
    }
    private JPanel createFormPanel () {
    	JPanel p = new JPanel (new GridLayout (0,2));
    	
        p.add ( new JLabel ("Customer ID:   "));
        p.add ( new JLabel (""));

        customerID = new JTextField (1);
        p.add(customerID);
        JButton b = new JButton ("Get Subscription Information");
        b.addActionListener (new ActionListener () {
        public void actionPerformed (ActionEvent e) {
        	try{	
 	    	    accountNo = MgrFactory.getAccountMgr().getCustomerDetailsById(customerID.getText()).getAcct().getAcctNo();
        		deRegisterPanel.revalidate();
            	deRegisterPanel.add("North", deRegisterScrollPanel());
        	}
	    	catch(Exception ex){
	    		JOptionPane.showMessageDialog(window, ex.getMessage());	
	    	} 	
    	}
        });
        p.add (b);
  
        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", p);     
        return bp;
    }
    
    private JScrollPane deRegisterScrollPanel () {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 2));
		
    	p.add (new JLabel ("Existing Subscripiton Information         :             "));
    	p.add(new JLabel("                    "));
		try
		{
			if(accountNo != null){
	 			
				List<SubscriptionPlan> subscribedPlans = manager.getAccountSubscriptions(accountNo);
				
				if(subscribedPlans != null)
				{
					 for (final SubscriptionPlan plan : subscribedPlans)
					 {
						p.add ( new JLabel (plan.getPlanDescription()));	
					 	JButton b = new JButton ("De-Register");
		   			    b.addActionListener (new ActionListener () {
		    			     public void actionPerformed (ActionEvent e) {		    			    	 	
		    			    	 	SubscriptionPlanDelDialog d = new SubscriptionPlanDelDialog (window, accountNo, plan, null);
		    		                d.pack();
		    		                d.setVisible (true);
		    			     }
					    });
		   			    p.add(b);
		   			    
		   			    features = plan.getOptionalFeatures();	
		   			    
		   			    
						for(final Feature feature: features){			
							p.add ( new JLabel ("       " + feature.getName()));
						    JButton b2 = new JButton ("De-Register");
			   			    b2.addActionListener (new ActionListener () {
			    			     public void actionPerformed (ActionEvent e) {
			    			    	 	SubscriptionPlanDelDialog d = new SubscriptionPlanDelDialog (window, accountNo, plan, feature);
			    			    	 	d.pack();
				    		            d.setVisible (true);
			    			     }
						    });
				   			p.add(b2);
						}				  
					 }
				}
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(window, ex.getMessage(),"" ,0);
		}

        JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add( p );
       
        return scrollPane;
    }
}
    
   
    	