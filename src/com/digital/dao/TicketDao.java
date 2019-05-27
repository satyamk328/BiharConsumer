package com.digital.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.TicketDetails;
import com.digital.model.vo.SeatDataToOperate;
import com.digital.model.vo.TicketVO;

import lombok.extern.slf4j.Slf4j;

@Repository("ticketDao")
@Slf4j
public class TicketDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Value("${select_TicketDetails_By_ScheduleAndBusId}")
	private String selectTicketDetailsByScheduleAndBusId;

	@Value("${insert_cancel_ticket_master_from_ticket_master}")
	private String insertCancelTicketMasterFromTicketMaster;

	@Value("${delete_ticket_master_by_scheduleId_busId_seatId}")
	private String deleteTicketMasterByScheduleIdBusIdSeatId;

	@Transactional(readOnly = true)
	public List<TicketDetails> getTicketDetailsByScheduleAndBusId(Long scheduleId, Long busId) {
		log.debug("Running select query for getTicketDetailsByScheduleAndBusId: {}",
				selectTicketDetailsByScheduleAndBusId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("busId", busId);

		List<TicketDetails> details = jdbcTemplateObject.query(selectTicketDetailsByScheduleAndBusId, parameters,
				new BeanPropertyRowMapper<>(TicketDetails.class));
		return (details != null && !details.isEmpty()) ? details : new ArrayList<>();
	}

	@Transactional
	public int cancelTickets(TicketVO bookTicketVO) {

		log.debug("Running select query for searchTripBySrcDescAndDate: {}", insertCancelTicketMasterFromTicketMaster);
		log.debug("Running select query for searchTripBySrcDescAndDate: {}", deleteTicketMasterByScheduleIdBusIdSeatId);
		// TODO null validation
		// TODO Logic to check cancellation policies
		for (SeatDataToOperate seatData : bookTicketVO.getSeatDataToOperate()) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("scheduleId", bookTicketVO.getScheduleId());
			parameters.addValue("busId", bookTicketVO.getBusId());

			parameters.addValue("seatId", seatData.getSeatId());

			try {
				jdbcTemplateObject.update(insertCancelTicketMasterFromTicketMaster, parameters);
				jdbcTemplateObject.update(deleteTicketMasterByScheduleIdBusIdSeatId, parameters);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	public Integer totalTicketCont(Long scheduleId, Long busId) {
		String sql = "SELECT COUNT(*) FROM ticket_master WHERE ScheduleId =:scheduleId AND BusId =:busId";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("busId", busId);
		return this.jdbcTemplateObject.queryForObject(sql, parameters, Integer.class);
	}
}
