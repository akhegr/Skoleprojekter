package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.DbLayerException;
import exception.TransactionFailedException;
import model.CarePackage;

public class DbCarePackage implements DbCarePackageIF {
	private static final String SELECT_ALL = "select * from CarePackage";
	private static final String SELECT_BY_ID = "select * from CarePackage where id = ?";
	private static final String SELECT_BY_TYPE = "select * from CarePackage where serviceType = ?";
	private static final String INSERT_DATA = "insert into CarePackage (serviceType, duration, price) values (?, ?, ?)";
	private static final String DELETE_DATA = "delete from CarePackage where id = ?";
	private static final String UPDATE_BY_ID = "UPDATE CarePackage SET serviceType = ?, duration = ?, price = ? where id = ?";

	private PreparedStatement selectAll;
	private PreparedStatement selectById;
	private PreparedStatement selectByType;
	private PreparedStatement insertData;
	private PreparedStatement deleteData;
	private PreparedStatement updateById;

	public DbCarePackage() throws SQLException, DbLayerException {
		selectAll = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_ALL);
		selectById = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ID);
		selectByType = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_TYPE);
		insertData = DbConnection.getInstance().getDBcon().prepareStatement(INSERT_DATA);
		deleteData = DbConnection.getInstance().getDBcon().prepareStatement(DELETE_DATA);
		updateById = DbConnection.getInstance().getDBcon().prepareStatement(UPDATE_BY_ID);
	}

	@Override
	public List<CarePackage> findAll() throws SQLException {
		ResultSet rs = selectAll.executeQuery();
		List<CarePackage> res = buildObjects(rs);
		return res;
	}

	@Override
	public CarePackage findById(int id) throws SQLException {
		selectById.setInt(1, id);
		ResultSet rs = selectById.executeQuery();
		CarePackage carePackage = buildObject(rs);
		return carePackage;
	}

	@Override
	public CarePackage findByType(String type) throws SQLException {
		selectByType.setString(1, type);
		ResultSet rs = selectByType.executeQuery();
		CarePackage carePackage = buildObject(rs);
		return carePackage;
	}

	@Override
	public int updateCarePackage(int id, String serviceType, long duration, double price)
			throws SQLException, TransactionFailedException, DbLayerException {
		int value = 0;
		DbConnection.getInstance().startTransaction();
		try {
			updateById.setString(1, serviceType);
			updateById.setLong(2, duration);
			updateById.setDouble(3, price);
			updateById.setInt(4, id);
			value = updateById.executeUpdate();
			DbConnection.getInstance().commitTransaction();
		}
		catch(SQLException e) {
			DbConnection.getInstance().rollbackTransaction();
		}
		return value;
	}
	
	@Override
	public int insertData(String type, long duration, double price) throws SQLException,
			TransactionFailedException, DbLayerException {
		int value = 0;
		DbConnection.getInstance().startTransaction();
		try {
		insertData.setString(1, type);
		insertData.setLong(2,duration);
		insertData.setDouble(3, price);
		value = insertData.executeUpdate();
		DbConnection.getInstance().commitTransaction();
		}
		catch (SQLException e) {
			DbConnection.getInstance().rollbackTransaction();
		}
		return value;
	}

	@Override
	public int remove(int id) throws SQLException, TransactionFailedException, DbLayerException {
		int value = 0;
		DbConnection.getInstance().startTransaction();
		try {
		deleteData.setInt(1, id);
		value = deleteData.executeUpdate();
		DbConnection.getInstance().commitTransaction();
		}
		catch(SQLException e) {
			DbConnection.getInstance().rollbackTransaction();
		}
		return value;
	}
	
	/**
	 * Build an instance of the object, based on resultset
	 * @param rs the information from the database
	 * @return an instance from the database
	 * @throws SQLException
	 */
	private CarePackage buildObject(ResultSet rs) throws SQLException {
		CarePackage g = null;
		if (rs.next()) {
			g = new CarePackage(rs.getInt("id"), rs.getString("serviceType"),
					rs.getLong("duration"), rs.getDouble("price"));
		}
		return g;
	}
	
	/**
	 * Builds a list of instances of the object, based on resultset
	 * @param rs the information from the database
	 * @return list of instances from the database
	 * @throws SQLException
	 */
	private List<CarePackage> buildObjects(ResultSet rs) throws SQLException {
		List<CarePackage> res = new ArrayList<>();
		int i = 1;
		while (rs.next()) {
			CarePackage tempCp = findById(i);
			if(tempCp != null) {
				res.add(tempCp);
			}
			i++;
		}

		return res;
	}
}
