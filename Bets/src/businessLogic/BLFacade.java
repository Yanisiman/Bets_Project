package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.Sport;
import domain.User;
import domain.UserBet;
import domain.BetChoice;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	@WebMethod public User checkLogin (String primaryKey, String password);
	
	@WebMethod public void createUser(User user);
	
	@WebMethod public User updateUser(String email, String username, String password, String name, String familyName, String creditCard, int money);
	
	@WebMethod public void deleteUser(User user);

	@WebMethod public void addMoney(User user, int money);
	
	@WebMethod public List<User> displayUsers();

	@WebMethod public User userBet(User user, int amount, BetChoice bet);
	
	@WebMethod public Event createEvent(String description, Date eventDate) throws EventFinished;
	
	@WebMethod public BetChoice addBetChoice(Question question, String response, float odd);
	
	@WebMethod public void removeEvent(Event event);
	
	@WebMethod public void removeQuestion(Question question);
	
	@WebMethod public void removeBetChoice(BetChoice bet);
	
	@WebMethod public Vector<User> getFriends(User user);
	
	@WebMethod public Vector<UserBet> getUserBets(User user);
	
	@WebMethod public boolean emailExist(String email);
	
	@WebMethod public void removeUserBet(UserBet bet);
	
	@WebMethod public void removeFriend(User user, User friend);
	
	@WebMethod public void addFriend(User user, String friend);
	
	@WebMethod public List<Sport> getAllSport();
	
	@WebMethod 	public boolean alreadyExist(String sport);
	
	@WebMethod public Vector<Event> getEvents (String sportname);
	
	@WebMethod 	public void addSport(Sport sport);
	
	@WebMethod 	public void removeSport (String sportName);
	
	@WebMethod public Sport getSport(String sportName);
	
	@WebMethod public Vector<Event> getSportEvents(Date date, Sport sport);
}

