package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User implements Serializable {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer uid;
	
	private String name;
	private String familyName;
	private int age;
	private String password;
	private String nationality;
	private String email;
	private String username;
	private String creditCard;
	private String birthDate;
	private Date creationDate;	
	private boolean isAdmin;
	
	private float money;
	private float budget;
	private boolean budgetBool;
	private float moneySpentPerMonth;	
	
	@XmlIDREF @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<UserBet> bets;
	
	@XmlIDREF @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<User> friends;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<Sport> preferences;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<Message> messagesForum;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<Report> reports;
	
	public User() {
		super();
	}
	
	
	/**
	 * @param name
	 * @param familyName
	 * @param age
	 * @param nationality
	 * @param email
	 * @param username
	 * @param creditCard
	 * @param birthDate
	 */
	public User(String name, String familyName, int age, String password,  String nationality, String email, String username,
			String creditCard, String birthDate) {
		super();
		this.name = name;
		this.familyName = familyName;
		this.age = age;
		this.password = password;
		this.nationality = nationality;
		this.email = email;
		this.username = username;
		this.creditCard = creditCard;
		this.birthDate = birthDate;
		this.creationDate = new Date();
		
		this.money = 0;
		this.budget = 0;
		this.budgetBool = false;
		this.moneySpentPerMonth = 0;
		this.isAdmin = false;
		
		this.bets = new Vector<UserBet>();
		this.friends = new Vector<User>();		
		this.preferences = new Vector<Sport>();
		this.messagesForum = new Vector<Message>();
		this.reports = new Vector<Report>();
	}


	public Vector<UserBet> getBets() {
		return bets;
	}


	public void setBets(Vector<UserBet> bets) {
		this.bets = bets;
	}
	
	public void addUserBet(UserBet userBet) {
		this.bets.add(userBet);
	}
	
	public void removeUserBet(UserBet bet) {
		bets.remove(bet);
	}


	public float getMoney() {
		return money;
	}


	public void setMoney(float money) {
		this.money = money;
	}
	
	public void updateMoney(float money) {
		this.money += money;
	}


	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	
	/** 
	 * @return the password 
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the creditCard
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	public Vector<User> getFriends() {
		return friends;
	}


	public void setFriends(Vector<User> friends) {
		this.friends = friends;
	}
	
	public void addFriend(User user) {
		friends.add(user);
	}
	
	public void removeFriend(User friend) {
		friends.remove(friend);
	}


	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}


	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}


	/**
	 * @return the budget
	 */
	public float getBudget() {
		return budget;
	}


	/**
	 * @param budget the budget to set
	 */
	public void setBudget(float budget) {
		this.budget = budget;
	}


	/**
	 * @return the moneySpentPerMonth
	 */
	public float getMoneySpentPerMonth() {
		return moneySpentPerMonth;
	}


	/**
	 * @param moneySpentPerMonth the moneySpentPerMonth to set
	 */
	public void setMoneySpentPerMonth(float moneySpentPerMonth) {
		this.moneySpentPerMonth = moneySpentPerMonth;
	}
	
	public void addMoneySpentPerMont(float money) {
		this.moneySpentPerMonth += money;
	}


	/**
	 * @return the preferences
	 */
	public Vector<Sport> getPreferences() {
		return preferences;
	}


	/**
	 * @param preferences the preferences to set
	 */
	public void setPreferences(Vector<Sport> preferences) {
		this.preferences = preferences;
	}
	
	public void addPreference(Sport sport) {
		this.preferences.add(sport);
	}


	/**
	 * @return the messagesForum
	 */
	public Vector<Message> getMessagesForum() {
		return messagesForum;
	}


	/**
	 * @param messagesForum the messagesForum to set
	 */
	public void setMessagesForum(Vector<Message> messagesForum) {
		this.messagesForum = messagesForum;
	}
	
	public void addMessage(Message message) {
		this.messagesForum.add(message);
	}


	/**
	 * @return the reports
	 */
	public Vector<Report> getReports() {
		return reports;
	}


	/**
	 * @param reports the reports to set
	 */
	public void setReports(Vector<Report> reports) {
		this.reports = reports;
	}
	
	public void addReport(Report report) {
		this.reports.add(report);
	}


	/**
	 * @return the budgetBool
	 */
	public boolean isBudgetBool() {
		return budgetBool;
	}


	/**
	 * @param budgetBool the budgetBool to set
	 */
	public void setBudgetBool(boolean budgetBool) {
		this.budgetBool = budgetBool;
	}


	@Override
	public String toString() {
		return username;	
	}



}
