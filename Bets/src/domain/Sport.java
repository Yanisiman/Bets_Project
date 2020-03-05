package domain;

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
	@Id @GeneratedValue
	private String sportName;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<Event> sportEvent = new Vector<Event>();
	

	/**
	 * @param sportName
	 * @param sportEvent
	 */
	
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
}
