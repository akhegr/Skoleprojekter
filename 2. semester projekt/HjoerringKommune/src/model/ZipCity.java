package model;

public class ZipCity {
	private int id;
	private String zipCode;
	private String city;
	
	public ZipCity(int id, String zipCode, String city) {
		setId(id);
		setZipCode(zipCode);
		setCity(city);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
