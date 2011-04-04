package sg.edu.nus.iss.billsys.gui;


import java.awt.event.*;
import javax.swing.*;

import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.vo.BillPeriod;

/**
 * @author Ma Huazhen, Xu Guoneng
 *
 */
public class obsolete_BillingReportGenerator extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow window;
	
	private JLabel lblBillPeriod;
	private JButton btnGenerate;
	
	private BillPeriod aBillPeriod;
	
    public obsolete_BillingReportGenerator (BillingWindow window) {
    	try
    	{
			this.window = window;
			
			iniFields();
			iniListeners();
			iniLayout();
    	}
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    private void iniFields(){
    	aBillPeriod = MgrFactory.getBillMgr().getNextBillPeriod();
    	lblBillPeriod = new JLabel ("Next Bill Period: " + aBillPeriod.printBillPeriod());
    	btnGenerate = new JButton ("Generate");
    }

    private void iniListeners(){
    	btnGenerate.addActionListener (new ActionListener () {
	        public void actionPerformed (ActionEvent e) {
	        	try{	
	        		MgrFactory.getBillMgr().generate(aBillPeriod);
	        		JOptionPane.showMessageDialog(window, "Bill generated successfully.");
	        		
	        		aBillPeriod = MgrFactory.getBillMgr().getNextBillPeriod();
	            	lblBillPeriod.setText("Next Bill Period: " + aBillPeriod.printBillPeriod());
	        		
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
    	add(lblBillPeriod);
    	add(btnGenerate);
    }
}