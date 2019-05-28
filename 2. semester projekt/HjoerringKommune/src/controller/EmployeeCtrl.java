package controller;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import database.DbEmployee;
import exception.DbLayerException;
import exception.TransactionFailedException;
import model.Employee;
import model.ZipCity;

public class EmployeeCtrl {
	private DbEmployee dbEmployee;
	private ZipCityCtrl zipCityCtrl;
	
	public EmployeeCtrl() throws SQLException, DbLayerException {
		dbEmployee = new DbEmployee();
		zipCityCtrl = new ZipCityCtrl();
	}
	
	/**
	 * Creates an employee based on its information
	 * @param fName The employee's firstname
	 * @param lName The employee's lastname
	 * @param address The employee's address
	 * @param zipCode The employee's zipcode
	 * @param phone The employee's phonenumber
	 * @param sex The employee's sex
	 * @param emergencyContact The employee's emergency contact
	 * @param jobTitle The employee's jobtitle
	 * @param picture Picture of the employee 
	 * @param email The employee's email address
	 * @return The id of the employee in the database 
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	public int createEmployee(String fName, String lName, String address, String zipCode, String phone,
			String sex, String emergencyContact, String jobTitle, Blob picture, String email) throws SQLException, TransactionFailedException, DbLayerException {
		ZipCity zipCity = zipCityCtrl.findByZipCode(zipCode);
		if(phone.trim().equals("")) {
			phone = null;
		}
		
		Employee employee = new Employee(0, fName, lName, address, zipCity, phone, sex, emergencyContact,
				jobTitle, picture, email);
		return dbEmployee.insertEmployee(employee);
	}
	
	/**
	 * Finds an employee based on its id in the database
	 * @param id
	 * @return the employee
	 * @throws SQLException
	 */
	public Employee findById(int id) throws SQLException {
		return dbEmployee.findById(id);
	}
	
	/**
	 * Finds an employee based on its phone number
	 * @param phone
	 * @return the employee
	 * @throws SQLException
	 */
	public Employee findByPhone(String phone) throws SQLException {
		return dbEmployee.findByPhone(phone);
	}
	
	/**
	 * Finds all employees in the database
	 * @return list of employees
	 * @throws SQLException
	 */
	public List<Employee> findAll() throws SQLException {
		return dbEmployee.findAll();
	}
	
	/**
	 * Updates an employee based on its id in the databased
	 * @param id The id of the employee in the database
	 * @param fName The employee's firstname
	 * @param lName The employee's lastname
	 * @param address The employee's address
	 * @param zipCode The employee's zipcode
	 * @param phone The employee's phonenumber
	 * @param sex The employee's sex
	 * @param emergencyContact The employee's emergency contact
	 * @param jobTitle The employee's jobtitle
	 * @param picture Picture of the employee 
	 * @param email The employee's email address
	 * @return The id of the employee in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	public int updateEmployee(int id, String fName, String lName, String address, String zipCode,
			String phone, String sex, String emergencyContact, String jobTitle, Blob picture,
			String email) throws SQLException, TransactionFailedException, DbLayerException {
		ZipCity zipCity = zipCityCtrl.findByZipCode(zipCode);
		return dbEmployee.update(id, fName, lName, address, zipCity, phone, sex, emergencyContact, jobTitle, picture, email);
	}
	
	/**
	 * Deletes an employee based on its id in the database
	 * @param id The id of the employee in the database
	 * @return The id of the employee in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	public int deleteEmployee(int id) throws SQLException, TransactionFailedException, DbLayerException {
		return dbEmployee.remove(id);
	}
}
