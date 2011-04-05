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
 * This class is a implementation class for accessing the data layer to get the feature rate information
 * It extends the genericdao to inherit the functionality of accessing the physical file and to return a 
 * raw data of two dimensional string array , which will in turn mapped to the domain objects for easy 
 * manipulation , This class also provides implementation to save back the domain object to the data file.
 * 
 * This class will be inheriting the Interface class to implement the public methods which will be used by the 
 * manager classes for the data read / update / create functionalities.
 *
 *
 */
public class FeatureRateDao extends GenericDao implements IFeatureRateDao{
	private final static String  FEATURE_RATES_DATA_FILE="data/FeatureRates.txt";//This Constant is to specify the file path to load the feature rate information
	private static final int COL_LENGTH=2;//This constant would give the number of columns expected in the file 
	private List<Rate> listFeatureRates=new ArrayList<Rate>();//Instance variable to hold the data from the parsed data of the file
	
	/*
	 * This method will implement the logic to map the raw data of two dimensional array to the Domain objects which is then used by the public methods of the Dao.
	 * (non-Javadoc)
	 * @see sg.edu.nus.iss.billsys.dao.GenericDao#objectDataMapping()
	 */
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
	
	/*
	 * The Constructor intialisation also invokes the call to map the raw data parsed from the file to domain object.
	 */
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
