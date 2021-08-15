
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class C206_CaseStudy {
	//DB Information
		private static final String JDBC_URL = "jdbc:mysql://localhost/c206";
		private static final String DB_USERNAME = "root";
		private static final String DB_PASSWORD = "";
		
		public static void main(String[] args) {
			C206_CaseStudy c = new C206_CaseStudy();
			c.start();
		}
		public void start() {
			DBUtil.init(JDBC_URL, DB_USERNAME, DB_PASSWORD);
			int input = 0;
			while(input != 6) {
				menu();			
				input = Helper.readInt("Enter Option > ");
				
				if(input == 1) {				
					doBuyer();
				}
				else if(input == 2) {				
					doAppointment();
				}
				else if(input == 3) {
					doFeedback();
				}			
				else if(input == 4) {
					doRcCar();
				}
				else if(input == 5) {
					innerMenu("Radio Control Parts");
				}
								
					
			}	
			System.out.println("Thank you for using Radio Control Car System");
		}
		
		
		private void menu() {
			Helper.line(20, "-*");
			System.out.println("Radio Control Car System");
			Helper.line(20, "-*");
			System.out.println("1. Maintain Guest");
			System.out.println("2. Maintain Appointment");
			System.out.println("3. Maintain Feedback");
			System.out.println("4. Maintain Radio Control cars");
			System.out.println("5. Maintain Radio Control parts");		
			System.out.println("6. Exit");				
			
		}
		private void innerMenu(String title) {
			Helper.line(20, "-*");
			System.out.println(title);
			Helper.line(20, "-*");
			System.out.println("1. ADD "+title);
			System.out.println("2. Delete "+title);
			System.out.println("3. View "+title);		
			
			if(title.equals("Appointment") || title.equals("Buyer"))
				System.out.println("4. Update "+title);
			else
				System.out.println("4. Search "+title);
			System.out.println("5. Exit "+title);
		}
		
		//BUYER MENU --- RAPHAEL
		private void doBuyer() {
			int option = 0;		
			while(option != 5) {
				innerMenu("Buyer");
				option = Helper.readInt("Enter option > ");
				if(option == 1) {
					String name = Helper.readString("Enter Buyer name > ");
					String mobileNo = Helper.readString("Enter Mobile Number > ");
					String email = Helper.readString("Enter Email > ");
					if(validateGuestInput(mobileNo,name,email))
						addBuyer(new Buyer(name,mobileNo,email));
					else
						System.out.println("Invalid Fields");
				}
				else if(option == 2) {
					System.out.println(viewBuyer()+"\n");
					int id = Helper.readInt("Enter Buyer ID > ");
					deleteBuyer(id);
				}
				else if(option == 3) {
					System.out.println(viewBuyer());
				}			
			}
		}		
		public int addBuyer(Buyer b) {
			String insertSQL;					
			String name = b.getName();
			String mobileNo = b.getMobileNo();
			String email = b.getEmail();
			insertSQL = String.format("INSERT INTO buyers(Name,MobileNo,Email) VALUES('%s','%s','%s' )",name,mobileNo,email);
			int rowsAffected = DBUtil.execSQL(insertSQL);

			if (rowsAffected == 1)
				System.out.println("Buyer Added!");		
			else
				System.out.println("Buyer Registration Failed!");
			
			return rowsAffected;
		}
		public int deleteBuyer(int id) {
			String deleteSQL = String.format("DELETE FROM buyers WHERE Buyer_ID = %d", id);
			int rowsAffected = DBUtil.execSQL(deleteSQL);
			if(rowsAffected > 0)
				System.out.println("Buyer deleted!");
			else
				System.out.println("Buyer deletion failed");
			return rowsAffected;
		}
		public String viewBuyer() {
			String sql = "SELECT * FROM buyers";	
			ResultSet rs = DBUtil.getTable(sql);
			String output = String.format("%-4s%-10s%-15s%s\n", "ID","Name","MobileNo","Email");
			try {
				while(rs.next()) {
					int id = rs.getInt("Buyer_ID");
					String name = rs.getString("Name");
					String mobileNo = rs.getString("MobileNo");
					String email = rs.getString("Email");
					output += String.format("%-4s%-10s%-15s%s\n", id,name,mobileNo,email);		
				}
			}catch(SQLException err){
				System.out.println("View Failed!");
			}
			return output;
		}
		public boolean validateGuestInput(String mobileNo, String name, String email) {
			return mobileNo.matches("[8|9][0-9]{7}") && name.length() > 3 && email.matches("[\\w]+@[\\w]+.com"); 
		}
		
		//APPOINTMENT MENU --- SARAN
		private void doAppointment() {
			int option = 0;		
			while(option != 5) {
				innerMenu("Appointment");
				option = Helper.readInt("Enter option > ");
				if(option == 1) {
					System.out.println(viewBuyer());
					int buyer_id = Helper.readInt("Enter Buyer ID > ");
					String AppointmentDate = Helper.readString("Enter App Date (MM/dd/yyyy) > ");
					String staffName = Helper.readString("Enter Staff-Incharge > ");				
					if(checkDate(AppointmentDate) && checkName(staffName))
						addAppointment(new Appointment(buyer_id,AppointmentDate,staffName));
					else
						System.out.println("Invalid Fields");
				}
				else if(option == 2) {
					System.out.println(viewAppointments()+"\n");
					int id = Helper.readInt("Enter Appointment ID > ");
					deleteAppointment(id);
				}
				else if(option == 3) {
					System.out.println(viewAppointments());
				}		
				else if(option == 4) {
					System.out.println(viewAppointments());
					int id = Helper.readInt("Enter Appointment ID > ");
					int buyer_id = Helper.readInt("Enter Buyer ID > ");
					String AppointmentDate = Helper.readString("Enter App Date (MM/dd/yyyy) > ");
					String staffName = Helper.readString("Enter Staff-Incharge > ");		
					
					if(checkDate(AppointmentDate) && checkName(staffName))
						updateAppointment(new Appointment(buyer_id,id,AppointmentDate,staffName));
					else
						System.out.println("Invalid Fields");		
				}
			}
		}
		public int updateAppointment(Appointment a) {
			
			String insertSQL = String.format("UPDATE appointment SET Buyer_ID = %d,Date='%s',Staff_Name ='%s' WHERE Appointment_ID = %d",a.getBuyer_id(),a.getDate(),a.getStaff_Name(),a.getAppointment_id());
			int rowsAffected = DBUtil.execSQL(insertSQL);

			if (rowsAffected == 1)
				System.out.println("Appointment Updated!");		
			else
				System.out.println("Appointment Buyer ID dont exist or Appointment_ID dont exist!");
			
			return rowsAffected;
			
		}		
		public int addAppointment(Appointment a) {
			String insertSQL;					
			String staffName = a.getStaff_Name();
			String appDate = a.getDate();
			int id = a.getBuyer_id();
			insertSQL = String.format("INSERT INTO appointment(Buyer_ID,Date,Staff_Name) VALUES(%d,'%s','%s' )",id,appDate,staffName);
			int rowsAffected = DBUtil.execSQL(insertSQL);

			if (rowsAffected == 1)
				System.out.println("Appointment Added!");		
			else
				System.out.println("Appointment Registration Failed!");
			
			return rowsAffected;

		}
		public int deleteAppointment(int id) {
			String deleteSQL = String.format("DELETE FROM appointment WHERE Appointment_ID = %d", id);
			int rowsAffected = DBUtil.execSQL(deleteSQL);
			if(rowsAffected > 0)
				System.out.println("Appointment deleted!");
			else
				System.out.println("ID does not exist");
			return rowsAffected;		
		}
		public String viewAppointments() {
			String sql = "SELECT * FROM appointment";	
			ResultSet rs = DBUtil.getTable(sql);
			String output = String.format("%-5s%-15s%s\n", "ID","Date","Staff Name");
			try {
				while(rs.next()) {
					int id = rs.getInt("Appointment_ID");
					String appDate = rs.getString("Date");
					String staffName = rs.getString("Staff_Name");				
					output += String.format("%-5s%-15s%s\n", id,appDate,staffName);		
				}
			}catch(SQLException err){
				System.out.println("View Failed!");
			}
			return output;
		}
		public boolean checkName(String name) {
			return name.length() > 3;
		}
		public boolean checkDate(String appDate) {		
			try {
				Date d = new SimpleDateFormat("MM/dd/yyyy").parse(appDate);
				return d.after(new Date());
			} catch (ParseException e) {
				System.out.println("Enter in MM/dd/yyyy format!");
			} 
			return false;
			
		}
		
		//FEEDBACK MENU --- HUI JUN
		public void doFeedback() 
		{
			int option = 0;		
			while(option != 5) {
				innerMenu("Feedback");
				option = Helper.readInt("Enter option > ");
				
				if(option == 1)
				{
					System.out.println("Add feedback");
					int id = Helper.readInt("Enter ID > ");
					String feedback = Helper.readString("Enter Feedback > ");
					int status = Helper.readInt("Enter status > ");
					int buyerid = Helper.readInt("Enter Buyer_ID > ");
					addFeedback(new Feedback(id,feedback,status,buyerid));
					
				}
				else if(option == 2)
				{
					System.out.println("Delete feedback");
					System.out.println(viewFeedback()+"\n");
					int id = Helper.readInt("Enter Feedback ID > ");
					deleteFeedback(id);
				}
				else if(option == 3)
				{
					System.out.println("View feedback");
					System.out.println(viewFeedback());
				}
			}
		}
		
		public String viewFeedback()
		{
			String sql = "SELECT * FROM feedback";	
			ResultSet rs = DBUtil.getTable(sql);
			String output = String.format("%-5s %-45s %-10s %-5s\n", "ID","Feedback","Status","Buyer ID");
			try {
				while(rs.next()) {
					int id = rs.getInt("Feedback_ID");
					String feedback = rs.getString("Feedback");
					int status = rs.getInt("Status");	
					int buyid = rs.getInt("Buyer_ID");
					output += String.format("%-5d %-45s %-10d %-5d\n", id,feedback,status,buyid);		
				}
			}catch(SQLException err){
				System.out.println("View Failed!");
			}
			return output;
		}
		
		public int deleteFeedback(int id)
		{
			String deleteSQL = String.format("DELETE FROM appointment WHERE Feedback_ID = %d", id);
			int rowsAffected = DBUtil.execSQL(deleteSQL);
			if(rowsAffected > 0)
				System.out.println("Feedback deleted!");
			else
				System.out.println("ID does not exist");
			return rowsAffected;
		}
		public int addFeedback(Feedback a) {					
			int id = a.getFeedback_id();
			String fb = a.getFeedback();
			String status = a.getStatus();
			int buyid = a.getBuyer_id();
			
			String insertSQL = String.format("INSERT INTO Feedback(Feedback_ID,Feedback,Status,Buyer_ID) VALUES(%d,'%s','%s' )",id,fb,status,buyid);
			int rowsAffected = DBUtil.execSQL(insertSQL);

			if (rowsAffected == 1)
				System.out.println("Feedback Added!");		
			else
				System.out.println("Feedback Failed!");
			
			return rowsAffected;

		}
		//RADIO CAR MENU --- RUDHI
				private void doRcCar() {
					int option = 0;		
					while(option != 5) {
						innerMenu("Radio Control Cars");
						option = Helper.readInt("Enter option > ");
						if(option == 1) {
							int product_id = Helper.readInt("Enter Product ID > ");
							String productName = Helper.readString("Enter Product Name > ");
							//int availability = Helper.readInt("Enter Availability > ");
							String features = Helper.readString("Enter features > ");
							double productPrice = Helper.readDouble("Enter Price > ");
							if(validateRadioControlCarInput(productName,productPrice))
								addRcCar(new Radio_Car(product_id,productName,features,productPrice));
							else
								System.out.println("Invalid Fields");
						}
						else if(option == 2) {
							System.out.println(viewRcCar()+"\n");
							int id = Helper.readInt("Enter Product ID > ");
							deleteRcCar(id);
						}
						else if(option == 3) {
							System.out.println(viewRcCar());
						}			
					}
				}
				
				private boolean validateRadioControlCarInput(String name, double price) {
					return name.length() > 3 && price > 0.0;
				}
				private int addRcCar(Radio_Car c) {
					String insertSQL;					
					int product_id = c.getProduct_ID();
					String product_name = c.getName();
					int availability = 0;
					double product_price = c.getPrice();
					String features = c.getFeatures();
					insertSQL = String.format("INSERT INTO radio_cars(Product_ID,Name,Availability,Price,Features) VALUES(%d,'%s', %d , %f ,'%s')",
							product_id,product_name,availability,product_price,features);
					int rowsAffected = DBUtil.execSQL(insertSQL);

					if (rowsAffected == 1)
						System.out.println("Radio Control Car Added!");		
					else
						System.out.println("Adding of Radio Control Car Failed!");
					
					return rowsAffected;

				}
				private int deleteRcCar(int id) {
					String deleteSQL = String.format("DELETE FROM radio_cars WHERE Product_ID = %d", id);
					int rowsAffected = DBUtil.execSQL(deleteSQL);
					if(rowsAffected > 0)
						System.out.println("Radio Control Car deleted!");
					else
						System.out.println("ID does not exist");
					return rowsAffected;		
				}
				private String viewRcCar() {
					String sql = "SELECT * FROM radio_cars";	
					ResultSet rs = DBUtil.getTable(sql);
					String output = String.format("%-15s%-15s%-15s%-10s%-10s\n", "Product ID","Name","Availability","Price","Features");
					try {
						while(rs.next()) {
							int id = rs.getInt("Product_ID");
							String productName = rs.getString("Name");
							String Availability = rs.getString("Availability");
							double price = rs.getDouble("Price");
							String features = rs.getString("Features");
							output += String.format("%-15s%-15s%-15s%-10s%-10s\n", id,productName,Availability,price,features);		
						}
					}catch(SQLException err){
						System.out.println("View Failed!");
					}
					return output;
				}
}
