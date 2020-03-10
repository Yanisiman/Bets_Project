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

	private final JButton editBtn = new JButton("Edit");
	private final JTextArea errorArea = new JTextArea();

	private final JTextField moneyField = new JTextField();

	private UserEditInfoGUI self;
	private BLFacade businessLogic;
	private User currentUser;
	private UserInformationGUI info;
	private final JTextField budgetField = new JTextField();
	private final JLabel budgetLbl = new JLabel("Budget :");
	private final JLabel addMoneyLbl = new JLabel("Add Money :");


	/**
	 * Create the frame.
	 */
	public UserEditInfoGUI(User user, UserInformationGUI uInfo) {
		budgetField.setColumns(10);
		
		currentUser = user;
		info = uInfo;
		
		familyNameField.setColumns(10);
		nameField.setColumns(10);
		moneyField.setColumns(10);
		setTitle("Bet & Ruin");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		setContentPane(panel);
		setSize(600, 650);
		self = this;

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 55, 89, 297, 53, 43, 50 };
		gbl_panel.rowHeights = new int[] { 20, 35, 0, 35, 35, 35, 35, 36, 35, 49, 43, 0, 35, 64, 20 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.5, 0.5, 0.5 };
		gbl_panel.rowWeights = new double[] { 1.0, 0.5, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0, 0.5, 0.0, 0.5, 0.0, 0.5, 0.0,
				1.0 };
		panel.setLayout(gbl_panel);

		betAndRuinLbl.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		GridBagConstraints gbc_betAndRuinLbl = new GridBagConstraints();
		gbc_betAndRuinLbl.insets = new Insets(0, 0, 5, 5);
		gbc_betAndRuinLbl.gridx = 3;
		gbc_betAndRuinLbl.gridy = 0;
		panel.add(betAndRuinLbl, gbc_betAndRuinLbl);

		GridBagConstraints gbc_registrationLbl = new GridBagConstraints();
		gbc_registrationLbl.insets = new Insets(0, 0, 5, 5);
		gbc_registrationLbl.gridx = 3;
		gbc_registrationLbl.gridy = 1;
		registrationLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(registrationLbl, gbc_registrationLbl);

		usernameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GridBagConstraints gbc_usernameLbl = new GridBagConstraints();
		gbc_usernameLbl.anchor = GridBagConstraints.EAST;
		gbc_usernameLbl.gridwidth = 2;
		gbc_usernameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLbl.gridx = 1;
		gbc_usernameLbl.gridy = 3;
		panel.add(usernameLbl, gbc_usernameLbl);

		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.gridwidth = 2;
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.gridx = 3;
		gbc_usernameField.gridy = 3;
		panel.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);

		GridBagConstraints gbc_oldPasswordLbl = new GridBagConstraints();
		gbc_oldPasswordLbl.gridwidth = 2;
		gbc_oldPasswordLbl.anchor = GridBagConstraints.EAST;
		gbc_oldPasswordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_oldPasswordLbl.gridx = 1;
		gbc_oldPasswordLbl.gridy = 4;
		oldPasswordLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(oldPasswordLbl, gbc_oldPasswordLbl);

		GridBagConstraints gbc_oldPasswordField = new GridBagConstraints();
		gbc_oldPasswordField.gridwidth = 2;
		gbc_oldPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_oldPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_oldPasswordField.gridx = 3;
		gbc_oldPasswordField.gridy = 4;
		panel.add(oldPasswordField, gbc_oldPasswordField);

		passwordLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GridBagConstraints gbc_passwordLbl = new GridBagConstraints();
		gbc_passwordLbl.anchor = GridBagConstraints.EAST;
		gbc_passwordLbl.gridwidth = 2;
		gbc_passwordLbl.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLbl.gridx = 1;
		gbc_passwordLbl.gridy = 5;
		panel.add(passwordLbl, gbc_passwordLbl);

		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 5;
		panel.add(passwordField, gbc_passwordField);

		GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.anchor = GridBagConstraints.EAST;
		gbc_nameLbl.gridwidth = 2;
		gbc_nameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nameLbl.gridx = 1;
		gbc_nameLbl.gridy = 6;
		nameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nameLbl, gbc_nameLbl);

		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 2;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 6;
		panel.add(nameField, gbc_nameField);

		GridBagConstraints gbc_familyNameLbl = new GridBagConstraints();
		gbc_familyNameLbl.anchor = GridBagConstraints.EAST;
		gbc_familyNameLbl.gridwidth = 2;
		gbc_familyNameLbl.insets = new Insets(0, 0, 5, 5);
		gbc_familyNameLbl.gridx = 1;
		gbc_familyNameLbl.gridy = 7;
		familyNameLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(familyNameLbl, gbc_familyNameLbl);

		GridBagConstraints gbc_familyNameField = new GridBagConstraints();
		gbc_familyNameField.gridwidth = 2;
		gbc_familyNameField.insets = new Insets(0, 0, 5, 5);
		gbc_familyNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_familyNameField.gridx = 3;
		gbc_familyNameField.gridy = 7;
		panel.add(familyNameField, gbc_familyNameField);

		GridBagConstraints gbc_creditCardLbl = new GridBagConstraints();
		gbc_creditCardLbl.gridwidth = 2;
		gbc_creditCardLbl.anchor = GridBagConstraints.EAST;
		gbc_creditCardLbl.insets = new Insets(0, 0, 5, 5);
		gbc_creditCardLbl.gridx = 1;
		gbc_creditCardLbl.gridy = 8;
		creditCardLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(creditCardLbl, gbc_creditCardLbl);
		creditCardField.setColumns(10);

		GridBagConstraints gbc_creditCardField = new GridBagConstraints();
		gbc_creditCardField.gridwidth = 2;
		gbc_creditCardField.insets = new Insets(0, 0, 5, 5);
		gbc_creditCardField.fill = GridBagConstraints.HORIZONTAL;
		gbc_creditCardField.gridx = 3;
		gbc_creditCardField.gridy = 8;
		panel.add(creditCardField, gbc_creditCardField);
		
		GridBagConstraints gbc_addMoneyLbl = new GridBagConstraints();
		gbc_addMoneyLbl.gridwidth = 2;
		gbc_addMoneyLbl.insets = new Insets(0, 0, 5, 5);
		gbc_addMoneyLbl.anchor = GridBagConstraints.EAST;
		gbc_addMoneyLbl.gridx = 1;
		gbc_addMoneyLbl.gridy = 9;
		addMoneyLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(addMoneyLbl, gbc_addMoneyLbl);
		
		
		GridBagConstraints gbc_moneyField = new GridBagConstraints();
		gbc_moneyField.gridwidth = 2;
		gbc_moneyField.insets = new Insets(0, 0, 5, 5);
		gbc_moneyField.fill = GridBagConstraints.HORIZONTAL;
		gbc_moneyField.gridx = 3;
		gbc_moneyField.gridy = 9;
		panel.add(moneyField, gbc_moneyField);

		GridBagConstraints gbc_editBtn = new GridBagConstraints();
		gbc_editBtn.fill = GridBagConstraints.BOTH;
		gbc_editBtn.insets = new Insets(0, 0, 5, 5);
		gbc_editBtn.gridx = 3;
		gbc_editBtn.gridy = 11;
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!usernameField.getText().equals("")) {
					if (businessLogic.checkLogin(usernameField.getText(), "") != null) {
						errorArea.setText("Username already taken by another user");
						return;
					}
				}				
				
				if (!oldPasswordField.getText().equals("") && !passwordField.getText().equals("")) {
					if (!oldPasswordField.getText().equals(currentUser.getPassword())){
						errorArea.setText("Old password given is not the good one");
						return;
					}
				}
				
				if (!moneyField.getText().equals("")) {
					try {
						int money = Integer.parseInt(moneyField.getText());
						businessLogic.addMoney(currentUser, money);
						currentUser.updateMoney(money);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				
				int budget = 0;
				if (!budgetField.getText().equals("")) {
					try {
						budget = Integer.parseInt(budgetField.getText());
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				
				currentUser = businessLogic.updateUser(currentUser.getEmail(), usernameField.getText(), passwordField.getText(),
						nameField.getText(), familyNameField.getText(), creditCardField.getText(), currentUser.getMoney(), budget);
				self.setVisible(false);
				info.refresh(currentUser);				
			}
		});
		
		GridBagConstraints gbc_budgetLbl = new GridBagConstraints();
		gbc_budgetLbl.gridwidth = 2;
		gbc_budgetLbl.insets = new Insets(0, 0, 5, 5);
		gbc_budgetLbl.anchor = GridBagConstraints.EAST;
		gbc_budgetLbl.gridx = 1;
		gbc_budgetLbl.gridy = 10;
		budgetLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(budgetLbl, gbc_budgetLbl);
		
		
		GridBagConstraints gbc_budgetField = new GridBagConstraints();
		gbc_budgetField.gridwidth = 2;
		gbc_budgetField.insets = new Insets(0, 0, 5, 5);
		gbc_budgetField.fill = GridBagConstraints.HORIZONTAL;
		gbc_budgetField.gridx = 3;
		gbc_budgetField.gridy = 10;
		panel.add(budgetField, gbc_budgetField);
		panel.add(editBtn, gbc_editBtn);

		GridBagConstraints gbc_errorArea = new GridBagConstraints();
		gbc_errorArea.gridwidth = 4;
		gbc_errorArea.insets = new Insets(0, 0, 5, 5);
		gbc_errorArea.fill = GridBagConstraints.BOTH;
		gbc_errorArea.gridx = 2;
		gbc_errorArea.gridy = 13;
		errorArea.setEditable(false);
		panel.add(errorArea, gbc_errorArea);

	}

	public void setBusinessLogic(BLFacade businessLogic) {
		this.businessLogic = businessLogic;
	}

}
