package database;

import java.sql.SQLException;
import java.util.List;

import model.ZipCity;

public interface DbZipCityIF {
	/**
	 * Iterates through all objects in the database,
	 * and makes a list of them.
	 * @return list of all the ZipCity
	 * @throws SQLException
	 */
	List<ZipCity> findAll() throws SQLException;
	
	/**
	 * Finds the object with the given id in the database
	 * @param id the object's id in the database
	 * @return the searched ZipCity
	 * @throws SQLException
	 */
	ZipCity findById(int id) throws SQLException;
	
	/**
	 * Finds the object with the given zipCode in the database
	 * @param zipCode the city's zipCode
	 * @return the searched ZipCity
	 * @throws SQLException
	 */
	ZipCity findByZipCode(String zipCode) throws SQLException;
}