package controller;

import java.sql.SQLException;

import database.DbCarePackage;
import exception.DbLayerException;
import exception.TransactionFailedException;
import model.CarePackage;

public class CarePackageCtrl {
	private DbCarePackage dbCarePackage;
	
	public CarePackageCtrl() throws SQLException, DbLayerException {
		dbCarePackage = new DbCarePackage();
	}
	
	public int createCarePackage(String type, long duration, double price) throws SQLException,
			TransactionFailedException, DbLayerException {
		return dbCarePackage.insertData(type, duration, price);
	}
	
	public CarePackage findById(int id) throws SQLException {
		return dbCarePackage.findById(id);
	}
	
	public CarePackage findByType(String type) throws SQLException {
		return dbCarePackage.findByType(type);
	}
	
	public int updateCarePackage(int id, String type, int duration, double price) throws SQLException,
			TransactionFailedException, DbLayerException {
		return dbCarePackage.updateCarePackage(id, type, duration, price);
	}
}
