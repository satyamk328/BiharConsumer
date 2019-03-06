package com.digital.audit.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.digital.payment.model.ServiceApiAuditLog;

@Repository
public class ServiceApiResponseLogDao {

	private static final Logger log = LoggerFactory.getLogger(ServiceApiResponseLogDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

    @Value("${service_api_response_log.insert.entry}")
    private String insertApiAuditLogSql;

    public int addLog(ServiceApiAuditLog apiAuditLog) {
    	log.info("call addLog {}", apiAuditLog);
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(apiAuditLog);
        KeyHolder holder = new GeneratedKeyHolder();
        return jdbcTemplate.update(insertApiAuditLogSql, params, holder);

    }
}
