package model;

import java.util.LinkedList;

public class Dependent extends Person {
	private String partnerName;
	private Journal journal;
	private LinkedList<CarePackage> carePackages;
	
	public Dependent(int id, String fName, String lName, String address, ZipCity zipCity, String phone, String sex,
				String emergencyContact, String partnerName, Journal journal) {
		super(id, fName, lName, address, zipCity, phone, sex, emergencyContact);
		setPartnerName(partnerName);
		setJournal(journal);		
		
	}
	
	public String getPartnerName() {
		return partnerName;
	}


	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	
	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	public LinkedList<CarePackage> getCarePackages() {
		return carePackages;
	}

	public void setCarePackages(LinkedList<CarePackage> carePackages) {
		this.carePackages = carePackages;
	}
	
	public void addCarePackage(CarePackage carePackage) {
		carePackages.add(carePackage);
	}
	
	public void deleteCarePackage(int id) {
		carePackages.remove(id);
	}
}
