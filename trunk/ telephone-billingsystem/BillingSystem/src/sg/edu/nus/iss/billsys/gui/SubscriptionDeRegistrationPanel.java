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

public class SubscriptionDeRegistrationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow        window;
	
	private JTextField           customerID;
	private List<PlanType>       listOfPlanType;
	private SubscriptionMgr      manager;
	private String[] planNames;
	private PlanType planType;
	private String accountNo;
	private JPanel deRegisterPanel;
	
    public SubscriptionDeRegistrationPanel (BillingWindow window) {   
		this.window = window;
	    try {
			manager = MgrFactory.getSubscriptionMgr();
		} catch (BillingSystemException e) {
			
		}
	    setLayout (new BorderLayout());
	    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	    
	    add ("North", createFormPanel());      

	    deRegisterPanel = deRegisterPanel();
	    add ("Center", deRegisterPanel);    
    }


    private JPanel createFormPanel () {
    	JPanel p = new JPanel (new GridLayout (0,2));
        customerID = new JTextField (1);
        p.add(customerID);
        JButton b = new JButton ("Get Subscription Information");
        b.addActionListener (new ActionListener () {
        public void actionPerformed (ActionEvent e) {
        	try{	
 	    	   // accountNo = MgrFactory.getAccountMgr().getCustomerDetailsById(customerID.getText()).getAcct().getAcctNo();
        		accountNo = "SA-2011-03-25-8481364";
        		deRegisterPanel.revalidate();
            	deRegisterPanel = deRegisterPanel();
         	    add ("Center", deRegisterPanel); 
        	}
	    	catch(Exception ex){
	    		JOptionPane.showMessageDialog(window, ex.getMessage());
        		
	    	} 	
    	}
        });
        p.add (b);

        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", new JLabel ("Customer ID:   "));
        bp.add ("Center", p);    
     
        return bp;
    }
    
    private JPanel deRegisterPanel () {
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
		    		    			//JOptionPane.showMessageDialog(window, plan.getPlanDescription());	 
		    			     }
					    });
		   			    p.add(b);
		   			    
		   			    List<Feature> features = plan.getOptionalFeatures();	
						for(Feature feature: features){			
							p.add ( new JLabel ("       " + feature.getName()));
						    JButton b2 = new JButton ("De-Register");
			   			    b2.addActionListener (new ActionListener () {
			    			     public void actionPerformed (ActionEvent e) {
			    			    	 
			    			     }
						    });
				   			p.add(b2);
						}				  
					 }
				}
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(window, ex.getMessage(),"" ,2);
		}
    
    	JPanel bp = new JPanel ();
        FlowLayout flowLayout = new FlowLayout();
        new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        bp.setLayout( flowLayout);


        if(accountNo != null){
        	bp.add (p);
        }
        return bp;
    }
}
    
   
    	