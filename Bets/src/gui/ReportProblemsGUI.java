package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Report;
import domain.ReportType;
import domain.User;

public class ReportProblemsGUI extends JFrame {

	private JPanel contentPane;
	private JLabel title = new JLabel("Report problems");
	private JComboBox<ReportType> problemTypecomboBox = new JComboBox<ReportType>();
	private JScrollPane scrollPane = new JScrollPane();
	private JTextArea textArea = new JTextArea();
	private JButton sendBtn = new JButton("Report");
	private final JButton closeBtn = new JButton("Close");
	
	private ReportProblemsGUI self;
	private ForumGUI forumGUI;
	private BLFacade bl;
	private User currentUser;

	/**
	 * Create the frame.
	 */
	public ReportProblemsGUI(BLFacade bl, User user) {
		this.self = this;
		this.bl = bl;
		this.currentUser = user;
		
		setSize(700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{33, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		title.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 2;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridx = 1;
		gbc_title.gridy = 0;
		contentPane.add(title, gbc_title);
		
		
		GridBagConstraints gbc_problemTypecomboBox = new GridBagConstraints();
		gbc_problemTypecomboBox.gridwidth = 2;
		gbc_problemTypecomboBox.insets = new Insets(0, 0, 5, 5);
		gbc_problemTypecomboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_problemTypecomboBox.gridx = 1;
		gbc_problemTypecomboBox.gridy = 2;
		contentPane.add(problemTypecomboBox, gbc_problemTypecomboBox);
		Vector<ReportType> tReportTypes = new Vector<ReportType>();
		tReportTypes.add(ReportType.USER_BEHAVIOR);
		tReportTypes.add(ReportType.MALFUNCTION);
		tReportTypes.add(ReportType.OTHER);
		problemTypecomboBox.setModel(new DefaultComboBoxModel<ReportType>(tReportTypes));
		
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(textArea);
		
		
		GridBagConstraints gbc_sendBtn = new GridBagConstraints();
		gbc_sendBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_sendBtn.insets = new Insets(0, 0, 5, 5);
		gbc_sendBtn.gridx = 1;
		gbc_sendBtn.gridy = 6;
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message = textArea.getText();
				if (message.equals(""))
					return;
				ReportType type = (ReportType) problemTypecomboBox.getSelectedItem();
				Report report = bl.sendReport(currentUser, message, type);
			}
		});
		contentPane.add(sendBtn, gbc_sendBtn);
		
		GridBagConstraints gbc_closeBtn = new GridBagConstraints();
		gbc_closeBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_closeBtn.insets = new Insets(0, 0, 5, 5);
		gbc_closeBtn.gridx = 2;
		gbc_closeBtn.gridy = 6;
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
			}
		});
		contentPane.add(closeBtn, gbc_closeBtn);
	}

}
