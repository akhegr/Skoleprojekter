package controller;

import java.sql.SQLException;
import java.util.List;

import database.DbMedicine;
import exception.DbLayerException;
import model.Medicine;

public class MedicineCtrl {
private DbMedicine dbMedicine;
	
	public MedicineCtrl() throws SQLException, DbLayerException {
		dbMedicine = new DbMedicine();
	}
	
	/**
	 * Finds all medicine in the system
	 * @return list containing medicine
	 * @throws SQLException
	 */
	public List<Medicine> findAll() throws SQLException {
		return dbMedicine.findAll();
	}
	
	/**
	 * Finds a Medicine based on its id in the database
	 * @param id
	 * @return object containing the medicine
	 * @throws SQLException
	 */
	public Medicine findById(int id) throws SQLException {
		return dbMedicine.findById(id);
	}
	
	/**
	 * Finds all medicine connected to a dependent
	 * @param dependentID The id of the dependent
	 * @return list containing medicine
	 * @throws SQLException
	 */
	public List<Medicine> findByDependentId(int dependentID) throws SQLException {
		return dbMedicine.findByDependentId(dependentID);
	}
}
