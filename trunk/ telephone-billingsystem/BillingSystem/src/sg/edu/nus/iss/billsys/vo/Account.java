package sg.edu.nus.iss.billsys.vo;
/**
 * 
 * @author: Wen Jing
 * Mar 24 2011
 *
 */
import java.text.*;
import java.util.*;

public class Account extends VirtualObject{
	
	private String acctNo;
	private Date dateCreated;
	private Date dateDeleted;

	public Account(Date today, int nextAcct){
		this.acctNo = this.genAcctNo(today, nextAcct);
	}
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	private String genAcctNo(Date today, int nextAcct){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		NumberFormat nf = new DecimalFormat("#00");
		return  "SA-" + Integer.toString(year) + "-" + nf.format(month) + "-" + nf.format(nextAcct);
	}
	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateDeleted() {
		return dateDeleted;
	}
	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}

	public String showAccount(){
		return this.toString();
	}
}