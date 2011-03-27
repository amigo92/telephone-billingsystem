package sg.edu.nus.iss.billsys.vo;

import java.lang.reflect.Field;

/**
 * 
 * @author Xu Guoneng
 *
 */
public class VirtualObject {

	/**
	 * To print out all the declared fields, excluding those from super class
	 * @author Xu Guoneng
	 */
	public String toString(){
		String  details = this.getClass().getSimpleName() + "(";
		for (Field f : this.getClass().getDeclaredFields()){
			f.setAccessible(true);
			try {
				 details += "[" + f.getName() + "=" + f.get(this) + "]";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		details += ")";
		return details;
	}
}
