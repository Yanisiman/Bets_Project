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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import exceptions.QuestionAlreadyExist;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class UserBet implements Serializable{
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id //@GeneratedValue
	
	private int amountBet;
	
	@XmlIDREF
	private User user;
	@XmlIDREF
	private Bet bet;
	
	public UserBet() {
		super();
	}
	
	/**
	 * @param user
	 * @param amountBet
	 * @param bet
	 */
	public UserBet(User user, int amountBet, Bet bet) {
		super();
		this.user = user;
		this.amountBet = amountBet;
		this.bet = bet;
		
		this.user.addUserBet(this);
		this.bet.addUserBet(this);
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
	public Bet getBet() {
		return bet;
	}

	/**
	 * @param bet the bet to set
	 */
	public void setBet(Bet bet) {
		this.bet = bet;
	}

}
