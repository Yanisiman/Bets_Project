package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
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
	private Vector<UserBet> bets;
	private int money;
	private boolean isAdmin;
	
	
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
		this.bets = new Vector<UserBet>();
		this.money = 0;
		this.isAdmin = false;
		
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


	public int getMoney() {
		return money;
	}


	public void setMoney(int money) {
		this.money = money;
	}
	
	public void updateMoney(int money) {
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
	
	@Override
	public String toString () {
		return name + familyName + " has been created \n";	
	}



}
