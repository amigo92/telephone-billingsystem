package sg.edu.nus.iss.billsys.dao;

import java.util.List;

/**
 * @author Lem Kian Hoa (Stephen)
 *
 */

public interface IPhoneNumbersDao {

	public List<String> getPhoneNumbers();
	public boolean removePhoneNumber(String phoneNo);

}
