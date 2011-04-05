package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * @author Lem Kian Hoa (Stephen)
 *
 */

public class MobileNumbersDao extends GenericDao implements IPhoneNumbersDao {
	
	private final static String  MOBILE_NUMBERS_DATA_FILE="data/MobileNumbers.txt";
	private static final int COL_LENGTH = 1;
	private List<String> listMobileNumbers = new ArrayList<String>();

	@Override
	protected void objectDataMapping()
			throws BillingSystemException {
		String[][] data=getDataAsArray(MOBILE_NUMBERS_DATA_FILE);
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
		if (!storeDataByArray(MOBILE_NUMBERS_DATA_FILE, data)) {
			throw new BillingSystemException("Saving to File Operation Failed for MobileNumbersData");
		}
	}
	
	protected MobileNumbersDao() throws BillingSystemException {
		this.objectDataMapping();
	}

	/*
	 * To retrieve a list of mobile voice numbers from the dao.
	 * @see sg.edu.nus.iss.billsys.dao.IPhoneNumbersDao#getPhoneNumbers()
	 */
	public List<String> getPhoneNumbers() {
		return listMobileNumbers;
	}
	
	/*
	 * To remove mobile voice number from the dao.
	 * @see sg.edu.nus.iss.billsys.dao.IPhoneNumbersDao#removePhoneNumber(java.lang.String)
	 */
	public boolean removePhoneNumber(String mobileNo) {
		return listMobileNumbers.remove(mobileNo);
	}
}
