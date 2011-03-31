/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import sg.edu.nus.iss.billsys.vo.Rate;

/**
 * @author Veera
 *
 */
public interface IFeatureRateDao {
	
	public Rate getPricebyFeatureCode(int feature_code);

}
