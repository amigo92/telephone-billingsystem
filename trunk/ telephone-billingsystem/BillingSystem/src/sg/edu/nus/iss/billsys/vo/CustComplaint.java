package sg.edu.nus.iss.billsys.vo;

import java.util.Date;

/**
 * 
 * @author Veera
 *
 */

public class CustComplaint {
	
	private String accNo;
	private String complaint_id;
	private String complaint_Details;
	private String status;
	private Date complaintDate;
	
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getComplaint_Details() {
		return complaint_Details;
	}
	public void setComplaint_Details(String complaint_Details) {
		this.complaint_Details = complaint_Details;
	}
	public String getComplaint_id() {
		return complaint_id;
	}
	public void setComplaint_id(String complaint_id) {
		this.complaint_id = complaint_id;
	}
	public Date getComplaintDate() {
		return complaintDate;
	}
	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
