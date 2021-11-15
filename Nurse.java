
public class Nurse extends User {
	//INSTANCE VARIABLE

	String doctorList;
	
	
	//Constructor

	public Nurse(String username, String password, String name, String doctorList) {
		super(username, password, name);
		// TODO Auto-generated constructor stub
		this.doctorList = doctorList;
		
	}
	//getter & setter
	
	public String getDocotrList() {
		return doctorList;
		
	}
	public void setDoctorList(String doctorList) {
		this.doctorList = doctorList; 
	}
	

}
