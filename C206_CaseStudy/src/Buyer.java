
public class Buyer {
	private int buyer_ID;
	private String name;
	private String mobileNo;
	private String email;
	private Feedback feedback;
	private Appointment appointment;
	public Buyer(int buyer_ID, String name, String mobileNo, String email) {		
		this.buyer_ID = buyer_ID;
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
	}
	public Buyer(String name, String mobileNo, String email) {			
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
	}
	
	public int getBuyer_ID() {
		return buyer_ID;
	}
	public void setBuyer_ID(int buyer_ID) {
		this.buyer_ID = buyer_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
	
	
	
}
