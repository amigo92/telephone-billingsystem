package sg.edu.nus.iss.billsys.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import sg.edu.nus.iss.billsys.constant.ComplaintStatus;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.util.NumberUtil;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.CustComplaint;
import sg.edu.nus.iss.billsys.vo.Customer;

public class UpdateComplaintPanel extends JPanel {
	private BillingWindow window;
	private JPanel customerIdPanel;
	private JPanel tablePanel;
	private JPanel statusPanel;
	private JPanel buttonPanel;
	private JButton cancelButton;
	private JTextArea complaintTextArea;
	private JLabel complaintLabel;
	private JComboBox statusComboBox;
	private JLabel statusLabel;
	private JTextField complaintIdTextField;
	private JLabel complaintIdLabel;
	private JTable complaintTable;
	private JSeparator jSeparator1;
	private JLabel getComplaintsLabel;
	private JTextField customerIdTextField;
	private JRadioButton nricRadioButton;
	private JRadioButton accountNoRadioButton;
	private JScrollPane updateComplaintScrollPane;
	private JButton getComplaintsButton;
	private JLabel errorMessageLabel;
	private JButton updateLogButton;
	private JLabel customerIdLabel;
	private ButtonGroup customerIdButtonGroup;

	public UpdateComplaintPanel(BillingWindow window) {
		super();
		this.window = window;
		this.window.setTitle("Billing System > Update Complaint");
		initGUI();
	}

	private void initGUI() {
		try {
			this.setLayout(new GridLayout(0, 1));

			getComplaintsLabel = new JLabel();
			getComplaintsLabel.setText("Get Complaints:");
			this.add(getComplaintsLabel);

			customerIdPanel = new JPanel();
			customerIdPanel.setLayout(formatGridLayout(new GridLayout(0, 5)));

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

			getComplaintsButton = new JButton();
			getComplaintsButton.setText("Get Complaints");
			getComplaintsButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					getComplaintsButtonActionPerformed(arg0);
				}
			});
			customerIdPanel.add(getComplaintsButton);

			this.add(customerIdPanel);

			jSeparator1 = new JSeparator();
			this.add(jSeparator1);

			TableModel complaintTableModel = new DefaultTableModel(
					new String[3][4], getColumnNames());
			complaintTable = new JTable();
			complaintTable.setPreferredSize(new java.awt.Dimension(485, 102));
			complaintTable.setModel(complaintTableModel);
			JTableHeader header = complaintTable.getTableHeader();
			header.setBackground(Color.yellow);
			updateComplaintScrollPane = new JScrollPane(complaintTable);
			complaintTable.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent event) {
							int viewRow = complaintTable.getSelectedRow();
							if (viewRow >= 0) {
								System.out.println("["
										+ complaintTable.getModel().getValueAt(
												viewRow, 0)
										+ ";"
										+ complaintTable.getModel().getValueAt(
												viewRow, 1)
										+ ";"
										+ complaintTable.getModel().getValueAt(
												viewRow, 2)
										+ ";"
										+ complaintTable.getModel().getValueAt(
												viewRow, 3) + "]");
								complaintIdTextField.setText(complaintTable
										.getModel().getValueAt(viewRow, 0)
										.toString());
								statusComboBox.setSelectedItem(complaintTable
										.getModel().getValueAt(viewRow, 3)
										.toString());
								complaintTextArea.setText(complaintTable
										.getModel().getValueAt(viewRow, 2)
										.toString());
							}
						}
					});
			this.add(updateComplaintScrollPane);

			statusPanel = new JPanel();
			statusPanel.setLayout(formatGridLayout(new GridLayout(0, 4)));

			complaintIdLabel = new JLabel("Complaint Id:");
			statusPanel.add(complaintIdLabel);

			complaintIdTextField = new JTextField();
			statusPanel.add(complaintIdTextField);
			complaintIdTextField.setEnabled(false);

			statusLabel = new JLabel();
			statusLabel.setText("Status:");
			statusPanel.add(statusLabel);

			ComboBoxModel stautsComboBoxModel = new DefaultComboBoxModel(StringUtil.getComplaintStatus());
			statusComboBox = new JComboBox();
			statusComboBox.setModel(stautsComboBoxModel);
			statusComboBox.setEnabled(true);

			statusPanel.add(statusComboBox);

			this.add(statusPanel);

			complaintLabel = new JLabel();
			complaintLabel.setText("Complaint:");
			this.add(complaintLabel);

			complaintTextArea = new JTextArea();
			complaintTextArea.setEnabled(false);
			this.add(complaintTextArea);

			errorMessageLabel = new JLabel();
			this.add(errorMessageLabel);

			buttonPanel = new JPanel();
			buttonPanel.setLayout(formatGridLayout(new GridLayout(0, 2)));

			updateLogButton = new JButton();
			updateLogButton.setText("Update Log");
			updateLogButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UpdateComplaintPanel.this.updateLogButtonActionPerformed(e);
				}
			});
			buttonPanel.add(updateLogButton);

			cancelButton = new JButton();
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UpdateComplaintPanel.this.cancelButtonActionPerformed(e);
				}
			});
			buttonPanel.add(cancelButton);

			this.add(buttonPanel);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getComplaintsButtonActionPerformed(ActionEvent evt) {
		System.out.println("getComplaintsButtonActionPerformed, event=" + evt);
		List<CustComplaint> complaints = null;
		Customer customer = null;

		if (StringUtil.isNullOrEmpty(this.customerIdTextField.getText())) {
			errorMessageLabel.setText("Invalid/ Empty customer Id!");
			errorMessageLabel.setForeground(Color.RED);
			return;
		}

		String customerIdType = null;

		if (accountNoRadioButton.isSelected()) {
			customerIdType = accountNoRadioButton.getActionCommand();
			try{
			customer = MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(this.customerIdTextField.getText().trim());
			}catch (Exception e) {
				e.printStackTrace();
			}
			if (customer == null) {
				errorMessageLabel.setText("Invalid Account #!");
				errorMessageLabel.setForeground(Color.RED);
				return;
			}
		}
		if (nricRadioButton.isSelected()) {
			customerIdType = nricRadioButton.getActionCommand();
			try{
			customer = MgrFactory.getAccountMgr().getCustomerDetailsById(this.customerIdTextField.getText().trim());
			}catch (Exception e) {
				e.printStackTrace();
			}
			if (customer == null) {
				errorMessageLabel.setText("Invalid NRIC!");
				errorMessageLabel.setForeground(Color.RED);
				return;
			}
		}

		if ("accountNo".equalsIgnoreCase(customerIdType)) {
			try {
				complaints = MgrFactory.getComplaintMgr()
						.getComplaintByAccount(
								customerIdTextField.getText().trim());
			} catch (BillingSystemException e) {
				e.printStackTrace();
				errorMessageLabel.setText("Internal error occurred while retrieving complaints!");
				return;
			}
		} else if ("nric".equalsIgnoreCase(customerIdType)) {
			try {
				complaints = MgrFactory.getComplaintMgr()
						.getComplaintByCustomerId(
								customerIdTextField.getText().trim());
			} catch (BillingSystemException e) {
				e.printStackTrace();
				errorMessageLabel.setText("Internal error occurred while retrieving complaints!");
				return;
			}
		}

		 if (complaints != null && complaints.size() > 0) {
			// populate the table
			TableModel complaintTableModel = new DefaultTableModel(
					getTableData(complaints), getColumnNames());
			complaintTable.setModel(complaintTableModel);
		 }
	}

	private GridLayout formatGridLayout(GridLayout layout) {
		layout.setHgap(5);
		layout.setVgap(5);
		return layout;
	}

	private void updateLogButtonActionPerformed(ActionEvent arg0) {
		System.out.println("logButton.actionPerformed, event=" + arg0);
		String customerIdType = null;
		long returnValue = 0;

//		if (StringUtil.isNullOrEmpty(customerIdTextField.getText())) {
//			errorMessageLabel.setText("Invalid/ Empty Customer Id!");
//			errorMessageLabel.setForeground(Color.RED);
//			return;
//		}
//
//		if (StringUtil.isNullOrEmpty(complaintTextArea.getText())) {
//			errorMessageLabel.setText("Invalid/ Empty Complaint!");
//			errorMessageLabel.setForeground(Color.RED);
//			return;
//		}

//		if (accountNoRadioButton.isSelected()) {
//			customerIdType = accountNoRadioButton.getActionCommand();
//		}
//		if (nricRadioButton.isSelected()) {
//			customerIdType = nricRadioButton.getActionCommand();
//		}

		System.out.println("customerId:" + customerIdTextField.getText());
		System.out.println("Complaint:" + complaintTextArea.getText());
		System.out.println("customerIdType:" + customerIdType);
		System.out.println("Status:"
				+ statusComboBox.getSelectedItem().toString());

		ComplaintStatus status = null;
		
		if (ComplaintStatus.COMPLETED.toString().equalsIgnoreCase(statusComboBox.getSelectedItem().toString())) {
			status = ComplaintStatus.COMPLETED;
		} else if (ComplaintStatus.PENDING.toString().equalsIgnoreCase(statusComboBox.getSelectedItem().toString())) {
			status = ComplaintStatus.PENDING;
		} 
		
		
		
		if (NumberUtil.isLong(complaintIdTextField.getText().trim())) {
			try {
				returnValue = MgrFactory.getComplaintMgr().updateComplaint(
						Long.parseLong(complaintIdTextField.getText().trim()), status);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BillingSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		if ("nric".equalsIgnoreCase(customerIdType)) {
//			try {
//				returnValue = MgrFactory.getComplaintMgr()
//						.createComplaintByCustomerId(
//								customerIdTextField.getText().trim(),
//								complaintTextArea.getText().trim());
//			} catch (BillingSystemException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else if ("accountNo".equalsIgnoreCase(customerIdType)) {
//			try {
//				returnValue = MgrFactory.getComplaintMgr()
//						.createComplaintByAccount(
//								customerIdTextField.getText().trim(),
//								complaintTextArea.getText().trim());
//			} catch (BillingSystemException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		// set error message
		if (returnValue > 0) {
			errorMessageLabel.setText("Successfully updated the complaint.");
			errorMessageLabel.setForeground(Color.BLUE);
		} else {
			errorMessageLabel
					.setText("Internal error occurred during the complaint updation!.");
			errorMessageLabel.setForeground(Color.RED);
		}
	}

	private void cancelButtonActionPerformed(ActionEvent arg0) {
		System.out.println("actionPerformed, event=" + arg0);
		this.setVisible(false);
	}

	private String[] getColumnNames() {
		String[] cols = { "Id", "Date/ Time", "Complaint", "Status" };
		return cols;
	}

	private String[][] getTableData(List<CustComplaint> complaints) {
		String[][] data = new String[complaints.size()][4];
		int rowNum = 0;
		for (CustComplaint complaint : complaints) {
			data[rowNum][0] = complaint.getComplaint_id();
			data[rowNum][1] = complaint.getComplaintDate().toString();
			data[rowNum][2] = complaint.getComplaint_Details();
			data[rowNum][3] = complaint.getStatus().toString();
			rowNum++;
		}
		return data;
	}

	private String[][] getTableTestData(List<CustComplaint> complaints) {
		String[][] data = new String[4][4];
		data[0][0] = "1";
		data[0][1] = "1/1/2011";
		data[0][2] = "complaint #1";
		data[0][3] = "Pending";
		data[1][0] = "2";
		data[1][1] = "1/2/2011";
		data[1][2] = "complaint #2";
		data[1][3] = "Completed";
		data[2][0] = "3";
		data[2][1] = "1/3/2011";
		data[2][2] = "complaint #3";
		data[2][3] = "Pending";
		data[3][0] = "4";
		data[3][1] = "1/4/2011";
		data[3][2] = "complaint #4";
		data[3][3] = "Completed";
		return data;
	}

}
