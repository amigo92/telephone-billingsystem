package sg.edu.nus.iss.billsys.gui;


import sg.edu.nus.iss.billsys.mgr.*;
import sg.edu.nus.iss.billsys.vo.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Ma Huazhen, Xu Guoneng
 *
 */
public class BillingReportView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow window;
	
	private JTextField txtNric;
	private JComboBox ddBillPeriod;
	private JTextArea txtReport;
	private JButton btnView;
	
	private BillPeriod billPeriod;
	
    public BillingReportView (BillingWindow window) {
    	try
    	{
			this.window = window;
			
			iniFields();
			iniListeners();
			
		    setLayout (new BorderLayout());
		    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		    iniLayout();
		    
		    txtNric.setText("S8481362F"); //TODO
		    
    	}
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    private void iniFields(){
    	txtNric = new JTextField (1);
    	createBillPeriodComboBox();
    	txtReport = new JTextArea("No record found.");
    	btnView = new JButton ("View");
    }
    
    private void iniListeners(){
    	btnView.addActionListener (new ActionListener () {
	        public void actionPerformed (ActionEvent e) {
	        	try{	
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
		    	catch(Exception ex)
		    	{
		    		ex.printStackTrace();
		    	} 	
	    	}
        });
    }
    
    private void iniLayout() {
    	JPanel p = new JPanel (new GridLayout (0,3));
    
    	p.add(new JLabel ("Customer's NRIC:   "));
        p.add(txtNric);
        p.add(new JLabel (""));
        
        p.add(new JLabel ("Bill Period  :   "));
        p.add(ddBillPeriod);
        p.add(new JLabel (""));
  
        p.add(new JLabel (""));
        p.add(new JLabel (""));
        
        add ("Center", new JScrollPane(txtReport));
        p.add(btnView);

        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());   
        bp.add ("North", p);
        
        add ("North", bp);
    }
 
 	private JComboBox createBillPeriodComboBox() {  
    	BillPeriod[] bps = MgrFactory.getBillMgr().getAllGeneratedBillPeriods();
    	String[] periods = new String[bps.length];
    	for(int i = 0; i < bps.length; i++){
    		periods[i] = bps[i].printBillPeriod();
    	}
    	
    	ddBillPeriod = new JComboBox(periods);
	    
    	ddBillPeriod.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		String yearMonth = (String)ddBillPeriod.getSelectedItem();
	    		billPeriod = new BillPeriod(Integer.parseInt(yearMonth.substring(0, 4)), Integer.parseInt(yearMonth.substring(5, 7)));
	    	}
	    });
	    
    	billPeriod = bps[0];	//starting month
    	
    	ddBillPeriod.setSelectedIndex(0);
	    add(ddBillPeriod, BorderLayout.PAGE_START);
	    return ddBillPeriod;
    }
}