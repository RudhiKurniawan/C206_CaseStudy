
public class Appointment {
	private int appointment_id;
	private String date;
	private String staff_Name;
	private int buyer_id;
	public Appointment(int buyer_id,int appointment_id, String date,String staff_Name) {		
		this.buyer_id = buyer_id;
		this.appointment_id = appointment_id;
		this.date = date;
		this.staff_Name = staff_Name;
	}
	public Appointment(int buyer_id, String date,String staff_Name) {		
		this.buyer_id = buyer_id;
		this.date = date;
		this.staff_Name = staff_Name;
	}
	public int getAppointment_id() {
		return appointment_id;
	}	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStaff_Name() {
		return staff_Name;
	}
	public void setStaff_Name(String staff_Name) {
		this.staff_Name = staff_Name;
	}
	public int getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}
	
	
}
