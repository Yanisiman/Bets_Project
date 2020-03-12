package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Message {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer messageNumber;
	private User user;
	private String message;
	private Date date;
	
	public Message(User user, String message) {
		this.user = user;
		this.message = message;
		this.date = new Date();
		
		this.user.addMessage(this);
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the messageNumber
	 */
	public Integer getMessageNumber() {
		return messageNumber;
	}

	/**
	 * @param messageNumber the messageNumber to set
	 */
	public void setMessageNumber(Integer messageNumber) {
		this.messageNumber = messageNumber;
	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
		try {
			return format.format(date) + " : " +  user + " : " + message;
		} catch (Exception e) {
			return date + " : " +  user + " : " + message;
		}		
	}

}
