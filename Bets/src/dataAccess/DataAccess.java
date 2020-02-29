package dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Bet;
import domain.Event;
import domain.Question;
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

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month, 28));

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

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}
			
			Bet b1 = new Bet(q1, "Home", 0);
			Bet b2 = new Bet(q1, "Draw", 0);
			Bet b3 = new Bet(q1, "Away", 0);
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

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

			db.persist(user);
			db.persist(user2);
			db.persist(yanis);
			db.persist(admin);
			
			db.persist(b1);
			db.persist(b2);
			db.persist(b3);
			
			yanis.addFriend(user2);

			
			Date date = new Date();
			date.setMinutes(date.getMinutes() + 1);
			
			Event e = new Event(35,"THIS IS A TEST", date);
			Question q = e.addQuestion("Who will win the match?", 1);
			Question q11 = e.addQuestion("Who will score first?", 2);
			
			
			Bet b4 = new Bet(q, "Home", 0);
			Bet b5 = new Bet(q, "Draw", 0);
			Bet b6 = new Bet(q11, "Away", 0);
			
			UserBet userBet = new UserBet(user2, 10, b4);
			q.setResult(b4);
			
			db.persist(e);
			db.persist(q);
			db.persist(q11);
			db.persist(b4);
			db.persist(b5);
			db.persist(b6);
			db.persist(userBet);
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

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
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
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
			TypedQuery<User> q2 = db.createQuery("SELECT u from User u " + "WHERE u.username = \"" + username + "\"", User.class);
			return q2.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}
	
	public User updateUser(String email, String username, String password, String name, String familyName, String creditCard, int money) {
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
		
		db.getTransaction().commit();
		return user;
	}
	
	public void deleteUser(User user) {
		db.getTransaction().begin();		
		Query q = db.createQuery("DELETE FROM User u WHERE u.email = \"" + user.getEmail() + "\"");
		q.executeUpdate();		
		db.getTransaction().commit();
	}

	public void storeUser(User user) {
		db.getTransaction().begin();
		db.persist(user);
		db.getTransaction().commit();
	}
	
	public void addMoneyUser(User u, int money) {
		try {
			TypedQuery<User> q = db.createQuery("SELECT u from User u WHERE u.username = \"" + u.getUsername() + "\"", User.class);
			User user = q.getSingleResult();
			
			db.getTransaction().begin();
			user.updateMoney(money);
			db.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error to get the user");
		}
	}
	
	public User userBet(User u, int amount, Bet bet) {
		try {
			
			TypedQuery<Bet> q = db.createQuery("SELECT b from Bet b WHERE b.betID = " + bet.getBetID(), Bet.class);
			Bet b = q.getSingleResult();
			
			TypedQuery<User> q2 = db.createQuery("SELECT u from User u WHERE u.email = \"" + u.getEmail() + "\"", User.class);
			User user = q2.getSingleResult();
			
			db.getTransaction().begin();
			UserBet userBet = new UserBet(user, amount, b);
			db.persist(userBet);
			db.getTransaction().commit();
			
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}
	
	public List<User> getUsers(){
		TypedQuery<User> a = db.createQuery("SELECT u from User u", User.class);
		return a.getResultList();
		
	}
	
	public Event createEvent(String description, Date eventDate) {
		db.getTransaction().begin();
		Event event = new Event(description, eventDate);
		db.persist(event);
		db.getTransaction().commit();
		return event;
	}
	
	public Bet addBet(Question question, String response, float odd) {
		TypedQuery<Question> q = db.createQuery("SELECT q from Question q WHERE q.questionNumber = " + question.getQuestionNumber(), Question.class);
		Question quest = q.getSingleResult();
		
		if (quest == null) {
			System.out.println("DIDNT FIND " + question);
			return null;
		}			
		
		db.getTransaction().begin();
		Bet bet = new Bet(quest, response, odd);
		db.persist(bet);
		db.getTransaction().commit();
		return bet;
	}
	
	public void removeEvent(Event e) {
		db.getTransaction().begin();		
		
		Query q = db.createQuery("DELETE FROM Event e WHERE e.eventNumber = " + e.getEventNumber());
		q.executeUpdate();		
		db.getTransaction().commit();
		
		for (Question question: e.getQuestions()) {
			removeQuestion(question);
			
			for (Bet b: question.getChoices()) {
				removeBet(b);
			}
		}

	}
	
	public void removeQuestion(Question question) {
		db.getTransaction().begin();		
		Query q = db.createQuery("DELETE FROM Question e WHERE e.questionNumber = " + question.getQuestionNumber());
		q.executeUpdate();		
		db.getTransaction().commit();
		
		for (Bet b: question.getChoices()) {
			removeBet(b);
		}
	}
	
	public void removeBet(Bet b) {
		db.getTransaction().begin();		
		Query q = db.createQuery("DELETE FROM Bet e WHERE e.betID = " + b.getBetID());
		q.executeUpdate();		
		db.getTransaction().commit();
	}
	
	public Vector<User> getFriends(User user){
		try {
			
			TypedQuery<User> q2 = db.createQuery("SELECT u from User u " + "WHERE u.username = \"" + user.getUsername() + "\"", User.class);
			User u = q2.getSingleResult();
			System.out.println(u.getFriends());
			return u.getFriends();

		} catch (Exception e) {
			System.out.println("Error with friends");
			return null;
		}
	}
	
	public Vector<UserBet> getUserBet(User user){
		try {
			
			TypedQuery<User> q2 = db.createQuery("SELECT u from User u " + "WHERE u.username = \"" + user.getUsername() + "\"", User.class);
			User u = q2.getSingleResult();
			System.out.println(u.getBets());
			return u.getBets();

		} catch (Exception e) {
			System.out.println("Error with userBets");
			return null;
		}
	}

}
