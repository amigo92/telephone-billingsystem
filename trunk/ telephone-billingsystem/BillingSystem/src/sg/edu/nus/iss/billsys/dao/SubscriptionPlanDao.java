package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sg.edu.nus.iss.billsys.constant.FeatureType;
import sg.edu.nus.iss.billsys.constant.PlanType;
import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.Account;
import sg.edu.nus.iss.billsys.vo.CableTvPlan;
import sg.edu.nus.iss.billsys.vo.DigitalVoicePlan;
import sg.edu.nus.iss.billsys.vo.Feature;
import sg.edu.nus.iss.billsys.vo.MobileVoicePlan;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;


/**
 * 
 * @author Veera
 *
 */
public class SubscriptionPlanDao extends GenericDao{
	
	private List<Account> accList=new ArrayList<Account>();
	
	@Override
	protected void objectDataMapping(String[][] data) {
		
		List<Account> accList=new ArrayList<Account>();
		
		String[][] featureData =getFeatureData();		
		
		Map<String,List <SubscriptionPlan>> groupPlansByAccNo=new HashMap<String, List <SubscriptionPlan> >();
		Map<String,List<Feature> > groupfeatureByPlanId=new HashMap<String, List<Feature> >();
		
		for(int i=0;i<featureData.length;i++){
			String planid=featureData[i][1];			
			if(groupfeatureByPlanId!=null && !groupfeatureByPlanId.isEmpty()){				
				if(groupfeatureByPlanId.get(planid)!=null){					
					List <Feature> tempList=(List<Feature>)groupfeatureByPlanId.get(planid);					
					if(tempList!=null && tempList.size()>0){
						tempList.add(initialiseFeature(featureData, i));
						groupfeatureByPlanId.put(planid,tempList);
					}else{
						groupFeatureByPlanId(groupfeatureByPlanId, featureData, i);	
					}					
				}else{
					groupFeatureByPlanId(groupfeatureByPlanId, featureData, i);
				}								
			}else {				
				groupFeatureByPlanId(groupfeatureByPlanId, featureData, i);
			}   		
	    }
		
		for(int i=0;i<data.length;i++){
			String accNo=data[i][1];
			
			if(groupPlansByAccNo!=null && !groupPlansByAccNo.isEmpty()){
				
				if(groupPlansByAccNo.get(accNo)!=null){
					
					List <SubscriptionPlan> tempList=(List<SubscriptionPlan>)groupPlansByAccNo.get(accNo);
					
					if(tempList!=null && tempList.size()>0){
						try{							
							
						tempList.add(initialisePlan(data, i ,groupfeatureByPlanId));			
							
						groupPlansByAccNo.put(accNo, tempList);
						
						}
						catch(Exception ex){
							throw new RuntimeException(ex);
						}
					}else{
						groupPlansByAccNo(groupPlansByAccNo, data, i ,groupfeatureByPlanId);	
					}
					
				}else{
					groupPlansByAccNo(groupPlansByAccNo, data, i ,groupfeatureByPlanId);
				}				
				
			}else {				
				groupPlansByAccNo(groupPlansByAccNo, data, i ,groupfeatureByPlanId);
			}

			 Iterator it = groupPlansByAccNo.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pairs = (Map.Entry)it.next();
			        
			        Account acct=new Account();
			        acct.setAcctNo((String)pairs.getKey());
			        acct.setPlans((ArrayList<SubscriptionPlan>)pairs.getValue());
			        accList.add(acct);
			        
			    }
    		
	    }
	
		this.accList=accList;
		
		
		
	}
	
	public Account getAccountbyAccountNo(String accNo){
		Account tempAccount=null;
		
		for (Iterator iter = accList.iterator(); iter.hasNext();) {
			Account element = (Account) iter.next();
			if(element.getAcctNo().equals(accNo)){
				
				tempAccount=element;
				break;
				
			}
			
		}
		return tempAccount;
	}
	
	private Feature initialiseFeature(String data[][],int index){
		Feature feature=null;
		try{
			
			FeatureType[] temp=FeatureType.values();
			feature =new Feature(temp[Integer.parseInt(data[index][2])],TimeUtils.parseDate(data[index][3]),TimeUtils.parseDate(data[index][4]));
			feature.setFeatureId(data[index][0]);
			
			}
			catch(Exception ex){
				throw new RuntimeException(ex);
			}
			return feature;
		
	}
	
	private SubscriptionPlan initialisePlan(String data[][],int index,Map<String,List<Feature>> groupfeatureByPlanId){
		
		
		SubscriptionPlan plan =null;							
		if(PlanType.DigitalVoice.getPlanCd()==Integer.parseInt((data[index][3]))){
			
			plan=new DigitalVoicePlan(data[index][1],data[index][2],null,null);		
			
			if(groupfeatureByPlanId!=null && !groupfeatureByPlanId.isEmpty()){
							
				List<Feature> featureList=groupfeatureByPlanId.get(data[index][1]);
				
				if(featureList!=null && featureList.size()>0){
					
					for (Iterator iter = featureList.iterator(); iter.hasNext();) {
						try{
						Feature element = (Feature) iter.next();
						if(element.getFeatureType().equals(FeatureType.Line)){
							plan.setDateTerminated(element.getDateTerminated());
							plan.setDateCommenced(element.getDateCommenced());
						}else{						
							plan.addOptionalFeature(element);
						}
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
				
			}
			
			
					
		}else if(PlanType.MobileVoice.getPlanCd()==Integer.parseInt((data[index][3]))){
			plan=new MobileVoicePlan(data[index][1],data[index][2],null,null);
			
			if(groupfeatureByPlanId!=null && !groupfeatureByPlanId.isEmpty()){
				
				List<Feature> featureList=groupfeatureByPlanId.get(data[index][1]);
				
				if(featureList!=null && featureList.size()>0){
					
					for (Iterator iter = featureList.iterator(); iter.hasNext();) {
						try{
						Feature element = (Feature) iter.next();
						if(element.getFeatureType().equals(FeatureType.Mobile)){
							plan.setDateTerminated(element.getDateTerminated());
							plan.setDateCommenced(element.getDateCommenced());
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
			plan=new CableTvPlan(data[index][1],null,null);
			
			if(groupfeatureByPlanId!=null && !groupfeatureByPlanId.isEmpty()){
				
				List<Feature> featureList=groupfeatureByPlanId.get(data[index][1]);
				
				if(featureList!=null && featureList.size()>0){
					
					for (Iterator iter = featureList.iterator(); iter.hasNext();) {
						try{
						Feature element = (Feature) iter.next();
						if(element.getFeatureType().equals(FeatureType.StdChannels)){
							plan.setDateTerminated(element.getDateTerminated());
							plan.setDateCommenced(element.getDateCommenced());
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected boolean validateData(String[][] data) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public SubscriptionPlanDao() {
		this.objectDataMapping(getSubscriptionPlanData());
	}
	
		

}
