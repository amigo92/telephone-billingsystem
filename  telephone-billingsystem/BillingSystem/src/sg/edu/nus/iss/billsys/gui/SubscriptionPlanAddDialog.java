package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
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
    
    private JFormattedTextField assignedNumberField;
    private JLabel assignedNumberLabel;
    
    private JTextField fromField;
    private JTextField untilField;
    
    private BillingWindow window;
    private String accountNo;
    private PlanType planType;
	   
	public SubscriptionPlanAddDialog(BillingWindow window, PlanType planType, String accountNo) {
		super(window,  "Register new Subscription Plan :"  + planType.name);
		manager = window.getSubscriptionMgr();
		
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
		//assignedNumberField = new JTextField (8);
		assignedNumberField = new JFormattedTextField(BillingUtil.createFormatter(window, "########"));

		p.add (assignedNumberField);
		p.add (new JLabel ("Start Date (d-MMM-yyyy)"));
		fromField = new JTextField (20);
		p.add (fromField);
		p.add (new JLabel ("End Date (d-MMM-yyyy)"));
		untilField = new JTextField (20);
		p.add (untilField);
		
		p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String assignedTelNo = assignedNumberField.getText();
		
		if(assignedTelNo.trim().equals("")  ){
			JOptionPane.showMessageDialog(window, "Phone Number must be 8 digits","",0);
			return false;
		}
		
		Date fromtDate;
		try {
			fromtDate = BillingUtil.getDateTime(fromField.getText());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
			return false;
		}
		Date utilDate;
		try {
			utilDate = BillingUtil.getDateTime(untilField.getText());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
			return false;
		}
		
		if(fromtDate.before(utilDate)){
			JOptionPane.showMessageDialog(window, "End Date must be after Start Date ","",0);	
			return false;
		}
	
		try {
			manager.registerNewSubscriptionPlan(accountNo, assignedTelNo, planType, fromtDate, utilDate);
			window.refreshSubRegPanel(accountNo);
		} catch (BillingSystemException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
			return false;
		}

		return true;
	}

}
