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

import com.digital.model.SeatDetails;

import lombok.extern.slf4j.Slf4j;

@Repository("seatDao")
@Slf4j
public class SeatDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Value("${select_SeatDetails_By_LayoutId}")
	private String selectSeatDetailsByLayoutId;

	@Transactional(readOnly = true)
	public List<SeatDetails> getSeatDetailsByLayoutId(Long layoutId) {
		log.debug("Running select query for getSeatDetailsByLayoutId: {}", selectSeatDetailsByLayoutId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("layoutId", layoutId);

		List<SeatDetails> seatDetails = jdbcTemplateObject.query(selectSeatDetailsByLayoutId, parameters,
				new BeanPropertyRowMapper<SeatDetails>(SeatDetails.class));
		return (seatDetails != null && !seatDetails.isEmpty()) ? seatDetails : new ArrayList<>();
	}

}
