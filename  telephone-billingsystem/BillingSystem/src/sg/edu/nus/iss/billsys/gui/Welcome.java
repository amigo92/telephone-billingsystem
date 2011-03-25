package sg.edu.nus.iss.billsys.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Welcome extends JPanel {

	public Welcome(){
		super();
		
		JLabel msg = new JLabel("Welcome back!");
		add(msg);
	}
}
