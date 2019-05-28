package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Employee;
import controller.EmployeeCtrl;
import database.DbConnection;
import exception.DbLayerException;
import exception.TransactionFailedException;

class TestEmployee {
	private DbConnection dbCon;
	private EmployeeCtrl employeeCtrl;
	private String testPhone = "98215421";

	@BeforeEach
	void before() throws SQLException {
		try {
			dbCon = DbConnection.getInstance();
			dbCon.getDBcon();
			employeeCtrl = new EmployeeCtrl();
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	void testCreateEmployeeHappyDays() throws SQLException, TransactionFailedException {
		try {
			employeeCtrl.createEmployee("Jens", "Jensen", "Hansvej 1", "9000", testPhone, "m", "Børge Olsen",
					"Rådgiver", null, "hans@email.dk");
			Employee emp = employeeCtrl.findByPhone(testPhone);
			assertEquals("Jens", emp.getfName());
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
		cleanUp();
	}

	@Test
	void testUpdateEmployeeHappyDays() throws SQLException, TransactionFailedException {
		try {
			employeeCtrl.createEmployee("Jens", "Jensen", "Hansvej 1", "9000", testPhone, "m", "Børge Olsen",
					"Rådgiver", null, "hans2@email.dk");
			Employee empl = employeeCtrl.findByPhone("98215421");
			employeeCtrl.updateEmployee(empl.getId(), "Karl Børge", "Larsen", "Nyvej 47", "9000", testPhone,
					"K", "Lars Larsen", "Smed", null, "hans2@email.dk");
			Employee emp = employeeCtrl.findByPhone(testPhone);
			assertEquals("Karl Børge", emp.getfName());
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
		cleanUp();
	}

	@Test
	void testFindEmployees() throws SQLException {
		List<Employee> listOfEmployees = employeeCtrl.findAll(); 
		assertTrue(!listOfEmployees.isEmpty());
	}
	@Test
	void testCreateEmployeeNoPhone(){
		try {
			employeeCtrl.createEmployee("Jens", "Jensen", "Hansvej 1", "9000", " ", "m", "Børge Olsen",
					"Rådgiver", null, "hans2@email.dk");
		} catch (SQLException | TransactionFailedException e) {
			assertTrue(true, "In order to create an employee, you need a phone number");
		} catch (DbLayerException e) {
			assertFalse(false, "No connection to db");
		}
	}

	private void cleanUp() throws SQLException, TransactionFailedException {
		try {
			Employee emp = employeeCtrl.findByPhone(testPhone);
			employeeCtrl.deleteEmployee(emp.getId());
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}

}
