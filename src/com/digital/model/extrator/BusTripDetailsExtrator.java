package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.digital.model.BusRouteDetails;
/**
 * @author Satyam Kumar
 *
 */
public class BusTripDetailsExtrator implements ResultSetExtractor<List<BusRouteDetails>> {

	@Override
	public List<BusRouteDetails> extractData(ResultSet rs) throws SQLException {

		List<BusRouteDetails> amenitiesList = new ArrayList<>();
		while (rs.next()) {
			BusRouteDetails busRoutDetails = new BusRouteDetails();
			busRoutDetails.setInclTaxes(true);
			busRoutDetails.setClassType("Normal");
			busRoutDetails.setOperatorId(rs.getLong("BusId"));
			busRoutDetails.setProviderId(rs.getString("providerid"));
			busRoutDetails.setTravelsName(rs.getString("travelsName"));
			String busType = (rs.getBoolean("isac") ? "A/C " : "NON AC ").concat(rs.getBoolean("issleeper") ? "Sleaper " : " ").concat(rs.getBoolean("isseater") ? "Seater " : " ").concat(rs.getString("layoutname"));
			busRoutDetails.setBusType(busType);
			busRoutDetails.setIdProofRequired(true);
			busRoutDetails.setAc(rs.getBoolean("isac"));
			busRoutDetails.setRefundable(true);
			busRoutDetails.setSleeper(rs.getBoolean("issleeper"));
			busRoutDetails.setSeater(rs.getBoolean("isseater"));
			busRoutDetails.setDuration(rs.getDouble("duration"));
			busRoutDetails.setDistance(rs.getDouble("distance"));
			busRoutDetails.setRouteId(rs.getString("routeid"));
			busRoutDetails.setTripid(rs.getString("tripid"));
			busRoutDetails.setTotalSeats(rs.getDouble("totalseats"));
			//busRoutDetails.setAvailableSeats(rs.getDouble("availableseats"));
			busRoutDetails.setArrivalDate(rs.getDate("arrivaldate").toString());
			busRoutDetails.setArrivalTime(rs.getTime("arrivaltime").toString());
			busRoutDetails.setDepartureDate(rs.getDate("departuredate").toString());
			busRoutDetails.setDepartureTime(rs.getTime("departuretime").toString());
			busRoutDetails.setSource(rs.getString("source"));
			busRoutDetails.setDestination(rs.getString("destination"));
			List<Double> list = new ArrayList<>();
			list.add(rs.getDouble("basefare"));
			if (rs.getDouble("sleaperFare") != 0) {
				list.add(rs.getDouble("basefare")+rs.getDouble("sleaperFare"));
			}
			if (rs.getDouble("seaterFare") != 0) {
				list.add(rs.getDouble("basefare")+rs.getDouble("seaterFare"));
			}
			busRoutDetails.setFares(list);

			amenitiesList.add(busRoutDetails);
		}
		return amenitiesList;
	}
}
