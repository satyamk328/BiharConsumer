package com.digital.audit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.digital.audit.model.ServiceApiAuditLog;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ServiceApiResponseLogDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
    @Value("${service_api_response_log.insert.entry}")
    private String insertApiAuditLogSql;

    public int addLog(ServiceApiAuditLog apiAuditLog) {
    	log.info("call addLog {}", apiAuditLog);
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(apiAuditLog);
        KeyHolder holder = new GeneratedKeyHolder();
        return jdbcTemplate.update(insertApiAuditLogSql, params, holder);
    }
}
