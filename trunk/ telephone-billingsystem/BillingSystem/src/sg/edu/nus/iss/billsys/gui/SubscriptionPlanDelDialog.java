package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.constant.PlanType.PlanCode;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.GuiOkCancelDialog;
import sg.edu.nus.iss.billsys.util.BillingUtil;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

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
    private SubscriptionDeRegistrationPanel parent;
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
		
		untilField.setText(BillingUtil.getCurrentDateStr());

		
		titleLabel.setText("De-Register for " + subscription.getPlanDescription() + (featureType == null ? "" : ":" + featureType.name));
	}
	
	@Override	
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
	protected boolean performOkAction(){
//		JOptionPane.showMessageDialog(window, "0","",0); error
//		JOptionPane.showMessageDialog(window, "1","",1); information
//		JOptionPane.showMessageDialog(window, "2","",2); warning
//		JOptionPane.showMessageDialog(window, "3","",3); question

		try
		{
        	dateTerminated = BillingUtil.getDateTime(untilField.getText());

			if(featureType == null)
				manager.deregisterSubscriptionPlan(accountNo, planId, dateTerminated);
			else
				manager.deregisterFeature(accountNo, planId, featureId, dateTerminated);
		
			return true;
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(window, ex.getMessage(),"",0);	
			return false;
		}
	}
}