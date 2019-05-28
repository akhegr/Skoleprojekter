package database;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exception.DbLayerException;
import exception.TransactionFailedException;
import model.Employee;
import model.ZipCity;

public class DbEmployee implements DbEmployeeIF {


	private static final String SELECT_ALL = "SELECT * FROM EMPLOYEE";
	private static final String SELECT_BY_ID =	"SELECT * FROM EMPLOYEE WHERE id = ?";
	private static final String SELECT_BY_PHONE = "SELECT * FROM EMPLOYEE WHERE phone = ?";
	private static final String SELECT_BY_JOBTITLE = "SELECT * FROM EMPLOYEE WHERE jobTitle = ?";
	private static final String INSERT_DATA = "INSERT INTO EMPLOYEE (fName, lName, address, zipcity_id, phone,"
			+ "sex, emergencyContact, jobTitle, picture, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_BY_ID = "UPDATE EMPLOYEE SET fName = ?, lName = ?, address = ?,"
			+ "zipcity_id = ?, phone = ?, sex = ?, emergencyContact = ?, jobTitle = ?, picture = ?,"  
			+ "email = ? WHERE id = ?";
	private static final String DELETE_DATA = "DELETE FROM EMPLOYEE WHERE id = ?";

	private PreparedStatement selectAll;
	private PreparedStatement selectById;
	private PreparedStatement selectByPhone;
	private PreparedStatement selectByJobTitle;
	private PreparedStatement insertData;
	private PreparedStatement updateById;
	private PreparedStatement deleteData;
	private DbZipCity dbZipCity;

	public DbEmployee() throws SQLException, DbLayerException {
		dbZipCity = new DbZipCity();
		selectAll = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_ALL);
		selectById = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ID);
		selectByPhone = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_PHONE);
		selectByJobTitle = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_JOBTITLE);
		insertData = DbConnection.getInstance().getDBcon().prepareStatement(INSERT_DATA);
		updateById = DbConnection.getInstance().getDBcon().prepareStatement(UPDATE_BY_ID);
		deleteData = DbConnection.getInstance().getDBcon().prepareStatement(DELETE_DATA);
	}

	@Override
	public List<Employee> findAll() throws SQLException {
		ResultSet rs = selectAll.executeQuery();
		List<Employee> res = buildObjects(rs);
		return res;
	}

	@Override
	public Employee findById(int id) throws SQLException {
		selectById.setInt(1, id);
		ResultSet rs = selectById.executeQuery();
		Employee employee = buildObject(rs);
		return employee;
	}

	@Override
	public Employee findByPhone(String phone) throws SQLException {
		selectByPhone.setString(1, phone);
		ResultSet rs = selectByPhone.executeQuery();
		Employee employee = buildObject(rs);
		return employee;
	}

	@Override
	public List<Employee> findByJobTitle(String jobTitle) throws SQLException {
		ResultSet rs = selectByJobTitle.executeQuery();
		List<Employee> res = buildObjects(rs);
		return res;
	}
	
	@Override
	public int insertEmployee(Employee employee) throws SQLException, TransactionFailedException, DbLayerException {
		int value = -1;
		DbConnection.getInstance().startTransaction();
		try {
			insertData.setString(1, employee.getfName());
			insertData.setString(2, employee.getlName());
			insertData.setString(3, employee.getAddress());
			insertData.setInt(4, employee.getZipCity().getId());
			insertData.setString(5, employee.getPhone());
			insertData.setString(6, employee.getSex());
			insertData.setString(7, employee.getEmergencyContact());
			insertData.setString(8, employee.getJobTitle());
			insertData.setBlob(9, employee.getPicture());
			insertData.setString(10, employee.getEmail());
			value = insertData.executeUpdate();
			DbConnection.getInstance().commitTransaction();
		}
		catch(SQLException e) {
			DbConnection.getInstance().rollbackTransaction();
		}

		return value;
	}

	@Override
	public int update(int id, String fName, String lName, String address, ZipCity zipCity, String phone,
			String sex, String emergencyContact, String jobTitle, Blob picture, String email) throws SQLException,
			TransactionFailedException, DbLayerException {
		int value = -1;
		DbConnection.getInstance().startTransaction();
		try {
		updateById.setString(1, fName);
		updateById.setString(2, lName);
		updateById.setString(3, address);
		updateById.setInt(4, zipCity.getId());
		updateById.setString(5, phone);
		updateById.setString(6, sex);
		updateById.setString(7, emergencyContact);
		updateById.setString(8, jobTitle);
		updateById.setBlob(9, picture);
		updateById.setString(10, email);
		updateById.setInt(11, id);
		value = updateById.executeUpdate();
		DbConnection.getInstance().commitTransaction();
		}
		catch(SQLException e) {
			DbConnection.getInstance().rollbackTransaction();
		}
		return value;
	}
	
	@Override
	public int remove(int id) throws SQLException, TransactionFailedException, DbLayerException {
		int value = -1;
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
	private Employee buildObject(ResultSet rs) throws SQLException {
		Employee g = null;

		while (rs.next()) {
			ZipCity zipCity = dbZipCity.findById(rs.getInt("zipcity_id"));
			g = new Employee(rs.getInt("id"), rs.getString("fName"), rs.getString("lName"),
					rs.getString("address"), zipCity, rs.getString("phone"), rs.getString("sex"),
					rs.getString("emergencyContact"), rs.getString("jobTitle"), rs.getBlob("picture"),
					rs.getString("email"));
		}
		return g;
	}

	/**
	 * Builds a list of instances of the object, based on resultset
	 * @param rs the information from the database
	 * @return list of instances from the database
	 * @throws SQLException
	 */	 
	private List<Employee> buildObjects(ResultSet rs) throws SQLException {
		List<Employee> res = new LinkedList<>();
		int i = 1;
		while (rs.next()) {
			//res.add(buildObject(rs));
			res.add(findById(i));
			i++;
		}
		return res;
	}
}
