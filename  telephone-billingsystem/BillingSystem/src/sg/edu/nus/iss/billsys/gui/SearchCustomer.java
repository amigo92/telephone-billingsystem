package sg.edu.nus.iss.billsys.gui;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.ButtonGroup;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.AccountMgr;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.tools.*;

/**
* @author Win Kyi Tin 
*/


public class SearchCustomer extends javax.swing.JPanel {
	private JPanel SearchCustPanel;
	private JLabel SearchCustTitleLabel;
	private JSeparator jSeparator1;

	private JRadioButton CustNameRadioButton;
	private JRadioButton NRICRadioButton;
	private JLabel SearchLabel;
	private JTextField SearchTextBox;
	private JButton SearchButton;
	private JPanel pTable;
	private JLabel errorMsgSearchLabel;
	private JLabel jLabel1;
	private JPanel SearchCustPantelCenter;
	private Customer cust;
	private AccountMgr accountMgr;
	//private  ArrayList<Customer> customer;
	private QueryTableModel qtm;
	private BillingWindow  window;
	private static final long serialVersionUID = 1L;
	private ArrayList< String[]> newlist = new ArrayList<String[]>();
	private String errorMsg=null;
	private JTable table ;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SearchCustomer());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public SearchCustomer() {
		super();
		initControl();
	}
	
	public SearchCustomer (BillingWindow window) {
		
		this.window = window;	
		initControl();
	}
	

	private void initControl() {
		try {
		
			
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(571, 300));
			{
				SearchCustPanel = new JPanel();
				this.add(SearchCustPanel, BorderLayout.NORTH);
				SearchCustPanel.setLayout(null);
				SearchCustPanel.setPreferredSize(new java.awt.Dimension(400, 28));
				{
					SearchCustTitleLabel = new JLabel();
					SearchCustPanel.add(SearchCustTitleLabel);
					SearchCustTitleLabel.setText("Search Customer");
					SearchCustTitleLabel.setBounds(12, 6, 389, 16);
					SearchCustTitleLabel.setFont(new java.awt.Font("Segoe UI",1,14));
				}
			}
			{
				SearchCustPantelCenter = new JPanel();
				this.add(SearchCustPantelCenter, BorderLayout.CENTER);
				SearchCustPantelCenter.setLayout(null);
				SearchCustPantelCenter.setPreferredSize(new java.awt.Dimension(571, 329));
				{
					jSeparator1 = new JSeparator();
					SearchCustPantelCenter.add(jSeparator1);
					jSeparator1.setBounds(0, 70, 559, 10);
				}
				{
					jLabel1 = new JLabel();
					SearchCustPantelCenter.add(jLabel1);
					jLabel1.setText("Search By : ");
					jLabel1.setBounds(12, 19, 119, 16);
				}
				{
					CustNameRadioButton = new JRadioButton();
					CustNameRadioButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							SearchTextBox.setText(null);
							 clearErrorMsgData();
						}
					});
					SearchCustPantelCenter.add(CustNameRadioButton);
					CustNameRadioButton.setText("Customer Name");
					CustNameRadioButton.setBounds(105, 35, 146, 20);
					CustNameRadioButton.setSelected(true);
				}
				{
					NRICRadioButton = new JRadioButton();
					NRICRadioButton.addMouseListener(new MouseAdapter() {						
						public void mouseClicked(MouseEvent arg0) {
							SearchTextBox.setText(null);
							 clearErrorMsgData();
						}
					});
					SearchCustPantelCenter.add(NRICRadioButton);
					NRICRadioButton.setText("NRIC");
					NRICRadioButton.setBounds(274, 35, 137, 20);
					NRICRadioButton.setSelected(false);
				}
				{
					SearchLabel = new JLabel();
					SearchCustPantelCenter.add(SearchLabel);
					SearchLabel.setText("Customer Name / NRIC :");
					SearchLabel.setBounds(12, 92, 174, 16);
				}
				{
					SearchTextBox = new JTextField();
					SearchCustPantelCenter.add(SearchTextBox);
					SearchTextBox.setBounds(193, 89, 243, 23);
				}
				{
					SearchButton = new JButton();
					SearchCustPantelCenter.add(SearchButton);
					SearchButton.setText("Search");
					SearchButton.setBounds(448, 89, 81, 23);
					
					System.out.println("scaa123478");
					
					SearchButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try
							{
								System.out.println("scaa1234789");
							  SearchButtonActionPerformed(evt);
							
							} catch (Exception e) {
							    // Print out the exception that occurred
								errorMsg=new BillingSystemException(e).getMessagebyException();
							}
						}
					});
				}
				{
					errorMsgSearchLabel = new JLabel();
					SearchCustPantelCenter.add(errorMsgSearchLabel);
					errorMsgSearchLabel.setText("*Please enter customer name/ nrc.");
					errorMsgSearchLabel.setBounds(12, 120, 386, 16);
					errorMsgSearchLabel.setOpaque(true);
					errorMsgSearchLabel.setForeground(new java.awt.Color(255, 0, 0));
					errorMsgSearchLabel.setVisible(false);		
				}
				{
					pTable = new JPanel();
					BorderLayout pTableLayout = new BorderLayout();
					pTable.setLayout(pTableLayout);
					SearchCustPantelCenter.add(pTable);
					pTable.setBounds(12, 153, 547, 107);
					

				}
				{
					 ButtonGroup group = new ButtonGroup();
					 group.add(CustNameRadioButton);
					 group.add(NRICRadioButton);
					 
				}
				{
				qtm = new QueryTableModel();
				table = new JTable(qtm);				
				JScrollPane scrollpane = new JScrollPane(table);
				pTable.add(scrollpane, BorderLayout.CENTER);
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						tableHeaderMouseClicked(evt);
					}
				});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void SearchButtonActionPerformed(ActionEvent evt) {
		newlist= new ArrayList<String[]>(); 	
	
		
		accountMgr= new AccountMgr();
		boolean bNRIC= true;
		System.out.println("scaa123478910");
	
		
		if (validateControls()){		
			
			if (CustNameRadioButton.isSelected()) {
				bNRIC= false;
			}
			if (NRICRadioButton.isSelected()) {
				bNRIC= true;
			}
		
			newlist.add(new String[] { "Customer Name", "Customer NRIC"});
			
			if (!bNRIC){
				//still waiting wen jing code for this method
				cust= accountMgr.getCustomerDetailsByName(SearchTextBox.getText());				
			}
			else {				
			
				cust= accountMgr.getCustomerDetailsById(SearchTextBox.getText() );		
				
			}				
		
			if (cust!= null){
				
				newlist.add(new String[] { cust.getName(), cust.getNric() });
				clearErrorMsgData();
				
			}
			else 
			{				
				System.out.println( newlist.size());
				//newlist.add(new String[] { " ", " "});
				errorMsgSearchLabel.setText("No match record founds." );
				errorMsgSearchLabel.setVisible(true);
			}	
			qtm.updateTable(newlist);
		}
		
		
	}
	
	private void bindToTable(){

//		ArrayList< String[]> newlist = new ArrayList<String[]>();
//		newlist.add(new String[] { "Customer Name", "Customer NRIC"});
//		newlist.add(new String[] { "a", "b", "c" });
//		newlist.add(new String[] { "a", "ab", "ac" });
		
		//table.setModel(qtm);
	//	qtm.updateTable(newlist);
	}
	
	private boolean validateControls(){
		boolean bReturn= true;
		if (StringUtil.isNullOrEmpty(this.SearchTextBox.getText())){			
			errorMsgSearchLabel.setVisible(true);
			bReturn= false;
		}	
		return bReturn;
	}
	
	private void tableHeaderMouseClicked(MouseEvent e) {
		
		if (e.getClickCount() == 2) {
	         JTable target = (JTable)e.getSource();
	         int row = target.getSelectedRow();
	         //int column = target.getSelectedColumn(); 
	         String strCustomerNRC=target.getModel().getValueAt(row, 1).toString();
	         window.refreshPanelForViewCust(cust.getNric());	
	}
		
}
	
	private void clearErrorMsgData(){
		errorMsgSearchLabel.setVisible(false);

	}
}
