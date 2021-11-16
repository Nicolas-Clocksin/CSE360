package application;

public class Nurse extends User {
	
	//instance variable
	String doctorList;
	
	//constructor
	public Nurse(String username, String password, String name, String doctorList) {
		super(username, password, name);
		this.doctorList = doctorList;
	}
	
	//getters/setters
	public String getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(String doctorList) {
		this.doctorList = doctorList;
	}
	
}
