package controller;

import java.sql.SQLException;

import database.DbConnection;
import exception.DbLayerException;

public class ConCheckCtrl {
	/**
	 * Checks whether the access to the database is available or not 
	 * @return status of the database
	 * @throws SQLException
	 * @throws DbLayerException 
	 */
	public boolean conCheck() throws SQLException, DbLayerException {
		boolean con;
		if (DbConnection.getInstance().checkDBcon() != null) {
			con = true;
		}
		else {
			DbConnection.getInstance().getDBcon();
			con = false;
		}
		return con;
	}
}
