package sg.edu.nus.iss.billsys.tools;

import java.text.*;
import java.math.*;

/**
 * To manage related elements, e.g. number formatting and GST rate
 * @author Xu Guoneng
 *
 */
public class FinanceUtils {

	public static String formatCentToDollar(int cents){
		return new DecimalFormat("0.00").format(cents/100.00);
	}
	
	public static int calGST(int amtInCent){
		return new BigDecimal(amtInCent).multiply(getGSTRate()).intValue();
	}
	
	public static BigDecimal getGSTRate(){
		return new BigDecimal("0.07");
	}

}
