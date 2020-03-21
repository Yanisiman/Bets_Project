package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Queue;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JTextArea;

public class TutorialGUI extends JFrame {

	private JPanel contentPane;
	private JTextArea tutoField = new JTextArea("");
	private JButton skipBtn = new JButton("Skip");
	private JButton nextBtn = new JButton("Next");
	private Vector<String> steps = new Vector<String>();
	private int step = 0;
	
	private TutorialGUI self;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TutorialGUI frame = new TutorialGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TutorialGUI() {
		this.self = this;
		setSize(400, 200);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{182, 180, 0};
		gbl_contentPane.rowHeights = new int[]{126, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_tutoField = new GridBagConstraints();
		gbc_tutoField.gridwidth = 2;
		gbc_tutoField.fill = GridBagConstraints.BOTH;
		gbc_tutoField.insets = new Insets(0, 0, 5, 5);
		gbc_tutoField.gridx = 0;
		gbc_tutoField.gridy = 0;
		tutoField.setWrapStyleWord(true);
		tutoField.setLineWrap(true);
		tutoField.setEditable(false);
		contentPane.add(tutoField, gbc_tutoField);
		
		
		GridBagConstraints gbc_skipBtn = new GridBagConstraints();
		gbc_skipBtn.insets = new Insets(0, 0, 0, 5);
		gbc_skipBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_skipBtn.gridx = 0;
		gbc_skipBtn.gridy = 1;
		skipBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.dispose();
			}
		});
		contentPane.add(skipBtn, gbc_skipBtn);
		
		
		GridBagConstraints gbc_nextBtn = new GridBagConstraints();
		gbc_nextBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_nextBtn.gridx = 1;
		gbc_nextBtn.gridy = 1;
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step += 1;
				if (step >= steps.size()) {
					self.dispose();
					return;
				}					
				tutoField.setText(steps.get(step));
			}
		});
		contentPane.add(nextBtn, gbc_nextBtn);
		
		steps.add("Hello and welcome in the Bet&Ruin's tutorial.\nYou will learn how to use the application to be able to bet");
		steps.add("First you need an account. If it's not the case some of the actions described later will not be able to you.");
		steps.add("After selecting a sport you enter in the calendar of events : you can choose a date with events.\nYou can select an event the questions related to it and choose to bet on a answer.");
		steps.add("You can also go in your profile to see and edit your personal information");
		steps.add("There is a forum where you can discuss with other users");
		steps.add("Finally you can log out of the application");
		steps.add("We hope this tutorial helped you to better understand how all this application works");
		
		tutoField.setText(steps.get(step));
	}

}
