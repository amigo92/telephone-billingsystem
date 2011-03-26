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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewCustomerDetail {

	private static final Insets insets = new Insets(0,0,0,0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCustomerDetail window = new ViewCustomerDetail();
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
	public ViewCustomerDetail() {
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
		JLabel customerNameLabel=new JLabel("Customer Name *");		
		GridBagConstraints gbc = new GridBagConstraints(0, 0,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(customerNameLabel, gbc);
		
		JLabel customerNameText = new JLabel();
		gbc = new GridBagConstraints(1, 0,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(customerNameText, gbc);
		
		JButton searchButton = new JButton("Search");
		gbc = new GridBagConstraints(2, 0,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(searchButton, gbc);
		
		
		//NRC
		JLabel nrcLabel=new JLabel("NRC *");		
		gbc = new GridBagConstraints(0, 1,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(nrcLabel, gbc);
		
		JLabel nrcText = new JLabel();
		gbc = new GridBagConstraints(1, 1,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(nrcText, gbc);
		//------------------------------
		
		//NRC
		JLabel dobLabel=new JLabel("Date of Birth *");		
		gbc = new GridBagConstraints(0, 2,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(dobLabel, gbc);
		
		JLabel dobText = new JLabel();
		gbc = new GridBagConstraints(1, 2,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(dobText, gbc);
		//------------------------------
		
		//NRC
		JLabel genderLabel=new JLabel("Gender *");		
		gbc = new GridBagConstraints(0, 3,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(genderLabel, gbc);
		
		JRadioButton genderMaleLabel=new JRadioButton("Male");		
		gbc = new GridBagConstraints(1, 3,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(genderMaleLabel, gbc);
		
		JRadioButton genderFemaleLabel=new JRadioButton("Female");		
		gbc = new GridBagConstraints(2, 3,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(genderFemaleLabel, gbc);
		//------------------------------
		
		//NRC
		JLabel address1Label=new JLabel("Address 1 *");		
		gbc = new GridBagConstraints(0, 4,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(address1Label, gbc);
		
		JLabel address1Text = new JLabel();
		gbc = new GridBagConstraints(1, 4,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(address1Text, gbc);
		//------------------------------
		
		//Address 2
		JLabel address2Label=new JLabel("Address 2");		
		gbc = new GridBagConstraints(0, 5,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(address2Label, gbc);
		
		JLabel address2Text = new JLabel();
		gbc = new GridBagConstraints(1, 5,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(address2Text, gbc);
		//------------------------------
		
		//Address 3
		JLabel address3Label=new JLabel("Address 3");		
		gbc = new GridBagConstraints(0, 6,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(address3Label, gbc);
		
		JLabel address3Text = new JLabel();
		gbc = new GridBagConstraints(1, 6,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(address3Text, gbc);
		//------------------------------
		
		//Account Information Details------------------------------
		JLabel AccInfoLabel = new JLabel("Account Information Detail");
		gbc = new GridBagConstraints(0, 7,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(AccInfoLabel, gbc);
		
		//Account No
		JLabel accountNoLabel=new JLabel("Account No");		
		gbc = new GridBagConstraints(0, 8,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(accountNoLabel, gbc);
		
		JLabel accountNoText = new JLabel();
		gbc = new GridBagConstraints(1, 8,
		2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(accountNoText, gbc);
		//------------------------------
		
		//Subscription Information Details------------------------------
		JLabel subInfoLabel = new JLabel("Subscription Information Detail");
		gbc = new GridBagConstraints(0, 9,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(subInfoLabel, gbc);
		
		//Grid
		Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
		        { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
		 Object columnNames[] = { "Column One", "Column Two", "Column Three" };
		 JTable table = new JTable(rowData, columnNames);
 	    JScrollPane scrollPane = new JScrollPane(table);
 	   gbc = new GridBagConstraints(0, 11,
 				3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
 	   
 	  frame.add(scrollPane, gbc);
 	  
 	   
		
		
		//Buttons
		JButton submitButton=new JButton("OK");		
		gbc = new GridBagConstraints(2, 13,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.add(submitButton, gbc);
		
		//------------------------------
		
		
		frame.setSize(300, 500);
		frame.setVisible(true);
	}

}
