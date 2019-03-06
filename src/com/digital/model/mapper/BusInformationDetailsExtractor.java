package com.digital.model.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.digital.model.BusDetails;
/**
 * @author Satyam Kumar
 *
 */
public class BusInformationDetailsExtractor implements ResultSetExtractor<List<BusDetails>> {

	@Override
	public List<BusDetails> extractData(ResultSet rs) throws SQLException {
		List<BusDetails> busDetails = new ArrayList<>();
		while (rs.next()) {
			BusDetails busDetail = new BusDetails();
			busDetail.setBusId(rs.getString("busid"));
			busDetail.setOwnerId(rs.getString("ownerid"));
			busDetail.setTravelsName(rs.getString("busname"));
			busDetail.setRouteId(rs.getString("name"));
			busDetail.setRouteName(rs.getString("icon"));
			busDetail.setBusType(rs.getString("icon"));
			busDetail.setBusTypeName(rs.getString("icon"));
			busDetail.setBookingDate(rs.getString("icon"));
			busDetail.setSourceStationId(rs.getString("sourceid"));
			busDetail.setDestinationStationId(rs.getString("destinationid"));
			busDetail.setAc(rs.getBoolean(""));
			busDetails.add(busDetail);
		}
		return busDetails;
	}

}