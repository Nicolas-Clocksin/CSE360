package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	/********************************
	 * Linked Lists
	 ******************************/

	static LinkedList<Patient> patientList = new LinkedList<Patient>();
	static LinkedList<Nurse> nurseList = new LinkedList<Nurse>();
	static LinkedList<Doctor> doctorList = new LinkedList<Doctor>();

	static LinkedList<Appointment> upcomingList = new LinkedList<Appointment>();
	static LinkedList<Appointment> waitingList = new LinkedList<Appointment>(); // I added this one
	static LinkedList<Appointment> recordList = new LinkedList<Appointment>();

	static LinkedList<Appointment> readyList = new LinkedList<Appointment>();
	static LinkedList<Appointment> currAppointmentList = new LinkedList<Appointment>();

	static Appointment currAppointment = null;
	static Appointment currRecord = null;
	static Patient currInfoPatient = null;

	static Patient currPatientUser = null;
	static Nurse currNurseUser = null;
	static Doctor currDoctorUser = null;

	static String currentApptoEdit = null; // Nurse: detects what patient appointment to edit
	static String currentPatienttoEdit = null;// Nurse: detects what patient info to edit
	static boolean infoPrinted = false;
	static boolean isUsernameAvailable = false;
	static boolean foundUsernameToReset = false;

	static String currsignUpUsername = null;// Add to global currsignUpUsername
	static String currsignUpPassword = null;// Add to global currsignUpPassword

	/********************************
	 * For Java FX Purposes
	 ******************************/
	@FXML
	private TextField usernameTextfield, passwordTextfield;
	@FXML
	private TextField bodyTemp, pulseRate, respRate, bloodPressure, examText, prescriptionText, notesText;
	@FXML
	private TextField newPhone, newPharmacy, newContact, newInsurance;
	@FXML
	private TextField profileName, profileDOB, profilePhone, permissionCode;

	@FXML
	private Button signUpButton, resetButton;

	@FXML
	private Label nameLabel;
	@FXML
	private Label LoginMessage;
	@FXML
	private Label appointmentLabel, vitalsLabel, createAppLabel, waitingLabel, examLabel;

	@FXML
	private Label dateLabel, examsLabel, notesLabel, participantsLabel, prescriptionsLabel, timeLabel, vitalzLabel,
			noAppFound;

	@FXML
	private Label CurrnameLabel, DOBLabel, phoneLabel, pharmacyLabel, contactLabel, insuranceLabel, editInfoMessage,
			currDoctor;

	@FXML
	private Label availableUsername, signUpMessage;

	@FXML
	private DatePicker appointmentDate;

	@FXML
	private ChoiceBox<String> appointmentTime, currDoctors, profileDoctor1, profileDoctor2;
	@FXML
	private ChoiceBox<String> profileNurses, profilePatients1, profilePatients2;

	// private String[] times = {"2:00-PM", "3:00-PM", "4:00-PM"};
	// private String[] times = {};
	// private String[] takenTimes = {};
	// private static ArrayList <String> times;
	// private static ArrayList <String> takenTimes;
	// private static ArrayList <String> availableTimes;
	private String[] doctorstoDisplay = {};

	@FXML
	private ListView<String> listOfAppointments, listOfWaitingAppointments, listOfRecordsN, listOfRecordsD,
			listOfUpAppointments, listOfEditAppointments, listOfEditAppointmentsN, listOfRecordsP, listOfPatientsToEdit;

	private Stage stage;
	private Scene scene;
	private Parent root;

	// For Closing program
	@FXML
	private Button exitButton;
	@FXML
	private AnchorPane LogInPane;
	Stage closeStage;

	/******************************************
	 * Main Starts - Initializes the program
	 *****************************************/

	public static void main(String[] args) {// throws FileNotFoundException{

		launch(args);

	}// End of main method

	@Override
	public void start(Stage stage) {

		/***************************************
		 * Read from text files to Linked Lists
		 *************************************/

		// Patients
		try {
			File myObj = new File("src/application/Patients.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				patientList.add(new Patient(myReader.nextLine(), myReader.nextLine(), myReader.nextLine(),
						myReader.nextLine(), myReader.nextLine(), myReader.nextLine(), myReader.nextLine(),
						myReader.nextLine(), myReader.nextLine(), myReader.nextLine()));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Nurses
		try {
			File myObj = new File("src/application/Nurses.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				nurseList.add(
						new Nurse(myReader.nextLine(), myReader.nextLine(), myReader.nextLine(), myReader.nextLine()));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Doctors
		try {
			File myObj = new File("src/application/Doctors.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				doctorList.add(new Doctor(myReader.nextLine(), myReader.nextLine(), myReader.nextLine(),
						myReader.nextLine(), myReader.nextLine()));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Upcoming Appointments
		try {
			File myObj = new File("src/application/Appointments.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				upcomingList.add(new Appointment(myReader.nextLine(), myReader.nextLine(), myReader.nextLine(),
						myReader.nextLine(), myReader.nextLine(), myReader.nextLine(), myReader.nextLine()));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// waiting appointments
		try {
			File myObj = new File("src/application/Waiting.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				waitingList.add(new Appointment(myReader.nextLine(), myReader.nextLine(), myReader.nextLine(),
						myReader.nextLine(), myReader.nextLine(), myReader.nextLine(), myReader.nextLine()));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// Recorded Appointments
		try {
			File myObj = new File("src/application/Records.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				recordList.add(new Appointment(myReader.nextLine(), myReader.nextLine(), myReader.nextLine(),
						myReader.nextLine(), myReader.nextLine(), myReader.nextLine(), myReader.nextLine()));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		/********************************
		 * The Application starts
		 ******************************/
		try {
			// BorderPane root = new BorderPane(); closeProgramX
			Parent root = FXMLLoader.load(getClass().getResource("LogInScene2.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			// if X is pressed to close the program -> call closeProgram(); closeProgram
			stage.setOnCloseRequest(event -> {
				event.consume();
				closeProgramX(stage);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// End of start()

	/********************************
	 * Display Labels
	 ******************************/
	public void displayOnLabel(String message) {
		nameLabel.setText(message);
	}

	public void displayLoginMessage(String message) {
		LoginMessage.setText(message);
	}

	public void displayAppointmentMessage(String message) {
		appointmentLabel.setText(message);
	}

	public void displayVitalsLabelMessage(String message) {
		vitalsLabel.setText(message);
	}

	public void displayExamLabelMessage(String message) {
		examLabel.setText(message);
	}

	public void displayCreateAppMessage(String message) {
		createAppLabel.setText(message);
	}

	public void displayWaitingAppMessage(String message) {
		waitingLabel.setText(message);
	}

	// New Ones
	public void displayDateLabel(String message) {
		dateLabel.setText(message);
	}

	public void displayExamsLabel(String message) {
		examsLabel.setText(message);
	}

	public void displayNotesLabel(String message) {
		notesLabel.setText(message);
	}

	public void displayParticipantsLabel(String message) {
		participantsLabel.setText(message);
	}

	public void displayPrescriptionsLabel(String message) {
		prescriptionsLabel.setText(message);
	}

	public void displayTimeLabel(String message) {
		timeLabel.setText(message);
	}

	public void displayVitalzLabel(String message) {
		vitalzLabel.setText(message);
	}

	public void displayNoAppFount(String message) {
		noAppFound.setText(message);
	}

	// DIsplays for Patient Edit info
	public void displayPName(String message) {
		CurrnameLabel.setText(message);
	}

	public void displayPDOB(String message) {
		DOBLabel.setText(message);
	}

	public void displayPPhone(String message) {
		phoneLabel.setText(message);
	}

	public void displayPPharmacy(String message) {
		pharmacyLabel.setText(message);
	}

	public void displayPContact(String message) {
		contactLabel.setText(message);
	}

	public void displayPInsurance(String message) {
		insuranceLabel.setText(message);
	}

	public void displayEditInfoLabel(String message) {
		editInfoMessage.setText(message);
	}

	public void displayEditCurrDoctorLabel(String message) {
		currDoctor.setText(message);
	}

	public void displayAvailableUsername(String message) {
		availableUsername.setText(message);
	}

	public void displaySignUpMessage(String message) {
		signUpMessage.setText(message);
	}

	/********************************
	 * Log in - Scene Controller
	 ******************************/
	public void login(ActionEvent event) throws IOException {

		String loginUsername = usernameTextfield.getText();
		String loginPassword = passwordTextfield.getText();

		if (loginUsername.trim().isEmpty() && loginPassword.trim().isEmpty())
			displayLoginMessage("Please Enter a Username and Password");

		else if (loginUsername.trim().isEmpty())
			displayLoginMessage("Please Enter a Username");

		else if (loginPassword.trim().isEmpty())
			displayLoginMessage("Please Enter a Password");

		else {
			// PATIENT LOGIN
			for (int i = 0; i < patientList.size(); i++) {
				if (loginUsername.equals(patientList.get(i).getUsername())) {
					if (loginPassword.equals(patientList.get(i).getPassword())) {
						currPatientUser = patientList.get(i);
						// System.out.println("user found");
						break;
					} else {
						// move to error scene or just restart login scene
					}
				}

			}

			// NURSE LOGIN
			for (int i = 0; i < nurseList.size(); i++) {
				if (loginUsername.equals(nurseList.get(i).getUsername())) {
					if (loginPassword.equals(nurseList.get(i).getPassword())) {
						currNurseUser = nurseList.get(i);
						break;
					} else {
						// move to error scene or just restart login scene
					}
				}
			}

			// DOCTOR LOGIN
			for (int i = 0; i < doctorList.size(); i++) {
				if (loginUsername.equals(doctorList.get(i).getUsername())) {
					if (loginPassword.equals(doctorList.get(i).getPassword())) {
						currDoctorUser = doctorList.get(i);
						break;
					} else {
						// move to error scene or just restart login scene
					}
				}
			}

			// Load Scenes
			if (currPatientUser != null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientScene.fxml"));
				root = loader.load();

				Main patientSceneController = loader.getController();
				patientSceneController.displayOnLabel("Welcome " + currPatientUser.getName());

				// Parent root = FXMLLoader.load(getClass().getResource("NurseScene.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}

			else if (currNurseUser != null) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("NurseScene.fxml"));
				root = loader.load();

				Main nurseSceneController = loader.getController();
				nurseSceneController.displayOnLabel("Welcome " + currNurseUser.getName());

				// Parent root = FXMLLoader.load(getClass().getResource("NurseScene.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}

			else if (currDoctorUser != null) {
				doctorList.get(0).setName("Angel FLores");
				System.out.println(doctorList.get(0).getName());
				FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorScene.fxml"));
				root = loader.load();

				Main doctorSceneController = loader.getController();
				doctorSceneController.displayOnLabel("Welcome " + currDoctorUser.getName());

				// Parent root = FXMLLoader.load(getClass().getResource("NurseScene.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}

			else {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ErrorScene.fxml"));
				root = loader.load();

				// ErrorSceneController errorSceneController = loader.getController();
				// patientSceneController.displayUsername(username);

				// Parent root = FXMLLoader.load(getClass().getResource("NurseScene.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		}
	}// End of Log in

	/********************************
	 * logout - Button Controller
	 ******************************/
	public void logout(ActionEvent event) throws IOException {

		currNurseUser = null;
		currDoctorUser = null;
		currPatientUser = null;

		Parent root = FXMLLoader.load(getClass().getResource("LogInScene2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/********************************
	 * Sign up - Scene Controller
	 ******************************/

	// add methods to sign up

	/********************************
	 * Nurse Main - Scene Controller
	 ******************************/

	public void goToUpcomingAppointments(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("UpcomingAppointmentsScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void goToAppointmentRecords(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("AppointmentRecordsScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void editPatientInfo(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("EditPatientInfoScene2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void editPatientAppointments(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("EditAppointmentScene2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void backToNurseMain(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("NurseScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/************************************************************
	 * Nurse Upcoming Appointment - Scene Controller
	 **********************************************************/
	public void takeVitals(ActionEvent event) throws IOException {

		listOfAppointments.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selectedAppointment = null;
		selectedAppointment = listOfAppointments.getSelectionModel().getSelectedItem();

		// String[] participantsStr = selectedAppointment.split(" ");

		if (listOfAppointments.getItems().isEmpty()) {

			// display message
			System.out.println("List is empty");
			displayAppointmentMessage("Click on 'Find Appointments' and select one");
		}

		else if (selectedAppointment == null) {

			// System.out.println("Nothing was selected");
			displayAppointmentMessage("Please select an appointment");

		}

		else {

			// System.out.println(selectedAppointment);

			String[] participantsStr = selectedAppointment.split(" ");

			for (int j = 0; j < upcomingList.size(); j++) {
				// participantsStr = upcomingList.get(j).getParticipants().split(" ");
				if (upcomingList.get(j).getDate().equals(participantsStr[2])
						&& upcomingList.get(j).getTime().equals(participantsStr[3]) && upcomingList.get(j)
								.getParticipants().equals(participantsStr[0] + " " + participantsStr[1])) {
					// currAppointmentList.add(upcomingList.get(j));
					System.out.println("Current Appointment was found");
					currAppointment = upcomingList.get(j);
					upcomingList.remove(j);
					System.out.println(currAppointment.getDate());
					break;
				}
				// selectedAppointment = null;
			}

			Parent root = FXMLLoader.load(getClass().getResource("VitalsScene.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of take Vitals

	public void recordVitals(ActionEvent event) throws IOException {

		// Define all textfields
		String temp = bodyTemp.getText();
		String pRate = pulseRate.getText();
		String rRate = respRate.getText();
		String pressure = bloodPressure.getText();

		// Check in anny is empty, if not, then update vitals and get back to upcom
		// Appointments
		if (temp.trim().isEmpty() && pRate.trim().isEmpty() && rRate.trim().isEmpty() && pressure.trim().isEmpty())
			displayVitalsLabelMessage("All vitals are missing");

		else if (temp.trim().isEmpty() || pRate.trim().isEmpty() || rRate.trim().isEmpty() || pressure.trim().isEmpty())
			displayVitalsLabelMessage("Some vitals are missing");

		else {
			// System.out.print("All things were inputted right");

			currAppointment.setVitals("Temp: " + temp + "   " + "P-Rate: " + pRate + "   " + "R-Rate: " + rRate + "   "
					+ "B-Press: " + pressure);

			waitingList.add(currAppointment);
			currAppointment = null;

			Parent root = FXMLLoader.load(getClass().getResource("UpcomingAppointmentsScene.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of Record Vitals

	@FXML
	void findAppointments(ActionEvent event) {

		// String[] participantsStr = null;

		for (int j = 0; j < upcomingList.size(); j++) {

			// Participants - Date - Time
			String appointment = upcomingList.get(j).getParticipants() + " " + upcomingList.get(j).getDate() + " "
					+ upcomingList.get(j).getTime();
			listOfAppointments.getItems().add(appointment);

		}
	}

	public void backToUpcomingApps(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("UpcomingAppointmentsScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/************************************************************
	 * Nurse Appointment Records - Scene Controller
	 **********************************************************/
	public void printRecords(ActionEvent event) throws IOException {

		listOfRecordsN.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selectedRecord = null;
		selectedRecord = listOfRecordsN.getSelectionModel().getSelectedItem();

		// String[] participantsStr = selectedAppointment.split(" ");

		if (listOfRecordsN.getItems().isEmpty()) {

			// display message
			System.out.println("List is empty");
			displayAppointmentMessage("Click on 'Find Records' and select one");
		}

		else if (selectedRecord == null) {

			// System.out.println("Nothing was selected");
			displayAppointmentMessage("Please select a Record");

		}

		else {

			// System.out.println(selectedAppointment);

			String[] participantsStr = selectedRecord.split(" ");

			for (int j = 0; j < recordList.size(); j++) {
				// participantsStr = upcomingList.get(j).getParticipants().split(" ");
				if (recordList.get(j).getDate().equals(participantsStr[2])
						&& recordList.get(j).getTime().equals(participantsStr[3])
						&& recordList.get(j).getParticipants().equals(participantsStr[0] + " " + participantsStr[1])) {
					// currAppointmentList.add(upcomingList.get(j));
					System.out.println("Record was found");
					currRecord = recordList.get(j);
					// recordList.remove(j);
					System.out.println(currRecord.getDate());
					break;
				}
				// selectedAppointment = null;
			}

			Parent root = FXMLLoader.load(getClass().getResource("RecordForNurses.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of printRecords

	@FXML
	void findRecordsNurse(ActionEvent event) {

		// String[] participantsStr = null;

		for (int j = 0; j < recordList.size(); j++) {

			// Participants - Date - Time
			String appointment = recordList.get(j).getParticipants() + " " + recordList.get(j).getDate() + " "
					+ recordList.get(j).getTime();
			listOfRecordsN.getItems().add(appointment);

		}
	}

	public void seeAppointment(ActionEvent event) {

		displayDateLabel(currRecord.getDate());
		displayExamsLabel(currRecord.getExams());
		displayNotesLabel(currRecord.getNotes());
		displayParticipantsLabel(currRecord.getParticipants());
		displayPrescriptionsLabel(currRecord.getPrescriptions());
		displayTimeLabel(currRecord.getTime());
		displayVitalzLabel(currRecord.getVitals());
	}

	public void backToAppointmentRecords(ActionEvent event) throws IOException {

		currRecord = null;

		Parent root = FXMLLoader.load(getClass().getResource("AppointmentRecordsScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/************************************************************
	 * Nurse Edit Patient Appointment - Scene Controller
	 **********************************************************/
	@FXML
	void findEditAppointmentsforNurses(ActionEvent event) {

		System.out.println("Trying to find an appointment");

		// assign Selected appointment to participants string
		// String[] participantsStr = null;

		for (int j = 0; j < upcomingList.size(); j++) {

			// String findApp = currPatientUser.getUsername() + " " +
			// currPatientUser.getCurrDoctor();

			String appointment = upcomingList.get(j).getParticipants() + " " + upcomingList.get(j).getDate() + " "
					+ upcomingList.get(j).getTime();
			listOfEditAppointmentsN.getItems().add(appointment);

		}
	} // End of findEditAppointmentsforNurses

	public void goToEditAppointmentForNurses(ActionEvent event) throws IOException {

		listOfEditAppointmentsN.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selectedRecord = null;
		selectedRecord = listOfEditAppointmentsN.getSelectionModel().getSelectedItem();

		// String[] participantsStr = selectedAppointment.split(" ");

		if (listOfEditAppointmentsN.getItems().isEmpty()) {

			// display message
			// System.out.println("List is empty");
			displayAppointmentMessage("Click on 'Find Appointments' and select one");
		}

		else if (selectedRecord == null) {

			// System.out.println("Nothing was selected");
			displayAppointmentMessage("Please select an Appointment");

		}

		else {

			// System.out.println(selectedAppointment); currAppointment

			String[] participantsStr = selectedRecord.split(" ");
			currentApptoEdit = participantsStr[0] + " " + participantsStr[1];

			for (int j = 0; j < upcomingList.size(); j++) {
				// participantsStr = upcomingList.get(j).getParticipants().split(" ");
				if (upcomingList.get(j).getDate().equals(participantsStr[2])
						&& upcomingList.get(j).getTime().equals(participantsStr[3]) && upcomingList.get(j)
								.getParticipants().equals(participantsStr[0] + " " + participantsStr[1])) {
					// currAppointmentList.add(upcomingList.get(j));
					System.out.println("Record was found");
					currAppointment = upcomingList.get(j);
					upcomingList.remove(j);
					System.out.println(currAppointment.getDate());
					break;
				}
				// selectedAppointment = null;
			}

			Parent root = FXMLLoader.load(getClass().getResource("FinishEditApp2.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of Go to Edit Appointment for Nurses

	public void pushEditedAppointmentForNurses(ActionEvent event) throws IOException {

		// appointmentTime.getItems().addAll(times);
		// String date = appointmentDate.get

		if (appointmentDate.getValue() == null) {

			displayCreateAppMessage("Please select Date ");

		}

		else if (appointmentTime.getValue() == null) {
			displayCreateAppMessage("Please select an available time");
		}

		else {
			LocalDate myDate = appointmentDate.getValue();

			String date = myDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
			// String time = the time on the screen
			String time = appointmentTime.getValue();
			String vitals = "";
			String exams = "";
			String notes = "";
			String prescriptions = "";
			// String participants = (currPatientUser.getUsername() + " " +
			// currPatientUser.getCurrDoctor());
			String participants = currentApptoEdit;
			// else -> Add to list, Return to Scene, Print report
			upcomingList.add(new Appointment(date, time, vitals, exams, notes, prescriptions, participants));

			System.out.println("Appointment added successfully");

			// Load patient Main Scene
			Parent root = FXMLLoader.load(getClass().getResource("NurseScene.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	}// End of Push Edited Appointment for nurses

	/************************************************************
	 * Nurse Edit Patient Info - Scene Controller
	 **********************************************************/
	@FXML
	void findPatientsToEdit(ActionEvent event) {

		System.out.println("Trying to find an appointment");

		// assign Selected appointment to participants string
		// String[] participantsStr = null;

		for (int j = 0; j < patientList.size(); j++) {

			// String findApp = currPatientUser.getUsername() + " " +
			// currPatientUser.getCurrDoctor();

			String patient = patientList.get(j).getDateOfBirth() + " " + patientList.get(j).getName();// + " " +
																										// upcomingList.get(j).getTime();
			listOfPatientsToEdit.getItems().add(patient);

		}
	} // End of findEditAppointmentsforNurses

	public void goToEditPatientInfo(ActionEvent event) throws IOException {

		listOfPatientsToEdit.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selectedPatient = null;
		selectedPatient = listOfPatientsToEdit.getSelectionModel().getSelectedItem();

		// String[] participantsStr = selectedAppointment.split(" ");

		if (listOfPatientsToEdit.getItems().isEmpty()) {

			// display message
			// System.out.println("List is empty");
			displayAppointmentMessage("Click on 'Find Appointments' and select one");
		}

		else if (selectedPatient == null) {

			// System.out.println("Nothing was selected");
			displayAppointmentMessage("Please select an Appointment");

		}

		else {

			// System.out.println(selectedAppointment); currAppointment

			String[] participantsStr = selectedPatient.split(" ");
			currentPatienttoEdit = selectedPatient;// participantsStr[0] + " " + participantsStr[1];
			// String pati

			for (int j = 0; j < patientList.size(); j++) {

				String findPatient = patientList.get(j).getDateOfBirth() + " " + patientList.get(j).getName();

				if (findPatient.equals(currentPatienttoEdit)) {
					// currAppointmentList.add(upcomingList.get(j)); currentPatienttoEdit
					System.out.println("Patient was found");
					currInfoPatient = patientList.get(j);
					// upcomingList.remove(j);
					System.out.println(currInfoPatient.getDateOfBirth() + " " + currInfoPatient.getName());
					break;
				}
			}

			Parent root = FXMLLoader.load(getClass().getResource("EditPatientInfoScene3.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of Go to Edit Appointment for Nurses

	public void updateInfoPatientsForNurses(ActionEvent event) throws IOException {

		if (infoPrinted == false) {
			displayEditInfoLabel("See current info first");
		} else {

			// Define all textfields
			String phonetoUptade = newPhone.getText();
			String pharmacytoUptade = newPharmacy.getText();
			String contactToUpdate = newContact.getText();
			String insuranceToUpdate = newInsurance.getText();
			String currDoctorToUpdate = currDoctors.getValue();

			// Check if any spots are empty
			// temp.trim().isEmpty()
			if (!phonetoUptade.trim().isEmpty())
				currInfoPatient.setPhone(phonetoUptade);

			if (!pharmacytoUptade.trim().isEmpty())
				currInfoPatient.setPharmacy(pharmacytoUptade);

			if (!contactToUpdate.trim().isEmpty())
				currInfoPatient.setContactInfo(contactToUpdate);

			if (!insuranceToUpdate.trim().isEmpty())
				currInfoPatient.setInsurance(insuranceToUpdate);

			if (currDoctorToUpdate != null)
				currInfoPatient.setCurrDoctor(currDoctorToUpdate);

			Parent root = FXMLLoader.load(getClass().getResource("NurseScene.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
	}// end of pdateInfoPatients

	public void seePatientInfoForNurses(ActionEvent event) {

		// private ChoiceBox<String> appointmentTime, currDoctors;
		// private String[] times = {"2:00-PM", "3:00-PM", "4:00-PM"};
		// private String[] doctorstoDisplay = {};

		// appointmentTime.getItems().addAll(times);

		for (int i = 0; i < doctorList.size(); i++) {
			currDoctors.getItems().add(doctorList.get(i).getUsername());
		}

		displayPName(currInfoPatient.getName());
		displayPDOB(currInfoPatient.getDateOfBirth());
		displayPPhone(currInfoPatient.getPhone());
		displayPPharmacy(currInfoPatient.getPharmacy());
		displayPContact(currInfoPatient.getContactInfo());
		displayPInsurance(currInfoPatient.getInsurance());
		displayEditCurrDoctorLabel(currInfoPatient.getCurrDoctor());

		infoPrinted = true;

	} // End of seePatientInfoForNurses

	/***********************************
	 * Doctor Main - Scene Controller
	 **********************************/
	public void goToAppointmentsWaiting(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("AppointmentsWaitingScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void goToAppointmentRecordsD(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("AppointmentRecordsScene2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void backToDoctorMain(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("DoctorScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/************************************************************
	 * Doctor Appointments Waiting - Scene Controller
	 **********************************************************/
	public void finishAppointment(ActionEvent event) throws IOException {

		listOfWaitingAppointments.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		String selectedAppointment = null;
		selectedAppointment = listOfWaitingAppointments.getSelectionModel().getSelectedItem();

		if (listOfWaitingAppointments.getItems().isEmpty()) {
			displayWaitingAppMessage("Click on 'Find Appointments' and select one");
		}

		else if (selectedAppointment == null) {

			// System.out.println("Nothing was selected");
			displayWaitingAppMessage("Please select an appointment");

		}

		else {

			String[] participantsStr = selectedAppointment.split(" ");

			for (int j = 0; j < waitingList.size(); j++) {
				// participantsStr = upcomingList.get(j).getParticipants().split(" ");
				if (waitingList.get(j).getDate().equals(participantsStr[2])
						&& waitingList.get(j).getTime().equals(participantsStr[3])
						&& waitingList.get(j).getParticipants().equals(participantsStr[0] + " " + participantsStr[1])) {
					// currAppointmentList.add(upcomingList.get(j));
					System.out.println("Waiting Appointment was found");
					currAppointment = waitingList.get(j);
					waitingList.remove(j);
					System.out.println(currAppointment.getDate());
					break;
				}
				// selectedAppointment = null;
			}

			Parent root = FXMLLoader.load(getClass().getResource("ExamsScene.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of finish Appointment

	public void recordExam(ActionEvent event) throws IOException {

		// Define textfield
		String exams = examText.getText();
		String prescriptions = prescriptionText.getText();
		String notes = notesText.getText();

		// Check in anny is empty, if not, then update vitals and get back to upcom
		// Appointments
		if (exams.trim().isEmpty() && prescriptions.trim().isEmpty() && notes.trim().isEmpty())// &&
																								// pressure.trim().isEmpty())
		{
			displayExamLabelMessage("All items are missing");
		}

		else if (exams.trim().isEmpty() || prescriptions.trim().isEmpty() || notes.trim().isEmpty())// &&
																									// pressure.trim().isEmpty())
		{
			displayExamLabelMessage("Some items are missing");
		}

		else {
			System.out.println("Notes were recorded properly");

			currAppointment.setExams(exams);
			currAppointment.setPrescriptions(prescriptions);
			currAppointment.setNotes(notes);

			recordList.add(currAppointment);
			currAppointment = null;

			Parent root = FXMLLoader.load(getClass().getResource("AppointmentsWaitingScene.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of Record Exam

	@FXML
	void findWaitingAppointments(ActionEvent event) {

		// String[] participantsStr = null;

		for (int j = 0; j < waitingList.size(); j++) {

			// Participants - Date - Time
			String appointment = waitingList.get(j).getParticipants() + " " + waitingList.get(j).getDate() + " "
					+ waitingList.get(j).getTime();
			listOfWaitingAppointments.getItems().add(appointment);

		}

	}

	public void backToWaitingApps(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("AppointmentsWaitingScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	/************************************************************
	 * Doctor Appointment Records - Scene Controller
	 **********************************************************/
	public void printRecordsN(ActionEvent event) throws IOException {

		listOfRecordsD.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selectedRecord = null;
		selectedRecord = listOfRecordsD.getSelectionModel().getSelectedItem();

		// String[] participantsStr = selectedAppointment.split(" ");

		if (listOfRecordsD.getItems().isEmpty()) {

			// display message
			System.out.println("List is empty");
			displayAppointmentMessage("Click on 'Find Records' and select one");
		}

		else if (selectedRecord == null) {

			// System.out.println("Nothing was selected");
			displayAppointmentMessage("Please select a Record");

		}

		else {

			// System.out.println(selectedAppointment);

			String[] participantsStr = selectedRecord.split(" ");

			for (int j = 0; j < recordList.size(); j++) {
				// participantsStr = upcomingList.get(j).getParticipants().split(" ");
				if (recordList.get(j).getDate().equals(participantsStr[2])
						&& recordList.get(j).getTime().equals(participantsStr[3])
						&& recordList.get(j).getParticipants().equals(participantsStr[0] + " " + participantsStr[1])) {
					// currAppointmentList.add(upcomingList.get(j));
					System.out.println("Record was found");
					currRecord = recordList.get(j);
					// recordList.remove(j);
					System.out.println(currRecord.getDate());
					break;
				}
				// selectedAppointment = null;
			}

			Parent root = FXMLLoader.load(getClass().getResource("RecordForDoctors.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of printRecords

	public void backToAppointmentRecordsD(ActionEvent event) throws IOException {

		currRecord = null;

		Parent root = FXMLLoader.load(getClass().getResource("AppointmentRecordsScene2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void findRecordsDoctor(ActionEvent event) {

		// String[] participantsStr = null;

		for (int j = 0; j < recordList.size(); j++) {

			// Participants - Date - Time
			String appointment = recordList.get(j).getParticipants() + " " + recordList.get(j).getDate() + " "
					+ recordList.get(j).getTime();
			listOfRecordsD.getItems().add(appointment);

		}
	}

	/***********************************
	 * Patient Main - Scene Controller
	 **********************************/
	public void goToPersonalUpcoming(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("PersonalUpcomingScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void goToPersonalRecord(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("PersonalRecordScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void editAppointment(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("EditAppointmentScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void editPatientInfoP(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("EditPatientInfoScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void backToAppointmentRecordsP(ActionEvent event) throws IOException {

		currRecord = null;

		Parent root = FXMLLoader.load(getClass().getResource("PersonalRecordScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void backToPatientMain(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("PatientScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/************************************************************
	 * Patient Upcoming Appointment - Scene Controller
	 **********************************************************/

	// goToUpAppointments
	@FXML
	void findUpAppointments(ActionEvent event) {

		System.out.println("Trying to find an appointment");

		// assign Selected appointment to participants string
		// String[] participantsStr = null;

		for (int j = 0; j < upcomingList.size(); j++) {

			String findApp = currPatientUser.getUsername() + " " + currPatientUser.getCurrDoctor();

			if (upcomingList.get(j).getParticipants().equals(findApp)) {
				// Participants - Date - Time
				String appointment = upcomingList.get(j).getParticipants() + " " + upcomingList.get(j).getDate() + " "
						+ upcomingList.get(j).getTime();
				listOfUpAppointments.getItems().add(appointment);

			} else {
				System.out.println("Appointment not found");
			}
		}
	} // End of find Upcoming Appointments

	public void goToPersonalAppointment(ActionEvent event) throws IOException {

		listOfUpAppointments.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selectedRecord = null;
		selectedRecord = listOfUpAppointments.getSelectionModel().getSelectedItem();

		// String[] participantsStr = selectedAppointment.split(" ");

		if (listOfUpAppointments.getItems().isEmpty()) {

			// display message
			// System.out.println("List is empty");
			displayAppointmentMessage("Click on 'Find Records' and select one");
		}

		else if (selectedRecord == null) {

			// System.out.println("Nothing was selected");
			displayAppointmentMessage("Please select a Record");

		}

		else {

			// System.out.println(selectedAppointment); currAppointment

			String[] participantsStr = selectedRecord.split(" ");

			for (int j = 0; j < upcomingList.size(); j++) {
				// participantsStr = upcomingList.get(j).getParticipants().split(" ");
				if (upcomingList.get(j).getDate().equals(participantsStr[2])
						&& upcomingList.get(j).getTime().equals(participantsStr[3]) && upcomingList.get(j)
								.getParticipants().equals(participantsStr[0] + " " + participantsStr[1])) {
					// currAppointmentList.add(upcomingList.get(j));
					System.out.println("Record was found");
					currAppointment = upcomingList.get(j);
					// recordList.remove(j);
					System.out.println(currAppointment.getDate());
					break;
				}
				// selectedAppointment = null;
			}

			Parent root = FXMLLoader.load(getClass().getResource("PatientUpAppointment.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of Go to Personal Appointment

	public void seePatientAppointment(ActionEvent event) {

		displayDateLabel(currAppointment.getDate());
		displayTimeLabel(currAppointment.getTime());
	}

	/************************************************************
	 * Patient Create Appointment - Scene Controller
	 **********************************************************/
	public void createAppointment(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("CreateAppointmentScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void pushAppointment(ActionEvent event) throws IOException {

		// appointmentTime.getItems().addAll(times);
		// String date = appointmentDate.get

		if (appointmentDate.getValue() == null) {

			displayCreateAppMessage("Please select Date ");

		}

		else if (appointmentTime.getValue() == null) {
			displayCreateAppMessage("Please select an available time");
		}

		else {

			LocalDate myDate = appointmentDate.getValue();

			String date = myDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
			// String time = the time on the screen
			String time = appointmentTime.getValue();
			String vitals = "";
			String exams = "";
			String notes = "";
			String prescriptions = "";
			String participants = (currPatientUser.getUsername() + " " + currPatientUser.getCurrDoctor());

			// else -> Add to list, Return to Scene, Print report
			upcomingList.add(new Appointment(date, time, vitals, exams, notes, prescriptions, participants));

			System.out.println("Appointment added successfully");

			// Load patient Main Scene
			Parent root = FXMLLoader.load(getClass().getResource("PatientScene.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	}// End of Push Appointment

	public void displayTimes(ActionEvent event) {

		ArrayList<String> times = new ArrayList<String>();
		ArrayList<String> takenTimes = new ArrayList<String>();
		ArrayList<String> availableTimes = new ArrayList<String>();

		// Add all the possible times
		times.add("10:00-AM");
		times.add("11:00-AM");
		times.add("12:00-PM");
		times.add("01:00-PM");
		times.add("02:00-PM");
		times.add("03:00-PM");
		times.add("04:00-PM");

		ObservableList<String> TimesList = FXCollections.observableArrayList(times);

		// private static ArrayList <String> times;
		// private static ArrayList <String> takenTimes;
		// private static ArrayList <String> availableTimes;

		// See in upcoming the available times from 10:00-AM t0 4:00-PM

		if (upcomingList.size() == 0)
			appointmentTime.getItems().addAll(TimesList);
		else {

			for (int i = 0; i < upcomingList.size(); i++) {

				for (int j = 0; j < times.size(); j++) {
					if (upcomingList.get(i).getTime().equals(times.get(j))) {
						takenTimes.add(upcomingList.get(i).getTime());
					} else {
						availableTimes.add(times.get(j));
					}
				}
			}

			ObservableList<String> AvTimesList = FXCollections.observableArrayList(availableTimes);
			appointmentTime.getItems().addAll(AvTimesList);

		}

		// appointmentTime.getItems().addAll(TimesList);

	}// End of displayTimes

	/************************************************************
	 * Patient Edit Appointment - Scene Controller
	 **********************************************************/
	@FXML
	void findEditAppointments(ActionEvent event) {

		System.out.println("Trying to find an appointment");

		// assign Selected appointment to participants string
		// String[] participantsStr = null;

		for (int j = 0; j < upcomingList.size(); j++) {

			String findApp = currPatientUser.getUsername() + " " + currPatientUser.getCurrDoctor();

			if (upcomingList.get(j).getParticipants().equals(findApp)) {
				// Participants - Date - Time
				String appointment = upcomingList.get(j).getParticipants() + " " + upcomingList.get(j).getDate() + " "
						+ upcomingList.get(j).getTime();
				listOfEditAppointments.getItems().add(appointment);

			} else {
				displayNoAppFount("No appointments were found");
				System.out.println("Appointment not found");
			}
		}
	} // End of find Upcoming Appointments

	public void goToEditAppointment(ActionEvent event) throws IOException {

		listOfEditAppointments.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selectedRecord = null;
		selectedRecord = listOfEditAppointments.getSelectionModel().getSelectedItem();

		// String[] participantsStr = selectedAppointment.split(" ");

		if (listOfEditAppointments.getItems().isEmpty()) {

			// display message
			// System.out.println("List is empty");
			displayAppointmentMessage("Click on 'Find Appointments' and select one");
		}

		else if (selectedRecord == null) {

			// System.out.println("Nothing was selected");
			displayAppointmentMessage("Please select an Appointment");

		}

		else {

			// System.out.println(selectedAppointment); currAppointment

			String[] participantsStr = selectedRecord.split(" ");

			for (int j = 0; j < upcomingList.size(); j++) {
				// participantsStr = upcomingList.get(j).getParticipants().split(" ");
				if (upcomingList.get(j).getDate().equals(participantsStr[2])
						&& upcomingList.get(j).getTime().equals(participantsStr[3]) && upcomingList.get(j)
								.getParticipants().equals(participantsStr[0] + " " + participantsStr[1])) {
					// currAppointmentList.add(upcomingList.get(j));
					System.out.println("Record was found");
					currAppointment = upcomingList.get(j);
					upcomingList.remove(j);
					System.out.println(currAppointment.getDate());
					break;
				}
				// selectedAppointment = null;
			}

			Parent root = FXMLLoader.load(getClass().getResource("FinishEditApp.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of Go to Edit Appointment

	public void pushEditedAppointment(ActionEvent event) throws IOException {

		// appointmentTime.getItems().addAll(times);
		// String date = appointmentDate.get

		if (appointmentDate.getValue() == null) {

			displayCreateAppMessage("Please select Date ");

		}

		else if (appointmentTime.getValue() == null) {
			displayCreateAppMessage("Please select an available time");
		}

		else {
			LocalDate myDate = appointmentDate.getValue();

			String date = myDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
			// String time = the time on the screen
			String time = appointmentTime.getValue();
			String vitals = "";
			String exams = "";
			String notes = "";
			String prescriptions = "";
			String participants = (currPatientUser.getUsername() + " " + currPatientUser.getCurrDoctor());

			// else -> Add to list, Return to Scene, Print report
			upcomingList.add(new Appointment(date, time, vitals, exams, notes, prescriptions, participants));

			System.out.println("Appointment added successfully");

			// Load patient Main Scene
			Parent root = FXMLLoader.load(getClass().getResource("PatientScene.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	}// End of Push Edited Appointment

	/************************************************************
	 * Patient Record - Scene Controller
	 **********************************************************/
	public void printRecordsP(ActionEvent event) throws IOException {

		listOfRecordsP.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selectedRecord = null;
		selectedRecord = listOfRecordsP.getSelectionModel().getSelectedItem();

		// String[] participantsStr = selectedAppointment.split(" ");

		if (listOfRecordsP.getItems().isEmpty()) {

			// display message
			System.out.println("List is empty");
			displayAppointmentMessage("Click on 'Find Records' and select one");
		}

		else if (selectedRecord == null) {

			// System.out.println("Nothing was selected");
			displayAppointmentMessage("Please select a Record");

		}

		else {

			// System.out.println(selectedAppointment);

			String[] participantsStr = selectedRecord.split(" ");

			for (int j = 0; j < recordList.size(); j++) {
				// participantsStr = upcomingList.get(j).getParticipants().split(" ");
				if (recordList.get(j).getDate().equals(participantsStr[2])
						&& recordList.get(j).getTime().equals(participantsStr[3])
						&& recordList.get(j).getParticipants().equals(participantsStr[0] + " " + participantsStr[1])) {
					// currAppointmentList.add(upcomingList.get(j));
					System.out.println("Record was found");
					currRecord = recordList.get(j);
					// recordList.remove(j);
					System.out.println(currRecord.getDate());
					break;
				}
				// selectedAppointment = null;
			}

			Parent root = FXMLLoader.load(getClass().getResource("RecordForPatients.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of printRecords

	@FXML
	public void findRecordsPatient(ActionEvent event) {

		for (int j = 0; j < recordList.size(); j++) {

			String findRecord = currPatientUser.getUsername() + " " + currPatientUser.getCurrDoctor();

			if (recordList.get(j).getParticipants().equals(findRecord)) {
				// Participants - Date - Time
				String appointment = recordList.get(j).getParticipants() + " " + recordList.get(j).getDate() + " "
						+ recordList.get(j).getTime();
				listOfRecordsP.getItems().add(appointment);

			} else {
				System.out.println("Appointment not found");
			}
		}
	}// End of findRecordsPatient()

	/************************************************************
	 * Patient Edit Personal Info - Scene Controller
	 **********************************************************/
	public void seePatientInfo(ActionEvent event) {

		displayPName(currPatientUser.getName());
		displayPDOB(currPatientUser.getDateOfBirth());
		displayPPhone(currPatientUser.getPhone());
		displayPPharmacy(currPatientUser.getPharmacy());
		displayPContact(currPatientUser.getContactInfo());
		displayPInsurance(currPatientUser.getInsurance());

		infoPrinted = true;

	}

	public void updateInfoPatients(ActionEvent event) throws IOException {

		if (infoPrinted == false) {
			displayEditInfoLabel("See current info first");
		} else {

			// Define all textfields
			String phonetoUptade = newPhone.getText();
			String pharmacytoUptade = newPharmacy.getText();
			String contactToUpdate = newContact.getText();
			String insuranceToUpdate = newInsurance.getText();

			// Check if any spots are empty
			// temp.trim().isEmpty()
			if (!phonetoUptade.trim().isEmpty())
				currPatientUser.setPhone(phonetoUptade);

			if (!pharmacytoUptade.trim().isEmpty())
				currPatientUser.setPharmacy(pharmacytoUptade);

			if (!contactToUpdate.trim().isEmpty())
				currPatientUser.setContactInfo(contactToUpdate);

			if (!insuranceToUpdate.trim().isEmpty())
				currPatientUser.setInsurance(insuranceToUpdate);

			Parent root = FXMLLoader.load(getClass().getResource("PatientScene.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
	}// end of pdateInfoPatients

	/********************************
	 * Sign Up - Scene Controller
	 ******************************/
	public void signUp(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("signUpScene.fxml"));
		root = loader.load();

		// NurseSceneController nurseSceneController = loader.getController();
		// nurseSceneController.displayUsername(usernameT);

		// Parent root = FXMLLoader.load(getClass().getResource("NurseScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}// End of Sign Up

	public void checkAvailableUsername(ActionEvent event) throws IOException {

		isUsernameAvailable = false;

		String lookUsername = usernameTextfield.getText();

		// Check on Patient List
		for (int i = 0; i < patientList.size(); i++) {
			if (lookUsername.equals(patientList.get(i).getUsername())) {
				isUsernameAvailable = false;
				break;
			} else
				isUsernameAvailable = true;
		}

		// Check on Nurse List
		for (int i = 0; i < nurseList.size(); i++) {
			if (lookUsername.equals(nurseList.get(i).getUsername()))
				isUsernameAvailable = false;
		}

		// Check on Doctor List
		for (int i = 0; i < doctorList.size(); i++) {
			if (lookUsername.equals(doctorList.get(i).getUsername()))
				isUsernameAvailable = false;
		}

		if (lookUsername.trim().isEmpty()) {
			displayAvailableUsername("Please enter a username");
		} else if (isUsernameAvailable == false) {
			displayAvailableUsername("Username is not Available");
		} else {
			displayAvailableUsername("Username is available :)");
		}

	}// End of checkAvailableUsername

	public void createAccount(ActionEvent event) throws IOException {

		// boolean isUsernameAvailable = true;

		String createUsername = usernameTextfield.getText();
		String createPassword = passwordTextfield.getText();

		if (createUsername.trim().isEmpty()) {
			displaySignUpMessage("Please complete steps above");
		} else if (isUsernameAvailable == false) {
			displaySignUpMessage("Cannot create a username that already exist");
		} else {

			if (createPassword.trim().isEmpty()) {
				displaySignUpMessage("Please Enter a Password");
			} else {
				System.out.println("Creating account");

				currsignUpUsername = createUsername;// Add to global currsignUpUsername
				currsignUpPassword = createPassword;// Add to global currsignUpPassword

				Parent root = FXMLLoader.load(getClass().getResource("signUpTypeScene.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}

		}

	}// End of createAccount

	public void pushAccountPatient(ActionEvent event) throws IOException {

		// boolean incomplete = false;

		// private Label profileName, profileDOB, profilePhone;
		// Create lables with the input
		String name = profileName.getText();
		String dob = profileDOB.getText();
		String phone = profilePhone.getText();

		// Check if empty
		if (name.trim().isEmpty() && dob.trim().isEmpty() && phone.trim().isEmpty())// && pressure.trim().isEmpty())
			displayEditInfoLabel("All fields are missing");

		else if (name.trim().isEmpty() || dob.trim().isEmpty() || phone.trim().isEmpty())
			displayEditInfoLabel("Some fields are missing");

		// else -> create profile
		else {

			patientList.add(new Patient(currsignUpUsername, currsignUpPassword, name, dob, "", "", phone, "", "", ""));

			System.out.println("New account for Patient created succesfully");

			Parent root = FXMLLoader.load(getClass().getResource("LogInScene2.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
	}// end of pushAccountPatient

	public void pushAccountNurse(ActionEvent event) throws IOException {

		// boolean incomplete = false;

		// private Label profileName, profileDOB, profilePhone;
		// Create lables with the input
		String name = profileName.getText();
		String doctor1 = profileDoctor1.getValue();
		String doctor2 = profileDoctor2.getValue();

		// Check if empty
		if (name.trim().isEmpty() && doctor1 == null && doctor2 == null)// && pressure.trim().isEmpty())
			displayEditInfoLabel("All fields are missing");

		else if (name.trim().isEmpty() || doctor1 == null || doctor2 == null)
			displayEditInfoLabel("Some fields are missing");

		else if (doctor1.equals(doctor2))
			displayEditInfoLabel("Doctors are duplicated - Please correct");
		// else -> create profile
		else {

			String doctorList = doctor1 + " " + doctor2;

			nurseList.add(new Nurse(currsignUpUsername, currsignUpPassword, name, doctorList));

			System.out.println("New account for Nurse created succesfully");

			Parent root = FXMLLoader.load(getClass().getResource("LogInScene2.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
	}// end of pushAccountPatient

	public void pushAccountDoctor(ActionEvent event) throws IOException {

		// boolean incomplete = false;

		// private Label profileName, profileDOB, profilePhone;
		// Create lables with the input
		String name = profileName.getText();
		String nurse = profileNurses.getValue();
		String patient1 = profilePatients1.getValue();
		String patient2 = profilePatients2.getValue();

		// String doctor1 = profileDoctor1.getValue();
		// String doctor2 = profileDoctor2.getValue();

		// Check if empty
		if (name.trim().isEmpty() && nurse == null && patient1 == null && patient2 == null)// &&
																							// pressure.trim().isEmpty())
			displayEditInfoLabel("All fields are missing");

		else if (name.trim().isEmpty() || nurse == null || patient1 == null || patient2 == null)
			displayEditInfoLabel("Some fields are missing");

		else if (patient1.equals(patient2))
			displayEditInfoLabel("Doctors are duplicated - Please correct");
		// else -> create profile
		else {

			String patients = patient1 + " " + patient2;

			doctorList.add(new Doctor(currsignUpUsername, currsignUpPassword, name, nurse, patients));

			System.out.println("New account for Doctor created succesfully");

			Parent root = FXMLLoader.load(getClass().getResource("LogInScene2.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
	}// end of pushAccountDoctor

	public void goToStep2Nurse(ActionEvent event) throws IOException {

		String code = "1234";

		String passCode = permissionCode.getText();

		if (passCode.trim().isEmpty()) {

			displaySignUpMessage("Please Enter a Passcode");

		}

		else if (!passCode.equals(code)) {

			displaySignUpMessage("The Passcode you entered is wrong");

		}

		else {

			Parent root = FXMLLoader.load(getClass().getResource("SignUpSet2Nurse.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of goToStep2Nurse

	public void goToStep2Doctor(ActionEvent event) throws IOException {

		String code = "1234";

		String passCode = permissionCode.getText();

		if (passCode.trim().isEmpty()) {

			displaySignUpMessage("Please Enter a Passcode");

		}

		else if (!passCode.equals(code)) {

			displaySignUpMessage("The Passcode you entered is wrong");

		}

		else {

			Parent root = FXMLLoader.load(getClass().getResource("SignUpSet2Doctor.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}

	}// End of goToStep2Doctor

	public void goToStep2Patient(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("signUpSet2Patient.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}// End of goToStep2Patient

	public void displayDoctors(ActionEvent event) throws IOException {

		for (int i = 0; i < doctorList.size(); i++) {
			profileDoctor1.getItems().add(doctorList.get(i).getUsername());
		}

		for (int i = 0; i < doctorList.size(); i++) {
			profileDoctor2.getItems().add(doctorList.get(i).getUsername());
		}

	}

	public void displayNurses(ActionEvent event) throws IOException {

		for (int i = 0; i < nurseList.size(); i++) {
			profileNurses.getItems().add(nurseList.get(i).getUsername());
		}

	}

	public void displayPatients(ActionEvent event) throws IOException {

		for (int i = 0; i < patientList.size(); i++) {
			profilePatients1.getItems().add(patientList.get(i).getUsername());
		}

		for (int i = 0; i < patientList.size(); i++) {
			profilePatients2.getItems().add(patientList.get(i).getUsername());
		}

	}

	/*************************************
	 * Reset Password - Scene Controller
	 ************************************/
	public void resetPassword(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("ResetPasswordScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void findUsername(ActionEvent event) throws IOException {

		// boolean foundUsername = true;
		foundUsernameToReset = false;

		String lookUsername = usernameTextfield.getText();

		// Check on Patient List
		for (int i = 0; i < patientList.size(); i++) {
			if (lookUsername.equals(patientList.get(i).getUsername())) {
				foundUsernameToReset = true;
				break;
			}
		}

		// Check on Nurse List
		for (int i = 0; i < nurseList.size(); i++) {
			if (lookUsername.equals(nurseList.get(i).getUsername()))
				foundUsernameToReset = true;
			break;
		}

		// Check on Doctor List
		for (int i = 0; i < doctorList.size(); i++) {
			if (lookUsername.equals(doctorList.get(i).getUsername()))
				foundUsernameToReset = true;
			break;
		}

		if (lookUsername.trim().isEmpty()) {
			displayAvailableUsername("Please enter a username");
		} else if (foundUsernameToReset == false) {
			displayAvailableUsername("Username was not found");
		} else {
			displayAvailableUsername("Username was found, you can reset password now");
		}

	}// End of findUsername

	// foundUsernameToReset

	public void updatePassword(ActionEvent event) throws IOException {

		// boolean isUsernameAvailable = true;

		String currentUsername = usernameTextfield.getText();
		String newPassword = passwordTextfield.getText();

		if (currentUsername.trim().isEmpty()) {
			displaySignUpMessage("Please complete steps above");
		} else if (foundUsernameToReset == false) {
			displaySignUpMessage("Cannot Reset a Password for an acccount that hasn't been found");
		} else {

			if (newPassword.trim().isEmpty()) {
				displaySignUpMessage("Please Enter a Password");
			} else {
				System.out.println("Updating Password");

				// Look in Patient list
				for (int i = 0; i < patientList.size(); i++) {

					if (currentUsername.equals(patientList.get(i).getUsername())) {
						patientList.get(i).setPassword(newPassword);
						System.out.println("Password updated in Patient List successfully");
					}

				}

				// Look in Nurse list
				for (int i = 0; i < nurseList.size(); i++) {

					if (currentUsername.equals(nurseList.get(i).getUsername())) {
						nurseList.get(i).setPassword(newPassword);
						System.out.println("Password updated in Nurse List successfully");
					}

				}

				// Look in Doctor list
				for (int i = 0; i < doctorList.size(); i++) {

					if (currentUsername.equals(doctorList.get(i).getUsername())) {
						doctorList.get(i).setPassword(newPassword);
						System.out.println("Password updated in Doctor List successfully");
					}

				}

				Parent root = FXMLLoader.load(getClass().getResource("LogInScene2.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();

			}

		}

	}// End of createAccount

	/*
	 * @FXML private Button exitButton;
	 * 
	 * @FXML private AnchorPane LogInPane; Stage closeStage;
	 */

	public void closeProgramX(Stage closeStage) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to logout!");
		alert.setContentText("Do you want to 'Save' before Exiting?");

		if (alert.showAndWait().get() == ButtonType.OK) {

			// ****************************************************
			// write Linked Lists back to text files
			// ****************************************************

			// patient
			try {
				FileWriter myWriter = new FileWriter("src/application/Patients.txt");
				Patient patout;

				while (patientList.size() != 0) {
					patout = patientList.removeFirst();

					myWriter.write(patout.getUsername() + "\n");
					myWriter.write(patout.getPassword() + "\n");
					myWriter.write(patout.getName() + "\n");
					myWriter.write(patout.getDateOfBirth() + "\n");
					myWriter.write(patout.getCurrDoctor() + "\n");
					myWriter.write(patout.getMessage() + "\n");
					myWriter.write(patout.getPhone() + "\n");
					myWriter.write(patout.getPharmacy() + "\n");
					myWriter.write(patout.getContactInfo() + "\n");
					myWriter.write(patout.getInsurance() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// nurse
			try {
				FileWriter myWriter = new FileWriter("src/application/Nurses.txt");
				Nurse nurout;

				while (nurseList.size() != 0) {
					nurout = nurseList.removeFirst();

					myWriter.write(nurout.getUsername() + "\n");
					myWriter.write(nurout.getPassword() + "\n");
					myWriter.write(nurout.getName() + "\n");
					myWriter.write(nurout.getDoctorList() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// doctor
			try {
				FileWriter myWriter = new FileWriter("src/application/Doctors.txt");
				Doctor docout;

				while (doctorList.size() != 0) {
					docout = doctorList.removeFirst();

					myWriter.write(docout.getUsername() + "\n");
					myWriter.write(docout.getPassword() + "\n");
					myWriter.write(docout.getName() + "\n");
					myWriter.write(docout.getCurrNurse() + "\n");
					myWriter.write(docout.getPatientList() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// upcoming appointments
			try {
				FileWriter myWriter = new FileWriter("src/application/Appointments.txt");
				Appointment upout;

				while (upcomingList.size() != 0) {
					System.out.println("Size of list is " + upcomingList.size());
					upout = upcomingList.removeLast();

					myWriter.write(upout.getDate() + "\n");
					myWriter.write(upout.getTime() + "\n");
					myWriter.write(upout.getVitals() + "\n");
					myWriter.write(upout.getExams() + "\n");
					myWriter.write(upout.getNotes() + "\n");
					myWriter.write(upout.getPrescriptions() + "\n");
					myWriter.write(upout.getParticipants() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// recorded appointments
			try {
				FileWriter myWriter = new FileWriter("src/application/Records.txt");
				Appointment rdout;

				while (recordList.size() != 0) {
					rdout = recordList.removeFirst();

					myWriter.write(rdout.getDate() + "\n");
					myWriter.write(rdout.getTime() + "\n");
					myWriter.write(rdout.getVitals() + "\n");
					myWriter.write(rdout.getExams() + "\n");
					myWriter.write(rdout.getNotes() + "\n");
					myWriter.write(rdout.getPrescriptions() + "\n");
					myWriter.write(rdout.getParticipants() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// waiting appointments
			try {
				FileWriter myWriter = new FileWriter("src/application/Waiting.txt");
				Appointment waout;

				while (waitingList.size() != 0) {
					waout = waitingList.removeFirst();

					myWriter.write(waout.getDate() + "\n");
					myWriter.write(waout.getTime() + "\n");
					myWriter.write(waout.getVitals() + "\n");
					myWriter.write(waout.getExams() + "\n");
					myWriter.write(waout.getNotes() + "\n");
					myWriter.write(waout.getPrescriptions() + "\n");
					myWriter.write(waout.getParticipants() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// Closing Window
			System.out.println("You successfully closed the program");
			closeStage.close();
		}

	}// End of closeProgram

	public void closeProgram(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to logout!");
		alert.setContentText("Do you want to 'Save' before Exiting?");

		if (alert.showAndWait().get() == ButtonType.OK) {

			// ****************************************************
			// write Linked Lists back to text files
			// ****************************************************

			// patient
			try {
				FileWriter myWriter = new FileWriter("src/application/Patients.txt");
				Patient patout;

				while (patientList.size() != 0) {
					patout = patientList.removeFirst();

					myWriter.write(patout.getUsername() + "\n");
					myWriter.write(patout.getPassword() + "\n");
					myWriter.write(patout.getName() + "\n");
					myWriter.write(patout.getDateOfBirth() + "\n");
					myWriter.write(patout.getCurrDoctor() + "\n");
					myWriter.write(patout.getMessage() + "\n");
					myWriter.write(patout.getPhone() + "\n");
					myWriter.write(patout.getPharmacy() + "\n");
					myWriter.write(patout.getContactInfo() + "\n");
					myWriter.write(patout.getInsurance() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// nurse
			try {
				FileWriter myWriter = new FileWriter("src/application/Nurses.txt");
				Nurse nurout;

				while (nurseList.size() != 0) {
					nurout = nurseList.removeFirst();

					myWriter.write(nurout.getUsername() + "\n");
					myWriter.write(nurout.getPassword() + "\n");
					myWriter.write(nurout.getName() + "\n");
					myWriter.write(nurout.getDoctorList() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// doctor
			try {
				FileWriter myWriter = new FileWriter("src/application/Doctors.txt");
				Doctor docout;

				while (doctorList.size() != 0) {
					docout = doctorList.removeFirst();

					myWriter.write(docout.getUsername() + "\n");
					myWriter.write(docout.getPassword() + "\n");
					myWriter.write(docout.getName() + "\n");
					myWriter.write(docout.getCurrNurse() + "\n");
					myWriter.write(docout.getPatientList() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// upcoming appointments
			try {
				FileWriter myWriter = new FileWriter("src/application/Appointments.txt");
				Appointment upout;

				while (upcomingList.size() != 0) {
					System.out.println("Size of list is " + upcomingList.size());
					upout = upcomingList.removeLast();

					myWriter.write(upout.getDate() + "\n");
					myWriter.write(upout.getTime() + "\n");
					myWriter.write(upout.getVitals() + "\n");
					myWriter.write(upout.getExams() + "\n");
					myWriter.write(upout.getNotes() + "\n");
					myWriter.write(upout.getPrescriptions() + "\n");
					myWriter.write(upout.getParticipants() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// recorded appointments
			try {
				FileWriter myWriter = new FileWriter("src/application/Records.txt");
				Appointment rdout;

				while (recordList.size() != 0) {
					rdout = recordList.removeFirst();

					myWriter.write(rdout.getDate() + "\n");
					myWriter.write(rdout.getTime() + "\n");
					myWriter.write(rdout.getVitals() + "\n");
					myWriter.write(rdout.getExams() + "\n");
					myWriter.write(rdout.getNotes() + "\n");
					myWriter.write(rdout.getPrescriptions() + "\n");
					myWriter.write(rdout.getParticipants() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// waiting appointments
			try {
				FileWriter myWriter = new FileWriter("src/application/Waiting.txt");
				Appointment waout;

				while (waitingList.size() != 0) {
					waout = waitingList.removeFirst();

					myWriter.write(waout.getDate() + "\n");
					myWriter.write(waout.getTime() + "\n");
					myWriter.write(waout.getVitals() + "\n");
					myWriter.write(waout.getExams() + "\n");
					myWriter.write(waout.getNotes() + "\n");
					myWriter.write(waout.getPrescriptions() + "\n");
					myWriter.write(waout.getParticipants() + "\n");
				}
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			// Closing Window
			closeStage = (Stage) LogInPane.getScene().getWindow();
			System.out.println("You successfully closed the program");
			closeStage.close();
		}

	}// End of closeProgram

} // End of main class
