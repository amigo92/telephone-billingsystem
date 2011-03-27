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
import sg.edu.nus.iss.billsys.vo.Bill;
import sg.edu.nus.iss.billsys.vo.BillPeriod;
import sg.edu.nus.iss.billsys.vo.CompanyProfile;
import sg.edu.nus.iss.billsys.vo.Customer;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;
import sg.edu.nus.iss.billsys.vo.TextBill;
import sg.edu.nus.iss.billsys.vo.Bill.DetailCharges;
import sg.edu.nus.iss.billsys.vo.Bill.Entry;
import sg.edu.nus.iss.billsys.vo.Bill.SummaryCharges;



import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BillingReportView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow        window;
	private JTextField           customerID;
	private String accountNo;
	private JPanel reportPanel;
	private int selectedMonth =1 ;
	private int selectedYear = 2011;
	private BillMgr manager;
	
    public BillingReportView (BillingWindow window) {
    	try
    	{
			this.window = window;
		    manager = MgrFactory.getBillMgr();
		    setLayout (new BorderLayout());
		    setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		    
		    add ("North", createFormPanel());      

		    reportPanel = createReportPanel();
		    add ("Center", reportPanel);
        
    	}
        catch(Exception e){
        	System.out.println(e.getMessage());
        }
    }
    private JPanel createFormPanel () {
    	JPanel p = new JPanel (new GridLayout (0,3));
    
    	p.add(new JLabel ("Customer ID:   "));
        customerID = new JTextField (1);
        p.add(customerID);
        p.add(new JLabel (""));
        
        p.add(new JLabel ("Month  :   "));
        p.add(createMonthComboBox());
        p.add(new JLabel (""));
        
        p.add(new JLabel ("Year  :   "));
        p.add(createYearComboBox());
        p.add(new JLabel (""));
  
        p.add(new JLabel (""));
        p.add(new JLabel (""));
        JButton b = new JButton ("View");
        b.addActionListener (new ActionListener () {
        public void actionPerformed (ActionEvent e) {
        	try{	
	    	    //accountNo = MgrFactory.getAccountMgr().getCustomerDetailsById(customerID.getText());
        		//accountNo = "acc_no1";
        		reportPanel.revalidate();
        		reportPanel = createReportPanel();
        		add ("Center", reportPanel);
        	}
	    	catch(Exception ex)
	    	{
	    		JOptionPane.showMessageDialog(window, ex.getMessage());
	    	} 	
    	}
        });
        
//        FlowLayout flowLayout = new FlowLayout();
//        flowLayout.setAlignment(FlowLayout.LEFT);
//        JPanel btp = new JPanel(flowLayout);
//        b.setSize(25, 25);
//        btp.add(b);
//        p.add (btp);
        
        p.add(b);

        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());   
        bp.add ("North", p);     
        return bp;
    }
    private JPanel createReportPanel () {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 1));
		   
		if(accountNo != null){
		  }
		
		BillPeriod billPeriod = new BillPeriod(selectedMonth,selectedYear);
		
		//manager.
		//manager.getAllGeneratedBillPeriods();
		
		
		//Bill bill =	manager.getBill(billPeriod, "SA-2011-03-25-8481361");
	
    
    
  
//        if(bill != null){
//        	bp.add ("North", new JLabel ("Billing Report:"));
//        	bp.add ("Center", new JLabel (bill.toString()));
//        }
    	
		JTextArea textBill = new JTextArea (testBill());
		textBill.setEditable(false);
		
		Font font = new Font("Verdana", Font.PLAIN, 12);
		textBill.setFont(font);
		//textBill.setForeground(Color.BLUE);

		textBill.setBackground(Color.lightGray);
       
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add( textBill );
		
		JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
		bp.add ("North", new JLabel ("Billing Report:"));
		bp.add( "Center", scrollPane );       
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
	    		   int selectedMonth = cb.getSelectedIndex() +1;
	            }
	    });
	    box.setSelectedIndex(iMonth);
	    add(box, BorderLayout.PAGE_START);
	    return box;
    }
 private JComboBox createYearComboBox () {  
    	
    	Calendar ca1 = Calendar.getInstance();
        int iYear=ca1.get(Calendar.YEAR);
        int years = iYear - 2011 + 1;

    	String[] months = new String[years];
    	for(int i = 0 ; i < years ; i++)
    	{
    		months[i] =  Integer.toString(iYear - i);
    	}
    	JComboBox box = new JComboBox(months);
	    
	    box.addActionListener(new ActionListener (){
	    	public void actionPerformed (ActionEvent e) {
	    		   JComboBox cb = (JComboBox)e.getSource();
	    		    int selectedYear = Integer.parseInt(cb.getSelectedItem().toString());
	            }
	    });
	    box.setSelectedIndex(0);
	    add(box, BorderLayout.PAGE_START);
	    return box;
    }
 
 public String testBill() {
		CompanyProfile profile = new CompanyProfile();
		profile.setAlias("One#");
		profile.setCompanyName("One# Pte Ltd");
		profile.setStreet("41 Uni Lane");
		profile.setUnit("#02-003");
		profile.setCountry("Singapore");
		profile.setPostalCd("654092");
		profile.setHotline("1654");
		profile.setEmail("customer@onehash.com");

		Customer cust = new Customer();
		cust.setName("Mohamed Ali Khan");
		cust.setAddress1("35, Heng Mui Keng Towers");
		cust.setAddress2("Clemence Avenue 1");
		cust.setAddress3("Singapore 356908");
		
		TextBill bill = new TextBill(80,65);
		bill.setaCompanyProfile(profile);
		bill.setaCustomer(cust);
		bill.setAcctNo("SA-2010-02-10");
		bill.setBillDate(new Date());
		bill.setDueDate(new Date());
		bill.setPreviousBalance(20080);
		bill.setTotalPaymentMade(20080);
		bill.setCurrChargesDue(32346);
		bill.setTotalCurrCharges(32346);
		bill.setTotalGST(2116);
		bill.addPaymentReceived(new Date(), 10080);
		bill.addPaymentReceived(new Date(), 10000);

		SummaryCharges charges = bill.new SummaryCharges();
		charges.setDesc("Digital Voice");
		charges.setTotalAmt(2730);
		charges.addEntry(bill.new Entry("Subscription charges",1500));
		charges.addEntry(bill.new Entry("Usage charges",1230));
		bill.addSummaryCharges(charges);

		charges = bill.new SummaryCharges();
		charges.setDesc("Mobile Voice");
		charges.setTotalAmt(20450);
		charges.addEntry(bill.new Entry("Subscription charges",13420));
		charges.addEntry(bill.new Entry("Usage charges",7030));
		bill.addSummaryCharges(charges);

		charges = bill.new SummaryCharges();
		charges.setDesc("Cable TV");
		charges.setTotalAmt(5050);
		charges.addEntry(bill.new Entry("Subscription charges",2020));
		charges.addEntry(bill.new Entry("Usage charges",7070));
		bill.addSummaryCharges(charges);
		
		DetailCharges dc = bill.new DetailCharges();
		dc.setDesc("Digital Voice Number 66178954");
		dc.setTotalAmt(2730);
		dc.addEntry(bill.new Entry("Subscription Charges",null));
		dc.addEntry(bill.new Entry("Line",1000));
		dc.addEntry(bill.new Entry("Call Transfer",500));
		dc.addEntry(bill.new Entry("Usage Charges",null));
		dc.addEntry(bill.new Entry("Local Calls",230));
		dc.addEntry(bill.new Entry("IDD Calls",1000));
		bill.addDetailChargesList(dc);
		
		dc = bill.new DetailCharges();
		dc.setDesc("Mobile Number 98760090");
		dc.setTotalAmt(20450);
		dc.addEntry(bill.new Entry("Subscription Charges",null));
		dc.addEntry(bill.new Entry("Mobile",10000));
		dc.addEntry(bill.new Entry("Roaming",400));
		dc.addEntry(bill.new Entry("Data Services",3020));
		dc.addEntry(bill.new Entry("Usage Charges",null));
		dc.addEntry(bill.new Entry("Local Calls",2200));
		dc.addEntry(bill.new Entry("IDD Calls",2800));
		dc.addEntry(bill.new Entry("Roaming Calls",2030));
		bill.addDetailChargesList(dc);
		
		dc = bill.new DetailCharges();
		dc.setDesc("Cable TV");
		dc.setTotalAmt(7050);
		dc.addEntry(bill.new Entry("Subscription Charges",5050));
		dc.addEntry(bill.new Entry("Add. Channel changes ($5x4)",2000));
		bill.addDetailChargesList(dc);
		
		System.out.println(bill);
		return bill.toString();
	}



}