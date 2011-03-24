package sg.edu.nus.iss.billsys.gui;

/**
 * @author Ma Huazhen
 *
 */

import sg.edu.nus.iss.billsys.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;

public class BillingWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private BillingSystem     manager;
    private JPanel contentPane;
    private BillingWindow window;

    private WindowListener windowListener = new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
            manager.shutdown ();
        }
    };

    public BillingWindow (BillingSystem manager) {
        super ("Billing System > Subscription");
       
        this.manager = manager;
        window = this;
        addWindowListener(windowListener);
        
        this.setJMenuBar(createMenuBar());
        contentPane = new JPanel(new BorderLayout());
        this.setContentPane(contentPane);
    }
    
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenuItem menuItem;
        JMenu menu;
        menuBar = new JMenuBar();

        menu = new JMenu("Account  ");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);   

        menuItem = new JMenuItem("item1");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	 
		     }
	    });
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menu = new JMenu("Billing  ");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);   

        menuItem = new JMenuItem("View Bill");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	 
		     }
	    });
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Generate Bill");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	 
		     }
	    });
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menu = new JMenu("Subscription  ");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);   

        menuItem = new JMenuItem("Introduction");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
			       SubscriptionIntroPanel regPanel = new SubscriptionIntroPanel (window);
		           
		           JPanel p = new JPanel ();
		           p.setLayout (new GridLayout(0, 1));
		           p.add (regPanel);
		           
		           contentPane.revalidate();
		           contentPane = regPanel;
		           window.setContentPane(contentPane);	 
		     }
	    });
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Register");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	  /* SubscriptionRegistrationPanel regPanel = new SubscriptionRegistrationPanel (window);
		           
		           JPanel p = new JPanel ();
		           p.setLayout (new GridLayout(0, 1));
		           p.add (regPanel);
		           
		           contentPane.revalidate();
		           contentPane = regPanel;
		           window.setContentPane(contentPane);*/
		         
		     }
	    });
        menu.add(menuItem);
        
        menuItem = new JMenuItem("De-Register");
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener (new ActionListener () {
		     public void actionPerformed (ActionEvent e) {
		    	 
		     }
	    });
        menu.add(menuItem);

        menu.addSeparator();

        return menuBar;
    }


   
   
  
}