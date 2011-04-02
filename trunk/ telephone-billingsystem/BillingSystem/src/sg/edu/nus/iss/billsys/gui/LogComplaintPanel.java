package sg.edu.nus.iss.billsys.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.constant.ComplaintStatus;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Customer;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class LogComplaintPanel extends javax.swing.JPanel {
	private JTextField customerIdTextField;
	private JRadioButton nricRadioButton;
	private JRadioButton accountNoRadioButton;
	private JLabel customerIdLabel;
	private JLabel statusLabel;
	private JLabel blankLabel1;
	private JLabel complaintLabel;
	private JLabel blankLabel2;
	private JButton cancelButton;
	private JButton logButton;
//	private JLabel errorMessageLabel;
	private JTextArea complaintTextArea;
	private JComboBox statusComboBox;
	private JSeparator jSeparator1;
	private JLabel logComplaintLabel;
	private BillingWindow window;
	private ButtonGroup customerIdButtonGroup;


	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new LogComplaintPanel(new BillingWindow()));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public LogComplaintPanel(BillingWindow window) {
		super();
		this.window = window;
//		this.window.setTitle("Billing System > Log Complaint");
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setMinimumSize(new java.awt.Dimension(800, 600));
			this.setMaximumSize(new java.awt.Dimension(800, 600));
			
			customerIdButtonGroup = new ButtonGroup();
			{
				logComplaintLabel = new JLabel();
				this.add(logComplaintLabel);
				logComplaintLabel.setText("Log Complaint");
				logComplaintLabel.setFont(new java.awt.Font("Tahoma",1,14));
				logComplaintLabel.setPreferredSize(new java.awt.Dimension(750, 25));
			}
			{
				jSeparator1 = new JSeparator();
				this.add(jSeparator1);
				jSeparator1.setPreferredSize(new java.awt.Dimension(750, 25));
			}
			{
				customerIdLabel = new JLabel();
				this.add(customerIdLabel);
				customerIdLabel.setText("Customer Id:");
				customerIdLabel.setPreferredSize(new java.awt.Dimension(200, 20));
			}
			{
				accountNoRadioButton = new JRadioButton();
				this.add(accountNoRadioButton);
				accountNoRadioButton.setText("Account #");
				accountNoRadioButton.setPreferredSize(new java.awt.Dimension(150, 20));
				accountNoRadioButton.setActionCommand("accountNo");
				customerIdButtonGroup.add(accountNoRadioButton);
			}
			{
				nricRadioButton = new JRadioButton();
				this.add(nricRadioButton);
				nricRadioButton.setText("NRIC");
				nricRadioButton.setPreferredSize(new java.awt.Dimension(150, 20));
				nricRadioButton.setSelected(true);
				nricRadioButton.setActionCommand("nric");
				customerIdButtonGroup.add(nricRadioButton);
			}
			{
				customerIdTextField = new JTextField();
				this.add(customerIdTextField);
				customerIdTextField.setPreferredSize(new java.awt.Dimension(250, 20));
			}
			{
				statusLabel = new JLabel();
				this.add(statusLabel);
				statusLabel.setText("Status:");
				statusLabel.setPreferredSize(new java.awt.Dimension(200, 20));
			}
			{
				ComboBoxModel jComboBox1Model = 
					new DefaultComboBoxModel(StringUtil.getComplaintStatus());
				statusComboBox = new JComboBox();
				this.add(statusComboBox);
				statusComboBox.setModel(jComboBox1Model);
				statusComboBox.setPreferredSize(new java.awt.Dimension(200, 20));
				statusComboBox.setSelectedItem(ComplaintStatus.PENDING.toString());
				statusComboBox.setEnabled(false);
			}
			{
				blankLabel1 = new JLabel();
				this.add(blankLabel1);
				blankLabel1.setText("");
				blankLabel1.setPreferredSize(new java.awt.Dimension(350, 20));
			}
			{
				complaintLabel = new JLabel();
				this.add(complaintLabel);
				complaintLabel.setText("Complaint:");
				complaintLabel.setPreferredSize(new java.awt.Dimension(200, 20));
			}
			{
				complaintTextArea = new JTextArea();
				this.add(complaintTextArea);
				complaintTextArea.setPreferredSize(new java.awt.Dimension(550, 91));
			}
			{
//				errorMessageLabel = new JLabel();
//				this.add(errorMessageLabel);
//				errorMessageLabel.setText("");
//				errorMessageLabel.setPreferredSize(new java.awt.Dimension(750, 20));
			}
			{
				logButton = new JButton();
				this.add(logButton);
				logButton.setText("Log");
				logButton.setPreferredSize(new java.awt.Dimension(150, 20));
				logButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LogComplaintPanel.this.logButtonActionPerformed(e);
					}
				});
			}
			{
				blankLabel2 = new JLabel();
				this.add(blankLabel2);
				blankLabel2.setText("");
				blankLabel2.setPreferredSize(new java.awt.Dimension(50, 20));
			}
			{
				cancelButton = new JButton();
				this.add(cancelButton);
				cancelButton.setText("Cancel");
				cancelButton.setPreferredSize(new java.awt.Dimension(150, 21));
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LogComplaintPanel.this.cancelButtonActionPerformed(e);
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void logButtonActionPerformed(ActionEvent arg0) {
		BillingSystemLogger.logInfo("Inside logButtonActionPerformed(), arg0=" + arg0);
		String customerIdType = null;
		long returnValue = 0;
		Customer customer = null;

		if (StringUtil.isNullOrEmpty(customerIdTextField.getText())) {
			JOptionPane.showMessageDialog(window, "Empty Customer Id!", "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (StringUtil.isNullOrEmpty(complaintTextArea.getText())) {
			JOptionPane.showMessageDialog(window, "Empty Complaint!", "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (accountNoRadioButton.isSelected()) {
			customerIdType = accountNoRadioButton.getActionCommand();

			try {
				customer = MgrFactory.getAccountMgr()
						.getCustomerDetailsByAccountId(
								customerIdTextField.getText().trim());
			} catch (BillingSystemException e) {
				JOptionPane.showMessageDialog(window, e.getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(window, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (customer == null) {
				JOptionPane.showMessageDialog(window, "Invalid Account #!", "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if (nricRadioButton.isSelected()) {
			customerIdType = nricRadioButton.getActionCommand();
			try {
				customer = MgrFactory.getAccountMgr().getCustomerDetailsById(
						customerIdTextField.getText().trim());
			} catch (BillingSystemException e) {
				JOptionPane.showMessageDialog(window, e.getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(window, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (customer == null) {
				JOptionPane.showMessageDialog(window, "Invalid NRIC!", "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		BillingSystemLogger.logInfo("customerId:" + customerIdTextField.getText());
		BillingSystemLogger.logInfo("Complaint:" + complaintTextArea.getText());
		BillingSystemLogger.logInfo("customerIdType:" + customerIdType);
		BillingSystemLogger.logInfo("Status:"
				+ statusComboBox.getSelectedItem().toString());

		if ("nric".equalsIgnoreCase(customerIdType)) {
			try {
				returnValue = MgrFactory.getComplaintMgr()
						.createComplaintByCustomerId(
								customerIdTextField.getText().trim(),
								complaintTextArea.getText().trim());
			} catch (BillingSystemException e) {
				JOptionPane.showMessageDialog(window, e.getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(window, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if ("accountNo".equalsIgnoreCase(customerIdType)) {
			try {
				returnValue = MgrFactory.getComplaintMgr()
						.createComplaintByAccount(
								customerIdTextField.getText().trim(),
								complaintTextArea.getText().trim());
			} catch (BillingSystemException e) {
				JOptionPane.showMessageDialog(window, e.getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(window, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		// set error message
		if (returnValue > 0) {
			JOptionPane.showMessageDialog(window, "Successfully created the complaint.", "Success Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(window, "Internal error occurred during the complaint creation!.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cancelButtonActionPerformed(ActionEvent arg0) {
		BillingSystemLogger.logInfo("Inside cancelButtonActionPerformed(), event=" + arg0);
		this.setVisible(false);
	}

}
