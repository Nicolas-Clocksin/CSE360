package application;

public class Appointment {
	//instance variables
	private String date;
	private String time;
	private String vitals;
	private String exams;
	private String notes;
	private String pescriptions;
	private String participants;
	//constructor
	public Appointment(String date, String time, String vitals, String exams, String notes, String pescriptions,
			String participants) {
		super();
		this.date = date;
		this.time = time;
		this.vitals = vitals;
		this.exams = exams;
		this.notes = notes;
		this.pescriptions = pescriptions;
		this.participants = participants;
	}
	//getters/setters
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getVitals() {
		return vitals;
	}
	public void setVitals(String vitals) {
		this.vitals = vitals;
	}
	public String getExams() {
		return exams;
	}
	public void setExams(String exams) {
		this.exams = exams;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getPrescriptions() {
		return pescriptions;
	}
	public void setPrescriptions(String pescriptions) {
		this.pescriptions = pescriptions;
	}
	public String getParticipants() {
		return participants;
	}
	public void setParticipants(String participants) {
		this.participants = participants;
	}
}
