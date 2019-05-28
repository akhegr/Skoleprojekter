package model;

public class MedicalHistory {
	private int id;
	private String data;
	
	public MedicalHistory(int id, String data) {
		setId(id);
		setData(data);
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
