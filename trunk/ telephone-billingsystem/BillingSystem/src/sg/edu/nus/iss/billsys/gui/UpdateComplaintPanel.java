package sg.edu.nus.iss.billsys.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import sg.edu.nus.iss.billsys.resource.ResourceHandler;
import sg.edu.nus.iss.billsys.util.NumberUtil;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.CustComplaint;
import sg.edu.nus.iss.billsys.vo.Customer;

/**
 * @author L Sriragavan
 *
 * This is the GUI class to update complaints
 */
public class UpdateComplaintPanel extends javax.swing.JPanel {
	/**
	 * Instance of the BillingWindow class.
	 */
	private BillingWindow window;
	/**
	 * Update complaint heading label.
	 */
	private JLabel updateComplaintsLabel;
	/**
	 * This is the label for the customer Id field.
	 */
	private JLabel customerIdLabel;
	/**
	 * This is the get complaint button.
	 */
	private JButton getComplaintsButton;
	/**
	 * This is the label for the complaint Id field.
	 */
	private JLabel complaintIdLabel;
	/**
	 * Separator variable.
	 */
	private JSeparator jSeparator3;
	/**
	 * This is the table instance to display complaints.
	 */
	private JTable complaintsTable;
	/**
	 * This is the container to contain the complaintTable instance.
	 */
	private JScrollPane complaintsScrollPane;
	/**
	 * Blank label used for spacing.
	 */
	private JLabel blankLabel1;
	/**
	 * This is the label for the status field.
	 */
	private JLabel statusLabel;
	/**
	 * This is the cancel button instance.
	 */
	private JButton cancelButton;
	/**
	 * Blank label used for spacing.
	 */
	private JLabel blankLabel2;
	/**
	 * This is the update button to update complaints.
	 */
	private JButton updateButton;
	/**
	 * This is the field to get comlaint input.
	 */
	private JTextArea complaintTextArea;
	/**
	 * This is the label for the complaint field.
	 */
	private JLabel complaintLabel;
	/**
	 * This is the status input field.
	 */
	private JComboBox statusComboBox;
	/**
	 * This is the complaint Id input field.
	 */
	private JTextField complaintIdTextField;
	/**
	 * This is the separator instance.
	 */
	private JSeparator jSeparator2;
	/**
	 * This is the customer Id input field.
	 */
	private JTextField customerIdTextField;
	/**
	 * This is the NRIC selection button.
	 */
	private JRadioButton nricRadioButton;
	/**
	 * This is the account # selection button.
	 */
	private JRadioButton accountNoRadioButton;
	/**
	 * This is a separator field.
	 */
	private JSeparator jSeparator1;
	/**
	 * This is a group button to which groups NRIC & Account # radio buttons.
	 */
	private ButtonGroup customerIdButtonGroup;

	/**
	 * Default constructor
	 * @param window BillingWindow instance
	 */
	public UpdateComplaintPanel(BillingWindow window) {
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
				updateComplaintsLabel = new JLabel();
				this.add(updateComplaintsLabel);
				updateComplaintsLabel.setText(ResourceHandler.getLabel("updatecomplaintform.lbl.heading"));
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
				customerIdLabel.setText(ResourceHandler.getLabel("updatecomplaintform.lbl.customerid"));
				customerIdLabel.setPreferredSize(new java.awt.Dimension(200, 14));
			}
			{
				accountNoRadioButton = new JRadioButton();
				this.add(accountNoRadioButton);
				accountNoRadioButton.setText(ResourceHandler.getLabel("updatecomplaintform.lbl.accountno"));
				accountNoRadioButton.setPreferredSize(new java.awt.Dimension(100, 18));
				accountNoRadioButton.setActionCommand("accountNo");
				customerIdButtonGroup.add(accountNoRadioButton);
			}
			{
				nricRadioButton = new JRadioButton();
				this.add(nricRadioButton);
				nricRadioButton.setText(ResourceHandler.getLabel("updatecomplaintform.lbl.nric"));
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
				getComplaintsButton.setText(ResourceHandler.getLabel("updatecomplaintform.btn.getcomplaints"));
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
				complaintIdLabel.setText(ResourceHandler.getLabel("updatecomplaintform.lbl.complaintid"));
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
				statusLabel.setText(ResourceHandler.getLabel("updatecomplaintform.lbl.status"));
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
				complaintLabel.setText(ResourceHandler.getLabel("updatecomplaintform.lbl.complaint"));
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
				updateButton.setText(ResourceHandler.getLabel("updatecomplaintform.btn.update"));
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
				cancelButton.setText(ResourceHandler.getLabel("updatecomplaintform.btn.cancel"));
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
	
	/**
	 * This is the event listener method to be called upon clicking the get complaints button.
	 * @param evt ActionEvent
	 */
	private void getComplaintsButtonActionPerformed(ActionEvent evt) {
		BillingSystemLogger.logInfo("Inside getComplaintsButtonActionPerformed, event=" + evt);
		List<CustComplaint> complaints = null;
		Customer customer = null;

		if (StringUtil.isNullOrEmpty(this.customerIdTextField.getText())) {
			JOptionPane.showMessageDialog(window, ResourceHandler.getError("updatecomplaintform.error1"), "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//should clear the previous result		
		TableModel emptyModel = new DefaultTableModel();
		complaintsTable.setModel(emptyModel);

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
				JOptionPane.showMessageDialog(window, ResourceHandler.getError("updatecomplaintform.error2"), "Error Message", JOptionPane.ERROR_MESSAGE);				
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
				JOptionPane.showMessageDialog(window, ResourceHandler.getError("updatecomplaintform.error3"), "Error Message", JOptionPane.ERROR_MESSAGE);
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
				BillingSystemLogger.logSevere("Exce" + evt);
				JOptionPane.showMessageDialog(window, e.getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (Exception e) {
				BillingSystemLogger.logInfo("Inside getComplaintsButtonActionPerformed, event=" + evt);
				JOptionPane.showMessageDialog(window, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		 if (complaints != null && complaints.size() > 0) {
			// populate the table
			TableModel complaintTableModel = new DefaultTableModel(
					getTableData(complaints), getColumnNames());
			complaintsTable.setModel(complaintTableModel);
		 } else {
				JOptionPane.showMessageDialog(window, ResourceHandler.getError("updatecomplaintform.error4"), "Warning Message", JOptionPane.WARNING_MESSAGE);
				// populate the table
				TableModel complaintTableModel = new DefaultTableModel(
						getTableData(new ArrayList<CustComplaint>()), getColumnNames());
				complaintsTable.setModel(complaintTableModel);
				return;
		 }
	}

	/**
	 * This is the event listener method to be called upon clicking the update complaint button.
	 * @param arg0 ActionEvent
	 */
	private void updateButtonActionPerformed(ActionEvent arg0) {
		BillingSystemLogger.logInfo("Inside updateButtonActionPerformed(), arg0=" + arg0);
		String customerIdType = null;
		long returnValue = 0;
		
		if (StringUtil.isNullOrEmpty(complaintTextArea.getText())) {
			JOptionPane.showMessageDialog(window, ResourceHandler.getError("logcomplaintform.error2"), "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}

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
			JOptionPane.showMessageDialog(window, ResourceHandler.getMessages("updatecomplaintform.success1"), "Success Message", JOptionPane.INFORMATION_MESSAGE);
			
			// reload the complaints list
			getComplaintsButtonActionPerformed(null);
		} else {
			JOptionPane.showMessageDialog(window, ResourceHandler.getError("updatecomplaintform.error5"), "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This is the event listener method to be called upon clicking the cancel button.
	 * @param arg0 ActionEvent
	 */
	private void cancelButtonActionPerformed(ActionEvent arg0) {
		BillingSystemLogger.logInfo("Inside cancelButtonActionPerformed(), event=" + arg0);
		this.setVisible(false);
	}

	/**
	 * This method returns the column names.
	 * @return String[]
	 */
	private String[] getColumnNames() {
		String[] cols = { ResourceHandler.getLabel("updatecomplaintform.col1"),
				ResourceHandler.getLabel("updatecomplaintform.col2"),
				ResourceHandler.getLabel("updatecomplaintform.col3"),
				ResourceHandler.getLabel("updatecomplaintform.col4") };
		return cols;
	}

	/**
	 * This method returns the table data from the list of complaints.
	 * @param complaints List<CustComplaint>
	 * @return String[][]
	 */
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
