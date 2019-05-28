package database;

import java.sql.SQLException;
import java.util.List;

import exception.DbLayerException;
import exception.TransactionFailedException;
import model.CarePackage;

public interface DbCarePackageIF {
	/**
	 * Iterates through all objects in the database,
	 * and makes a list of them.
	 * @return list of all the objects
	 * @throws SQLException
	 */
	List<CarePackage> findAll() throws SQLException;

	/**
	 * Finds an object with the given id in the database
	 * @param id the object's id in the database
	 * @return the searched CarePackage
	 * @throws SQLException
	 */
	CarePackage findById(int id) throws SQLException;

	/**
	 * Finds the object with the given type in the database
	 * @param type the object's type
	 * @return the searched CarePackage
	 * @throws SQLException
	 */
	CarePackage findByType(String type) throws SQLException;
	
	/**
	 * Finds the object with the given id in the database,
	 * and updates it with given parametres
	 * @param id the id in the database
	 * @param type the type in the database
	 * @param duration the duration in the database
	 * @param price the price in the database 
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int updateCarePackage(int id, String type, long duration, double price) throws SQLException, TransactionFailedException, DbLayerException;

	/**
	 * Inserts the object in the database
	 * @param type the type in the database
	 * @param duration the duration in the database
	 * @param price the price in the database
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int insertData(String type, long duration, double price) throws SQLException, TransactionFailedException, DbLayerException;
	
	/**
	 * Deletes the object in the database
	 * @param id the id in the database
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int remove(int id) throws SQLException, TransactionFailedException, DbLayerException;
}
