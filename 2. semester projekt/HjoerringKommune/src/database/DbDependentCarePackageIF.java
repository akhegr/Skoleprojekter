package database;

import java.sql.SQLException;
import java.util.List;

import model.DependentCarePackage;

public interface DbDependentCarePackageIF {
	/**
	 * Iterates through all dependentCarePackage in the database,
	 * and makes a list of them.
	 * @return list of all the dependentCarePackage
	 * @throws SQLException
	 */
	List<DependentCarePackage> findAll() throws SQLException;
	
	/**
	 * Finds the dependentCarePackage with the given dependentId,
	 * and carePackageId in the database
	 * @param dependentId
	 * @param carePackageId
	 * @return the searched object
	 * @throws SQLException
	 */
	DependentCarePackage findById(int dependentId, int carePackageId) throws SQLException;
	
	/**
	 * Finds a list of dependentCarePackage by dependent's id
	 * @param dependentId the dependent's id in the database
	 * @return list of dependentCarePackage
	 * @throws SQLException
	 */
	List<DependentCarePackage> findCPIdsForDependentID(int dependentId) throws SQLException;
	
	/**
	 * Inserts the dependentCarePackage in the database
	 * @param dependentId the dependent's id
	 * @param carePackageId the carePackage's id
	 * @return the id in the database
	 * @throws SQLException
	 */
	int insertData(int dependentId, int carePackageId) throws SQLException;
	
	/**
	 * Deletes the dependentCarePackage in the database 
	 * @param dependentId the dependent's id
	 * @param carePackageId the carePackage's Id
	 * @return the id in the database
	 * @throws SQLException
	 */
	int remove(int dependentId, int carePackageId) throws SQLException;
}
