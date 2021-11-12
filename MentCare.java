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