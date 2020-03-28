package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Message;
import domain.Report;
import domain.ReportType;
import domain.User;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class ForumGUI extends JFrame {

	private JPanel contentPane;
	private JTextField sendMessageField = new JTextField();;
	private JList messageList = new JList();
	private Timer timer;
	private TimerTask timerTask;
	
	private JButton reportProblemBtn = new JButton("Report a problem");
	private JButton sendMessageBtn = new JButton("Send message :");
	private JButton closeBtn = new JButton("Close");
	
	private JScrollPane scrollPane = new JScrollPane();
	
	private JComboBox<ReportType> reportTypecomboBox = new JComboBox<ReportType>();
	private final JButton deleteMessageBtn = new JButton("Delete message");
	private final JCheckBox toggleBtn = new JCheckBox("Forum");
	
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
		gbl_contentPane.columnWidths = new int[]{18, 334, 175, 144, 0};
		gbl_contentPane.rowHeights = new int[]{0, 409, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		GridBagConstraints gbc_messageList = new GridBagConstraints();
		gbc_messageList.gridwidth = 2;
		gbc_messageList.insets = new Insets(0, 0, 5, 5);
		gbc_messageList.fill = GridBagConstraints.BOTH;
		gbc_messageList.gridx = 0;
		gbc_messageList.gridy = 0;
		
		
		GridBagConstraints gbc_reportTypecomboBox = new GridBagConstraints();
		gbc_reportTypecomboBox.insets = new Insets(0, 0, 5, 5);
		gbc_reportTypecomboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_reportTypecomboBox.gridx = 1;
		gbc_reportTypecomboBox.gridy = 0;
		reportTypecomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportType type = (ReportType) reportTypecomboBox.getSelectedItem();
				if (type == null)
					return;
				messageList.setListData(businessLogic.getReportByType(type));
			}
		});
		
		GridBagConstraints gbc_toggleBtn = new GridBagConstraints();
		gbc_toggleBtn.insets = new Insets(0, 0, 5, 5);
		gbc_toggleBtn.gridx = 0;
		gbc_toggleBtn.gridy = 0;
		toggleBtn.setSelected(true);
		toggleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayForumOrReports(toggleBtn.isSelected());
			}
		});
		contentPane.add(toggleBtn, gbc_toggleBtn);
		contentPane.add(reportTypecomboBox, gbc_reportTypecomboBox);
		
		
		GridBagConstraints gbc_reportProblemBtn = new GridBagConstraints();
		gbc_reportProblemBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_reportProblemBtn.insets = new Insets(0, 0, 5, 0);
		gbc_reportProblemBtn.gridx = 3;
		gbc_reportProblemBtn.gridy = 0;
		reportProblemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportProblemsGUI reportProblemsGUI = new ReportProblemsGUI(businessLogic, currentUser);
				reportProblemsGUI.setVisible(true);
			}
		});
		
		GridBagConstraints gbc_deleteMessageBtn = new GridBagConstraints();
		gbc_deleteMessageBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_deleteMessageBtn.insets = new Insets(0, 0, 5, 5);
		gbc_deleteMessageBtn.gridx = 2;
		gbc_deleteMessageBtn.gridy = 0;
		deleteMessageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(deleteMessageBtn, gbc_deleteMessageBtn);
		contentPane.add(reportProblemBtn, gbc_reportProblemBtn);
		
		
		GridBagConstraints gbc_sendMessageField = new GridBagConstraints();
		gbc_sendMessageField.insets = new Insets(0, 0, 0, 5);
		gbc_sendMessageField.fill = GridBagConstraints.HORIZONTAL;
		gbc_sendMessageField.gridx = 1;
		gbc_sendMessageField.gridy = 2;
		contentPane.add(sendMessageField, gbc_sendMessageField);
		sendMessageField.setColumns(20);
		
		
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
		gbc_sendMessageBtn.gridy = 2;
		contentPane.add(sendMessageBtn, gbc_sendMessageBtn);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(messageList);		
		
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
			}
		});
		GridBagConstraints gbc_closeBtn = new GridBagConstraints();
		gbc_closeBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeBtn.gridx = 3;
		gbc_closeBtn.gridy = 2;
		contentPane.add(closeBtn, gbc_closeBtn);
		
		if (!currentUser.isAdmin()) {
			reportTypecomboBox.setVisible(false);
			toggleBtn.setVisible(false);
			deleteMessageBtn.setVisible(false);
			reportProblemBtn.setVisible(false);
			
			displayMessages();
		}
		else {
			displayForumOrReports(true);
		}
		
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
	
	private void displayForumOrReports(boolean forum) {
		if (forum) {
			displayMessages();
		}
		else {
			timer.cancel();
			
			Vector<ReportType> tReportTypes = new Vector<ReportType>();
			tReportTypes.add(ReportType.USER_BEHAVIOR);
			tReportTypes.add(ReportType.MALFUNCTION);
			tReportTypes.add(ReportType.OTHER);
			reportTypecomboBox.setModel(new DefaultComboBoxModel<ReportType>(tReportTypes));
			
			messageList.setListData(businessLogic.getReportByType(ReportType.USER_BEHAVIOR));			
		}
	}
	
	private void displayMessages() {
		Vector<Message> messages = businessLogic.getAllMessages();
		messageList.setListData(messages);
		
		timer = new Timer();
		timerTask = new TimerTask() {
			
			@Override
			public void run() {
				messageList.setListData(businessLogic.getAllMessages());				
			}
		};
		timer.scheduleAtFixedRate(timerTask, FRAMEBITS, 5000);
	}

}
