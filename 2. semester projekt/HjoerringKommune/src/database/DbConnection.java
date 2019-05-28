package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import exception.TransactionFailedException;
import exception.DbLayerException;

public class DbConnection {
	// Constants used to get access to the database
	
	private static final String driver = "jdbc:sqlserver://kraka.ucn.dk;sendTimeAsDateTime=f‌​a‌​lse";
	private static final String databaseName = ";databaseName=dmaa0917_1067309";
	
	private static String userName = "; user=dmaa0917_1067309";
	private static String password = ";password=Password1!";
	private String url = driver + databaseName + userName + password;
	
	private DatabaseMetaData dma;
	private static Connection con;
	
	// an instance of the class is generated
	private static DbConnection instance = null;
	
	// the constructor is private to ensure that only one object of this class
	// is created
	private DbConnection() throws DbLayerException {
		try {
			// load of driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Driver class loaded ok");
			
		} catch (Exception e) {
			System.out.println("Cannot find the driver");
			System.out.println(e.getMessage());
		}
		try {
			// connection to the database
			con = DriverManager.getConnection(url);
			con.setAutoCommit(true);
			dma = con.getMetaData(); // get meta data
			System.out.println("Connection to " + dma.getURL());
			System.out.println("Driver " + dma.getDriverName());
			System.out.println("Database product name " + dma.getDatabaseProductName());
		} // end try
		catch (Exception e) {
			throw new DbLayerException(e, url);
		} // end catch
	}// end constructor
	
	// closeDb: closes the connection to the database
	public static void closeConnection() {
		try {
			con.close();
			instance = null;
			System.out.println("The connection is closed");
		} catch (Exception e) {
			System.out.println("Error trying to close the database " + e.getMessage());
		}
	}// end closeDB
	
	// getDBcon: returns the singleton instance of the DB connection
	public Connection getDBcon() {
		return con;
	}
	
	public Connection checkDBcon() throws SQLException {
		return DriverManager.getConnection(url);
	}
	
	// getDBcon: returns the singleton instance of the DB connection
	public static boolean instanceIsNull() {
		return (instance == null);
	}
	
	// this method is used to get the instance of the connection
	public static DbConnection getInstance() throws DbLayerException {
		if (instance == null) {
			instance = new DbConnection();
		}
		return instance;
	}
	
	public void startTransaction() throws SQLException {
		con.setAutoCommit(false);
		System.out.println("transaction started");
	}
	
	public void commitTransaction() throws SQLException {
		con.commit();
		con.setAutoCommit(true);
		System.out.println("transaction committed");
	}
	
	public void rollbackTransaction() throws SQLException, TransactionFailedException {
		con.rollback();
		con.setAutoCommit(true);
		System.out.println("insert failed - rolling back");
		throw new TransactionFailedException();
	}
	
}// end DbConnection
