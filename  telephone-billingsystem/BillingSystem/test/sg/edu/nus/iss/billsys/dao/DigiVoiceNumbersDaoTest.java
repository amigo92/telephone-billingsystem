/**
 * 
 */
package sg.edu.nus.iss.billsys.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Level;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;

/**
 * @author Veera
 *
 */
public class DigiVoiceNumbersDaoTest {
	private DigiVoiceNumbersDao digiDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			digiDao=new DigiVoiceNumbersDao();
		}catch (Exception e) {
			fail("Exception while initialising DigiVoiceNumbersDao !!");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		digiDao=null;
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.DigiVoiceNumbersDao#objectDataMapping()}.
	 */
	@Test
	public void testObjectDataMapping() {
		if(digiDao==null){
			fail("Exception while initialising DigiVoiceNumbersDao , objectDataMapping have errors !!");
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.DigiVoiceNumbersDao#saveObjectData()}.
	 */
	@Test
	public void testSaveObjectData() {
		try{
			digiDao.saveObjectData();
		}catch (Exception e) {
			fail("Exception while Saving the Object to File DigiVoiceNumbersDao.saveObjectData !! , Saving Failed.");
			BillingSystemLogger.log(Level.SEVERE, e);
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.DigiVoiceNumbersDao#getPhoneNumbers()}.
	 */
	@Test
	public void testGetPhoneNumbers() {
		assertNotNull(digiDao.getPhoneNumbers());
	}

	/**
	 * Test method for {@link sg.edu.nus.iss.billsys.dao.DigiVoiceNumbersDao#removePhoneNumber(java.lang.String)}.
	 */
	@Test
	public void testRemovePhoneNumber() {
		List<String> list = digiDao.getPhoneNumbers();
		assertNotNull(list);
		assertTrue(list.size() > 0);
		String no = list.get(0);
		assertNotNull(no);
		assertTrue(list.contains(no));
		assertTrue(digiDao.removePhoneNumber(no));
		assertFalse(list.contains(no));
	}

}
