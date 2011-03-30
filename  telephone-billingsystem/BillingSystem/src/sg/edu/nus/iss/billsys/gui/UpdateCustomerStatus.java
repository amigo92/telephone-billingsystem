package sg.edu.nus.iss.billsys.gui;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.AccountMgr;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Customer;
import java.awt.Font;

/**
* @author Win Kyi Tin 
*/
public class UpdateCustomerStatus extends javax.swing.JPanel {
	private JPanel CustStatusTop;
	private JPanel UpdateCustStatusCenter;
	private JTextField nrcText;
	private JLabel customerNameLabel;
	private JRadioButton rdDeactivitation;
	private JLabel custNameLabel;
	private JButton cancelButton;
	private JButton submitButton;
	private JSeparator jSeparator1;
	private JLabel statusLabel;
	private JRadioButton rdActivitation;
	private JButton searchButton;
	private JLabel custNricLabel;
	private JLabel updateCustStatusLabel;
	private ButtonGroup bgroup;
	private Customer cust;
	private String errorMsg=null;
	private String strCustomerID;
	private BillingWindow  window;
	private AccountMgr   accountMgr;
	private static final long serialVersionUID = 1L;
	private JLabel errorMsgNRICLabel;
	private JLabel errorMsgSearchLabel;

		
	


		
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new UpdateCustomerStatus());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public UpdateCustomerStatus(BillingWindow window) {
		this.window = window;
		initControl();
	}
	
	public UpdateCustomerStatus() {
		super();
		initControl();
	}
	
	private void initControl() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(508, 255));
			{
				CustStatusTop = new JPanel();
				BoxLayout CustStatusTopLayout = new BoxLayout(CustStatusTop, javax.swing.BoxLayout.X_AXIS);
				this.add(CustStatusTop, BorderLayout.NORTH);
				CustStatusTop.setLayout(CustStatusTopLayout);
				{
					updateCustStatusLabel = new JLabel();
					CustStatusTop.add(updateCustStatusLabel);
					updateCustStatusLabel.setText("Update Customer Status");
					updateCustStatusLabel.setPreferredSize(new java.awt.Dimension(424, 20));
					updateCustStatusLabel.setFont(new java.awt.Font("Segoe UI",1,14));
				}
			}
			{
				UpdateCustStatusCenter = new JPanel();
				this.add(UpdateCustStatusCenter, BorderLayout.CENTER);
				UpdateCustStatusCenter.setLayout(null);
				UpdateCustStatusCenter.setPreferredSize(new java.awt.Dimension(508, 321));
				{
					nrcText = new JTextField();
					UpdateCustStatusCenter.add(nrcText);
					nrcText.setBounds(192, 31, 170, 23);
				}
				{
					custNricLabel = new JLabel();
					UpdateCustStatusCenter.add(custNricLabel);
					custNricLabel.setText("Customer NRIC :");
					custNricLabel.setBounds(27, 34, 153, 16);
				}
				{
					searchButton = new JButton();
					UpdateCustStatusCenter.add(searchButton);
					searchButton.setText("Search");
					searchButton.setBounds(374, 31, 78, 23);
					searchButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							searchButtonActionPerformed(evt);
						}
					});
				}
				{
					rdActivitation = new JRadioButton();
					UpdateCustStatusCenter.add(rdActivitation);
					rdActivitation.setText("Activiation");
					rdActivitation.setBounds(192, 117, 133, 20);
					//rdActivitation.setSelected(true);
				}
				{
					rdDeactivitation = new JRadioButton();
					UpdateCustStatusCenter.add(rdDeactivitation);
					rdDeactivitation.setText("Deactivitation");
					rdDeactivitation.setBounds(330, 117, 122, 20);
					//rdDeactivitation.setSelected(false);
				}
				{
					bgroup = new ButtonGroup();
					bgroup.add(rdActivitation);
					bgroup.add(rdDeactivitation);				
				}
				
				{
					statusLabel = new JLabel();
					UpdateCustStatusCenter.add(statusLabel);
					statusLabel.setText("Status :");
					statusLabel.setBounds(27, 119, 139, 16);
				}
				{
					jSeparator1 = new JSeparator();
					UpdateCustStatusCenter.add(jSeparator1);
					jSeparator1.setBounds(10, 171, 507, 10);
				}
				{
					submitButton = new JButton();
					UpdateCustStatusCenter.add(submitButton);
					submitButton.setText("Submit");
					submitButton.setBounds(290, 194, 79, 23);
					submitButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try {
						
								submitButtonActionPerformed(evt);
							
							} catch (Exception e) {
							    // Print out the exception that occurred
								errorMsg=new BillingSystemException(e).getMessagebyException();
							}
						}
					});
				}
				{
					cancelButton = new JButton();
					UpdateCustStatusCenter.add(cancelButton);
					cancelButton.setText("Cancel");
					cancelButton.setBounds(380, 194, 77, 23);
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							cancelButtonActionPerformed(evt);
						}
					});
				}
				{
					custNameLabel = new JLabel();
					UpdateCustStatusCenter.add(custNameLabel);
					custNameLabel.setText("Customer Name :");
					custNameLabel.setBounds(27, 82, 139, 16);
				}
				{
					customerNameLabel = new JLabel();
					UpdateCustStatusCenter.add(customerNameLabel);
					customerNameLabel.setBounds(192, 73, 265, 25);
				}
				{
					errorMsgNRICLabel = new JLabel("*Please enter NRIC.");
					errorMsgNRICLabel.setBounds(27, 146, 153, 14);


					errorMsgNRICLabel.setOpaque(true);
					errorMsgNRICLabel.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgNRICLabel.setVisible(false);
					UpdateCustStatusCenter.add(errorMsgNRICLabel);
					
				}
				{
					errorMsgSearchLabel = new JLabel("*No match record found.");
					errorMsgSearchLabel.setBounds(27, 146, 205, 14);
					errorMsgSearchLabel.setOpaque(true);
					errorMsgSearchLabel.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgSearchLabel.setVisible(false);
					UpdateCustStatusCenter.add(errorMsgSearchLabel);
				}
			
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void cancelButtonActionPerformed(ActionEvent evt) {
		System.out.println("cancelButton.actionPerformed, event="+evt);
		//TODO add your code for cancelButton.actionPerformed
		this.setVisible(false);
	}
	
	private void submitButtonActionPerformed(ActionEvent evt) {
		try
		{
			clearErrorMsgData();
			
			if (validateControl()){	
					
					controlsToObject(cust);	
				
					System.out.println(cust.getAccIdByCust());
					Boolean bReturn=accountMgr.deleteCustomer(cust.getAccIdByCust());
				
					if (bReturn){
						JOptionPane.showMessageDialog(null,"Customer Status is updated." , "Billing System", 1);
					}
					
			}
			else{
				errorMsgNRICLabel.setVisible(true);
			}
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		
			
	}
	
	private void searchButtonActionPerformed(ActionEvent evt) {
			accountMgr= new AccountMgr();
			clearErrorMsgData();
			System.out.println("a");
			if (validateControl()){					
				
			cust= accountMgr.getCustomerDetailsById(nrcText.getText() );			
		
			if (cust!=null){
		
			if (cust.isDeleted()){
						rdActivitation.setSelected(false);
						rdDeactivitation.setSelected(true);
					}
					else
					{	
						rdDeactivitation.setSelected(false);
						rdActivitation.setSelected(true);
					}
			}
			else {
				
				errorMsgSearchLabel.setVisible(true);
			}
		}
			else {
				
				errorMsgNRICLabel.setVisible(true);
			}
	}

	private void controlsToObject(Customer cust){
	
		if (rdActivitation.isSelected()) {
			cust.setIsDeleted(false);
		}
		if (rdDeactivitation.isSelected()) {
			cust.setIsDeleted(true);
		}
	
	}
	
	private boolean validateControl(){
		boolean bReturn= true;
		if (StringUtil.isNullOrEmpty(this.nrcText.getText())){			
			errorMsgSearchLabel.setVisible(true);
			bReturn= false;
		}	
		return bReturn;
	}
	
	private void clearErrorMsgData(){
		errorMsgSearchLabel.setVisible(false);
		errorMsgNRICLabel.setVisible(false);		

	}
}
