package sg.edu.nus.iss.billsys.exception;

import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.resource.ResourceHandler;

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
	private Exception e=null;//to hold the instance of the exception object passed as an argument.
	private String errorMsg;// to hold the message corresponding to the exception passed as an argument.
	
	/*
	 * To create a instance of BillingSystemException with a exception .
	 */
	public BillingSystemException(Exception e) {
		super(e);
		this.e=e;
		logException(e);
	}
	
	/*
	 * To create a instance of BillingSystemException with a message or resource key.
	 */
	public BillingSystemException(String msg) {
		super(msg);
		this.errorMsg=msg;
		BillingSystemLogger.logSevere("New Billing Exception Initialised with the following message : "+getMessagebyException());
	}
	
	/*
	 * To have more centralised controled over what message needs to be given for the exception 
	 * for now we are returning the message of the exception and for anypoint of time we can make it a generic one 
	 * for all the exceptions so we can hide the exception details and show a friendly error .
	 * 
	 */
	private String getErrorCodebyException(Exception e){
		
		return e.getMessage();
		
	}
	/*
	 * To log the exception
	 */
	private void logException(Exception e){
		e.printStackTrace();
		BillingSystemLogger.logSevere(e);
		
	}
	/*
	 * To return a message respective to the instance of this exception , either by the passes in exception or by the message passed in as an argument.
	 */
	public String getMessagebyException(){
		
		if(errorMsg==null)
		getErrorCodebyException(e);
		
		if(errorMsg!=null){
			try{
				errorMsg=ResourceHandler.getError(errorMsg);
			}catch (Exception e) {
				BillingSystemLogger.logInfo("Cannot find the error key inside resource file , hence returning the same string");
			}
		}
		
		return errorMsg;
		
	}
	
	


}
