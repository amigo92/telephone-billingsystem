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

import javax.swing.BorderFactory;
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
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

public class SubscriptionPlanAddFDialog extends GuiOkCancelDialog {
	private static final long serialVersionUID = 1L;
	
    protected SubscriptionMgr manager;
        
    private JTextField fromField;
    private JTextField untilField;
    private JComboBox featureBox;
    
    private BillingWindow window;
    private String accountNo;
    private SubscriptionPlan subscription;
    
    private String planId;
    private FeatureType selectedFeatureType;
    
    private List<FeatureType> unregisteredFeatures;
     
	public SubscriptionPlanAddFDialog(BillingWindow window, String accountNo, SubscriptionPlan subscription) {
		super(window, "Register New Feature for : " + subscription.getPlanDescription());
		
		this.window = window;
		this.accountNo = accountNo;
		this.planId = subscription.getPlanId();
		this.subscription = subscription;
		
		manager = window.getSubscriptionMgr();
		unregisteredFeatures = getUnRegisterFeature( );

		if(unregisteredFeatures == null || unregisteredFeatures.size() == 0){
			JPanel p = new JPanel ();
			p.setLayout (new GridLayout (0, 2));
			p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

			p.add (new JLabel("All featuers has been registered!"));
			
			add("Center",p);

		}
		else {
			fromField.setText(BillingUtil.getCurrentDateStr());
			untilField.setText(BillingUtil.getNextYearStr());
			
			for(FeatureType f: unregisteredFeatures)
				featureBox.addItem(f.name);
			
		    featureBox.setSelectedIndex(0);
		}
	
	}
	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 2));
		
		p.add (new JLabel("Please select: "));
		p.add(createFeatureComboBox()); 
		p.add (new JLabel ("Start Date (d-MMM-yyyy) *"));
		fromField = new JTextField (20);
		p.add (fromField);
		p.add (new JLabel ("End Date (d-MMM-yyyy)"));
		untilField = new JTextField (20);
		p.add (untilField);
		
		p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		return p;
	}

	 private JComboBox createFeatureComboBox () {  	
			featureBox = new JComboBox();		
					    
			featureBox.addActionListener(new ActionListener (){
				public void actionPerformed (ActionEvent e) {
					   JComboBox cb = (JComboBox)e.getSource();
						   selectedFeatureType =  unregisteredFeatures.get(cb.getSelectedIndex());
			        }
			});
		
			return featureBox;
	   }

	 @Override	
	 protected boolean performOkAction() {
		if(unregisteredFeatures != null && unregisteredFeatures.size() > 0)
		{
			Date fromDate;
			try {
				fromDate = BillingUtil.getDateTime(fromField.getText());
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(window, e1.getMessage(),"",0);
				 return false;
			}
			
			Date utilDate;
			if(StringUtil.isNullOrEmpty(untilField.getText().trim()))
				utilDate = null;
			else{
				try {
					utilDate = BillingUtil.getDateTime(untilField.getText());
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
					return false;
				}
			}
		
			if(utilDate != null && fromDate.after(utilDate)){
				JOptionPane.showMessageDialog(window, "End Date must be after Start Date ","",0);	
				return false;
			}
			
			try {
				manager.registerNewFeature(accountNo, planId, selectedFeatureType, fromDate, utilDate);
				window.refreshSubRegPanel(accountNo);
			} catch (BillingSystemException e) {
				JOptionPane.showMessageDialog(window, e.getMessage(),"",0);
				return false;
			}
		}
		return true;
	}
	
    private List<FeatureType> getUnRegisterFeature () {
    	List<FeatureType> allFeatureTypes = manager.getPlanOptionalFeatures(subscription.getPlanType());
    	List<Feature> regFeatures = subscription.getOptionalFeatures();

    	List<FeatureType> unRegisteredFestures = (List<FeatureType>) allFeatureTypes;
  	
    	if(regFeatures == null || regFeatures.size() == 0 ){
    		return allFeatureTypes;
    	}else{
	    	 for(Feature e: regFeatures){
	    		if(!e.getFeatureType().allowMultiple)
	    			 unRegisteredFestures.remove(e.getFeatureType());
    		 }
	    	return unRegisteredFestures;
    	}
    }
}
