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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import sg.edu.nus.iss.billsys.constant.SessionKeys;
import sg.edu.nus.iss.billsys.constant.UserRole;
import sg.edu.nus.iss.billsys.exception.BillingSystemException;
import sg.edu.nus.iss.billsys.logger.BillingSystemLogger;
import sg.edu.nus.iss.billsys.mgr.MgrFactory;
import sg.edu.nus.iss.billsys.resource.ResourceHandler;
import sg.edu.nus.iss.billsys.util.StringUtil;

/**
 * @author L Sriragavan
 *
 * This is the GUI form class for the login form.
 */
public class LoginForm extends javax.swing.JFrame {
	/**
	 * Holds the label for username field.
	 */
	private JLabel usernameLabel;
	/**
	 * Holds the label for password field.
	 */
	private JLabel passwordLabel;
	/**
	 * Holds the label for error messages.
	 */
	private JLabel errorMsgLabel;
	/**
	 * This is the text field to get the user name.
	 */
	private JTextField usernameText;
	/**
	 * This is the text field to get the password.
	 */
	private JPasswordField passwordText;
	/**
	 * This is the button to perform login.
	 */
	private JButton loginButton;
	/**
	 * This is the button to cancel login.
	 */
	private JButton cancelButton;

	/**
	 * Default constructor.
	 */
	public LoginForm() {
		super();
		initGUI();
	}

	/**
	 * This method draws the login form GUI.
	 */
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout(
					(JComponent) getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle(ResourceHandler.getLabel("loginform.lbl.title"));
			{
				cancelButton = new JButton();
				cancelButton.setText(ResourceHandler.getLabel("loginform.btn.cancel"));
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						cancelButtonActionPerformed(arg0);
						
					}
					
				});
			}
			{
				loginButton = new JButton();
				loginButton.setText(ResourceHandler.getLabel("loginform.btn.ok"));
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
				usernameLabel.setText(ResourceHandler.getLabel("loginform.lbl.username"));
			}
			{
				errorMsgLabel = new JLabel();
				errorMsgLabel.setOpaque(true);
				errorMsgLabel.setForeground(new java.awt.Color(255, 0, 0));
			}
			{
				passwordLabel = new JLabel();
				passwordLabel.setText(ResourceHandler.getLabel("loginform.lbl.password"));
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

	/**
	 * This is the action handler method which will be called when clicking
	 *  on cancel button. 
	 * @param arg0 action event
	 */
	protected void cancelButtonActionPerformed(ActionEvent arg0) {
		BillingSystemLogger.logInfo("Inside cancelButtonActionPerformed(), arg0=" + arg0);
		// exit application
		System.exit(0);
	}

	/**
	 * This is the action handler method which will be called when clicking
	 *  on login button. 
	 * @param arg0 action event
	 */
	protected void loginButtonActionPerformed(ActionEvent arg0) {
		BillingSystemLogger.logInfo("loginButton.actionPerformed, arg0=" + arg0);
		BillingSystemLogger.logInfo(this.usernameText.getText());
		BillingSystemLogger.logInfo(this.passwordText.getText());

		// validate username & password input
		if (StringUtil.isNullOrEmpty(this.usernameText.getText())
				|| StringUtil.isNullOrEmpty(this.passwordText.getText())) {
			// display error message
			JOptionPane.showMessageDialog(this, ResourceHandler.getError("loginform.error1"), "Error Message", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(this, ResourceHandler.getError("loginform.error2"), "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		} catch (BillingSystemException e) {
//			// display error message
			JOptionPane.showMessageDialog(this, e.getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, new BillingSystemException(e).getMessagebyException(), "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	/**
	 * This method launches the billing window form.
	 */
	private void startBillingWindow() {

		BillingWindow billingWindow = new BillingWindow ();
		billingWindow.pack ();
		billingWindow.setSize(800, 600);
		billingWindow.setVisible (true);	
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenSize = tk.getScreenSize();
	    billingWindow.setLocation(screenSize.width/2-400, screenSize.height/2-300);
	    billingWindow.setResizable(false);
	}

}
