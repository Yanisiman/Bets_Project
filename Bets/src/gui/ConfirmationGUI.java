package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.BetChoice;
import domain.User;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ConfirmationGUI extends JFrame {

	private JPanel contentPane;
	private ConfirmationGUI self;
	
	/**
	 * Create the frame.
	 */
	
	public ConfirmationGUI() {
		super();
		
		this.setSize(600, 200);
		this.self = this;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{276, 0};
		gbl_contentPane.rowHeights = new int[]{83, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		contentPane.add(textArea, gbc_textArea);
		
		JButton acceptBtn = new JButton("Accept");
		GridBagConstraints gbc_acceptBtn = new GridBagConstraints();
		gbc_acceptBtn.anchor = GridBagConstraints.NORTH;
		gbc_acceptBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_acceptBtn.gridx = 0;
		gbc_acceptBtn.gridy = 1;
		contentPane.add(acceptBtn, gbc_acceptBtn);
	}
	
	public ConfirmationGUI(RegisterGUI register, String message, BLFacade businessLogic, User newUser) {
		
		this.setSize(600, 200);
		this.self = this;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton acceptBtn = new JButton("Accept");
		contentPane.add(acceptBtn, BorderLayout.SOUTH);
		acceptBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
				register.setVisible(false);

				RulesGUI rulesGUI = new RulesGUI();
				rulesGUI.setUser(newUser);
				rulesGUI.setBusinessLogic(businessLogic);
				rulesGUI.setVisible(true);
			}
		});
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
		textArea.setText(message);
	}
	
	public ConfirmationGUI(FindQuestionsGUI findQuestionsGUI, String message, BLFacade businessLogic, User currentUser, BetChoice bet, int amount) {
		
		this.setSize(600, 200);
		this.self = this;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton acceptBtn = new JButton("Accept");
		contentPane.add(acceptBtn, BorderLayout.SOUTH);
		acceptBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
				User newCurrentUser = businessLogic.userBet(currentUser, amount, bet);
				findQuestionsGUI.setCurrentUser(newCurrentUser);
				findQuestionsGUI.displayBetChoices();
				
			}
		});
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
		textArea.setText(message);
	}
	
	public ConfirmationGUI(CreateQuestionGUI createQuestionGUI, String message) {
		
		this.setSize(600, 200);
		this.self = this;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton acceptBtn = new JButton("Accept");
		contentPane.add(acceptBtn, BorderLayout.SOUTH);
		acceptBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
	}
	
	public ConfirmationGUI(UserEditInfoGUI userEditInfoGUI, String message) {
		this.setSize(600, 200);
		this.self = this;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton acceptBtn = new JButton("Accept");
		contentPane.add(acceptBtn, BorderLayout.SOUTH);
		acceptBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
	}
	
	

}
