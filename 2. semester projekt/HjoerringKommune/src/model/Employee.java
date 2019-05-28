package model;

import java.sql.Blob;

public class Employee extends Person {
	private String jobTitle;
	private Blob picture;
	private String email;

	public Employee(int id, String fName, String lName, String address, ZipCity zipCity, String phone,
			String sex, String emergencyContact, String jobTitle, Blob picture, String email) {
		super(id, fName, lName, address, zipCity, phone, sex, emergencyContact);
		setJobTitle(jobTitle);
		setPicture(picture);
		setEmail(email);
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
