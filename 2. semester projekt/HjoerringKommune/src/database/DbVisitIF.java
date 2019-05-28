package database;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import exception.DbLayerException;
import exception.TransactionFailedException;
import model.Dependent;
import model.Employee;
import model.Visit;

public interface DbVisitIF {
	/**
	 * Iterates through all visit in the database,
	 * and makes a list of them.
	 * @return list of all the visit
	 * @throws SQLException
	 */
	List<Visit> findAll() throws SQLException;
	
	/**
	 * Finds the searched visit with the given id in the database
	 * @param id the object's id in the database
	 * @return the searched visit
	 * @throws SQLException
	 */
	Visit findById(int id) throws SQLException;
	
	/**
	 * Finds all visit with the given date
	 * @param date the visit's date in the database
	 * @return list of all visit with the given date
	 * @throws SQLException
	 */
	List<Visit> findByDate(String date) throws SQLException;
	
	
	/**
	 * Finds all visit with the given employee
	 * @param employee the employee to the visit
	 * @return
	 * @throws SQLException
	 */
	List<Visit> findByEmployee(Employee employee) throws SQLException;
	
	/**
	 * Finds all visit with the given dependent
	 * @param dependent the dependent to the visit
	 * @return
	 * @throws SQLException
	 */
	List<Visit> findByDependent(Dependent dependent) throws SQLException;
	
	/**
	 * Finds the visit with the given id in the database,
	 * and updates it with given parametres
	 * @param id the id in the database
	 * @param startDate the new startdate in the database
	 * @param startTime the new starttime in the database
	 * @param dependent the new dependent in the database
	 * @param employee the new employee in the database
	 * @param note the new note in the database
	 * @param visitEnd the new visitend in the database
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int update(int id, String startDate, LocalTime startTime, Dependent dependent, Employee employee, String note, LocalTime visitEnd)
			throws SQLException, TransactionFailedException, DbLayerException;
	
	/**
	 * Inserts the visit in the database
	 * @param v an instance of a visit
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int insertVisit(Visit v) throws SQLException, TransactionFailedException, DbLayerException;
	
	/**
	 * Deletes all visit with the employeeId in the database
	 * @param id the id in the database
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int removeByEmployeeId(int id) throws SQLException, TransactionFailedException, DbLayerException;
	
	/**
	 * Deletes a specific visit with the id in the database
	 * @param id the id in the database
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int removeById(int id) throws SQLException, TransactionFailedException, DbLayerException;
}
