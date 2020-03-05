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
public class BetChoice implements Serializable{
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer choiceNumber;
	
	private String response;
	private float odds;
	private float totalAmount;
	
	@XmlIDREF
	private Question question;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<UserBet> userBets;

	
	public BetChoice() {
		super();
	}

	/**
	 * @param question
	 * @param response
	 * @param userBets
	 * @param odd
	 */
	public BetChoice(Question question, String response, float odd) {
		super();
		this.question = question;
		this.question.addChoice(this);
		this.response = response;
		this.userBets = new Vector<UserBet>();
		this.odds = odd;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return the userBets
	 */
	public Vector<UserBet> getUserBets() {
		return userBets;
	}

	/**
	 * @param userBets the userBets to set
	 */
	public void setUserBets(Vector<UserBet> userBets) {
		this.userBets = userBets;
	}
	
	public void addUserBet(UserBet userBet) {
		this.userBets.add(userBet);
	}
	
	public void removeUserBet(UserBet bet) {
		userBets.remove(bet);
	}

	/**
	 * @return the odd
	 */
	public float getOdds() {
		return odds;
	}

	/**
	 * @param odd the odd to set
	 */
	public void setOdds(float odd) {
		this.odds = odd;
	}
	
	/**
	 * @return the betID
	 */
	public Integer getChoiceNumber() {
		return choiceNumber;
	}

	/**
	 * @param betID the betID to set
	 */
	public void setBetID(Integer choiceNumber) {
		this.choiceNumber = choiceNumber;
	}

	/**
	 * @return the totalAmount
	 */
	public float getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void addTotalAmount(float amount) {
		this.totalAmount += amount;
	}


	/**
	 * @param choiceNumber the choiceNumber to set
	 */
	public void setChoiceNumber(Integer choiceNumber) {
		this.choiceNumber = choiceNumber;
	}

	@Override
	public String toString() {
		return response + "| odd : " + odds;
	}

}
