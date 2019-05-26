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

import lombok.extern.slf4j.Slf4j;

@Repository("bookingDao")
@Slf4j
public class BookingDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Value("${select_TicketDetails_By_ScheduleAndBusId}")
	private String selectTicketDetailsByScheduleAndBusId;

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

}
