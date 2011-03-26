package sg.edu.nus.iss.billsys.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import sg.edu.nus.iss.billsys.mgr.MgrFactory;

public class Login extends JPanel {

	private static final long serialVersionUID = -787796397769905542L;

	private JLabel lblName;
	private JTextField txtName;
	
	private JLabel lblPwd;
	private JPasswordField txtPwd;
	
	private JButton btnSubmit  = new JButton();
	
	public Login(){
		super();
		
		MgrFactory.getUserMgr().logout();

		iniFields();
		iniLayout();
	}
	
	private void iniFields(){
		lblName = new JLabel("User ID:");
		txtName = new JTextField("Veera1", 20);	//TODO
		
		lblPwd = new JLabel("Password:");
		txtPwd = new JPasswordField("pass1", 20);	//TODO

		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(MgrFactory.getUserMgr().isValidAuthUser(txtName.getText(), new String(txtPwd.getPassword()))){
						BillingSystem.updateContentPane(new Welcome());
					}
					else{
						BillingSystem.updateContentPane(new Login());
						BillingSystem.setMsgOK("Invalid input for username or password!");
					}
				}
	        }
		);
	}
	
	private void iniLayout() {
		try {
			GroupLayout thisLayout = new GroupLayout(this);
			setLayout(thisLayout);
			
			thisLayout
					.setVerticalGroup(thisLayout
							.createSequentialGroup()
							.addContainerGap(200, 200)
							.addGroup(
									thisLayout
											.createParallelGroup(
													GroupLayout.Alignment.BASELINE)
											.addComponent(
													txtName,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													lblName,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(
									thisLayout
											.createParallelGroup()
											.addComponent(
													txtPwd,
													GroupLayout.Alignment.LEADING,
													GroupLayout.PREFERRED_SIZE,
													22,
													GroupLayout.PREFERRED_SIZE)
											.addGroup(
													GroupLayout.Alignment.LEADING,
													thisLayout
															.createSequentialGroup()
															.addGap(8)
															.addComponent(
																	lblPwd,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.PREFERRED_SIZE)))
							.addGap(27)
							.addGroup(
									thisLayout
											.createParallelGroup(
													GroupLayout.Alignment.BASELINE)
											.addComponent(
													btnSubmit,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE))
							.addContainerGap(27, 27));
			thisLayout
					.setHorizontalGroup(thisLayout
							.createSequentialGroup()
							.addContainerGap(500, 500)
							.addGroup(
									thisLayout
											.createParallelGroup()
											.addGroup(
													thisLayout
															.createSequentialGroup()
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addComponent(
																					btnSubmit,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					112,
																					GroupLayout.PREFERRED_SIZE)
																			.addComponent(
																					lblPwd,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					112,
																					GroupLayout.PREFERRED_SIZE)
																			.addComponent(
																					lblName,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					112,
																					GroupLayout.PREFERRED_SIZE))
															.addGap(30)
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addComponent(
																					txtPwd,
																					GroupLayout.Alignment.LEADING,
																					0,
																					113,
																					Short.MAX_VALUE)
																			.addComponent(
																					txtName,
																					GroupLayout.Alignment.LEADING,
																					0,
																					113,
																					Short.MAX_VALUE)))
											.addGroup(
													thisLayout
															.createSequentialGroup()
															.addGap(0,
																	0,
																	Short.MAX_VALUE)))
							.addContainerGap(500, 500)
							)
							;

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
