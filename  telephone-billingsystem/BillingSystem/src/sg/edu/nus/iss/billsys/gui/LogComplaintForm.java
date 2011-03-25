package sg.edu.nus.iss.billsys.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.util.StringUtil;



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
public class LogComplaintForm extends javax.swing.JFrame {
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
	private JLabel customerIdLabel;
	private ButtonGroup customerIdButtonGroup;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LogComplaintForm inst = new LogComplaintForm();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setTitle("Log Complaint");
			}
		});
	}
	
	public LogComplaintForm() {
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
				complaintLabel = new JLabel();
				complaintLabel.setText("Complaint:");
			}
			{
				complaintTextPane = new JTextPane();
			}
			{
				errorMessageLabel = new JLabel();
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
				logButton.setText("Log");
				logButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						logButtonMouseClicked(evt);
					}
				});
			}
			{
				statusLabel = new JLabel();
				statusLabel.setText("Status:");
			}
			{
				ComboBoxModel stautsComboBoxModel = 
					new DefaultComboBoxModel(GUIConstants.COMPLAINT_STATUS_VALUES);
				statusComboBox = new JComboBox();
				statusComboBox.setModel(stautsComboBoxModel);
				statusComboBox.setEnabled(false);
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
				.addContainerGap(24, 24)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(customerIdTextField, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(customerIdLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(accountNoRadioButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(nricRadioButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(24)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(statusComboBox, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(7)
				        .addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(39)
				.addComponent(complaintLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(complaintTextPane, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
				.addGap(22)
				.addComponent(errorMessageLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
				.addGap(23)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(logButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(15, 15));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(customerIdLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
				                .addGap(14))
				            .addComponent(complaintLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(statusComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				                .addGap(10))
				            .addComponent(accountNoRadioButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(nricRadioButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				                .addGap(22)
				                .addComponent(customerIdTextField, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 0, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addPreferredGap(nricRadioButton, cancelButton, LayoutStyle.ComponentPlacement.INDENT)
				                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 26, Short.MAX_VALUE))))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(complaintTextPane, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(thisLayout.createSequentialGroup()
				                .addComponent(errorMessageLabel, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 0, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(0, 29, Short.MAX_VALUE)
				                .addComponent(logButton, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
				                .addGap(191)))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
				.addContainerGap(33, 33));
			pack();
			this.setSize(400, 383);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void logButtonMouseClicked(MouseEvent evt) {
		System.out.println("logButton.mouseClicked, event="+evt);
		String customerIdType = null;
		long returnValue = 0;
		
		if (StringUtil.isNullOrEmpty(customerIdTextField.getText())) {
			errorMessageLabel.setText("Invalid/ Empty Customer Id!");
			return;
		}
		
		if (StringUtil.isNullOrEmpty(complaintTextPane.getText())) {
			errorMessageLabel.setText("Invalid/ Empty Complaint!");
			return;
		}
		
		if (accountNoRadioButton.isSelected()) {
			customerIdType = accountNoRadioButton.getActionCommand();
		}
		if (nricRadioButton.isSelected()) {
			customerIdType = nricRadioButton.getActionCommand();
		}
		
		System.out.println("customerId:" + customerIdTextField.getText());
		System.out.println("Complaint:" + complaintTextPane.getText());
		System.out.println("customerIdType:" + customerIdType);
		System.out.println("Status:" + statusComboBox.getSelectedItem().toString());
		
		if ("nric".equalsIgnoreCase(customerIdType)) {
			returnValue = MgrFactory.getComplaintMgr().createComplaintByCustomerId(customerIdTextField.getText().trim(), complaintTextPane.getText().trim());
		} else if ("accountNo".equalsIgnoreCase(customerIdType)) {
			returnValue = MgrFactory.getComplaintMgr().createComplaintByAccount(customerIdTextField.getText().trim(), complaintTextPane.getText().trim());
		}

		// set error message
		if (returnValue > 0) {
			errorMessageLabel.setText("Successfully created the complaint.");
			errorMessageLabel.setForeground(Color.BLUE);
		} else {
			errorMessageLabel.setText("Internal error occurred during the complaint creation!.");
			errorMessageLabel.setForeground(Color.RED);
		}
	}

	private void cancelButtonMouseClicked(MouseEvent evt) {
		System.out.println("cancelButton.mouseClicked, event="+evt);
		this.setVisible(false);
	}
}
