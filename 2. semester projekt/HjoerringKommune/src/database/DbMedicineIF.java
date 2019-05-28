package database;

import java.sql.SQLException;
import java.util.List;

import model.Medicine;

public interface DbMedicineIF {
	/**
	 * Iterates through all medicine in the database,
	 * and makes a list of them.
	 * @return list of all medicine
	 * @throws SQLException
	 */
	List<Medicine> findAll() throws SQLException;

	/**
	 * Finds medicine with the given id in the database
	 * @param id the medicine's id in the database
	 * @return the searched visit
	 * @throws SQLException
	 */
	Medicine findById(int id) throws SQLException;
	
	/**
	 * Finds all kind of the medicine, the dependent (with a given id) uses.
	 * @param id the dependent's id
	 * @return the searched medicine
	 * @throws SQLException
	 */
	List<Medicine> findByDependentId(int dependentID) throws SQLException;
}
