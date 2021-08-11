import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class C206_CaseStudy {

	public static void main(String[] args) {
		//BUYER MENU --- RAPHAEL
		  private void doBuyer() {
		    int option = 0;    
		    while(option != 4) {
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
		  
		  private int addBuyer(Buyer b) {
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
		  private int deleteBuyer(int id) {
		    String deleteSQL = String.format("DELETE FROM buyers WHERE Buyer_ID = %d", id);
		    int rowsAffected = DBUtil.execSQL(deleteSQL);
		    if(rowsAffected > 0)
		      System.out.println("Buyer deleted!");
		    else
		      System.out.println("Buyer deletion failed");
		    return rowsAffected;
		  }
		  private String viewBuyer() {
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
		  private boolean validateGuestInput(String mobileNo, String name, String email) {
		    return mobileNo.matches("[8|9][0-9]{7}") && name.length() > 3 && email.matches("[\\w]+@[\\w]+.com"); 
		  }
		
	}

}
