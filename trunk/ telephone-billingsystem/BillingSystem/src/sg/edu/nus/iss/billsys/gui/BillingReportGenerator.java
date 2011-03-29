package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */
import sg.edu.nus.iss.billsys.*;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.BillMgr;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.GuiConfirmDialog;
import sg.edu.nus.iss.billsys.vo.Account;
import sg.edu.nus.iss.billsys.vo.BillPeriod;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;



import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BillingReportGenerator extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow        window;
	private JTextField           customerID;
	private List<PlanType>       listOfPlanType;
	private BillMgr manager;
	private String[] planNames;
	private PlanType planType;
	private String accountNo;
	private JPanel featurePanel;
	
	private int selectedMonth =1 ;
	private int selectedYear = 2011;
	
    public BillingReportGenerator (BillingWindow window) {
    	try
    	{
			this.window = window;
		    manager = MgrFactory.getBillMgr();
		    setLayout (new BorderLayout());
		    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		    
		    add ("North", createFormPanel());      
    	}
        catch(Exception e){
        	System.out.println(e.getMessage());
        }
    }

    public void refresh () {
    	JOptionPane.showMessageDialog(window, "refresh");
    }

    private JPanel createFormPanel () {
    	JPanel p = new JPanel (new GridLayout (0,3));

        p.add(new JLabel ("Month  :   "));
        p.add(createMonthComboBox());
        p.add(new JLabel (""));
        
        p.add(new JLabel ("Year  :   "));
        p.add(createYearComboBox());
        p.add(new JLabel (""));
        
        p.add(new JLabel (""));
        p.add(new JLabel (""));
        JButton b = new JButton ("Generate Bill");
        b.addActionListener (new ActionListener () {
        public void actionPerformed (ActionEvent e) {
        	try{	
        			BillPeriod p =manager.getNextBillPeriod();
        			manager.generate(p);
        	}
	    	catch(Exception ex)
	    	{
	    		JOptionPane.showMessageDialog(window, ex.getStackTrace());
	    	} 	
    	}
        });
        
        p.add (b);
        
        
		p.add(new JLabel("Generated Bill Period:"));
		p.add(new JLabel(""));
		p.add(new JLabel(""));
		
		BillPeriod[] billPeriods = manager.getAllGeneratedBillPeriods();

		for(BillPeriod bill:billPeriods ){
			p.add(new JLabel(bill.getBillDate()));
			p.add(new JLabel(""));
			p.add(new JLabel(""));
		}
       
       
       
        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", p);
        return bp;
    }


    private JComboBox createMonthComboBox () {  
    	
    	Calendar ca1 = Calendar.getInstance();
        int iMonth=ca1.get(Calendar.MONTH);
    	String[] months = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	    JComboBox box = new JComboBox(months);
	    
	    box.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		   JComboBox cb = (JComboBox)e.getSource();
	    		   selectedMonth = cb.getSelectedIndex() +1;
	            }
	    });
	    box.setSelectedIndex(iMonth);
	    add(box, BorderLayout.PAGE_START);
	    return box;
    }
    private JComboBox createYearComboBox () {  
    	
    	Calendar ca1 = Calendar.getInstance();
        int iYear=ca1.get(Calendar.YEAR);
        int years = iYear - 2010 + 1;

    	String[] months = new String[years];
    	for(int i = 0 ; i < years ; i++)
    	{
    		months[i] =  Integer.toString(iYear - i);
    	}
    	JComboBox box = new JComboBox(months);
	    
	    box.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		   JComboBox cb = (JComboBox)e.getSource();
	    		   selectedYear = Integer.parseInt(cb.getSelectedItem().toString());
	            }
	    });
	    box.setSelectedIndex(0);
	    add(box, BorderLayout.PAGE_START);
	    return box;
    }

}