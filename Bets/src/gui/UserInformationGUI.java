package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bet;
import domain.User;
import domain.UserBet;

import javax.swing.JTextArea;
import javax.swing.JList;

public class UserInformationGUI extends JFrame {

	private JPanel panel;
	private JLabel betAndRuinLbl = new JLabel("BET & RUIN");
	private JLabel usernameLbl = new JLabel("Username :");
	private JLabel passwordLbl = new JLabel("Password :");

	private final JLabel registrationLbl = new JLabel("Registration : Enter all your information");
	private final JLabel emailLbl = new JLabel("Email :");
	private final JLabel nameLbl = new JLabel("Name :");
	private final JLabel familyNameLbl = new JLabel("Family Name :");
	private final JLabel nationalityLbl = new JLabel("Nationality :");
	private final JLabel ageLbl = new JLabel("Birth date :");
	private final JLabel creditCardLbl = new JLabel("Credit card :");
	private final JButton deleteAccountBtn = new JButton("Delete Account");
	private final JButton editButton = new JButton("Edit");
	private final JLabel usernameField = new JLabel("");
	private final JLabel emailField = new JLabel("");
	private final JLabel passwordField = new JLabel("");
	private final JLabel nameField = new JLabel("");
	private final JLabel familyNameField = new JLabel("");
	private final JLabel nationalityField = new JLabel("");
	private final JLabel birthField = new JLabel("");
	private final JLabel creditCardField = new JLabel("");
	private final JLabel moneyLbl = new JLabel("Money in account :");
	private final JLabel moneyField = new JLabel("");
	private final JButton closeBtn = new JButton("Close");

	private UserInformationGUI self;
	private FindQuestionsGUI mainWindow;
	private BLFacade businessLogic;
	private User currentUser;
	private JLabel friendsLbl = new JLabel("Friends :");
	private JList<User> friendsList = new JList<User>();
	private JLabel betsLbl = new JLabel("Bets :");
	private JList<UserBet> betsList = new JList<UserBet>();

	/**
	 * Create the frame.
	 */
	public UserInformationGUI(User user, FindQuestionsGUI fq) {
		currentUser = user;
		mainWindow = fq;
		setTitle("Bet & Ruin");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		setContentPane(panel);
		setSize(1200, 650);
		self = this;

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 57, 20, 50, 80, 0, 68, 194, 91, 50, 30, 50, 45 };
		gbl_panel.rowHeights = new int[] { 20, 35, 0, 0, 35, 35, 35, 35, 36, 35, 49, 43, 35, 35, 20 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.5, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.5, 0.5, 1.0 };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, 0.5, 0.5, 0.5, 0.0, 1.0, 0.0, 0.5, 0.0, 0.5,
				1.0 };
		panel.setLayout(gbl_panel);

		betAndRuinLbl.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		GridBagConstraints gbc_betAndRuinLbl = new GridBagConstraints();
		gbc_betAndRuinLbl.gridwidth = 5;
		gbc_betAndRuinLbl.insets = new Insets(0, 0, 5, 5);
		gbc_betAndRuinLbl.gridx = 3;
		gbc_betAndRuinLbl.gridy = 0;
		panel.add(betAndRuinLbl, gbc_betAndRuinLbl);

		GridBagConstraints gbc_deleteAccountBtn = new GridBagConstraints();
		gbc_deleteAccountBtn.gridwidth = 2;
		gbc_deleteAccountBtn.fill = GridBagConstraints.BOTH;
		gbc_deleteAccountBtn.insets = new Insets(0, 0, 5, 0);
		gbc_deleteAccountBtn.gridx = 10;
		gbc_deleteAccountBtn.gridy = 0;
		deleteAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				businessLogic.deleteUser(currentUser);
				self.setVisible(false);
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setBusinessLogic(businessLogic);
				loginGUI.setVisible(true);
			}
		});

		deleteAccountBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(deleteAccountBtn, gbc_deleteAccountBtn);

		GridBagConstraints gbc_registrationLbl = new GridBagConstraints();
		gbc_registrationLbl.gridwidth = 5;
		gbc_registrationLbl.insets = new Insets(0, 0, 5, 5);
		gbc_registrationLbl.gridx = 3;
		gbc_registrationLbl.gridy = 1;
		registrationLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(registrationLbl, gbc_registrationLbl);

		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.gridwidth = 5;
		gbc_editButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editButton.insets = new Insets(0, 0, 5, 5);
		gbc_editButton.gridx = 1;
		gbc_editButton.gridy = 3;
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserEditInfoGUI userEditInfoGUI = new UserEditInfoGUI(currentUser, self);
				userEditInfoGUI.setBusinessLogic(businessLogic);
				userEditInfoGUI.setVisible(true);
			}
		});
		editButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(editButton, gbc_editButton);

		GridBagConstraints gbc_friendsLbl = new GridBagConstraints();
		gbc_friendsLbl.gridwidth = 4;
		gbc_friendsLbl.insets = new Insets(0, 0, 5, 5);
		gbc_friendsLbl.gridx = 7;
		gbc_friendsLbl.gridy = 3;
		friendsLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(friendsLbl, gbc_friendsLbl);

		usernameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GridBagConstraints gbc_usernameLbl = new GridBagConstraints();
		gbc_usernameLbl.anchor = GridBagConstraints.EAST;
		gbc_usernameLbl.gridwidth = 2;
		gbc_usernameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLbl.gridx = 1;
		gbc_usernameLbl.gridy = 4;
		panel.add(usernameLbl, gbc_usernameLbl);

		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.gridwidth = 3;
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.gridx = 3;
		gbc_usernameField.gridy = 4;
		usernameField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(usernameField, gbc_usernameField);

		GridBagConstraints gbc_friendsList = new GridBagConstraints();
		gbc_friendsList.gridheight = 4;
		gbc_friendsList.gridwidth = 4;
		gbc_friendsList.insets = new Insets(0, 0, 5, 5);
		gbc_friendsList.fill = GridBagConstraints.BOTH;
		gbc_friendsList.gridx = 7;
		gbc_friendsList.gridy = 4;
		panel.add(friendsList, gbc_friendsList);

		GridBagConstraints gbc_emailLbl = new GridBagConstraints();
		gbc_emailLbl.anchor = GridBagConstraints.EAST;
		gbc_emailLbl.gridwidth = 2;
		gbc_emailLbl.insets = new Insets(0, 0, 5, 5);
		gbc_emailLbl.gridx = 1;
		gbc_emailLbl.gridy = 5;
		emailLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(emailLbl, gbc_emailLbl);

		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.gridwidth = 3;
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.gridx = 3;
		gbc_emailField.gridy = 5;
		emailField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(emailField, gbc_emailField);

		passwordLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.anchor = GridBagConstraints.EAST;
		gbc_passwordLbl.gridwidth = 2;
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 1;
		gbc_passwordLbl.gridy = 6;
		panel.add(passwordLbl, gbc_passwordLbl);

		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 6;
		passwordField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(passwordField, gbc_passwordField);

		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.anchor = GridBagConstraints.EAST;
		gbc_nameLbl.gridwidth = 2;
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.gridx = 1;
		gbc_nameLbl.gridy = 7;
		nameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nameLbl, gbc_nameLbl);

		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 3;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 7;
		nameField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nameField, gbc_nameField);

		GridBagConstraints gbc_familyNameLbl = new GridBagConstraints();
		gbc_familyNameLbl.anchor = GridBagConstraints.EAST;
		gbc_familyNameLbl.gridwidth = 2;
		gbc_familyNameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_familyNameLbl.gridx = 1;
		gbc_familyNameLbl.gridy = 8;
		familyNameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(familyNameLbl, gbc_familyNameLbl);

		GridBagConstraints gbc_familyNameField = new GridBagConstraints();
		gbc_familyNameField.gridwidth = 3;
		gbc_familyNameField.insets = new Insets(0, 0, 5, 5);
		gbc_familyNameField.gridx = 3;
		gbc_familyNameField.gridy = 8;
		familyNameField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(familyNameField, gbc_familyNameField);

		GridBagConstraints gbc_betsLbl = new GridBagConstraints();
		gbc_betsLbl.gridwidth = 4;
		gbc_betsLbl.insets = new Insets(0, 0, 5, 5);
		gbc_betsLbl.gridx = 7;
		gbc_betsLbl.gridy = 8;
		betsLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(betsLbl, gbc_betsLbl);

		GridBagConstraints gbc_nationalityLbl = new GridBagConstraints();
		gbc_nationalityLbl.anchor = GridBagConstraints.EAST;
		gbc_nationalityLbl.gridwidth = 2;
		gbc_nationalityLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nationalityLbl.gridx = 1;
		gbc_nationalityLbl.gridy = 9;
		nationalityLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nationalityLbl, gbc_nationalityLbl);

		GridBagConstraints gbc_nationalityField = new GridBagConstraints();
		gbc_nationalityField.gridwidth = 3;
		gbc_nationalityField.insets = new Insets(0, 0, 5, 5);
		gbc_nationalityField.gridx = 3;
		gbc_nationalityField.gridy = 9;
		nationalityField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nationalityField, gbc_nationalityField);

		GridBagConstraints gbc_betsList = new GridBagConstraints();
		gbc_betsList.gridheight = 4;
		gbc_betsList.gridwidth = 4;
		gbc_betsList.insets = new Insets(0, 0, 5, 5);
		gbc_betsList.fill = GridBagConstraints.BOTH;
		gbc_betsList.gridx = 7;
		gbc_betsList.gridy = 9;
		panel.add(betsList, gbc_betsList);

		GridBagConstraints gbc_ageLbl = new GridBagConstraints();
		gbc_ageLbl.anchor = GridBagConstraints.EAST;
		gbc_ageLbl.gridwidth = 2;
		gbc_ageLbl.insets = new Insets(0, 0, 5, 5);
		gbc_ageLbl.gridx = 1;
		gbc_ageLbl.gridy = 10;
		ageLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(ageLbl, gbc_ageLbl);

		GridBagConstraints gbc_birthField = new GridBagConstraints();
		gbc_birthField.gridwidth = 3;
		gbc_birthField.insets = new Insets(0, 0, 5, 5);
		gbc_birthField.gridx = 3;
		gbc_birthField.gridy = 10;
		birthField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(birthField, gbc_birthField);

		GridBagConstraints gbc_creditCardLbl = new GridBagConstraints();
		gbc_creditCardLbl.anchor = GridBagConstraints.EAST;
		gbc_creditCardLbl.gridwidth = 2;
		gbc_creditCardLbl.insets = new Insets(0, 0, 5, 5);
		gbc_creditCardLbl.gridx = 1;
		gbc_creditCardLbl.gridy = 11;
		creditCardLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(creditCardLbl, gbc_creditCardLbl);

		GridBagConstraints gbc_creditCardField = new GridBagConstraints();
		gbc_creditCardField.gridwidth = 3;
		gbc_creditCardField.insets = new Insets(0, 0, 5, 5);
		gbc_creditCardField.gridx = 3;
		gbc_creditCardField.gridy = 11;
		creditCardField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(creditCardField, gbc_creditCardField);

		//refresh(currentUser);

		GridBagConstraints gbc_moneyLbl = new GridBagConstraints();
		gbc_moneyLbl.anchor = GridBagConstraints.EAST;
		gbc_moneyLbl.gridwidth = 2;
		gbc_moneyLbl.insets = new Insets(0, 0, 5, 5);
		gbc_moneyLbl.gridx = 1;
		gbc_moneyLbl.gridy = 12;
		moneyLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(moneyLbl, gbc_moneyLbl);

		GridBagConstraints gbc_moneyField = new GridBagConstraints();
		gbc_moneyField.gridwidth = 3;
		gbc_moneyField.insets = new Insets(0, 0, 5, 5);
		gbc_moneyField.gridx = 3;
		gbc_moneyField.gridy = 12;
		moneyField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(moneyField, gbc_moneyField);

		GridBagConstraints gbc_closeBtn = new GridBagConstraints();
		gbc_closeBtn.gridwidth = 5;
		gbc_closeBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeBtn.insets = new Insets(0, 0, 0, 5);
		gbc_closeBtn.gridx = 3;
		gbc_closeBtn.gridy = 14;
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				self.setVisible(false);
				mainWindow.setCurrentUser(currentUser);
			}
		});
		closeBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		panel.add(closeBtn, gbc_closeBtn);

	}

	public void refresh(User currentUser) {
		
		usernameField.setText(currentUser.getUsername());
		emailField.setText(currentUser.getEmail());

		String password = "";
		for (int i = 0; i < currentUser.getPassword().length(); i++) {
			password += '*';
		}
		passwordField.setText(password);

		nameField.setText(currentUser.getName());
		familyNameField.setText(currentUser.getFamilyName());
		nationalityField.setText(currentUser.getNationality());
		birthField.setText(currentUser.getBirthDate());

		String credit = "";
		for (int i = 0; i < currentUser.getCreditCard().length(); i++) {
			if (i == 0 || i == currentUser.getCreditCard().length() - 1)
				credit += currentUser.getCreditCard().charAt(i);
			else {
				credit += '*';
			}
		}
		creditCardField.setText(credit);
		moneyField.setText(String.valueOf(currentUser.getMoney()));
		
		
		Vector<User> friends = businessLogic.getFriends(currentUser);
		Vector<UserBet> bets = businessLogic.getUserBets(currentUser);
		
		friendsList.setListData(friends);
		betsList.setListData(bets);
	}

	public void setBusinessLogic(BLFacade businessLogic) {
		this.businessLogic = businessLogic;
		
		refresh(currentUser);
	}

}
