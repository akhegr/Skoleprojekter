package database;

import java.sql.SQLException;
import java.util.List;

import exception.DbLayerException;
import exception.TransactionFailedException;
import model.Dependent;
import model.Journal;
import model.ZipCity;

public interface DbDependentIF {
	/**
	 * Iterates through all dependent in the database,
	 * and makes a list of them.
	 * @return list of all the objects
	 * @throws SQLException
	 */
	List<Dependent> findAll() throws SQLException;
	
	/**
	 * Finds the dependent with the given id in the database
	 * @param id the dependent's id in the database
	 * @return the searched dependent
	 * @throws SQLException
	 */
	Dependent findById(int id) throws SQLException;
	
	/**
	 * Finds the dependent with the given journalId in the database
	 * @param journalId the dependent's journalId
	 * @return the searched dependent
	 * @throws SQLException
	 */
	Dependent findByJournalId(int journalId) throws SQLException;
	
	/**
	 * Finds the dependent with the given id in the database,
	 * and updates it with given parametres
	 * @param journal the dependent's journal
	 * @param fName the dependent's firstname
	 * @param lName the dependent's lastname
	 * @param address the dependent's address
	 * @param zipCity the dependent's zipCity
	 * @param phone the dependent's phone number
	 * @param sex the dependent's sex
	 * @param partnerName the dependent's partner's name
	 * @param emergencyContact the dependent's emergency contact
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int update(Journal journal, String fName, String lName, String address, ZipCity zipCity, String phone, String sex,
			String partnerName, String emergencyContact) throws SQLException, TransactionFailedException, DbLayerException;
	
	/**
	 * Inserts the dependent in the database
	 * @param dependent the instance of the dependent
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int insertDependent(Dependent dependent) throws SQLException, TransactionFailedException, DbLayerException;
	
	/**
	 * Deletes the dependent in the database
	 * @param id the id in the database
	 * @return the id in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	int remove(int id) throws SQLException, TransactionFailedException, DbLayerException;
}
