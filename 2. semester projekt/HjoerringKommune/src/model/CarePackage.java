package model;

public class CarePackage {
	private int id;
	private String type;
	private long duration;
	private double price;

	public CarePackage(int id, String type, long duration, double price) {
		setId(id);
		setType(type);
		setDuration(duration);
		setPrice(price);
	}
	
	public CarePackage(String type, long duration, double price) {
		setType(type);
		setDuration(duration);
		setPrice(price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
