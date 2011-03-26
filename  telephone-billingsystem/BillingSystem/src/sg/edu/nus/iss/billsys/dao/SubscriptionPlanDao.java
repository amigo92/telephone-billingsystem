package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.Account;
import sg.edu.nus.iss.billsys.vo.CableTvPlan;
import sg.edu.nus.iss.billsys.vo.DigitalVoicePlan;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.MobileVoicePlan;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;
import sg.edu.nus.iss.billsys.vo.VoicePlan;


/**
 * 
 * @author Veera
 *
 */
public class SubscriptionPlanDao extends GenericDao{
	
	private Map<String,List<SubscriptionPlan>> subscriptionMap=new HashMap<String,List<SubscriptionPlan>>();
	
	@Override
	protected void objectDataMapping(String[][] data) {
		
		List<Account> accList=new ArrayList<Account>();
		
		String[][] featureData =getFeatureData();		
		
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
					try{							
						BillingSystemLogger.logInfo("tempList.size()"+tempList.size());					
						tempList.add(initialisePlan(data, i ,groupfeatureByPlanId));			
						groupPlansByAccNo.put(accNo, tempList);						
						}
					catch(Exception ex){
						throw new RuntimeException(ex);
					}
				
			}else {				
				groupPlansByAccNo(groupPlansByAccNo, data, i ,groupfeatureByPlanId);
			}
	    }
		BillingSystemLogger.logInfo("groupPlansByAccNo.size()"+groupPlansByAccNo.size());
		
		this.subscriptionMap=groupPlansByAccNo;
		
		
		
	}
	
	public Account getAccountbyAccountNo(String accNo){
		Account tempAccount=null;
		
		/*for (Iterator iter = accList.iterator(); iter.hasNext();) {
			Account element = (Account) iter.next();
			if(element.getAcctNo().equals(accNo)){
				
				tempAccount=element;
				break;
				
			}
			
		}*/
		return tempAccount;
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
	
	private Feature initialiseFeature(String data[][],int index){
		Feature feature=null;
		try{
			
			feature = new Feature(data[index][0],getFeatureTypeByCode(data[index][2]),TimeUtils.parseDate(data[index][3]),TimeUtils.parseDate(data[index][4]));
			
			}
			catch(Exception ex){
				throw new RuntimeException(ex);
			}
			return feature;
		
	}
	
	private SubscriptionPlan initialisePlan(String data[][],int index,Map<String,List<Feature>> groupfeatureByPlanId){
		
		
		SubscriptionPlan plan =null;	
		
		BillingSystemLogger.logInfo("Digital Voice - Plan Type"+data[index][3] +" Plan id "+data[index][0]);
		
		if(PlanType.DigitalVoice.getPlanCd()==Integer.parseInt((data[index][3]))){
			
			plan=new DigitalVoicePlan(data[index][0],data[index][1],data[index][2]);
			
			if(groupfeatureByPlanId!=null && !groupfeatureByPlanId.isEmpty()){
							
				List<Feature> featureList=groupfeatureByPlanId.get(data[index][0]);
				
				if(featureList!=null && featureList.size()>0){
					
					BillingSystemLogger.logInfo("Digital Voice - featureList.size()"+featureList.size());
					
					for (Iterator iter = featureList.iterator(); iter.hasNext();) {
						try{
						Feature element = (Feature) iter.next();
						BillingSystemLogger.logInfo("Digital Voice - element.getFeatureType()"+element.getFeatureType());
						if(element.getFeatureType().equals(FeatureType.Line)){
							BillingSystemLogger.logInfo("Digital Voice - Basic element.getFeatureType()"+element.getFeatureType());
							plan.setBasicFeature(element);
						}else{						
							BillingSystemLogger.logInfo("Digital Voice - Optional element.getFeatureType()"+element.getFeatureType());
							plan.addOptionalFeature(element);
						}
						}catch (Exception e) {
							e.printStackTrace();
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
						try{
						Feature element = (Feature) iter.next();
						if(element.getFeatureType().equals(FeatureType.Mobile)){
							plan.setBasicFeature(element);
						}else{						
							plan.addOptionalFeature(element);
						}
						}catch (Exception e) {
							e.printStackTrace();
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
						try{
						Feature element = (Feature) iter.next();
						if(element.getFeatureType().equals(FeatureType.StdChannels)){
							plan.setBasicFeature(element);
						}else{						
							plan.addOptionalFeature(element);
						}
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
				
			}
		}	
		return plan;
		
	}
	
	private void groupPlansByAccNo(Map<String,List<SubscriptionPlan> > map,String data[][],int index,Map<String,List<Feature>> groupfeatureByPlanId){
		try{	
					
			List <SubscriptionPlan> tempList=new ArrayList<SubscriptionPlan>();				
			tempList.add(initialisePlan(data, index,groupfeatureByPlanId));			
			
			map.put(data[index][1], tempList);
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
			
		}
	
	private void groupFeatureByPlanId(Map<String,List<Feature> > map,String data[][],int index){
	
		List <Feature> tempList=new ArrayList<Feature>();				
		tempList.add(initialiseFeature(data, index));			
		
		map.put(data[index][1], tempList);
		
	}
	
	@Override
	protected void saveObjectData() {
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
		
		String planData[][]=new String[tempSubPlanList.size()][4];
		
		for (Iterator iter = tempSubPlanList.iterator(); iter.hasNext();) {
			List<Feature> tempFeatureList=new ArrayList<Feature>();			
			
			SubscriptionPlan element = (SubscriptionPlan) iter.next();
			
			planData[cnt][0]=element.getPlanId();
			planData[cnt][1]=element.getAcctNo();
			planData[cnt][3]=String.valueOf(element.getPlanType().getPlanCd());
			if(element.getPlanType().getPlanCd()!=2){
				VoicePlan vplan=(VoicePlan)element;
				planData[cnt][2]=vplan.getAssignedTelNo();
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
		
		String featureData[][]=new String[featureCount][5];
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
					featureData[cnt][3]=TimeUtils.dateToString(element.getDateCommenced());
					featureData[cnt][4]=TimeUtils.dateToString(element.getDateTerminated());
							
					cnt++;	
							
				}
		        
		        
		    }
		
		
			saveSubscriptionPlanData(planData);
			saveFeatureData(featureData);
		
	}
	
	@Override
	protected boolean validateData(String[][] data) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public SubscriptionPlanDao() {
		this.objectDataMapping(getSubscriptionPlanData());
	}
	
	public static String generateSequence(){
		
		return UUID.randomUUID().toString();
		
	}
	

}
