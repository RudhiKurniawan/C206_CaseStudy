
public class Feedback {
	private int feedback_id;
	private String feedback;
	private int status;
	private int buyer_id;
	public Feedback(int feedback_id, String feedback, int status, int buyer_id) {		
		this.feedback_id = feedback_id;
		this.feedback = feedback;
		this.status = status;
		this.buyer_id = buyer_id;
	}
	public Feedback(String feedback, int status, int buyer_id) {				
		this.feedback = feedback;
		this.status = status;
		this.buyer_id = buyer_id;
	}
	public int getFeedback_id() {
		return feedback_id;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getStatus() {
		return status == 0 ? "Pending" : "Solved";
	}
	public void setStatus(int status) {
		status = status;
	}
	public int getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}
	
	
	
}
