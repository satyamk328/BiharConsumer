package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.digital.model.BusAmenity;
/**
 * @author Satyam Kumar
 *
 */
public class BusAmenitiesExtractor implements ResultSetExtractor<List<BusAmenity>> {

	@Override
	public List<BusAmenity> extractData(ResultSet rs) throws SQLException {/*
		List<BusAmenity> amenitiesList = new ArrayList<>();
		while (rs.next()) {
			BusAmenity busAmenities = new BusAmenity();
			busAmenities.setId(rs.getString("id"));
			busAmenities.setLabel(rs.getString("label"));
			busAmenities.setName(rs.getString("name"));
			busAmenities.setIcon(rs.getString("icon"));
			amenitiesList.add(busAmenities);
		}
		return amenitiesList;
	*/
	return null;	
	}

}
