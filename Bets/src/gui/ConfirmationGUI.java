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

public class ConfirmationGUI extends JFrame {

	private JPanel contentPane;
	private ConfirmationGUI self;
	
	/**
	 * Create the frame.
	 */
	
	public ConfirmationGUI() {
		super();
		setResizable(false);
		this.setSize(300, 200);
		this.self = this;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton acceptBtn = new JButton("Accept");
		contentPane.add(acceptBtn, BorderLayout.SOUTH);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		textArea.setEnabled(false);
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
	}
	
	public ConfirmationGUI(RegisterGUI register, String message, BLFacade businessLogic, User newUser) {
		setResizable(false);
		this.setSize(300, 200);
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
		setResizable(false);
		this.setSize(300, 200);
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
		setResizable(false);
		this.setSize(300, 200);
		this.self = this;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton acceptBtn = new JButton("Accept");
		contentPane.add(acceptBtn, BorderLayout.SOUTH);
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
	}
	
	public ConfirmationGUI(UserEditInfoGUI userEditInfoGUI, String message) {
		setResizable(false);
		this.setSize(300, 200);
		this.self = this;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton acceptBtn = new JButton("Accept");
		contentPane.add(acceptBtn, BorderLayout.SOUTH);
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
	}
	
	

}
