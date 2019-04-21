package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.digital.model.BusCityStopLocationsDetails;
/**
 * @author Satyam Kumar
 *
 */
public class BusStopLocationDetailsRowMapper implements ResultSetExtractor<List<BusCityStopLocationsDetails>> {

	@Override
	public List<BusCityStopLocationsDetails> extractData(ResultSet rs) throws SQLException {/*
		List<BusCityStopLocationsDetails> boadingStopingDetails =  new ArrayList<>();
		while(rs.next()) {
			BusCityStopLocationsDetails locationDetails = new BusCityStopLocationsDetails();
			locationDetails.setId(rs.getString("id"));
			locationDetails.setBusid(rs.getString("tripid"));
			locationDetails.setLocationName(rs.getString("locationname"));
			locationDetails.setLocationAddress(rs.getString("locationaddress"));
			locationDetails.setContactNumber(rs.getString("contactnumber"));
			locationDetails.setLat(rs.getDouble("lat"));
			locationDetails.setLng(rs.getDouble("lng"));
			//locationDetails.setDate(rs.getDate("arrivaldate").toString());
			//locationDetails.setTime(rs.getTime("arrivaltime").toString());
			boadingStopingDetails.add(locationDetails);
		}
		return boadingStopingDetails;
	*/
	return null;
	}
}
