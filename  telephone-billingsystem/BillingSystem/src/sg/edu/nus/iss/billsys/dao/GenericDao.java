package sg.edu.nus.iss.billsys.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.billsys.exception.BillingSystemException;



/**
 * 
 * @author Veera
 * This is an abstract class and the Sublclass DAO classes 
 * which taps the data should implement the abstract methods here
 * all the read / write protected methods are visible only to the subclasses
 * and the classes in the DAO package.
 *
 */
public abstract class GenericDao {
	
	/*
	 * Constants Holding the file name for the data structure.It is kept private 
	 * since the IO will be only made through this BaseDao
	 */
	
	private final static String  CALL_HISTORY_DATA_FILE="data/CallHistory.txt";
	private final static String  CUSTOMER_DATA_FILE="data/Customer.txt";
	private final static String  FEATURE_DATA_FILE="data/Feature.txt";
	private final static String  FEATURE_RATES_DATA_FILE="data/FeatureRates.txt";
	private final static String  PAYMENT_HISTORY_DATA_FILE="data/PaymentHistory.txt";
	private final static String  PLANRATES_DATA_FILE="data/PlanRates.txt";
	private final static String  SUBSCRIPTION_PLAN_DATA_FILE="data/SubscriptionPlan.txt";
	private final static String  USER_DATA_FILE="data/User.txt";
	private final static String  COMPLAINTS_DATA_FILE="data/Complaints.txt";
	
	
	private static String [][] callHistoryData=null;
	private static String [][] customerData=null;
	private static String [][] featureData=null;
	private static String [][] featureRatesData=null;
	private static String [][] paymentHistoryData=null;
	private static String [][] planRatesData=null;
	private static String [][] subscriptionPlanData=null;
	private static String [][] userData=null;
	private static String [][] complaintData=null;
	
	/*
	 * This static block will load the data to the String [][] objects which represents the
	 * data file .
	 */
	static{
		
		initializeLoadData();
		
	}
	/*
	 * This method is used to get the Data file as a two dimensional array
	 * this is a reusable methods which can be used for any data file to 2d array.
	 */
	private static String[][] getDataAsArray(String filename){
		String [][]data=null;
		try{
			FileReader fr = new FileReader(filename); 
			BufferedReader br = new BufferedReader(fr); 
				
			data=initializeArray(initializeList(br));
			
			br.close();
			fr.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	/*
	 * This method is invoked to save the information in the String [][] objects 
	 * to the data file , It is a reusable method by any data file as long as the 
	 * correct filename and the String [][] is given to it.
	 * 
	 */
	private boolean storeDataByArray(String filename , String [][] data){
		
		try {
			String firstLine=readFirstLine(filename);
		    BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		    
		    out.write(firstLine);	
	    	out.newLine();  
	    	
		    for(int i=0;i<data.length;i++){
		    	StringBuffer sb=new StringBuffer("");
		    	for(int z=0;z<data[i].length;z++){
		    		sb.append("\"");
		    		sb.append(data[i][z]);
		    		if(z<data[i].length-1)sb.append("\",");
		    		else sb.append("\"");
		    	}
		    	out.write(sb.toString());	
		    	if(i!=data.length-1)out.newLine();    	
		    }
    
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	/*
	 * This method is used to retain the first line i.e the header of each data file.
	 * This is used when we perform write operation on to the data file .
	 * 
	 */
	private String readFirstLine(String filename){
		String line=null;
		try{
			FileReader fr = new FileReader(filename); 
			BufferedReader br = new BufferedReader(fr); 
				
			line = br.readLine();
			
			
			br.close();
			fr.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return line;
	}
	
	/*
	 * This is a helper method to first initally convert the data file information on to a list
	 * since we are unsure about the number of lines in it .
	 */
	private static ArrayList<String> initializeList(BufferedReader br){
					
		String line;
		ArrayList<String> linesList=new ArrayList<String>();
				
		try{
			
		while((line = br.readLine()) != null) { 
			
			if(line!=null && line.trim().length()>0)
			linesList.add(line);
		
		}
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return linesList;
	}
	
	private static String[] parse(String line){
		return line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	}
	
	private static String removeQoutes(String cell){
		String tempCell=null;
		
		if(cell!=null && cell.length()>2){
			tempCell=cell.substring(1, cell.length()-1);
		}
		return tempCell;
	}
	/*
	 * This is a helper method to convert the list object retrieved from the file on to a String [][] 
	 * which gives precise information on the cell level detail of the data.
	 */
	private static String[][]  initializeArray(ArrayList<String> lines){
		
		int cols=parse((String)lines.get(0)).length; 
		
		
		String [][] data=new String [lines.size()-1][cols];
				
		try{
			for(int i=0;i<data.length;i++){
				String[] cell=parse((String)lines.get(i+1));
				for (int j = 0; j < cell.length; j++) {
					data[i][j]=removeQoutes(cell[j]);
				}
			
			}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/*
	 * This method is used by the static block to load all the data file on to 
	 * the String [][] object which will then used by the application for data
	 * mapping and manipulation.
	 */
		
	private static void initializeLoadData(){
		
		
		callHistoryData=getDataAsArray(CALL_HISTORY_DATA_FILE);
		customerData=getDataAsArray(CUSTOMER_DATA_FILE);
		featureData=getDataAsArray(FEATURE_DATA_FILE);
		featureRatesData=getDataAsArray(FEATURE_RATES_DATA_FILE);
		paymentHistoryData=getDataAsArray(PAYMENT_HISTORY_DATA_FILE);
		planRatesData=getDataAsArray(PLANRATES_DATA_FILE);
		subscriptionPlanData=getDataAsArray(SUBSCRIPTION_PLAN_DATA_FILE);
		userData=getDataAsArray(USER_DATA_FILE);
		complaintData=getDataAsArray(COMPLAINTS_DATA_FILE);
		
		
	}
	/*
	 * This method is used by the dao classes to validate the data type and 
	 * data format.
	 */
	protected boolean validateData(String [][] data,String dataType, int colLength) throws BillingSystemException{

		boolean valid =false;
		
		if(data==null){
			throw new BillingSystemException(dataType+" Data is Empty . Please check again");
		}else if(data!=null && data.length==0){
			throw new BillingSystemException(dataType+" Data is Empty . Please check again");
		}else if(data[0].length!=colLength)
			throw new BillingSystemException(dataType+" Data is Corrupted , Expecting "+colLength+" columns but the actual size is "+data[0].length);
		
		valid=true;
		
		return valid;
	}
	
	/*
	 * This method is used by the dao classes to map the data object with the 
	 * domain object
	 */
	protected abstract void objectDataMapping(String [][] data) throws BillingSystemException;
	
	/*
	 * This method is used by the dao classes to save the  
	 * domain object to data object for saving.
	 */
	protected abstract void saveObjectData() throws BillingSystemException;
	
	/*
	 * These are the list of protected methods used by the subclasses to get the reference to
	 * the Data object which represents the Data file.
	 */
	
	protected String[][] getCallHistoryData(){
		
		return callHistoryData;
	}	
	protected String[][] getCustomerData(){
			
		return customerData;
	}
	protected String[][] getFeatureData(){
		
		return featureData;
	}
	protected String[][] getFeatureRateData(){
		
		return featureRatesData;
	}
	protected String[][] getPaymentHistoryData(){
		
		return paymentHistoryData;
	}
	protected String[][] getPlanRatesData(){
		
		return planRatesData;
	}
	protected String[][] getSubscriptionPlanData(){
		
		return subscriptionPlanData;
	}
	protected String[][] getUserData(){
		
		return userData;
	}	
	protected String[][] getComplaintsData(){
		
		return complaintData;
	}
	/*
	 * These are the list of protected methods used by the subclasses to write the 
	 * the Data object which represents the Data file on to the Data file.
	 */
	
	protected boolean saveCallHistoryData(String[][] data){
		callHistoryData=data;
		return storeDataByArray(CALL_HISTORY_DATA_FILE, callHistoryData);
	}	
	protected boolean saveCustomerData(String[][] data){
		customerData=data;
		return storeDataByArray(CUSTOMER_DATA_FILE, customerData);
	}
	protected boolean saveFeatureData(String[][] data){
		featureData=data;
		return storeDataByArray(FEATURE_DATA_FILE, featureData);
	}
	protected boolean saveFeatureRateData(String[][] data){
		featureRatesData=data;
		return storeDataByArray(FEATURE_RATES_DATA_FILE, featureRatesData);
	}
	protected boolean savePaymentHistoryData(String[][] data){
		paymentHistoryData=data;
		return storeDataByArray(PAYMENT_HISTORY_DATA_FILE, paymentHistoryData);
	}
	protected boolean savePlanRatesData(String[][] data){
		planRatesData=data;
		return storeDataByArray(PLANRATES_DATA_FILE, planRatesData);
	}
	protected boolean saveSubscriptionPlanData(String[][] data){
		subscriptionPlanData=data;
		return storeDataByArray(SUBSCRIPTION_PLAN_DATA_FILE, subscriptionPlanData);
	}
	protected boolean saveUserData(String[][] data){
		userData=data;
		return storeDataByArray(USER_DATA_FILE, userData);
	}
	protected boolean saveComplaintsData(String[][] data){
		complaintData=data;
		return storeDataByArray(COMPLAINTS_DATA_FILE, complaintData);
	}
	

}
