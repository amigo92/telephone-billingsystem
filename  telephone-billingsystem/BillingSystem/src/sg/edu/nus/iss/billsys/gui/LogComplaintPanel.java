package sg.edu.nus.iss.billsys.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.constant.ComplaintStatus;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Customer;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class LogComplaintPanel extends JPanel {
	private BillingWindow window;
	private JPanel customerIdPanel;
	private JPanel statusPanel;
	private JPanel buttonPanel;
	private JButton cancelButton;
	private JTextPane complaintTextPane;
	private JLabel complaintLabel;
	private JComboBox statusComboBox;
	private JLabel statusLabel;
	private JTextField customerIdTextField;
	private JRadioButton nricRadioButton;
	private JRadioButton accountNoRadioButton;
	private JLabel errorMessageLabel;
	private JButton logButton;
	private ButtonGroup customerIdButtonGroup;
	JPanel mainPanel;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */

	public LogComplaintPanel(BillingWindow window) {
		super();
		this.window = window;
		this.window.setTitle("Billing System > Log Complaint");
		initGUI();
	}

	private void initGUI() {
		try {
			this.setLayout(new GridLayout(0, 1));

			customerIdPanel = new JPanel();
			customerIdPanel.setLayout(formatGridLayout(new GridLayout(0, 4)));

			complaintLabel = new JLabel();
			complaintLabel.setText("Complaint:");
			customerIdPanel.add(complaintLabel);

			customerIdButtonGroup = new ButtonGroup();

			accountNoRadioButton = new JRadioButton();
			accountNoRadioButton.setText("Account #");
			accountNoRadioButton.setActionCommand("accountNo");
			customerIdButtonGroup.add(accountNoRadioButton);
			customerIdPanel.add(accountNoRadioButton);

			nricRadioButton = new JRadioButton();
			nricRadioButton.setText("NRIC");
			nricRadioButton.setSelected(true);
			nricRadioButton.setActionCommand("nric");
			customerIdButtonGroup.add(nricRadioButton);
			customerIdPanel.add(nricRadioButton);

			customerIdTextField = new JTextField();
			customerIdPanel.add(customerIdTextField);

			this.add(customerIdPanel);

			statusPanel = new JPanel();
			statusPanel.setLayout(formatGridLayout(new GridLayout(0, 2)));

			statusLabel = new JLabel();
			statusLabel.setText("Status:");
			statusPanel.add(statusLabel);

			ComboBoxModel stautsComboBoxModel = new DefaultComboBoxModel(
					StringUtil.getComplaintStatus());
			statusComboBox = new JComboBox();
			statusComboBox.setSelectedItem(ComplaintStatus.PENDING.toString());
			statusComboBox.setModel(stautsComboBoxModel);
			statusComboBox.setEnabled(false);

			statusPanel.add(statusComboBox);

			this.add(statusPanel);

			complaintLabel = new JLabel();
			complaintLabel.setText("Complaint:");
			this.add(complaintLabel);

			complaintTextPane = new JTextPane();
			this.add(complaintTextPane);

			errorMessageLabel = new JLabel();
			this.add(errorMessageLabel);

			buttonPanel = new JPanel();
			buttonPanel.setLayout(formatGridLayout(new GridLayout(0, 2)));

			logButton = new JButton();
			logButton.setText("Log");
			logButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LogComplaintPanel.this.logButtonActionPerformed(e);
				}
			});
			buttonPanel.add(logButton);

			cancelButton = new JButton();
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LogComplaintPanel.this.cancelButtonActionPerformed(e);
				}
			});
			buttonPanel.add(cancelButton);

			this.add(buttonPanel);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private GridLayout formatGridLayout(GridLayout layout) {
		layout.setHgap(5);
		layout.setVgap(5);
		return layout;
	}

	private void logButtonActionPerformed(ActionEvent arg0) {
		System.out.println("logButton.mouseClicked, event=" + arg0);
		String customerIdType = null;
		long returnValue = 0;
		Customer customer = null;

		if (StringUtil.isNullOrEmpty(customerIdTextField.getText())) {
			errorMessageLabel.setText("Empty Customer Id!");
			errorMessageLabel.setForeground(Color.RED);
			return;
		}

		if (StringUtil.isNullOrEmpty(complaintTextPane.getText())) {
			errorMessageLabel.setText("Empty Complaint!");
			errorMessageLabel.setForeground(Color.RED);
			return;
		}

		if (accountNoRadioButton.isSelected()) {
			customerIdType = accountNoRadioButton.getActionCommand();

			try {
				customer = MgrFactory.getAccountMgr()
						.getCustomerDetailsByAccountId(
								customerIdTextField.getText().trim());
			} catch (BillingSystemException e) {
				errorMessageLabel.setText(e.getMessagebyException());
				errorMessageLabel.setForeground(Color.RED);
				return;
			} catch (Exception e) {
				errorMessageLabel.setText(new BillingSystemException(e).getMessagebyException());
				errorMessageLabel.setForeground(Color.RED);
				return;
			}
			
			if (customer == null) {
				errorMessageLabel.setText("Invalid Account #!");
				errorMessageLabel.setForeground(Color.RED);
				return;
			}
		}
		if (nricRadioButton.isSelected()) {
			customerIdType = nricRadioButton.getActionCommand();
			try {
				customer = MgrFactory.getAccountMgr().getCustomerDetailsById(
						customerIdTextField.getText().trim());
			} catch (BillingSystemException e) {
				errorMessageLabel.setText(e.getMessagebyException());
				errorMessageLabel.setForeground(Color.RED);
				return;
			} catch (Exception e) {
				errorMessageLabel.setText(new BillingSystemException(e).getMessagebyException());
				errorMessageLabel.setForeground(Color.RED);
				return;
			}

			if (customer == null) {
				errorMessageLabel.setText("Invalid NRIC!");
				errorMessageLabel.setForeground(Color.RED);
				return;
			}
		}

		System.out.println("customerId:" + customerIdTextField.getText());
		System.out.println("Complaint:" + complaintTextPane.getText());
		System.out.println("customerIdType:" + customerIdType);
		System.out.println("Status:"
				+ statusComboBox.getSelectedItem().toString());

		if ("nric".equalsIgnoreCase(customerIdType)) {
			try {
				returnValue = MgrFactory.getComplaintMgr()
						.createComplaintByCustomerId(
								customerIdTextField.getText().trim(),
								complaintTextPane.getText().trim());
			} catch (BillingSystemException e) {
				errorMessageLabel.setText(e.getMessagebyException());
				errorMessageLabel.setForeground(Color.RED);
				return;
			} catch (Exception e) {
				errorMessageLabel.setText(new BillingSystemException(e).getMessagebyException());
				errorMessageLabel.setForeground(Color.RED);
				return;
			}
		} else if ("accountNo".equalsIgnoreCase(customerIdType)) {
			try {
				returnValue = MgrFactory.getComplaintMgr()
						.createComplaintByAccount(
								customerIdTextField.getText().trim(),
								complaintTextPane.getText().trim());
			} catch (BillingSystemException e) {
				errorMessageLabel.setText(e.getMessagebyException());
				errorMessageLabel.setForeground(Color.RED);
				return;
			} catch (Exception e) {
				errorMessageLabel.setText(new BillingSystemException(e).getMessagebyException());
				errorMessageLabel.setForeground(Color.RED);
				return;
			}
		}

		// set error message
		if (returnValue > 0) {
			errorMessageLabel.setText("Successfully created the complaint.");
			errorMessageLabel.setForeground(Color.BLUE);
		} else {
			errorMessageLabel
					.setText("Internal error occurred during the complaint creation!.");
			errorMessageLabel.setForeground(Color.RED);
		}
	}

	private void cancelButtonActionPerformed(ActionEvent arg0) {
		System.out.println("cancelButton.mouseClicked, event=" + arg0);
		this.setVisible(false);
	}
}
