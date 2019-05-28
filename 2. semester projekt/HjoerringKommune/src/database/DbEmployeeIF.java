package database;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import exception.DbLayerException;
import exception.TransactionFailedException;
import model.Employee;
import model.ZipCity;

public interface DbEmployeeIF {
	/**
	 * Iterates through all employee in the database,
	 * and makes a list of them.
	 * @return list of all the objects
	 * @throws SQLException
	 */
	List<Employee> findAll() throws SQLException;
	
	/**
	 * Find the employee with the given id in the database
	 * @param id the employee's id in the database
	 * @return the instance of an employee
	 * @throws SQLException
	 */
	Employee findById(int id) throws SQLException;
	
	/**
	 * Find the employee with the given phone number
	 * @param phone the employee's phone number
	 * @return the instance of an employee
	 * @throws SQLException
	 */
	Employee findByPhone(String phone) throws SQLException;
	
	/**
	 * Finds the employee with the given jobTitle in the database
	 * @param type the employee's jobTitle
	 * @return the instance of an employee
	 * @throws SQLException
	 */
	List<Employee> findByJobTitle(String jobTitle) throws SQLException;
	
	/**
	 * Finds the employee with the given id in the database,
	 * and updates it with given parametres
	 * @param id the id in the database
	 * @param fName the employee's firstname 
	 * @param lName the employee's lastname
	 * @param address the employee's address
	 * @param zipCity the employee's city's zipCity
	 * @param phone the employee's phonenumber
	 * @param sex the employee's sex
	 * @param emergencyContact the employee's emergency contact
	 * @param jobTitle the employee's jobTitle
	 * @param picture the employee's picture
	 * @param email the employee's email address
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int update(int id, String fName, String lName, String address, ZipCity zipCity, String phone, String sex,
			String emergencyContact, String jobTitle, Blob picture, String email) throws SQLException, TransactionFailedException, DbLayerException;
	
	/**
	 * Inserts the employee in the database
	 * @param employee the instance of an employee to insert
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int insertEmployee(Employee employee) throws SQLException, TransactionFailedException, DbLayerException;
	
	/**
	 * Deletes the employee in the database
	 * @param id the id in the database
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int remove(int id) throws SQLException, TransactionFailedException, DbLayerException;
}
