package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.DbLayerException;
import model.MedicalHistory;

public class DbMedicalHistory implements DbMedicalHistoryIF {
	private static final String SELECT_ALL = "SELECT * FROM MEDICALHISTORY";
	private static final String SELECT_BY_ID = "SELECT * FROM MEDICALHISTORY WHERE id = ?";
	private static final String SELECT_BY_DEPENDENTID = "SELECT * FROM MEDICALHISTORY WHERE dependentID = ?";

	private PreparedStatement selectAll;
	private PreparedStatement selectById;
	private PreparedStatement selectByDependentId;

	public DbMedicalHistory() throws SQLException, DbLayerException {
		selectAll = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_ALL);
		selectById = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ID);
		selectByDependentId = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_DEPENDENTID);
	}

	@Override
	public List<MedicalHistory> findAll() throws SQLException {
		ResultSet rs = selectAll.executeQuery();
		List<MedicalHistory> res = buildObjects(rs);
		return res;
	}

	@Override
	public MedicalHistory findById(int id) throws SQLException {
		selectById.setInt(1, id);
		ResultSet rs = selectById.executeQuery();
		MedicalHistory medicalHistory = buildObject(rs);
		return medicalHistory;
	}

	@Override
	public List<MedicalHistory> findByDependentId(int dependentID) throws SQLException {
		ResultSet rs = selectByDependentId.executeQuery();
		List<MedicalHistory> res = buildObjects(rs);
		return res;
	}
	
	/**
	 * Build an instance of the object, based on resultset
	 * @param rs the information from the database
	 * @return an instance from the database
	 * @throws SQLException
	 */
	private MedicalHistory buildObject(ResultSet rs) throws SQLException {
		MedicalHistory g = null;
		if (rs.next()) {
			g = new MedicalHistory(rs.getInt("id"), rs.getString("data"));
		}
		return g;
	}

	/**
	 * Builds a list of instances of the object, based on resultset
	 * @param rs the information from the database
	 * @return list of instances from the database
	 * @throws SQLException
	 */
	private List<MedicalHistory> buildObjects(ResultSet rs) throws SQLException {
		List<MedicalHistory> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}
}
