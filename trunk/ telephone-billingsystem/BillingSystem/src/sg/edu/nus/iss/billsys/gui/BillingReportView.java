package sg.edu.nus.iss.billsys.gui;


import sg.edu.nus.iss.billsys.mgr.*;
import sg.edu.nus.iss.billsys.vo.*;

import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author Ma Huazhen, Xu Guoneng
 *
 */
public class BillingReportView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow window;
	
	private JLabel lblNric;
	private JTextField txtNric;
	private JLabel lblBP;
	private JComboBox ddBillPeriod;
	private JTextArea txtReport;
	private JScrollPane spReport;
	
	private JLabel lblBillPeriod;
	private JButton btnGenerate;
	
	private BillPeriod aBillPeriod;
	
    public BillingReportView (BillingWindow window) {
    	try
    	{
			this.window = window;
			
			iniFields();
			iniListeners();
		    iniLayout();
		    
		    txtNric.setText("S8481362F"); //TODO  to be removed
    	}
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    private void iniFields(){
    	lblNric = new JLabel ("Customer's NRIC:");
    	txtNric = new JTextField(10);
    	lblBP = new JLabel ("Bill Period:");
    	ddBillPeriod = createBillPeriodComboBox();
    	txtReport = new JTextArea("No record found.");
    	txtReport.setEditable(false);
    	txtReport.setFont(new Font("Lucida Console", Font.PLAIN,12));
    	spReport = new JScrollPane(txtReport);
    	
    	aBillPeriod = MgrFactory.getBillMgr().getNextBillPeriod();
    	lblBillPeriod = new JLabel ("Next Bill Period: " + aBillPeriod.printBillPeriod());
    	btnGenerate = new JButton ("Generate");
    	
    	if(!window.isAdmin()){
    		lblBillPeriod.setVisible(false);
    		btnGenerate.setVisible(false);
    	}
    }
    
    private void iniListeners(){
    	ddBillPeriod.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		try{
		    		String yearMonth = (String)ddBillPeriod.getSelectedItem();
		    		BillPeriod billPeriod = new BillPeriod(Integer.parseInt(yearMonth.substring(0, 4)), Integer.parseInt(yearMonth.substring(5, 7)));
		    		
		    		Customer customer = MgrFactory.getAccountMgr().getCustomerDetailsById(txtNric.getText());
	        		
	        		if(customer == null){
	        			JOptionPane.showMessageDialog(window, "No customer found.");
	        			txtReport.setText("No record found.");
	        		}
	        		else{
	        			String accountNo = customer.getAcct().getAcctNo();
	        			Bill bill = MgrFactory.getBillMgr().getBill(billPeriod, accountNo);
	        			if(bill != null){
	        				txtReport.setText(bill.toString());
	        			}
	        			else{
	        				txtReport.setText("No record found.");
	        			}
	        		}
	
	        		window.validate();
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
    		            								.addComponent(txtNric, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
    		              		            	              GroupLayout.PREFERRED_SIZE)
    		            								.addComponent(ddBillPeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
    		              		            	              GroupLayout.PREFERRED_SIZE)  
    		            						)))	
    		            		.addComponent(lblBillPeriod)	
			    		    	.addComponent(btnGenerate)
    		            		.addComponent(spReport)		
    		            
    	 );
    	 
    	 layout.setVerticalGroup(
    		        layout.createSequentialGroup()
    		            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		            		.addComponent(lblNric)
    		            		.addComponent(txtNric))
    		            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    		            		.addComponent(lblBP)
    		            		.addComponent(ddBillPeriod))
    		            .addComponent(lblBillPeriod)	
			    		.addComponent(btnGenerate)		
    	    		    .addComponent(spReport)	
    	);
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
}