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
public class FeatureRateDao extends GenericDao implements IFeatureRateDao{
	private final static String  FEATURE_RATES_DATA_FILE="data/FeatureRates.txt";
	private static final int COL_LENGTH=2;
	private List<Rate> listFeatureRates=new ArrayList<Rate>();
	
	
	@Override
	protected final void objectDataMapping() throws BillingSystemException{
		String[][] data=getDataAsArray(FEATURE_RATES_DATA_FILE);
		if(validateData(data,"Feature Rate",COL_LENGTH)){
		try{
			List<Rate> listFeatureRates=new ArrayList<Rate>();
			
			for(int i=0;i<data.length;i++){
		    	
				Rate rate=new Rate();
		    	
				rate.setRate_code((data[i][0]));
				rate.setPrice(Integer.parseInt(data[i][1]));
		    	
				listFeatureRates.add(rate);	
		    }
			
			
			this.listFeatureRates=listFeatureRates;
			}
			catch(Exception ex){
				throw new RuntimeException(ex);
			}
		}
	}
	
	@Override
	protected final void saveObjectData() throws BillingSystemException{
	//This method will not be implemented , since there is no save use case for this data,only read operation is required on the Payment history
	}	
	
	protected FeatureRateDao() throws BillingSystemException{
	 this.objectDataMapping();
	}
	
	public Rate getPricebyFeatureCode(int feature_code){
		
		Rate tempRate=null;
		
		for (Iterator iter = listFeatureRates.iterator(); iter.hasNext();) {
			Rate element = (Rate) iter.next();
			
			if(element.getRate_code().equals(String.valueOf(feature_code))){
				tempRate=element;
				break;
			}
					
		}
		return tempRate;
	}
		

}
