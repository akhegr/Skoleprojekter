package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DbConnection;
import exception.DbLayerException;

class testDbConnection {

	DbConnection con = null;

	@BeforeEach
	public void setUp() {
		try {
			con = DbConnection.getInstance();
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void wasConnected() {
		try {
			con = DbConnection.getInstance();
		} catch (DbLayerException e) {
			e.printStackTrace();
		}
		assertNotNull(con, "Connected - connection cannot be null");

		DbConnection.closeConnection();
		boolean wasNullified = DbConnection.instanceIsNull();
		assertTrue(wasNullified, "Disconnected - instance set to null");
	}

	@AfterEach
	public void cleanUp() {
		DbConnection.closeConnection();
	}
}
