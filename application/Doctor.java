package application;

public class Doctor extends User {
	//instance variables
	String patientList;
	String currNurse;
	
	//constructor
	public Doctor(String username, String password, String name, String currNurse, String patientList) {
		super(username, password, name);
		this.currNurse = currNurse;
		this.patientList = patientList;
	}
	
	//getters/setters
	public String getPatientList() {
		return patientList;
	}
	public void setPatientList(String patientList) {
		this.patientList = patientList;
	}
	public String getCurrNurse() {
		return currNurse;
	}
	public void setCurrNurse(String currNurse) {
		this.currNurse = currNurse;
	}
	
}
