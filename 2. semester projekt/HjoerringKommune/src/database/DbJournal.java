package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.DbLayerException;
import exception.PersonWithCprNoNotExistException;
import model.Journal;

public class DbJournal implements DbJournalIF {
	private static final String SELECT_ALL = "SELECT * FROM JOURNAL";
	private static final String SELECT_BY_ID = "SELECT * FROM JOURNAL WHERE id = ?";
	private static final String FIND_ID_BY_CPRNO = "SELECT id FROM JOURNAL WHERE dependent_cprNo = ?";
	private static final String SELECT_BY_CPRNO = "SELECT * FROM JOURNAL WHERE dependent_cprNo = ?";
	private static final String INSERT_JOURNAL = "INSERT INTO JOURNAL (disease, allergies, doctor, dependent_cprNo)VALUES(?,?,?,?)";
	private static final String DELETE_JOURNAL = "DELETE JOURNAL FROM JOURNAL WHERE dependent_cprNo = ?";
	
	private PreparedStatement selectAll;
	private PreparedStatement selectById;
	private PreparedStatement selectByCprNo;
	private PreparedStatement findIdByCprNo;
	private PreparedStatement insertJournal;
	private PreparedStatement deleteJournal;
	
	public DbJournal() throws SQLException, DbLayerException {
		selectAll = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_ALL);
		selectById = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ID);
		findIdByCprNo = DbConnection.getInstance().getDBcon().prepareStatement(FIND_ID_BY_CPRNO);
		selectByCprNo = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_CPRNO);
		insertJournal = DbConnection.getInstance().getDBcon().prepareStatement(INSERT_JOURNAL);
		deleteJournal = DbConnection.getInstance().getDBcon().prepareStatement(DELETE_JOURNAL);
	}

	@Override
	public List<Journal> findAll() throws SQLException {
		ResultSet rs = selectAll.executeQuery();
		List<Journal> res = buildObjects(rs);
		return res;
	}

	@Override
	public Journal findById(int id) throws SQLException {
		selectById.setInt(1, id);
		ResultSet rs = selectById.executeQuery();
		Journal journal = buildObject(rs);
		return journal;
	}

	@Override
	public Journal findByCprNo(String cprNo) throws SQLException, PersonWithCprNoNotExistException {
		selectByCprNo.setString(1, cprNo);
		ResultSet rs = selectByCprNo.executeQuery();
		Journal journal = buildObject(rs);
		if(journal == null) {
			throw new PersonWithCprNoNotExistException(cprNo);
		}
		return journal;
	}
	
	@Override
	public int findIdByCprNo(String cprNo) throws SQLException {
		findIdByCprNo.setString(1, cprNo);
		ResultSet rs = findIdByCprNo.executeQuery();
		int jId = buildObject(rs).getId();
		return jId;
		
	}
	
	@Override
	public void insertJournal(Journal journal) throws SQLException {
		insertJournal.setString(1, journal.getDiseases());
		insertJournal.setString(2, journal.getAllergies());
		insertJournal.setString(3, journal.getDoctor());
		insertJournal.setString(4, journal.getCprNo());
		insertJournal.executeUpdate();
	}
	
	@Override
	public void deleteJournal(String cprNo) throws SQLException{
		deleteJournal.setString(1, cprNo);
		deleteJournal.execute();
	}

	/**
	 * Build an instance of the object, based on resultset
	 * @param rs the information from the database
	 * @return an instance from the database
	 * @throws SQLException
	 */
	private Journal buildObject(ResultSet rs) throws SQLException {
		Journal g = null;
		while(rs.next()) {
			g = new Journal(rs.getInt("id"), rs.getString("disease"), rs.getString("allergies"),
						rs.getString("doctor"), rs.getString("dependent_cprNo"));
				
		}
		return g;
	}

	public boolean verifyCprNo(String cprNo) {
		boolean modStatus = false;
		int length = cprNo.length();
		//String hyphen = cprNo.substring(6,7);
		char hyphen = cprNo.charAt(6); 
		if((length == 11) && (hyphen == '-'))
		{
	        String d1 = cprNo.substring(0,1);
	        String d2 = cprNo.substring(1,2);
	
	        String m1 = cprNo.substring(2,3);
	        String m2 = cprNo.substring(3,4);
	
	        String y1 = cprNo.substring(4,5);
	        String y2 = cprNo.substring(5,6);
	        
	        String l1 = cprNo.substring(7,8);
	        String l2 = cprNo.substring(8,9);
	        String l3 = cprNo.substring(9,10);
	        String c1 = cprNo.substring(10,11);
	        
	        int digit1 = Integer.parseInt(d1);
	        int digit2 = Integer.parseInt(d2);
	        int digit3 = Integer.parseInt(m1);
	        int digit4 = Integer.parseInt(m2);
	        int digit5 = Integer.parseInt(y1);
	        int digit6 = Integer.parseInt(y2);
	        int digit7 = Integer.parseInt(l1);
	        int digit8 = Integer.parseInt(l2);
	        int digit9 = Integer.parseInt(l3);
	        int digit10 = Integer.parseInt(c1);
	        
	        
	        double multiplyCPR = (digit1 * 4) + (digit2 * 3) + (digit3 * 2) + (digit4 * 7) + (digit5 * 6) + (digit6 * 5) + 
	        (digit7 * 4) + (digit8 * 3) + (digit9 * 2) + (digit10 * 1);
        
	        if(multiplyCPR % 11 == 0)
	        {
	            modStatus = true;
	        }
		}
		
        return modStatus;
	}
	
	/**
	 * Builds a list of instances of the object, based on resultset
	 * @param rs the information from the database
	 * @return list of instances from the database
	 * @throws SQLException
	 */	 
	private List<Journal> buildObjects(ResultSet rs) throws SQLException {
		List<Journal> res = new ArrayList<>();
		int i = 1;
		while (rs.next()) {
			//res.add(buildObject(rs));
			Journal tempJournal = findById(i);
			if(tempJournal != null) {
				res.add(tempJournal);
			}
			i++;
		}
		return res;
	}
}
 