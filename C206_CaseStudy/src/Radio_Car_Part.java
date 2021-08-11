
public class Radio_Car_Part {
	private int part_ID;
	private String name;
	private int availability;
	private double price;
	private String feature;
	public Radio_Car_Part(int part_ID, String name, int availability, double price, String feature) {
		super();
		this.part_ID = part_ID;
		this.name = name;
		this.availability = availability;
		this.price = price;
		this.feature = feature;
	}
	public int getPart_ID() {
		return part_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvailability() {
		return availability == 0 ? "Available" : "Out Of Stock";
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	
}
