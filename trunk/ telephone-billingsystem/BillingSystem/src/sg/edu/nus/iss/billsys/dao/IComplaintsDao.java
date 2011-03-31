/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import java.util.List;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.vo.CustComplaint;

/**
 * @author Veera
 *
 */
public interface IComplaintsDao {
	
	public void saveObjectData() throws BillingSystemException;
	public List<CustComplaint> getComplaintList(String accNo);
	public String addComplaint(CustComplaint obj) throws BillingSystemException;
	public int updateComplaint(CustComplaint obj)  throws BillingSystemException;
	public CustComplaint getComplaint(String complaintId);
	

}
