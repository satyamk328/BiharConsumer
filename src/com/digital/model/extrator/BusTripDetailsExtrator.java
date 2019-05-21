package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.digital.model.BusScheduleDetails;

/**
 * @author Satyam Kumar
 *
 */
public class BusTripDetailsExtrator implements ResultSetExtractor<List<BusScheduleDetails>> {

	@Override
	public List<BusScheduleDetails> extractData(ResultSet rs) throws SQLException {

		List<BusScheduleDetails> busScheduleDetails = new ArrayList<>();
		while (rs.next()) {
			BusScheduleDetails busRoutDetails = new BusScheduleDetails();
			busRoutDetails.setClassType("Normal");
			busRoutDetails.setScheduleId(rs.getLong("scheduleId"));
			busRoutDetails.setBusId(rs.getLong("busId"));
			busRoutDetails.setSleeperFare(rs.getDouble("sleeperFare"));
			busRoutDetails.setSeaterFare(rs.getDouble("seaterFare"));
			busRoutDetails.setIsFixedFare(rs.getInt("isFixedFare"));
			busRoutDetails.setSourceId(rs.getString("srcCity"));
			busRoutDetails.setDestinationId(rs.getString("destCity"));
			
			busRoutDetails.setSrcCitySequance(rs.getInt("srcCitySequance"));
			busRoutDetails.setDestCitySequance(rs.getInt("destCitySequance"));
			
			busRoutDetails.setSrcStops(rs.getString("srcStops"));
			busRoutDetails.setDestStops(rs.getString("destStops"));

			busRoutDetails.setDepartureDate(
					rs.getDate("departureDate") != null ? rs.getDate("departureDate").toString() : "");
			busRoutDetails.setDepartureTime(
					rs.getTime("departureTime") != null ? rs.getDate("departureTime").toString() : "");
			busRoutDetails.setArrivalDate(
					rs.getDate("arrivalDate") != null ? rs.getDate("arrivalDate").toString() : "");
			busRoutDetails.setArrivalTime(
					rs.getTime("arrivalTime") != null ? rs.getDate("arrivalTime").toString() : "");

			busScheduleDetails.add(busRoutDetails);
		}
		return busScheduleDetails;
	}
}
