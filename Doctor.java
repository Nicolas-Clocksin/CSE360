public class Doctor extends User{

	//Instance variables
	Patient patientList;
    Nurse currNurse;
	//constructor
	public Doctor(String username, String password, String name, Patient patientList, Nurse currNurse){
		super(username, password, name);
        this.patientList = patientList;
        this.currNurse = currNurse;
	}
	//getters and setters

	public String getPatientList() {
		return patientList;
	}

	public void setPatientList(Patient list) {
		this.patientList = list;
	}

	public String getCurrDoctor() {
		return currDoctor;
	}

	public void setCurrNurse(String currNurse) {
		this.currNurse = currNurse;
	}

	public Appointment takingPhysical(Appointment currentAppt) {
		return currentAppt.physical(this.name, this.patientList);
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

    public void viewRecords(Patient patient){
        for(int i = 0; i < patient.records[i].length; i++){
            System.out.println(patient.records[i]);
        }
        return;
    }

	}

