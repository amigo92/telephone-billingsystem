package sg.edu.nus.iss.billsys.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.CableTvPlan;
import sg.edu.nus.iss.billsys.vo.DigitalVoicePlan;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.MobileVoicePlan;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;
import sg.edu.nus.iss.billsys.vo.VoicePlan;


/**
 * 
 * @author Veera, Lem Kian Hao (Stephen)
 * 
 * This class is a implementation class for accessing the data layer to get the Subscription information
 * It extends the genericdao to inherit the functionality of accessing the physical file and to return a 
 * raw data of two dimensional string array , which will in turn mapped to the domain objects for easy 
 * manipulation , This class also provides implementation to save back the domain object to the data file.
 * 
 * This class will be inheriting the Interface class to implement the public methods which will be used by the 
 * manager classes for the data read / update / create functionalities.
 *
 */
public class SubscriptionPlanDao extends GenericDao implements ISubscriptionPlanDao{
	
	private final static String  FEATURE_DATA_FILE="data/Feature.txt";//This Constant is to specify the file path to load the Customer Optional and Basic Feature information
	private final static String  SUBSCRIPTION_PLAN_DATA_FILE="data/SubscriptionPlan.txt";//This Constant is to specify the file path to load the Subscription information
	private static final int SUBSCRIPTION_COL_LENGTH=4; //This constant would give the number of columns expected in the Subscription file 
	private static final int FEATURE_COL_LENGTH=5; //This constant would give the number of columns expected in the Customer Optional and Basic Feature file 
	
	private Map<String,List<SubscriptionPlan>> subscriptionMap=new HashMap<String,List<SubscriptionPlan>>();//Instance variable to hold the data from the parsed data of the file
	
	/*
	 * This method will implement the logic to map the raw data of two dimensional array to the Domain objects which is then used by the public methods of the Dao.
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.GenericDao#objectDataMapping()
	 */
	@Override
	protected final void objectDataMapping() throws BillingSystemException{
		String[][] data=getDataAsArray(SUBSCRIPTION_PLAN_DATA_FILE);
		String[][] featureData =getDataAsArray(FEATURE_DATA_FILE);		
		
		if(validateData(data,"Susbscription Plan",SUBSCRIPTION_COL_LENGTH) && validateData(featureData, "Features", FEATURE_COL_LENGTH)){
		
		Map<String,List <SubscriptionPlan>> groupPlansByAccNo=new HashMap<String, List <SubscriptionPlan> >();
		Map<String,List<Feature> > groupfeatureByPlanId=new HashMap<String, List<Feature> >();
		
		for(int i=0;i<featureData.length;i++){
			String planid=featureData[i][1];			
			if(groupfeatureByPlanId!=null && !groupfeatureByPlanId.isEmpty() && groupfeatureByPlanId.get(planid)!=null){				
					List <Feature> tempList=(List<Feature>)groupfeatureByPlanId.get(planid);					
					tempList.add(initialiseFeature(featureData, i));
					BillingSystemLogger.logInfo("groupfeatureByPlanId.size() -- existing planid "+planid+" "+groupfeatureByPlanId.size());
					groupfeatureByPlanId.put(planid,tempList);									
			}else {	
				BillingSystemLogger.logInfo("groupfeatureByPlanId -- new planid");
				groupFeatureByPlanId(groupfeatureByPlanId, featureData, i);
			}   		
	    }
		BillingSystemLogger.logInfo("groupfeatureByPlanId.size()"+groupfeatureByPlanId.size());
		
		for(int i=0;i<data.length;i++){
			String accNo=data[i][1];
			
			if(groupPlansByAccNo!=null && !groupPlansByAccNo.isEmpty() && groupPlansByAccNo.get(accNo)!=null){
					List <SubscriptionPlan> tempList=(List<SubscriptionPlan>)groupPlansByAccNo.get(accNo);
					BillingSystemLogger.logInfo("tempList.size()"+tempList.size());					
					tempList.add(initialisePlan(data, i ,groupfeatureByPlanId));			
					groupPlansByAccNo.put(accNo, tempList);						
						
				
			}else {				
				groupPlansByAccNo(groupPlansByAccNo, data, i ,groupfeatureByPlanId);
			}
	    }
		BillingSystemLogger.logInfo("groupPlansByAccNo.size()"+groupPlansByAccNo.size());
		
		this.subscriptionMap=groupPlansByAccNo;
		
		}
		
	}
	
	private FeatureType getFeatureTypeByCode(String featureCode){
		
		FeatureType[] temp=FeatureType.values();
		FeatureType tempFeatureType=null;
		
		for (int i = 0; i < temp.length; i++) {
			if(String.valueOf(temp[i].getFeatureCd()).equals(featureCode))
				tempFeatureType=temp[i];
				
		}
		return tempFeatureType;
	}
	
	private Feature initialiseFeature(String data[][],int index) throws BillingSystemException{
		Feature feature=null;
		try{
			Date commenced = (data[index][3] == null) ? null : TimeUtils.parseDate(data[index][3]);
			Date terminated = (data[index][4] == null) ? null : TimeUtils.parseDate(data[index][4]);
			feature = new Feature(data[index][0],getFeatureTypeByCode(data[index][2]),commenced,terminated);
			}
			catch(ParseException ex){
				throw new BillingSystemException("Parsing Exception occured on the Date Commenced :"+data[index][3]+" and Date Terminated :"+data[index][4]+" for Feature");
			}
			return feature;
		
	}
	
	private SubscriptionPlan initialisePlan(String data[][],int index,Map<String,List<Feature>> groupfeatureByPlanId) throws BillingSystemException{
		
		
		SubscriptionPlan plan =null;	
		
		BillingSystemLogger.logInfo("Digital Voice - Plan Type"+data[index][3] +" Plan id "+data[index][0]);
		
		if(PlanType.DigitalVoice.getPlanCd()==Integer.parseInt((data[index][3]))){
			
			plan=new DigitalVoicePlan(data[index][0],data[index][1],data[index][2]);
			
			if(groupfeatureByPlanId!=null && !groupfeatureByPlanId.isEmpty()){
							
				List<Feature> featureList=groupfeatureByPlanId.get(data[index][0]);
				
				if(featureList!=null && featureList.size()>0){
					
					BillingSystemLogger.logInfo("Digital Voice - featureList.size()"+featureList.size());
					
					for (Iterator iter = featureList.iterator(); iter.hasNext();) {
						
						Feature element = (Feature) iter.next();
						BillingSystemLogger.logInfo("Digital Voice - element.getFeatureType()"+element.getFeatureType());
						if(element.getFeatureType().equals(FeatureType.Line)){
							BillingSystemLogger.logInfo("Digital Voice - Basic element.getFeatureType()"+element.getFeatureType());
							plan.setBasicFeature(element);
						}else{						
							BillingSystemLogger.logInfo("Digital Voice - Optional element.getFeatureType()"+element.getFeatureType());
							plan.addOptionalFeature(element);
						}
						
					}
					
				}
				
			}
			
			
					
		}else if(PlanType.MobileVoice.getPlanCd()==Integer.parseInt((data[index][3]))){
			plan=new MobileVoicePlan(data[index][0],data[index][1],data[index][2]);
			
			if(groupfeatureByPlanId!=null && !groupfeatureByPlanId.isEmpty()){
				
				List<Feature> featureList=groupfeatureByPlanId.get(data[index][0]);
				
				if(featureList!=null && featureList.size()>0){
					
					BillingSystemLogger.logInfo("Mobile Voice - featureList.size()"+featureList.size());
					
					for (Iterator iter = featureList.iterator(); iter.hasNext();) {
						
						Feature element = (Feature) iter.next();
						if(element.getFeatureType().equals(FeatureType.Mobile)){
							plan.setBasicFeature(element);
						}else{						
							plan.addOptionalFeature(element);
						}
						
					}
					
				}
				
			}
			
		}else if(PlanType.CableTv.getPlanCd()==Integer.parseInt((data[index][3]))){
			plan=new CableTvPlan(data[index][0],data[index][1]);
			
			if(groupfeatureByPlanId!=null && !groupfeatureByPlanId.isEmpty()){
				
				List<Feature> featureList=groupfeatureByPlanId.get(data[index][0]);
				
				if(featureList!=null && featureList.size()>0){
					
					BillingSystemLogger.logInfo("Cable TV - featureList.size()"+featureList.size());
					
					for (Iterator iter = featureList.iterator(); iter.hasNext();) {
						
						Feature element = (Feature) iter.next();
						if(element.getFeatureType().equals(FeatureType.StdChannels)){
							plan.setBasicFeature(element);
						}else{						
							plan.addOptionalFeature(element);
						}
						
					}
					
				}
				
			}
		}	
		return plan;
		
	}
	
	private void groupPlansByAccNo(Map<String,List<SubscriptionPlan> > map,String data[][],int index,Map<String,List<Feature>> groupfeatureByPlanId) throws BillingSystemException{
			
					
			List <SubscriptionPlan> tempList=new ArrayList<SubscriptionPlan>();				
			tempList.add(initialisePlan(data, index,groupfeatureByPlanId));			
			
			map.put(data[index][1], tempList);
		
			
		}
	
	private void groupFeatureByPlanId(Map<String,List<Feature> > map,String data[][],int index) throws BillingSystemException{
	
		List <Feature> tempList=new ArrayList<Feature>();				
		tempList.add(initialiseFeature(data, index));			
		
		map.put(data[index][1], tempList);
		
	}
	
	/*
	 * This method implements the data mapping back to the raw format of two dimensional array from the domain objects and 
	 * save it in to the file.
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.GenericDao#saveObjectData()
	 */
	@Override
	public final void saveObjectData() throws BillingSystemException{
		int cnt=0;	
		int featureCount=0;
		List<SubscriptionPlan> tempSubPlanList=new ArrayList<SubscriptionPlan>();
		Map<String,List<Feature>> tempFeatureMap=new HashMap<String,List<Feature>>();
		
		Iterator it = subscriptionMap.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	    	 Map.Entry pairs = (Map.Entry)it.next();
		     String accNo=(String)pairs.getKey();
		     List<SubscriptionPlan> tempList=(ArrayList<SubscriptionPlan>)pairs.getValue();
		     if(tempList!=null && tempList.size()>0){
					tempSubPlanList.addAll(tempList);
				}
	    	
	    }
		
		String planData[][]=new String[tempSubPlanList.size()][SUBSCRIPTION_COL_LENGTH];
		
		for (Iterator iter = tempSubPlanList.iterator(); iter.hasNext();) {
			List<Feature> tempFeatureList=new ArrayList<Feature>();			
			
			SubscriptionPlan element = (SubscriptionPlan) iter.next();
			
			planData[cnt][0]=element.getPlanId();
			planData[cnt][1]=element.getAcctNo();
			planData[cnt][2]="";
			planData[cnt][3]=String.valueOf(element.getPlanType().getPlanCd());
			if(element instanceof VoicePlan){
				VoicePlan vplan=(VoicePlan)element;
				if(vplan.getAssignedTelNo() != null){
					planData[cnt][2]=vplan.getAssignedTelNo();
				}
			}
			tempFeatureList.add(element.getBasicFeature());
			featureCount+=1;
			if(element.getOptionalFeatures()!=null && element.getOptionalFeatures().size()>0){
				tempFeatureList.addAll(element.getOptionalFeatures());
				featureCount+=element.getOptionalFeatures().size();
			}
			
			tempFeatureMap.put(element.getPlanId(), tempFeatureList);
			cnt++;	
					
		}
		
		String featureData[][]=new String[featureCount][FEATURE_COL_LENGTH];
		cnt=0;	
		
		 it = tempFeatureMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        String planId=(String)pairs.getKey();
		        List<Feature> tempFeatureList=(ArrayList<Feature>)pairs.getValue();
		       
		        for (Iterator iter = tempFeatureList.iterator(); iter.hasNext();) {
					Feature element = (Feature) iter.next();
					
					featureData[cnt][0]=element.getFeatureId();
					featureData[cnt][1]=planId;
					featureData[cnt][2]=String.valueOf(element.getFeatureType().getFeatureCd());
					featureData[cnt][3]=element.getDateCommenced() == null ? "" : TimeUtils.dateToString(element.getDateCommenced());
					featureData[cnt][4]=element.getDateTerminated() == null ? "" : TimeUtils.dateToString(element.getDateTerminated());
							
					cnt++;	
							
				}
		        
		        
		    }
		
		if(validateData(planData,"Susbscription Plan",SUBSCRIPTION_COL_LENGTH) && validateData(featureData, "Features", FEATURE_COL_LENGTH)){
			if(!storeDataByArray(SUBSCRIPTION_PLAN_DATA_FILE, planData))throw new BillingSystemException("Saving to File Operation Failed for SubscriptionPlanData");
			if(!storeDataByArray(FEATURE_DATA_FILE, featureData))throw new BillingSystemException("Saving to File Operation Failed for FeatureData");
		}
		
	}
	/*
	 * The Constructor intialisation also invokes the call to map the raw data parsed from the file to domain object.
	 */	
	protected SubscriptionPlanDao() throws BillingSystemException{
		this.objectDataMapping();
	}

	/*
	 * To retrieve a list of subscription plan objects of an account.
	 * @see sg.edu.nus.iss.billsys.dao.ISubscriptionPlanDao#getAccountSubscriptions(java.lang.String)
	 */
	public List<SubscriptionPlan> getAccountSubscriptions(String acctNo) {
		if (acctNo == null || acctNo.length() == 0) {
			return null;
		}
		return subscriptionMap.get(acctNo);
	}
	
	/*
	 * To retrieve subscription plan object of an account by plan id.
	 * @see sg.edu.nus.iss.billsys.dao.ISubscriptionPlanDao#getAccountSubscription(java.lang.String, java.lang.String)
	 */
	public SubscriptionPlan getAccountSubscription(String acctNo, String planId) {
		if (planId == null || planId.length() == 0) {
			return null;
		}
		List<SubscriptionPlan> list = getAccountSubscriptions(acctNo);
		if (list == null) {
			return null;
		}
		for (SubscriptionPlan plan : list) {
			if (planId.equals(plan.getPlanId())) {
				return plan;
			}
		}
		return null;
	}
	
	/*
	 * To add subscription plan object of an account into the dao 
	 * @see sg.edu.nus.iss.billsys.dao.ISubscriptionPlanDao#addAccountSubscription(java.lang.String, sg.edu.nus.iss.billsys.vo.SubscriptionPlan)
	 */
	public boolean addAccountSubscription(String acctNo, SubscriptionPlan plan) {
		if (acctNo == null || acctNo.length() == 0) {
			return false;
		}
		if (plan == null) {
			return false;
		}
		List<SubscriptionPlan> list = subscriptionMap.get(acctNo);
		if (list == null) {
			list = new ArrayList<SubscriptionPlan>(); 
			subscriptionMap.put(acctNo, list);
		}
		list.add(plan);
		return true;
	}
	

}
