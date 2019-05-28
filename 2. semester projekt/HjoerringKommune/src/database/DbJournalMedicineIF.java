package database;

import java.sql.SQLException;
import java.util.List;

import model.JournalMedicine;

public interface DbJournalMedicineIF {
	/**
	 * Iterates through all journalMedicin in the database,
	 * and makes a list of them.
	 * @return list of all the journalMedicin
	 * @throws SQLException
	 */
	List<JournalMedicine> findAll() throws SQLException;
	
	/**
	 * Finds all journalMedicin with the given journalId and
	 * medicineId in the database
	 * @param journalId the journalMedicin's journalId in the database
	 * @param medicineId the journalMedicin's medicineId in the database
	 * @return the searched journalMedicin
	 * @throws SQLException
	 */
	JournalMedicine findById(int journalId, int medicineId) throws SQLException;
}
