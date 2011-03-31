/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import java.util.List;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.SubscriptionPlan;

/**
 * @author Veera
 *
 */
public interface ISubscriptionPlanDao {
	
	public boolean addAccountSubscription(String acctNo, SubscriptionPlan plan);
	public SubscriptionPlan getAccountSubscription(String acctNo, String planId) ;
	public List<SubscriptionPlan> getAccountSubscriptions(String acctNo) ;
	public String generateSequence();
	public void saveObjectData() throws BillingSystemException;

}
