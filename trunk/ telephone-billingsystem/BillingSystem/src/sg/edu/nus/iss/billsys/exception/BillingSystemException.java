package sg.edu.nus.iss.billsys.exception;
/**
 * This is a Exception handler class used by the BillingSystem.All the method to handle exception will 
 * throw it to this class.
 * 
 * This Exception classes implementation will be done further 
 * @author Veera
 *
 */
public class BillingSystemException extends Exception{

	
	private static final long serialVersionUID = 1L;
	private Exception e=null;
	private String errorMsg;
	
	
	public BillingSystemException(Exception e) {
		super(e);
		this.e=e;
		logException(e);
	}
	
	public BillingSystemException(String msg) {
		super(msg);
		this.errorMsg=msg;
	}
		
	private String getErrorCodebyException(Exception e){
		
		return e.getMessage();
		
	}
	
	private void logException(Exception e){
		
	}
	
	public String getMessagebyException(){
		
		if(errorMsg==null)
		getErrorCodebyException(e);
		
		return errorMsg;
		
	}
	
	


}
