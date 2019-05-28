package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exception.DbLayerException;
import exception.TransactionFailedException;
import model.CarePackage;
import model.Dependent;
import model.DependentCarePackage;
import model.Journal;
import model.ZipCity;

public class DbDependent implements DbDependentIF {
	private static final String SELECT_ALL = "select * from Dependent";
	private static final String SELECT_BY_ID = "select * from Dependent where id = ?";
	private static final String SELECT_BY_JOURNAL_ID = "select * from Dependent where journal_id = ?";
	private static final String INSERT_DATA = "insert into Dependent (fName, lName, address, zipcity_id, phone, sex, "
							+ "emergencyContact, partnerName, journal_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE_BY_CPRNO = "UPDATE Dependent SET fName = ?, lName = ?, address = ?,"
							+ "zipcity_id = ?, phone = ?, sex = ?, partnerName = ?,"
							+ "emergencyContact = ? WHERE journal_id = ?";
	private static final String DELETE_DATA = "delete from Dependent where id = ?";
	
	private PreparedStatement selectAll;
	private PreparedStatement selectById;
	private PreparedStatement selectByJournalId;
	private PreparedStatement insertData;
	private PreparedStatement updateByCprNo;
	private PreparedStatement deleteData;
	private DbZipCity dbZipCity;
	private DbJournal dbJournal;
	private DbDependentCarePackage dbDepCarePackage;
	private DbCarePackage dbCarePackage;

	public DbDependent() throws SQLException, DbLayerException {
		dbJournal = new DbJournal();
		dbZipCity = new DbZipCity();
		dbCarePackage = new DbCarePackage();
		dbDepCarePackage = new DbDependentCarePackage();
		selectAll = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_ALL);
		selectById = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ID);
		selectByJournalId = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_JOURNAL_ID);
		insertData = DbConnection.getInstance().getDBcon().prepareStatement(INSERT_DATA);
		updateByCprNo = DbConnection.getInstance().getDBcon().prepareStatement(UPDATE_BY_CPRNO);
		deleteData = DbConnection.getInstance().getDBcon().prepareStatement(DELETE_DATA);
	}

	@Override
	public List<Dependent> findAll() throws SQLException {
		ResultSet rs = selectAll.executeQuery();
		List<Dependent> res = buildObjects(rs);
		return res;
	}

	@Override
	public Dependent findById(int id) throws SQLException {
		selectById.setInt(1, id);
		ResultSet rs = selectById.executeQuery();
		Dependent dependent = buildObject(rs);
		return dependent;
	}

	@Override
	public Dependent findByJournalId(int journalId) throws SQLException {
		selectByJournalId.setInt(1, journalId);
		ResultSet rs = selectByJournalId.executeQuery();
		Dependent dependent = buildObject(rs);
		return dependent;
	}
	
	@Override
	public int update(Journal journal, String fName, String lName, String address, ZipCity zipCity,
			String phone, String sex, String partnerName, String emergencyContact) throws SQLException,
			TransactionFailedException, DbLayerException {
		int value = 0;
		DbConnection.getInstance().startTransaction();
		try {
		updateByCprNo.setString(1, fName);
		updateByCprNo.setString(2, lName);
		updateByCprNo.setString(3, address);
		updateByCprNo.setInt(4, zipCity.getId());
		updateByCprNo.setString(5, phone);
		updateByCprNo.setString(6, sex);
		updateByCprNo.setString(7, partnerName);
		updateByCprNo.setString(8, emergencyContact);
		updateByCprNo.setInt(9, journal.getId());
		value = updateByCprNo.executeUpdate();
		DbConnection.getInstance().commitTransaction();
		}
		catch (SQLException e) {
			DbConnection.getInstance().rollbackTransaction();			
		}
		return value;
	}
	
	@Override
	public int insertDependent(Dependent dependent) throws SQLException,
			TransactionFailedException, DbLayerException {
		int value = 0;
		DbConnection.getInstance().startTransaction();
		try {
			insertData.setString(1, dependent.getfName());
			insertData.setString(2, dependent.getlName());
			insertData.setString(3, dependent.getAddress());
			insertData.setInt(4, dependent.getZipCity().getId());
			insertData.setString(5, dependent.getPhone());
			insertData.setString(6, dependent.getSex());
			insertData.setString(7, dependent.getEmergencyContact());
			insertData.setString(8, dependent.getPartnerName());
			insertData.setInt(9, dependent.getJournal().getId());
			value = insertData.executeUpdate();
			DbConnection.getInstance().commitTransaction();
		}
		catch(SQLException e) {
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
	private Dependent buildObject(ResultSet rs) throws SQLException {
		Dependent g = null;
		CarePackage tempCp = null;
		LinkedList<CarePackage> cpsToAdd = new LinkedList<>();
		ZipCity zipCity = null;
		Journal journal = null;
		int cpId = -1;
		while(rs.next()) {
			zipCity = dbZipCity.findById(rs.getInt("zipcity_id"));
			journal = dbJournal.findById(rs.getInt("journal_id"));
			List<DependentCarePackage> tempDepCpList = dbDepCarePackage.findCPIdsForDependentID(rs.getInt("id"));
			
			g = new Dependent(rs.getInt("id"), rs.getString("fName"), rs.getString("lName"), rs.getString("address"),
					zipCity, rs.getString("phone"), rs.getString("sex"), rs.getString("emergencyContact"), 
					rs.getString("partnerName"), journal);
			
			for(DependentCarePackage dcp : tempDepCpList) {
				cpId = dcp.getCarePackageId();
				tempCp = dbCarePackage.findById(cpId);
				cpsToAdd.add(tempCp);
			}
		}
		g.setCarePackages(cpsToAdd);
		return g;
	}

	/**
	 * Builds a list of instances of the object, based on resultset
	 * @param rs the information from the database
	 * @return list of instances from the database
	 * @throws SQLException
	 */
	private List<Dependent> buildObjects(ResultSet rs) throws SQLException {
		List<Dependent> res = new LinkedList<>();
		Dependent tempDep = null;
		int i = 1;
		while (rs.next()) {
			tempDep = findById(i);
			if(tempDep != null) {
				res.add(tempDep);
			}
			i++;
		}
		return res;
	}
}
