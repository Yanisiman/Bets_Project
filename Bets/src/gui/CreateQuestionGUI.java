package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.jdo.listener.CreateLifecycleListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.BetChoice;
import domain.Event;
import domain.Question;
import domain.Sport;
import domain.User;
import domain.UserBet;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CreateQuestionGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelQuery = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Query"));
	private JLabel jLabelMinBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));

	private JTextField jTextFieldQuery = new JTextField();
	private JTextField jTextFieldPrice = new JTextField();
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarMio = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	private JButton deleteUserBtn = new JButton("Delete user");

	private final JLabel betsLbl = new JLabel("Bets");
	private final JLabel newBetLbl = new JLabel("New bet");
	private JTextField newBetField = new JTextField();
	private final JLabel oddLbl = new JLabel("Odds");
	private final JTextField oddField = new JTextField();

	private JButton newBetBtn = new JButton("New bet");
	private JComboBox<BetChoice> betsComboBox = new JComboBox<BetChoice>();
	private JLabel questionLbl = new JLabel("Questions");
	private JComboBox<Question> questionComboBox = new JComboBox<Question>();
	private JButton createEventBtn = new JButton("Create Event");

	private JLabel newEventLbl = new JLabel("New Event :");
	private JTextField newEventField = new JTextField();

	private final JButton accountBtn = new JButton("Account");
	
	private JButton removeQuestionBtn = new JButton("Remove");
	private JButton removeBetBtn = new JButton("Remove");
	private JButton removeEventBtn = new JButton("Remove");

	private Date eventDate = new Date();

	private Sport sport;
	private BLFacade businessLogic;
	private User currentUser;
	private CreateQuestionGUI self;

	public CreateQuestionGUI(User currentUser, BLFacade businessLogic, Sport sport) {
		this.currentUser = currentUser;
		this.businessLogic = businessLogic;
		this.sport = sport;
		this.self = this;

		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 600));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(275, 47, 250, 20));
		jLabelListOfEvents.setBounds(new Rectangle(275, 21, 277, 20));
		jLabelQuery.setBounds(new Rectangle(25, 254, 90, 20));
		jTextFieldQuery.setBounds(new Rectangle(115, 254, 350, 20));
		jLabelMinBet.setBounds(new Rectangle(480, 254, 75, 20));
		jTextFieldPrice.setBounds(new Rectangle(555, 254, 60, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setBounds(new Rectangle(223, 285, 130, 30));
		jButtonCreate.setEnabled(false);

		oddField.setBounds(555, 366, 60, 20);
		oddField.setColumns(10);

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(264, 501, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(172, 244, 305, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldQuery, null);
		this.getContentPane().add(jLabelQuery, null);
		this.getContentPane().add(jTextFieldPrice, null);

		this.getContentPane().add(jLabelMinBet, null);
		this.getContentPane().add(jLabelListOfEvents, null);
		this.getContentPane().add(jComboBoxEvents, null);

		this.getContentPane().add(jCalendar, null);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);

		deleteUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DeleteUser deleteUser = new DeleteUser(businessLogic);
				deleteUser.setVisible(true);

			}
		});
		deleteUserBtn.setBounds(375, -2, 111, 28);
		getContentPane().add(deleteUserBtn);

		JButton btnLogOut = new JButton("Log out"); //$NON-NLS-1$ //$NON-NLS-2$
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.setVisible(false);
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setBusinessLogic(businessLogic);
				loginGUI.setVisible(true);
			}
		});
		btnLogOut.setBounds(589, 0, 97, 25);
		getContentPane().add(btnLogOut);
		accountBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInformationGUI adminInformationGUI = new UserInformationGUI(currentUser, null);
				adminInformationGUI.setBusinessLogic(businessLogic);
				adminInformationGUI.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				adminInformationGUI.setUndecorated(true);
				adminInformationGUI.setVisible(true);

			}
		});
		accountBtn.setBounds(489, 0, 97, 25);

		getContentPane().add(accountBtn);

		createEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String description = newEventField.getText();

				try {

					Event event = businessLogic.createEvent(description, eventDate);
					
					Timer timer = new Timer();
					TimerTask timerTask = new TimerTask() {
						
						@Override
						public void run() {
							System.out.println("Event Closed");
							Event e = businessLogic.getEvent(event);
							for (Question q: e.getQuestions()) {
								for (BetChoice b: q.getChoices()) {
									for (UserBet userBet: b.getUserBets()) {
										if (q.getResult().equals(b)) {		
											int amount = userBet.getAmountBet();
											float odds = userBet.getOdds();
											System.out.println(userBet.getUser() + " Wins " + odds * amount + " " + userBet.getBet());
											businessLogic.addMoney(userBet.getUser(), amount * odds);
										}
									}
								}
							}
						}
					};		
					timer.schedule(timerTask, eventDate);
				
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					updateEvents(eventDate, dateformat1);

				} catch (EventFinished e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		createEventBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		createEventBtn.setBounds(397, 133, 128, 20);
		getContentPane().add(createEventBtn);

		questionComboBox.setBounds(115, 223, 350, 20);
		getContentPane().add(questionComboBox);

		questionLbl.setBounds(new Rectangle(40, 332, 75, 20));
		questionLbl.setBounds(40, 223, 75, 20);
		getContentPane().add(questionLbl);

		betsComboBox.setBounds(115, 332, 350, 20);
		getContentPane().add(betsComboBox);
		betsLbl.setBounds(55, 332, 60, 23);

		getContentPane().add(betsLbl);
		newBetLbl.setBounds(49, 366, 66, 20);

		getContentPane().add(newBetLbl);

		newBetField.setBounds(115, 366, 350, 19);
		getContentPane().add(newBetField);
		newBetField.setColumns(10);
		oddLbl.setBounds(480, 366, 75, 17);

		getContentPane().add(oddLbl);

		getContentPane().add(oddField);

		newBetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Question question = (Question) questionComboBox.getSelectedItem();

				if (question == null)
					return;

				String betString = newBetField.getText();

				try {
					float odd = Float.parseFloat(oddField.getText());

					BetChoice bet = businessLogic.addBetChoice(question, betString, odd);
					betsComboBox.addItem(bet);
					betsComboBox.repaint();

				} catch (Exception e2) {
					return;
				}
			}
		});
		// newBetBtn.setEnabled(false);
		newBetBtn.setBounds(223, 396, 130, 30);
		getContentPane().add(newBetBtn);

		newEventLbl.setBounds(275, 100, 78, 20);
		getContentPane().add(newEventLbl);

		newEventField.setBounds(356, 100, 224, 20);
		getContentPane().add(newEventField);
		newEventField.setColumns(10);
		removeQuestionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Question question = (Question) questionComboBox.getSelectedItem();
				if (question == null)
					return;
				businessLogic.removeQuestion(question);
			}
		});
		
		removeQuestionBtn.setBounds(491, 220, 89, 23);
		getContentPane().add(removeQuestionBtn);
		removeBetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BetChoice bet = (BetChoice) betsComboBox.getSelectedItem();
				if (bet == null)
					return;
				businessLogic.removeBetChoice(bet);
			}
		});
		
		removeBetBtn.setBounds(491, 331, 89, 23);
		getContentPane().add(removeBetBtn);
		removeEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Event event = (Event) jComboBoxEvents.getSelectedItem();
				if (event == null)
					return;
				businessLogic.removeEvent(event);
			}
		});
		
		removeEventBtn.setBounds(535, 46, 89, 23);
		getContentPane().add(removeEventBtn);
		
		JButton resultBetBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		resultBetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BetChoice bet = (BetChoice) betsComboBox.getSelectedItem();
				if (bet == null)
					return;
				Question question = (Question) questionComboBox.getSelectedItem();
				if (question == null)
					return;
				
				businessLogic.setResult(question, bet);
				User admin = businessLogic.checkLogin("admin", "");
				businessLogic.userBet(admin, 10, bet);
			}
		});
		resultBetBtn.setBounds(589, 331, 89, 23);
		getContentPane().add(resultBetBtn);

		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				questionComboBox.removeAll();
				betsComboBox.removeAll();
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					jCalendar.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));

					eventDate = firstDay;

					updateEvents(firstDay, dateformat1);

				}
				paintDaysWithEvents(jCalendar);
			}
		});

		jComboBoxEvents.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((e.getStateChange() == ItemEvent.SELECTED)) {
					Event event = (Event) jComboBoxEvents.getSelectedItem();
					System.out.println("Event ---> " + event);
					questionComboBox.removeAll();

					int size = event.getQuestions().size();
					if (size == 0) {
						// newBetBtn.setEnabled(false);
						return;
					}

					newBetBtn.setEnabled(true);

					Question questions[] = new Question[size];
					event.getQuestions().toArray(questions);

					questionComboBox.setModel(new DefaultComboBoxModel<Question>(questions));

					Question question = questions[0];

					System.out.println("Question --> " + question);
					betsComboBox.removeAll();

					BetChoice bets[] = new BetChoice[question.getChoices().size()];
					question.getChoices().toArray(bets);

					System.out.println(question.getChoices());

					betsComboBox.setModel(new DefaultComboBoxModel<BetChoice>(bets));
				}
			}
		});
		;

		questionComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				Question question = (Question) questionComboBox.getSelectedItem();
				System.out.println("Question --> " + question);
				betsComboBox.removeAll();

				BetChoice bets[] = new BetChoice[question.getChoices().size()];
				question.getChoices().toArray(bets);

				System.out.println(question.getChoices());

				betsComboBox.setModel(new DefaultComboBoxModel<BetChoice>(bets));
			}
		});

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

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		domain.Event event = ((domain.Event) jComboBoxEvents.getSelectedItem());

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty
			String inputQuery = jTextFieldQuery.getText();

			if (inputQuery.length() > 0) {

				// It could be to trigger an exception if the introduced string is not a number
				float inputPrice = Float.parseFloat(jTextFieldPrice.getText());

				if (inputPrice <= 0)
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				else {

					// Obtain the business logic from a StartWindow class (local or remote)

					Question question = businessLogic.createQuestion(event, inputQuery, inputPrice);

					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryCreated"));

					questionComboBox.addItem(question);
					questionComboBox.repaint();
					newBetBtn.setVisible(true);
				}
			} else
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuery"));
		} catch (EventFinished e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished") + ": "
					+ event.getDescription());
		} catch (QuestionAlreadyExist e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		} catch (java.lang.NumberFormatException e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void updateEvents(Date firstDay, DateFormat dateformat1) {
		try {
			//Vector<domain.Event> events = businessLogic.getEvents(firstDay);
			Vector<domain.Event> events = businessLogic.getSportEvents(firstDay, sport);

			if (events.isEmpty())
				jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
						+ dateformat1.format(calendarMio.getTime()));
			else
				jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
						+ dateformat1.format(calendarMio.getTime()));
			jComboBoxEvents.removeAllItems();
			System.out.println("Events " + events);

			for (domain.Event ev : events)
				modelEvents.addElement(ev);
			jComboBoxEvents.repaint();

			if (events.size() == 0)
				jButtonCreate.setEnabled(false);
			else
				jButtonCreate.setEnabled(true);

		} catch (Exception e1) {

			jLabelError.setText(e1.getMessage());
		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		System.exit(0); // Close the process
	}

	public void setBusinessogic(BLFacade bl) {
		businessLogic = bl;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public void setSport(Sport sport) {
		this.sport = sport;
	}
}