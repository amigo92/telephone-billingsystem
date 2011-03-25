package sg.edu.nus.iss.billsys.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneLayout;

import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.util.NumberUtil;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.CustComplaint;



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
public class UpdateComplaintForm extends javax.swing.JFrame {
	private JButton cancelButton;
	private JTextArea complaintTextArea;
	private JLabel complaintLabel;
	private JComboBox statusComboBox;
	private JLabel statusLabel;
	private JTextField complaintIdTextField;
	private JLabel complaintIdLabel;
	private JTable complaintTable;
	private JSeparator jSeparator1;
	private JLabel GetComplaintsLabel;
	private JTextField customerIdTextField;
	private JRadioButton nricRadioButton;
	private JRadioButton accountNoRadioButton;
	private JScrollPane updateComplaintScrollPane;
	private JButton getComplaintsButton;
	private JLabel errorMessageLabel;
	private JButton logButton;
	private JLabel customerIdLabel;
	private ButtonGroup customerIdButtonGroup;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UpdateComplaintForm inst = new UpdateComplaintForm();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setTitle("Update Complaint");
			}
		});
	}
	
	public UpdateComplaintForm() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			customerIdButtonGroup = new ButtonGroup();
			{
				errorMessageLabel = new JLabel();
			}
			{
				complaintIdTextField = new JTextField();
				complaintIdTextField.setEnabled(false);
			}
			{
				statusLabel = new JLabel();
				statusLabel.setText("Status:");
			}
			{
				ComboBoxModel statusComboBoxModel = 
					new DefaultComboBoxModel(GUIConstants.COMPLAINT_STATUS_VALUES);
				statusComboBox = new JComboBox();
				statusComboBox.setModel(statusComboBoxModel);
			}
			{
				complaintLabel = new JLabel();
				complaintLabel.setText("Complaint: ");
			}
			{
				complaintTextArea = new JTextArea();
				complaintTextArea.setEnabled(false);
			}
			{
				complaintIdLabel = new JLabel();
				complaintIdLabel.setText("Complaint Id: ");
			}
			{
//				
				{
					TableModel complaintTableModel = new DefaultTableModel(
							new String[3][4],
							getColumnNames());
					complaintTable = new JTable();
					complaintTable.setPreferredSize(new java.awt.Dimension(485, 102));
					complaintTable.setModel(complaintTableModel);
					JTableHeader header = complaintTable.getTableHeader();
					header.setBackground(Color.yellow);
					updateComplaintScrollPane = new JScrollPane(complaintTable);
					complaintTable.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
			            public void valueChanged(ListSelectionEvent event) {
			                int viewRow = complaintTable.getSelectedRow();
			                if (viewRow >= 0) {
			                    System.out.println("[" + complaintTable.getModel().getValueAt(viewRow, 0)
			                    		+ ";" + complaintTable.getModel().getValueAt(viewRow, 1)
			                    		+ ";" + complaintTable.getModel().getValueAt(viewRow, 2)
			                    		+ ";" + complaintTable.getModel().getValueAt(viewRow, 3) + "]");
			                    complaintIdTextField.setText(complaintTable.getModel().getValueAt(viewRow, 0).toString());
			                    statusComboBox.setSelectedItem(complaintTable.getModel().getValueAt(viewRow, 3).toString());
			                    complaintTextArea.setText(complaintTable.getModel().getValueAt(viewRow, 2).toString());
			                }
			            }
			        });
				}
			}
			{
				getComplaintsButton = new JButton();
				getComplaintsButton.setText("Get Complaints");
				getComplaintsButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						getComplaintsButtonMouseClicked(evt);
					}
				});
			}
			{
				jSeparator1 = new JSeparator();
			}
			{
				GetComplaintsLabel = new JLabel();
				GetComplaintsLabel.setText("Get Complaints");
				GetComplaintsLabel.setFont(new java.awt.Font("Tahoma",1,11));
			}
			{
				cancelButton = new JButton();
				cancelButton.setText("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						cancelButtonMouseClicked(evt);
					}
				});
			}
			{
				logButton = new JButton();
				logButton.setText("Update Log");
				logButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						logButtonMouseClicked(evt);
					}
				});
			}
			{
				customerIdLabel = new JLabel();
				customerIdLabel.setText("Customer Id: ");
			}
			{
				accountNoRadioButton = new JRadioButton();
				accountNoRadioButton.setText("Account #");
				accountNoRadioButton.setActionCommand("accountNo");
				customerIdButtonGroup.add(accountNoRadioButton);
			}
			{
				nricRadioButton = new JRadioButton();
				nricRadioButton.setText("NRIC");
				nricRadioButton.setSelected(true);
				nricRadioButton.setActionCommand("nric");
				customerIdButtonGroup.add(nricRadioButton);
			}
			{
				customerIdTextField = new JTextField();
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(GetComplaintsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(customerIdTextField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(customerIdLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(accountNoRadioButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(nricRadioButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(getComplaintsButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(36)
				.addComponent(updateComplaintScrollPane, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				        .addComponent(complaintIdTextField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addComponent(statusLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addComponent(statusComboBox, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(7)
				        .addComponent(complaintIdLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(complaintLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(complaintTextArea, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
				.addGap(17)
				.addComponent(errorMessageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(logButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(24, 24));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(thisLayout.createSequentialGroup()
				                .addGroup(thisLayout.createParallelGroup()
				                    .addComponent(GetComplaintsLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(complaintIdLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
				                        .addGap(22))
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(customerIdLabel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
				                        .addGap(28))
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(complaintLabel, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
				                        .addGap(44)))
				                .addGroup(thisLayout.createParallelGroup()
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(complaintIdTextField, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
				                        .addGap(34))
				                    .addComponent(accountNoRadioButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(21)
				                .addComponent(logButton, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
				                .addGap(34)))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(statusLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
				            .addComponent(nricRadioButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(statusComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 166, Short.MAX_VALUE))
				            .addGroup(thisLayout.createSequentialGroup()
				                .addGap(28)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addGap(0, 0, Short.MAX_VALUE)
				                        .addComponent(customerIdTextField, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
				                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				                        .addComponent(getComplaintsButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addGap(0, 28, Short.MAX_VALUE)
				                        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
				                        .addGap(31))))))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 488, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 22, Short.MAX_VALUE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(updateComplaintScrollPane, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(complaintTextArea, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(errorMessageLabel, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE)))
				.addContainerGap(20, 20));
			pack();
			this.setSize(550, 459);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void getComplaintsButtonMouseClicked(MouseEvent evt) {
		System.out.println("getComplaintsButton.mouseClicked, event="+evt);
		List<CustComplaint> complaints = null;
		
		if (StringUtil.isNullOrEmpty(customerIdTextField.getText())) {
			errorMessageLabel.setText("Invalid/ Empty customer Id!");
			errorMessageLabel.setForeground(Color.RED);
			return;
		}
		
		String customerIdType = null;
		
		if (accountNoRadioButton.isSelected()) {
			customerIdType = accountNoRadioButton.getActionCommand();
		}
		if (nricRadioButton.isSelected()) {
			customerIdType = nricRadioButton.getActionCommand();
		}
		
		
		if ("accountNo".equalsIgnoreCase(customerIdType)) {
			complaints = MgrFactory.getComplaintMgr().getComplaintByAccount(customerIdTextField.getText().trim());
		} else if ("nric".equalsIgnoreCase(customerIdType)){
			complaints = MgrFactory.getComplaintMgr().getComplaintByCustomerId(customerIdTextField.getText().trim());
		}
		
//		if (complaints != null && complaints.size() > 0) {
			// populate the table
			TableModel complaintTableModel = new DefaultTableModel(
					getTableTestData(complaints), getColumnNames());
			complaintTable.setModel(complaintTableModel);
//		}
	}

	private String[] getColumnNames() {
		String[] cols = {"Id", "Date/ Time", "Complaint", "Status"};
		return cols;
	}

	private String[][] getTableData(List<CustComplaint> complaints) {
		String[][] data = new String[complaints.size()][4];
		int rowNum = 0;
		for (CustComplaint complaint : complaints) {
			data[rowNum][0] = complaint.getComplaint_id();
			data[rowNum][1] = complaint.getComplaintDate().toString();
			data[rowNum][2] = complaint.getComplaint_Details();
			data[rowNum][3] = complaint.getStatus();
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
	
	private void logButtonMouseClicked(MouseEvent evt) {
		System.out.println("logButton.mouseClicked, event="+evt);
		
		if (!NumberUtil.isLong(complaintIdTextField.getText().trim())) {
			errorMessageLabel.setText("Invalid Complaint Id!");
			errorMessageLabel.setForeground(Color.RED);
			return;
		}
		
		int returnValue = MgrFactory.getComplaintMgr().updateComplaint(Long.parseLong(complaintIdTextField.getText().trim()), statusComboBox.getSelectedItem().toString().trim());
		
		if (returnValue > 0) {
			errorMessageLabel.setText("Update successful.");
			errorMessageLabel.setForeground(Color.BLUE);
		} else {
			errorMessageLabel.setText("Internal error occurred during updation!");
			errorMessageLabel.setForeground(Color.RED);

		}
	}
	
	private void cancelButtonMouseClicked(MouseEvent evt) {
		System.out.println("cancelButton.mouseClicked, event="+evt);
		this.setVisible(false);
	}
}
