package sg.edu.nus.iss.billsys.dao;


public class CallRateDao extends GenericDao {

	public CallRateDao(){
		super("C:/callrate.txt");
	}
	
	public int getRate(int planTypeCd, int callTxnTypeCd){
		return 0; //TODO
	}

}
