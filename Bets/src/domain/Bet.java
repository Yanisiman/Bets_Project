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

import exceptions.QuestionAlreadyExist;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bet implements Serializable{
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	
	private Question question;
	private String response;
	private float odd;
	
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
	public Bet(Question question, String response) {
		super();
		this.question = question;
		this.response = response;
		this.userBets = new Vector<UserBet>();
		this.odd = 0;
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

}
