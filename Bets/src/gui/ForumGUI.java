package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Forum;
import domain.Message;
import domain.User;

public class ForumGUI extends JFrame {

	private JPanel contentPane;
	private JTextField sendMessageField;
	private JList<Message> messageList = new JList<Message>();
	private BLFacade businessLogic;
	private User currentUser;
	private ForumGUI self;
	
	//private Forum forum;

	/**
	 * Create the frame.
	 */
	public ForumGUI(BLFacade bl, User user) {
		this.businessLogic = bl;
		this.currentUser = user;
		this.self = this;
		
		setSize(700, 500);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{21, 366, 218, 124, 0};
		gbl_contentPane.rowHeights = new int[]{409, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_messageList = new GridBagConstraints();
		gbc_messageList.gridwidth = 2;
		gbc_messageList.insets = new Insets(0, 0, 5, 5);
		gbc_messageList.fill = GridBagConstraints.BOTH;
		gbc_messageList.gridx = 0;
		gbc_messageList.gridy = 0;
		
		sendMessageField = new JTextField();
		GridBagConstraints gbc_sendMessageField = new GridBagConstraints();
		gbc_sendMessageField.insets = new Insets(0, 0, 0, 5);
		gbc_sendMessageField.fill = GridBagConstraints.HORIZONTAL;
		gbc_sendMessageField.gridx = 1;
		gbc_sendMessageField.gridy = 1;
		contentPane.add(sendMessageField, gbc_sendMessageField);
		sendMessageField.setColumns(20);
		
		JButton sendMessageBtn = new JButton("Send message :");
		sendMessageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message = sendMessageField.getText();
				if (message.equals(""))
					return;
				businessLogic.createMessage(currentUser, message);
				messageList.setListData(businessLogic.getAllMessages());
				sendMessageField.setText("");
			}
		});
		GridBagConstraints gbc_sendMessageBtn = new GridBagConstraints();
		gbc_sendMessageBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_sendMessageBtn.insets = new Insets(0, 0, 0, 5);
		gbc_sendMessageBtn.gridx = 2;
		gbc_sendMessageBtn.gridy = 1;
		contentPane.add(sendMessageBtn, gbc_sendMessageBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(messageList);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		Vector<Message> messages = businessLogic.getAllMessages();
		messageList.setListData(messages);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
			}
		});
		GridBagConstraints gbc_closeBtn = new GridBagConstraints();
		gbc_closeBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeBtn.gridx = 3;
		gbc_closeBtn.gridy = 1;
		contentPane.add(closeBtn, gbc_closeBtn);
		
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				messageList.setListData(businessLogic.getAllMessages());				
			}
		};
		timer.scheduleAtFixedRate(timerTask, FRAMEBITS, 5000);
	}

	/**
	 * @param businessLogic the businessLogic to set
	 */
	public void setBusinessLogic(BLFacade businessLogic) {
		this.businessLogic = businessLogic;
	}

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
