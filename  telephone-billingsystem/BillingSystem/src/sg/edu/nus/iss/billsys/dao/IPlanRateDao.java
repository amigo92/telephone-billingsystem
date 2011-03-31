/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.Rate;

/**
 * @author Veera
 *
 */
public interface IPlanRateDao {
	
	public Rate getPricebyPlanType(String plan_type) throws BillingSystemException;

}
