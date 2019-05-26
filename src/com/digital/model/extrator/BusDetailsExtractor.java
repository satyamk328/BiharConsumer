package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.digital.model.BusDetails;

public class BusDetailsExtractor implements ResultSetExtractor<List<BusDetails>>{

	@Override
	public List<BusDetails> extractData(ResultSet rs) throws SQLException {
		List<BusDetails> busDetails = new ArrayList<>();
		
		while (rs.next()) {
			BusDetails busDetail = new BusDetails();
			busDetail.setBusId(rs.getLong("BusId"));
			busDetail.setOwnerId(rs.getLong("ownerId"));
			busDetail.setLayoutId(rs.getLong("layoutId"));
			busDetail.setBusNumber(rs.getString("busNumber"));
			busDetail.setTravelsName(rs.getString("travelsName"));
			busDetail.setColor(rs.getString("color"));
			busDetail.setTotalSeats(rs.getLong("totalSeats"));
			busDetail.setIsAc(rs.getBoolean("IsAc"));
			busDetail.setIsSeater(rs.getBoolean("IsSeater"));
			busDetail.setIsSleeper(rs.getBoolean("IsSleeper"));
			busDetail.setLayout(rs.getString("Layout"));
			busDetail.setLayOutDescription(rs.getString("layOutDescription"));
			String seatType = (rs.getBoolean("IsSeater") ? "Seater " : "").concat(rs.getBoolean("IsSleeper") ? "Sleeper " : "");
			busDetail.setSeatType(seatType);
			String busType = (rs.getBoolean("IsAc") ? "A/C " : "NON AC ").concat(rs.getBoolean("IsSeater") ? "Seater " : " ").concat(rs.getBoolean("IsSleeper") ? "Sleeper " : " ").concat(rs.getString("Layout"));
			busDetail.setBusType(busType);
			busDetails.add(busDetail);
		}
		return busDetails;
	}


}
