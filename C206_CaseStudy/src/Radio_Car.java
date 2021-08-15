
public class Radio_Car {
	private int product_ID;
	private String name;
	private int availability;
	private String features;
	private double price;
	public Radio_Car(int product_ID, String name, String features, double price) {
		super();
		this.product_ID = product_ID;
		this.name = name;
		this.availability = 0;
		this.features = features;
		this.price = price;
	}
	public Radio_Car(int product_ID, String name, int availability) {
		this.product_ID = product_ID;
		this.name = name;
		this.availability = 0;
		
	}
	public int getProduct_ID() {
		return product_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvailability() {
		return availability == 0 ? "Available": "Out of Stock";
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
}
