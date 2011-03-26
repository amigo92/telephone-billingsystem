package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.Rate;

/**
 * 
 * @author Veera
 *
 */
public class PlanRateDao extends GenericDao{
	
	private static final int COL_LENGTH=2;
	private List<Rate> listPlanRates=new ArrayList<Rate>();
	
	
	@Override
	protected void objectDataMapping(String[][] data) throws BillingSystemException{
	
		if(validateData(data,"Plan Rate",COL_LENGTH)){
			
			List<Rate> listPlanRates=new ArrayList<Rate>();
			
			for(int i=0;i<data.length;i++){
		    	
				Rate rate=new Rate();
		    	
				rate.setRate_code((data[i][0]));
				rate.setPrice(Integer.parseInt(data[i][1]));
		    	
				listPlanRates.add(rate);	
		    }
			
			
			this.listPlanRates=listPlanRates;
		}
			
	}
	
	@Override
	protected void saveObjectData() throws BillingSystemException{
	//This method will not be implemented , since there is no save use case for this data,only read operation is required on the Payment history
	}	
	
	public PlanRateDao() throws BillingSystemException{
	 this.objectDataMapping(getPlanRatesData());
	}
	
	public Rate getPricebyPlanType(String plan_type){
		
		Rate tempRate=null;
		
		for (Iterator iter = listPlanRates.iterator(); iter.hasNext();) {
			Rate element = (Rate) iter.next();
			
			if(element.getRate_code().equals(plan_type)){
				tempRate=element;
				break;
			}
					
		}
		return tempRate;
	}
		

}
