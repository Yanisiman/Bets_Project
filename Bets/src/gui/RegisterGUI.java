package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.User;

public class RegisterGUI extends JFrame {

	private JPanel panel;
	private JTextField usernameField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel betAndRuinLbl = new JLabel("BET & RUIN");
	private JLabel usernameLbl = new JLabel("Username :");
	private JLabel passwordLbl = new JLabel("Password :");

	private final JLabel registrationLbl = new JLabel("Registration : Enter all your information");
	private final JLabel emailLbl = new JLabel("Email :");
	private final JTextField emailField = new JTextField();
	private final JLabel nameLbl = new JLabel("Name :");
	private final JTextField nameField = new JTextField();
	private final JLabel familyNameLbl = new JLabel("Family Name :");
	private final JTextField familyNameField = new JTextField();
	private final JLabel nationalityLbl = new JLabel("Nationality :");
	private final JTextField nationalityField = new JTextField();
	private final JLabel ageLbl = new JLabel("Birth date (DD/MM/YYYY) :");
	private final JTextField ageField = new JTextField();
	private final JLabel creditCardLbl = new JLabel("Credit card :");
	private final JTextField creditCardField = new JTextField();
	private final JButton registerBtn = new JButton("Register");
	private final JTextArea errorArea = new JTextArea();

	private RegisterGUI self;
	private BLFacade businessLogic;
	private User user;

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		creditCardField.setColumns(10);
		ageField.setColumns(10);
		nationalityField.setColumns(10);
		familyNameField.setColumns(10);
		nameField.setColumns(10);
		emailField.setColumns(10);
		setTitle("Bet & Ruin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		setContentPane(panel);
		setSize(800, 650);
		self = this;

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 20, 50, 80, 68, 297, 66, 35, 30, 50, 20 };
		gbl_panel.rowHeights = new int[] { 20, 35, 0, 35, 35, 35, 35, 36, 35, 49, 43, 0, 35, 43, 20 };
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

		GridBagConstraints gbc_emailLbl = new GridBagConstraints();
		gbc_emailLbl.anchor = GridBagConstraints.EAST;
		gbc_emailLbl.gridwidth = 2;
		gbc_emailLbl.insets = new Insets(0, 0, 5, 5);
		gbc_emailLbl.gridx = 2;
		gbc_emailLbl.gridy = 4;
		emailLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(emailLbl, gbc_emailLbl);

		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.gridwidth = 3;
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 4;
		gbc_emailField.gridy = 4;
		panel.add(emailField, gbc_emailField);

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

		GridBagConstraints gbc_nationalityLbl = new GridBagConstraints();
		gbc_nationalityLbl.anchor = GridBagConstraints.EAST;
		gbc_nationalityLbl.gridwidth = 2;
		gbc_nationalityLbl.insets = new Insets(0, 0, 5, 5);
		gbc_nationalityLbl.gridx = 2;
		gbc_nationalityLbl.gridy = 8;
		nationalityLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(nationalityLbl, gbc_nationalityLbl);

		GridBagConstraints gbc_nationalityField = new GridBagConstraints();
		gbc_nationalityField.gridwidth = 3;
		gbc_nationalityField.insets = new Insets(0, 0, 5, 5);
		gbc_nationalityField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nationalityField.gridx = 4;
		gbc_nationalityField.gridy = 8;
		panel.add(nationalityField, gbc_nationalityField);

		GridBagConstraints gbc_ageLbl = new GridBagConstraints();
		gbc_ageLbl.anchor = GridBagConstraints.EAST;
		gbc_ageLbl.gridwidth = 2;
		gbc_ageLbl.insets = new Insets(0, 0, 5, 5);
		gbc_ageLbl.gridx = 2;
		gbc_ageLbl.gridy = 9;
		ageLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(ageLbl, gbc_ageLbl);

		GridBagConstraints gbc_ageField = new GridBagConstraints();
		gbc_ageField.gridwidth = 3;
		gbc_ageField.insets = new Insets(0, 0, 5, 5);
		gbc_ageField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ageField.gridx = 4;
		gbc_ageField.gridy = 9;
		panel.add(ageField, gbc_ageField);

		GridBagConstraints gbc_creditCardLbl = new GridBagConstraints();
		gbc_creditCardLbl.anchor = GridBagConstraints.EAST;
		gbc_creditCardLbl.gridwidth = 2;
		gbc_creditCardLbl.insets = new Insets(0, 0, 5, 5);
		gbc_creditCardLbl.gridx = 2;
		gbc_creditCardLbl.gridy = 10;
		creditCardLbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(creditCardLbl, gbc_creditCardLbl);

		GridBagConstraints gbc_creditCardField = new GridBagConstraints();
		gbc_creditCardField.gridwidth = 3;
		gbc_creditCardField.insets = new Insets(0, 0, 5, 5);
		gbc_creditCardField.fill = GridBagConstraints.HORIZONTAL;
		gbc_creditCardField.gridx = 4;
		gbc_creditCardField.gridy = 10;
		panel.add(creditCardField, gbc_creditCardField);

		GridBagConstraints gbc_registerBtn = new GridBagConstraints();
		gbc_registerBtn.fill = GridBagConstraints.BOTH;
		gbc_registerBtn.insets = new Insets(0, 0, 5, 5);
		gbc_registerBtn.gridx = 4;
		gbc_registerBtn.gridy = 12;
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String name = nameField.getText();
				String familyName = familyNameField.getText();
				String password = passwordField.getText();
				String nationality = nationalityField.getText();
				String email = emailField.getText();
				String username = usernameField.getText();
				String creditCard = creditCardField.getText();
				String birthDate = ageField.getText();

				if (name.equals("") || familyName.equals("") || password.equals("") || nationality.equals("")
						|| email.equals("") || username.equals("") || creditCard.equals("") || birthDate.equals("")) {
					errorArea.setText("One or more information incorrect");
					return;
				}

				SimpleDateFormat format = new SimpleDateFormat("dd'/'MM'/'yyyy", Locale.ENGLISH);
				try {
					Date birth = format.parse(birthDate);
					LocalDate d = LocalDate.of(birth.getYear(), birth.getMonth(), birth.getDay());
					LocalDate now = LocalDate.now();
					Period p = Period.between(d, now);
					String age_string = String.valueOf(p.getYears()).substring(2);
					int age = Integer.parseInt(age_string);

					User newUser = new User(name, familyName, age, password, nationality, email, username, creditCard,
							birthDate);
					businessLogic.createUser(newUser);

					self.setVisible(false);

					RulesGUI rulesGUI = new RulesGUI();
					rulesGUI.setBusinessLogic(businessLogic);
					rulesGUI.setVisible(true);
					rulesGUI.setUser(newUser);

				} catch (DateTimeParseException e) {
					// TODO: handle exception
					errorArea.setText("Birth date is incorrect");
					return;
				} catch (Exception e) {
					// TODO: handle exception
					errorArea.setText("An error occured : " + e.getMessage());
					return;
				}
			}
		});
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

	public void setBusinessLogic(BLFacade bl) {
		businessLogic = bl;
	}
	


}
