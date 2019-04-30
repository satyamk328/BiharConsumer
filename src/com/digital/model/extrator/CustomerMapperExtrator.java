package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.digital.model.vo.CustomerBusTicketVO;
/**
 * @author Satyam Kumar
 *
 */
public class CustomerMapperExtrator implements ResultSetExtractor<List<CustomerBusTicketVO>> {

	@Override
	public List<CustomerBusTicketVO> extractData(ResultSet rs) throws SQLException {
		List<CustomerBusTicketVO> customerTicket = new ArrayList<>();
		while (rs.next()) {
			CustomerBusTicketVO customerBusTicketVO = new CustomerBusTicketVO();
			customerBusTicketVO.setBookingid(rs.getString("bookingid"));
			customerBusTicketVO.setUserId(rs.getString("userid"));
			customerBusTicketVO.setBusname(rs.getString("busname"));
			customerBusTicketVO.setBusnumber(rs.getString("busnumber"));
			customerBusTicketVO.setSrccityname(rs.getString("srccityname"));
			customerBusTicketVO.setDestcityname(rs.getString("destcityname"));
			customerBusTicketVO.setArrivaldatetime(rs.getTimestamp("arrivaldatetime").toString());
			customerBusTicketVO.setDeparturedatetime(rs.getTimestamp("departuredatetime").toString());
			customerBusTicketVO.setSeattype(rs.getString("seattype"));
			customerBusTicketVO.setTotalfare(rs.getDouble("totalfare"));
			customerBusTicketVO.setCustomername(rs.getString("customername"));
			customerBusTicketVO.setAge(rs.getInt("age"));
			customerBusTicketVO.setEmail(rs.getString("email"));
			customerBusTicketVO.setIdtype(rs.getString("idtype"));
			customerBusTicketVO.setIdnumber(rs.getString("idnumber"));
			customerBusTicketVO.setIslicence(rs.getBoolean("islicence"));
			customerBusTicketVO.setSeatnumber(rs.getString("seatnumber"));
			customerTicket.add(customerBusTicketVO);
		}
		return customerTicket;
	}

}
