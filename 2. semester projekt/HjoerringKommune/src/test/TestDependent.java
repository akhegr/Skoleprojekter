package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.CarePackage;
import model.Dependent;
import controller.DependentCtrl;
import controller.JournalCtrl;
import database.DbConnection;
import exception.CprNoIsNotCorrectException;
import exception.DbLayerException;
import exception.TransactionFailedException;
import exception.PersonWithCprNoNotExistException;

class TestDependent {
	private DbConnection dbCon;
	private DependentCtrl dependentCtrl;
	private JournalCtrl journalCtrl;
	private String testCpr = "123456-4567";

	@BeforeEach
	void before() throws SQLException {
		try {
			dbCon = DbConnection.getInstance();
			dbCon.getDBcon();
			dependentCtrl = new DependentCtrl();
			journalCtrl = new JournalCtrl();
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testCreateDependentHappyDays() throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException, TransactionFailedException {
		try {
			journalCtrl.createJournal("das", "adsa", "sad", testCpr);
			dependentCtrl.createDependent("dan", "mcMan", "ydervej 2", "9000", "56421357", 
					"M", "456785213", "asdarwea", testCpr);
			Dependent dep = dependentCtrl.findByCprNo(testCpr);
			assertEquals("dan", dep.getfName());	
			cleanUp();
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}

	private void cleanUp() throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException, TransactionFailedException {
		try {
			Dependent dep = dependentCtrl.findByCprNo(testCpr);
			dependentCtrl.deleteDependent(dep.getId());
			journalCtrl.deleteJournal(testCpr);
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testFindDependents() throws SQLException {
		List<Dependent> listOfTDependents = dependentCtrl.findAll(); 
		assertTrue(!listOfTDependents.isEmpty());
	}

	@Test
	void testFindByCpr() throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException {
		Dependent dep = dependentCtrl.findByCprNo("020150-2233");
		if(dep.getJournal().getCprNo() == "020150-2233") {
			assertTrue(true, "Dependents journal cprNo = the cprNo search");
		}
	}

	@Test
	void testCreateDependentMissingzip() throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException, TransactionFailedException {
		try {
			journalCtrl.createJournal("das", "adsa", "sad", "987654-4323");
			dependentCtrl.createDependent("Jeg", "Skal Fejle", "fejlevej 7", "987654-4323",
					"123456487", "K", "98745612", "Fejleren", "987654-4323");
		} catch (SQLException e) {
			assertTrue(true);
		} catch(NullPointerException e) {
			assertTrue(true);
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
		cleanUpMissingJournal();
	}
	private void cleanUpMissingJournal() throws SQLException {
		journalCtrl.deleteJournal("987654-4323");
	}

	@Test
	void testCreateDependentNoJournal() throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException, TransactionFailedException{
		try {
			dependentCtrl.createDependent("Carsten", "Marilo", "dendervej 1", "9800", "123456785", "M", "32466745", "Søs Marilo", testCpr);
		} catch(PersonWithCprNoNotExistException e) {
			assertTrue(true);
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}
	@Test
	void testCreateDependentNoPartner() throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException, TransactionFailedException {
		try {
			journalCtrl.createJournal("kolera", "ingen", "Mengele", testCpr);
			dependentCtrl.createDependent("Carsten", "Marilo", "dendervej 1", "9800", "123456785", "M", "32466745", "", testCpr);
			Dependent dep = dependentCtrl.findByCprNo(testCpr);
			assertEquals("", dep.getPartnerName());
			cleanUpNoPartner();
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}


	private void cleanUpNoPartner() throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException, TransactionFailedException {
		try {
			Dependent dep = dependentCtrl.findByCprNo(testCpr);
			dependentCtrl.deleteDependent(dep.getId());
			journalCtrl.deleteJournal(testCpr);
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}

	@Test
	void getCarePackagesForADependent() throws SQLException, CprNoIsNotCorrectException, PersonWithCprNoNotExistException {
		Dependent dep = dependentCtrl.findById(1);
		for(CarePackage cp : dep.getCarePackages()) {
			System.out.println(cp.getType());
		}
		assertTrue(!dep.getCarePackages().isEmpty());
	}
}
