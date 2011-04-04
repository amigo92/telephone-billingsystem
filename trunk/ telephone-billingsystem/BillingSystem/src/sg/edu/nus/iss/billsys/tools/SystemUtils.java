package sg.edu.nus.iss.billsys.tools;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class SystemUtils {

	/**
	 * 
	 * @return the company's profile
	 */
	public static CompanyProfile getCompanyProfile(){
		CompanyProfile profile = new CompanyProfile();
		
		HashMap<String, String> map = getMap("data/CompanyProfile.txt");
		
		for (Field f : CompanyProfile.class.getDeclaredFields()){
			f.setAccessible(true);
			try{
				f.set(profile, map.get(f.getName()));
			}
			catch(Exception ex){
				//do nothing
			}
		}

		return profile;
	}
	
	/**
	 * 
	 * @return a unique sequence number
	 */
	public static String generateSequence(){
		return UUID.randomUUID().toString();
	}
	
	private static HashMap<String, String> getMap(String fileName){
		HashMap<String, String> map = new HashMap<String, String>();
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(new File(fileName)));
			String s = null;
			while((s = br.readLine()) != null){
				StringTokenizer st = new StringTokenizer(s, "=");
				map.put(st.nextToken(), st.nextToken());
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
}
