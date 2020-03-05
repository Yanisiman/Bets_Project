package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class SportGUI extends JFrame {

	private JPanel panel = new JPanel();
	private JLabel lblNewLabel_1 = new JLabel("SELECT A SPORT :");
	private JPanel contentPane;
	private JLabel newSportLabel = new JLabel("New sport :");
	private JButton nextButton = new JButton("NEXT");
	private JButton addButton = new JButton("ADD");
	private JTextArea textArea = new JTextArea();
	private JComboBox comboBox = new JComboBox();
	private JTextField textField = new JTextField();
	private BLFacade businessLogic;
	private User user;
	private SportGUI self;
	/**
	 * Create the frame.
	 *
	 */
	
	public SportGUI() {
		
		self = this;
		
		/*if(!user.isAdmin()) {
			newSportLabel.setVisible(false);
			textField.setVisible(false);
			addButton.setVisible(false);
		}
		else {
			newSportLabel.setVisible(true);
			textField.setVisible(true);
			addButton.setVisible(true);
		}*/
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 8;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridwidth = 9;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 6;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		
		
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.gridwidth = 2;
		gbc_nextButton.insets = new Insets(0, 0, 5, 5);
		gbc_nextButton.gridx = 9;
		gbc_nextButton.gridy = 8;
		panel.add(nextButton, gbc_nextButton);
		
		
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 8;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 6;
		gbc_textArea.gridy = 9;
		panel.add(textArea, gbc_textArea);
		
		
		GridBagConstraints gbc_newSportLabel = new GridBagConstraints();
		gbc_newSportLabel.gridwidth = 3;
		gbc_newSportLabel.insets = new Insets(0, 0, 0, 5);
		gbc_newSportLabel.gridx = 1;
		gbc_newSportLabel.gridy = 11;
		panel.add(newSportLabel, gbc_newSportLabel);
		
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 11;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 11;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.gridwidth = 3;
		gbc_addButton.insets = new Insets(0, 0, 0, 5);
		gbc_addButton.gridx = 16;
		gbc_addButton.gridy = 11;
		panel.add(addButton, gbc_addButton);
	}
	
	public void setBusinessLogic(BLFacade business_logic) {
		businessLogic = business_logic;
	}
	
	public void setUser (User user) {
		this.user = user;
	}

}
