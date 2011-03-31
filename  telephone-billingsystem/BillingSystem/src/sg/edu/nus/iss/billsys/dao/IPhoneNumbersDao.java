package sg.edu.nus.iss.billsys.dao;

import java.util.List;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * @author Lem Kian Hoa (Stephen)
 *
 */

public interface IPhoneNumbersDao {

	public List<String> getPhoneNumbers();
	public boolean removePhoneNumber(String phoneNo);
	public void saveObjectData() throws BillingSystemException;
}
