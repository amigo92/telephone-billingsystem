package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */
import java.awt.GridLayout;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class SubscriptionPlanAddDialog extends GuiOkCancelDialog {
	private static final long serialVersionUID = 1L;
	
    protected SubscriptionMgr manager;
    
    private JTextField assignedNumberField;
    private JLabel assignedNumberLabel;
    
    private JTextField fromField;
    private JTextField untilField;
    
    private BillingWindow window;
    private String accountNo;
    private PlanType planType;
	   
	public SubscriptionPlanAddDialog(BillingWindow window, PlanType planType, String accountNo) {
		super(window,  "Register new Subscription Plan :"  + planType.name);
		manager = MgrFactory.getSubscriptionMgr();
		
		this.window = window;
		this.accountNo = accountNo;
		this.planType = planType;
		
		if(planType.planCode == PlanCode.CABLE_TV ){
			assignedNumberField.setVisible(false);
			assignedNumberLabel.setVisible(false);
		}
		fromField.setText(BillingUtil.getCurrentDateStr());
		untilField.setText(BillingUtil.getNextYearStr());

	}

	@Override
	
	
	protected JPanel createFormPanel() {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 2));
		  
		p.add (assignedNumberLabel = new JLabel ("Assigned Phone Number"));
		assignedNumberField = new JTextField (8);
		p.add (assignedNumberField);
		p.add (new JLabel ("Start Date (d-MMM-yyyy)"));
		fromField = new JTextField (20);
		p.add (fromField);
		p.add (new JLabel ("End Date (d-MMM-yyyy)"));
		untilField = new JTextField (20);
		p.add (untilField);
		  
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String assignedTelNo = assignedNumberField.getText();
		
		Date dateCommenced;
		try {
			dateCommenced = BillingUtil.getDateTime(fromField.getText());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
			return false;
		}
		Date dateTerminated;
		try {
			dateTerminated = BillingUtil.getDateTime(untilField.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
			return false;
		}

		try {
			manager.registerNewSubscriptionPlan(accountNo, assignedTelNo, planType, dateCommenced, dateTerminated);
		} catch (BillingSystemException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
			return false;
		}

		return true;
	}

}
