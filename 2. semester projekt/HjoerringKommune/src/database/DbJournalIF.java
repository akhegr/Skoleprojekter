package database;

import java.sql.SQLException;
import java.util.List;

import exception.PersonWithCprNoNotExistException;
import model.Journal;

public interface DbJournalIF {
	/**
	 * Iterates through all journal in the database,
	 * and makes a list of them.
	 * @return list of all the journal
	 * @throws SQLException
	 */
	List<Journal> findAll() throws SQLException;
	
	/**
	 * Finds the journal with the given id in the database
	 * @param id the journal's id in the database
	 * @return the searched journal
	 * @throws SQLException
	 */
	Journal findById(int id) throws SQLException;

	/**
	 * Finds the journal with the given cpr number
	 * @param cprNo the journal's cpr number
	 * @return the searched journal
	 * @throws SQLException
	 * @throws PersonWithCprNoNotExistException
	 */
	Journal findByCprNo(String cprNo) throws SQLException, PersonWithCprNoNotExistException;
	
	/**
	 * Finds a journalId based on the cpr number
	 * @param cprNo the dependent's cpr number
	 * @return the dependent's journalId
	 * @throws SQLException
	 */
	int findIdByCprNo(String cprNo) throws SQLException;
	
	/**
	 * Inserts the journal in the database
	 * @param journal the instance of a journal to insert
	 * @throws SQLException
	 */
	void insertJournal(Journal journal) throws SQLException;
	
	/**
	 * Deletes the journal in the database
	 * @param cprNo the dependent's cpr number
	 * @throws SQLException
	 */
	void deleteJournal(String cprNo) throws SQLException;
}
