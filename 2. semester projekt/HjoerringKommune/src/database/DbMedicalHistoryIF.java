package database;

import java.sql.SQLException;
import java.util.List;

import model.MedicalHistory;

public interface DbMedicalHistoryIF {
	/**
	 * Iterates through all medicalHistory in the database,
	 * and makes a list of them.
	 * @return list of all the medicalHistory
	 * @throws SQLException
	 */
	List<MedicalHistory> findAll() throws SQLException;
	
	/**
	 * Finds all medicalHistory with the given dependentId in the database
	 * @param dependentID the dependent's id
	 * @return list of all the medicalHistory
	 * @throws SQLException
	 */
	List<MedicalHistory> findByDependentId(int dependentID) throws SQLException;
	
	/**
	 * Find the medicalHistory with the given id in the database
	 * @param id the medicalHistory's id in the database
	 * @return the searched medicalHistory
	 * @throws SQLException
	 */
	MedicalHistory findById(int id) throws SQLException;
}