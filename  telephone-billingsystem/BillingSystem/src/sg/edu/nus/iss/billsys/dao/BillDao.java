package sg.edu.nus.iss.billsys.dao;

import java.io.Serializable;
import java.util.ArrayList;

import sg.edu.nus.iss.billsys.vo.*;

import java.util.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class BillDao {

	private static BillStore aBillStore;
	
	static{
		aBillStore = new BillStore();
	}
	
	public Set<BillPeriod> getAllGeneratedBillPeriods(){
		return aBillStore.getMap().keySet();
	}

	public BillPeriod getNextBillPeriod(){
		return aBillStore.getCurrBillPeriod().getNextBillPeriod();
	}
	
	public boolean isBillReady(BillPeriod billPeriod){
		BillPeriod currBp = aBillStore.getCurrBillPeriod();
		return billPeriod.getSatrtTime().before(currBp.getSatrtTime());
	}
	
	public void setCurrBillPeriod(BillPeriod currBillPeriod){
		aBillStore.setCurrBillPeriod(currBillPeriod);
	}
	
	/**
	 * 
	 * @param billPeriod
	 * @return null if the bills for that month are not yet ready
	 */
	public ArrayList<Bill> getBills(BillPeriod billPeriod){
		return aBillStore.getMap().get(billPeriod);
	}
	
	public void add(BillPeriod billPeriod, ArrayList<Bill> bills){
		aBillStore.getMap().put(billPeriod, bills);
	}
	
	private static class BillStore implements Serializable{
		private static final long serialVersionUID = -4389045505568351994L;
		
		private HashMap<BillPeriod, ArrayList<Bill>> map;
		private BillPeriod currBillPeriod;
		
		BillStore(){
			currBillPeriod = new BillPeriod(2011, 2);
			map =  new HashMap<BillPeriod, ArrayList<Bill>>();
			map.put(currBillPeriod, new ArrayList<Bill>());
		}
		
		BillPeriod getCurrBillPeriod(){
			return currBillPeriod;
		}
		
		public void setCurrBillPeriod(BillPeriod currBillPeriod) {
			this.currBillPeriod = currBillPeriod;
		}

		HashMap<BillPeriod, ArrayList<Bill>> getMap(){
			return map;
		}
	}

}
