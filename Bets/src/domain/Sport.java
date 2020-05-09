package domain;

import java.lang.annotation.Repeatable;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Sport {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id 
	@GeneratedValue
	private Integer sportNumber;
	private String sportName;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST) 
	private Vector<Event> sportEvent = new Vector<Event>();
	

	/**
	 * @param sportName
	 * @param sportEvent
	 */
	
	public Sport() {
		super();
	}
	
	public Sport(String sportName) {
		this.sportName = sportName;
		this.sportEvent = new Vector<Event>();
	}


	public String getSportName() {
		return sportName;
	}


	public void setSportName(String sportName) {
		this.sportName = sportName;
	}


	public Vector<Event> getSportEvent() {
		return sportEvent;
	}


	public void setSportEvent(Vector<Event> sportEvent) {
		this.sportEvent = sportEvent;
	}
	
	public void addEvent(Event event) {
		sportEvent.add(event);
	}
	
	public void removeEvent(Event event) {
		sportEvent.remove(event);
	}


	/**
	 * @return the sportNumber
	 */
	public Integer getSportNumber() {
		return sportNumber;
	}


	/**
	 * @param sportNumber the sportNumber to set
	 */
	public void setSportNumber(Integer sportNumber) {
		this.sportNumber = sportNumber;
	}
	
	@Override
	public String toString() {
		return sportName;
	}
}
