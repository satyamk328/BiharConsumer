package com.digital.audit.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.digital.audit.model.ServiceApiAuditLog;
import com.digital.model.TripMaster;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ServiceApiResponseLogDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Value("${service_api_response_log.insert.entry}")
	private String insertApiAuditLogSql;

	@Value("${service_api_response_log.update.entry}")
	private String updateApiAuditLogSql;

	@Value("${service_api_response_log.get.entry}")
	private String getApiAuditLogSql;

	public Long addLog(ServiceApiAuditLog apiAuditLog) {
		log.info("call addLog {}", apiAuditLog);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(apiAuditLog);
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(insertApiAuditLogSql, params, holder);
		return holder.getKey().longValue();
	}

	public int updateLog(ServiceApiAuditLog apiAuditLog) {
		log.info("call addLog {}", apiAuditLog);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(apiAuditLog);
		return jdbcTemplate.update(updateApiAuditLogSql, params);
	}

	public ServiceApiAuditLog getLog(Long logId) {
		log.info("call getLog {}", logId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("logId", logId);
		List<ServiceApiAuditLog> apiAuditLogs = jdbcTemplate.query(getApiAuditLogSql, parameters,
				new BeanPropertyRowMapper<>(ServiceApiAuditLog.class));
		return (apiAuditLogs.isEmpty()) ? new ServiceApiAuditLog() : apiAuditLogs.get(0);
	}
}
