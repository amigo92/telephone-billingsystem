package sg.edu.nus.iss.billsys.gui;

import java.awt.GridLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.GuiOkCancelDialog;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;
/**
 * @author Ma Huazhen
 *
 */
public class SubscriptionPlanDelDialog extends GuiOkCancelDialog {
	private static final long serialVersionUID = 1L;
	
    protected SubscriptionMgr manager;    
    private JTextField untilField;
    private BillingWindow window;
    private String accountNo;
    private String planId;
    private FeatureType featureType;
    private String featureId;
    private Date dateTerminated;
    private JLabel titleLabel;
   
	public SubscriptionPlanDelDialog(BillingWindow window, String accountNo, SubscriptionPlan subscription,  Feature feature) {
		super(window,  "De-Register");
		manager = window.getSubscriptionMgr();
		
		this.window = window;
		this.accountNo = accountNo;
		this.planId = subscription.getPlanId();
		if( feature != null) {
			this.featureType = feature.getFeatureType();
			this.featureId = feature.getFeatureId();
		}	
//		Initialize End Date field
		untilField.setText(TimeUtils.getCurrentDateStr());
		
		titleLabel.setText("De-Register for " + subscription.getPlanDescription() + (featureType == null ? "" : ":" + featureType.name));
	}
	
	@Override
	/**
	 * Main Form Panel
	 */
	protected JPanel createFormPanel() {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 2));
		titleLabel = new JLabel("");
		p.add (titleLabel);
		p.add(new JLabel(""));
		
		p.add (new JLabel ("End Date (d-MMM-yyyy)"));
		untilField = new JTextField (20);
		p.add (untilField);
		
		p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		return p;
	}

	@Override
	/**
	 * Click OK button
	 */
	protected boolean performOkAction(){
		try{
        	dateTerminated = TimeUtils.getDateTime(untilField.getText());
        	
        	if(TimeUtils.getYesterdayDate().after(dateTerminated))
        	{
    			JOptionPane.showMessageDialog(window, "End Date must be today afterwards.","Error",0);	
    			return false;
    			
        	}else{
            	
				if(featureType == null)
					manager.deregisterSubscriptionPlan(accountNo, planId, dateTerminated);
				else
					manager.deregisterFeature(accountNo, planId, featureId, dateTerminated);
	
				window.refreshSubRegPanel(accountNo);
				return true;
        	}
			
		}catch(BillingSystemException ex){
			JOptionPane.showMessageDialog(window, ex.getMessagebyException(),"Error",0);	
			return false;
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(window, ex.getMessage(),"Error",0);	
			return false;
		}
	}
}
