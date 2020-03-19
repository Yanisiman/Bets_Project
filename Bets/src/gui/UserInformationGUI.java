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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.BetChoice;
import domain.Sport;
import domain.User;
import domain.UserBet;

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;

public class UserInformationGUI extends JFrame {

	private JPanel panel;
	private JLabel betAndRuinLbl = new JLabel("BET & RUIN");
	private JLabel usernameLbl = new JLabel("Username :");
	private JLabel passwordLbl = new JLabel("Password :");

	private final JLabel registrationLbl = new JLabel("My account");
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
	private JLabel betsLbl = new JLabel("Bets :");
	private final JButton removeBetBtn = new JButton("Remove bet");
	private final JTextField addFriendField = new JTextField();
	private final JButton addFriendBtn = new JButton("Add Friend");
	private final JComboBox<UserBet> betsComboBox = new JComboBox<>();
	private final JComboBox<User> friendComboBox = new JComboBox<User>();
	private final JButton removeFriendBtn = new JButton("Remove friend");
	private final JLabel favoriteSportsLbl = new JLabel("Favorite sports");
	private final JComboBox<Sport> sportsComboBox = new JComboBox<Sport>();
	private final JTextField sportField = new JTextField();
	private final JLabel sportLbl = new JLabel("New favorite sport");
	private final JButton sportBtn = new JButton("Add");
	private final JLabel budgetField = new JLabel("");
	private final JLabel moneySpentLbl = new JLabel("Money spent :");
	private final JLabel moneySpentField = new JLabel("");
	private final JCheckBox budgetCheckBox = new JCheckBox(" Budget");
	private final JTextArea textArea = new JTextArea();
	private final JTextArea textAreaFriend = new JTextArea();

	/**
	 * Create the frame.
	 */
	public UserInformationGUI(User user, FindQuestionsGUI fq) {
		sportField.setColumns(10);
		addFriendField.setColumns(10);
		currentUser = user;
		mainWindow = fq;
		setTitle("Bet & Ruin");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		setContentPane(panel);
		setSize(1200, 650);
		self = this;

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 87, 20, 50, 80, 72, 194, 52, 91, 50, 78 };
		gbl_panel.rowHeights = new int[] { 20, 35, 0, 35, 35, 35, 35, 36, 35, 49, 43, 35, 36, 34, 20 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.5, 1.0, 0.0, 1.0, 0.0, 1.0, 0.5, 0.0 };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.5, 0.5, 1.0, 0.0, 1.0, 0.0, 0.5, 0.0, 0.0, 0.0,
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
		gbc_deleteAccountBtn.fill = GridBagConstraints.BOTH;
		gbc_deleteAccountBtn.insets = new Insets(0, 0, 5, 0);
		gbc_deleteAccountBtn.gridx = 9;
		gbc_deleteAccountBtn.gridy = 0;
		deleteAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				businessLogic.deleteUser(currentUser);
				self.setVisible(false);
				mainWindow.setVisible(false);
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
		gbc_editButton.gridwidth = 3;
		gbc_editButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editButton.insets = new Insets(0, 0, 5, 5);
		gbc_editButton.gridx = 1;
		gbc_editButton.gridy = 2;
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserEditInfoGUI userEditInfoGUI = new UserEditInfoGUI(currentUser, self);
				userEditInfoGUI.setBusinessLogic(businessLogic);
				userEditInfoGUI.setVisible(true);
			}
		});
		editButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(editButton, gbc_editButton);

		GridBagConstraints gbc_favoriteSportsLbl = new GridBagConstraints();
		gbc_favoriteSportsLbl.insets = new Insets(0, 0, 5, 5);
		gbc_favoriteSportsLbl.gridx = 5;
		gbc_favoriteSportsLbl.gridy = 2;
		favoriteSportsLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(favoriteSportsLbl, gbc_favoriteSportsLbl);

		GridBagConstraints gbc_friendsLbl = new GridBagConstraints();
		gbc_friendsLbl.gridwidth = 2;
		gbc_friendsLbl.insets = new Insets(0, 0, 5, 5);
		gbc_friendsLbl.gridx = 7;
		gbc_friendsLbl.gridy = 2;
		friendsLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(friendsLbl, gbc_friendsLbl);

		usernameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GridBagConstraints gbc_usernameLbl = new GridBagConstraints();
		gbc_usernameLbl.anchor = GridBagConstraints.EAST;
		gbc_usernameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLbl.gridx = 1;
		gbc_usernameLbl.gridy = 3;
		panel.add(usernameLbl, gbc_usernameLbl);

		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.gridwidth = 2;
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.gridx = 2;
		gbc_usernameField.gridy = 3;
		usernameField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(usernameField, gbc_usernameField);

		GridBagConstraints gbc_sportsComboBox = new GridBagConstraints();
		gbc_sportsComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_sportsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_sportsComboBox.gridx = 5;
		gbc_sportsComboBox.gridy = 3;
		panel.add(sportsComboBox, gbc_sportsComboBox);

		GridBagConstraints gbc_addFriendField = new GridBagConstraints();
		gbc_addFriendField.insets = new Insets(0, 0, 5, 5);
		gbc_addFriendField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addFriendField.gridx = 7;
		gbc_addFriendField.gridy = 3;
		panel.add(addFriendField, gbc_addFriendField);

		GridBagConstraints gbc_addFriendBtn = new GridBagConstraints();
		gbc_addFriendBtn.insets = new Insets(0, 0, 5, 5);
		gbc_addFriendBtn.gridx = 8;
		gbc_addFriendBtn.gridy = 3;
		addFriendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (addFriendField.getText().equals(""))
					return;
				if (businessLogic.addFriend(currentUser, addFriendField.getText()))
					textAreaFriend.setText("Added");
				else
					textAreaFriend.setText("No user found");

				Vector<User> friends = businessLogic.getFriends(currentUser);
				friendComboBox.setModel(new DefaultComboBoxModel<User>(friends));
				friendComboBox.repaint();
			}
		});
		panel.add(addFriendBtn, gbc_addFriendBtn);

		GridBagConstraints gbc_emailLbl = new GridBagConstraints();
		gbc_emailLbl.anchor = GridBagConstraints.EAST;
		gbc_emailLbl.insets = new Insets(0, 0, 5, 5);
		gbc_emailLbl.gridx = 1;
		gbc_emailLbl.gridy = 4;
		emailLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(emailLbl, gbc_emailLbl);

		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.gridwidth = 2;
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.gridx = 2;
		gbc_emailField.gridy = 4;
		emailField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(emailField, gbc_emailField);

		GridBagConstraints gbc_friendComboBox = new GridBagConstraints();
		gbc_friendComboBox.gridwidth = 2;
		gbc_friendComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_friendComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_friendComboBox.gridx = 7;
		gbc_friendComboBox.gridy = 4;
		panel.add(friendComboBox, gbc_friendComboBox);

		passwordLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.anchor = GridBagConstraints.EAST;
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 1;
		gbc_passwordLbl.gridy = 5;
		panel.add(passwordLbl, gbc_passwordLbl);

		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 5;
		passwordField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(passwordField, gbc_passwordField);

		GridBagConstraints gbc_removeFriendBtn = new GridBagConstraints();
		gbc_removeFriendBtn.gridwidth = 2;
		gbc_removeFriendBtn.insets = new Insets(0, 0, 5, 5);
		gbc_removeFriendBtn.gridx = 7;
		gbc_removeFriendBtn.gridy = 5;
		removeFriendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User friend = (User) friendComboBox.getSelectedItem();
				if (friend == null)
					return;
				businessLogic.removeFriend(currentUser, friend);
				Vector<User> friends = businessLogic.getFriends(currentUser);
				friendComboBox.setModel(new DefaultComboBoxModel<User>(friends));
				friendComboBox.repaint();
			}
		});

		GridBagConstraints gbc_sportLbl = new GridBagConstraints();
		gbc_sportLbl.insets = new Insets(0, 0, 5, 5);
		gbc_sportLbl.gridx = 5;
		gbc_sportLbl.gridy = 5;
		sportLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(sportLbl, gbc_sportLbl);
		panel.add(removeFriendBtn, gbc_removeFriendBtn);

		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.anchor = GridBagConstraints.EAST;
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.gridx = 1;
		gbc_nameLbl.gridy = 6;
		nameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nameLbl, gbc_nameLbl);

		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 2;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.gridx = 2;
		gbc_nameField.gridy = 6;
		nameField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nameField, gbc_nameField);

		GridBagConstraints gbc_sportField = new GridBagConstraints();
		gbc_sportField.insets = new Insets(0, 0, 5, 5);
		gbc_sportField.fill = GridBagConstraints.HORIZONTAL;
		gbc_sportField.gridx = 5;
		gbc_sportField.gridy = 6;
		panel.add(sportField, gbc_sportField);
		
		GridBagConstraints gbc_textAreaFriend = new GridBagConstraints();
		gbc_textAreaFriend.gridwidth = 2;
		gbc_textAreaFriend.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaFriend.fill = GridBagConstraints.BOTH;
		gbc_textAreaFriend.gridx = 7;
		gbc_textAreaFriend.gridy = 6;
		textAreaFriend.setEditable(false);
		panel.add(textAreaFriend, gbc_textAreaFriend);

		GridBagConstraints gbc_familyNameLbl = new GridBagConstraints();
		gbc_familyNameLbl.anchor = GridBagConstraints.EAST;
		gbc_familyNameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_familyNameLbl.gridx = 1;
		gbc_familyNameLbl.gridy = 7;
		familyNameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(familyNameLbl, gbc_familyNameLbl);

		GridBagConstraints gbc_familyNameField = new GridBagConstraints();
		gbc_familyNameField.gridwidth = 2;
		gbc_familyNameField.insets = new Insets(0, 0, 5, 5);
		gbc_familyNameField.gridx = 2;
		gbc_familyNameField.gridy = 7;
		familyNameField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(familyNameField, gbc_familyNameField);

		GridBagConstraints gbc_sportBtn = new GridBagConstraints();
		gbc_sportBtn.insets = new Insets(0, 0, 5, 5);
		gbc_sportBtn.gridx = 5;
		gbc_sportBtn.gridy = 7;
		sportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sportField.getText().equals(""))
					return;
				
				String text = "No sport found";
				String newSport = sportField.getText();
				List<Sport> sports = businessLogic.getAllSport();
				for (Sport s : sports) {
					if (s.getSportName().equals(newSport)) {
						currentUser = businessLogic.addUserSport(s, currentUser);
						text = "Added " + newSport;
						break;
					}
				}
				textArea.setText(text);
				refresh(currentUser);
			}
		});
		panel.add(sportBtn, gbc_sportBtn);

		GridBagConstraints gbc_nationalityLbl = new GridBagConstraints();
		gbc_nationalityLbl.anchor = GridBagConstraints.EAST;
		gbc_nationalityLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nationalityLbl.gridx = 1;
		gbc_nationalityLbl.gridy = 8;
		nationalityLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nationalityLbl, gbc_nationalityLbl);

		GridBagConstraints gbc_nationalityField = new GridBagConstraints();
		gbc_nationalityField.gridwidth = 2;
		gbc_nationalityField.insets = new Insets(0, 0, 5, 5);
		gbc_nationalityField.gridx = 2;
		gbc_nationalityField.gridy = 8;
		nationalityField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nationalityField, gbc_nationalityField);
		
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 5;
		gbc_textArea.gridy = 8;
		textArea.setEditable(false);
		panel.add(textArea, gbc_textArea);

		GridBagConstraints gbc_betsLbl = new GridBagConstraints();
		gbc_betsLbl.gridwidth = 2;
		gbc_betsLbl.insets = new Insets(0, 0, 5, 5);
		gbc_betsLbl.gridx = 7;
		gbc_betsLbl.gridy = 8;
		betsLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(betsLbl, gbc_betsLbl);

		GridBagConstraints gbc_ageLbl = new GridBagConstraints();
		gbc_ageLbl.anchor = GridBagConstraints.EAST;
		gbc_ageLbl.insets = new Insets(0, 0, 5, 5);
		gbc_ageLbl.gridx = 1;
		gbc_ageLbl.gridy = 9;
		ageLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(ageLbl, gbc_ageLbl);

		GridBagConstraints gbc_birthField = new GridBagConstraints();
		gbc_birthField.gridwidth = 2;
		gbc_birthField.insets = new Insets(0, 0, 5, 5);
		gbc_birthField.gridx = 2;
		gbc_birthField.gridy = 9;
		birthField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(birthField, gbc_birthField);

		GridBagConstraints gbc_betsComboBox = new GridBagConstraints();
		gbc_betsComboBox.gridwidth = 2;
		gbc_betsComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_betsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_betsComboBox.gridx = 7;
		gbc_betsComboBox.gridy = 9;
		panel.add(betsComboBox, gbc_betsComboBox);

		GridBagConstraints gbc_creditCardLbl = new GridBagConstraints();
		gbc_creditCardLbl.anchor = GridBagConstraints.EAST;
		gbc_creditCardLbl.insets = new Insets(0, 0, 5, 5);
		gbc_creditCardLbl.gridx = 1;
		gbc_creditCardLbl.gridy = 10;
		creditCardLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(creditCardLbl, gbc_creditCardLbl);

		GridBagConstraints gbc_creditCardField = new GridBagConstraints();
		gbc_creditCardField.gridwidth = 2;
		gbc_creditCardField.insets = new Insets(0, 0, 5, 5);
		gbc_creditCardField.gridx = 2;
		gbc_creditCardField.gridy = 10;
		creditCardField.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(creditCardField, gbc_creditCardField);

		GridBagConstraints gbc_removeBetBtn = new GridBagConstraints();
		gbc_removeBetBtn.gridwidth = 2;
		gbc_removeBetBtn.insets = new Insets(0, 0, 5, 5);
		gbc_removeBetBtn.gridx = 7;
		gbc_removeBetBtn.gridy = 10;
		removeBetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserBet bet = (UserBet) betsComboBox.getSelectedItem();
				if (bet == null)
					return;
				businessLogic.removeUserBet(bet);
				Vector<UserBet> bets = businessLogic.getUserBets(currentUser);
				betsComboBox.setModel(new DefaultComboBoxModel<UserBet>(bets));
				betsComboBox.repaint();
			}
		});
		panel.add(removeBetBtn, gbc_removeBetBtn);

		// refresh(currentUser);

		GridBagConstraints gbc_moneyLbl = new GridBagConstraints();
		gbc_moneyLbl.anchor = GridBagConstraints.EAST;
		gbc_moneyLbl.insets = new Insets(0, 0, 5, 5);
		gbc_moneyLbl.gridx = 1;
		gbc_moneyLbl.gridy = 11;
		moneyLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(moneyLbl, gbc_moneyLbl);

		GridBagConstraints gbc_moneyField = new GridBagConstraints();
		gbc_moneyField.gridwidth = 2;
		gbc_moneyField.insets = new Insets(0, 0, 5, 5);
		gbc_moneyField.gridx = 2;
		gbc_moneyField.gridy = 11;
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

		GridBagConstraints gbc_budgetCheckBox = new GridBagConstraints();
		gbc_budgetCheckBox.anchor = GridBagConstraints.EAST;
		gbc_budgetCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_budgetCheckBox.gridx = 1;
		gbc_budgetCheckBox.gridy = 12;
		budgetCheckBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		budgetCheckBox.setEnabled(false);
		panel.add(budgetCheckBox, gbc_budgetCheckBox);

		GridBagConstraints gbc_budgetField = new GridBagConstraints();
		gbc_budgetField.gridwidth = 2;
		gbc_budgetField.insets = new Insets(0, 0, 5, 5);
		gbc_budgetField.gridx = 2;
		gbc_budgetField.gridy = 12;
		panel.add(budgetField, gbc_budgetField);

		GridBagConstraints gbc_moneySpentLbl = new GridBagConstraints();
		gbc_moneySpentLbl.anchor = GridBagConstraints.EAST;
		gbc_moneySpentLbl.insets = new Insets(0, 0, 5, 5);
		gbc_moneySpentLbl.gridx = 1;
		gbc_moneySpentLbl.gridy = 13;
		moneySpentLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(moneySpentLbl, gbc_moneySpentLbl);

		GridBagConstraints gbc_moneySpentField = new GridBagConstraints();
		gbc_moneySpentField.gridwidth = 2;
		gbc_moneySpentField.insets = new Insets(0, 0, 5, 5);
		gbc_moneySpentField.gridx = 2;
		gbc_moneySpentField.gridy = 13;
		panel.add(moneySpentField, gbc_moneySpentField);
		closeBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		panel.add(closeBtn, gbc_closeBtn);

	}

	public void refresh(User currentUser) {

		currentUser = businessLogic.checkLogin(currentUser.getUsername(), "");

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

		if (currentUser.isBudgetBool())
			budgetCheckBox.setSelected(true);
		else
			budgetCheckBox.setSelected(false);

		budgetField.setText(String.valueOf(currentUser.getBudget()));
		moneySpentField.setText(String.valueOf(currentUser.getMoneySpentPerMonth()));

		/*
		 * Vector<User> friends = businessLogic.getFriends(currentUser); Vector<UserBet>
		 * bets = businessLogic.getUserBets(currentUser); Vector<Sport> sports =
		 * businessLogic.getUserPreferences(currentUser);
		 */

		friendComboBox.setModel(new DefaultComboBoxModel<User>(currentUser.getFriends()));
		betsComboBox.setModel(new DefaultComboBoxModel<UserBet>(currentUser.getBets()));
		sportsComboBox.setModel(new DefaultComboBoxModel<Sport>(currentUser.getPreferences()));
	}

	public void setBusinessLogic(BLFacade businessLogic) {
		this.businessLogic = businessLogic;

		refresh(currentUser);
	}

}
