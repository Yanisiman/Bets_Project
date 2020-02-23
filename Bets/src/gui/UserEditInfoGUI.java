package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.User;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserEditInfoGUI extends JFrame {

	private JPanel panel;
	private JLabel betAndRuinLbl = new JLabel("BET & RUIN");
	private final JLabel registrationLbl = new JLabel("Edit your information");

	private JTextField usernameField = new JTextField();
	private JLabel usernameLbl = new JLabel("Username :");

	private final JLabel oldPasswordLbl = new JLabel("Old Password :");
	private final JPasswordField oldPasswordField = new JPasswordField();

	private JPasswordField passwordField = new JPasswordField();
	private JLabel passwordLbl = new JLabel("New Password :");

	private final JLabel nameLbl = new JLabel("Name :");
	private final JTextField nameField = new JTextField();

	private final JLabel familyNameLbl = new JLabel("Family Name:");
	private final JTextField familyNameField = new JTextField();

	private final JLabel creditCardLbl = new JLabel("Credit card :");
	private final JTextField creditCardField = new JTextField();

	private final JButton registerBtn = new JButton("Edit");
	private final JTextArea errorArea = new JTextArea();

	private final JTextField moneyField = new JTextField();
	private final JLabel moneyLbl = new JLabel("Add money :");

	private UserEditInfoGUI self;
	private BLFacade businessLogic;
	private User currentUser;
	private UserInformationGUI info;
	private final JButton addMoneyBtn = new JButton("Add");


	/**
	 * Create the frame.
	 */
	public UserEditInfoGUI(User user, UserInformationGUI uInfo) {
		
		currentUser = user;
		info = uInfo;
		
		familyNameField.setColumns(10);
		nameField.setColumns(10);
		moneyField.setColumns(10);
		setTitle("Bet & Ruin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		setContentPane(panel);
		setSize(800, 650);
		self = this;

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 20, 50, 80, 68, 297, 66, 35, 30, 50, 20 };
		gbl_panel.rowHeights = new int[] { 20, 35, 0, 35, 35, 35, 35, 36, 35, 49, 43, 0, 35, 64, 20 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 0.5, 1.0 };
		gbl_panel.rowWeights = new double[] { 1.0, 0.5, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, 0.5, 0.0, 0.5, 0.0, 0.5, 0.0,
				1.0 };
		panel.setLayout(gbl_panel);

		betAndRuinLbl.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		GridBagConstraints gbc_betAndRuinLbl = new GridBagConstraints();
		gbc_betAndRuinLbl.gridwidth = 3;
		gbc_betAndRuinLbl.insets = new Insets(0, 0, 5, 5);
		gbc_betAndRuinLbl.gridx = 3;
		gbc_betAndRuinLbl.gridy = 0;
		panel.add(betAndRuinLbl, gbc_betAndRuinLbl);

		GridBagConstraints gbc_registrationLbl = new GridBagConstraints();
		gbc_registrationLbl.insets = new Insets(0, 0, 5, 5);
		gbc_registrationLbl.gridx = 4;
		gbc_registrationLbl.gridy = 1;
		registrationLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(registrationLbl, gbc_registrationLbl);

		usernameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GridBagConstraints gbc_usernameLbl = new GridBagConstraints();
		gbc_usernameLbl.anchor = GridBagConstraints.EAST;
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

		GridBagConstraints gbc_oldPasswordLbl = new GridBagConstraints();
		gbc_oldPasswordLbl.gridwidth = 2;
		gbc_oldPasswordLbl.anchor = GridBagConstraints.EAST;
		gbc_oldPasswordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_oldPasswordLbl.gridx = 2;
		gbc_oldPasswordLbl.gridy = 4;
		oldPasswordLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(oldPasswordLbl, gbc_oldPasswordLbl);

		GridBagConstraints gbc_oldPasswordField = new GridBagConstraints();
		gbc_oldPasswordField.gridwidth = 3;
		gbc_oldPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_oldPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_oldPasswordField.gridx = 4;
		gbc_oldPasswordField.gridy = 4;
		panel.add(oldPasswordField, gbc_oldPasswordField);

		passwordLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.anchor = GridBagConstraints.EAST;
		gbc_passwordLbl.gridwidth = 2;
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 2;
		gbc_passwordLbl.gridy = 5;
		panel.add(passwordLbl, gbc_passwordLbl);

		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 4;
		gbc_passwordField.gridy = 5;
		panel.add(passwordField, gbc_passwordField);

		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.anchor = GridBagConstraints.EAST;
		gbc_nameLbl.gridwidth = 2;
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.gridx = 2;
		gbc_nameLbl.gridy = 6;
		nameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nameLbl, gbc_nameLbl);

		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 3;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 4;
		gbc_nameField.gridy = 6;
		panel.add(nameField, gbc_nameField);

		GridBagConstraints gbc_familyNameLbl = new GridBagConstraints();
		gbc_familyNameLbl.anchor = GridBagConstraints.EAST;
		gbc_familyNameLbl.gridwidth = 2;
		gbc_familyNameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_familyNameLbl.gridx = 2;
		gbc_familyNameLbl.gridy = 7;
		familyNameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(familyNameLbl, gbc_familyNameLbl);

		GridBagConstraints gbc_familyNameField = new GridBagConstraints();
		gbc_familyNameField.gridwidth = 3;
		gbc_familyNameField.insets = new Insets(0, 0, 5, 5);
		gbc_familyNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_familyNameField.gridx = 4;
		gbc_familyNameField.gridy = 7;
		panel.add(familyNameField, gbc_familyNameField);

		GridBagConstraints gbc_creditCardLbl = new GridBagConstraints();
		gbc_creditCardLbl.gridwidth = 2;
		gbc_creditCardLbl.anchor = GridBagConstraints.EAST;
		gbc_creditCardLbl.insets = new Insets(0, 0, 5, 5);
		gbc_creditCardLbl.gridx = 2;
		gbc_creditCardLbl.gridy = 8;
		creditCardLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(creditCardLbl, gbc_creditCardLbl);
		creditCardField.setColumns(10);

		GridBagConstraints gbc_creditCardField = new GridBagConstraints();
		gbc_creditCardField.gridwidth = 3;
		gbc_creditCardField.insets = new Insets(0, 0, 5, 5);
		gbc_creditCardField.fill = GridBagConstraints.HORIZONTAL;
		gbc_creditCardField.gridx = 4;
		gbc_creditCardField.gridy = 8;
		panel.add(creditCardField, gbc_creditCardField);

		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 9;
		moneyLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(moneyLbl, gbc_lblNewLabel);
		
		GridBagConstraints gbc_moneyField = new GridBagConstraints();
		gbc_moneyField.insets = new Insets(0, 0, 5, 5);
		gbc_moneyField.fill = GridBagConstraints.HORIZONTAL;
		gbc_moneyField.gridx = 4;
		gbc_moneyField.gridy = 9;
		panel.add(moneyField, gbc_moneyField);

		GridBagConstraints gbc_registerBtn = new GridBagConstraints();
		gbc_registerBtn.gridwidth = 4;
		gbc_registerBtn.fill = GridBagConstraints.BOTH;
		gbc_registerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_registerBtn.gridx = 3;
		gbc_registerBtn.gridy = 11;
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!usernameField.getText().equals("")) {
					if (businessLogic.checkLogin(usernameField.getText(), "") == null)
						currentUser.setUsername(usernameField.getText());
					else {
						errorArea.setText("Username already taken by another user");
						return;
					}
				}				
				
				if (!oldPasswordField.getText().equals("") && passwordField.getText().equals("")) {
					if (oldPasswordField.getText().equals(currentUser.getPassword()))
						currentUser.setPassword(passwordField.getText());
					else {
						errorArea.setText("Old password given is not the good one");
						return;
					}
				}
						
				if (!nameField.getText().equals(""))
					currentUser.setName(nameField.getText());
				if (!familyNameField.getText().equals(""))
					currentUser.setFamilyName(familyNameField.getText());
				if (!creditCardField.getText().equals(""))
					currentUser.setCreditCard(creditCardField.getText());
				
				businessLogic.updateUser(currentUser);
				self.setVisible(false);
				info.refresh();				
			}
		});
		
		GridBagConstraints gbc_addMoneyBtn = new GridBagConstraints();
		gbc_addMoneyBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_addMoneyBtn.gridwidth = 2;
		gbc_addMoneyBtn.insets = new Insets(0, 0, 5, 5);
		gbc_addMoneyBtn.gridx = 5;
		gbc_addMoneyBtn.gridy = 9;
		addMoneyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int money = Integer.parseInt(moneyField.getText());
					businessLogic.addMoney(currentUser, money);
				} catch (Exception e) {
					
				}
			}
		});
		addMoneyBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		panel.add(addMoneyBtn, gbc_addMoneyBtn);
		panel.add(registerBtn, gbc_registerBtn);

		GridBagConstraints gbc_errorArea = new GridBagConstraints();
		gbc_errorArea.gridwidth = 4;
		gbc_errorArea.insets = new Insets(0, 0, 5, 5);
		gbc_errorArea.fill = GridBagConstraints.BOTH;
		gbc_errorArea.gridx = 3;
		gbc_errorArea.gridy = 13;
		errorArea.setEditable(false);
		panel.add(errorArea, gbc_errorArea);

	}

	public void setBusinessLogic(BLFacade businessLogic) {
		this.businessLogic = businessLogic;
	}

}
