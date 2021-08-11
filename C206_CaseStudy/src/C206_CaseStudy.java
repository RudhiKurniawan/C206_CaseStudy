import java.util.ArrayList;


public class C206_CaseStudy {

	ArrayList<Feedback> fbList = new ArrayList<Feedback>();
	
	public static void main(String[] args) {


		C206_CaseStudy mainhere = new C206_CaseStudy();
		mainhere.start();
		
	}
	
	private void start()
	{
		int option = -1;

		while (option != 6) {

			menu();
			option = Helper.readInt("Enter Choice > ");

			if (option == 1) {
				//addCustomer();
			} else if (option == 2) {
				//showAllCustomers();
			} else if (option == 3)
			{
				
			}
			else if (option == 4)
			{
				
			}
			else if(option == 5)
			{
				feedback();
			}
			else if (option == 6) {
				System.out.println("Thank you for using our service!");
			}
		}
	}
	
	private void menu() {
		
		Helper.line(60, "=");
		System.out.println("WELCOME TO RADIO CONTROL CAR PORTAL");
		Helper.line(60, "=");
		System.out.println("1. Visitor Registration");
		System.out.println("2. Radio Control Car Listing");
		System.out.println("3. Radio Control Car Part Listing");
		System.out.println("4. Appointments");
		System.out.println("5. Feedback");
		System.out.println("6. Quit");
	}
	
	private void feedback()
	{
		int option = -1;

		while (option != 5) {

			feedbackmenu();
			option = Helper.readInt("Enter Choice > ");

			if (option == 1) {
				viewAllFeedback();
			} else if (option == 2) {
				//showAllCustomers();
			} else if (option == 3)
			{
				
			}
			else if (option == 4)
			{
				
			}
			else if(option == 5)
			{
	
			}
		}
		
	}
	
	private void feedbackmenu()
	{
		Helper.line(60, "=");
		System.out.println("FEEDBACK MENU");
		Helper.line(60, "=");
		System.out.println("1. View All Feedback");
		System.out.println("2. Add Feedback");
		System.out.println("3. Delete Feedback");
		System.out.println("4. Update Feedback");
		System.out.println("5. Exit");
	}
	
	private void viewAllFeedback()
	{
		Helper.line(60, "=");
		System.out.println("SHOWING ALL FEEDBACK");
		Helper.line(60, "=");
		String output = String.format("%-10s %-40s %-15s %-10s\n", "ID","Feedback","Status","Buyer ID");

		for (Feedback fb : fbList)
		{
			output += String.format("%-10d %-40s %-15d -10d\n", fb.getFeedback_id(), fb.getFeedback(),fb.getStatus(),fb.getBuyer_id());
		}
		System.out.println(output);
	}
	private void addFeedback()
	{
		Helper.line(60, "=");
		System.out.println("ADDING FEEDBACK");
		Helper.line(60, "=");
	}
}
