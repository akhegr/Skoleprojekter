package controller;

import java.sql.SQLException;
import java.util.List;

import database.DbDependentCarePackage;
import exception.DbLayerException;
import model.DependentCarePackage;

public class DependentCarePackageCtrl {
	private DbDependentCarePackage dbDepCP;

	public DependentCarePackageCtrl() throws SQLException, DbLayerException {
		dbDepCP = new DbDependentCarePackage();
	}

	/**
	 * Gets a list of carepackages connected to a specific dependent
	 * @param dependentId The id of the dependent in the database
	 * @return list of carepackages
	 * @throws SQLException
	 */
	public List<DependentCarePackage> getCarePackagesForDependent(int dependentId) throws SQLException{
		List<DependentCarePackage> depCPList = dbDepCP.findCPIdsForDependentID(dependentId);
		return depCPList;	
	}
}
