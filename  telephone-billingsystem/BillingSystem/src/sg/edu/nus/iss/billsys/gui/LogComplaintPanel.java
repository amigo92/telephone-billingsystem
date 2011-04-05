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
import sg.edu.nus.iss.billsys.resource.ResourceHandler;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Customer;

/**
 * 
 * @author L Sriragavan
 * 
 * This is the GUI class to log complaints. 
 *
 */
public class LogComplaintPanel extends javax.swing.JPanel {
	/**
	 * This is the customer Id input field. 
	 */
	private JTextField customerIdTextField;
	/**
	 * This is the NRIC selection input field. 
	 */
	private JRadioButton nricRadioButton;
	/**
	 * This is the account # selection input field. 
	 */
	private JRadioButton accountNoRadioButton;
	/**
	 * This is the label for customer Id input field. 
	 */
	private JLabel customerIdLabel;
	/**
	 * This is the label for status input field. 
	 */
	private JLabel statusLabel;
	/**
	 * Blank label for spacing. 
	 */
	private JLabel blankLabel1;
	/**
	 * This is the label for complaint input field. 
	 */
	private JLabel complaintLabel;
	/**
	 * Blank label for spacing. 
	 */
	private JLabel blankLabel2;
	/**
	 * This is the customer Id input field 
	 */
	private JButton cancelButton;
	/**
	 * This is the cancel button. 
	 */
	private JButton logButton;
	/**
	 * This is the cancel button. 
	 */
	/**
	 * This is the customer input field. 
	 */
	private JTextArea complaintTextArea;
	/**
	 * This is the status input field. 
	 */
	private JComboBox statusComboBox;
	/**
	 * This is the separator instance. 
	 */
	private JSeparator jSeparator1;
	/**
	 * This is the label for complaint input field. 
	 */
	private JLabel logComplaintLabel;
	/**
	 * This is the instance for BillingWindow class. 
	 */
	private BillingWindow window;
	/**
	 * This is the group instance for NRIC & account # selection. 
	 */
	private ButtonGroup customerIdButtonGroup;

	/**
	 * Default constructor
	 * @param window BillingWindow instance.
	 */
	public LogComplaintPanel(BillingWindow window) {
		super();
		this.window = window;
		initGUI();
	}

	/**
	 * This method draws the GUI.
	 */
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setMinimumSize(new java.awt.Dimension(800, 600));
			this.setMaximumSize(new java.awt.Dimension(800, 600));
			
			customerIdButtonGroup = new ButtonGroup();
			{
				logComplaintLabel = new JLabel();
				this.add(logComplaintLabel);
				logComplaintLabel.setText(ResourceHandler.getLabel("logcomplaintform.lbl.heading"));
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
				customerIdLabel.setText(ResourceHandler.getLabel("logcomplaintform.lbl.customerid"));
				customerIdLabel.setPreferredSize(new java.awt.Dimension(200, 20));
			}
			{
				accountNoRadioButton = new JRadioButton();
				this.add(accountNoRadioButton);
				accountNoRadioButton.setText(ResourceHandler.getLabel("logcomplaintform.lbl.accountno"));
				accountNoRadioButton.setPreferredSize(new java.awt.Dimension(150, 20));
				accountNoRadioButton.setActionCommand("accountNo");
				customerIdButtonGroup.add(accountNoRadioButton);
			}
			{
				nricRadioButton = new JRadioButton();
				this.add(nricRadioButton);
				nricRadioButton.setText(ResourceHandler.getLabel("logcomplaintform.lbl.nric"));
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
				statusLabel.setText(ResourceHandler.getLabel("logcomplaintform.lbl.status"));
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
				complaintLabel.setText(ResourceHandler.getLabel("logcomplaintform.lbl.complaint"));
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
				logButton.setText(ResourceHandler.getLabel("logcomplaintform.btn.log"));
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
				cancelButton.setText(ResourceHandler.getLabel("logcomplaintform.btn.cancel"));
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
	
	/**
	 * The action listener method to be called upon clicking the Log button.
	 * @param arg0 ActionEvent
	 */
	private void logButtonActionPerformed(ActionEvent arg0) {
		BillingSystemLogger.logInfo("Inside logButtonActionPerformed(), arg0=" + arg0);
		String customerIdType = null;
		long returnValue = 0;
		Customer customer = null;

		if (StringUtil.isNullOrEmpty(customerIdTextField.getText())) {
			JOptionPane.showMessageDialog(window, ResourceHandler.getError("logcomplaintform.error1"), "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (StringUtil.isNullOrEmpty(complaintTextArea.getText())) {
			JOptionPane.showMessageDialog(window, ResourceHandler.getError("logcomplaintform.error2"), "Error Message", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(window, ResourceHandler.getError("logcomplaintform.error3"), "Error Message", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(window, ResourceHandler.getError("logcomplaintform.error4"), "Error Message", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(window, ResourceHandler.getMessages("logcomplaintform.success1"), "Success Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(window, ResourceHandler.getError("logcomplaintform.error5"), "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * The action listener method to be called upon clicking the Cancel button.
	 * @param arg0 ActionEvent
	 */
	private void cancelButtonActionPerformed(ActionEvent arg0) {
		BillingSystemLogger.logInfo("Inside cancelButtonActionPerformed(), event=" + arg0);
		this.setVisible(false);
	}

}
