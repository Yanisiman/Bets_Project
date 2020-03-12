package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Event;
import domain.Sport;
import domain.User;
import domain.UserBet;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class SportGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JLabel lblNewLabel_1 = new JLabel("SELECT A SPORT :");
	private JPanel contentPane;
	private JLabel newSportLabel = new JLabel("New sport :");
	private JButton nextButton = new JButton("NEXT");
	private JButton addButton = new JButton("ADD");
	private JTextArea textArea = new JTextArea();
	private JComboBox <String> comboBox = new JComboBox<String>();
	private final JButton removeButton = new JButton("REMOVE");
	private JTextField textField = new JTextField();
	private BLFacade businessLogic;
	private User user;
	private SportGUI self;
	private boolean b;
	private int n = 0;
	private String string = "";
	

	
	public SportGUI(BLFacade businessLogic, User user) {
		this.self = this;
		this.businessLogic = businessLogic;
		this.user = user;
		
		
		displaySports();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{69, 58, 130, 137, 76, 69, 0};
		gbl_panel.rowHeights = new int[]{30, 14, 35, 22, 65, 23, 22, 35, 0, 23, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.insets = new Insets(0, 0, 5, 5);
		gbc_nextButton.gridx = 2;
		gbc_nextButton.gridy = 5;
		panel.add(nextButton, gbc_nextButton);
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex() == -1) {
					textArea.setText("Choose a sport");
				}
				else {
					String sportname = comboBox.getSelectedItem().toString();
					Sport sport = businessLogic.getSport(sportname);
					if (sport == null)
					{
						textArea.setText("You didn't choose a sport");
						return;
					}
					
					if(b == false) {
						FindQuestionsGUI findQuestionsGUI = new FindQuestionsGUI(user, businessLogic, sport );
						findQuestionsGUI.setBusinessLogic(businessLogic); 
						findQuestionsGUI.setSport(sport);
						findQuestionsGUI.setCurrentUser(user);
						self.setVisible(false);
						findQuestionsGUI.setVisible(true);
						System.out.print("User/Spectator selects the sport " + sportname);
					}
					else {
						CreateQuestionGUI createQuestionGUI = new CreateQuestionGUI(user, businessLogic, sport); 
						createQuestionGUI.setSport(sport);
						createQuestionGUI.setBusinessogic(businessLogic);
						createQuestionGUI.setCurrentUser(user);
						self.setVisible(false);
						createQuestionGUI.setVisible(true);
						System.out.print("Admin selects the sport " + sportname);
					}
				}
				
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sportString = comboBox.getSelectedItem().toString();
				
				if(comboBox.getSelectedIndex() == -1)
					textArea.setText("Select a sport to delete");
				else {
					if(n == 1 && string.equals(sportString)) {
						businessLogic.removeSport(sportString);
						comboBox.removeItem(sportString);
						textArea.setText("" +sportString + " removed");
						string = "";
						n = 0;
					}
					else {
						if(n== 1 && string.equals(sportString) == false)
							n = 0;
						textArea.setText("Are you sure to remove " + sportString + " ? The events related to it will be also removed.");
						string = sportString;
						n = 1;
					}
					
				}
			}
		});
		
		GridBagConstraints gbc_removeButton = new GridBagConstraints();
		gbc_removeButton.insets = new Insets(0, 0, 5, 5);
		gbc_removeButton.gridx = 3;
		gbc_removeButton.gridy = 5;
		panel.add(removeButton, gbc_removeButton);
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridwidth = 4;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 6;
		panel.add(textArea, gbc_textArea);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newSport = textField.getText();
				
				if(newSport.isEmpty() == false) {
					boolean b = businessLogic.alreadyExist(newSport);
					if(b == false) {
						Sport sport = new Sport(newSport);
						businessLogic.addSport(sport);
						comboBox.addItem(sport.getSportName());
						textArea.setText("" + newSport + " is a new sport");
					}
					else {
						textArea.setText("" + newSport + " already exists");
					}
				}
				else {
					textArea.setText("Write a sport");
				}
			}
		});
		GridBagConstraints gbc_newSportLabel = new GridBagConstraints();
		gbc_newSportLabel.insets = new Insets(0, 0, 5, 5);
		gbc_newSportLabel.gridx = 1;
		gbc_newSportLabel.gridy = 9;
		panel.add(newSportLabel, gbc_newSportLabel);
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridwidth = 2;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 9;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 5, 5);
		gbc_addButton.gridx = 4;	
		gbc_addButton.gridy = 9;
		panel.add(addButton, gbc_addButton);

	}
	
	
	
	private void displaySports() {
		user = businessLogic.checkLogin(user.getUsername(), "");
		comboBox.addItem("Favorite sports");
		Vector<Sport> preferences = businessLogic.getUserPreferences(user);
		for (Sport sport: preferences)
			comboBox.addItem(sport.getSportName());
			
		comboBox.addItem("-----------------");
		comboBox.addItem("All Sports");
		List<Sport> allSport = businessLogic.getAllSport();
		for(Sport s : allSport) {
				comboBox.addItem(s.getSportName());
		}
		
	}



	public void setBusinessLogic(BLFacade business_logic) {
		businessLogic = business_logic;
	}
	
	public void setUser (User user) {
		this.user = user;
	}
	
	public void setB (boolean b) {
		this.b = b;
		if(b == false) {
			newSportLabel.setVisible(false);
			textField.setVisible(false);
			addButton.setVisible(false);
			removeButton.setVisible(false);
		}
	}

}
