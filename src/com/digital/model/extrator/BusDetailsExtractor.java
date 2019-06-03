package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.digital.model.BusMaster;

public class BusDetailsExtractor implements ResultSetExtractor<List<BusMaster>> {

	@Override
	public List<BusMaster> extractData(ResultSet rs) throws SQLException {
		List<BusMaster> busDetails = new ArrayList<>();

		while (rs.next()) {
			BusMaster busDetail = new BusMaster();
			busDetail.setBusId(rs.getLong("BusId"));
			busDetail.setOwnerId(rs.getLong("ownerId"));
			busDetail.setLayoutId(rs.getLong("layoutId"));
			busDetail.setBusNumber(rs.getString("busNumber"));
			busDetail.setTravelsName(rs.getString("travelsName"));
			busDetail.setColor(rs.getString("color"));
			busDetail.setIsAc(rs.getBoolean("IsAc"));
			busDetail.setIsSeater(rs.getBoolean("IsSeater"));
			busDetail.setIsSleeper(rs.getBoolean("IsSleeper"));
			busDetail.setLayout(rs.getString("Layout"));
			busDetail.setLayOutDescription(rs.getString("layOutDescription"));
			String seat = rs.getBoolean("IsSeater") ? "Seater/" : "";
			String sl = rs.getBoolean("IsSleeper") ? "Sleeper " : "";
			String seatType = sl.equals("") ? seat.substring(0, seat.length() - 1) : seat.concat(sl);
			busDetail.setSeatType(seatType);
			String busType = (rs.getBoolean("IsAc") ? "A/C " : "NON AC ")
					.concat(seatType).concat(rs.getString("Layout"));
			busDetail.setBusType(busType);
			busDetails.add(busDetail);
		}
		return busDetails;
	}

}
