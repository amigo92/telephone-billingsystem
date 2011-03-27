package sg.edu.nus.iss.billsys.dao;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import sg.edu.nus.iss.billsys.vo.*;

import java.util.*;
import java.io.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class BillDao {

	private static BillStore aBillStore;
	
	static{
		FileInputStream fis = null;
		try{
			try{
				fis = new FileInputStream(new File(getFilepath()));
				ObjectInputStream ois = new ObjectInputStream(fis);
				aBillStore = (BillStore)ois.readObject();
				
				if(aBillStore == null){
					aBillStore = new BillStore();
				}
				
				ois.close();
			}
			catch(java.io.EOFException ex){
				aBillStore = new BillStore();
			}
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
	
	public void purge(){
		aBillStore = new BillStore();
		save();
	}
	
	public static void save(){
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(new File(getFilepath()));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(aBillStore);
			oos.close();
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static String getFilepath(){
		return "data/BillStore.ser";
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
