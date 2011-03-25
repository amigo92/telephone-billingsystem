package sg.edu.nus.iss.billsys.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import sg.edu.nus.iss.billsys.mgr.MgrFactory;

public class Login extends JPanel {

	public Login(){
		super();
		
		MgrFactory.getUserMgr().logout();
		
		setLayout(new GridLayout(3, 2));
		
		JLabel lblName = new JLabel("User ID:");
		add(lblName);
		JTextField txtName = new JTextField(20);
		add(txtName);
		
		JLabel lblPwd = new JLabel("Password:");
		add(lblPwd);
		JPasswordField txtPwd = new JPasswordField(20);
		add(txtPwd);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//TODO check for validity
					BillingSystem.updateContentPane(new Welcome());
				}
	        }
		);
		
		add(btnSubmit);
		
	}
}
