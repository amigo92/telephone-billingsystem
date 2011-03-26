package sg.edu.nus.iss.billsys.gui;

/**
 * 
 * @author Win Kyi Tin
 *
 */

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CustomerStatusWindow {

	private static final Insets insets = new Insets(0,0,0,0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerStatusWindow window = new CustomerStatusWindow();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerStatusWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final JFrame frame = new JFrame("GridBagLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		
		//Customer Name
		JLabel customerNameLabel=new JLabel("Customer Name");		
		GridBagConstraints gbc = new GridBagConstraints(0, 0,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(customerNameLabel, gbc);
		
		JTextField customerNameText = new JTextField();
		gbc = new GridBagConstraints(1, 0,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(customerNameText, gbc);
		//------------------------------
		
		
		//Status
		JLabel genderLabel=new JLabel("Status");		
		gbc = new GridBagConstraints(0, 1,
				1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
				frame.add(genderLabel, gbc);
		
		JRadioButton genderRegisterLabel=new JRadioButton("Register");		
		gbc = new GridBagConstraints(1, 1,
				1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
				frame.add(genderRegisterLabel, gbc);
		
		
		JRadioButton genderDregisterLabel=new JRadioButton("De-Register");		
		gbc = new GridBagConstraints(2, 1,
				3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
				frame.add(genderDregisterLabel, gbc);
		//-----------------------------
		
		//Buttons
		JButton submitButton=new JButton("Submit");		
		gbc = new GridBagConstraints(1, 2,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(submitButton, gbc);
		
		JButton cancelButton = new JButton("Cancel");
		gbc = new GridBagConstraints(2, 2,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(cancelButton, gbc);
		//------------------------------
		
		frame.setSize(400, 150);
		frame.setVisible(true);
	}

}
