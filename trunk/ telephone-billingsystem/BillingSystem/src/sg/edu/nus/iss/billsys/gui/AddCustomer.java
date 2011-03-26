package sg.edu.nus.iss.billsys.gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

import sg.edu.nus.iss.billsys.mgr.AccountMgr;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Customer;

/**
* @author Win Kyi Tin 
*/

public class AddCustomer extends JPanel {
	private JPanel panelCustRegTop;
	private JLabel CustRegTitle;
	private JPanel panelCustRegCenter;
	private JButton CancelButton;
	private JButton SubmitButton;
	private JLabel jLabel4;
	private JTextField InterestText;
	private JTextField contactTelText;
	private JLabel errorMsgNRIC;
	private JLabel ContactTelLabel;
	private JLabel address3Label;
	private JLabel address2Label;
	private JLabel address1Label;
	private JLabel errorMsgCustName;
	private JTextField address3Text;
	private JTextField address2Text;
	private JTextField address1Text;
	private JTextField nricText;
	private JLabel nricLabel;
	private JTextField CustNameText;
	private JLabel CustNameLabel;

	private BillingWindow  window;
	private static final long serialVersionUID = 1L;
	
	
	public AddCustomer (BillingWindow window) {
		this.window = window;
		initControl();

	}
	
	public AddCustomer () {
		
		initControl();

	}
	
	private void initControl() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(691, 497));
			{
				panelCustRegTop = new JPanel();
				this.add(panelCustRegTop, BorderLayout.NORTH);
				{
					CustRegTitle = new JLabel();
					panelCustRegTop.add(CustRegTitle);
					CustRegTitle.setText("Customer Registration");
					CustRegTitle.setFont(new java.awt.Font("Segoe UI",1,14));
					CustRegTitle.setPreferredSize(new java.awt.Dimension(610, 37));
				}
			}
			{
				panelCustRegCenter = new JPanel();
				this.add(panelCustRegCenter, BorderLayout.CENTER);
				panelCustRegCenter.setLayout(null);
				panelCustRegCenter.setPreferredSize(new java.awt.Dimension(691, 427));
				{
					CustNameLabel = new JLabel();
					panelCustRegCenter.add(CustNameLabel);
					CustNameLabel.setText("Customer Name* :");
					CustNameLabel.setBounds(12, 8, 167, 16);
				}
				{
					CustNameText = new JTextField();
					panelCustRegCenter.add(CustNameText);
					CustNameText.setBounds(197, 5, 218, 23);
				}
				{
					errorMsgCustName = new JLabel();
					panelCustRegCenter.add(errorMsgCustName);
					errorMsgCustName.setText("*Please enter customer name.");
					errorMsgCustName.setOpaque(true);
					errorMsgCustName.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgCustName.setVisible(false);
					errorMsgCustName.setBounds(422, 8, 257, 16);
				}
				{
					nricLabel = new JLabel();
					panelCustRegCenter.add(nricLabel);
					nricLabel.setText("NRIC* :");
					nricLabel.setBounds(12, 43, 98, 16);
				}
				{
					nricText = new JTextField();
					panelCustRegCenter.add(nricText);
					nricText.setBounds(197, 40, 218, 23);
				}
				
				{
					address1Text = new JTextField();
					panelCustRegCenter.add(address1Text);
					address1Text.setBounds(197, 80, 309, 57);
				}
				{
					address2Text = new JTextField();
					panelCustRegCenter.add(address2Text);
					address2Text.setBounds(197, 149, 309, 57);
				}
				{
					address3Text = new JTextField();
					panelCustRegCenter.add(address3Text);
					address3Text.setBounds(197, 216, 309, 57);
				}
				{
					address1Label = new JLabel();
					panelCustRegCenter.add(address1Label);
					address1Label.setText("Address 1  :");
					address1Label.setBounds(12, 95, 100, 16);
				}
				{
					address2Label = new JLabel();
					panelCustRegCenter.add(address2Label);
					address2Label.setText("Address 2  :");
					address2Label.setBounds(12, 161, 100, 16);
				}
				{
					address3Label = new JLabel();
					panelCustRegCenter.add(address3Label);
					address3Label.setText("Address 3  :");
					address3Label.setBounds(12, 236, 100, 16);
				}
				{
					ContactTelLabel = new JLabel();
					panelCustRegCenter.add(ContactTelLabel);
					ContactTelLabel.setText("Contact Telephone :");
					ContactTelLabel.setBounds(12, 290, 167, 16);
				}
				{
					errorMsgNRIC = new JLabel();
					panelCustRegCenter.add(errorMsgNRIC);
					errorMsgNRIC.setBounds(422, 41, 247, 20);
					errorMsgNRIC.setText("*Please enter NRIC.");
					errorMsgNRIC.setOpaque(true);
					errorMsgNRIC.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgNRIC.setVisible(false);				
				
				}
				{
					contactTelText = new JTextField();
					panelCustRegCenter.add(contactTelText);
					contactTelText.setBounds(197, 287, 96, 23);
				}
				{
					InterestText = new JTextField();
					panelCustRegCenter.add(InterestText);
					InterestText.setBounds(197, 322, 304, 48);
				}
				{
					jLabel4 = new JLabel();
					panelCustRegCenter.add(jLabel4);
					jLabel4.setText("Interest :");
					jLabel4.setBounds(13, 337, 142, 16);
				}
				{
					SubmitButton = new JButton();
					panelCustRegCenter.add(SubmitButton);
					SubmitButton.setText("Submit");
					SubmitButton.setBounds(292, 404, 96, 23);
					SubmitButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							SubmitButtonActionPerformed(evt);
						}
					});
				}
				{
					CancelButton = new JButton();
					panelCustRegCenter.add(CancelButton);
					CancelButton.setText("Cancel");
					CancelButton.setBounds(399, 404, 96, 23);
					CancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							CancelButtonActionPerformed(evt);
						}
					});
				}

			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void SubmitButtonActionPerformed(ActionEvent evt) {
		if (validateControls()) {
			Customer cust= new Customer();
			controlsToObject(cust);
			AccountMgr accountMgr= new AccountMgr();
			cust = accountMgr.create(cust);
			
			if (cust.getAcct().equals(null) ){
				JOptionPane.showMessageDialog(null,"Customer Information is saving successfully." , "Billing System", 1);
			}
				
		}
	}
	
	private void CancelButtonActionPerformed(ActionEvent evt) {
		
	}
	
	private void controlsToObject(Customer cust){
		cust.setName(CustNameText.getText());
		cust.setNric(nricText.getText());
		cust.setContact_tel(contactTelText.getText());
		cust.setAddress1(address1Text.getText());
		cust.setAddress2(address2Text.getText());
		cust.setAddress3(address3Text.getText());
		cust.setInterest(InterestText.getText());
		
	}
	
	private boolean validateControls(){
		boolean bReturn= false;
		if (StringUtil.isNullOrEmpty(this.CustNameText.getText())){
			// display error message
			errorMsgCustName.setVisible(true);
			bReturn= true;
		}
		
		if (StringUtil.isNullOrEmpty(this.nricText.getText())){
			// display error message
			errorMsgNRIC.setVisible(true);
			bReturn= true;
		}	
		
		
		return bReturn;
	}
	
}
