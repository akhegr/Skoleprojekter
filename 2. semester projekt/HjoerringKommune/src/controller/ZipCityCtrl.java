package controller;

import java.sql.SQLException;

import database.DbZipCity;
import exception.DbLayerException;
import model.ZipCity;


public class ZipCityCtrl {
	private DbZipCity dbZipCity;
	
	public ZipCityCtrl() throws SQLException, DbLayerException {
		dbZipCity = new DbZipCity();
	}
	
	/**
	 * Finds a ZipCity based on its id in the database
	 * @param id The id in the database
	 * @return object containing zipcode and city
	 * @throws SQLException
	 */
	public ZipCity findById(int id) throws SQLException {
		return dbZipCity.findById(id);
	}
	
	/**
	 * Finds a ZipCity based on its zipCode
	 * @param zipCode The city's zipcode
	 * @return object containing zipcode and city
	 * @throws SQLException
	 */
	public ZipCity findByZipCode(String zipCode) throws SQLException {
		return dbZipCity.findByZipCode(zipCode);
	}
}
