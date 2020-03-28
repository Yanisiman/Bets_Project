package dataAccess;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.BetChoice;
import domain.Event;
import domain.Message;
import domain.Question;
import domain.Report;
import domain.ReportType;
import domain.Sport;
import domain.User;
import domain.UserBet;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c;

	public DataAccess(boolean initializeMode) {

		c = ConfigXML.getInstance();

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode)
			fileName = fileName + ";drop";

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}
	}

	public DataAccess() {
		new DataAccess(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));
			Event ev21 = new Event(21, "Digital Step Stone", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 2));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 2));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 2));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 2));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 2));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 2));
			Event ev22 = new Event(22, "Digital Step Stone", UtilDate.newDate(year, month, 2));

			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month, 28));
			Event ev23 = new Event(23, "Digital Step Stone", UtilDate.newDate(year, month, 28));

			Sport sport = new Sport("Football");
			Sport sport2 = new Sport("Dance");
			
			String sports[] = new String[] {"Basketball", "Handball", "Gymnastics", "Badminton", "Table tennis", 
					"Volleyball", "Tennis", "Baseball", "Cricket", "Dodgeball", "Frisbee", "Quidditch", "BMX", "Cycling", 
					"Judo", "Wrestling", "Boxing", "Taekwondo", "Equitation", "Golf", "Curling", "Sprint", "Biathlon"};
			
			for (String s: sports)
			{
				Sport nS = new Sport(s);
				db.persist(nS);
			}			

			User user = new User("Marion", "Bernard", 19, "111", "French", "marion.bernard@epita.fr", "MarionBer",
					"123456789", "04-09-2000");
			User user2 = new User("Mariusz", "Januszek", 24, "polpol", "Polish", "mariusz.januszek95@gmail.com",
					"MariuszJan", "987654321", "29-07-1995");
			User yanis = new User("Yanis", "Chaabane", 19, "yayko", "French", "yanis.chaabane@epita.fr", "Yayko",
					"1232131", "25/04/2000");
			Admin admin = new Admin("Mariusz", "Januszek", 24, "admin", "Polish", "mariusz.januszek955@gmail.com",
					"admin", "987654321", "29-07-1995");

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
			Question q7;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
				q7 = ev21.addQuestion("Qué equipo ganará la competencia?", 4);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
				q7 = ev21.addQuestion("Which team will win the competition", 4);

			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);
				q7 = ev21.addQuestion("Zein taldek irabaziko du lehiaketa?", 4);

			}

			BetChoice b1 = new BetChoice(q1, "Home", 1.2f);
			BetChoice b2 = new BetChoice(q1, "Draw", 2);
			BetChoice b3 = new BetChoice(q1, "Away", 1.8f);

			q1.setResult(b1);

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
			db.persist(q7);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);
			db.persist(ev21);
			db.persist(ev22);
			db.persist(ev23);

			db.persist(user);
			db.persist(user2);
			db.persist(yanis);
			db.persist(admin);

			db.persist(b1);
			db.persist(b2);
			db.persist(b3);

			db.persist(sport);
			db.persist(sport2);

			yanis.addFriend(user2);
			yanis.addPreference(sport);
			yanis.setMoney(500);

			sport.addEvent(ev1);
			sport.addEvent(ev2);
			sport.addEvent(ev3);
			sport.addEvent(ev4);
			sport.addEvent(ev5);
			sport.addEvent(ev6);
			sport.addEvent(ev7);
			sport.addEvent(ev8);
			sport.addEvent(ev10);
			sport.addEvent(ev11);
			sport.addEvent(ev12);
			sport.addEvent(ev13);
			sport.addEvent(ev14);
			sport.addEvent(ev15);
			sport.addEvent(ev16);
			sport.addEvent(ev17);
			sport.addEvent(ev18);
			sport.addEvent(ev19);
			sport.addEvent(ev20);
			sport2.addEvent(ev21);
			sport2.addEvent(ev22);
			sport2.addEvent(ev23);
			
			ev1.setSport(sport);
			ev2.setSport(sport);
			ev3.setSport(sport);
			ev4.setSport(sport);
			ev5.setSport(sport);
			ev6.setSport(sport);
			ev7.setSport(sport);
			ev8.setSport(sport);
			ev9.setSport(sport);
			ev10.setSport(sport);
			ev11.setSport(sport);
			ev12.setSport(sport);
			ev13.setSport(sport);
			ev14.setSport(sport);
			ev15.setSport(sport);
			ev16.setSport(sport);
			ev17.setSport(sport);
			ev18.setSport(sport);
			ev19.setSport(sport);
			ev20.setSport(sport);
			ev21.setSport(sport2);
			ev22.setSport(sport2);
			ev23.setSport(sport2);
			
			Date date = new Date();
			date.setDate(date.getDate() - 2);

			Event e = new Event(35, "THIS IS A TEST", date);
			Question q = e.addQuestion("Who will win the match?", 1);
			Question q11 = e.addQuestion("Who will score first?", 2);

			BetChoice b4 = new BetChoice(q, "Home", 1.7f);
			BetChoice b5 = new BetChoice(q, "Draw", 1);
			BetChoice b6 = new BetChoice(q11, "Away", 2f);

			UserBet userBet = new UserBet(yanis, 10, b4);
			q.setResult(b4);

			db.persist(e);
			db.persist(q);
			db.persist(q11);
			db.persist(b4);
			db.persist(b5);
			db.persist(b6);
			db.persist(userBet);
			
			sport.addEvent(e);
			e.setSport(sport);

			db.getTransaction().commit();

			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	
	
	
	/** *** ** Events ** *** **/
	
	public Event createEvent(String description, Date eventDate, Sport sport) {
		try {
			Sport s = db.find(Sport.class, sport);
			db.getTransaction().begin();
			Event event = new Event(description, eventDate);
			event.setSport(s);
			s.addEvent(event);
			db.persist(event);
			db.getTransaction().commit();
			
			return event;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate.getDay()=?1", Event.class);
		int day = date.getDay();
		query.setParameter(1, day);
		List<Event> events = query.getResultList();

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);

		for (Event ev : events) {
			System.out.println(ev.toString());

			Calendar cal2 = Calendar.getInstance();

			cal2.setTime(ev.getEventDate());
			boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
					&& cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

			if (sameDay)
				res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}
	
	public Vector<Date> getEventsMonth(Date date, Sport sport) {
		try {
			System.out.println(">> DataAccess: getEventsMonth");
			Vector<Date> res = new Vector<Date>();

			Date firstDayMonthDate = UtilDate.firstDayMonth(date);
			Date lastDayMonthDate = UtilDate.lastDayMonth(date);
			
			Sport s = db.find(Sport.class, sport);

			TypedQuery<Date> query = db.createQuery(
					"SELECT DISTINCT ev.eventDate FROM Event ev WHERE (ev.eventDate BETWEEN ?1 and ?2) AND ev.sport.sportNumber =" + s.getSportNumber(), Date.class);
			query.setParameter(1, firstDayMonthDate);
			query.setParameter(2, lastDayMonthDate);
			List<Date> dates = query.getResultList();
			for (Date d : dates) {
				System.out.println(d.toString());
				res.add(d);
			}
			return res;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public Event getEvent(Event event) {
		try {
			TypedQuery<Event> q = db
					.createQuery("SELECT e from Event e WHERE e.eventNumber = " + event.getEventNumber(), Event.class);
			return q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public void removeEvent(Event e, Sport sport) {
		db.getTransaction().begin();

		Query q = db.createQuery("DELETE FROM Event e WHERE e.eventNumber = " + e.getEventNumber());
		q.executeUpdate();
		Sport sport2 = db.find(Sport.class,sport);
		sport2.removeEvent(e);
		db.getTransaction().commit();
		for (Question question : e.getQuestions()) {
			removeQuestion(question);

			for (BetChoice b : question.getChoices()) {
				removeBet(b);
			}
		}
	}
	
	
	
	
	
	
	/** *** ** Questions ** *** **/
	
	
	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		db.persist(ev);
		db.persist(q); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}
	
	public Question getQuestion(Question question) {
		try {
			TypedQuery<Question> q = db.createQuery(
					"SELECT q from Question q WHERE q.questionNumber = " + question.getQuestionNumber(),
					Question.class);
			return q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public void removeQuestion(Question question) {
		db.getTransaction().begin();
		Query q = db.createQuery("DELETE FROM Question e WHERE e.questionNumber = " + question.getQuestionNumber());
		q.executeUpdate();
		db.getTransaction().commit();

		for (BetChoice b : question.getChoices()) {
			removeBet(b);
		}
	}
	
	public Question setResult(Question question, BetChoice choice) {
		try {
			db.getTransaction().begin();

			TypedQuery<Question> q = db.createQuery(
					"SELECT q from Question q WHERE q.questionNumber = " + question.getQuestionNumber(),
					Question.class);
			Question quest = q.getSingleResult();

			TypedQuery<BetChoice> q2 = db.createQuery(
					"SELECT b from BetChoice b WHERE b.choiceNumber = " + choice.getChoiceNumber(), BetChoice.class);
			BetChoice b = q2.getSingleResult();

			quest.setResult(b);

			db.getTransaction().commit();
			
			return quest;
		} catch (Exception e) {
			return null;
		}
	}
	

	
	
	
	/** *** ** BetChoices ** *** **/
	
	public BetChoice addBet(Question question, String response, float odds) {
		TypedQuery<Question> q = db.createQuery(
				"SELECT q from Question q WHERE q.questionNumber = " + question.getQuestionNumber(), Question.class);
		Question quest = q.getSingleResult();

		if (quest == null) {
			System.out.println("DIDNT FIND " + question);
			return null;
		}

		db.getTransaction().begin();
		BetChoice bet = new BetChoice(quest, response, odds);
		db.persist(bet);
		db.getTransaction().commit();
		return bet;
	}
	
	public void removeBet(BetChoice b) {
		db.getTransaction().begin();
		Query q = db.createQuery("DELETE FROM BetChoice e WHERE e.choiceNumber = " + b.getChoiceNumber());
		q.executeUpdate();
		db.getTransaction().commit();
	}
	
	private void updateOdds(BetChoice bet) {
		try {
			TypedQuery<BetChoice> q = db.createQuery(
					"SELECT b FROM BetChoice b WHERE b.choiceNumber = " + bet.getChoiceNumber(), BetChoice.class);
			BetChoice b = q.getSingleResult();

			db.getTransaction().begin();
			float users = b.getUserBets().size();
			bet.setOdds(1 + 1 / users);
			db.getTransaction().commit();

		} catch (Exception e) {
			return;
		}

	}
	
	
	
	
	
	/** *** ** UserBet ** *** **/
	
	public User userBet(User u, int amount, BetChoice bet) {
		try {

			TypedQuery<BetChoice> q = db.createQuery(
					"SELECT b from BetChoice b WHERE b.choiceNumber = " + bet.getChoiceNumber(), BetChoice.class);
			BetChoice b = q.getSingleResult();

			TypedQuery<User> q2 = db.createQuery("SELECT u from User u WHERE u.email = \"" + u.getEmail() + "\"",
					User.class);
			User user = q2.getSingleResult();

			db.getTransaction().begin();
			UserBet userBet = new UserBet(user, amount, b);
			db.persist(userBet);

			user.updateMoney(-amount);
			user.addMoneySpentPerMont(amount);

			db.getTransaction().commit();

			updateOdds(b);

			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Vector<UserBet> getUserBet(User user) {
		try {

			TypedQuery<User> q2 = db.createQuery(
					"SELECT u from User u " + "WHERE u.username = \"" + user.getUsername() + "\"", User.class);
			User u = q2.getSingleResult();
			System.out.println(u.getBets());
			return u.getBets();

		} catch (Exception e) {
			System.out.println("Error with userBets");
			return null;
		}
	}
	
	public void removeUserBet(UserBet bet) {
		try {
			db.getTransaction().begin();

			TypedQuery<User> q = db.createQuery(
					"SELECT u from User u " + "WHERE u.username = \"" + bet.getUser().getUsername() + "\"", User.class);
			User u = q.getSingleResult();

			TypedQuery<BetChoice> q2 = db.createQuery(
					"SELECT b from BetChoice b WHERE b.choiceNumber = " + bet.getBet().getChoiceNumber(),
					BetChoice.class);
			BetChoice b = q2.getSingleResult();

			TypedQuery<UserBet> q4 = db.createQuery(
					"SELECT b from UserBet b WHERE b.userBetNumber = " + bet.getUserBetNumber(), UserBet.class);
			UserBet c = q4.getSingleResult();

			Query q3 = db.createQuery("DELETE FROM UserBet e WHERE e.userBetNumber = " + bet.getUserBetNumber());
			q3.executeUpdate();

			u.removeUserBet(c);
			b.removeUserBet(c);

			db.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	
	
	
	/** *** ** Users ** *** **/
	
	public User getUser(String username, String password) {
		try {
			TypedQuery<User> q2 = db.createQuery("SELECT u from User u " + "WHERE (u.username = \"" + username
					+ "\" OR u.email = \"" + username + "\") AND u.password = \"" + password + "\"", User.class);
			return q2.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	public User getUser(String username) {
		try {
			TypedQuery<User> q2 = db.createQuery("SELECT u from User u " + "WHERE u.username = \"" + username + "\"",
					User.class);
			return q2.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}
	
	public void storeUser(User user) {
		db.getTransaction().begin();
		db.persist(user);
		db.getTransaction().commit();
	}

	public void deleteUser(User user) {
		db.getTransaction().begin();
		Query q = db.createQuery("DELETE FROM User u WHERE u.email = \"" + user.getEmail() + "\"");
		q.executeUpdate();
		db.getTransaction().commit();
	}

	public User updateUser(String email, String username, String password, String name, String familyName,
			String creditCard, float money, float budget) {
		TypedQuery<User> q = db.createQuery("SELECT u from User u WHERE u.email = \"" + email + "\"", User.class);
		User user = q.getSingleResult();
		db.getTransaction().begin();

		if (!username.equals(""))
			user.setUsername(username);
		if (!password.equals(""))
			user.setPassword(password);
		if (!name.equals(""))
			user.setName(name);
		if (!familyName.equals(""))
			user.setFamilyName(familyName);
		if (!creditCard.equals(""))
			user.setCreditCard(creditCard);
		
		user.setMoney(money);
		
		if (budget == 0)
			user.setBudgetBool(false);
		else
			user.setBudgetBool(true);
		user.setBudget(budget);

		db.getTransaction().commit();
		return user;
	}
	
	public void addMoneyUser(User u, float money) {
		try {
			TypedQuery<User> q = db.createQuery("SELECT u from User u WHERE u.username = \"" + u.getUsername() + "\"",
					User.class);
			User user = q.getSingleResult();

			db.getTransaction().begin();
			user.updateMoney(money);
			db.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error to get the user");
		}
	}
	
	public List<User> getUsers() {
		TypedQuery<User> a = db.createQuery("SELECT u from User u", User.class);
		return a.getResultList();
	}
	
	public Vector<User> getFriends(User user) {
		try {

			TypedQuery<User> q2 = db.createQuery(
					"SELECT u from User u " + "WHERE u.username = \"" + user.getUsername() + "\"", User.class);
			User u = q2.getSingleResult();
			System.out.println(u.getFriends());
			return u.getFriends();

		} catch (Exception e) {
			System.out.println("Error with friends");
			return null;
		}
	}
	
	public boolean addFriend(User user, String friend) {
		try {
			db.getTransaction().begin();

			TypedQuery<User> q = db.createQuery(
					"SELECT u from User u " + "WHERE u.username = \"" + user.getUsername() + "\"", User.class);
			User u = q.getSingleResult();

			TypedQuery<User> q2 = db.createQuery("SELECT u from User u " + "WHERE u.username = \"" + friend + "\"",
					User.class);
			User f = q2.getSingleResult();

			if (f.isAdmin() || u.getFriends().contains(f))				
				return false;
			
			u.addFriend(f);
			db.getTransaction().commit();
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public void removeFriend(User user, User friend) {
		try {
			db.getTransaction().begin();

			TypedQuery<User> q = db.createQuery(
					"SELECT u from User u " + "WHERE u.username = \"" + user.getUsername() + "\"", User.class);
			User u = q.getSingleResult();

			TypedQuery<User> q2 = db.createQuery(
					"SELECT u from User u " + "WHERE u.username = \"" + friend.getUsername() + "\"", User.class);
			User f = q2.getSingleResult();

			u.removeFriend(f);

			db.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	
	
	/** *** ** Sports ** *** **/
	
	public List<Sport> getSport() {
		TypedQuery<Sport> query = db.createQuery("SELECT s FROM Sport s ", Sport.class);
		List<Sport> sports = query.getResultList();
		for (Sport c : sports) {
			System.out.println(c + " is a sport.");
		}
		return sports;
	}

	public void addSport(Sport sport) {
		db.getTransaction().begin();
		db.persist(sport);
		db.getTransaction().commit();
	}

	public void removeSport(String sport) {
		db.getTransaction().begin();
		TypedQuery<Sport> q1 = db.createQuery("SELECT s FROM Sport s " + "WHERE s.sportName =  \"" + sport + "\"",
				Sport.class);
		Sport sport2 = q1.getSingleResult();
		db.remove(sport2);
		db.getTransaction().commit();
		for(Event e: sport2.getSportEvent())
			removeEvent(e, sport2);
	}

	public User addSportUser(Sport sport, User user) {
		try {
			TypedQuery<Sport> q = db
					.createQuery("SELECT s FROM Sport s WHERE s.sportNumber = " + sport.getSportNumber(), Sport.class);
			Sport s = q.getSingleResult();

			TypedQuery<User> q2 = db
					.createQuery("SELECT u FROM User u WHERE u.username = \"" + user.getUsername() + "\"", User.class);
			User u = q2.getSingleResult();

			db.getTransaction().begin();
			u.addPreference(s);
			db.getTransaction().commit();

			return u;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	
	public Vector<Event> getSportEvents(Sport sport) {
		try {
			Sport s = db.find(Sport.class, sport);
			return s.getSportEvent();
		} catch (Exception e) {
			return null;
		}
		
	}

	public Vector<Sport> getUserPreferences(User user) {
		try {
			TypedQuery<User> q2 = db.createQuery(
					"SELECT u from User u " + "WHERE u.username = \"" + user.getUsername() + "\"", User.class);
			User u = q2.getSingleResult();
			System.out.println(u.getPreferences());
			return u.getPreferences();
		} catch (Exception e) {
			System.out.println("Error with userBets");
			return null;
		}
	}

	public Sport getUniqueSport(String sportName) {
		try {
			TypedQuery<Sport> query = db.createQuery("SELECT s FROM Sport s " + "WHERE s.sportName = \"" + sportName + "\"",
					Sport.class);
			Sport sports = query.getSingleResult();
			return sports;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	/** *** ** Messages ** *** **/
	
	public Message createMessage(User user, String message) {
		user = getUser(user.getUsername());
		
		db.getTransaction().begin();
		Message m = new Message(user, message);
		db.persist(m);
		db.getTransaction().commit();
		return m;
	}
	
	public boolean deleteMessage(Message message) {
		try {
			db.getTransaction().begin();
			Query q = db.createQuery("DELETE FROM Message m WHERE m.messageNumber = " + message.getMessageNumber());
			q.executeUpdate();
			db.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<Message> getAllMessages(){
		try {
			TypedQuery<Message> q = db.createQuery("SELECT m FROM Message m" , Message.class);
			List<Message> messages = q.getResultList();
			return messages;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Message> getMessagesOfUser(User user){
		try {
			TypedQuery<Message> q = db.createQuery("SELECT m FROM Message m WHERE m.user.uid = " + user.getUid(), Message.class);
			List<Message> messages = q.getResultList();
			return messages;
		} catch (Exception e) {
			return null;
		}
	}
	
	public User getUserOfMessage(Message message) {
		try {
			TypedQuery<Message> q = db.createQuery("SELECT m FROM Message m" , Message.class);
			Message m = q.getSingleResult();
			return m.getUser();
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	
	/** *** ** Reports ** *** **/
	
	public Report sendReport(User user, String message, ReportType type) {
		user = db.find(User.class, user);
		db.getTransaction().begin();
		Report report = new Report(user, message, type);
		db.persist(report);
		db.getTransaction().commit();
		return report;
	}
	
	public List<Report> getReportByType(ReportType type) {
		try {
			TypedQuery<Report> q = db.createQuery("SELECT r FROM Report r", Report.class);
			List<Report> reports = q.getResultList().stream().filter(t -> t.getType() == type).collect(Collectors.toList());			
			return (List<Report>) reports;
		} catch (Exception e) {
			return null;
		}
	}

}
