package sg.edu.nus.iss.billsys.dao;

import java.io.*;
/**
 * 
 * Data Access Object
 *
 */
public abstract class GenericDao {

	protected String currWorkingFilePath;
	
	protected GenericDao(){
		this(null);
	}
	
	protected GenericDao(String filePath){
		currWorkingFilePath = filePath;
	}
	
	/**
	 * Client must close the Reader after using
	 * @return
	 * @throws FileNotFoundException
	 */
	protected BufferedReader getCurrReader() throws FileNotFoundException{
		return new BufferedReader(new FileReader(getCurrWorkingFilePath()));
	}

	public String getCurrWorkingFilePath() {
		return currWorkingFilePath;
	}

	public void setCurrWorkingFilePath(String currWorkingFilePath) {
		this.currWorkingFilePath = currWorkingFilePath;
	}
	
	
}
