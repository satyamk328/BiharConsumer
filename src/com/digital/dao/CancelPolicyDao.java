package com.digital.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.digital.model.BusCancellationPolicies;
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
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;
	
	@Transactional(readOnly = true)
	public List<BusCancellationPolicies> getCancelPolicyByBusId(Long busId) {
		log.debug("Running insert query for getCancelPolicyByBusId {}", selectBusCancelPolicyByBusIdQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("busId", busId);
		return jdbcTemplateObject.query(selectBusCancelPolicyByBusIdQuery, parameters, new BeanPropertyRowMapper<>(BusCancellationPolicies.class));
	}
	
	@Transactional(readOnly = true)
	public List<BusCancellationPolicies> getCancelPolicy() {
		log.debug("Running insert query for getCancelPolicy {}", selectBusCancelPolicyByBusIdQuery);
		return jdbcTemplateObject.query(selectBusCancelPolicyQuery, new BeanPropertyRowMapper<>(BusCancellationPolicies.class));
	}
	
}
