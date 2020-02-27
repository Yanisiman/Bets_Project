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
public class Bet implements Serializable{
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer betID;
	
	private String response;
	private float odd;
	
	@XmlIDREF
	private Question question;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<UserBet> userBets;

	
	public Bet() {
		super();
	}

	/**
	 * @param question
	 * @param response
	 * @param userBets
	 * @param odd
	 */
	public Bet(Question question, String response, float odd) {
		super();
		this.question = question;
		this.question.addChoices(this);
		this.response = response;
		this.userBets = new Vector<UserBet>();
		this.odd = odd;
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

	/**
	 * @return the odd
	 */
	public float getOdd() {
		return odd;
	}

	/**
	 * @param odd the odd to set
	 */
	public void setOdd(float odd) {
		this.odd = odd;
	}
	
	/**
	 * @return the betID
	 */
	public Integer getBetID() {
		return betID;
	}

	/**
	 * @param betID the betID to set
	 */
	public void setBetID(Integer betID) {
		this.betID = betID;
	}

	@Override
	public String toString() {
		return response + "| odd : " + odd;
	}

}
