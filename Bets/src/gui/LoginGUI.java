package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.User;

import javax.swing.JTextArea;

public class LoginGUI extends JFrame {

	private JPanel panel;
	private JTextField usernameField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel betAndRuinLbl = new JLabel("BET & RUIN");
	private JLabel usernameLbl = new JLabel("Username or Email :");
	private JLabel passwordLbl = new JLabel("Password :");
	private JButton loginBtn = new JButton("Login");
	private JLabel noAccountLbl = new JLabel("You don't have any account yet ? Let's register !");
	private JButton registerBtn = new JButton("Register");
	private JButton spectatorBtn = new JButton("Go to spectator view");
	private final JTextArea textArea = new JTextArea();
	
	private LoginGUI self;
	private BLFacade businessLogic;
	

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setTitle("Bet & Ruin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		setContentPane(panel);
		setSize(800, 650);

		self = this;

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 20, 50, 80, 55, 150, 78, 35, 30, 50, 20 };
		gbl_panel.rowHeights = new int[] { 20, 35, 0, 35, 35, 54, 34, 34, 35, 0, 67, 0, 35, 20 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 0.5, 1.0 };
		gbl_panel.rowWeights = new double[] { 1.0, 0.5, 0.0, 0.5, 0.5, 0.5, 1.0, 0.0, 0.5, 0.0, 0.5, 0.0, 0.5, 1.0 };
		panel.setLayout(gbl_panel);

		betAndRuinLbl.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		GridBagConstraints gbc_betAndRuinLbl = new GridBagConstraints();
		gbc_betAndRuinLbl.gridwidth = 3;
		gbc_betAndRuinLbl.insets = new Insets(0, 0, 5, 5);
		gbc_betAndRuinLbl.gridx = 3;
		gbc_betAndRuinLbl.gridy = 0;
		panel.add(betAndRuinLbl, gbc_betAndRuinLbl);

		usernameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GridBagConstraints gbc_usernameLbl = new GridBagConstraints();
		gbc_usernameLbl.gridwidth = 2;
		gbc_usernameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLbl.gridx = 2;
		gbc_usernameLbl.gridy = 3;
		panel.add(usernameLbl, gbc_usernameLbl);

		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.gridwidth = 3;
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.gridx = 4;
		gbc_usernameField.gridy = 3;
		panel.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);

		passwordLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.gridwidth = 2;
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 2;
		gbc_passwordLbl.gridy = 4;
		panel.add(passwordLbl, gbc_passwordLbl);

		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 4;
		gbc_passwordField.gridy = 4;
		panel.add(passwordField, gbc_passwordField);

		GridBagConstraints gbc_loginBtn = new GridBagConstraints();
		gbc_loginBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginBtn.insets = new Insets(0, 0, 5, 5);
		gbc_loginBtn.gridx = 4;
		gbc_loginBtn.gridy = 5;
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userString = usernameField.getText();
				String passwordString = passwordField.getText();
				User currentUser = businessLogic.checkLogin(userString, passwordString);
				
				if (currentUser != null) {
					self.setVisible(false);
					FindQuestionsGUI findQuestionsGUI = new FindQuestionsGUI(currentUser, businessLogic);
					findQuestionsGUI.setBusinessLogic(businessLogic);
					findQuestionsGUI.setVisible(true);
				} else {
					textArea.setText("Access denied");
				}
			}
		});
		panel.add(loginBtn, gbc_loginBtn);

		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 3;
		gbc_textArea.gridy = 6;
		textArea.setEditable(false);
		panel.add(textArea, gbc_textArea);

		noAccountLbl.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		GridBagConstraints gbc_noAccountLbl = new GridBagConstraints();
		gbc_noAccountLbl.gridwidth = 3;
		gbc_noAccountLbl.insets = new Insets(0, 0, 5, 5);
		gbc_noAccountLbl.gridx = 3;
		gbc_noAccountLbl.gridy = 7;
		panel.add(noAccountLbl, gbc_noAccountLbl);

		GridBagConstraints gbc_registerBtn = new GridBagConstraints();
		gbc_registerBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_registerBtn.gridx = 4;
		gbc_registerBtn.gridy = 8;
		panel.add(registerBtn, gbc_registerBtn);
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));
				self.setVisible(false);
				RegisterGUI registerGUI = new RegisterGUI();
				registerGUI.setBusinessLogic(businessLogic);
				registerGUI.setVisible(true);
			}
		});

		GridBagConstraints gbc_spectatorBtn = new GridBagConstraints();
		gbc_spectatorBtn.gridwidth = 3;
		gbc_spectatorBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_spectatorBtn.insets = new Insets(0, 0, 5, 5);
		gbc_spectatorBtn.gridx = 3;
		gbc_spectatorBtn.gridy = 11;
		panel.add(spectatorBtn, gbc_spectatorBtn);
		spectatorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// dispatchEvent(new WindowEvent(self, WindowEvent.WINDOW_CLOSING));
				self.setVisible(false);
				FindQuestionsGUI findQuestionsGUI = new FindQuestionsGUI(null , businessLogic);
				findQuestionsGUI.setBusinessLogic(businessLogic);
				findQuestionsGUI.setVisible(true);
			}
		});
	}

	public void setBusinessLogic(BLFacade business_logic) {
		businessLogic = business_logic;
	}

	public JButton getRegisterListener() {
		return registerBtn;
	}

}
