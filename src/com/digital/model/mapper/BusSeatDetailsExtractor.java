package com.digital.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.digital.model.BusSeatDetails;
/**
 * @author Satyam Kumar
 *
 */
public class BusSeatDetailsExtractor implements ResultSetExtractor<List<BusSeatDetails>>{

	@Override
	public List<BusSeatDetails> extractData(ResultSet rs) throws SQLException {
		List<BusSeatDetails> busSeatDetails = new ArrayList<>();
		
		while (rs.next()) {
			BusSeatDetails seatDetails = new BusSeatDetails();
			seatDetails.setRow(Integer.parseInt(rs.getString("rowname")));
			seatDetails.setColumn(Integer.parseInt(rs.getString("columnname")));
			seatDetails.setLength(rs.getInt("length"));
			seatDetails.setWidth(rs.getInt("width"));
			seatDetails.setLayoutId(rs.getString("seatlayoutid"));
			seatDetails.setSeatType(rs.getString("seattype"));
			seatDetails.setSeatNumber(rs.getString("seatnumber"));
			seatDetails.setSeatName(rs.getString("seatnumber"));
			seatDetails.setAvailable(rs.getBoolean("isavailable"));
			seatDetails.setLadiesSeat(rs.getBoolean("isladiesseat"));
			seatDetails.setMenSeat(rs.getBoolean("ismenseat"));
			seatDetails.setLowerBerth(rs.getBoolean("islowerberth"));
			//seatDetails.setReservedForLadies(rs.getBoolean("isreservedforladies"));
			seatDetails.setFare(rs.getDouble("fare"));
			seatDetails.setServiceTaxPercent(0);
			busSeatDetails.add(seatDetails);
		}
		return busSeatDetails;
	}

}
