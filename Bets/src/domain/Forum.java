package domain;

import java.util.Vector;

public class Forum {

	private Vector<Message> messages;
	
	public Forum() {
		this.messages = new Vector<Message>();
	}

	/**
	 * @return the messages
	 */
	public Vector<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(Vector<Message> messages) {
		this.messages = messages;
	}
	
	public void addMessage(Message message)	{
		this.messages.add(message);
	}

}
