package sg.edu.nus.iss.billsys.gui;

/**
 * 
 * @author Win Kyi Tin
 *
 */

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLayeredPane;

import sg.edu.nus.iss.billsys.dao.CustomerDao;
import sg.edu.nus.iss.billsys.mgr.AccountMgr;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerRegistration {

	private JFrame frame;
	private AccountMgr     Accountmanager;
	private CustomerDao Customer;
	
	private static final Insets insets = new Insets(0,0,0,0);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRegistration window = new CustomerRegistration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerRegistration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		final JFrame frmCustomerRegistration = new JFrame("GridBagLayout");
		frmCustomerRegistration.setTitle("Customer Registration");
		frmCustomerRegistration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCustomerRegistration.getContentPane().setLayout(new GridBagLayout());
		
		//Customer Name
		JLabel customerNameLabel=new JLabel("Customer Name *");		
		GridBagConstraints gbc = new GridBagConstraints(0, 0,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0);
		frmCustomerRegistration.getContentPane().add(customerNameLabel, gbc);
		
		JTextField customerNameText = new JTextField();
		gbc = new GridBagConstraints(1, 0,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
		frmCustomerRegistration.getContentPane().add(customerNameText, gbc);
		//------------------------------
		
		//NRC
		JLabel nrcLabel=new JLabel("NRC *");		
		gbc = new GridBagConstraints(0, 1,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0);
		frmCustomerRegistration.getContentPane().add(nrcLabel, gbc);
		
		JTextField nrcText = new JTextField();
		gbc = new GridBagConstraints(1, 1,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
		frmCustomerRegistration.getContentPane().add(nrcText, gbc);
		//------------------------------
		
		//DOB
		JLabel dobLabel=new JLabel("Date of Birth *");		
		gbc = new GridBagConstraints(0, 2,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0);
		frmCustomerRegistration.getContentPane().add(dobLabel, gbc);
		
		JTextField dobText = new JTextField();
		gbc = new GridBagConstraints(1, 2,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
		frmCustomerRegistration.getContentPane().add(dobText, gbc);
		//------------------------------
		
		//Gender
		JLabel genderLabel=new JLabel("Gender *");		
		gbc = new GridBagConstraints(0, 3,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0);
		frmCustomerRegistration.getContentPane().add(genderLabel, gbc);
		
		JRadioButton genderMaleLabel=new JRadioButton("Male");		
		gbc = new GridBagConstraints(1, 3,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0);
		frmCustomerRegistration.getContentPane().add(genderMaleLabel, gbc);
		
		JRadioButton genderFemaleLabel=new JRadioButton("Female");		
		gbc = new GridBagConstraints(2, 3,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
		frmCustomerRegistration.getContentPane().add(genderFemaleLabel, gbc);
		//------------------------------
		
		//Address1
		JLabel address1Label=new JLabel("Address 1 *");		
		gbc = new GridBagConstraints(0, 4,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0);
		frmCustomerRegistration.getContentPane().add(address1Label, gbc);
		
		JTextField address1Text = new JTextField();
		gbc = new GridBagConstraints(1, 4,
		2, 2, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
		frmCustomerRegistration.getContentPane().add(address1Text, gbc);
		//------------------------------
		
		//Address 2
		JLabel address2Label=new JLabel("Address 2");		
		gbc = new GridBagConstraints(0, 6,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0);
		frmCustomerRegistration.getContentPane().add(address2Label, gbc);
		
		JTextField address2Text = new JTextField();
		gbc = new GridBagConstraints(1, 6,
		2, 2, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
		frmCustomerRegistration.getContentPane().add(address2Text, gbc);
		//------------------------------
		
		//Address 3
		JLabel address3Label=new JLabel("Address 3");		
		gbc = new GridBagConstraints(0, 8,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0);
		frmCustomerRegistration.getContentPane().add(address3Label, gbc);
		
		JTextField address3Text = new JTextField();
		gbc = new GridBagConstraints(1, 8,
		2, 2, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
		frmCustomerRegistration.getContentPane().add(address3Text, gbc);
		//------------------------------
		
		//Buttons
		JButton submitButton=new JButton("Submit");		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			//	Accountmanager.create(Customer) ;
			}
		});
		gbc = new GridBagConstraints(1, 11,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0);
		frmCustomerRegistration.getContentPane().add(submitButton, gbc);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 System.exit(0);
			}
		});
		gbc = new GridBagConstraints(2, 11,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 5, 0), 0, 0);
		frmCustomerRegistration.getContentPane().add(cancelButton, gbc);
		//------------------------------
		
		frmCustomerRegistration.setSize(300, 336);
		frmCustomerRegistration.setVisible(true);
		
		
	}
	
}
