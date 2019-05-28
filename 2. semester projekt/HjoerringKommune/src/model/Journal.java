package model;

public class Journal {
	private int id;
	private String diseases;
	private String allergies;
	private String doctor;
	private String cprNo;
	
	public Journal(int id, String diseases, String allergies, String doctor, String cprNo) {
		setId(id);
		setDiseases(diseases);
		setAllergies(allergies);
		setDoctor(doctor);
		setCprNo(cprNo);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiseases() {
		return diseases;
	}

	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getCprNo() {
		return cprNo;
	}

	public void setCprNo(String cprNo) {
		this.cprNo = cprNo;
	}


}
