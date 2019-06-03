package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.digital.model.ScheduleBusDetails;
import com.digital.utils.CommonUtil;

/**
 * @author Satyam Kumar
 *
 */
public class BusScheduleDetailsExtrator implements ResultSetExtractor<List<ScheduleBusDetails>> {

	@Override
	public List<ScheduleBusDetails> extractData(ResultSet rs) throws SQLException {

		List<ScheduleBusDetails> busScheduleDetails = new ArrayList<>();
		while (rs.next()) {
			ScheduleBusDetails busRoutDetails = new ScheduleBusDetails();
			busRoutDetails.setClassType("Normal");
			busRoutDetails.setScheduleId(rs.getLong("scheduleId"));
			busRoutDetails.setBusId(rs.getLong("busId"));
			busRoutDetails.setSleeperFare(rs.getDouble("sleeperFare"));
			busRoutDetails.setSeaterFare(rs.getDouble("seaterFare"));
			busRoutDetails.setFares(Arrays.asList(rs.getDouble("seaterFare"), rs.getDouble("sleeperFare")));
			busRoutDetails.setIsFixedFare(rs.getInt("isFixedFare"));
			busRoutDetails.setSourceId(rs.getLong("srcCityId"));
			busRoutDetails.setDestinationId(rs.getLong("destCityId"));

			busRoutDetails.setSrcCitySequance(rs.getInt("srcCitySequance"));
			busRoutDetails.setDestCitySequance(rs.getInt("destCitySequance"));

			busRoutDetails.setSrcStops(rs.getString("srcStops"));
			busRoutDetails.setDestStops(rs.getString("destStops"));

			busRoutDetails.setDepartureDate(
					rs.getDate("departureDate") != null ? rs.getDate("departureDate").toString() : "");
			busRoutDetails.setDepartureTime(CommonUtil.convert24HrsTo12Hrs(rs.getString("departureTime")));
			busRoutDetails
					.setArrivalDate(rs.getDate("arrivalDate") != null ? rs.getDate("arrivalDate").toString() : "");
			busRoutDetails.setArrivalTime(CommonUtil.convert24HrsTo12Hrs(rs.getString("arrivalTime")));

			busScheduleDetails.add(busRoutDetails);
		}
		return busScheduleDetails;
	}
}
