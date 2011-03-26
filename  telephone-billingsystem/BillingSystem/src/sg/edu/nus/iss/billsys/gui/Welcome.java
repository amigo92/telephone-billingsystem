package sg.edu.nus.iss.billsys.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.billsys.mgr.MgrFactory;

public class Welcome extends JPanel {

	public Welcome(){
		super();
		
		JLabel msg = new JLabel("Welcome back, " + MgrFactory.getUserMgr().getCurrentAuthUserId() + "!");
		add(msg);
	}
}
