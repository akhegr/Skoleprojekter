package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.DbLayerException;
import model.Medicine;

public class DbMedicine implements DbMedicineIF {
	private static final String SELECT_ALL = "SELECT * FROM MEDICINE";
	private static final String SELECT_BY_ID = "SELECT * FROM MEDICINE WHERE id = ?";
	private static final String SELECT_BY_DEPENDENTID = "SELECT * FROM MEDICINE WHERE dependentID = ?";

	private PreparedStatement selectAll;
	private PreparedStatement selectById;
	private PreparedStatement selectByDependentId;
	
	public DbMedicine() throws SQLException, DbLayerException {
		selectAll = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_ALL);
		selectById = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ID);
		selectByDependentId = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_DEPENDENTID);
	}

	@Override
	public List<Medicine> findAll() throws SQLException {
		ResultSet rs = selectAll.executeQuery();
		List<Medicine> res = buildObjects(rs);
		return res;
	}

	@Override
	public Medicine findById(int id) throws SQLException {
		selectById.setInt(1, id);
		ResultSet rs = selectById.executeQuery();
		Medicine medicine = buildObject(rs);
		return medicine;
	}

	@Override
	public List<Medicine> findByDependentId(int dependentID) throws SQLException {
		selectByDependentId.setInt(1, dependentID);
		ResultSet rs = selectByDependentId.executeQuery();
		List<Medicine> res = buildObjects(rs);
		return res;
	}
	
	/**
	 * Build an instance of the object, based on resultset
	 * @param rs the information from the database
	 * @return an instance from the database
	 * @throws SQLException
	 */
	private Medicine buildObject(ResultSet rs) throws SQLException {
		Medicine g = null;
		if (rs.next()) {
			g = new Medicine(rs.getInt("id"), rs.getString("name"), rs.getString("unit"),
					rs.getString("manufactor"), rs.getString("barcode"));
		}
		return g;
	}

	/**
	 * Builds a list of instances of the object, based on resultset
	 * @param rs the information from the database
	 * @return list of instances from the database
	 * @throws SQLException
	 */	 
	private List<Medicine> buildObjects(ResultSet rs) throws SQLException {
		List<Medicine> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}
}
