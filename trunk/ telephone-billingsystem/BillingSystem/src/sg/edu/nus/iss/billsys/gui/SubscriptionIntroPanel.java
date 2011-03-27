package sg.edu.nus.iss.billsys.gui;

/**
 * @author Ma Huazhen
 *
 */
import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;

import java.util.List;
import java.awt.*;

import javax.swing.*;

public class SubscriptionIntroPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow        window;
	private SubscriptionMgr      manager;

    public SubscriptionIntroPanel (BillingWindow window) {
 
    	this.window = window;
        manager = MgrFactory.getSubscriptionMgr();
        setLayout (new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        add ("North", createFormPanel());      
    }
    
    private JPanel createFormPanel () {
    	JPanel p = new JPanel (new GridLayout (0,3));
    	
    	p.add(new JLabel(""));
    	p.add(new JLabel("Monthly Subscription Charge"));
    	p.add(new JLabel("Usage Charge (per second)"));
    	PlanType[] planTypes = manager.getAllPlanType();
    	
    	for (PlanType planType : planTypes){
    	
    		p.add(new JLabel(planType.name));
        	//p.add(new JLabel(""));
        	//p.add(new JLabel(""));
        	FeatureType basicFeatureType = manager.getPlanBasicFeatures(planType);
    	
	    	//p.add(new JLabel("     " + basicFeatureType.name));
	        p.add(new JLabel("     " + manager.getSubscriptionCharge(basicFeatureType)));
	    	p.add(new JLabel(""));
	    	List<FeatureType> optionalFeatureTypes = manager.getPlanOptionalFeatures(planType);
    	
	    	for (FeatureType featureType : optionalFeatureTypes ){	
	    		
	    		if(featureType.usageCharge)
	    		{
	    			p.add(new JLabel("     " + featureType.name));
			    	p.add(new JLabel(""));
			    	p.add(new JLabel("     " + manager.getSubscriptionCharge(featureType)));				    	
	    		}else {
		    		p.add(new JLabel("     " + featureType.name));
			    	p.add(new JLabel("     " + manager.getSubscriptionCharge(featureType)));	
			    	p.add(new JLabel(""));
	    		}	
	    	} 	
    	}
    	
    	JLabel headerLabel =  new JLabel ("Introduction of Subcription Plan:   ");
    	headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    	
        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North",headerLabel);
        bp.add ("Center", p);
        return bp;
    }
}