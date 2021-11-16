package application;

public class Patient extends User {
	
	//instance variables
	String dateOfBirth;
	String currDoctor;
	String message;
	String phone;
	String pharmacy;
	String contactInfo;
	String insurance;
	
	//constructor
	public Patient(String username, String password, String name, String dateOfBirth, String currDoctor, String message,
			String phone, String pharmacy, String conctactInfo, String insurance) {
		super(username, password, name);
		this.dateOfBirth = dateOfBirth;
		this.currDoctor = currDoctor;
		this.message = message;
		this.phone = phone;
		this.pharmacy = pharmacy;
		this.contactInfo = conctactInfo;
		this.insurance = insurance;
	}

	//getters/setters
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCurrDoctor() {
		return currDoctor;
	}

	public void setCurrDoctor(String currDoctor) {
		this.currDoctor = currDoctor;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String conctactInfo) {
		this.contactInfo = conctactInfo;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	
}
