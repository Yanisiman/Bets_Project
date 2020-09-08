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

import exceptions.QuestionAlreadyExist;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@XmlType(name="userBett")
public class UserBet implements Serializable{

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer userBetNumber;
	private int amountBet;
	private float odds;
	
	@XmlIDREF
	private User user;
	
	@XmlIDREF
	private BetChoice betChoice;
	
	public UserBet() {
		super();
	}
	
	/**
	 * @param user
	 * @param amountBet
	 * @param bet
	 */
	public UserBet(User user, int amountBet, BetChoice bet) {
		super();
		this.user = user;
		this.amountBet = amountBet;
		this.betChoice = bet;
		this.odds = bet.getOdds();
		
		this.user.addUserBet(this);
		this.betChoice.addUserBet(this);
		this.betChoice.addTotalAmount(amountBet);
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the amountBet
	 */
	public int getAmountBet() {
		return amountBet;
	}

	/**
	 * @param amountBet the amountBet to set
	 */
	public void setAmountBet(int amountBet) {
		this.amountBet = amountBet;
	}

	/**
	 * @return the bet
	 */
	public BetChoice getBet() {
		return betChoice;
	}

	/**
	 * @param bet the bet to set
	 */
	public void setBet(BetChoice bet) {
		this.betChoice = bet;
	}
	
	/**
	 * @return the userBetNumber
	 */
	public Integer getUserBetNumber() {
		return userBetNumber;
	}

	/**
	 * @param userBetNumber the userBetNumber to set
	 */
	public void setUserBetNumber(Integer userBetNumber) {
		this.userBetNumber = userBetNumber;
	}
	
	/**
	 * @return the odds
	 */
	public float getOdds() {
		return odds;
	}

	/**
	 * @param odds the odds to set
	 */
	public void setOdds(float odds) {
		this.odds = odds;
	}

	@Override
	public String toString() {
		Question q = betChoice.getQuestion();
		Event e = q.getEvent();
		return  e + " : \n\t" +  q +  " : \n\t" + betChoice + " -> " + amountBet + "€";
	}

}
