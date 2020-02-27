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
import java.util.Vector;

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
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.User;
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
	private JTextField newBetField =  new JTextField();
	private final JLabel oddLbl = new JLabel("Odd");
	private final JTextField oddField = new JTextField();
	
	private JButton newBetBtn = new JButton("New bet");
	private JComboBox<Bet> betsComboBox = new JComboBox<Bet>();
	private JLabel questionLbl = new JLabel("Questions");
	private JComboBox<Question> questionComboBox = new JComboBox<Question>();
	private JButton createEventBtn = new JButton("Create Event");
	
	private JLabel newEventLbl = new JLabel("New Event :");
	private JTextField newEventField = new JTextField();
	
	private Date eventDate = new Date(); 

	private BLFacade businessLogic;
	private User currentUser;	
	

	public CreateQuestionGUI (User currentUser, BLFacade businessLogic) {
		this.currentUser = currentUser;
		this.businessLogic = businessLogic;
	
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
		jLabelListOfEvents.setBounds(new Rectangle(290, 18, 277, 20));
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
		jButtonClose.setBounds(new Rectangle(185, 484, 130, 30));
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
		deleteUserBtn.setBounds(347, 486, 130, 28);
		getContentPane().add(deleteUserBtn);
		createEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String description = newEventField.getText();
				
				try {
					businessLogic.createEvent(description, eventDate);
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
					
					Bet bet = businessLogic.addBet(question, betString, odd);
					betsComboBox.addItem(bet);
					betsComboBox.repaint();
					
				} catch (Exception e2) {
					return;
				}	
			}
		});
		//newBetBtn.setEnabled(false);
		newBetBtn.setBounds(223, 396, 130, 30);
		getContentPane().add(newBetBtn);
		
		newEventLbl.setBounds(275, 100, 78, 20);
		getContentPane().add(newEventLbl);
		
		newEventField.setBounds(356, 100, 224, 20);
		getContentPane().add(newEventField);
		newEventField.setColumns(10);

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

					try {
						Vector<domain.Event> events = businessLogic.getEvents(firstDay);

						if (events.isEmpty())
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarMio.getTime()));
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
						//newBetBtn.setEnabled(false);
						return;
					}
					
					newBetBtn.setEnabled(true);
					
					Question questions[] = new Question[size];
					event.getQuestions().toArray(questions);
					
					questionComboBox.setModel(new DefaultComboBoxModel<Question>(questions));
					
					Question question = questions[0];
					
					System.out.println("Question --> " + question);				
					betsComboBox.removeAll();
					
					Bet bets[] = new Bet[question.getChoices().size()];
					question.getChoices().toArray(bets);
					
					System.out.println(question.getChoices());
					
					betsComboBox.setModel(new DefaultComboBoxModel<Bet>(bets));
				}
			}
		});;
		
		questionComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				Question question = (Question) questionComboBox.getSelectedItem();
				System.out.println("Question --> " + question);				
				betsComboBox.removeAll();
				
				Bet bets[] = new Bet[question.getChoices().size()];
				question.getChoices().toArray(bets);
				
				System.out.println(question.getChoices());
				
				betsComboBox.setModel(new DefaultComboBoxModel<Bet>(bets));
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

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		System.exit(0); //Close the process
	}

	public void setBusinessogic(BLFacade bl) {
		businessLogic = bl;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}