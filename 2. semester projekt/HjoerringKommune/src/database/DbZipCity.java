package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.DbLayerException;
import model.ZipCity;

public class DbZipCity implements DbZipCityIF {
	private static final String SELECT_ALL = "SELECT * FROM ZIPCITY";
	private static final String SELECT_BY_ID = "SELECT * FROM ZIPCITY WHERE id = ?";
	private static final String SELECT_BY_ZIPCODE = "SELECT * FROM ZIPCITY WHERE zipCode = ?";
	
	private PreparedStatement selectAll;
	private PreparedStatement selectById;
	private PreparedStatement selectByZipCode;

	public DbZipCity() throws SQLException, DbLayerException {
		selectAll = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_ALL);
		selectById = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ID);
		selectByZipCode = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ZIPCODE);
	}

	@Override
	public List<ZipCity> findAll() throws SQLException {
		ResultSet rs = selectAll.executeQuery();
		List<ZipCity> res = buildObjects(rs);
		return res;
	}

	@Override
	public ZipCity findById(int id) throws SQLException {
		selectById.setInt(1, id);
		ResultSet rs = selectById.executeQuery();
		ZipCity zipCity = buildObject(rs);
		return zipCity;
	}

	@Override
	public ZipCity findByZipCode(String zipCode) throws SQLException {
		selectByZipCode.setString(1, zipCode);
		ResultSet rs = selectByZipCode.executeQuery();
		ZipCity zipCity = buildObject(rs);
		return zipCity;
	}
	
	/**
	 * Build an instance of the object, based on resultset
	 * @param rs the information from the database
	 * @return an instance from the database
	 * @throws SQLException
	 */
	private ZipCity buildObject(ResultSet rs) throws SQLException {
		ZipCity g = null;
		if (rs.next()) {
			g = new ZipCity(rs.getInt("id"), rs.getString("zipCode"), rs.getString("city"));
		}
		return g;
	}
	
	/**
	 * Builds a list of instances of the object, based on resultset
	 * @param rs the information from the database
	 * @return list of instances from the database
	 * @throws SQLException
	 */
	private List<ZipCity> buildObjects(ResultSet rs) throws SQLException {
		List<ZipCity> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}
}
