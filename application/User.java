package application;

public class User {
	//instance variables
	private String username;
	private String password;
	private String name;
	//constructor
	public User(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
	}
	//setters/getters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//methods
	
	//resetPassword takes in some proof string to check against
	//past data to determine if they can reset password
	void resetPassword(String proof, String p) {
		//This is where they would do some sort of check...
		if(proof != null) {
			setPassword(p);
		}
		
	}
	//verifyLogin takes a password and compares it to the users password
	//returning true if they are the same, false otherwise
	boolean verifyLogin(String p) {
		return password == p;
	}
	
	
}
