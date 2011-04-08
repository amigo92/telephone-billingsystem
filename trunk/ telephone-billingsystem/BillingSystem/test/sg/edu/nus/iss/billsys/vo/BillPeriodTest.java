package sg.edu.nus.iss.billsys.vo;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class BillPeriodTest {

	@Test
	/**
	 * to test BillPeriod's inRange()
	 */
	public void testInRange(){
		BillPeriod bp = new BillPeriod(2011, 3);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2011, 1, 10);
		assertFalse(bp.isInRange(cal.getTime()));
		
		cal.set(2011, 2, 10);
		assertTrue(bp.isInRange(cal.getTime()));
		
		cal.set(2011, 6, 10);
		assertFalse(bp.isInRange(cal.getTime()));
	}
	
	@Test
	/**
	 * to test BillPeriod's isOverlapped()
	 */
	public void testIsOverlapped(){
		BillPeriod bp = new BillPeriod(2011, 3);
		Date sDate = new Date();
		Date eDate = new Date();
		
		Calendar cal = Calendar.getInstance();
		
		cal.clear();
		cal.set(2011, 1, 10);
		sDate = cal.getTime();
		cal.clear();
		cal.set(2011, 1, 26);
		eDate = cal.getTime();
		assertFalse(bp.isOverlapped(sDate, eDate));
		
		cal.set(2011, 1, 10);
		sDate = cal.getTime();
		cal.set(2011, 2, 20);
		eDate = cal.getTime();
		assertTrue(bp.isOverlapped(sDate, eDate));
		
		cal.set(2011, 2, 10);
		sDate = cal.getTime();
		cal.set(2011, 2, 20);
		eDate = cal.getTime();
		assertTrue(bp.isOverlapped(sDate, eDate));
		
		cal.set(2011, 2, 10);
		sDate = cal.getTime();
		cal.set(2011, 3, 20);
		eDate = cal.getTime();
		assertTrue(bp.isOverlapped(sDate, eDate));
		
		cal.set(2011, 3, 10);
		sDate = cal.getTime();
		cal.set(2011, 4, 20);
		eDate = cal.getTime();
		assertFalse(bp.isOverlapped(sDate, eDate));
	}
}
