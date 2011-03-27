package sg.edu.nus.iss.billsys.mgr;


import static org.junit.Assert.*;

import org.junit.*;

import sg.edu.nus.iss.billsys.vo.*;

/**
 * ]
 * @author Xu Guoneng
 *
 */
public class BillMgrTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MgrFactory.getBillMgr().purge();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFirstBillPeriod(){
		BillPeriod bp = MgrFactory.getBillMgr().getAllGeneratedBillPeriods()[0];
		assertEquals(new BillPeriod(2011, 2), bp);
	}
}
