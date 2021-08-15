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
	private static final int FEEDBACK_ID = 1;	
	private static final int PRODUCT_ID =1;
	
	@Before 
	public void setUp() throws Exception { 
		DBUtil.init(JDBC_URL, DB_USERNAME, DB_PASSWORD);
		//Add buyer with id 999 into table.
		String insertSQL = String.format("INSERT INTO buyers(Buyer_ID,Name,MobileNo,Email) VALUES(%d,'%s','%s','%s' )",BUYER_ID,"TEST","TEST","TEST");
		DBUtil.execSQL(insertSQL);
		
		//Add Appointment with buyer_id 999
		insertSQL = String.format("INSERT INTO appointment(Appointment_ID,Buyer_ID,Date,Staff_Name) VALUES(%d,%d,'%s','%s' )",APPOINTMENT_ID,BUYER_ID,"8/14/2029","JEFFREY");
		DBUtil.execSQL(insertSQL);
		
		//Add Feedback with buyer_id 1 and feedback id _1
		insertSQL = String.format("INSERT INTO Feedback(Feedback_ID,Feedback,Status,Buyer_ID) VALUES(%d,'%s',%d,%d )",FEEDBACK_ID,"TEST",0,BUYER_ID);
		DBUtil.execSQL(insertSQL);
		
		//Add Radio Car product id 1
		insertSQL = String.format("INSERT INTO radio_cars(Product_ID,Name,Availability,Price,Features) VALUES(%d,'%s',%d,%.2f,'%s' )",PRODUCT_ID,"TEST",0,0.0,"TEST");
		DBUtil.execSQL(insertSQL);
		System.out.println("SETUP");
		
		
		
	}

	@After
	public void tearDown() throws Exception {
		//RESET TABLES TO 0 RECORDS
		DBUtil.execSQL("DELETE FROM feedback");
		DBUtil.execSQL("DELETE FROM appointment");						
		DBUtil.execSQL("DELETE FROM buyers");	
		DBUtil.execSQL("DELETE FROM radio_cars");		
		
		//RESET AUTO INCREMENT COUNT
		DBUtil.execSQL("ALTER TABLE appointment AUTO_INCREMENT = 1");
		DBUtil.execSQL("ALTER TABLE buyers AUTO_INCREMENT = 1");
		DBUtil.execSQL("ALTER TABLE feedback AUTO_INCREMENT = 1");
		DBUtil.execSQL("ALTER TABLE radio_cars AUTO_INCREMENT = 1");
		
		System.out.println("TEAR DOWN");
	}

	@Test 
	public void c206_test() {
		//fail("Not yet implemented"); 
		assertTrue("C206_CaseStudy_SampleTest ",true);
	}
	
	// Raphael /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void addBuyer() {
		System.out.println("\nBuyer -Add");
		Helper.line(40, "-*");
		
		Buyer b = new Buyer(2,"RAPHAEL","92782826", "RAPHAEL@GMAIL.COM");
		assertEquals("Check if new buyer can be inserted into Database",1,c.addBuyer(b));		
		assertEquals("Check if same buyer can be inserted into Database",0,c.addBuyer(b));		
		
		String output = String.format("%-4s%-10s%-15s%s\n", "ID","Name","MobileNo","Email");		
		output += String.format("%-4s%-10s%-15s%s\n", 1,"TEST","TEST","TEST");	
		output += String.format("%-4s%-10s%-15s%s\n", 2,"RAPHAEL","92782826","RAPHAEL@GMAIL.COM");
		
		assertEquals("Check that BUYER can be displayed after adding",c.viewBuyer(),output);
		
		
	}
	@Test
	public void updateBuyer() {
		System.out.println("\nBuyer -Add");
		Helper.line(40, "-*");
		
		Buyer b = new Buyer(1,"TESTER","91234567","Tester@gmail.com");
		assertEquals("Test that buyer with valid input can be updated",c.updateBuyer(b),1);
		
		b = new Buyer(999,"TESTER","91234567","Tester@gmail.com");		
		assertNotNull("Check Buyer not Null", b);
		assertEquals("Test that appointment that does not exist cannot be updated",c.updateBuyer(b),0);
		
		

	}
	@Test
	public void deleteBuyer() {
		System.out.println("\nBuyer -Delete");
		Helper.line(40, "-*");
		
		DBUtil.execSQL("DELETE FROM feedback");
		DBUtil.execSQL("DELETE FROM appointment");
		
		System.out.println(c.viewBuyer());
		
		assertEquals("Check if can delete existing buyer",1,c.deleteBuyer(BUYER_ID));		
		assertEquals("Check if can delete non-existing buyer",0,c.deleteBuyer(BUYER_ID));
		
		String output = String.format("%-4s%-10s%-15s%s\n", "ID","Name","MobileNo","Email");		
		assertEquals("Check that output is empty",c.viewBuyer(),output);
		
	}
	
	@Test
	public void viewBuyer() {
		System.out.println("\nBuyer -View");
		Helper.line(40, "-*");
		
		String output = String.format("%-4s%-10s%-15s%s\n", "ID","Name","MobileNo","Email");
		output += String.format("%-4s%-10s%-15s%s\n", 1,"TEST","TEST","TEST");			
		assertNotNull("Variable contains values",output);
		assertEquals("Check that all BUYER can be displayed",c.viewBuyer(),output);		
		
		DBUtil.execSQL(String.format("INSERT INTO buyers(Buyer_ID,Name,MobileNo,Email) VALUES(%d,'%s','%s','%s' )",2,"TEST1","TEST1","TEST1"));		
		output += String.format("%-4s%-10s%-15s%s\n", 2,"TEST1","TEST1","TEST1");		
		
		assertEquals("Check that BUYER can be displayed after adding",c.viewBuyer(),output);
				
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// SARAN ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test //Saran
	public void addAppointment() {
		System.out.println("\nAppointment -Add");
		Helper.line(40, "-*");
		
		String date = "9/14/2021";
		String staffName = "Jerry";
		
		Appointment failApp = new Appointment(9999, date, staffName);
		int addResult = c.addAppointment(failApp);
		assertEquals("Check if Appointment is not added into Database when given non existing user id",addResult, 0);
		
		Appointment passApp = new Appointment(BUYER_ID, date, staffName);
		addResult = c.addAppointment(passApp);
		assertEquals("Check if Appointment is added into Database when given existing user id",addResult, 1);	
				
		String output = String.format("%-5s%-15s%s\n", "ID","Date","Staff Name");
		output += String.format("%-5s%-15s%s\n", 1,"8/14/2029","JEFFREY");
		output += String.format("%-5s%-15s%s\n", 3,date,staffName);
		
		System.out.println(c.viewAppointments());
		
		assertEquals("Test that there are no appointments",c.viewAppointments(),output);
		
		
	}
	
	@Test //Saran
	public void deleteAppointment() {
		System.out.println("\nAppointment -DELETE");
		Helper.line(40, "-*");
		
		
		int deleteResult = c.deleteAppointment(9999);
		assertEquals("Check if can delete non-existent buyer appointment",deleteResult, 0);
		
		deleteResult = c.deleteAppointment(APPOINTMENT_ID);
		assertEquals("Check if can delete existing buyer appointment ",deleteResult, 1);
		
		deleteResult = c.deleteAppointment(APPOINTMENT_ID);
		assertEquals("Check if can delete non-existent buyer appointment ",deleteResult, 0);				
	}
	
	@Test //Saran
	public void viewAppointment() {				
		System.out.println("\nAppointment -View");
		Helper.line(40, "-*");
		
		String output = String.format("%-5s%-15s%s\n", "ID","Date","Staff Name");
		output += String.format("%-5s%-15s%s\n", APPOINTMENT_ID,"8/14/2029","JEFFREY");
		
		assertEquals("Test that all appointment can be displayed",c.viewAppointments(),output);
		
		
		DBUtil.execSQL("DELETE FROM appointment");
		output = String.format("%-5s%-15s%s\n", "ID","Date","Staff Name");
		assertEquals("Check that does not display DELETED appointment",c.viewAppointments(),output);		
		
		DBUtil.execSQL(String.format("INSERT INTO appointment(Appointment_ID,Buyer_ID,Date,Staff_Name) VALUES(%d,%d,'%s','%s' )",APPOINTMENT_ID,BUYER_ID,"8/14/2029","JEFFREY"));	
		output = String.format("%-5s%-15s%s\n", "ID","Date","Staff Name");
		output += String.format("%-5s%-15s%s\n", APPOINTMENT_ID,"8/14/2029","JEFFREY");
		assertEquals("Check that display ADDED appointment",c.viewAppointments(),output);
		
		
	}
	@Test
	public void updateAppointment() {
		System.out.println("\nAppointment -Update");
		Helper.line(40, "-*");
		
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
	public void addFeedback()
	{
		System.out.println("\nFeedback -ADD");
		Helper.line(40, "-*");
		Feedback newFeedback = new Feedback("ILOVERADIOCAR",0,BUYER_ID);
		assertEquals("Check if new feedback can be inserted into Database",c.addFeedback(newFeedback),1);		
		assertEquals("Check if same feedback cannot be inserted into Database",c.addFeedback(newFeedback),0);	
	}
	
	@Test //Hui Jun
	public void viewFeedback()
	{
		System.out.println("\nFeedback - VIEW");
		Helper.line(40, "-*");
		String output = String.format("%-5s %-45s %-10s %-5s\n", "ID","Feedback","Status","Buyer ID");
		output += String.format("%-5d %-45s %-10d %-5d\n", FEEDBACK_ID,"TEST",0,BUYER_ID);			
		assertEquals("Check that all feedback can be displayed",c.viewFeedback(),output);
	}
	
	@Test //Hui Jun
	public void deleteFeedback()
	{
		System.out.println("\nFeedback - DELETE");
		Helper.line(40, "-*");
		int deleteFeedback = c.deleteFeedback(99);
		assertEquals("Check if can delete feedback that do not exist",deleteFeedback, 0);
		
		deleteFeedback = c.deleteFeedback(FEEDBACK_ID);
		assertEquals("Check if can delete existing feedback ",deleteFeedback, 1);
		
		deleteFeedback = c.deleteFeedback(deleteFeedback);
		assertEquals("Check if can delete feedback that was deleted ",deleteFeedback, 0);
	}
	
	@Test //Rudhi
	public void addRcCar()
	{		
		System.out.println("\nRadio Control Car -ADD");
		Helper.line(40, "-*");
		Radio_Car newRadioCar = new Radio_Car("TESTER2","TEST",0.0);		
		assertEquals("Check if new Radio Control Car can be inserted into Database",c.addRcCar(newRadioCar),1);		
		assertEquals("Check if same Radio Control Car cannot be inserted into Database",c.addRcCar(newRadioCar),0)	;	
	}
	
	@Test //Rudhi
	public void viewRcCar()
	{
		//insertSQL = String.format("INSERT INTO radio_cars(Product_ID,Name,Availability,Price,Features) VALUES(%d,'%s',%d,%.2f,'%s' )",PRODUCT_ID,"TEST",0,0.0,"TEST");
		System.out.println("\nRadio Control Car - VIEW");
		Helper.line(40, "-*");
		String output = String.format("%-15s%-15s%-15s%-10s%-10s\n", "Product ID","Name","Availability","Price","Features");
		output += String.format("%-15s%-15s%-15s%-10s%-10s\n", PRODUCT_ID,"TEST",0,0.0,"TEST");		
		System.out.println(c.viewRcCar());
		assertEquals("Check that all Radio Control Car can be displayed",c.viewRcCar(),output);
	}
	
	@Test //Rudhi
	public void deleteRcCar()
	{
		System.out.println("\nRadio Control Car - DELETE");
		Helper.line(40, "-*");
		
		int deleteRcCar = c.deleteRcCar(99);
		assertEquals("Check if can delete Radio Control Car that do not exist",deleteRcCar, 0);
		
		deleteRcCar = c.deleteRcCar(PRODUCT_ID);
		assertEquals("Check if can delete existing Radio Control Car ",deleteRcCar, 1);
		
		deleteRcCar = c.deleteRcCar(deleteRcCar);
		assertEquals("Check if can delete feedback that was deleted ",deleteRcCar, 0);
	}
	
}
