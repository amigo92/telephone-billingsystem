package sg.edu.nus.iss.billsys.dao;

import sg.edu.nus.iss.billsys.constant.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class CallRateDao extends GenericDao {

	public CallRateDao(){
		super("C:/callrate.txt");
	}
	
	public int getRate(int planTypeCd, int callTxnTypeCd){
		return 0; //TODO
	}

}
