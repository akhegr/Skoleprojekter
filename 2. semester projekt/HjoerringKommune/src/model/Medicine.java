package model;

public class Medicine {
	private int id;
	private String name;
	private String unit;
	private String barcode;
	private String manufactor;
	
	public Medicine(int id, String name, String unit, String manufactor, String barcode) {
		setId(id);
		setName(name);
		setUnit(unit);
		setManufactor(manufactor);
		setBarcode(barcode);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}
}
