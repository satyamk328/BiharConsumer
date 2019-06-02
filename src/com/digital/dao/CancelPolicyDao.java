package com.digital.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.digital.model.CancelPolicies;
import com.digital.model.TicketCancellationPolicy;

import lombok.extern.slf4j.Slf4j;

@Repository("cancelPolicyDao")
@Slf4j
public class CancelPolicyDao {

	/**
	 * Cancel policy query
	 */
	@Value("${select_bus_cancellationByBusId}")
	private String selectBusCancelPolicyByBusIdQuery;
	
	@Value("${select_bus_cancellation}")
	private String selectBusCancelPolicyQuery;
	
	@Value("${select_cancellation_policy_by_busId}")
	private String selectCancellationtPolicyByBusId;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;
	
	@Transactional(readOnly = true)
	public List<CancelPolicies> getCancelPolicyByBusId(Long busId) {
		log.debug("Running insert query for getCancelPolicyByBusId {}", selectBusCancelPolicyByBusIdQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("busId", busId);
		return jdbcTemplateObject.query(selectBusCancelPolicyByBusIdQuery, parameters, new BeanPropertyRowMapper<>(CancelPolicies.class));
	}
	
	@Transactional(readOnly = true)
	public List<CancelPolicies> getCancelPolicy() {
		log.debug("Running insert query for getCancelPolicy {}", selectBusCancelPolicyByBusIdQuery);
		return jdbcTemplateObject.query(selectBusCancelPolicyQuery, new BeanPropertyRowMapper<>(CancelPolicies.class));
	}
	
	@Transactional(readOnly=true)
	public List<TicketCancellationPolicy> getTicketCancellationPolicy(Long busId) {
		log.debug("Running select query for getTicketCancellationPolicy: {}", selectCancellationtPolicyByBusId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("busId", busId);
		return jdbcTemplateObject.query(selectCancellationtPolicyByBusId, parameters,
				new BeanPropertyRowMapper<>(TicketCancellationPolicy.class));
	}
}
