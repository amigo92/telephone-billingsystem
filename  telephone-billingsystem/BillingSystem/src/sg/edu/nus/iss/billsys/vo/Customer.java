package sg.edu.nus.iss.billsys.vo;

import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author: Wen Jing
 * Mar 24 2011
 *
 */
public class Customer extends VirtualObject implements Cloneable, Serializable{

	private static final long serialVersionUID = 6206122414964453622L;
	
	private String name;
	private String address1;
	private String address2;
	private String address3;
	private String contactTel;
	private String interest;
	private boolean isDeleted;
	private String nric;
	private transient Account acct;
	
	public Customer(){
		super();
	}
	
	public Customer(String name, String address1, String address2, String address3, String contactTel, String interest, String nric){
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.contactTel = contactTel;
		this.interest = interest;
		this.nric = nric;
		this.acct = new Account();
	}
	
	public Account getAcct() {
		return acct;
	}

	public void setAcct(Account acct) {
		this.acct = acct;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}


	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public boolean isDeleted() {

		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public void setIsDeleted(boolean isDeleted, Date today){
		this.isDeleted = isDeleted;
		this.acct.setDateDeleted(today);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}
	
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
	}
	
	public String getAccountId(){
		return this.acct.getAcctNo();
	}
	public String getAddress(){
		return this.address1 + "\n" + this.address2 + "\n" + this.address3 + "\n";
	}
	
	public String getAccIdByCust(){
		return this.acct.getAcctNo();
	}
	
	public void reactiveCustomer(Date today){
		this.isDeleted = false;
		this.acct.setDateCreated(today);
		this.acct.setDateDeleted(null);
	}
}
