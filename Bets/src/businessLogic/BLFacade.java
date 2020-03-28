package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.Report;
import domain.ReportType;
import domain.Sport;
import domain.User;
import domain.UserBet;
import domain.BetChoice;
import domain.Event;
import domain.Message;
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
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	
	
	/** *** ** Events ** *** **/
	
	@WebMethod public Event createEvent(String description, Date eventDate, Sport sport) throws EventFinished;
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	@WebMethod public Vector<Event> getEvents (String sportname);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	@WebMethod public Vector<Date> getEventsMonth(Date date, Sport sport);
	
	@WebMethod public Event getEvent(Event event);
	
	@WebMethod public void removeEvent(Event event, Sport sport);
	
	

	/** *** ** Questions ** *** **/
	
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
	@WebMethod public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	@WebMethod public Question getQuestion(Question question);
	
	@WebMethod public void removeQuestion(Question question);
	
	@WebMethod public Question setResult(Question question, BetChoice choice);
	
	
	
	/** *** ** BetChoices ** *** **/
	
	@WebMethod public BetChoice addBetChoice(Question question, String response, float odd);
	
	@WebMethod public void removeBetChoice(BetChoice bet);
	
	
	
	
	/** *** ** UserBet ** *** **/
	
	@WebMethod public Vector<UserBet> getUserBets(User user);
	
	@WebMethod public void removeUserBet(UserBet bet);
	
	@WebMethod public User userBet(User user, int amount, BetChoice bet);
	
	
	
	
	/** *** ** Users ** *** **/
	
	@WebMethod public User checkLogin (String primaryKey, String password);
	
	@WebMethod public void createUser(User user);
	
	@WebMethod public User updateUser(String email, String username, String password, String name, String familyName, String creditCard, float money, float budget);
	
	@WebMethod public void deleteUser(User user);

	@WebMethod public void addMoney(User user, float money);
	
	@WebMethod public List<User> displayUsers();
	
	@WebMethod public boolean emailExist(String email);

	@WebMethod public boolean addFriend(User user, String friend);
	
	@WebMethod public void removeFriend(User user, User friend);
	
	@WebMethod public Vector<User> getFriends(User user);
	
	
	
	/** *** ** Sports ** *** **/
	
	@WebMethod public void addSport(Sport sport);
	
	@WebMethod public void removeSport (String sportName);

	@WebMethod public User addUserSport(Sport sport, User user);
	
	@WebMethod public List<Sport> getAllSport();
	
	@WebMethod public Sport getSport(String sportName);
	
	@WebMethod public Vector<Event> getSportEvents(Date date, Sport sport);
	
	@WebMethod public Vector<Sport> getUserPreferences(User user);
	
	@WebMethod 	public boolean alreadyExist(String sport);
	
	
	

	/** *** ** Messages ** *** **/
	
	@WebMethod public Vector<Message> getAllMessages();
	
	@WebMethod public Message createMessage(User user, String message);
	
	@WebMethod public Vector<Message> getMessagesOfUser(User user);
	
	@WebMethod public User getUserOfMessage(Message message);
	
	
	
	
	/** *** ** Reports ** *** **/
	
	@WebMethod public Report sendReport(User user, String message, ReportType type);
	
	@WebMethod public Vector<Report> getReportByType(ReportType type);
}
