package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.DomainCombiner;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.BetChoice;
import domain.Event;
import domain.Question;
import domain.Sport;
import domain.User;
import domain.UserBet;

public class FindQuestionsGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private JTable tableEvents = new JTable();
	private JTable tableQueries = new JTable();
	
	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),};
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query")};

	private DefaultTableModel tableModelEvents = new DefaultTableModel(null, columnNamesEvents);
	private DefaultTableModel tableModelQueries = new DefaultTableModel(null, columnNamesQueries);


	private JTextField amountBetField = new JTextField();
	private JComboBox<BetChoice> choiceBetComboBox = new JComboBox<BetChoice>();
	private JLabel amountBetLbl = new JLabel();
	private JButton betBtn = new JButton();
	private JButton editAccountBtn = new JButton("Account");
	private final JButton logoutBtn = new JButton("Log out");
	private JTextArea textArea = new JTextArea();
	private JLabel moneyField = new JLabel("Money :");
	private JButton forumBtn = new JButton("Forum");

	private Sport sport;
	private BLFacade businessLogic;
	private User currentUser;
	private FindQuestionsGUI self;
	private final JButton registerBtn = new JButton("Register");
	private final JButton closeBttn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$

	public FindQuestionsGUI(User currentUser, BLFacade bl, Sport sport) {
		this.currentUser = currentUser;
		this.businessLogic = bl;
		this.sport = sport;
		this.self = this;
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jbInit();
			if (currentUser == null) {
				registerBtn.setVisible(true);
			} else {
				registerBtn.setVisible(false);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(138, 221, 406, 14);
		jLabelEvents.setBounds(297, 44, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				tableModelQueries.setDataVector(null, columnNamesQueries);
				jLabelQueries.setText("");
				
				choiceBetComboBox.setVisible(false);
				amountBetField.setVisible(false);
				betBtn.setVisible(false);
				amountBetLbl.setVisible(false);
				
				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableQueries.removeAll();
				tableQueries.repaint();
				
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						//Vector<domain.Event> events = businessLogic.getEvents(firstDay);
						Vector<domain.Event> events = businessLogic.getSportEvents(firstDay, sport);


						if (events.isEmpty())
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						else
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarMio.getTime()));

						for (domain.Event ev : events) {
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events " + ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not
																												// shown
																												// in
																												// JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				paintDaysWithEvents(jCalendar1);
			}
		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(297, 60, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(138, 236, 406, 116));
		
		tableEvents.setDefaultEditor(Object.class, null);
		tableQueries.setDefaultEditor(Object.class, null);

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				choiceBetComboBox.setVisible(false);
				amountBetField.setVisible(false);
				betBtn.setVisible(false);
				amountBetLbl.setVisible(false);
				
				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableQueries.removeAll();
				tableQueries.repaint();
				
				int i = tableEvents.getSelectedRow();
				Event ev = (Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
				Vector<Question> queries = ev.getQuestions();

				tableModelQueries.setColumnCount(3); // another column added to allocate ev objects

				if (queries.isEmpty())
					jLabelQueries.setText(
							ResourceBundle.getBundle("Etiquetas").getString("NoQueries") + ": " + ev.getDescription());
				else
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent") + " "
							+ ev.getDescription());

				for (Question q : queries) {
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2));
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);

		scrollPaneQueries.setViewportView(tableQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);

		choiceBetComboBox.setBounds(52, 377, 316, 16);
		getContentPane().add(choiceBetComboBox);
		choiceBetComboBox.setVisible(false);

		amountBetField.setBounds(448, 375, 96, 20);
		getContentPane().add(amountBetField);
		amountBetField.setColumns(10);
		amountBetField.setVisible(false);

		amountBetLbl.setText("Amount :");
		amountBetLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		amountBetLbl.setBounds(378, 378, 63, 14);
		getContentPane().add(amountBetLbl);
		amountBetLbl.setVisible(false);
		betBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setVisible(false);
				try {
					currentUser = businessLogic.checkLogin(currentUser.getUsername(), "");
					int amount = Integer.parseInt(amountBetField.getText());
					BetChoice bet = (BetChoice) choiceBetComboBox.getSelectedItem();
					
					String message = "Are you sure you want to bet " + amount + "€ ?";
					
					if (currentUser.getMoney() < amount)
					{
						textArea.setVisible(true);
						textArea.setText("You don't have enough money to bet. You can money to your account in your edit profile tab");
						return;
					}
					if (currentUser.getMoneySpentPerMonth() + amount > currentUser.getBudget())
					{
						message = "Are you sure you want to bet " + amount + "€ ? You already bet more than your budget allows you. "
								+ "To remember you have a budget of " + currentUser.getBudget() + "€ per month!";
					}					
					
					ConfirmationGUI confirmationGUI = new ConfirmationGUI(self, message, businessLogic, currentUser, bet, amount);
					confirmationGUI.setVisible(true);
					
				} catch (Exception e) {
					return;
				}
			}
		});

		betBtn.setText("Bet");
		betBtn.setBounds(554, 374, 89, 23);
		getContentPane().add(betBtn);
		editAccountBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		editAccountBtn.setBounds(464, 0, 108, 25);

		getContentPane().add(editAccountBtn);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				self.setVisible(false);
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setBusinessLogic(businessLogic);
				loginGUI.setVisible(true);
			}
		});
		logoutBtn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		logoutBtn.setBounds(578, 0, 108, 26);

		getContentPane().add(logoutBtn);
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				self.setVisible(false);
				RegisterGUI registerGui = new RegisterGUI();
				registerGui.setBusinessLogic(businessLogic);
				registerGui.setVisible(true);
			}
		});
		registerBtn.setBounds(40, 415, 97, 25);

		getContentPane().add(registerBtn);
		betBtn.setVisible(false);

		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayBetChoices();
			}
		});

		if (currentUser == null) {
			editAccountBtn.setVisible(false);
			logoutBtn.setVisible(false);
			moneyField.setVisible(false);
		}
		else 
			moneyField.setText("Money : " + currentUser.getMoney() + " €");
		
		editAccountBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentUser = businessLogic.checkLogin(currentUser.getUsername(), "");
				UserInformationGUI userInformationGUI = new UserInformationGUI(currentUser, self);
				userInformationGUI.setBusinessLogic(businessLogic);
				userInformationGUI.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				userInformationGUI.setUndecorated(true);
				userInformationGUI.setVisible(true);
			}
		});
		
		textArea.setBounds(147, 415, 406, 25);
		textArea.setText("");
		textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setVisible(false);
		getContentPane().add(textArea);

		moneyField.setBounds(263, 2, 103, 23);
		getContentPane().add(moneyField);	
		
		forumBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentUser = businessLogic.checkLogin(currentUser.getUsername(), "");
				ForumGUI forum = new ForumGUI(businessLogic, currentUser);
				forum.setVisible(true);
			}
		});
		
		forumBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		forumBtn.setBounds(367, 1, 87, 25);
		getContentPane().add(forumBtn);

		closeBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
				SportGUI sportGUI = new SportGUI(businessLogic, currentUser);
				sportGUI.setBusinessLogic(businessLogic);
				sportGUI.setUser(currentUser);
				sportGUI.setVisible(true);
				sportGUI.setB(false);
			}
		});
		closeBttn.setBounds(291, 443, 117, 29);
		
		getContentPane().add(closeBttn);

	}

	public void setBusinessLogic(BLFacade bl) {
		businessLogic = bl;
	}

	public void paintDaysWithEvents(JCalendar jCalendar) {
		// For each day in current month, it is checked if there are events, and in that
		// case, the background color for that day is changed.

		Vector<Date> dates = businessLogic.getEventsMonth(jCalendar.getDate());

		Calendar calendar = jCalendar.getCalendar();

		int month = calendar.get(Calendar.MONTH);
		int today = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;

		for (Date d : dates) {

			calendar.setTime(d);
			System.out.println(d);

			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, month);

	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
		moneyField.setText("Money : " + currentUser.getMoney() + " €");
	}
	

	public void displayBetChoices() {
		if (currentUser == null)
			return;

		int i = tableQueries.getSelectedRow();
		Question q = businessLogic.getQuestion((Question) tableModelQueries.getValueAt(i, 2)); // obtain ev object

		BetChoice[] bets = new BetChoice[q.getChoices().size()];
		q.getChoices().toArray(bets);

		choiceBetComboBox.setModel(new DefaultComboBoxModel<BetChoice>(bets));

		choiceBetComboBox.setVisible(true);
		amountBetField.setVisible(true);
		betBtn.setVisible(true);
		amountBetLbl.setVisible(true);
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}
}
