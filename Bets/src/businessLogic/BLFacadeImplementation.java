package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.BetChoice;
import domain.Event;
import domain.Question;
import domain.Sport;
import domain.User;
import domain.UserBet;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();
		
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}
		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	    DataAccess dBManager=new DataAccess();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		qry=dBManager.createQuestion(event,question,betMinimum);		

		dBManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  { 
		DataAccess dbManager=new DataAccess();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		DataAccess dbManager=new DataAccess();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	}
    
    @WebMethod
	public User checkLogin (String primaryKey, String password) {
    	if (password.equals("")) {
    		DataAccess dbManagerAccess = new DataAccess();
    		User user = dbManagerAccess.getUser(primaryKey);
    		dbManagerAccess.close();
    		return user;
    	}
    	
		DataAccess dbManagerAccess = new DataAccess();
		User user = dbManagerAccess.getUser(primaryKey, password);
		dbManagerAccess.close();
		return user;
	}


	@WebMethod
	public void createUser(User user) {
		DataAccess dBManager = new DataAccess();
		dBManager.storeUser(user);
		dBManager.close();
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		DataAccess dBManager = new DataAccess();
		dBManager.deleteUser(user);
		dBManager.close();
	}

	@Override
	public void addMoney(User user, float money) {
		DataAccess dBManager = new DataAccess();
		dBManager.addMoneyUser(user, money);
		dBManager.close();
	}


	@Override
	public User updateUser(String email, String username, String password, String name, String familyName, String creditCard, float money, float budget) {
		DataAccess dBManager = new DataAccess();
		User user = dBManager.updateUser(email, username, password, name, familyName, creditCard, money, budget);
		dBManager.close();
		return user;
	}


	@Override
	public User userBet(User u, int amount, BetChoice bet) {
		DataAccess dBManager = new DataAccess();
		User user = dBManager.userBet(u, amount, bet);
		dBManager.close();
		return user;
	}


	@Override
	public List<User> displayUsers() {
		DataAccess dBManager = new DataAccess();
		List<User> userList = dBManager.getUsers();
		dBManager.close();
		return userList;	
		
	}


	@Override
	public Event createEvent(String description, Date eventDate, Sport sport) throws EventFinished {
		DataAccess dBManager=new DataAccess();
		Event event = null;
		
		if(new Date().compareTo(eventDate)>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		event = dBManager.createEvent(description, eventDate, sport);

		dBManager.close();
		
		return event;
	}


	@Override
	public BetChoice addBetChoice(Question question, String response, float odd) {
		DataAccess dBManager = new DataAccess();
		BetChoice bet = dBManager.addBet(question, response, odd);
		dBManager.close();
		return bet;
	}
	
	@Override
	public Event getEvent(Event event) {
		DataAccess dBManager = new DataAccess();
		Event e = dBManager.getEvent(event);
		dBManager.close();
		return e;
	}


	@Override
	public void removeEvent(Event event, Sport sport) {
		DataAccess dBManager = new DataAccess();
		dBManager.removeEvent(event, sport);
		dBManager.close();
	}


	@Override
	public void removeQuestion(Question question) {
		DataAccess dBManager = new DataAccess();
		dBManager.removeQuestion(question);
		dBManager.close();
	}


	@Override
	public void removeBetChoice(BetChoice bet) {
		DataAccess dBManager = new DataAccess();
		dBManager.removeBet(bet);
		dBManager.close();
	}


	@Override
	public Vector<User> getFriends(User user) {
		DataAccess dBManager = new DataAccess();
		Vector<User> friends = dBManager.getFriends(user);
		dBManager.close();
		return friends;
	}


	@Override
	public Vector<UserBet> getUserBets(User user) {
		DataAccess dBManager = new DataAccess();
		Vector<UserBet> bets = dBManager.getUserBet(user);
		dBManager.close();
		return bets;
	}
	
	@Override
	public boolean emailExist(String email) {
		DataAccess dBManager = new DataAccess();
		List<User> users = dBManager.getUsers();
		dBManager.close();
		
		for(User user : users) {
			if(user.getEmail().equals(email))
				return true;
		}
		
		return false;
	}


	@Override
	public void removeUserBet(UserBet bet) {
		DataAccess dBManager = new DataAccess();
		dBManager.removeUserBet(bet);
		dBManager.close();
	}


	@Override
	public void removeFriend(User user, User friend) {
		DataAccess dBManager = new DataAccess();
		dBManager.removeFriend(user, friend);
		dBManager.close();
	}


	@Override
	public void addFriend(User user, String friend) {
		DataAccess dBManager = new DataAccess();
		dBManager.addFriend(user, friend);
		dBManager.close();
	}
	
	@Override
	public List<Sport> getAllSport(){
		DataAccess dBManager = new DataAccess();
		List<Sport> sports = dBManager.getSport();	
		dBManager.close();
		return sports;
	}
	
	@Override
	public boolean alreadyExist(String sport) {
		List<Sport> sports = getAllSport();
		for(Sport s: sports) {
			if(s.getSportName().equals(sport))
				return true;
		}
		return false;
	}
	
	@Override
	public void addSport(Sport sport) {
		DataAccess dBManager = new DataAccess();
		dBManager.addSport(sport);
		dBManager.close();
	}


	@Override
	public void setResult(Question question, BetChoice choice) {
		DataAccess dBManager = new DataAccess();
		dBManager.setResult(question, choice);
		dBManager.close();
	}


	@Override
	public Question getQuestion(Question question) {
		DataAccess dBManager = new DataAccess();
		Question q = dBManager.getQuestion(question);
		dBManager.close();
		return q;
	}

	@Override
	public void removeSport (String sportName) {
		DataAccess dBManager = new DataAccess();
		dBManager.removeSport(sportName);
		dBManager.close();
	}
	
	@Override 
	public Vector<Event> getEvents (String sportname){
		DataAccess dBManager = new DataAccess();
		List<Sport> sports = dBManager.getSport();
		Vector<Event> events = null;		
		for(Sport s: sports)
		{
			if (s.getSportName().equals(sportname)) {
				events = s.getSportEvent();
				break;
			}
		}
		dBManager.close();
		return events;
	}


	@Override
	public User addUserSport(Sport sport, User user) {
		DataAccess dBManager = new DataAccess();
		User u = dBManager.addSportUser(sport, user);
		dBManager.close();
		return u;
	}


	@Override
	public Vector<Sport> getUserPreferences(User user) {
		DataAccess dBManager = new DataAccess();
		Vector<Sport> sports = dBManager.getUserPreferences(user);
		dBManager.close();
		return sports;
	}
	
	@Override
	public Sport getSport(String sportName) {
		DataAccess dBManager = new DataAccess();
		Sport sport = dBManager.getUniqueSport(sportName);
		dBManager.close();
		return sport;
	}
	
	@Override
	public Vector<Event> getSportEvents(Date date, Sport sport){
		Vector<Event> events = getEvents(date);
		Vector<Event> sportEvents = sport.getSportEvent();
		Vector <Event> finalEvents = new Vector<Event>();
		for(Event e: sportEvents) {
			if(events.contains(e))
				finalEvents.add(e);
		}
		return finalEvents;
	}
}

