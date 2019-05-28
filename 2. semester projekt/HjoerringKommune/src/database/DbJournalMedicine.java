package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.DbLayerException;
import model.JournalMedicine;

public class DbJournalMedicine implements DbJournalMedicineIF {
	private static final String SELECT_ALL = "SELECT * FROM JOURNAL_MEDICINE";
	private static final String SELECT_BY_ID = "SELECT * FROM JOURNAL_MEDICINE WHERE journal_id = ? AND medicine_id = ?";

	private PreparedStatement selectAll;
	private PreparedStatement selectById;
	
	public DbJournalMedicine() throws SQLException, DbLayerException {
		selectAll = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_ALL);
		selectById = DbConnection.getInstance().getDBcon().prepareStatement(SELECT_BY_ID);
	}

	@Override
	public List<JournalMedicine> findAll() throws SQLException {
		ResultSet rs = selectAll.executeQuery();
		List<JournalMedicine> res = buildObjects(rs);
		return res;
	}

	@Override
	public JournalMedicine findById(int journalId, int medicineId) throws SQLException {
		selectById.setInt(1, journalId);
		selectById.setInt(1, medicineId);
		ResultSet rs = selectById.executeQuery();
		JournalMedicine journalMedicine = buildObject(rs);
		return journalMedicine;
	}
	
	/**
	 * Build an instance of the object, based on resultset
	 * @param rs the information from the database
	 * @return an instance from the database
	 * @throws SQLException
	 */
	private JournalMedicine buildObject(ResultSet rs) throws SQLException {
		JournalMedicine g = null;
		if (rs.next()) {
			g = new JournalMedicine(rs.getInt("journal_id"), rs.getInt("medicine_id"),
					rs.getString("unit"), rs.getFloat("amount"), rs.getFloat("dosage"));
		}
		return g;
	}
	
	/**
	 * Builds a list of instances of the object, based on resultset
	 * @param rs the information from the database
	 * @return list of instances from the database
	 * @throws SQLException
	 */
	private List<JournalMedicine> buildObjects(ResultSet rs) throws SQLException {
		List<JournalMedicine> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}
}
