package sg.edu.nus.iss.billsys.gui;

/**
 * @author Ma Huazhen
 *
 */
import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.FinanceUtils;

import java.util.List;
import java.awt.*;

import javax.swing.*;

public class SubscriptionIntroPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private SubscriptionMgr      manager;
	private BillingWindow window;

    public SubscriptionIntroPanel (BillingWindow window) {
    	this.window = window;
        manager = window.getSubscriptionMgr();
        setLayout (new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        add ("North", createFormPanel());      
    }
    
    private JPanel createFormPanel () {
    	JPanel p = new JPanel (new GridLayout (0,3));
    	
    	p.add(new JLabel(""));
    	p.add(new JLabel("Monthly Subscription Charge"));
    	p.add(new JLabel("Usage Charge (per second)"));
    	
    	try{
	    	PlanType[] planTypes = manager.getAllPlanType();
	    	
	    	for (PlanType planType : planTypes){
	    	
	    		p.add(new JLabel(planType.name));
	        	FeatureType basicFeatureType = manager.getPlanBasicFeature(planType);
	    		p.add(new JLabel("     " + FinanceUtils.formatCentToDollar(manager.getSubscriptionCharge(basicFeatureType))));
		    	p.add(new JLabel(""));
		    	List<FeatureType> optionalFeatureTypes = manager.getPlanOptionalFeatures(planType);   	
		    	for (FeatureType featureType : optionalFeatureTypes ){	
		    		p.add(new JLabel("     " + featureType.name));
			    	p.add(new JLabel("     " + FinanceUtils.formatCentToDollar(manager.getSubscriptionCharge(featureType))));	
			    	p.add(new JLabel(""));
		    	} 	
		    	List<FeatureType> usageChargeFeatureTypes = manager.getPlanUsageChargeFeatures(planType);
		    	for (FeatureType featureType : usageChargeFeatureTypes ){	
	    			p.add(new JLabel("     " + featureType.name));
			    	p.add(new JLabel(""));
			    	p.add(new JLabel("     " + FinanceUtils.formatCentToDollar(manager.getSubscriptionCharge(featureType))));				    	
	    		}
	    	}
    	}catch(Exception ex){
    		JOptionPane.showMessageDialog(window, ex.getMessage(),"Error", 0);
    	} 	
    	
    	JLabel title = new JLabel("<html><center><h3>Introduction of Subcription Plan</h3></center></html>");   
    	title.setHorizontalAlignment(SwingConstants.CENTER );
    	    	
        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North",title);
        bp.add ("Center", p);
        return bp;
    }
}