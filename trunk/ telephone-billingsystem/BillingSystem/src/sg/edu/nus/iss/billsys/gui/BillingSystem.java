package sg.edu.nus.iss.billsys.gui;

/**
 * @author Ma Huazhen
 *
 */
public class BillingSystem  {
	
	private  LoginForm loginForm;

	public BillingSystem () {
	  
	    }	
	public static void main(String[] args) {
		BillingSystem manager = new BillingSystem ();
	    manager.start ();
	}
	public void start() {	
		login();
	}

    public void login() {
    	loginForm = new LoginForm();
		loginForm.setVisible (true);		
	}
}
