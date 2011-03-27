package sg.edu.nus.iss.billsys.gui;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
* @author Win Kyi Tin 
*/

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.mgr.AccountMgr;
import sg.edu.nus.iss.billsys.vo.Customer;

public class ViewCustomerDetails extends javax.swing.JPanel {


	private BillingWindow  window;
	private JLabel jLabel4;
	private JLabel CustAccontNoLabel;
	private JPanel SubscriptionPanel;
	private JLabel SubscriptionTitleLabel;
	private JLabel accountNoLabel;
	private JSeparator jSeparator2;
	private JSeparator jSeparator1;
	private JLabel accountTitleLabel;
	private JLabel CustInterestLabel;
	private JLabel CustTeleLabel;
	private JLabel Custaddress3Label;
	private JLabel Custaddress2Label;
	private JLabel Custaddress1Label;
	private JLabel CustNameLabel;
	private JLabel address3Label;
	private JLabel ContactTelLabel;
	private JLabel address2Label;
	private JLabel address1Label;
	private JLabel nameLabel;
	private JButton SearchCustButton;
	private JTextField nricText;
	private JLabel nricLabel;
	private JPanel ViewCustPanelCenter;
	private JLabel ViewCustTitleLabel;
	private JPanel ViewCustTitlePanel;
	private static final long serialVersionUID = 1L;
	
	private String strCustomerID;
	Customer cust= new Customer();
	AccountMgr accountMgr= new AccountMgr();
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new ViewCustomerDetails());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public ViewCustomerDetails() {
		super();
		initGUI();
	}
	
	public ViewCustomerDetails(String strCustomerID) {
		super();
		initGUI();
	}
	
	public ViewCustomerDetails(BillingWindow window,String strCustomerID) {
		super();
		initGUI();
		this.strCustomerID=strCustomerID;
		this.GetCustomerDetails();
		ObjectsToControls();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(655, 829));
			{
				ViewCustTitlePanel = new JPanel();
				this.add(ViewCustTitlePanel, BorderLayout.NORTH);
				{
					ViewCustTitleLabel = new JLabel();
					ViewCustTitlePanel.add(ViewCustTitleLabel);
					ViewCustTitleLabel.setText("Customer Details");
					ViewCustTitleLabel.setPreferredSize(new java.awt.Dimension(372, 16));
					ViewCustTitleLabel.setFont(new java.awt.Font("Segoe UI",1,14));
				}
			}
			{
				ViewCustPanelCenter = new JPanel();
				this.add(ViewCustPanelCenter, BorderLayout.CENTER);
				ViewCustPanelCenter.setLayout(null);
				ViewCustPanelCenter.setPreferredSize(new java.awt.Dimension(655, 763));
				{
					nricLabel = new JLabel();
					ViewCustPanelCenter.add(nricLabel);
					nricLabel.setText("Customer NRIC :");
					nricLabel.setBounds(12, 19, 146, 16);
				}
				{
					nricText = new JTextField();
					ViewCustPanelCenter.add(nricText);
					nricText.setBounds(170, 16, 192, 23);
				}
				{
					SearchCustButton = new JButton();
					ViewCustPanelCenter.add(SearchCustButton);
					SearchCustButton.setText("Search Customer");
					SearchCustButton.setBounds(374, 16, 162, 23);
					SearchCustButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							SearchCustButtonActionPerformed(evt);
						}
					});
				}
				{
					nameLabel = new JLabel();
					ViewCustPanelCenter.add(nameLabel);
					nameLabel.setText("Customer Name :");
					nameLabel.setBounds(12, 54, 140, 16);
				}
				{
					address1Label = new JLabel();
					ViewCustPanelCenter.add(address1Label);
					address1Label.setText("Address 1  :");
					address1Label.setBounds(12, 85, 140, 40);
				}
				{
					address2Label = new JLabel();
					ViewCustPanelCenter.add(address2Label);
					address2Label.setText("Address 2  :");
					address2Label.setBounds(12, 137, 152, 40);
				}
				{
					jLabel4 = new JLabel();
					ViewCustPanelCenter.add(jLabel4);
					jLabel4.setText("Interest :");
					jLabel4.setBounds(12, 283, 60, 40);
				}
				{
					ContactTelLabel = new JLabel();
					ViewCustPanelCenter.add(ContactTelLabel);
					ContactTelLabel.setText("Contact Telephone :");
					ContactTelLabel.setBounds(12, 236, 143, 42);
				}
				{
					address3Label = new JLabel();
					ViewCustPanelCenter.add(address3Label);
					address3Label.setText("Address 3  :");
					address3Label.setBounds(12, 196, 152, 40);
				}
				{
					CustNameLabel = new JLabel();
					ViewCustPanelCenter.add(CustNameLabel);
					CustNameLabel.setBounds(170, 59, 216, 16);
				}
				{
					Custaddress1Label = new JLabel();
					ViewCustPanelCenter.add(Custaddress1Label);
					Custaddress1Label.setBounds(170, 87, 192, 46);
				}
				{
					Custaddress2Label = new JLabel();
					ViewCustPanelCenter.add(Custaddress2Label);
					Custaddress2Label.setBounds(170, 149, 238, 41);
				}
				{
					Custaddress3Label = new JLabel();
					ViewCustPanelCenter.add(Custaddress3Label);
					Custaddress3Label.setBounds(170, 208, 216, 45);
				}
				{
					CustTeleLabel = new JLabel();
					ViewCustPanelCenter.add(CustTeleLabel);
					CustTeleLabel.setBounds(161, 250, 180, 16);
				}
				{
					CustInterestLabel = new JLabel();
					ViewCustPanelCenter.add(CustInterestLabel);
					CustInterestLabel.setBounds(164, 293, 175, 16);
				}
				{
					accountTitleLabel = new JLabel();
					ViewCustPanelCenter.add(accountTitleLabel);
					accountTitleLabel.setText("Account Information");
					accountTitleLabel.setBounds(12, 329, 230, 16);
					accountTitleLabel.setFont(new java.awt.Font("Segoe UI",1,14));
				}
				{
					jSeparator1 = new JSeparator();
					ViewCustPanelCenter.add(jSeparator1);
					jSeparator1.setBounds(3, 319, 650, 10);
				}
				{
					jSeparator2 = new JSeparator();
					ViewCustPanelCenter.add(jSeparator2);
					jSeparator2.setBounds(0, 395, 655, 10);
				}
				{
					accountNoLabel = new JLabel();
					ViewCustPanelCenter.add(accountNoLabel);
					accountNoLabel.setText("Account No :");
					accountNoLabel.setBounds(12, 367, 140, 16);
				}
				{
					CustAccontNoLabel = new JLabel();
					ViewCustPanelCenter.add(CustAccontNoLabel);
					CustAccontNoLabel.setBounds(164, 367, 272, 10);
				}
				{
					SubscriptionTitleLabel = new JLabel();
					ViewCustPanelCenter.add(SubscriptionTitleLabel);
					SubscriptionTitleLabel.setText("Subscription Information");
					SubscriptionTitleLabel.setBounds(3, 411, 285, 16);
					SubscriptionTitleLabel.setFont(new java.awt.Font("Segoe UI",1,14));
				}
				{
					SubscriptionPanel = new JPanel();
					ViewCustPanelCenter.add(SubscriptionPanel);
					SubscriptionPanel.setBounds(3, 433, 645, 304);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void SearchCustButtonActionPerformed(ActionEvent evt) {
		BillingSystem.updateContentPane(new SearchCustomer(window));
	}
	
	private void ObjectsToControls(){
		
		CustNameLabel.setText(cust.getName()) ;
		nricText.setText(cust.getNric()) ;
		Custaddress1Label.setText(cust.getAddress1()) ;
		Custaddress2Label.setText(cust.getAddress2()) ;
		Custaddress3Label.setText(cust.getAddress3()) ;
		CustTeleLabel.setText(cust.getContact_tel());
		CustInterestLabel.setText(cust.getInterest());
		accountNoLabel.setText(cust.getAcct().getAcctNo()) ;
		
	
	}
	
	private void GetCustomerDetails(){
		//cust= accountMgr.getCustomerDetailsByCustomerId(strCustomerID);
		
		
	}
}
