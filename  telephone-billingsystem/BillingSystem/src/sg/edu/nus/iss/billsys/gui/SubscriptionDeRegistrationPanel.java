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
	private JPanel featurePanel;
	
    public SubscriptionDeRegistrationPanel (BillingWindow window) {   
		this.window = window;
	    manager = MgrFactory.getSubscriptionMgr();
	    setLayout (new BorderLayout());
	    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	    
	    add ("North", createFormPanel());      

	    featurePanel = deRegisterFeaturePanel();
	    add ("Center", featurePanel);    
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
        		accountNo = "acc_no1";
        		featurePanel.revalidate();
        		featurePanel = deRegisterFeaturePanel();
        		add ("Center", featurePanel);
        		
        	}
	    	catch(Exception ex)
	    	{
	    		JOptionPane.showMessageDialog(window, ex.getMessage() + ex.getLocalizedMessage());
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
    
    private JPanel deRegisterFeaturePanel () {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 2));
		try
		{
			if(accountNo != null){
	 			
				Collection<SubscriptionPlan> subscribedPlans = manager.getAccountSubscriptions(accountNo);
				
				if(subscribedPlans != null)
				{
					// for (Iterator<SubscriptionPlan> it = subscribedPlans.iterator(); it.hasNext(); )
					// {
						// SubscriptionPlan plan = it.next();
						// p.add ( new JLabel (plan.getPlanDescription()));
						 
					// }
				}
	
				/*for(SubscriptionPlan plan: subscribedPlans){
	
					p.add ( new JLabel (plan.getPlanDescription()));
					List<Feature> features = plan.getOptionalFeatures();	
					for(Feature feature: features){			
						p.add ( new JLabel (feature.getName()));
						
						JButton b = new JButton ("De-Register");
		   			    b.addActionListener (new ActionListener () {
		    			     public void actionPerformed (ActionEvent e) {
		    			    	 
		    			     }
					    });
		   			    p.add(b);
					}	
				}	*/
			}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(window, ex.getMessage());
		}
    
    	JPanel bp = new JPanel ();
        bp.setLayout (new FlowLayout());
        if(accountNo != null){
        	bp.add ("North", new JLabel ("Existing Subscripiton Information:"));
        	bp.add ("Center", p);
        }
     
        return bp;
    }
}
    
   
    	