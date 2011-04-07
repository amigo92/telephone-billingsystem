package sg.edu.nus.iss.billsys.gui;


import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.*;
import sg.edu.nus.iss.billsys.vo.*;

import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * @author Ma Huazhen, Xu Guoneng
 *
 */
public class BillingReportView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow window;
	private AccountMgr accountMgr;
	private BillMgr billMgr;

	private JLabel lblTitleView;
	private JLabel lblTitleGenerate;
	private JLabel lblNric;
	private JComboBox ddAccount;
	private JLabel lblBP;
	private JComboBox ddBillPeriod;
	private JTextArea txtReport;
	private JScrollPane spReport;

	
	private JLabel lblBillPeriod;
	private JButton btnGenerate;
	
	private BillPeriod aBillPeriod;
	private ArrayList<Customer>  customersList;
	private String accountNo;
	
    public BillingReportView (BillingWindow window) {
    	try
    	{
			this.window = window;
			accountMgr = window.getAccountMgr();
			billMgr =  MgrFactory.getBillMgr();
			
		    customersList =  accountMgr.getAllActiveCustomers();

			iniFields();
			iniListeners();
		    iniLayout();
		    
//		    Initialize ComboBox & trigger listener to set the value for "accountNo" 
		    ddAccount.setSelectedIndex(0);
//		    Initialize ComboBox & trigger listener to set the value for "aBillPeriod" 
	    	ddBillPeriod.setSelectedIndex(0);
	    	
    	}
        catch(Exception ex){
    		JOptionPane.showMessageDialog(window, ex.getMessage(),"", 0);
        }
    }
    
    private void iniFields(){
    	lblTitleView = new JLabel("<html><center><h3>View Bill Report</h3></center></html>");   
    	lblTitleView.setHorizontalAlignment(SwingConstants.CENTER );
    	
    	lblTitleGenerate = new JLabel("<html><center><h3>Generate Bill Report</h3></center></html>");   
    	lblTitleGenerate.setHorizontalAlignment(SwingConstants.CENTER );
    	
    	lblNric = new JLabel ("Please select a customer:");
    	ddAccount = createCustomerComboBox ();
    	lblBP = new JLabel ("Bill Period:");
    	ddBillPeriod = createBillPeriodComboBox();
    	txtReport = new JTextArea("No record found.");
    	txtReport.setEditable(false);
    	txtReport.setFont(new Font("Lucida Console", Font.PLAIN,12));
    	spReport = new JScrollPane(txtReport);
    	    	
    	aBillPeriod = MgrFactory.getBillMgr().getNextBillPeriod();
    	lblBillPeriod = new JLabel ("Next Bill Period: " + aBillPeriod.printBillPeriod());
    	btnGenerate = new JButton ("Generate for all customers");
    	
//    	Generate Bill Report function is only available for Administrator
    	if(!window.isAdmin()){
    		lblBillPeriod.setVisible(false);
    		btnGenerate.setVisible(false);
    		lblTitleGenerate.setVisible(false);
    	}
    }
    
    private void iniListeners(){
    	ddAccount.addActionListener(new ActionListener (){
 	    	public void actionPerformed (ActionEvent e) {
				  JComboBox cb = (JComboBox)e.getSource();
				  Customer  selectedCustomer = customersList.get(cb.getSelectedIndex());
				  accountNo = selectedCustomer.getAcct().getAcctNo();
				 
				  refreshReport();
 	        }
 	    });

    	
    	ddBillPeriod.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		try{
	    			refreshReport();
	    		} catch(Exception ex){
	        		JOptionPane.showMessageDialog(window, ex.getMessage(),"", 0);
		    	} 
	    	}
	    });
    	
    	btnGenerate.addActionListener (new ActionListener () {
	        public void actionPerformed (ActionEvent e) {
	        	try{	
	        		billMgr.generate(aBillPeriod);
	            	ddBillPeriod.addItem(aBillPeriod.printBillPeriod());
	            	ddBillPeriod.setSelectedIndex(ddBillPeriod.getItemCount() -1 );
	            	
	        		JOptionPane.showMessageDialog(window, "Bill generated successfully.");
	        		
	        		aBillPeriod = billMgr.getNextBillPeriod();
	            	lblBillPeriod.setText("Next Bill Period: " + aBillPeriod.printBillPeriod());
	            	
	        		window.validate();
	        	}catch(BillingSystemException ex){
	        		JOptionPane.showMessageDialog(window, ex.getMessagebyException(),"Error", 0);
		    	} catch(Exception ex){
	        		JOptionPane.showMessageDialog(window, ex.getMessage(),"Error", 0);
		    	} 	
	    	}
        });
    }
    
    private void iniLayout() {
    	 GroupLayout layout = new GroupLayout(this);
    	 this.setLayout(layout);
    	 
    	 layout.setAutoCreateGaps(true);
    	 layout.setAutoCreateContainerGaps(true);
    	 
    	 layout.setHorizontalGroup(
    		        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    		        			.addComponent(lblTitleView)	
    		            		.addGroup((layout.createSequentialGroup()
    		            					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    		            					.addComponent(lblNric)
			    		            					.addComponent(lblBP)
    		            						)
    		            					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    		            								.addComponent(ddAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
    		              		            	              GroupLayout.PREFERRED_SIZE)
    		            								.addComponent(ddBillPeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
    		              		            	              GroupLayout.PREFERRED_SIZE)  
    		            						)))	

    		            		.addComponent(spReport)	
    		            		
    		            		.addComponent(lblTitleGenerate)	
    		            		.addComponent(lblBillPeriod)	
			    		    	.addComponent(btnGenerate)
    		            
    	 );
    	 
    	 layout.setVerticalGroup(
    		        layout.createSequentialGroup()
    		        	.addComponent(lblTitleView)	
    		            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		            		.addComponent(lblNric)
    		            		.addComponent(ddAccount))
    		            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		            		.addComponent(lblBP)
    		            		.addComponent(ddBillPeriod))
		
    	    		    .addComponent(spReport)	
    	    		    
    	    		    .addComponent(lblTitleGenerate)	
    	    		    .addComponent(lblBillPeriod)	
			    		.addComponent(btnGenerate)
    	);
    }
    
    private JComboBox createCustomerComboBox () {  	
    	ddAccount = new JComboBox();
	    
	    for(Customer c :customersList){
	    	ddAccount.addItem(c.getName()+ "-" + c.getNric());
	    }

	    return ddAccount;
    }
 
    /**
     *ComboBox for generated bill periods 
     */
 	private JComboBox createBillPeriodComboBox() {  
    	BillPeriod[] bps = billMgr.getAllGeneratedBillPeriods();
    	String[] periods = new String[bps.length];
    	for(int i = 0; i < bps.length; i++){
    		periods[i] = bps[i].printBillPeriod();
    	}
    	
    	ddBillPeriod = new JComboBox(periods);
	    return ddBillPeriod;
    }
 	
 	/**
 	 * Refresh report
 	 * Trigger by  Bill Report ComboBox change Or Customer ComboBox change
 	 */
 	private void refreshReport(){
			String yearMonth = (String)ddBillPeriod.getSelectedItem();
    		BillPeriod billPeriod = new BillPeriod(Integer.parseInt(yearMonth.substring(0, 4)), Integer.parseInt(yearMonth.substring(5, 7)));
    		
			Bill bill = billMgr.getBill(billPeriod, accountNo);
			if(bill != null){
				txtReport.setText(bill.toString());
				txtReport.setCaretPosition(0);
			}
			else{
				txtReport.setText("No record found.");
			}
    		window.validate();
 	}
}