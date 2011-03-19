package sg.edu.nus.iss.billsys.resource;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Veera
 * 
 * This class is used for the resource handling , It includes label , messages and errors to be retrieved from the mentioned resource bundle by Locale. 
 * 
 */

public class ResourceHandler {
	
	private static ResourceBundle labels = null; // this object reference will hold the resource bundle containing labels
	private static ResourceBundle messages = null; // this object reference will hold the resource bundle containing messages
	private static ResourceBundle errors = null; // this object reference will hold the resource bundle containing errors
	
	private static Locale defaultLocale=Locale.ENGLISH; // for now we assume our application supports only the english language and hence setting it as default.
	
	static{
		
		try{
			 /*
			  * we assume for now our application support only English and hence the Default Locale
			  * is set to English and the same is passed as an arguement to get the resource bundle.
			  */
			 labels = ResourceBundle.getBundle("label", defaultLocale);
			 messages = ResourceBundle.getBundle("messages", defaultLocale);
			 errors = ResourceBundle.getBundle("error", defaultLocale);
					
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/*
	 * This public method is used for retrieving the resourcebundle value for labels, 
	 * this hides the implementation for loading the physical file by Locale and 
	 * respective logics
	 * 
	 */
	public static String getLabel(String key){
		
		return labels.getString(key);
	}
	
	/*
	 * This public method is used for retrieving the resourcebundle value for messages, 
	 * this hides the implementation for loading the physical file by Locale and 
	 * respective logics
	 * 
	 */
	public static String getMessages(String key){
		
		return messages.getString(key);
	}
	
	/*
	 * This public method is used for retrieving the resourcebundle value for errors, 
	 * this hides the implementation for loading the physical file by Locale and 
	 * respective logics
	 * 
	 */
	public static String getError(String key){
	
		return errors.getString(key);
	}

}
