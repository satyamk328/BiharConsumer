package com.digital.audit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.digital.audit.model.CentralizedLogsVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AuditDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${central_log_insert_query}")
	private String centralLogInsertQuery;

	public int addCentralizedLog(final CentralizedLogsVO centralizedLogs) {
		log.info("call addCentralizedLog {}", centralizedLogs);
		final String sql = " insert into central_logs(AppName, LogLevel, LogTimeStamp, LogMessage)  "
				+ " values( :appName, :logLevel, :logTimeStamp, :logMessage)";
		final KeyHolder holder = new GeneratedKeyHolder();
		final BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(centralizedLogs);
		jdbcTemplate.update(sql, params, holder);
		return (holder.getKey() == null) ? 0 : holder.getKey().intValue();
	}

}
