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
	 * This method is used to get the Data file as a two dimensional array
	 * this is a reusable methods which can be used for any data file to 2d array.
	 */
	protected final String[][] getDataAsArray(String filename){
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
	protected final boolean storeDataByArray(String filename , String [][] data){
		
		try {
			String firstLine=readFirstLine(filename);
		    BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		    
		    out.write(firstLine);	
	    	out.newLine();  
	    	
		    for(int i=0;i<data.length;i++){
		    	StringBuffer sb=new StringBuffer("");
		    	for(int z=0;z<data[i].length;z++){
		    		sb.append("\"");
		    		sb.append(data[i][z]!=null?data[i][z].replaceAll("\\r|\\n", " "):data[i][z]);
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
	protected abstract void objectDataMapping() throws BillingSystemException;
	
	/*
	 * This method is used by the dao classes to save the  
	 * domain object to data object for saving.
	 */
	protected abstract void saveObjectData() throws BillingSystemException;


}
