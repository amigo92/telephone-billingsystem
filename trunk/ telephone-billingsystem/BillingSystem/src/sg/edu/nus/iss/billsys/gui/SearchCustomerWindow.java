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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchCustomerWindow {

	private static final Insets insets = new Insets(0,0,0,0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCustomerWindow window = new SearchCustomerWindow();
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
	public SearchCustomerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final JFrame frame = new JFrame("GridBagLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridBagLayout());
		
		//Search By
		JLabel SearchLabel=new JLabel("Search By");		
		GridBagConstraints gbc = new GridBagConstraints(0, 0,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.getContentPane().add(SearchLabel, gbc);
		
		//Search By
		JRadioButton customerNoLabel=new JRadioButton("NRC");		
		gbc = new GridBagConstraints(1, 1,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.getContentPane().add(customerNoLabel, gbc);
		
		JRadioButton customerNameLabel=new JRadioButton("Customer Name");		
		gbc = new GridBagConstraints(2, 1,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.getContentPane().add(customerNameLabel, gbc);
		//-----------------------
		
		//Customer No/Name
		JLabel customerNoNameLabel=new JLabel("Customer No / Customer Name");		
		gbc = new GridBagConstraints(0, 2,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.getContentPane().add(customerNoNameLabel, gbc);
		
		JTextField customerNoNameText = new JTextField();
		gbc = new GridBagConstraints(1, 2,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.getContentPane().add(customerNoNameText, gbc);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		gbc = new GridBagConstraints(2, 2,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.getContentPane().add(searchButton, gbc);
		//------------------------------
		
		//Result Grid
		Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
		        { "Row2-Column1", "Row2-Column2", "Row2-Column3" },
		        { "Row2-Column1", "Row2-Column2", "Row2-Column3" },
		        { "Row2-Column1", "Row2-Column2", "Row2-Column3" },
		        { "Row2-Column1", "Row2-Column2", "Row2-Column3" },
		        { "Row2-Column1", "Row2-Column2", "Row2-Column3" },
		        { "Row2-Column1", "Row2-Column2", "Row2-Column3" }};
		 Object columnNames[] = { "Customer ID", "Customer Name", "NRC" };
		 JTable table = new JTable(rowData, columnNames);
 	    JScrollPane scrollPane = new JScrollPane(table);
 	   gbc = new GridBagConstraints(0, 4,
 				3, 6, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
 	   
 	   frame.getContentPane().add(scrollPane, gbc);
 	  
		//Buttons
		JButton submitButton=new JButton("Submit");		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		gbc = new GridBagConstraints(1, 10,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.getContentPane().add(submitButton, gbc);
		
		JButton cancelButton = new JButton("Cancel");
		gbc = new GridBagConstraints(2, 10,
		1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
		frame.getContentPane().add(cancelButton, gbc);
		//------------------------------
		
		frame.setSize(500, 300);
		frame.setVisible(true);
		
	}
}
