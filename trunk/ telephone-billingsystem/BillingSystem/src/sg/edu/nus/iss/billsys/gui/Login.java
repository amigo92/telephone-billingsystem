package sg.edu.nus.iss.billsys.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import sg.edu.nus.iss.billsys.mgr.MgrFactory;

public class Login extends JPanel {

	
	JTextField txtName;
	JPasswordField txtPwd;
	
	public Login(){
		super();
		
		MgrFactory.getUserMgr().logout();
		
		setLayout(new GridLayout(3, 2));
		
		JLabel lblName = new JLabel("User ID:");
		add(lblName);
		txtName = new JTextField("Veera1", 20);	//TODO
		add(txtName);
		
		JLabel lblPwd = new JLabel("Password:");
		add(lblPwd);
		txtPwd = new JPasswordField("pass1", 20);	//TODO
		add(txtPwd);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(MgrFactory.getUserMgr().isValidAuthUser(txtName.getText(), new String(txtPwd.getPassword()))){
						BillingSystem.updateContentPane(new Welcome());
					}
					else{
						BillingSystem.updateContentPane(new Login());
					}
				}
	        }
		);
		
		add(btnSubmit);
		
	}
}
