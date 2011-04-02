package sg.edu.nus.iss.billsys.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.billsys.constant.SessionKeys;
import sg.edu.nus.iss.billsys.constant.UserRole;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.util.StringUtil;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class LoginForm extends javax.swing.JFrame {
	private JButton cancelButton;
	private JPasswordField passwordText;
	private JLabel errorMsgLabel;
	private JLabel passwordLabel;
	private JLabel usernameLabel;
	private JTextField usernameText;
	private JButton loginButton;

	public LoginForm() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout(
					(JComponent) getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Login form");
			{
				cancelButton = new JButton();
				cancelButton.setText("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						cancelButtonActionPerformed(arg0);
						
					}
					
				});
			}
			{
				loginButton = new JButton();
				loginButton.setText("Login");
				getRootPane().setDefaultButton(loginButton);
				loginButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						loginButtonActionPerformed(arg0);
						
					}
					
				});
			}
			{
				usernameText = new JTextField();
			}
			{
				usernameLabel = new JLabel();
				usernameLabel.setText("Username:");
			}
			{
				errorMsgLabel = new JLabel();
				errorMsgLabel.setOpaque(true);
				errorMsgLabel.setForeground(new java.awt.Color(255, 0, 0));
			}
			{
				passwordLabel = new JLabel();
				passwordLabel.setText("Password:");
			}
			{
				passwordText = new JPasswordField();
			}
			thisLayout
					.setVerticalGroup(thisLayout
							.createSequentialGroup()
							.addContainerGap(27, 27)
							.addGroup(
									thisLayout
											.createParallelGroup(
													GroupLayout.Alignment.BASELINE)
											.addComponent(
													usernameText,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													usernameLabel,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(
									thisLayout
											.createParallelGroup()
											.addComponent(
													passwordText,
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
																	passwordLabel,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.PREFERRED_SIZE)))
							.addGap(27)
							.addComponent(errorMsgLabel,
									GroupLayout.PREFERRED_SIZE, 14,
									GroupLayout.PREFERRED_SIZE)
							.addGap(0, 32, Short.MAX_VALUE)
							.addGroup(
									thisLayout
											.createParallelGroup(
													GroupLayout.Alignment.BASELINE)
											.addComponent(
													cancelButton,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													loginButton,
													GroupLayout.Alignment.BASELINE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.PREFERRED_SIZE))
							.addContainerGap(27, 27));
			thisLayout
					.setHorizontalGroup(thisLayout
							.createSequentialGroup()
							.addContainerGap(19, 19)
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
																					loginButton,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					112,
																					GroupLayout.PREFERRED_SIZE)
																			.addComponent(
																					passwordLabel,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					112,
																					GroupLayout.PREFERRED_SIZE)
																			.addComponent(
																					usernameLabel,
																					GroupLayout.Alignment.LEADING,
																					GroupLayout.PREFERRED_SIZE,
																					112,
																					GroupLayout.PREFERRED_SIZE))
															.addGap(30)
															.addGroup(
																	thisLayout
																			.createParallelGroup()
																			.addComponent(
																					cancelButton,
																					GroupLayout.Alignment.LEADING,
																					0,
																					113,
																					Short.MAX_VALUE)
																			.addComponent(
																					passwordText,
																					GroupLayout.Alignment.LEADING,
																					0,
																					113,
																					Short.MAX_VALUE)
																			.addComponent(
																					usernameText,
																					GroupLayout.Alignment.LEADING,
																					0,
																					113,
																					Short.MAX_VALUE)))
											.addGroup(
													thisLayout
															.createSequentialGroup()
															.addComponent(
																	errorMsgLabel,
																	GroupLayout.PREFERRED_SIZE,
																	255,
																	GroupLayout.PREFERRED_SIZE)
															.addGap(0,
																	0,
																	Short.MAX_VALUE)))
							.addContainerGap(35, 35));
			pack();
			this.setSize(317, 237);
		    Toolkit tk = Toolkit.getDefaultToolkit();
		    Dimension screenSize = tk.getScreenSize();
		    setLocation(screenSize.width/2-160, screenSize.height/2-120);
		    setResizable(false);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	protected void cancelButtonActionPerformed(ActionEvent arg0) {
		System.out.println("cancelButton.actionPerformed, arg0=" + arg0);
		// exit application
		System.exit(0);
	}

	protected void loginButtonActionPerformed(ActionEvent arg0) {
		System.out.println("loginButton.actionPerformed, arg0=" + arg0);

		System.out.println(this.usernameText.getText());
		System.out.println(this.passwordText.getText());

		// validate username & password input
		if (StringUtil.isNullOrEmpty(this.usernameText.getText())
				|| StringUtil.isNullOrEmpty(this.passwordText.getText())) {
			// display error message
			this.errorMsgLabel.setText("Invalid input for username/ password!");
			return;
		}

		// do user authentication
		try {
			if (MgrFactory.getUserMgr().isValidAuthUser(
					this.usernameText.getText(), this.passwordText.getText())) {
				
				// get the user role and save in the session
				UserRole role = MgrFactory.getUserMgr().getAuthUserRole();
				SessionMgr.map.put(SessionKeys.USER_ROLE, role);
				
				// display the main application
				startBillingWindow();
				
				this.setVisible(false);
				this.dispose();
				
			} else {
//				 display error message
				this.errorMsgLabel.setText("Authentication failed!");
			}
		} catch (BillingSystemException e) {
//			// display error message
			this.errorMsgLabel.setText("Not able to login, internal error occurred!");
		}
	}

	public void startBillingWindow() {

		BillingWindow billingWindow = new BillingWindow ();
		billingWindow.pack ();
		billingWindow.setSize(600, 600);
		billingWindow.setVisible (true);	
	}

}
