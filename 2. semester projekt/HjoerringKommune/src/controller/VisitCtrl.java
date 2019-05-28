package controller;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import controller.DependentCtrl;
import controller.EmployeeCtrl;
import database.DbVisit;
import exception.CprNoIsNotCorrectException;
import exception.CreateVisitDialogClosedException;
import exception.DbLayerException;
import exception.DependentNotSetException;
import exception.TransactionFailedException;
import exception.PersonWithCprNoNotExistException;
import model.CarePackage;
import model.Dependent;
import model.Employee;
import model.Visit;


public class VisitCtrl {
	private DbVisit dbVisit;
	private DependentCtrl dependentCtrl;
	private EmployeeCtrl employeeCtrl;
	private Visit visit;


	public VisitCtrl() throws SQLException, DbLayerException {
		dependentCtrl = new DependentCtrl();
		employeeCtrl = new EmployeeCtrl();
		dbVisit = new DbVisit();
	}

	/**
	 * Clears the current local visit
	 */
	public void clearVisit() {
		visit = new Visit(0, null, 0, 0, null, null, null, null, null);
	}

	/**
	 * Returns all visits on a specific date
	 * @param date The chosen date
	 * @return list of visits
	 * @throws SQLException
	 */
	public List<Visit> selectDate(String date) throws SQLException {

		visit.setStartDate(date);
		return dbVisit.findByDate(date);
	}

	/**
	 * Checks whether a combination of employee
	 * and time is available, and in that case
	 * the local visit stores it 
	 * @param employee The chosen employee
	 * @param hour The chosen hour
	 * @param minutes The chosen minutes
	 * @return status of chosen combination
	 * @throws SQLException
	 */
	public boolean selectTimeAndEmployee(Employee employee, int hour, int minutes) throws SQLException {
		boolean available = false;
		LocalTime time = LocalTime.of(hour, minutes);
		List<Visit> empVisits = dbVisit.findByEmployee(employee);
		if(empVisits.isEmpty() == true) {
			available = true;
		}
		if(!empVisits.isEmpty()) {
			for(Visit v : empVisits) {	
				if(v.getStartTime() == time || v.getVisitEnd() == time) {
					available = false;
				}
				if(v.getStartDate() == visit.getStartDate()) {
					available = false;
				}
				else{
					available = true;
				}	
			}
		}
		if(available == true) {
			visit.setStartTime(hour, minutes);
			visit.setEmployee(employee);
		}
		return available;

	}


	/**
	 * If the chosen dependent is available,
	 * it get stored in the local visit
	 * @param dependent The chosen dependent
	 * @return status of dependent available
	 * @throws SQLException
	 * @throws DependentNotSetException
	 * @throws CreateVisitDialogClosedException 
	 */
	public boolean selectDependent(Dependent dependent) throws SQLException, DependentNotSetException, CreateVisitDialogClosedException {
		boolean available = false;
		List<Visit> visits = dbVisit.findByDate(visit.getStartDate());
		if(visits.isEmpty() == true) {
			available = true;
		}
		if(!visits.isEmpty()) {
			for(Visit v : visits) {
				if(v.getEmployee().getId() == visit.getEmployee().getId() && v.getStartTime() != visit.getStartTime()
						&& v.getDependent().getId() != dependent.getId()) {
					available = true;
				}
				else if(v.getEmployee().getId() == visit.getEmployee().getId() && v.getStartTime() != visit.getStartTime()
						&& v.getDependent().getId() == dependent.getId()) {
					available = true;
				}
				else if(v.getEmployee().getId() != visit.getEmployee().getId() && v.getStartTime() != visit.getStartTime()
						&& v.getDependent().getId() != dependent.getId()) {
					available = true;
				}
				else {
					available = false;
				}
			}
		}
		if(available == true) {
			visit.setDependent(dependent);
		}
		else if(available == false) {
			throw new DependentNotSetException(dependent);
		}
		if(visit.getDependent() == null) {
			throw new CreateVisitDialogClosedException(); 
		}

		return available;
	}

	/**
	 * Save a comment to a specific local visit
	 * @param note Note to the visit
	 * @return returns true, when the note is set
	 * @throws SQLException
	 */
	public boolean editNote(String note) throws SQLException {
		visit.setNote(note);
		return true;
	}

	/**
	 * Saves the local visit in the database,
	 * and clears it 
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	public int insertVisit() throws SQLException, TransactionFailedException, DbLayerException {
		Visit localVisit = visit;
		clearVisit();
		return dbVisit.insertVisit(localVisit);
	}

	/**
	 * Returns a list of all visits
	 * @return lists all visits 
	 * @throws SQLException
	 */
	public List<Visit> findAll() throws SQLException {
		return dbVisit.findAll();
	}

	/**
	 * Returns a specific visit specified on
	 * the id in the database
	 * @param id
	 * @return visit
	 * @throws SQLException
	 */
	public Visit findById(int id) throws SQLException {
		return dbVisit.findById(id);
	}

	/**
	 * Returns a list of visits on a specific date
	 * @param date date of visit
	 * @return list of visits
	 * @throws SQLException
	 */
	public List<Visit> findByDate(String date) throws SQLException {
		return dbVisit.findByDate(date);
	}

	public Visit findByEmpAndTime(String date, String time, Employee employee) throws SQLException {
		int hour = Integer.parseInt(time.substring(0, 2));
		int min = Integer.parseInt(time.substring(3, 5));
		LocalTime localTime = LocalTime.of(hour, min);
		return dbVisit.findByEmpAndTime(employee, date, localTime);
	}


	/**
	 * Updates a visit based on its information
	 * @param id Visit id
	 * @param startDate Start date of visit
	 * @param startTime Start time of visit
	 * @param cprNo Cpr-no of dependent
	 * @param empId Employee id
	 * @param note Note to visit
	 * @param visitEnd Visit end time
	 * @return the id of the visit
	 * @throws SQLException
	 * @throws CprNoIsNotCorrectException
	 * @throws PersonWithCprNoNotExistException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	public int updateVisit(int id, String startDate, LocalTime startTime, String cprNo, int empId,
			String note, LocalTime visitEnd) throws SQLException, CprNoIsNotCorrectException,
	PersonWithCprNoNotExistException, TransactionFailedException, DbLayerException {
		Dependent dependent = dependentCtrl.findByCprNo(cprNo);
		Employee employee = employeeCtrl.findById(empId);
		return dbVisit.update(id, startDate, startTime, dependent, employee, note, visitEnd);
	}

	/**
	 * Deletes all visits with a specific employee
	 * @param id Employee id
	 * @return id Employee id
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	public int deleteVisitsByEmployeeId(int id) throws SQLException, TransactionFailedException,
	DbLayerException {
		return dbVisit.removeByEmployeeId(id);
	}

	/**
	 * Deletes a specific visit by its id in the database
	 * @param id Visit id
	 * @return id Visit id
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	public int deleteVisitById(int id) throws SQLException, TransactionFailedException, DbLayerException {
		return dbVisit.removeById(id);
	}

	/**
	 * Sets the visit end time by using the starttime,
	 * and the chosen carepackage to calculate endtime
	 * @param startTime Start time of the visit
	 * @param carePackage The chosen carepackage
	 * @throws SQLException
	 */
	public void setVisitEnd(LocalTime startTime, CarePackage carePackage) throws SQLException{
		long cpDuration = carePackage.getDuration();
		LocalTime visitEnd = calculateDuration(startTime, cpDuration);
		visit.setVisitEnd(visitEnd);
	}

	/**
	 * Gets the starttime of the local visit
	 * @return the clock as LocalTime
	 */
	public LocalTime getStartTime() {
		return visit.getStartTime();
	}

	/**
	 * Returns a list of all dependents
	 * @return list of dependent
	 * @throws SQLException
	 */
	public List<Dependent> listDependents() throws SQLException {
		return dependentCtrl.findAll();
	}

	/**
	 * Calculates the end time of a visit by
	 * using starttime and duration
	 * @param startTime
	 * @param cpDuration
	 * @return LocalTime end time 
	 */
	private LocalTime calculateDuration(LocalTime startTime, long cpDuration) {
		LocalTime visitEnd = startTime.plusMinutes(cpDuration);
		return visitEnd;
	}

	/**
	 * Gets a list of carepackages connected to a dependent
	 * @return list of carepackage
	 */
	public LinkedList<CarePackage> getDependentsCarePackages() {
		return visit.getDependent().getCarePackages();
	}

	/**
	 * Sets the chosen carepackage
	 * @param carePackage The chosen carepackage
	 */
	public void setCarePackage(CarePackage carePackage) {
		visit.setCarePackage(carePackage);
	}





	/**
	 * Finds all employees in the database
	 * @return list of employees
	 * @throws SQLException 
	 */
	public List<Employee> findAllEmployees() throws SQLException {
		return employeeCtrl.findAll();
	}

	/**
	 * Finds an employee based on its id in the database
	 * @param employeeId
	 * @return
	 * @throws SQLException
	 */
	public Employee findEmployeeById(int employeeId) throws SQLException {
		return employeeCtrl.findById(employeeId);
	}


	public Dependent findDependentByCprNo(String cprNo) throws SQLException, CprNoIsNotCorrectException,
	PersonWithCprNoNotExistException {
		return dependentCtrl.findByCprNo(cprNo);
	}

	public Dependent findDependentById(int id) throws SQLException, CprNoIsNotCorrectException {
		return dependentCtrl.findById(id);
	}
}
