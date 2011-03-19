package sg.edu.nus.iss.billsys.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;



/**
 * @author Veera
 * 
 * This is a logger class for the application which would be used by the classes with in this project to log any errors , exceptions , info , debug  statements
 * 
 */

public class BillingSystemLogger {
	
	private static final String LOG_FILEPATH="BillingSystem.log";//specifies the path and file name of the log file.
	
	private static final Level LOG_LEVEL=Level.ALL;//specifies the filter level of the logs , which will be printed to the file . 
	
	private static final boolean FILE_APPEND = true;// specifies that the file will be appended and not created for every instance.
	
	private static Logger logger; // the logger object which will hold the handler and the formatting implementation.s
	
	static {
		/*
		 * This code block implements the logging mechanism by specifying the format to be used
		 * the file name and path for the log file , and the filter level for logging.
		 * 
		 */
	    try {
	      
	      FileHandler fh = new FileHandler(LOG_FILEPATH, FILE_APPEND);
	      
	      fh.setFormatter(logFormat());
	      
	      logger = Logger.getLogger("BillingSystemLogger");
	      logger.setLevel(LOG_LEVEL);
	      logger.addHandler(fh);
	      
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	}

	/*
	 * This public method gives an option to log the Severe /Errors / Exception level Object information.
	 */
	public static void logSevere(Object log){
		logger.severe(log.toString());
	}
	
	/*
	 * This public method gives an option to log the Info level Object information.
	 */
	public static void logInfo(Object log){
		logger.info(log.toString());
	}
	
	/*
	 * This public method gives an option to specify the loglevel and the Object info to be logged.
	 */
	
	public static void log(Level level , Object log){
		logger.log(level, log.toString());
	}
	
	
	/*
	 * specifies the format of the log file , we can change the implementation of this method to have a different 
	 * file format of the log eg : simpleformatter , xmlformatter.
	 * 
	 */
	private static Formatter logFormat(){
		
		return new Formatter() {
	          public String format(LogRecord rec) {
		             StringBuffer buf = new StringBuffer(1000);
		             buf.append(new java.util.Date());
		             buf.append(" : ");
		             buf.append(rec.getLevel());
		             buf.append(" : ");
		             buf.append(formatMessage(rec));
		             buf.append('\n');
		             return buf.toString();
		             }
		           };
	}
	
	
	}
	
