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

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.tools.*;
import sg.edu.nus.iss.billsys.util.JTextFieldUtil;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.AccountMgr;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;


/**
* @author Win Kyi Tin 
*/

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
	private String errorMsg=null;
	private String strNRC;
	private Customer cust= new Customer();	

	private JLabel errorMsgSearchLabel;
	private JLabel errorMsgNRICLabel;
	private JLabel ErrMsgTeleNoLabel;
	private JLabel ErrMsgAlphabet;
	private JLabel errorMsgLabelName;
	
	private JLabel lblCustomersStatus;
	private JRadioButton rdbtnDeactivate;
	private JRadioButton rdbtnActivated;
	private ButtonGroup bgroup;
	private JButton btnEditCustomerInformation;
	private JTextField CustNameTextBox;
	private JTextField CustAdd1TextBox;
	private JTextField CustAdd2TextBox;
	private JTextField CustAdd3TextBox;
	private JTextField CustContact;
	private JTextField CustInterest;
	private JButton  btnSubscriptionInformation;
	private int MaxCharForString=30;
	private int MaxCharForNRIC=10;
	private int MaxCharForInteresting=50;
	private boolean bFlagForEdit= false;
	private int MaxCharForPhone=15;
	
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
		this.window = window;
		initGUI();
		this.strNRC=strNRC;
		this.GetCustomerDetails();
		ObjectsToControls();
	}
	
	public ViewCustomerDetails(BillingWindow window) {
		super();
		this.window = window;
		initGUI();				
	}
	
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new Dimension(655, 526));
			{
				ViewCustTitlePanel = new JPanel();
				this.add(ViewCustTitlePanel, BorderLayout.NORTH);
				ViewCustTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					ViewCustTitleLabel = new JLabel();
					ViewCustTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
					nricText.setDocument(new JTextFieldUtil(MaxCharForNRIC));
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
					Custaddress2Label.setBounds(170, 137, 272, 23);
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
					accountTitleLabel.setBounds(12, 376, 230, 16);
					accountTitleLabel.setFont(new java.awt.Font("Segoe UI",1,14));
				}
				{
					jSeparator1 = new JSeparator();
					ViewCustPanelCenter.add(jSeparator1);
					jSeparator1.setBounds(0, 355, 655, 10);
				}
				{
					accountNoLabel = new JLabel();
					ViewCustPanelCenter.add(accountNoLabel);
					accountNoLabel.setText("Account No :");
					accountNoLabel.setBounds(12, 402, 140, 16);
				}
				{
					CustAccontNoLabel = new JLabel( );
					ViewCustPanelCenter.add(CustAccontNoLabel);
					CustAccontNoLabel.setBounds(170, 405, 272, 10);
					
				}
				if (window.isAdmin()){
					
				
				btnSubscriptionInformation = new JButton("Subscription Information");
				btnSubscriptionInformation.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// call 
						
						if (validateControl()){
							window.refreshSubRegPanel(cust.getAccIdByCust());	
						}
						else {		
							
							errorMsgNRICLabel.setVisible(true);
							errorMsgSearchLabel.setVisible(false);
							
							ClearData();
						}
										
					}
				});
				btnSubscriptionInformation.setBounds(371, 466, 180, 23);
				ViewCustPanelCenter.add(btnSubscriptionInformation);
				}
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
					errorMsgNRICLabel.setBounds(463, 20, 170, 14);


					errorMsgNRICLabel.setOpaque(true);
					errorMsgNRICLabel.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgNRICLabel.setVisible(false);
					ViewCustPanelCenter.add(errorMsgNRICLabel);
				}
				{
					lblCustomersStatus = new JLabel("Customer 's Status :");
					lblCustomersStatus.setBounds(10, 330, 116, 14);
					ViewCustPanelCenter.add(lblCustomersStatus);
				}
				{
				 rdbtnActivated = new JRadioButton("Activated");
				 rdbtnActivated.setEnabled(false);
				rdbtnActivated.setBounds(170, 325, 88, 23);
				ViewCustPanelCenter.add(rdbtnActivated);
				}
				{
				 rdbtnDeactivate = new JRadioButton("Deactivate");
				 rdbtnDeactivate.setEnabled(false);
				 rdbtnDeactivate.setBounds(260, 325, 109, 23);
				 ViewCustPanelCenter.add(rdbtnDeactivate);
				}
				{
					bgroup = new ButtonGroup();
					bgroup.add(rdbtnActivated);
					bgroup.add(rdbtnDeactivate);				
				}
				if (window.isAdmin()){
					
				
				{
				btnEditCustomerInformation = new JButton("Edit Customer Information");
				btnEditCustomerInformation.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0){
						
							if (btnEditCustomerInformation.getText().equals("Edit Customer Information")){
								bFlagForEdit= true;
								 VisibilityControls(true);
								
							}
							else if (btnEditCustomerInformation.getText().equals("Update Customer Information")){
								 if (validateControl()){									
									 UpdateCustomerInformation();
									 bFlagForEdit=false;
									// VisibilityControls(false);
								 }	
								 
							}
						
					}
				});
				btnEditCustomerInformation.setBounds(371, 326, 238, 23);
				ViewCustPanelCenter.add(btnEditCustomerInformation);
				}
				}
				
				{
					CustNameTextBox = new JTextField();				
					CustNameTextBox.setBounds(170, 50, 192, 20);
					ViewCustPanelCenter.add(CustNameTextBox);
					CustNameTextBox.setColumns(10);
					CustNameTextBox.setVisible(false);
					CustNameTextBox.setDocument(new JTextFieldUtil(MaxCharForString));
				}
				
				{
					CustAdd1TextBox = new JTextField();
					CustAdd1TextBox.setBounds(170, 95, 255, 20);
					ViewCustPanelCenter.add(CustAdd1TextBox);
					CustAdd1TextBox.setColumns(10);
					CustAdd1TextBox.setVisible(false);
					CustAdd1TextBox.setDocument(new JTextFieldUtil(MaxCharForString));
				}
				{
					CustAdd2TextBox = new JTextField();
					CustAdd2TextBox.setBounds(170, 147, 255, 20);
					ViewCustPanelCenter.add(CustAdd2TextBox);
					CustAdd2TextBox.setColumns(10);
					CustAdd2TextBox.setVisible(false);
					CustAdd2TextBox.setDocument(new JTextFieldUtil(MaxCharForString));
				}
				
				{
					CustAdd3TextBox = new JTextField();
					CustAdd3TextBox.setBounds(170, 206, 255, 20);
					ViewCustPanelCenter.add(CustAdd3TextBox);
					CustAdd3TextBox.setColumns(10);
					CustAdd3TextBox.setVisible(false);
					CustAdd3TextBox.setDocument(new JTextFieldUtil(MaxCharForString));
				}
				
				{
					CustContact = new JTextField();
					CustContact.setBounds(170, 247, 192, 20);
					ViewCustPanelCenter.add(CustContact);
					CustContact.setColumns(10);
					CustContact.setVisible(false);
					CustContact.setDocument(new JTextFieldUtil(MaxCharForPhone));
				}
				{
					CustInterest = new JTextField();
					CustInterest.setBounds(170, 293, 255, 20);
					ViewCustPanelCenter.add(CustInterest);
					CustInterest.setColumns(10);
					CustInterest.setVisible(false);
					CustInterest.setDocument(new JTextFieldUtil(MaxCharForString));
				}
				{
					ErrMsgTeleNoLabel = new JLabel("*Invalid Telephone Number.Please use xx-xxxxxxxx");
					ErrMsgTeleNoLabel.setBounds(360, 250, 295, 14);
					ViewCustPanelCenter.add(ErrMsgTeleNoLabel);
					ErrMsgTeleNoLabel.setVisible(false);
					ErrMsgTeleNoLabel.setOpaque(true);
					ErrMsgTeleNoLabel.setForeground(new java.awt.Color(255, 0, 0));
				}
				{
					ErrMsgAlphabet = new JLabel("*Invalid alphabet.");
					ErrMsgAlphabet.setBounds(381, 55, 207, 14);
					ViewCustPanelCenter.add(ErrMsgAlphabet);
					ErrMsgAlphabet.setVisible(false);
					ErrMsgAlphabet.setOpaque(true);
					ErrMsgAlphabet.setForeground(new java.awt.Color(255, 0, 0));
				}
				{
					errorMsgLabelName = new JLabel();
					ViewCustPanelCenter.add(errorMsgLabelName);
					errorMsgLabelName.setText("*Please enter customer name.");
					errorMsgLabelName.setBounds(381, 54, 207, 16);
					errorMsgLabelName.setOpaque(true);
					errorMsgLabelName.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgLabelName.setVisible(false);
				}
//				{
//					qtm = new QueryTableModel();
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();			
			errorMsg=new BillingSystemException(e).getMessagebyException();
			JOptionPane.showMessageDialog(window, errorMsg, "Error  Message", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void SearchCustButtonActionPerformed(ActionEvent evt){
		clearErrorMsgData();
		bFlagForEdit= false;
		VisibilityControls(false);
		ControlBtnTextbox(false);
		
		if (validateControl()){				
			
			try {
				cust= MgrFactory.getAccountMgr().getCustomerDetailsById(nricText.getText() );
			} catch (BillingSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorMsg= e.getMessagebyException();
				JOptionPane.showMessageDialog(window, errorMsg, "Error  Message", JOptionPane.ERROR_MESSAGE);
			}			
		
			if (cust!=null){
		
				ObjectsToControls();
				btnEditCustomerInformation.setVisible(true);
				btnSubscriptionInformation.setVisible(true);
			}
			else {			
			
				errorMsgSearchLabel.setVisible(true);
				errorMsgNRICLabel.setVisible(false);
				ClearData();
			}
		}
		else {		
			
			errorMsgNRICLabel.setVisible(true);
			errorMsgSearchLabel.setVisible(false);			
			ClearData();
		}
	}
	
	private void UpdateCustomerInformation(){
		try {
			ControlsToObject();
			boolean bReturn = MgrFactory.getAccountMgr().updateCustomer(cust);
			
			
			if(bReturn){
				ObjectsToControls();
				VisibilityControls(false);
				JOptionPane.showMessageDialog(window, "Customer information has been updated. ", "Success Message", JOptionPane.INFORMATION_MESSAGE);
								
			}		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorMsg=new BillingSystemException(e).getMessagebyException();
			JOptionPane.showMessageDialog(window, errorMsg, "Error  Message", JOptionPane.ERROR_MESSAGE);
		}				
		
	}
	private void ObjectsToControls(){		
		CustNameLabel.setText(cust.getName()) ;
		nricText.setText(cust.getNric()) ;
		Custaddress1Label.setText(changeNullValue(cust.getAddress1())) ;
		Custaddress2Label.setText(changeNullValue(cust.getAddress2())) ;
		Custaddress3Label.setText(changeNullValue(cust.getAddress3())) ;
		CustTeleLabel.setText(changeNullValue(cust.getContactTel()));
		CustInterestLabel.setText(changeNullValue(cust.getInterest()));
		CustAccontNoLabel.setText(changeNullValue(cust.getAccIdByCust())) ;
		
		if (cust.isDeleted()){
			rdbtnActivated.setSelected(false);
			rdbtnDeactivate.setSelected(true);
		}
		else
		{	
			rdbtnDeactivate.setSelected(false);
			rdbtnActivated.setSelected(true);
		}

	
		CustNameTextBox.setText(changeNullValue(cust.getName())) ;	
		CustAdd1TextBox.setText(changeNullValue(cust.getAddress1())) ;
		CustAdd2TextBox.setText(changeNullValue(cust.getAddress2())) ;
		CustAdd3TextBox.setText(changeNullValue(cust.getAddress3())) ;
		CustContact.setText(changeNullValue(cust.getContactTel()));
		CustInterest.setText(changeNullValue(cust.getInterest()));
		
		//qtm.updateTable(listSubPlan);
	}
	
	private String changeNullValue(String s){
		String returnString="";
		
		if (s.equalsIgnoreCase("null")) {			
			returnString="";}
		else 
		{
			returnString=s;
		}
	
		return returnString;
	}
	private void ControlsToObject(){
	
		cust.setName(CustNameTextBox.getText());
		cust.setAddress1(CustAdd1TextBox.getText());
		cust.setAddress2(CustAdd2TextBox.getText());
		cust.setAddress3(CustAdd3TextBox.getText());
		cust.setContactTel(CustContact.getText());
		cust.setInterest(CustInterest.getText());
		
		if (rdbtnActivated.isSelected()) {
			cust.setIsDeleted(false);
		}
		if (rdbtnDeactivate.isSelected()) {
			cust.setIsDeleted(true);
		}
		
		System.out.println("done");
	}
	private void VisibilityControls(Boolean  bFlag){		
		
		CustNameTextBox.setVisible(bFlag);
		CustAdd1TextBox.setVisible(bFlag);
		CustAdd2TextBox.setVisible(bFlag);
		CustAdd3TextBox.setVisible(bFlag);
		CustContact.setVisible(bFlag);
		CustInterest.setVisible(bFlag);
		 rdbtnActivated.setEnabled(bFlag);
		 rdbtnDeactivate.setEnabled(bFlag);
		
		 
		 ControlBtnTextbox(bFlag);
		
		
		
		CustNameLabel.setVisible(!bFlag);
		Custaddress1Label.setVisible(!bFlag);
		Custaddress2Label.setVisible(!bFlag);
		Custaddress3Label.setVisible(!bFlag);
		CustTeleLabel.setVisible(!bFlag);
		CustInterestLabel.setVisible(!bFlag);
	}
	
	private void ControlBtnTextbox(boolean bFlag){
		if (bFlag){
			btnEditCustomerInformation.setText("Update Customer Information");				
	 
		}
		else {
			btnEditCustomerInformation.setText("Edit Customer Information");	
  
		}	
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
		rdbtnDeactivate.setSelected(false);
		rdbtnActivated.setSelected(false);
		
		CustNameTextBox.setText(null) ;
		CustAdd1TextBox.setText(null) ;
		CustAdd2TextBox.setText(null) ;
		CustAdd3TextBox.setText(null) ;
		CustTeleLabel.setText(null);
		CustInterest.setText(null);
	
		
		
	}
	
	private void GetCustomerDetails(){
		try {
			cust= MgrFactory.getAccountMgr().getCustomerDetailsById(strNRC);
			ObjectsToControls();
		} catch (BillingSystemException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			errorMsg=e.getMessagebyException();
			JOptionPane.showMessageDialog(window, errorMsg, "Error  Message", JOptionPane.ERROR_MESSAGE);
		}			
	}
	
	private boolean validateControl(){
		boolean bReturn= true;
		
		System.out.println( "eeror"+bFlagForEdit);
		if (StringUtil.isNullOrEmpty(this.nricText.getText())){			
			errorMsgSearchLabel.setVisible(true);
			bReturn= bReturn & false;
			System.out.println( "nricText "+bReturn);
		}	
		
		if (bFlagForEdit){
			
			if (!StringUtil.isNullOrEmpty(this.CustContact.getText())){				
				if (!this.CustContact.getText().matches("^\\(?(\\d{2})\\)?[- ]?(\\d{8})$")){
				// display error message
				ErrMsgTeleNoLabel.setVisible(true);
				bReturn=bReturn &  false;
				}
				else
				{
					ErrMsgTeleNoLabel.setVisible(false);
					bReturn= bReturn & true;
				}System.out.println( "CustContact "+bReturn);
			}	
			
			if (StringUtil.isNullOrEmpty(this.CustNameTextBox.getText())){
				// display error message
				errorMsgLabelName.setVisible(true);
				bReturn= bReturn & false;
			}
			else
			{
				if (!this.CustNameTextBox.getText().matches("^[a-zA-Z ]+$"))
				{
					errorMsgLabelName.setVisible(false);
					ErrMsgAlphabet.setVisible(true);
					bReturn=bReturn &  false;
				}
				else
				{
					errorMsgLabelName.setVisible(false);
					ErrMsgAlphabet.setVisible(false);
					bReturn= bReturn & true;
				}
			}System.out.println("CustContact "+bReturn);
		}
		System.out.println( "Returnj "+bReturn);
		return bReturn;
	}
	

	private void clearErrorMsgData(){
		errorMsgSearchLabel.setVisible(false);		
		errorMsgNRICLabel.setVisible(false);
		errorMsgLabelName.setVisible(false);
		ErrMsgAlphabet.setVisible(false);
		ErrMsgTeleNoLabel.setVisible(false);
	}
}
