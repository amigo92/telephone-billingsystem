package sg.edu.nus.iss.billsys.gui;

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

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		// SwingUtilities.invokeLater(new Runnable() {
		// public void run() {
		// LoginForm inst = new LoginForm();
		// inst.setLocationRelativeTo(null);
		// inst.setVisible(true);
		// }
		// });
	}

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
				cancelButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						cancelButtonMouseClicked(evt);
					}
				});
			}
			{
				loginButton = new JButton();
				loginButton.setText("Login");
				loginButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						loginButtonMouseClicked(evt);
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
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private void loginButtonMouseClicked(MouseEvent evt) {
		System.out.println("loginButton.mouseClicked, event=" + evt);

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
		if (MgrFactory.getUserMgr().isValidAuthUser(
				this.usernameText.getText(), this.passwordText.getText())) {
			// display the main application
			openDisplayMainApp();
		} else {
			// display error message
			this.errorMsgLabel.setText("Authentication failed!");
		}

	}

	private void cancelButtonMouseClicked(MouseEvent evt) {
		System.out.println("cancelButton.mouseClicked, event=" + evt);
		// exit application
		System.exit(0);
	}

	private void openDisplayMainApp() {
		// display the main app
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainAppForm inst = new MainAppForm();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
		
		// hide the login form
		this.setVisible(false);

		
	}

}
