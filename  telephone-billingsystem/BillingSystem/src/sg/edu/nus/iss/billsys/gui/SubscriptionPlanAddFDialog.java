package sg.edu.nus.iss.billsys.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.SubscriptionMgr;
import sg.edu.nus.iss.billsys.tools.GuiOkCancelDialog;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.util.StringUtil;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;
/**
 * @author Ma Huazhen
 *
 */
public class SubscriptionPlanAddFDialog extends GuiOkCancelDialog implements ItemListener{
	private static final long serialVersionUID = 1L;
	
    protected SubscriptionMgr manager;
        
    private JTextField fromField;
    private JTextField untilField;
    private JPanel checkPanel;

    private ArrayList<String> selectedFeatures;
    private List<FeatureType> unregisteredFeatures;
    private List<String> featureNames;
    
    private BillingWindow window;
    private String accountNo;
    private SubscriptionPlan subscription;
    
    private String planId;
    
     
	public SubscriptionPlanAddFDialog(BillingWindow window, String accountNo, SubscriptionPlan subscription) {
		super(window, "Register New Feature for : " + subscription.getPlanDescription());
		
		this.window = window;
		this.accountNo = accountNo;
		this.planId = subscription.getPlanId();
		this.subscription = subscription;
		
		manager = window.getSubscriptionMgr();
		
		unregisteredFeatures = getAvailableFeatures( );
		
//		No feature available for registration
		if(unregisteredFeatures == null || unregisteredFeatures.size() == 0){
			JPanel p = new JPanel ();
			p.setLayout (new GridLayout (0, 2));
			p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

			p.add (new JLabel("All featuers has been registered!"));
			
			add("Center",p);

		}
		else {
			
			// Initialize the start date field.
			fromField.setText(TimeUtils.getCurrentDateStr());
			if(subscription.getDateTerminated() != null){
				untilField.setText(TimeUtils.getDateTimeStr(subscription.getDateTerminated()));
			}
			
			// Initialize available features's check boxes
			selectedFeatures = new ArrayList<String>();
			featureNames = new ArrayList<String>();
			JCheckBox cb;
			
	    	for(FeatureType ft:unregisteredFeatures)
	    	{
	    		if(ft.allowMultiple){
	    			for(int i = 0 ; i < 1 ;i++){
	    				cb = new JCheckBox(ft.name);
	    				cb.addItemListener(this);
	    				checkPanel.add(cb);
	    			}	
	    		}else{
					cb = new JCheckBox(ft.name);
					cb.addItemListener(this);
					checkPanel.add(cb);
	    		}	
				featureNames.add(ft.name);
	    	}
		}
	}
	@Override
	/**
	 * Main Form Panel
	 */
	protected JPanel createFormPanel() {
		JPanel p = new JPanel ();
		p.setLayout (new GridLayout (0, 2));
		
		p.add (new JLabel("Please select: "));
		checkPanel = new JPanel(new FlowLayout());
		p.add (checkPanel);
		
		p.add (new JLabel ("Start Date (d-MMM-yyyy) *"));
		fromField = new JTextField (20);
		p.add (fromField);
		p.add (new JLabel ("End Date (d-MMM-yyyy)"));
		untilField = new JTextField (20);
		p.add (untilField);
		
		p.add (new JLabel ("* compulsory field"));
		p.add (new JLabel (""));

		p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		return p;
	}
	/**
	 * Add or remove feature from selected feature list
	 * */
	public void itemStateChanged(ItemEvent e) {
        JCheckBox source = (JCheckBox)e.getSource();

    	String featureName =source.getText();

        if (e.getStateChange() == ItemEvent.DESELECTED) {
        	if(selectedFeatures.contains(featureName))
        		selectedFeatures.remove(featureName);
        }
        else{
        	if(!selectedFeatures.contains(featureName))
        		selectedFeatures.add(featureName);
        }
    }
	 @Override	
	 /**
		 * Click OK button
		 */
	 protected boolean performOkAction() {
		if(unregisteredFeatures != null && unregisteredFeatures.size() > 0)
		{
//			Validation
			if(StringUtil.isNullOrEmpty(fromField.getText())){	
				JOptionPane.showMessageDialog(window, "Start Date can't be empty!","",0);	
				 return false;
			}
			
			Date fromDate;
			try {				
				fromDate = TimeUtils.getDateTime(fromField.getText());		
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(window, e1.getMessage(),"",0);
				 return false;
			}

			Date utilDate;
			if(StringUtil.isNullOrEmpty(untilField.getText()))
			{

//				If feature end date is empty, it will follow subscription plan's end date
				if (subscription.getDateTerminated() != null)
					utilDate = subscription.getDateTerminated();
				else
					utilDate = null;
			}
			else{
				try {		
						utilDate = TimeUtils.getDateTime(untilField.getText());
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(window, e.getMessage(),"",0);	
					return false;
				}
			}
			
			if(utilDate != null && subscription.getDateTerminated() != null && utilDate.after(subscription.getDateTerminated()) ){
				JOptionPane.showMessageDialog(window, "Feature's End Date must be equal or before plan's End Date","",2);	
				return false;	
			} 
			
			if(utilDate != null && fromDate.after(utilDate)){
				JOptionPane.showMessageDialog(window, "End Date must be after Start Date ","",2);	
				return false;
			}
			
			try {
				// Register new features
				
				if(selectedFeatures !=null && selectedFeatures.size() >0 ){	
					for(String f : selectedFeatures ){
						int index = featureNames.indexOf(f);
						FeatureType selectedFeatureType = unregisteredFeatures.get(index);
						manager.registerNewFeature(accountNo, planId, selectedFeatureType, fromDate, utilDate);
					}
				}
				else{
					JOptionPane.showMessageDialog(window, "No feature selected!","",2);	
					return false;
				}
				window.refreshSubRegPanel(accountNo);
			} catch (BillingSystemException e) {
				JOptionPane.showMessageDialog(window, e.getMessagebyException(),"Error",0);
				return false;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(window, e.getMessage(),"Error",0);
				return false;
			}
		}
		return true;
	}
	/**
	 *Get list of available features for registration
	 *except for Cable TV's feature "Additional channel" 
	 *which can be registered for multiple times 
	 **/
    private List<FeatureType> getAvailableFeatures() {
    	List<FeatureType> allFeatureTypes = manager.getPlanOptionalFeatures(subscription.getPlanType());
    	List<Feature> regFeatures = subscription.getOptionalFeatures();

    	List<FeatureType> availableFeatures = (List<FeatureType>) allFeatureTypes;
  	
    	if(regFeatures == null || regFeatures.size() == 0 ){
    		return allFeatureTypes;
    	}else{
	    	 for(Feature e: regFeatures){
	    		if(!e.getFeatureType().allowMultiple)
	    		{
	    			if(!e.isTerminated())
	    				availableFeatures.remove(e.getFeatureType());
	    		}
    		 }
	    	return availableFeatures;
    	}
    }
}
