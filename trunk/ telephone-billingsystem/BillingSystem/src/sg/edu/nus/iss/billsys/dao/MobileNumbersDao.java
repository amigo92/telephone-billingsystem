package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * @author Lem Kian Hoa (Stephen)
 *
 */

public class MobileNumbersDao extends GenericDao implements IPhoneNumbersDao {
	
	private static final int COL_LENGTH = 1;
	private List<String> listMobileNumbers = new ArrayList<String>();

	@Override
	protected void objectDataMapping(String[][] data)
			throws BillingSystemException {
		
		if (validateData(data,"Mobile Number",COL_LENGTH)) {
			List<String> listMobileNumbers = new ArrayList<String>();
			for (int i=0;i<data.length;i++) {
				listMobileNumbers.add(data[i][0]);	
		    }
			this.listMobileNumbers = listMobileNumbers;
		}
	}

	@Override
	public void saveObjectData() throws BillingSystemException {
		
		String data[][] = new String[listMobileNumbers.size()][COL_LENGTH];
		int cnt=0;
		for (String s : listMobileNumbers) {
			data[cnt++][0]=s;
		}
		if (!saveMobileNumbersData(data)) {
			throw new BillingSystemException("Saving to File Operation Failed for MobileNumbersData");
		}
	}
	
	protected MobileNumbersDao() throws BillingSystemException {
		this.objectDataMapping(getMobileNumbersData());
	}

	public List<String> getPhoneNumbers() {
		return listMobileNumbers;
	}
	
	public boolean removePhoneNumber(String mobileNo) {
		return listMobileNumbers.remove(mobileNo);
	}
}
