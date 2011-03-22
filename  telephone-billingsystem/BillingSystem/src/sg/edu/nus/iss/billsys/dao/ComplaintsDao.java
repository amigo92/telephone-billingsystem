package sg.edu.nus.iss.billsys.dao;


import java.util.*;

import sg.edu.nus.iss.billsys.tools.TimeUtils;
import sg.edu.nus.iss.billsys.vo.*;

/**
 * 
 * @author Veera
 *
 */
public class ComplaintsDao extends GenericDao {
	
	private List<CustComplaint> listComplaints=new ArrayList<CustComplaint>();
	
	public ComplaintsDao() {
		this.objectDataMapping(getComplaintsData());
	}
	
	@Override
	protected void saveObjectData() {
		int cnt=0;
		
		String data[][]=new String[listComplaints.size()][5];
			
		for (Iterator iter = listComplaints.iterator(); iter.hasNext();) {
		
			CustComplaint element = (CustComplaint) iter.next();			
				
				data[cnt][0]=element.getAccNo();
				data[cnt][1]=element.getComplaint_id();
				data[cnt][2]=element.getComplaint_Details();
				data[cnt][3]=element.getStatus();
				data[cnt][4]=element.getComplaintDate().toString();
				
				cnt++;				
			}
			saveComplaintsData(data);
		
	}
	
	@Override
	protected boolean validateData(String[][] data) {
		// TO be Impleted later , to check the correctness of the data file.
		return false;
	}
	
	@Override
	protected void objectDataMapping(String[][] data) {
		try{
		
			List<CustComplaint> listComplaints=new ArrayList<CustComplaint>();
		
		for(int i=0;i<data.length;i++){
	    	
			CustComplaint comp=new CustComplaint();
			
			comp.setAccNo(data[i][0]);
			comp.setComplaint_Details(data[i][2]);
			comp.setComplaint_id(data[i][1]);
			comp.setComplaintDate(TimeUtils.parseDate(data[i][4]));
			comp.setStatus(data[i][3]);
	    		    	
			listComplaints.add(comp);	
	    }
		
		
		this.listComplaints=listComplaints;
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
	}

	
}
