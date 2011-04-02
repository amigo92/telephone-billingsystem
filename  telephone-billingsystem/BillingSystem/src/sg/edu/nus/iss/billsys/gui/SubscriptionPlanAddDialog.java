package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
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
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Customer;

public class SubscriptionPlanAddDialog extends GuiOkCancelDialog {
	private static final long serialVersionUID = 1L;
	
    protected SubscriptionMgr manager;
    
    private JFormattedTextField assignedNumberField;
    private JLabel assignedNumberLabel;
    
    private JTextField fromField;
    private JTextField untilField;
    private JComboBox phoneNumberBox;
    
    private BillingWindow window;
    private String accountNo;
    private PlanType planType;
    private String selectedPhoneNumber;
   
	   
	public SubscriptionPlanAddDialog(BillingWindow window, PlanType planType, String accountNo) {
		super(window,  "Register new Subscription Plan :"  + planType.name);
		manager = window.getSubscriptionMgr();
		
		this.window = window;
		this.accountNo = accountNo;
		this.planType = planType;
		
		fromField.setText(BillingUtil.getCurrentDateStr());
	
		if(planType.planCode == PlanCode.CABLE_TV ){
			assignedNumberLabel.setVisible(false);
			phoneNumberBox.setVisible(false);
		}else{
			List<String> phoneNumbers = manager.getAvailPhoneNumbers(planType);

			for(String n : phoneNumbers){
				phoneNumberBox.addItem(n);
			}
		    phoneNumberBox.setSelectedIndex(0);
		}

	}

	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 2));
		  
		p.add (assignedNumberLabel = new JLabel ("Please select a phone number"));
	
		p.add (createPhoneNumberComboBox ());
		p.add (new JLabel ("Start Date (d-MMM-yyyy) *"));
		fromField = new JTextField (20);
		p.add (fromField);
		p.add (new JLabel ("End Date (d-MMM-yyyy)"));
		untilField = new JTextField (20);
		p.add (untilField);
		
		p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		return p;
	}
	
	private JComboBox createPhoneNumberComboBox () {  	
	    phoneNumberBox = new JComboBox();
	    
	 
	    phoneNumberBox.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		   JComboBox cb = (JComboBox)e.getSource();
	    		   selectedPhoneNumber = (String)cb.getSelectedItem();
	        }
	    });
	 
	    //add(accountBox, BorderLayout.PAGE_START);
	    return phoneNumberBox;
    }

  

	@Override
	protected boolean performOkAction() {
//		String assignedTelNo = assignedNumberField.getText();
//		
//		if(planType.planCode != PlanCode.CABLE_TV && assignedTelNo.trim().equals("")  ){
//			JOptionPane.showMessageDialog(window, "Phone Number must be 8 digits","",0);
//			return false;
//		}
		
		String assignedTelNo = selectedPhoneNumber;
		
		Date fromtDate;
		try {
			fromtDate = BillingUtil.getDateTime(fromField.getText());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
			return false;
		}
		Date utilDate;
		if(StringUtil.isNullOrEmpty(untilField.getText().trim()))
			utilDate = null;
		else{
			try {
				utilDate = BillingUtil.getDateTime(untilField.getText());
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
				return false;
			}
		}

		if(utilDate != null && fromtDate.after(utilDate)){
			JOptionPane.showMessageDialog(window, "End Date must be after Start Date ","",0);	
			return false;
		}
	
		try {
			manager.registerNewSubscriptionPlan(accountNo, assignedTelNo, planType, fromtDate, utilDate);
			window.refreshSubRegPanel(accountNo);
			
		} catch (BillingSystemException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
			return false;
		}

		return true;
	}

}
