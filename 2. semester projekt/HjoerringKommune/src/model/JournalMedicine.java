package model;

public class JournalMedicine {
	private int medicineId;
	private int journalId;
	private float amount;
	private float dosage;
	
	public JournalMedicine(int journalId, int medicineId, String unit, float amount, float dosage) {
		setMedicineId(medicineId);
		setJournalId(journalId);
		setAmount(amount);
		setDosage(dosage);
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getDosage() {
		return dosage;
	}

	public void setDosage(float dosage) {
		this.dosage = dosage;
	}
	
	
}
