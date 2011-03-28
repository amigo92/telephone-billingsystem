package sg.edu.nus.iss.billsys.mgr;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;
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
	
	@Test
	public void testGetMarchBillPeriod(){
		BillPeriod bp = MgrFactory.getBillMgr().getNextBillPeriod();
		assertEquals(new BillPeriod(2011, 3), bp);
	}
	
	@Test
	public void testGenerateMarchBill() throws BillingSystemException{
		BillPeriod bp = MgrFactory.getBillMgr().getNextBillPeriod();
		MgrFactory.getBillMgr().generate(bp);
		
		assertEquals(2, MgrFactory.getBillMgr().getAllGeneratedBillPeriods().length);
	}
	
	@Test
	public void testListMarchBills() throws BillingSystemException{
		ArrayList<Bill> bills = MgrFactory.getBillMgr().getBills(new BillPeriod(2011, 3));
		for(Bill bill : bills){
			System.out.println(bill);
		}
	}
	
	@Test
	public void testGetAprilBillPeriod() throws BillingSystemException{
		BillPeriod bp = MgrFactory.getBillMgr().getNextBillPeriod();
		assertEquals(new BillPeriod(2011, 4), bp);
		
		MgrFactory.getBillMgr().generate(bp);
		assertEquals(3, MgrFactory.getBillMgr().getAllGeneratedBillPeriods().length);
	}
	
	@Test
	public void testListAprilBills() throws BillingSystemException{
		ArrayList<Bill> bills = MgrFactory.getBillMgr().getBills(new BillPeriod(2011, 4));
		for(Bill bill : bills){
			System.out.println(bill);
		}
	}
	
	@Test
	public void testWriteMarchBills() throws BillingSystemException, IOException{
		BillPeriod bp = new BillPeriod(2011, 3);
		
		ArrayList<Bill> bills = MgrFactory.getBillMgr().getBills(bp);
		MgrFactory.getBillMgr().writeBills("data/", bp, bills);
		
	}
}
