package sg.edu.nus.iss.billsys.gui;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.util.JTextFieldUtil;

/**
* @author Win Kyi Tin 
*/

public class AddCustomer extends javax.swing.JPanel {
	private JPanel AddCustTitlePanel;
	private JLabel ContactTelLabel;
	private JLabel IntestingLabel;
	private JLabel errorMsgNIRC;
	private JButton CancelButton;
	private JButton SubmitButton;
	private JTextField CustContactTelText;
	private JTextField InterestingText;
	private JLabel NRCLabel;
	private JLabel Address1Label;
	private JTextField CustAdd3Text;
	private JLabel CustAdd2Label;
	private JLabel CustAdd3Label;
	private JTextField CustNIRCText;
	private JTextField CustAdd1Text;
	private JTextField CustAdd2Text;
	private JTextField CustNameText;
	private JLabel CustomerNameLabel;
	private JPanel CustomerCenterPanel;
	private JLabel CustomerTitle;
	private JLabel ErrMsgTeleNoLabel; 
	private JLabel errorMsgLabelName;
	private JLabel ErrMsgInvalidNric;
	private BillingWindow window;
	private JLabel ErrMsgAlphabet;
	private int MaxCharForString=30;
	private int MaxCharForNRIC=10;
	private int MaxCharForInteresting=50;
	

	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new AddCustomer());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setSize(668, 419);
		frame.setVisible(true);
		frame.setPreferredSize(new java.awt.Dimension(668, 419));
	}
	
	public AddCustomer() {
		super();
		initGUI();
	}
	
	public AddCustomer(BillingWindow window) {
		this.window = window;
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(619, 428));
			{
				AddCustTitlePanel = new JPanel();
				this.add(AddCustTitlePanel, BorderLayout.NORTH);
				{
					CustomerTitle = new JLabel();
					AddCustTitlePanel.add(CustomerTitle);
					CustomerTitle.setText("Customer Registration");
					CustomerTitle.setFont(new java.awt.Font("Segoe UI",1,14));
					CustomerTitle.setPreferredSize(new java.awt.Dimension(254, 20));
				}
			}
			{
				CustomerCenterPanel = new JPanel();
				this.add(CustomerCenterPanel, BorderLayout.CENTER);
				CustomerCenterPanel.setPreferredSize(new java.awt.Dimension(621, 391));
				CustomerCenterPanel.setLayout(null);
				{
					CustomerNameLabel = new JLabel();
					CustomerCenterPanel.add(CustomerNameLabel);
					CustomerNameLabel.setText("Customer Name* :");
					CustomerNameLabel.setBounds(6, 18, 154, 16);
				}
				{
					NRCLabel = new JLabel();
					CustomerCenterPanel.add(NRCLabel);
					NRCLabel.setText("NRIC * :");
					NRCLabel.setBounds(6, 56, 117, 16);
				}
				{
					Address1Label = new JLabel();
					CustomerCenterPanel.add(Address1Label);
					Address1Label.setText("Address 1  : ");
					Address1Label.setBounds(6, 91, 102, 16);
				}
				{
					CustNameText = new JTextField();
					CustomerCenterPanel.add(CustNameText);
					CustNameText.setBounds(148, 18, 252, 23);
					CustNameText.setDocument(new JTextFieldUtil(MaxCharForString));
				}
				{
					CustNIRCText = new JTextField();
					CustomerCenterPanel.add(CustNIRCText);
					CustNIRCText.setBounds(148, 53, 252, 23);
					CustNIRCText.setDocument(new JTextFieldUtil(MaxCharForNRIC));
				}
				{
					CustAdd1Text = new JTextField();					
					CustomerCenterPanel.add(CustAdd1Text);
					CustAdd1Text.setBounds(148, 88, 252, 23);
					CustAdd1Text.setDocument(new JTextFieldUtil(MaxCharForString));

				}
				{
					CustAdd2Text = new JTextField();
					CustomerCenterPanel.add(CustAdd2Text);
					CustAdd2Text.setBounds(148, 122, 252, 23);
					CustAdd2Text.setDocument(new JTextFieldUtil(MaxCharForString));
				}
				{
					CustAdd2Label = new JLabel();
					CustomerCenterPanel.add(CustAdd2Label);
					CustAdd2Label.setText("Address 2  : ");
					CustAdd2Label.setBounds(6, 125, 102, 16);
				}
				{
					CustAdd3Label = new JLabel();
					CustomerCenterPanel.add(CustAdd3Label);
					CustAdd3Label.setText("Address 3  : ");
					CustAdd3Label.setBounds(6, 159, 102, 16);
				}
				{
					CustAdd3Text = new JTextField();
					CustomerCenterPanel.add(CustAdd3Text);
					CustAdd3Text.setBounds(148, 156, 252, 23);
					CustAdd3Text.setDocument(new JTextFieldUtil(MaxCharForString));
				}
				{
					ContactTelLabel = new JLabel();
					CustomerCenterPanel.add(ContactTelLabel);
					ContactTelLabel.setText("Contact Telephone :");
					ContactTelLabel.setBounds(6, 193, 142, 16);
				}
				{
					CustContactTelText = new JTextField();
					CustomerCenterPanel.add(CustContactTelText);
					CustContactTelText.setBounds(148, 190, 164, 23);
					CustContactTelText.setDocument(new JTextFieldUtil(MaxCharForNRIC));
				}
				{
					InterestingText  = new JTextField();
					CustomerCenterPanel.add(InterestingText);
					InterestingText.setBounds(148, 220, 394, 23);
					InterestingText.setDocument(new JTextFieldUtil(MaxCharForInteresting));
				}
				{
					IntestingLabel = new JLabel();
					CustomerCenterPanel.add(IntestingLabel);
					IntestingLabel.setText("Interesting :");
					IntestingLabel.setBounds(6, 230, 142, 16);
				}
				{
					SubmitButton = new JButton();
					CustomerCenterPanel.add(SubmitButton);
					SubmitButton.setText("Submit");
					SubmitButton.setBounds(231, 272, 81, 23);
					SubmitButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							 try{
							      
								 SubmitButtonActionPerformed(evt);
								 
							 	} catch (BillingSystemException ex) {
								    // Print out the exception that occurred
								  //  System.out.println(ex.getMessage());
							 		ex.printStackTrace();
							 		JOptionPane.showMessageDialog(window,ex.getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
									return;
								} catch (Exception e) {
								    // Print out the exception that occurred
									e.printStackTrace();
									JOptionPane.showMessageDialog(window, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
									return;
									
								}
								
							
						}
					});
				}
				{
					CancelButton = new JButton();
					CustomerCenterPanel.add(CancelButton);
					CancelButton.setText("Cancel");
					CancelButton.setBounds(320, 272, 77, 23);
					CancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							CancelButtonActionPerformed(evt);
						}
					});
				}
				{
					errorMsgLabelName = new JLabel();
					CustomerCenterPanel.add(errorMsgLabelName);
					errorMsgLabelName.setText("*Please enter customer name.");
					errorMsgLabelName.setBounds(406, 21, 207, 16);
					errorMsgLabelName.setOpaque(true);
					errorMsgLabelName.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgLabelName.setVisible(false);
				}
				{
					errorMsgNIRC = new JLabel();
					CustomerCenterPanel.add(errorMsgNIRC);
					errorMsgNIRC.setText("*Please enter NIRC.");
					errorMsgNIRC.setBounds(406, 49, 169, 16);
					errorMsgNIRC.setOpaque(true);
					errorMsgNIRC.setVisible(false);
					errorMsgNIRC.setForeground(new java.awt.Color(255, 0, 0));
				}
				{
					ErrMsgTeleNoLabel = new JLabel("*Invalid Telephone Number");
					ErrMsgTeleNoLabel.setBounds(320, 194, 194, 14);
					CustomerCenterPanel.add(ErrMsgTeleNoLabel);
					ErrMsgTeleNoLabel.setVisible(false);
					ErrMsgTeleNoLabel.setOpaque(true);
					ErrMsgTeleNoLabel.setForeground(new java.awt.Color(255, 0, 0));
				}
				{
					ErrMsgInvalidNric = new JLabel("*Invalid NRIC.");
					ErrMsgInvalidNric.setBounds(410, 50, 122, 14);
					CustomerCenterPanel.add(ErrMsgInvalidNric);
					ErrMsgInvalidNric.setVisible(false);
					ErrMsgInvalidNric.setOpaque(true);
					ErrMsgInvalidNric.setForeground(new java.awt.Color(255, 0, 0));
				}
				{
					ErrMsgAlphabet = new JLabel("*Invalid alphabet.");
					ErrMsgAlphabet.setBounds(406, 19, 207, 14);
					CustomerCenterPanel.add(ErrMsgAlphabet);
					ErrMsgAlphabet.setVisible(false);
					ErrMsgAlphabet.setOpaque(true);
					ErrMsgAlphabet.setForeground(new java.awt.Color(255, 0, 0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(window, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void CancelButtonActionPerformed(ActionEvent evt) {
	//	System.out.println("CancelButton.actionPerformed, event="+evt);
		
		this.setVisible(false);
	}
	
	private void SubmitButtonActionPerformed(ActionEvent evt) throws BillingSystemException {
	//	System.out.println("SubmitButton.actionPerformed, event="+evt);

		
		try{
			if (validateControls()){
				Customer cust = MgrFactory.getAccountMgr().getCustomerDetailsById(CustNIRCText.getText());
				if(cust != null){
					JOptionPane.showMessageDialog(null ,"This customer has been registered before!","BillingSystem ",1);
					clearErrorMsgData();
				}
				else{
					try
					{
					cust = MgrFactory.getAccountMgr().createCustomer(CustNameText.getText(), CustNIRCText.getText(), CustContactTelText.getText(), CustAdd1Text.getText(), CustAdd2Text.getText(), CustAdd3Text.getText(), InterestingText.getText());
					
					
					if ( cust.getAccIdByCust() != null){
						JOptionPane.showMessageDialog(null ,"Customer Profile is created successfully.","BillingSystem ",1);
						clearErrorMsgData();
					}
					}
					catch (BillingSystemException ex)
					{				
						ex.printStackTrace();	
						JOptionPane.showMessageDialog(window, ex.getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
						return;
					}catch(Exception e){
						e.printStackTrace();	
						JOptionPane.showMessageDialog(window, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();	
			JOptionPane.showMessageDialog(window, new BillingSystemException(ex).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}


	}
	
	private boolean validateControls(){
		boolean bReturn= true;
		if (StringUtil.isNullOrEmpty(this.CustNameText.getText())){
			// display error message
			errorMsgLabelName.setVisible(true);
			bReturn= false;
		}
		else
		{
			if (!this.CustNameText.getText().matches("^[a-zA-Z]+$"))
			{
				errorMsgLabelName.setVisible(false);
				ErrMsgAlphabet.setVisible(true);
				bReturn= false;
			}
			else
			{
				errorMsgLabelName.setVisible(false);
				ErrMsgAlphabet.setVisible(false);
				bReturn= true;
			}
		}
		
		
		if (StringUtil.isNullOrEmpty(this.CustNIRCText.getText())){
			// display error message
			errorMsgNIRC.setVisible(true);
			bReturn= false;
		}	
		else
		{
			if (!this.CustNIRCText.getText().matches("S\\d{7}[A-Z]"))
			{
				errorMsgNIRC.setVisible(false);
				ErrMsgInvalidNric.setVisible(true);
				bReturn= false;
			}
			else
			{
				errorMsgNIRC.setVisible(false);
				ErrMsgInvalidNric.setVisible(false);
				bReturn= true;
			}
	
		}
		
		if (!StringUtil.isNullOrEmpty(this.CustContactTelText.getText())){
			
			if (!isNumeric(this.CustContactTelText.getText())){
			// display error message
			ErrMsgTeleNoLabel.setVisible(true);
			bReturn= false;
			}
			else
			{
				ErrMsgTeleNoLabel.setVisible(false);
				bReturn= true;
			}
		}		
		
		
		
		return bReturn;
	}
	
	public boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

	
	private void clearErrorMsgData(){
		errorMsgLabelName.setVisible(false);
		errorMsgNIRC.setVisible(false);
		ErrMsgInvalidNric.setVisible(false);
		ErrMsgAlphabet.setVisible(false);

	}

}

