import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C206_CaseStudyTest {
	private static final C206_CaseStudy c = new C206_CaseStudy();
	private static final String JDBC_URL = "jdbc:mysql://localhost/c206";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";
	private static final int BUYER_ID = 1;
	private static final int APPOINTMENT_ID = 1;	
	
	@Before 
	public void setUp() throws Exception { 
		DBUtil.init(JDBC_URL, DB_USERNAME, DB_PASSWORD);
		//Add buyer with id 999 into table.
		String insertSQL = String.format("INSERT INTO buyers(Buyer_ID,Name,MobileNo,Email) VALUES(%d,'%s','%s','%s' )",BUYER_ID,"TEST","TEST","TEST");
		DBUtil.execSQL(insertSQL);
		
		//Add Appointment with buyer_id 999
		insertSQL = String.format("INSERT INTO appointment(Appointment_ID,Buyer_ID,Date,Staff_Name) VALUES(%d,%d,'%s','%s' )",APPOINTMENT_ID,BUYER_ID,"8/14/2029","JEFFREY");
		int rowsAffected = DBUtil.execSQL(insertSQL);
		
	}

	@After
	public void tearDown() throws Exception {
		//RESET TABLES TO 0 RECORDS
		DBUtil.execSQL("DELETE FROM appointment");						
		DBUtil.execSQL("DELETE FROM buyers");
		System.out.println("TEAR DOWN");
		
		//RESET AUTO INCREMENT COUNT
		DBUtil.execSQL("ALTER TABLE appointment AUTO_INCREMENT = 1");
		DBUtil.execSQL("ALTER TABLE buyers AUTO_INCREMENT = 1");
	}

	@Test 
	public void c206_test() {
		//fail("Not yet implemented"); 
		assertTrue("C206_CaseStudy_SampleTest ",true);
	}
	// Raphael /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void addBuyer() {
		Buyer b = new Buyer(2,"RAPHAL","92782826", "RAPHAEL@GMAIL.COM");
		assertEquals("Check if new buyer can be inserted into Database",1,c.addBuyer(b));		
		assertEquals("Check if same buyer can be inserted into Database",0,c.addBuyer(b));				
	}
	
	@Test
	public void deleteBuyer() {		
		assertEquals("Check if can delete existing buyer",1,c.deleteAppointment(1));		
		assertEquals("Check if can delete non-existing buyer",0,c.deleteAppointment(1));
	}
	
	@Test
	public void viewBuyer() {
		String output = String.format("%-4s%-10s%-15s%s\n", "ID","Name","MobileNo","Email");
		output += String.format("%-4s%-10s%-15s%s\n", 1,"TEST","TEST","TEST");	
		
		assertEquals("Check that all appointment can be displayed",c.viewBuyer(),output);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// SARAN ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test //Saran
	public void addAppointment() {
		String date = "9/14/2021";
		String staffName = "Jerry";
		Appointment failApp = new Appointment(9999, date, staffName);
		int addResult = c.addAppointment(failApp);
		assertEquals("Check if Appointment is not added into Database when given non existing user id",addResult, 0);
		
		Appointment passApp = new Appointment(BUYER_ID, date, staffName);
		addResult = c.addAppointment(passApp);
		assertEquals("Check if Appointment is added into Database when given existing user id",addResult, 1);				
	}
	
	@Test //Saran
	public void deleteAppointment() {
		int deleteResult = c.deleteAppointment(9999);
		assertEquals("Check if can delete non-existent buyer appointment",deleteResult, 0);
		
		deleteResult = c.deleteAppointment(APPOINTMENT_ID);
		assertEquals("Check if can delete existing buyer appointment ",deleteResult, 1);
		
		deleteResult = c.deleteAppointment(APPOINTMENT_ID);
		assertEquals("Check if can delete non-existent buyer appointment ",deleteResult, 0);				
	}
	
	@Test //Saran
	public void viewAppointment() {					
		String output = String.format("%-5s%-15s%s\n", "ID","Date","Staff Name");
		output += String.format("%-5s%-15s%s\n", APPOINTMENT_ID,"8/14/2029","JEFFREY");
		
		assertEquals("Test that all appointment can be displayed",c.viewAppointments(),output);
		
	}
	@Test
	public void updateAppointment() {		
		Appointment updatedAppointment = new Appointment(BUYER_ID,APPOINTMENT_ID,"10/14/2029","TESTER");
		assertEquals("Test that appointment with valid input can be updated",c.updateAppointment(updatedAppointment),1);
		
		updatedAppointment = new Appointment(BUYER_ID,99999,"10/14/2029","TESTER");
		assertEquals("Test that appointment that does not exist cannot be updated",c.updateAppointment(updatedAppointment),0);
		
		updatedAppointment = new Appointment(99999,APPOINTMENT_ID,"10/14/2029","TESTER");
		assertEquals("Test that Buyer that does not exist cannot be updated",c.updateAppointment(updatedAppointment),0);
		
	}
	@Test //Saran
	public void testDate(){		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		LocalDateTime today = LocalDateTime.now();  
		
		assertEquals("Test that method returns false when given not a date",c.checkDate("00000"),false);
		assertEquals("Test that method returns false when given date older than today",c.checkDate("8/13/2021"),false);
		assertEquals("Test that method returns true when given date more than today",c.checkDate(today.toString()),false);
		assertEquals("Test that method returns true when given date more than today",c.checkDate("8/14/2099"),true);
	}
	@Test //Saran
	public void testName() {
		assertEquals("Test that return false when empty",c.checkName(""), false);
		assertEquals("Test that return false when name length less than 3",c.checkName("Red"), false);
		assertEquals("Test that return false when name valid",c.checkName("Saran"), true);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test //Hui Jun
	public void viewFeedback()
	{
		String output = String.format("%-4s %-10s %-15s %-10d\n", "Feedback_ID","Feedback","Status","Buyer_ID");
		output += String.format("%-4d %-10s %-15s %-10d\n", 1,"Feedback","Status",1);	
		
		assertEquals("Check that all appointment can be displayed",c.viewFeedback(),output);
	}
	
	public void addFeedback()
	{
		Feedback newFeedback = new Feedback(10,"ILOVERADIOCAR",0,13);
		assertEquals("Check if new feedback can be inserted into Database",1,c.addFeedback(newFeedback));		
		assertEquals("Check if same feedback cannot be inserted into Database",0,c.addFeedback(newFeedback));	
	}
	
	public void deleteFeedback()
	{
		int deleteFeedback = c.deleteFeedback(99);
		assertEquals("Check if can delete feedback that do not exist",deleteFeedback, 0);
		
		deleteFeedback = c.deleteFeedback(99);
		assertEquals("Check if can delete existing feedback ",deleteFeedback, 1);
		
		deleteFeedback = c.deleteFeedback(99);
		assertEquals("Check if can delete non-existent feedback ",deleteFeedback, 0);
	}
	
}
