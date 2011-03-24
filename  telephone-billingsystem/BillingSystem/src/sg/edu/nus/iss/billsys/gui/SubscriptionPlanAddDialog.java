package sg.edu.nus.iss.billsys.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.constant.PlanType.PlanCode;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.GuiOkCancelDialog;



public class SubscriptionPlanAddDialog extends GuiOkCancelDialog {
	private static final long serialVersionUID = 1L;
	
    protected SubscriptionMgr manager;
    
    private JTextField assignedNumberField;
    private JLabel assignedNumberLabel;
    
    private JTextField fromField;
    private JTextField untilField;
    
    private BillingWindow window;
    private String accountNo;
	   
	public SubscriptionPlanAddDialog(BillingWindow window, PlanType planType, String accountNo) {
		super(window,  "Register new Subscription Plan :"  + planType.name);
		manager = MgrFactory.getSubscriptionMgr();
		
		this.window = window;
		this.accountNo = accountNo;
		
		
		if(planType.planCode == PlanCode.CABLE_TV ){
			assignedNumberField.setVisible(false);
			assignedNumberLabel.setVisible(false);
		}
		
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
		JOptionPane.showMessageDialog(window, "empty","",0);

		// manager.registerNewSubscriptionPlan(accountNo, assignedTelNo, planType, dateCommenced, dateTerminated)

		return false;
	}

}
