package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class MentCare {

	public static void main(String[] args) {
		
	    
	    

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		//init Linked Lists
		
		LinkedList<Patient> patientList = new LinkedList<Patient>();
		LinkedList<Nurse> nurseList = new LinkedList<Nurse>();
		LinkedList<Doctor> doctorList = new LinkedList<Doctor>();
		
		
		LinkedList<Appointment> upcomingList = new LinkedList<Appointment>();
		LinkedList<Appointment> recordList = new LinkedList<Appointment>();
		
		// ****************************************************
		//read from text files to Linked Lists
		// ****************************************************
		
		//patients	
		try {
			File myObj = new File("src/Patients.txt");
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) {
		    	patientList.add(new Patient(myReader.nextLine(),
					    					myReader.nextLine(),
					    					myReader.nextLine(),
					    					myReader.nextLine(),
					    					myReader.nextLine(),
					    					myReader.nextLine(),
					    					myReader.nextLine(),
					    					myReader.nextLine(),
					    					myReader.nextLine(),
					    					myReader.nextLine()));
		    }
		    myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		//nurses	
		try {
			File myObj = new File("src/Nurses.txt");
			   Scanner myReader = new Scanner(myObj);
			   while (myReader.hasNextLine()) {
				   nurseList.add(new Nurse(myReader.nextLine(),
			    						   myReader.nextLine(),
			    						   myReader.nextLine(),
			    						   myReader.nextLine()));
			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		//doctors
		try {
			File myObj = new File("src/Doctors.txt");
			   Scanner myReader = new Scanner(myObj);
			   while (myReader.hasNextLine()) {
				   doctorList.add(new Doctor(myReader.nextLine(),
			    						     myReader.nextLine(),
			    						     myReader.nextLine(),
			    						     myReader.nextLine(),
			    						     myReader.nextLine()));
			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		//upcoming appointments
		try {
			File myObj = new File("src/Appointments.txt");
			   Scanner myReader = new Scanner(myObj);
			   while (myReader.hasNextLine()) {
				   upcomingList.add(new Appointment(myReader.nextLine(),
			    						     		myReader.nextLine(),
			    						     		myReader.nextLine(),
			    						     		myReader.nextLine(),
			    						     		myReader.nextLine(),
			    						     		myReader.nextLine(),
			    						     		myReader.nextLine()));
			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		//recorded appointments
		try {
			File myObj = new File("src/Records.txt");
			   Scanner myReader = new Scanner(myObj);
			   while (myReader.hasNextLine()) {
				   recordList.add(new Appointment(myReader.nextLine(),
			    						     		myReader.nextLine(),
			    						     		myReader.nextLine(),
			    						     		myReader.nextLine(),
			    						     		myReader.nextLine(),
			    						     		myReader.nextLine(),
			    						     		myReader.nextLine()));
			}
			myReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		// ****************************************************
		//start main loop for program, meaning multiple users can login/logout in series
		// ****************************************************
		
		//initialized variables
		LinkedList<Appointment> readyList = new LinkedList<Appointment>();
		LinkedList<Appointment> currAppointmentList = new LinkedList<Appointment>();
		
		Appointment currAppointment = null;
		Patient currInfoPatient = null;
		
		Patient currPatientUser = null;
		Nurse currNurseUser = null;
		Doctor currDoctorUser = null;
		
		String[] participantsStr = null;
		String loginUsername;
		String loginPassword;
		
		
		//for time being every "" is a value that needs to be taken by the scene gui's
		
		//create an account
		//will need to check inputs before creation
		/*patientList.add(new Patient("",
									  "",
									  "",
									  "",
									  "",
									  "",
									  "",
									  "",
									  "",
									  ""));*/
		
		//reset password
		/*for(int i=0;i<patientList.size();i++) {
			if(loginUsername == patientList.get(i).getUsername()) {
				patientList.get(i).resetPassword("","");
			}
		}
		for(int i=0;i<nurseList.size();i++) {
			if(loginUsername == nurseList.get(i).getUsername()) {
				nurseList.get(i).resetPassword("","");
			}
		}
		for(int i=0;i<doctorList.size();i++) {
			if(loginUsername == doctorList.get(i).getUsername()) {
				doctorList.get(i).resetPassword("","");
			}
		}*/
		
		//LOGINS
		
		

		
		
		loginUsername = "leetman441";
		loginPassword = "klang334";

		//PATIENT LOGIN
		for(int i=0;i<patientList.size();i++) {
			if(loginUsername.equals(patientList.get(i).getUsername())) {
				if(loginPassword.equals(patientList.get(i).getPassword())) {
					currPatientUser = patientList.get(i);
					break;	
				}
				else {
					//move to error scene or just restart login scene
				}
			}
		}
		
		
		
		
		//NURSE LOGIN
		for(int i=0;i<nurseList.size();i++) {
			if(loginUsername.equals(nurseList.get(i).getUsername())) {
				if(loginPassword.equals(nurseList.get(i).getPassword())) {
					currNurseUser = nurseList.get(i);
					break;
				}
				else {
					//move to error scene or just restart login scene
				}
			}
		}
		
		//DOCTOR LOGIN
		for(int i=0;i<doctorList.size();i++) {
			if(loginUsername.equals(doctorList.get(i).getUsername())) {
				if(loginPassword.equals(doctorList.get(i).getPassword())) {
					currDoctorUser = doctorList.get(i);
					break;
				}
				else {
					//move to error scene or just restart login scene
				}
			}
		}
		
		/************************************************
		 * Successfully copied/implemented up to here 
		 **********************************************/
		
		//PATIENT CASES
		if(currPatientUser != null) {
			//view upcoming appointments
			for(int j=0;j<upcomingList.size();j++) {
				participantsStr = upcomingList.get(j).getParticipants().split(" ");
				if(participantsStr[0] == currPatientUser.getUsername()) {
					currAppointmentList.add(upcomingList.get(j));
				}
			}
			//then display this currUserUpcoming to scene
			participantsStr = null;
			currAppointmentList.clear();
			
			//view past appointments
			for(int k=0;k<recordList.size();k++) {
				participantsStr = recordList.get(k).getParticipants().split(" ");
				if(participantsStr[0] == currPatientUser.getUsername()) {
					currAppointmentList.add(recordList.get(k));
				}
			}
			//then display this currUserRecord to scene
			participantsStr = null;
			currAppointmentList.clear();
			
			//create new appointment
			//will need to check inputs before creation
			upcomingList.add(1, new Appointment("",
												"",
												"",
												"",
												"",
												"",
												""));
			
			//cancel an appointment
			for(int m=0;m<upcomingList.size();m++) {
				participantsStr = upcomingList.get(m).getParticipants().split(" ");
				if(participantsStr[0] == currPatientUser.getUsername()) {
					if(upcomingList.get(m).getDate() == "" && upcomingList.get(m).getTime() == "") {
						upcomingList.remove(m);
					}
				}
			}
			participantsStr = null;
			
			//view patient info
			//just use getters and setters on currPatientUser
			//for messaging it would be a constant getting and setting of it.
			//DO NOT allow for getting or setting of password from here. Thats for the reset password.
			
			//END PATIENT CASES
			currPatientUser = null;
		}
		
		/****************
		 * Nurses
		 ****************/
		
		//NURSE CASES
		if (currNurseUser != null) {
			
			//view upcoming appointments
			for(int j=0;j<upcomingList.size();j++) {
				participantsStr = upcomingList.get(j).getParticipants().split(" ");
				if(currNurseUser.getDoctorList().contains(participantsStr[1])) {
					currAppointmentList.add(upcomingList.get(j));
				}
			}
			
			//then display this currUserUpcoming to scene
			participantsStr = null;
			currAppointmentList.clear();
			
			
			
			
			//view patient records
			for(int k=0;k<recordList.size();k++) {
				participantsStr = recordList.get(k).getParticipants().split(" ");
				if(currNurseUser.getDoctorList().contains(participantsStr[1])) {
					currAppointmentList.add(recordList.get(k));
				}
			}
			//then display this currUserRecord to scene
			participantsStr = null;
			currAppointmentList.clear();
			
			//edit upcoming appointment
			for(int m=0;m<upcomingList.size();m++) {
				participantsStr = upcomingList.get(m).getParticipants().split(" ");
				if(currNurseUser.getDoctorList().contains(participantsStr[1]) && "".contains(participantsStr[0])) {
					if(upcomingList.get(m).getDate() == "" && upcomingList.get(m).getTime() == "") {
						currAppointment = new Appointment("",
														 "",
														 upcomingList.get(m).getVitals(),
														 upcomingList.get(m).getExams(),
														 upcomingList.get(m).getNotes(),
														 upcomingList.get(m).getPrescriptions(), 
														 upcomingList.get(m).getParticipants());
						upcomingList.remove(m);
						upcomingList.add(currAppointment);
						break;
					}
				}
			}
			currAppointment = null;
			participantsStr = null;
			
			//ready appointment
			for(int n=0;n<upcomingList.size();n++) {
				participantsStr = upcomingList.get(n).getParticipants().split(" ");
				if(currNurseUser.getDoctorList().contains(participantsStr[1])) {
					currAppointment = upcomingList.remove(n);
				}
			}
			
			if(currAppointment != null) {
				currAppointment.setVitals("");
				readyList.addLast(currAppointment);
			}
			currAppointment = null;
			participantsStr = null;
			
			//view patient info
			for(int z=0;z<patientList.size();z++) {
				if(patientList.get(z).getUsername() == "") {
					currInfoPatient = patientList.get(z);
					break;
				}
			}
			
			if(currInfoPatient != null) {
				//use getters to view all info EXCEPT PASSWORD
				//use setter to append text to the MESSAGE variable to update chatroom, IE chat with patient
			}
			currInfoPatient = null;
			
			//END NURSE CASES
			currNurseUser = null;
		}
		
		//DOCTOR CASES
		if(currDoctorUser != null) {
			
			//start appointment
			for(int j=0;j<readyList.size();j++) {
				participantsStr = readyList.get(j).getParticipants().split(" ");
				if(currDoctorUser.getPatientList().contains(participantsStr[0])) {
					currAppointment = readyList.removeFirst();
					break;
				}
			}
			if(currAppointment != null) {
				currAppointment.setExams("");
				currAppointment.setNotes("");
				currAppointment.setPrescriptions("");
				recordList.push(currAppointment);
			}
			currAppointment = null;
			participantsStr = null;
			
			//view patient records
			for(int k=0;k<recordList.size();k++) {
				participantsStr = recordList.get(k).getParticipants().split(" ");
				if(currDoctorUser.getPatientList().contains(participantsStr[0])) {
					currAppointmentList.add(recordList.get(k));
				}
			}
			//then display this currUserRecord to scene
			participantsStr = null;
			currAppointmentList.clear();
			
			//view patient info
			for(int z=0;z<patientList.size();z++) {
				if(patientList.get(z).getUsername() == "") {
					currInfoPatient = patientList.get(z);
					break;
				}
			}
			
			if(currInfoPatient != null) {
				//use getters to view all info EXCEPT PASSWORD
				//use setter to append text to the MESSAGE variable to update chatroom, IE chat with patient
			}
			currInfoPatient = null;
			
			//END DOCTOR CASES
			currDoctorUser = null;
		}
		
		// ****************************************************
		//write Linked Lists back to text files
		// ****************************************************
		
		//patient
		try {
			FileWriter myWriter = new FileWriter("src/Patients.txt");
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
		} 
		catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		}
		
		//nurse
		try {
			FileWriter myWriter = new FileWriter("src/Nurses.txt");
			Nurse nurout;
			
			
			while (nurseList.size() != 0) {
				nurout = nurseList.removeFirst();
				
				myWriter.write(nurout.getUsername() + "\n");
				myWriter.write(nurout.getPassword() + "\n");
				myWriter.write(nurout.getName() + "\n");
				myWriter.write(nurout.getDoctorList() + "\n");
			}
			myWriter.close();
		} 
		catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		}
		
		//doctor
		try {
			FileWriter myWriter = new FileWriter("src/Doctors.txt");
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
		} 
		catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		}
		
		//upcoming appointments
		try {
			FileWriter myWriter = new FileWriter("src/Appointments.txt");
			Appointment upout;
			
			
			while (upcomingList.size() != 0) {
				upout = upcomingList.removeFirst();
				
				myWriter.write(upout.getDate() + "\n");
				myWriter.write(upout.getTime() + "\n");
				myWriter.write(upout.getVitals() + "\n");
				myWriter.write(upout.getExams() + "\n");
				myWriter.write(upout.getNotes() + "\n");
				myWriter.write(upout.getPrescriptions() + "\n");
				myWriter.write(upout.getParticipants() + "\n");
			}
			myWriter.close();
		} 
		catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		}
		
		//recorded appointments
		try {
			FileWriter myWriter = new FileWriter("src/Records.txt");
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
		} 
		catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		}
		
	}
		
}