package sg.edu.nus.iss.billsys.gui;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

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
	private  ArrayList<Customer> customer;
	private QueryTableModel qtm;
	private BillingWindow  window;
	private static final long serialVersionUID = 1L;
	
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
					SearchCustPantelCenter.add(CustNameRadioButton);
					CustNameRadioButton.setText("Customer Name");
					CustNameRadioButton.setBounds(105, 35, 146, 20);
					CustNameRadioButton.setSelected(true);
				}
				{
					NRICRadioButton = new JRadioButton();
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
					SearchButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							SearchButtonActionPerformed(evt);
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
				qtm = new QueryTableModel();
				JTable table = new JTable(qtm);				
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
		cust= new Customer();
		accountMgr= new AccountMgr();
		boolean bNRIC= true;
		if (validateControls()){
			if (CustNameRadioButton.isSelected()) {
				bNRIC= false;
			}
			if (NRICRadioButton.isSelected()) {
				bNRIC= true;
			}
			
			if (bNRIC){
				//customer= accountMgr.getCustomerDetailsByName(SearchTextBox.getText() );
			}
			else {
				cust= accountMgr.getCustomerDetailsById(SearchTextBox.getText() );
			}
			
			this.bindToTable();
		}
		
		
	}
	
	private void bindToTable(){

		ArrayList< String[]> newlist = new ArrayList<String[]>();
		newlist.add(new String[] { "Head1", "Head2", "Head3" });
		newlist.add(new String[] { "a", "b", "c" });
		newlist.add(new String[] { "a", "ab", "ac" });
		
		//table.setModel(qtm);
		qtm.updateTable(newlist);
	}
	
	private boolean validateControls(){
		boolean bReturn= false;
		if (StringUtil.isNullOrEmpty(this.SearchTextBox.getText())){			
			errorMsgSearchLabel.setVisible(true);
			bReturn= true;
		}	
		return bReturn;
	}
	
	private void tableHeaderMouseClicked(MouseEvent e) {
		
		if (e.getClickCount() == 2) {
	         JTable target = (JTable)e.getSource();
	         int row = target.getSelectedRow();
	         //int column = target.getSelectedColumn(); 
	         String strCustomerID=target.getModel().getValueAt(row, 0).toString();
	        System.out.println(target.getModel().getValueAt(row, 0));
	      //  BillingSystem.updateContentPane(new ViewCustomerDetails(window,strCustomerID) ); 
	}

}
}
