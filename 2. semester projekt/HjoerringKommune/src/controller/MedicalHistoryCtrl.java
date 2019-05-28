package controller;

import java.sql.SQLException;
import java.util.List;

import database.DbMedicalHistory;
import exception.DbLayerException;
import model.MedicalHistory;

public class MedicalHistoryCtrl {
	private DbMedicalHistory dbMedicalHistory;
	
	public MedicalHistoryCtrl() throws SQLException, DbLayerException {
		dbMedicalHistory = new DbMedicalHistory();
	}
	
	/**
	 * Finds a list of medicalHistory
	 * @return list of all medical histories
	 * @throws SQLException
	 */
	public List<MedicalHistory> findAll() throws SQLException {
		return dbMedicalHistory.findAll();
	}
	
	/**
	 * Finds a medicalHistory based on its id in the database
	 * @param id The medicalhistory's id in the database 
	 * @return MedicalHistory
	 * @throws SQLException
	 */
	public MedicalHistory findById(int id) throws SQLException {
		return dbMedicalHistory.findById(id);
	}
	
	/**
	 * Finds a list of medicalHistory based on the dependent's id
	 * @param dependentId
	 * @return list of medicalHistory
	 * @throws SQLException
	 */
	public List<MedicalHistory> findByDependentId(int dependentId) throws SQLException {
		return dbMedicalHistory.findByDependentId(dependentId);
	}
}
