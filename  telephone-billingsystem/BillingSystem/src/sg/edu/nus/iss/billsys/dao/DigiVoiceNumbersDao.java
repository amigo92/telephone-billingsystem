package sg.edu.nus.iss.billsys.dao;

import java.util.ArrayList;
import java.util.List;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;

/**
 * @author Lem Kian Hoa (Stephen)
 *
 */

public class DigiVoiceNumbersDao extends GenericDao implements IPhoneNumbersDao {
	
	private final static String  DIGIVOICE_NUMBERS_DATA_FILE="data/DigiVoiceNumbers.txt";
	private static final int COL_LENGTH = 1;
	private List<String> listNumbers = new ArrayList<String>();

	@Override
	protected void objectDataMapping()
			throws BillingSystemException {
		String[][] data=getDataAsArray(DIGIVOICE_NUMBERS_DATA_FILE);
		if (validateData(data,"DigiVoice Number",COL_LENGTH)) {
			List<String> listNumbers = new ArrayList<String>();
			for (int i=0;i<data.length;i++) {
				listNumbers.add(data[i][0]);	
		    }
			this.listNumbers = listNumbers;
		}
	}

	@Override
	public void saveObjectData() throws BillingSystemException {
		
		String data[][] = new String[listNumbers.size()][COL_LENGTH];
		int cnt=0;
		for (String s : listNumbers) {
			data[cnt++][0]=s;
		}
		if (!storeDataByArray(DIGIVOICE_NUMBERS_DATA_FILE, data)) {
			throw new BillingSystemException("Saving to File Operation Failed for DigiVoiceNumbersData");
		}
	}
	
	protected DigiVoiceNumbersDao() throws BillingSystemException {
		this.objectDataMapping();
	}

	public List<String> getPhoneNumbers() {
		return listNumbers;
	}
	
	public boolean removePhoneNumber(String phoneNo) {
		return listNumbers.remove(phoneNo);
	}
}
