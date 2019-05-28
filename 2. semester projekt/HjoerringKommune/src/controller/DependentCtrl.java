package controller;

import java.sql.SQLException;
import java.util.List;

import database.DbDependent;
import exception.CprNoIsNotCorrectException;
import exception.DbLayerException;
import exception.TransactionFailedException;
import exception.PersonWithCprNoNotExistException;
import model.Dependent;
import model.Journal;
import model.ZipCity;

public class DependentCtrl {
	private DbDependent dbDependent;
	private ZipCityCtrl zipCityCtrl;
	private JournalCtrl journalCtrl;
	private CarePackageCtrl carePackageCtrl;
	
	
	public DependentCtrl() throws SQLException, DbLayerException {
		dbDependent = new DbDependent();
		zipCityCtrl = new ZipCityCtrl();
		journalCtrl = new JournalCtrl();
		carePackageCtrl = new CarePackageCtrl();
	}
	
	/**
	 * Creates a dependent based on its information
	 * @param fName The dependent's firstname
	 * @param lName The dependent's lastname
	 * @param address The dependent's address
	 * @param zipCode The dependent's zipcode
	 * @param phone The dependent's phonenumber
	 * @param sex The dependent's sex
	 * @param emergencyContact The dependent's emergency contact
	 * @param partnerName The dependent's partner's number
	 * @param cprNo The dependent's cpr number
	 * @return The id of the dependent in the database 
	 * @throws SQLException
	 * @throws CprNoIsNotCorrectException
	 * @throws PersonWithCprNoNotExistException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	public int createDependent(String fName, String lName, String address, String zipCode,
				String phone, String sex, String emergencyContact, String partnerName,
				String cprNo) throws SQLException, CprNoIsNotCorrectException,
				PersonWithCprNoNotExistException, TransactionFailedException, DbLayerException {
		ZipCity zipCity = zipCityCtrl.findByZipCode(zipCode);
		Journal journal = journalCtrl.findByCprNo(cprNo);
		
		Dependent dependent = new Dependent(0, fName, lName, address, zipCity, phone, 
				sex, emergencyContact, partnerName, journal);
		return dbDependent.insertDependent(dependent);
	}
	
	/**
	 * Finds a dependent based on its cpr number in the database
	 * @param cprNo The cpr number of the dependent
	 * @return dependent
	 * @throws SQLException
	 * @throws CprNoIsNotCorrectException
	 * @throws PersonWithCprNoNotExistException
	 */
	public Dependent findByCprNo(String cprNo) throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException {
		Journal j = journalCtrl.findByCprNo(cprNo);
		Dependent dep = dbDependent.findByJournalId(j.getId());
		return dep;
	}
	
	/**
	 * Finds a dependent based on its id in the database
	 * @param id
	 * @return dependent
	 * @throws SQLException
	 */
	public Dependent findById(int id) throws SQLException {
		Journal j = journalCtrl.findById(id);
		Dependent dep = dbDependent.findByJournalId(j.getId());
		return dep;
	}

	/**
	 * Finds all employees in the database
	 * @return list of dependents
	 * @throws SQLException
	 */
	public List<Dependent> findAll() throws SQLException {
		return dbDependent.findAll();
	}
	
	/**
	 * 
	 * @param fName The dependent's firstname
	 * @param lName The dependent's lastname
	 * @param address The dependent's address
	 * @param zipCode The dependent's zipcode
	 * @param phone The dependent's phonenumber
	 * @param cprNo The dependent's cpr number
	 * @param sex The dependent's sex
	 * @param partnerName The dependent's partner's number
	 * @param emergencyContact The dependent's emergency contact
	 * @return The id of the dependent in the database 
	 * @throws SQLException
	 * @throws CprNoIsNotCorrectException
	 * @throws PersonWithCprNoNotExistException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	public int updateDependent(String fName, String lName, String address, String zipCode, String phone,
			String cprNo, String sex, String partnerName, String emergencyContact) throws SQLException,
			CprNoIsNotCorrectException, PersonWithCprNoNotExistException, TransactionFailedException,
			DbLayerException {
		ZipCity zipCity = zipCityCtrl.findByZipCode(zipCode);
		Journal j = journalCtrl.findByCprNo(cprNo);
		return dbDependent.update(j, fName, lName, address, zipCity, phone, sex, partnerName, emergencyContact);
	}
	
	/**
	 * Deletes a dependent based on its id in the database
	 * @param id The id of the dependent in the database
	 * @return The id of the dependent in the database
	 * @throws SQLException
	 * @throws TransactionFailedException
	 * @throws DbLayerException 
	 */
	public int deleteDependent(int id) throws SQLException, TransactionFailedException, DbLayerException {
		return dbDependent.remove(id);
	}
	
	/**
	 * Adds a carepackage to a dependent based on its id in the database
	 * @param cprNo The dependent's cpr number
	 * @param id The id of the carepackage in the database
	 * @throws SQLException
	 * @throws CprNoIsNotCorrectException
	 * @throws PersonWithCprNoNotExistException
	 */
	public void addCarepackageToDependent(String cprNo, int id) throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException {
		Dependent dep = findByCprNo(cprNo);
		dep.addCarePackage(carePackageCtrl.findById(id));
	}
	
	/**
	 * Deletes a carepackage from a dependent based on its id in the database
	 * @param cprNo The dependent's cpr number
	 * @param id The id of the carepackage in the database
	 * @throws SQLException
	 * @throws CprNoIsNotCorrectException
	 * @throws PersonWithCprNoNotExistException
	 */
	public void removeCarepackageFromDependent(String cprNo, int id) throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException {
		Dependent dep = findByCprNo(cprNo);
		dep.deleteCarePackage(id);
	}
}
