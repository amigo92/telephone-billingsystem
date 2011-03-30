package sg.edu.nus.iss.billsys.gui;
import java.awt.BorderLayout;
import java.awt.Container;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

/**
* @author Win Kyi Tin 
*/

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.tools.*;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.mgr.AccountMgr;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

public class ViewCustomerDetails extends javax.swing.JPanel {


	private BillingWindow  window;
	private JLabel jLabel4;
	private JLabel CustAccontNoLabel;
	private JLabel accountNoLabel;
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
	//private QueryTableModel qtm;
	private static final long serialVersionUID = 1L;
	
	private String strNRC;
	private Customer cust= new Customer();
	private AccountMgr accountMgr= new AccountMgr();
	private SubscriptionMgr subMgr ;//= new SubscriptionMgr();
	private JLabel errorMsgSearchLabel;
	private JLabel errorMsgNRICLabel;
	
	//private List<SubscriptionPlan> listSubPlan = new ArrayList<SubscriptionPlan>() ;
	
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
	
	public ViewCustomerDetails(String strNRC) {
		super();
		initGUI();
	}
	
	public ViewCustomerDetails(BillingWindow window,String strNRC) {
		super();
		initGUI();
		this.strNRC=strNRC;
		this.GetCustomerDetails();
		ObjectsToControls();
	}
	
	public ViewCustomerDetails(BillingWindow window) {
		super();
		initGUI();				
	}
	
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new Dimension(655, 467));
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
					SearchCustButton.setText("Search");
					SearchCustButton.setBounds(371, 16, 82, 23);
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
					CustNameLabel = new JLabel( );
					ViewCustPanelCenter.add(CustNameLabel);
					CustNameLabel.setBounds(170, 54, 216, 16);
				}
				{
					Custaddress1Label = new JLabel( );
					ViewCustPanelCenter.add(Custaddress1Label);
					Custaddress1Label.setBounds(170, 82, 192, 46);
				}
				{
					Custaddress2Label = new JLabel( );
					ViewCustPanelCenter.add(Custaddress2Label);
					Custaddress2Label.setBounds(170, 137, 238, 41);
				}
				{
					Custaddress3Label = new JLabel( );
					ViewCustPanelCenter.add(Custaddress3Label);
					Custaddress3Label.setBounds(170, 194, 216, 45);
				}
				{
					CustTeleLabel = new JLabel( );
					ViewCustPanelCenter.add(CustTeleLabel);
					CustTeleLabel.setBounds(170, 249, 180, 16);
				}
				{
					CustInterestLabel = new JLabel( );
					ViewCustPanelCenter.add(CustInterestLabel);
					CustInterestLabel.setBounds(170, 295, 175, 16);
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
					accountNoLabel = new JLabel();
					ViewCustPanelCenter.add(accountNoLabel);
					accountNoLabel.setText("Account No :");
					accountNoLabel.setBounds(12, 364, 140, 16);
				}
				{
					CustAccontNoLabel = new JLabel( );
					ViewCustPanelCenter.add(CustAccontNoLabel);
					CustAccontNoLabel.setBounds(170, 367, 272, 10);
					
				}
				
				JButton btnSubscriptionInformation = new JButton("Subscription Information");
				btnSubscriptionInformation.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// call 
						System.out.println("aa");
//						setVisible(false);
//						//window.BillingWindow1(cust) ;					
//						System.out.println("ba");
//						SearchCustomer sC= new SearchCustomer(window);
//						JPanel j= new JPanel() ;
//						j.revalidate();
//						j= sC;
//						Container c= window.getContentPane();
//						Container.add(j);
//						
//						BillingSystem bs= new BillingSystem();
//						bs.startBillingWindow1(cust);
						
//						window.setContentPane(window.getContentPane());
//						JPanel j= new JPanel() ;
//						j.revalidate();
//						j= sC;
//						window.setContentPane(j);
						
					//	sC.setVisible(true);
						
						
//						AddCustomer currentPanel = new AddCustomer(window);
//						contentPane.revalidate();
//						contentPane = currentPanel;
//						window.setContentPane(contentPane);
//						
						//SearchCustomer sC= new SearchCustomer(window.getContentPane()); 
						//window.add(sC);
					}
				});
				btnSubscriptionInformation.setBounds(347, 410, 180, 23);
				ViewCustPanelCenter.add(btnSubscriptionInformation);
				{
					errorMsgSearchLabel = new JLabel("*No match record founds.");
					errorMsgSearchLabel.setBounds(463, 20, 146, 14);


					errorMsgSearchLabel.setOpaque(true);
					errorMsgSearchLabel.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgSearchLabel.setVisible(false);
					ViewCustPanelCenter.add(errorMsgSearchLabel);	
				}
				{
					errorMsgNRICLabel = new JLabel("*Please enter NRIC.");
					errorMsgNRICLabel.setBounds(475, 20, 170, 14);


					errorMsgNRICLabel.setOpaque(true);
					errorMsgNRICLabel.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgNRICLabel.setVisible(false);
					ViewCustPanelCenter.add(errorMsgNRICLabel);
				}
				
//				{
//					qtm = new QueryTableModel();
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void SearchCustButtonActionPerformed(ActionEvent evt) {
		accountMgr= new AccountMgr();
		clearErrorMsgData();
		System.out.println("a");
		if (validateControl()){					
			
		cust= accountMgr.getCustomerDetailsById(nricText.getText() );			
	
		if (cust!=null){
	
			ObjectsToControls();
		}
		else {
			
			System.out.println("2");
			errorMsgSearchLabel.setVisible(true);
			errorMsgNRICLabel.setVisible(false);
			ClearData();
		}
	}
		else {		
			System.out.println("1");
			errorMsgNRICLabel.setVisible(true);
			errorMsgSearchLabel.setVisible(false);
			
			ClearData();
		}
	}
	
	private void ObjectsToControls(){		
		CustNameLabel.setText(cust.getName()) ;
		nricText.setText(cust.getNric()) ;
		Custaddress1Label.setText(cust.getAddress1()) ;
		Custaddress2Label.setText(cust.getAddress2()) ;
		Custaddress3Label.setText(cust.getAddress3()) ;
		CustTeleLabel.setText(cust.getContactTel());
		CustInterestLabel.setText(cust.getInterest());
		CustAccontNoLabel.setText(cust.getAccIdByCust()) ;		
		//qtm.updateTable(listSubPlan);
	}
	
	private void ClearData(){
		CustNameLabel.setText(null) ;
		//nricText.setText(null) ;
		Custaddress1Label.setText(null) ;
		Custaddress2Label.setText(null) ;
		Custaddress3Label.setText(null) ;
		CustTeleLabel.setText(null);
		CustInterestLabel.setText(null);
		CustAccontNoLabel.setText(null) ;	
	}
	
	private void GetCustomerDetails(){
		cust= accountMgr.getCustomerDetailsById(strNRC);			
//		listSubPlan=subMgr.getAccountSubscriptions(cust.getAccIdByCust());
	}
	
	private boolean validateControl(){
		boolean bReturn= true;
		if (StringUtil.isNullOrEmpty(this.nricText.getText())){			
			errorMsgSearchLabel.setVisible(true);
			bReturn= false;
		}	
		System.out.println(bReturn);
		return bReturn;
	}
	
	private void clearErrorMsgData(){
		errorMsgSearchLabel.setVisible(false);		
		errorMsgNRICLabel.setVisible(false);
	}
}
