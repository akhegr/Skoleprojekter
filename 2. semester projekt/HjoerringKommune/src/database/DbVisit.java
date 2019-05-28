package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import exception.DbLayerException;
import exception.TransactionFailedException;
import model.CarePackage;
import model.Dependent;
import model.Employee;
import model.Visit;

public class DbVisit implements DbVisitIF {
	private static final String SELECT_ALL = "SELECT * FROM VISIT";
	private static final String SELECT_BY_ID = "SELECT * FROM VISIT WHERE id = ?";
	private static final String SELECT_BY_DATE = "SELECT * FROM VISIT WHERE startDate = ?";
	private static final String FIND_BY_EMPLOYEE = "SELECT * FROM VISIT WHERE employee_id = ?";
	private static final String FIND_BY_DEPENDENT = "SELECT * FROM VISIT WHERE dependent_id = ?";
	private static final String FIND_BY_EMP_AND_TIME = "SELECT * FROM VISIT WHERE employee_id = ? AND startDate = ? AND startTime= convert(time, ?)";
	
	private static final String INSERT_DATA = "INSERT INTO VISIT (startDate, startTime, note, dependent_id, employee_id, visitEnd, carePackage_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_BY_ID = "UPDATE VISIT SET startDate = ?, startTime = ?, dependent_id = ?,"
			+ "employee_id = ?, note = ?, visitEnd = ?, carePackage_id = ? WHERE id = ?";
	private static final String DELETE_DATA_BY_EMPLOYEE_ID = "DELETE FROM VISIT WHERE employee_id = ?";
	private static final String DELETE_DATA_BY_ID = "DELETE FROM VISIT WHERE id = ?";

	private PreparedStatement selectAll;
	private PreparedStatement selectById;
	private PreparedStatement selectByDate;
	private PreparedStatement findByEmployee;
	private PreparedStatement findByDependent;
	private PreparedStatement findByEmpAndTime;
	private PreparedStatement insertData;
	private PreparedStatement updateById;
	private PreparedStatement deleteDataByEmployeeId;
	private PreparedStatement deleteDataById;

	private DbDependent dbDependent;
	private DbEmployee dbEmployee;
	private DbCarePackage dbCarePackage;

	public DbVisit() throws SQLException, DbLayerException {
		dbDependent = new DbDependent();
		dbEmployee = new DbEmployee();
		dbCarePackage = new DbCarePackage();

		selectAll = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_ALL);
		selectById = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ID);
		findByEmpAndTime = DbConnection.getInstance().getDBcon().prepareStatement(FIND_BY_EMP_AND_TIME);
		selectByDate = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_DATE);
		findByEmployee = DbConnection.getInstance().getDBcon().prepareStatement(FIND_BY_EMPLOYEE);
		findByDependent = DbConnection.getInstance().getDBcon().prepareStatement(FIND_BY_DEPENDENT);
		insertData = DbConnection.getInstance().getDBcon().prepareStatement(INSERT_DATA);
		updateById = DbConnection.getInstance().getDBcon().prepareStatement(UPDATE_BY_ID);
		deleteDataByEmployeeId = DbConnection.getInstance().getDBcon().prepareStatement(DELETE_DATA_BY_EMPLOYEE_ID);
		deleteDataById = DbConnection.getInstance().getDBcon().prepareStatement(DELETE_DATA_BY_ID);
	}

	@Override
	public List<Visit> findAll() throws SQLException {
		ResultSet rs = selectAll.executeQuery();
		List<Visit> res = buildObjects(rs);
		return res;
	}

	@Override
	public Visit findById(int id) throws SQLException {
		selectById.setInt(1, id);
		ResultSet rs = selectById.executeQuery();
		Visit visit = buildObject(rs);
		return visit;
	}

	@Override
	public List<Visit> findByDate(String date) throws SQLException {
		selectByDate.setString(1, date);
		ResultSet rs = selectByDate.executeQuery();
		List<Visit> res = buildObjects(rs);
		return res;
	}
	
	public Visit findByEmpAndTime(Employee emp, String date, LocalTime time) throws SQLException {
		findByEmpAndTime.setInt(1, emp.getId());
		findByEmpAndTime.setString(2, date);
		Time startTime = Time.valueOf(time);
		
		findByEmpAndTime.setTime(3, startTime);
		ResultSet rs = findByEmpAndTime.executeQuery();
		Visit visit = buildObject(rs);
		return visit;
	}
	
	@Override
	public List<Visit> findByEmployee(Employee employee) throws SQLException {
		int employeeId = employee.getId();
		findByEmployee.setInt(1, employeeId);
		ResultSet rs = findByEmployee.executeQuery();
		List<Visit> res = buildObjects(rs);
		return res;
	}
	
	@Override
	public List<Visit> findByDependent(Dependent dependent) throws SQLException {
		int dependentId = dependent.getId();
		findByDependent.setInt(1, dependentId);
		ResultSet rs = findByDependent.executeQuery();
		List<Visit> res = buildObjects(rs);
		return res;
	}

	@Override
	public int update(int id, String startDate, LocalTime startTime, Dependent dependent, Employee employee,
			String note, LocalTime visitEnd) throws SQLException, TransactionFailedException, DbLayerException {
		int value = -1;
		DbConnection.getInstance().startTransaction();
		try {
			int dependentId = dependent.getId();
			int employeeId = employee.getId();
			updateById.setString(1, startDate);
			updateById.setTime(2, java.sql.Time.valueOf(startTime));
			updateById.setInt(3, dependentId);
			updateById.setInt(4, employeeId);
			updateById.setString(5, note);
			updateById.setTime(6, java.sql.Time.valueOf(visitEnd));
			updateById.setInt(7, id);
			value = updateById.executeUpdate();
			DbConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DbConnection.getInstance().rollbackTransaction();
		}
		return value;
	}

	/**
	 * Build an instance of the object, based on resultset
	 * 
	 * @param rs the information from the database
	 * @return an instance from the database
	 * @throws SQLException
	 */
	private Visit buildObject(ResultSet rs) throws SQLException {
		Visit g = null;
		while (rs.next()) {
			Dependent d = dbDependent.findById(rs.getInt("dependent_id"));
			Employee e = dbEmployee.findById(rs.getInt("employee_id"));
			CarePackage cp = dbCarePackage.findById(rs.getInt("carePackage_id"));
			int startHour = rs.getTime("startTime").toLocalTime().getHour();
			int startMinute = rs.getTime("startTime").toLocalTime().getMinute();
			LocalTime visitEnd = rs.getTime("visitEnd").toLocalTime();

			g = new Visit(rs.getInt("id"), rs.getString("startDate"), startHour, startMinute, d, e,
					rs.getString("note"), visitEnd, cp);
		}
		return g;
	}

	/**
	 * Builds a list of instances of the object, based on resultset
	 * @param rs the information from the database
	 * @return list of instances from the database
	 * @throws SQLException
	 */
	private List<Visit> buildObjects(ResultSet rs) throws SQLException {
		List<Visit> res = new LinkedList<>();
		Visit g = null;
		while (rs.next()) {
			// res.add(buildObject(rs));
			Dependent d = dbDependent.findById(rs.getInt("dependent_id"));
			Employee e = dbEmployee.findById(rs.getInt("employee_id"));
			CarePackage cp = dbCarePackage.findById(rs.getInt("carePackage_id"));
			int startHour = rs.getTime("startTime").toLocalTime().getHour();
			int startMinute = rs.getTime("startTime").toLocalTime().getMinute();
			LocalTime visitEnd = rs.getTime("visitEnd").toLocalTime();

			g = new Visit(rs.getInt("id"), rs.getString("startDate"), startHour, startMinute, d, e,
					rs.getString("note"), visitEnd, cp);
			res.add(g);
		}
		return res;
	}

	@Override
	public int insertVisit(Visit v) throws SQLException, TransactionFailedException, DbLayerException {
		int value = -1;
		DbConnection.getInstance().startTransaction();
		try {
			int dependentId = v.getDependent().getId();
			int employeeId = v.getEmployee().getId();
			int carePackageId = v.getCarePackage().getId();
			insertData.setString(1, v.getStartDate());
			insertData.setTime(2, java.sql.Time.valueOf(v.getStartTime()));
			insertData.setString(3, v.getNote());
			insertData.setInt(4, dependentId);
			insertData.setInt(5, employeeId);
			insertData.setTime(6, java.sql.Time.valueOf(v.getVisitEnd()));
			insertData.setInt(7, carePackageId);
			value = insertData.executeUpdate();
			DbConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DbConnection.getInstance().rollbackTransaction();	
		}
		return value;
	}

	@Override
	public int removeByEmployeeId(int id) throws SQLException, TransactionFailedException, DbLayerException {
		int value = -1;
		DbConnection.getInstance().startTransaction();
		try {
			deleteDataByEmployeeId.setInt(1, id);
			value = deleteDataByEmployeeId.executeUpdate();
			DbConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DbConnection.getInstance().rollbackTransaction();
		}
		return value;
	}

	@Override
	public int removeById(int id) throws SQLException, TransactionFailedException, DbLayerException {
		int value = -1;
		DbConnection.getInstance().startTransaction();
		try {
			deleteDataById.setInt(1, id);
			value = deleteDataById.executeUpdate();
			DbConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DbConnection.getInstance().rollbackTransaction();
		}
		return value;
	}
}
