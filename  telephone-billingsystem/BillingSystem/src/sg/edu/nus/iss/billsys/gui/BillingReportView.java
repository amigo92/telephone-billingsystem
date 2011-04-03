package sg.edu.nus.iss.billsys.gui;


import sg.edu.nus.iss.billsys.mgr.*;
import sg.edu.nus.iss.billsys.vo.*;

import java.awt.Font;
import java.awt.Label;
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
		    customersList =  accountMgr.getAllActiveCustomers();

			iniFields();
			iniListeners();
		    iniLayout();
    	}
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    private void iniFields(){
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
    	
    	if(!window.isAdmin()){
    		lblBillPeriod.setVisible(false);
    		btnGenerate.setVisible(false);
    	}
    }
    
    private void iniListeners(){
    	ddAccount.addActionListener(new ActionListener (){
 	    	public void actionPerformed (ActionEvent e) {
				  JComboBox cb = (JComboBox)e.getSource();
				  Customer  selectedCustomer = customersList.get(cb.getSelectedIndex());
				  accountNo = selectedCustomer.getAcct().getAcctNo();
				   
				  refreshReprot();
 	        }
 	    });

    	
    	ddBillPeriod.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		try{
	    			refreshReprot();
	    		}
	    		catch(Exception ex){
	    			ex.printStackTrace();
	    		}
	    	}
	    });
    	
    	btnGenerate.addActionListener (new ActionListener () {
	        public void actionPerformed (ActionEvent e) {
	        	try{	
	        		MgrFactory.getBillMgr().generate(aBillPeriod);
	        		JOptionPane.showMessageDialog(window, "Bill generated successfully.");
	        		
	        		aBillPeriod = MgrFactory.getBillMgr().getNextBillPeriod();
	            	lblBillPeriod.setText("Next Bill Period: " + aBillPeriod.printBillPeriod());
	            	
	            	ddBillPeriod.addItem(aBillPeriod.printBillPeriod());   		
	        		window.validate();
	        	}
		    	catch(Exception ex)
		    	{
		    		ex.printStackTrace();
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
    		            		.addComponent(lblBillPeriod)	
			    		    	.addComponent(btnGenerate)
    		            
    	 );
    	 
    	 layout.setVerticalGroup(
    		        layout.createSequentialGroup()
    		            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		            		.addComponent(lblNric)
    		            		.addComponent(ddAccount))
    		            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		            		.addComponent(lblBP)
    		            		.addComponent(ddBillPeriod))
		
    	    		    .addComponent(spReport)	
    	    		    .addComponent(lblBillPeriod)	
			    		.addComponent(btnGenerate)
    	);
    }
    
    private JComboBox createCustomerComboBox () {  	
	    JComboBox accountBox = new JComboBox();
	    
	    for(Customer c :customersList){
	    	accountBox.addItem(c.getName()+ "-" + c.getNric());
	    }
	 
	   
	    accountBox.setSelectedIndex(0);
	    return accountBox;
    }
 
 	private JComboBox createBillPeriodComboBox() {  
    	BillPeriod[] bps = MgrFactory.getBillMgr().getAllGeneratedBillPeriods();
    	String[] periods = new String[bps.length];
    	for(int i = 0; i < bps.length; i++){
    		periods[i] = bps[i].printBillPeriod();
    	}
    	
    	ddBillPeriod = new JComboBox(periods);
    	ddBillPeriod.setSelectedIndex(0);
	    add(ddBillPeriod);
	    return ddBillPeriod;
    }
 	
 	private void refreshReprot(){
			String yearMonth = (String)ddBillPeriod.getSelectedItem();
    		BillPeriod billPeriod = new BillPeriod(Integer.parseInt(yearMonth.substring(0, 4)), Integer.parseInt(yearMonth.substring(5, 7)));
    		
			Bill bill = MgrFactory.getBillMgr().getBill(billPeriod, accountNo);
			if(bill != null){
				txtReport.setText(bill.toString());
			}
			else{
				txtReport.setText("No record found.");
			}
    		window.validate();
 	}
}