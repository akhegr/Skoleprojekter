package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.VisitCtrl;
import database.DbConnection;
import exception.CprNoIsNotCorrectException;
import exception.CreateVisitDialogClosedException;
import exception.DbLayerException;
import exception.DependentNotSetException;
import exception.TransactionFailedException;
import exception.PersonWithCprNoNotExistException;
import model.CarePackage;
import model.Dependent;
import model.Employee;
import model.Visit;

class TestVisit {
	private DbConnection dbCon;
	private VisitCtrl visitCtrl;

	@BeforeEach
	void before() throws SQLException {
		try {
			dbCon = DbConnection.getInstance();
			dbCon.getDBcon();
			visitCtrl = new VisitCtrl();
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testVisitHappyDays() throws SQLException, CprNoIsNotCorrectException,
	PersonWithCprNoNotExistException, DependentNotSetException, TransactionFailedException {
		int valueToCheck = -1;
		try {
			visitCtrl.clearVisit();
			visitCtrl.selectDate("21-juni-2018");
			Employee emp = visitCtrl.findEmployeeById(3);
			Dependent dep = visitCtrl.findDependentByCprNo("123456-1231");
			CarePackage cp = dep.getCarePackages().get(0);
			visitCtrl.selectTimeAndEmployee(emp, 20, 15);
			visitCtrl.selectDependent(dep);
			visitCtrl.setCarePackage(cp);
			visitCtrl.editNote("har en hund");
			LocalTime startTime = visitCtrl.getStartTime();
			visitCtrl.setVisitEnd(startTime, cp);
			valueToCheck = visitCtrl.insertVisit();
			cleanUp();
		}
		catch(TransactionFailedException e) {
			assertTrue(false, "If the transaction failed because of a visit with the"
					+ " same startTime, end, employee and dependent this assert is correct.");
			e.printStackTrace();
			cleanUp();
		} catch (CreateVisitDialogClosedException e) {
			assertTrue(valueToCheck != -1, "if the value is -1 it was not inserted");
			e.printStackTrace();
			cleanUp();
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}
	private void cleanUp() throws SQLException, TransactionFailedException {
		try {
			int idToRemove = findTestVisit();
			visitCtrl.deleteVisitById(idToRemove);
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}

	private int findTestVisit() throws SQLException {
		List<Visit> tempList = visitCtrl.findByDate("21-maj-2018");
		String happyTest = "har en hund";
		int idToRemove = tempList.stream()
				.filter(v -> v.getNote().equals(happyTest) 
						&& v.getEmployee().getId() == 3 
						&& v.getDependent().getJournal().getCprNo().equals("123456-1231"))
				.mapToInt(v -> v.getId())
				.sum();
		System.out.println(idToRemove);
		return idToRemove;
	}
	@Test
	void testVisitTimeNotValidNegative() throws SQLException, CprNoIsNotCorrectException,
	PersonWithCprNoNotExistException, DependentNotSetException, TransactionFailedException {

		try {
			visitCtrl.clearVisit();
			visitCtrl.selectDate("21-maj-2018");
			Employee emp = visitCtrl.findEmployeeById(3);
			Dependent dep = visitCtrl.findDependentByCprNo("123456-1231");
			CarePackage cp = dep.getCarePackages().get(0);
			visitCtrl.selectTimeAndEmployee(emp, -1, -1);
			visitCtrl.selectDependent(dep);
			visitCtrl.setCarePackage(cp);
			visitCtrl.editNote("har en hund");
			LocalTime startTime = visitCtrl.getStartTime();
			visitCtrl.setVisitEnd(startTime, cp);
			visitCtrl.insertVisit();
		}
		catch(DateTimeException e) {
			assertTrue(true);
		} catch (CreateVisitDialogClosedException e) {
			e.printStackTrace();
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}
	@Test
	void testVisitTimeNotValidOver() throws SQLException, CprNoIsNotCorrectException,
	PersonWithCprNoNotExistException, DependentNotSetException, TransactionFailedException {

		try {
			visitCtrl.clearVisit();
			visitCtrl.selectDate("21-maj-2018");
			Employee emp = visitCtrl.findEmployeeById(3);
			Dependent dep = visitCtrl.findDependentByCprNo("123456-1231");
			CarePackage cp = dep.getCarePackages().get(0);
			visitCtrl.selectTimeAndEmployee(emp, 24, 60);
			visitCtrl.selectDependent(dep);
			visitCtrl.setCarePackage(cp);
			visitCtrl.editNote("har en hund");
			LocalTime startTime = visitCtrl.getStartTime();
			visitCtrl.setVisitEnd(startTime, cp);
			visitCtrl.insertVisit();
		}
		catch(DateTimeException e) {
			assertTrue(true);
		} catch (CreateVisitDialogClosedException e) {
			e.printStackTrace();
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}
}
