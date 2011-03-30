package sg.edu.nus.iss.billsys.gui;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.AccountMgr;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Customer;

/**
* @author Win Kyi Tin 
*/

public class AddCustomer extends javax.swing.JPanel {
	private JPanel AddCustTitlePanel;
	private JLabel ContactTelLabel;
	private JLabel IntestingLabel;
	private JLabel errorMsgNIRC;
	private JLabel errorMsgLabelName;
	private JButton CancelButton;
	private JButton SubmitButton;
	private JTextField CustContactTelText;
	private JTextField InterestingText;
	private JLabel NRCLabel;
	private JLabel Address1Label;
	private JTextField CustAdd3Text;
	private JLabel CustAdd2Label;
	private JLabel CustAdd3Label;
	private JTextField CustNIRCText;
	private JTextField CustAdd1Text;
	private JTextField CustAdd2Text;
	private JTextField CustNameText;
	private JLabel CustomerNameLabel;
	private JPanel CustomerCenterPanel;
	private JLabel CustomerTitle;
	
	private String errorMsg=null;
	private BillingWindow window;

	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new AddCustomer());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setSize(668, 419);
		frame.setVisible(true);
		frame.setPreferredSize(new java.awt.Dimension(668, 419));
	}
	
	public AddCustomer() {
		super();
		initGUI();
	}
	
	public AddCustomer(BillingWindow window) {
		this.window = window;
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(619, 428));
			{
				AddCustTitlePanel = new JPanel();
				this.add(AddCustTitlePanel, BorderLayout.NORTH);
				{
					CustomerTitle = new JLabel();
					AddCustTitlePanel.add(CustomerTitle);
					CustomerTitle.setText("Customer Registration");
					CustomerTitle.setFont(new java.awt.Font("Segoe UI",1,14));
					CustomerTitle.setPreferredSize(new java.awt.Dimension(254, 20));
				}
			}
			{
				CustomerCenterPanel = new JPanel();
				this.add(CustomerCenterPanel, BorderLayout.CENTER);
				CustomerCenterPanel.setPreferredSize(new java.awt.Dimension(621, 391));
				CustomerCenterPanel.setLayout(null);
				{
					CustomerNameLabel = new JLabel();
					CustomerCenterPanel.add(CustomerNameLabel);
					CustomerNameLabel.setText("Customer Name* :");
					CustomerNameLabel.setBounds(6, 18, 154, 16);
				}
				{
					NRCLabel = new JLabel();
					CustomerCenterPanel.add(NRCLabel);
					NRCLabel.setText("NRIC * :");
					NRCLabel.setBounds(6, 56, 117, 16);
				}
				{
					Address1Label = new JLabel();
					CustomerCenterPanel.add(Address1Label);
					Address1Label.setText("Address 1  : ");
					Address1Label.setBounds(6, 97, 102, 16);
				}
				{
					CustNameText = new JTextField();
					CustomerCenterPanel.add(CustNameText);
					CustNameText.setBounds(148, 18, 252, 23);
				}
				{
					CustNIRCText = new JTextField();
					CustomerCenterPanel.add(CustNIRCText);
					CustNIRCText.setBounds(148, 53, 252, 23);
				}
				{
					CustAdd1Text = new JTextField();
					CustomerCenterPanel.add(CustAdd1Text);
					CustAdd1Text.setBounds(148, 88, 252, 36);
				}
				{
					CustAdd2Text = new JTextField();
					CustomerCenterPanel.add(CustAdd2Text);
					CustAdd2Text.setBounds(148, 136, 252, 36);
				}
				{
					CustAdd2Label = new JLabel();
					CustomerCenterPanel.add(CustAdd2Label);
					CustAdd2Label.setText("Address 2  : ");
					CustAdd2Label.setBounds(6, 145, 102, 16);
				}
				{
					CustAdd3Label = new JLabel();
					CustomerCenterPanel.add(CustAdd3Label);
					CustAdd3Label.setText("Address 3  : ");
					CustAdd3Label.setBounds(6, 196, 102, 16);
				}
				{
					CustAdd3Text = new JTextField();
					CustomerCenterPanel.add(CustAdd3Text);
					CustAdd3Text.setBounds(148, 187, 252, 36);
				}
				{
					ContactTelLabel = new JLabel();
					CustomerCenterPanel.add(ContactTelLabel);
					ContactTelLabel.setText("Contact Telephone :");
					ContactTelLabel.setBounds(6, 243, 142, 16);
				}
				{
					CustContactTelText = new JTextField();
					CustomerCenterPanel.add(CustContactTelText);
					CustContactTelText.setBounds(148, 240, 164, 23);
				}
				{
					InterestingText  = new JTextField();
					CustomerCenterPanel.add(InterestingText);
					InterestingText.setBounds(148, 275, 252, 36);
				}
				{
					IntestingLabel = new JLabel();
					CustomerCenterPanel.add(IntestingLabel);
					IntestingLabel.setText("Interesting :");
					IntestingLabel.setBounds(8, 284, 142, 16);
				}
				{
					SubmitButton = new JButton();
					CustomerCenterPanel.add(SubmitButton);
					SubmitButton.setText("Submit");
					SubmitButton.setBounds(231, 358, 81, 23);
					SubmitButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							 try{
							      
								 SubmitButtonActionPerformed(evt);
								 
							 	} catch (BillingSystemException ex) {
								    // Print out the exception that occurred
								    System.out.println(ex.getMessage());
								    errorMsg=new BillingSystemException(ex).getMessagebyException();
								} catch (Exception e) {
								    // Print out the exception that occurred
									errorMsg=new BillingSystemException(e).getMessagebyException();
								}
								
							
						}
					});
				}
				{
					CancelButton = new JButton();
					CustomerCenterPanel.add(CancelButton);
					CancelButton.setText("Cancel");
					CancelButton.setBounds(323, 358, 77, 23);
					CancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							CancelButtonActionPerformed(evt);
						}
					});
				}
				{
					errorMsgLabelName = new JLabel();
					CustomerCenterPanel.add(errorMsgLabelName);
					errorMsgLabelName.setText("*Please enter customer name.");
					errorMsgLabelName.setBounds(406, 21, 207, 16);
					errorMsgLabelName.setOpaque(true);
					errorMsgLabelName.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgLabelName.setVisible(false);
				}
				{
					errorMsgNIRC = new JLabel();
					CustomerCenterPanel.add(errorMsgNIRC);
					errorMsgNIRC.setText("*Please enter NIRC.");
					errorMsgNIRC.setBounds(406, 49, 169, 16);
					errorMsgNIRC.setOpaque(true);
					errorMsgNIRC.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgNIRC.setVisible(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void CancelButtonActionPerformed(ActionEvent evt) {
		System.out.println("CancelButton.actionPerformed, event="+evt);
		
		this.setVisible(false);
	}
	
	private void SubmitButtonActionPerformed(ActionEvent evt) throws BillingSystemException {
		System.out.println("SubmitButton.actionPerformed, event="+evt);

		
		try{
			if (validateControls()){
				
				AccountMgr accountMgr= new AccountMgr();
				System.out.println("Before save");
				Customer cust =accountMgr.createCustomer(this.CustNameText.getText(),this.CustNameText.getText(),this.CustContactTelText.getText(),this.CustAdd1Text.getText(),this.CustAdd2Text.getText(),this.CustAdd3Text.getText(),this.InterestingText.getText() );
				System.out.println("save");
				System.out.println(cust.getAccIdByCust());
				if ( cust.getAccIdByCust() != null){
					JOptionPane.showMessageDialog(null ,"Customer Profile is created successfully.","BillingSystem ",1);
					clearErrorMsgData();
				}
				
				
			}
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}


	}

	private boolean validateControls(){
		boolean bReturn= true;
		if (StringUtil.isNullOrEmpty(this.CustNameText.getText())){
			// display error message
			errorMsgLabelName.setVisible(true);
			bReturn= false;
		}
		
		if (StringUtil.isNullOrEmpty(this.CustNIRCText.getText())){
			// display error message
			errorMsgNIRC.setVisible(true);
			bReturn= false;
		}		
		
		return bReturn;
	}
	
	private void clearErrorMsgData(){
		errorMsgLabelName.setVisible(false);
		errorMsgNIRC.setVisible(false);

	}
}
