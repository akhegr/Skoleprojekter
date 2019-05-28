package controller;

import java.sql.SQLException;

import database.DbJournal;
import exception.CprNoIsNotCorrectException;
import exception.DbLayerException;
import exception.PersonWithCprNoNotExistException;
import model.Journal;

public class JournalCtrl {
	private DbJournal dbJournal;
	
	public JournalCtrl() throws SQLException, DbLayerException {
		dbJournal = new DbJournal();
	}
		
	/**
	 * Creates a journal in the database
	 * @param diseases The dependent's diseases
	 * @param allergies The dependent's allergies
	 * @param doctor The dependent's doctor
	 * @param cprNo The dependent's cpr number
	 * @throws SQLException
	 */
	public void createJournal(String diseases, String allergies, String doctor, String cprNo) throws SQLException {
		Journal journal = new Journal(0, diseases, allergies, doctor, cprNo);
		dbJournal.insertJournal(journal);
	}

	/**
	 * Finds a journal based on its id in the database
	 * @param id The id of the journal in the database 
	 * @return journal
	 * @throws SQLException
	 */
	public Journal findById(int id) throws SQLException {
		return dbJournal.findById(id);
	}
	
	/**
	 * Finds a journal based on the cpr number
	 * @param cprNo The dependent's cpr number
	 * @return journal
	 * @throws SQLException
	 * @throws CprNoIsNotCorrectException
	 * @throws PersonWithCprNoNotExistException
	 */
	public Journal findByCprNo(String cprNo) throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException {
		Journal j = null;
		if(dbJournal.verifyCprNo(cprNo)) {
			j = dbJournal.findByCprNo(cprNo);
		}
		else {
			throw new CprNoIsNotCorrectException(cprNo);
		}
		return j;
	}
	
	/**
	 * Deletes a journal based on the dependent's cpr number
	 * @param cprNo  The dependent's cpr number
	 * @throws SQLException
	 */
	public void deleteJournal(String cprNo) throws SQLException{
		dbJournal.deleteJournal(cprNo);
	}
}
