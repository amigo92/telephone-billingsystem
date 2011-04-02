package sg.edu.nus.iss.billsys.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.constant.ComplaintStatus;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.util.NumberUtil;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.CustComplaint;
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
public class UpdateComplaintPanel extends javax.swing.JPanel {

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	private BillingWindow window;
	private JLabel customerIdLabel;
	private JButton getComplaintsButton;
	private JLabel complaintIdLabel;
	private JSeparator jSeparator3;
	private JTable complaintsTable;
	private JScrollPane complaintsScrollPane;
	private JLabel blankLabel1;
	private JLabel statusLabel;
	private JButton cancelButton;
	private JLabel blankLabel2;
	private JButton updateButton;
	private JTextArea complaintTextArea;
	private JLabel complaintLabel;
	private JComboBox statusComboBox;
	private JTextField complaintIdTextField;
	private JSeparator jSeparator2;
	private JTextField customerIdTextField;
	private JRadioButton nricRadioButton;
	private JRadioButton accountNoRadioButton;
	private JSeparator jSeparator1;
	private JLabel updateComplaintsLabel;
	private ButtonGroup customerIdButtonGroup;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new TestPanel(new BillingWindow()));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public UpdateComplaintPanel(BillingWindow window) {
		super();
		this.window = window;
		this.window.setTitle("Billing System > Update Complaint");
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setMinimumSize(new java.awt.Dimension(800, 600));
			this.setMaximumSize(new java.awt.Dimension(800, 600));
			
			customerIdButtonGroup = new ButtonGroup();
			
			{
				updateComplaintsLabel = new JLabel();
				this.add(updateComplaintsLabel);
				updateComplaintsLabel.setText("Update Complaints");
				updateComplaintsLabel.setPreferredSize(new java.awt.Dimension(750, 23));
				updateComplaintsLabel.setFont(new java.awt.Font("Tahoma",1,14));
			}
			{
				jSeparator1 = new JSeparator();
				this.add(jSeparator1);
				jSeparator1.setPreferredSize(new java.awt.Dimension(750, 15));
			}
			{
				customerIdLabel = new JLabel();
				this.add(customerIdLabel);
				customerIdLabel.setText("Customer Id:");
				customerIdLabel.setPreferredSize(new java.awt.Dimension(200, 14));
			}
			{
				accountNoRadioButton = new JRadioButton();
				this.add(accountNoRadioButton);
				accountNoRadioButton.setText("Account #");
				accountNoRadioButton.setPreferredSize(new java.awt.Dimension(100, 18));
				accountNoRadioButton.setActionCommand("accountNo");
				customerIdButtonGroup.add(accountNoRadioButton);
			}
			{
				nricRadioButton = new JRadioButton();
				this.add(nricRadioButton);
				nricRadioButton.setText("NRIC");
				nricRadioButton.setPreferredSize(new java.awt.Dimension(100, 18));
				nricRadioButton.setSelected(true);
				nricRadioButton.setActionCommand("nric");
				customerIdButtonGroup.add(nricRadioButton);
			}
			{
				customerIdTextField = new JTextField();
				this.add(customerIdTextField);
				customerIdTextField.setPreferredSize(new java.awt.Dimension(200, 21));
			}
			{
				getComplaintsButton = new JButton();
				this.add(getComplaintsButton);
				getComplaintsButton.setText("Get Complaints");
				getComplaintsButton.setPreferredSize(new java.awt.Dimension(150, 21));
				getComplaintsButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						getComplaintsButtonActionPerformed(arg0);
					}
				});
			}
			{
				jSeparator2 = new JSeparator();
				this.add(jSeparator2);
				jSeparator2.setPreferredSize(new java.awt.Dimension(750, 15));
			}
			{
				complaintsScrollPane = new JScrollPane();
				this.add(complaintsScrollPane);
				complaintsScrollPane.setPreferredSize(new java.awt.Dimension(750, 125));
				{
					TableModel complaintsTable1Model = 
						new DefaultTableModel(new String[3][4], getColumnNames());
					complaintsTable =  new JTable(){
						public boolean isCellEditable(int rowIndex, int colIndex) {
					        return false;   //Disallow the editing of any cell
					    }
					};

					complaintsScrollPane.setViewportView(complaintsTable);
					complaintsTable.setModel(complaintsTable1Model);
					complaintsTable.setPreferredSize(new java.awt.Dimension(750, 125));
					JTableHeader header = complaintsTable.getTableHeader();
					header.setBackground(Color.yellow);
					complaintsTable.getSelectionModel().addListSelectionListener(
							new ListSelectionListener() {
								public void valueChanged(ListSelectionEvent event) {
									int viewRow = complaintsTable.getSelectedRow();
									if (viewRow >= 0) {
										BillingSystemLogger.logInfo("["
												+ complaintsTable.getModel().getValueAt(
														viewRow, 0)
												+ ";"
												+ complaintsTable.getModel().getValueAt(
														viewRow, 1)
												+ ";"
												+ complaintsTable.getModel().getValueAt(
														viewRow, 2)
												+ ";"
												+ complaintsTable.getModel().getValueAt(
														viewRow, 3) + "]");
										complaintIdTextField.setText(complaintsTable
												.getModel().getValueAt(viewRow, 0)
												.toString());
										statusComboBox.setSelectedItem(complaintsTable
												.getModel().getValueAt(viewRow, 3)
												.toString());
										complaintTextArea.setText(complaintsTable
												.getModel().getValueAt(viewRow, 2)
												.toString());
									}
								}
							});
				}
			}
			{
				jSeparator3 = new JSeparator();
				this.add(jSeparator3);
				jSeparator3.setPreferredSize(new java.awt.Dimension(750, 10));
			}
			{
				complaintIdLabel = new JLabel();
				this.add(complaintIdLabel);
				complaintIdLabel.setText("Complaint Id:");
				complaintIdLabel.setPreferredSize(new java.awt.Dimension(200, 14));
			}
			{
				complaintIdTextField = new JTextField();
				this.add(complaintIdTextField);
				complaintIdTextField.setPreferredSize(new java.awt.Dimension(100, 21));
				complaintIdTextField.setEnabled(false);
			}
			{
				blankLabel1 = new JLabel();
				this.add(blankLabel1);
				blankLabel1.setText("");
				blankLabel1.setPreferredSize(new java.awt.Dimension(150, 14));
			}
			{
				statusLabel = new JLabel();
				this.add(statusLabel);
				statusLabel.setText("Status:");
				statusLabel.setPreferredSize(new java.awt.Dimension(200, 14));
			}
			{
				ComboBoxModel jComboBox1Model = 
					new DefaultComboBoxModel(StringUtil.getComplaintStatus());
				statusComboBox = new JComboBox();
				this.add(statusComboBox);
				statusComboBox.setModel(jComboBox1Model);
				statusComboBox.setPreferredSize(new java.awt.Dimension(100, 21));
			}
			{
				complaintLabel = new JLabel();
				this.add(complaintLabel);
				complaintLabel.setText("Complaint:");
				complaintLabel.setPreferredSize(new java.awt.Dimension(200, 14));
			}
			{
				complaintTextArea = new JTextArea();
				this.add(complaintTextArea);
				complaintTextArea.setPreferredSize(new java.awt.Dimension(550, 95));
				complaintTextArea.setEnabled(false);
			}
			{
				updateButton = new JButton();
				this.add(updateButton);
				updateButton.setText("Update");
				updateButton.setPreferredSize(new java.awt.Dimension(150, 21));
				updateButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						UpdateComplaintPanel.this.updateButtonActionPerformed(e);
					}
				});
			}
			{
				blankLabel2 = new JLabel();
				this.add(blankLabel2);
				blankLabel2.setText("");
				blankLabel2.setPreferredSize(new java.awt.Dimension(50, 14));
			}
			{
				cancelButton = new JButton();
				this.add(cancelButton);
				cancelButton.setText("Cancel");
				cancelButton.setPreferredSize(new java.awt.Dimension(150, 21));
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						UpdateComplaintPanel.this.cancelButtonActionPerformed(e);
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getComplaintsButtonActionPerformed(ActionEvent evt) {
		BillingSystemLogger.logInfo("Inside getComplaintsButtonActionPerformed, event=" + evt);
		List<CustComplaint> complaints = null;
		Customer customer = null;

		if (StringUtil.isNullOrEmpty(this.customerIdTextField.getText())) {
			JOptionPane.showMessageDialog(window, "Empty customer Id!", "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String customerIdType = null;

		if (accountNoRadioButton.isSelected()) {
			customerIdType = accountNoRadioButton.getActionCommand();
			try{
				customer = MgrFactory.getAccountMgr().getCustomerDetailsByAccountId(this.customerIdTextField.getText().trim());
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
			try{
				customer = MgrFactory.getAccountMgr().getCustomerDetailsById(this.customerIdTextField.getText().trim());
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

		if ("accountNo".equalsIgnoreCase(customerIdType)) {
			try {
				complaints = MgrFactory.getComplaintMgr()
						.getComplaintByAccount(
								customerIdTextField.getText().trim());
			} catch (BillingSystemException e) {
				JOptionPane.showMessageDialog(window, e.getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(window, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if ("nric".equalsIgnoreCase(customerIdType)) {
			try {
				complaints = MgrFactory.getComplaintMgr()
						.getComplaintByCustomerId(
								customerIdTextField.getText().trim());
			} catch (BillingSystemException e) {
				JOptionPane.showMessageDialog(window, e.getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(window, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		 if (complaints != null && complaints.size() > 0) {
			// populate the table
			TableModel complaintTableModel = new DefaultTableModel(
					getTableData(complaints), getColumnNames());
			complaintsTable.setModel(complaintTableModel);
		 }
	}

	private void updateButtonActionPerformed(ActionEvent arg0) {
		BillingSystemLogger.logInfo("Inside updateButtonActionPerformed(), arg0=" + arg0);
		String customerIdType = null;
		long returnValue = 0;

		BillingSystemLogger.logInfo("customerId:" + customerIdTextField.getText());
		BillingSystemLogger.logInfo("Complaint:" + complaintTextArea.getText());
		BillingSystemLogger.logInfo("customerIdType:" + customerIdType);
		BillingSystemLogger.logInfo("Status:"
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
			JOptionPane.showMessageDialog(window, "Successfully updated the complaint.", "Success Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(window, "Internal error occurred during the complaint updation!.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cancelButtonActionPerformed(ActionEvent arg0) {
		BillingSystemLogger.logInfo("Inside cancelButtonActionPerformed(), event=" + arg0);
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
}
