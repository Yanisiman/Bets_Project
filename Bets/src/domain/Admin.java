package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Admin extends User implements Serializable{
	
	public Admin() {
		super();
	}
	
	public Admin(String name, String familyName, int age, String password, String nationality, String email, String username,
			String creditCard, String birthDate) {
		super(name, familyName, age, password, nationality, email, username, creditCard, birthDate);
		setAdmin(true);
	}
	
	public Admin addAdmin(User user) {
		return new Admin(user.getName(), user.getFamilyName(), user.getAge(), user.getPassword(), user.getNationality(),
				user.getEmail(), user.getUsername(), user.getCreditCard(), user.getBirthDate());
	}
	

	public void accessUserAccount() {
		
	}
	
	public String toString() {
		return this.getName();
	}
}
