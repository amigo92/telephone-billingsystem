package sg.edu.nus.iss.billsys.gui;

import sg.edu.nus.iss.billsys.*;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.GuiConfirmDialog;
import sg.edu.nus.iss.billsys.vo.Account;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;



import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SubscriptionIntroPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BillingWindow        window;
	private SubscriptionMgr      manager;

    public SubscriptionIntroPanel (BillingWindow window) {
 
    	this.window = window;
        manager = MgrFactory.getSubscriptionMgr();
        setLayout (new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        add ("North", createFormPanel());      
 
        }


    private JPanel createFormPanel () {
    	JPanel p = new JPanel (new GridLayout (0,2));


        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", new JLabel ("Introduction of Subcription Plan:   "));
        bp.add ("Center", p);
        return bp;
    }
}