package sg.edu.nus.iss.billsys.dao;


public class CallRateDao extends GenericDao {
@Override
protected void objectDataMapping(String[][] data) {
	// TODO Auto-generated method stub
	
}
@Override
protected void saveObjectData() {
	// TODO Auto-generated method stub
	
}
@Override
protected boolean validateData(String[][] data) {
	// TODO Auto-generated method stub
	return false;
}

	public CallRateDao(){
		//super("C:/callrate.txt");
	}
	
	public int getRate(int planTypeCd, int callTxnTypeCd){
		return 0; //TODO
	}

}
