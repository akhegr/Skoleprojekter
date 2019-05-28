package model;

public abstract class Person {
	private int id;
	private String fName;
	private String lName;
	private String address;
	private ZipCity zipCity;
	private String phone;
	private String sex;
	private String emergencyContact;

	public Person(int id, String fName, String lName, String address, ZipCity zipCity, String phone, String sex, String emergencyContact) {
		setId(id);
		setfName(fName);
		setlName(lName);
		setAddress(address);
		setZipCity(zipCity);
		setPhone(phone);
		setSex(sex);
		setEmergencyContact(emergencyContact);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ZipCity getZipCity() {
		return zipCity;
	}

	public void setZipCity(ZipCity zipCity) {
		this.zipCity = zipCity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

}
