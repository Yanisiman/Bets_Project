package domain;

public class Admin extends User {

	public Admin(String name, String familyName, int age, String password, String nationality, String email, String username,
			String creditCard, String birthDate) {
		super(name, familyName, age, password, nationality, email, username, creditCard, birthDate);
	}
	
	public Admin addAdmin(User user) {
		return new Admin(user.getName(), user.getFamilyName(), user.getAge(), user.getPassword(), user.getNationality(),
				user.getEmail(), user.getUsername(), user.getCreditCard(), user.getBirthDate());
	}

}
