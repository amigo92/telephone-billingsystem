package sg.edu.nus.iss.billsys.gui;
/**
 * @author Ma Huazhen
 *
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.GuiOkCancelDialog;
import sg.edu.nus.iss.billsys.util.BillingUtil;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

public class SubscriptionPlanAddFDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
    protected SubscriptionMgr manager;
        
    private JTextField fromField;
    private JTextField untilField;
    private JPanel featurePanel;
    
    private BillingWindow window;
    private String accountNo;
    private SubscriptionPlan subscription;
    
    private String planId;
    private FeatureType selectedFeatureType;
    
    private List<FeatureType> unregisteredFeatures;
     
	public SubscriptionPlanAddFDialog(BillingWindow window, String accountNo, SubscriptionPlan subscription) {
		super(window, "Register New Feature for : " + subscription.getPlanDescription());
        
		manager = MgrFactory.getSubscriptionMgr();
		
		this.window = window;
		this.accountNo = accountNo;
		this.planId = subscription.getPlanId();
		this.subscription = subscription;
		
		add ("Center", createFormPanel());
        add ("South",  createButtonPanel());
        
        fromField.setText(BillingUtil.getCurrentDateStr());
		untilField.setText(BillingUtil.getNextYearStr());
	}

	protected JPanel createFormPanel() {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 2));
		
		p.add (new JLabel("Please select: "));

		featurePanel = createFeaturePanel();
		p.add(featurePanel);  

		p.add (new JLabel ("Start Date (d-MMM-yyyy)"));
		fromField = new JTextField (20);
		p.add (fromField);
		p.add (new JLabel ("End Date (d-MMM-yyyy)"));
		untilField = new JTextField (20);
		p.add (untilField);
		
		JPanel bp = new JPanel (new BorderLayout());
      	bp.add ("North", p);
		  
		return p;
	}
	protected JPanel createFeaturePanel() {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 1));
    	List<Feature> features = subscription.getOptionalFeatures();	
		unregisteredFeatures = getUnRegisterFeature(features, manager.getPlanOptionalFeatures(subscription.getPlanType()));
		if(unregisteredFeatures != null && unregisteredFeatures.size() > 0 ){
			p.add(createFeatureComboBox(unregisteredFeatures));		
		}		
		return p;
	}
	
	 private JComboBox createFeatureComboBox (List<FeatureType> features) {  	
	    	JComboBox featureBox = new JComboBox();		
	    	
		    	if(features != null){
			    String[] featuresNames = new String[features.size()];
			    for (int i = 0 ; i <features.size(); i ++ ) {
			    	featuresNames[i] = features.get(i).name;
			    } 
			    
			    featureBox = new JComboBox(featuresNames);
			    
			    featureBox.addActionListener(new ActionListener (){
			    	public void actionPerformed (ActionEvent e) {
			    		   JComboBox cb = (JComboBox)e.getSource();
			    			   JOptionPane.showMessageDialog(window, "feature","",0);
			    			   selectedFeatureType =  unregisteredFeatures.get(cb.getSelectedIndex());
			            }
			    });
			    featureBox.setSelectedIndex(0);
	    	} 
		    add(featureBox, BorderLayout.PAGE_START);
		    return featureBox;
	    }

	protected boolean performOkAction() {
		
		Date fromDate;
		try {
			fromDate = BillingUtil.getDateTime(fromField.getText());
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(window, e1.getMessage(),"",0);
			 return false;
		}
		Date utilDate;
		try {
			utilDate = BillingUtil.getDateTime(untilField.getText());
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(window, e1.getMessage(),"",0);
			 return false;
		}
	
		try {
			manager.registerNewFeature(accountNo, planId, selectedFeatureType, fromDate, utilDate);
		} catch (BillingSystemException e) {
			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);
			return false;
		}

		return true;
	}
	
    private List<FeatureType> getUnRegisterFeature (List<Feature> regFeatures, List<FeatureType> allFeatureTypes) {
    	List<FeatureType> unRegisteredFestures = (List<FeatureType>) allFeatureTypes;
    	
    	if(regFeatures == null || regFeatures.size() == 0 ){
    		return allFeatureTypes;
    	}else{
	    	 for(Feature e: regFeatures){
	    		 unRegisteredFestures.remove(e);
    		 }
	    	return unRegisteredFestures;
    	}
    }
	
    private JPanel createButtonPanel () {
        JPanel p = new JPanel ();

        JButton b;
        ActionListener l;

        b = new JButton ("OK");
        l = new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                boolean success = performOkAction ();
                if (success) {
                    destroyDialog ();
                }
            }
        };
        b.addActionListener (l);
        p.add (b);

        b = new JButton ("Cancel");
        l = new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                destroyDialog ();
            }
        };
        b.addActionListener (l);
        p.add (b);

        return p;
    }

    private void destroyDialog () {
        setVisible (false);
        dispose();
    }

//public class SubscriptionPlanAddFDialog extends GuiOkCancelDialog {
//	private static final long serialVersionUID = 1L;
//	
//    protected SubscriptionMgr manager;
//        
//    private JTextField fromField;
//    private JTextField untilField;
//    private JPanel featurePanel;
//    
//    private BillingWindow window;
//    private String accountNo;
//    private SubscriptionPlan subscription;
//
//    
//    private String planId;
//    private FeatureType featureType;
//    private Date dateTerminated;
//    
//    private List<FeatureType> unregisteredFeatures;
//    
//
//	   
//	public SubscriptionPlanAddFDialog(BillingWindow window, String accountNo, SubscriptionPlan subscription) {
//		super(window,  "Register new feature ");
//		manager = MgrFactory.getSubscriptionMgr();
//		
//		this.window = window;
//		this.accountNo = accountNo;
//		this.planId = subscription.getPlanId();
//		this.subscription = subscription;
//		
//		featurePanel.revalidate();
//		featurePanel = createFeaturePanel();
//		featurePanel.repaint();
//		//super.add(featurePanel);
//
//	}
//
//	@Override	
//	protected JPanel createFormPanel() {
//		JPanel p = new JPanel ();
//		p.setLayout (new GridLayout (0, 2));
//
//		//p.add (assignedNumberField);
//		p.add (new JLabel ("Start Date (d-MMM-yyyy)"));
//		fromField = new JTextField (20);
//		p.add (fromField);
//		p.add (new JLabel ("End Date (d-MMM-yyyy)"));
//		untilField = new JTextField (20);
//		p.add (untilField);
//		featurePanel = new JPanel(new GridLayout (0, 1));
//		p.add(featurePanel);  
//		
//		JPanel bp = new JPanel (new BorderLayout());
//      	bp.add ("North", p);
//		  
//		return p;
//	}
//	protected JPanel createFeaturePanel() {
//		JPanel p = new JPanel ();
//		p.setLayout (new GridLayout (0, 2));
//    	List<Feature> features = subscription.getOptionalFeatures();	
//		unregisteredFeatures = getUnRegisterFeature(features, manager.getPlanOptionalFeatures(subscription.getPlanType()));
//
//		if(unregisteredFeatures != null && unregisteredFeatures.size() > 0 ){
//			//p.add(new Label("Register new feature"));
//			p.add (new JLabel("Please select: "));
//
//			p.add(createFeatureComboBox(unregisteredFeatures));		
//		}		
//		return p;
//	}
//	
//	 private JComboBox createFeatureComboBox (List<FeatureType> features) {  	
//	    	JComboBox featureBox = new JComboBox();		
//	    	
//		    	if(features != null)
//		    	{
//			    String[] featuresNames = new String[features.size() + 1];
//			    featuresNames[0] = "Please select:";
//			    for (int i = 0 ; i <features.size(); i ++ ) {
//			    	featuresNames[i+1] = features.get(i).name;
//			    } 
//			    
//			    featureBox = new JComboBox(featuresNames);
//			    
//			    featureBox.addActionListener(new ActionListener (){
//			    	public void actionPerformed (ActionEvent e) {
//			    		   JComboBox cb = (JComboBox)e.getSource();
//			    		   if(cb.getSelectedIndex() != 0){
//
//			    			   
//			    		   }
//			            }
//			    });
//			    featureBox.setSelectedIndex(0);
//
//	    	}
//		 
//		    
//		    add(featureBox, BorderLayout.PAGE_START);
//		    return featureBox;
//	    }
//
//	@Override
//	protected boolean performOkAction() {
//		JOptionPane.showMessageDialog(window, "empty","",0);
//		
//		Date fromDate;
//		try {
//			fromDate = BillingUtil.getDateTime(fromField.getText());
//		} catch (ParseException e1) {
//			JOptionPane.showMessageDialog(window, e1.getMessage(),"",0);
//			 return false;
//		}
//		Date utilDate;
//		try {
//			utilDate = BillingUtil.getDateTime(untilField.getText());
//		} catch (ParseException e1) {
//			JOptionPane.showMessageDialog(window, e1.getMessage(),"",0);
//			 return false;
//		}
//		
//		
//		
////		try {
////			
////			manager.registerNewFeature(accountNo, planId, featureType, fromDate, utilDate);
////		} catch (BillingSystemException e) {
////			// TODO Auto-generated catch block
////			JOptionPane.showMessageDialog(window, e.getMessage(),"",0);
////
////		}
//
//		return false;
//	}
//	
//    private List<FeatureType> getUnRegisterFeature (List<Feature> regFeatures, List<FeatureType> allFeatureTypes) {
//    	List<FeatureType> unRegisteredFestures = (List<FeatureType>) allFeatureTypes;
//    	
//    	if(regFeatures == null || regFeatures.size() == 0 ){
//    		return allFeatureTypes;
//    	}else{
//	    	 for(Feature e: regFeatures){
//	    		 unRegisteredFestures.remove(e);
//    		 }
//	    	return unRegisteredFestures;
//    	}
//    }
//	
//	
//	
	

}
