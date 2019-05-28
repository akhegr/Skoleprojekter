package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exception.DbLayerException;
import model.DependentCarePackage;

public class DbDependentCarePackage implements DbDependentCarePackageIF {
	private static final String SELECT_ALL = "SELECT * FROM DEPENDENT_CAREPACKAGE";
	private static final String SELECT_BY_ID = "SELECT * FROM DEPENDENT_CAREPACKAGE WHERE dependent_id = ? AND carepackage_id = ?";
	private static final String INSERT_DATA = "INSERT INTO DEPENDENT_CAREPACKAGE (dependent_id, carepackage_id) VALUES (?, ?)";
	private static final String DELETE_DATA = "DELETE * FROM DEPENDENT_CAREPACKAGE WHERE dependent_id = ? AND carepackage_id = ?";
	private static final String SELECT_CPID_FOR_DEPENDENT = "SELECT * FROM DEPENDENT_CAREPACKAGE where dependent_id = ?";

	private PreparedStatement selectCPIdsForDependent;
	private PreparedStatement selectAll;
	private PreparedStatement selectById;
	private PreparedStatement insertData;
	private PreparedStatement deleteData;

	public DbDependentCarePackage() throws SQLException, DbLayerException {
		selectAll = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_ALL);
		selectById = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ID);
		insertData = DbConnection.getInstance().getDBcon().prepareStatement(INSERT_DATA);
		deleteData = DbConnection.getInstance().getDBcon().prepareStatement(DELETE_DATA);
		selectCPIdsForDependent = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_CPID_FOR_DEPENDENT);
	}

	@Override
	public List<DependentCarePackage> findAll() throws SQLException {
		ResultSet rs = selectAll.executeQuery();
		List<DependentCarePackage> res = buildObjects(rs);
		return res;
	}

	@Override
	public DependentCarePackage findById(int dependentId, int carePackageId) throws SQLException {
		selectById.setInt(1, dependentId);
		selectById.setInt(2, carePackageId);
		ResultSet rs = selectById.executeQuery();
		DependentCarePackage dependentCarePackage = buildObject(rs);
		return dependentCarePackage;
	}
	
	@Override
	public List<DependentCarePackage> findCPIdsForDependentID(int dependentId) throws SQLException {
		selectCPIdsForDependent.setInt(1, dependentId);
		ResultSet rs = selectCPIdsForDependent.executeQuery();
		return buildObjects(rs);
	}
	
	@Override
	public int insertData(int dependentId, int carePackageId) throws SQLException {
		insertData.setInt(1, dependentId);
		insertData.setInt(2, carePackageId);
		return insertData.executeUpdate();
	}
	
	@Override
	public int remove(int dependentId, int carePackageId) throws SQLException {
		deleteData.setInt(1, dependentId);
		deleteData.setInt(2, carePackageId);
		return deleteData.executeUpdate();
	}
	
	/**
	 * Build an instance of the object, based on resultset
	 * @param rs the information from the database
	 * @return an instance from the database
	 * @throws SQLException
	 */
	private DependentCarePackage buildObject(ResultSet rs) throws SQLException {
		DependentCarePackage dcp = null;
		while (rs.next()) {
			dcp = new DependentCarePackage(rs.getInt("dependent_id"), rs.getInt("carepackage_id"));
		}
		return dcp;
	}
	
	/**
	 * Builds a list of instances of the object, based on resultset
	 * @param rs the information from the database
	 * @return list of instances from the database
	 * @throws SQLException
	 */	 
	private List<DependentCarePackage> buildObjects(ResultSet rs) throws SQLException {
		List<DependentCarePackage> res = new LinkedList<>();	
		while (rs.next()) {
			DependentCarePackage dcp = new DependentCarePackage(rs.getInt("dependent_id"), rs.getInt("carepackage_id"));
			res.add(dcp);
		}
		return res;
	}
}
