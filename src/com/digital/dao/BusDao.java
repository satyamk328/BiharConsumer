package com.digital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.BusMaster;
import com.digital.model.extrator.BusDetailsExtractor;

import lombok.extern.slf4j.Slf4j;

@Repository("busDao")
@Slf4j
public class BusDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;
	
	@Value("${select_Bus_Details_By_BusId}")
	private String selectBusDetailsByBusId;
	
	@Transactional(readOnly = true)
	public BusMaster getBusDetailsByBusId(Long busId) {
		log.debug("Running select query for getBusDetailsByBusId: {}", selectBusDetailsByBusId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("busId", busId);
		List<BusMaster> busDetails = jdbcTemplateObject.query(selectBusDetailsByBusId, parameters,	new BusDetailsExtractor());
		return (busDetails != null && !busDetails.isEmpty()) ? busDetails.get(0) : new BusMaster();
	}
	
}
