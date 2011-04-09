package sg.edu.nus.iss.billsys.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// @author Win Kyi Tin

	public class JTextFieldUtil extends PlainDocument {

		   private int limit ;	
		  
		    public  JTextFieldUtil(int limit) {
			    super();
			    this.limit = limit;
		  	}

		   
		   public void insertString(int offset, String  str, AttributeSet attr)
		       throws BadLocationException {
		   
		   if (getLength()+str.length()>limit){
			   // If it does, then truncate it			   
			   str = str.substring(0, limit - getLength());
			}
			        super.insertString(offset, str, attr);
			   
		 }
	}

