package model;

import java.time.LocalTime;

public class Visit {
	private int id;
	private String startDate;
	private LocalTime startTime;
	private LocalTime visitEnd;
	private Dependent dependent;
	private Employee employee;
	private String note;
	private CarePackage carePackage;

	public CarePackage getCarePackage() {
		return carePackage;
	}

	public void setCarePackage(CarePackage carePackage) {
		this.carePackage = carePackage;
	}

	public Visit(int id, String startDate, int startHour, int startMinute, Dependent dependent, Employee employee, String note, LocalTime visitEnd, CarePackage carePackage) {
		setId(id);
		setStartDate(startDate);
		setStartTime(startHour, startMinute);
		setDependent(dependent);
		setEmployee(employee);
		setNote(note);
		setVisitEnd(visitEnd);
		setCarePackage(carePackage);
	}

	public void setVisitEnd(LocalTime visitEnd) {
		this.visitEnd = visitEnd;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(int hour, int minute) {
		this.startTime = LocalTime.of(hour, minute);
	}

	public Dependent getDependent() {
		return dependent;
	}


	public void setDependent(Dependent dependent) {
		this.dependent = dependent;
	}


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalTime getVisitEnd() {
		return visitEnd;
	}
}
