package com.digital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.TopCities;
import com.digital.model.mapper.TopCityRowMapper;
/**
 * @author Satyam Kumar
 *
 */
@Repository("topCityDao")
public class TopCityDao {

	private static final Logger log = LoggerFactory.getLogger(TopCityDao.class);

	@Value("${select_top_cities}")
    private String selectAllCityQuery;
	@Value("${select_search_top_cities}")
    private String selectSearchTopCityQuery;
	@Value("${insert_top_city}")
    private String insertTopCityQuery;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<TopCities> getAllStation() {
		log.debug("Running insert query for getAllStation {}", selectAllCityQuery);
		return jdbcTemplate.query(selectAllCityQuery, new TopCityRowMapper());
	}

	@Transactional
	public List<TopCities> searchStationByStationName(String stationName) {
		log.debug("Running insert query for getAllStation {}", selectSearchTopCityQuery);
		return jdbcTemplate.query(selectSearchTopCityQuery, new Object[] { "%" + stationName + "%" }, new TopCityRowMapper());
	}

	@Transactional
	public String addStationName(TopCities topCities) {
		log.debug("Running insert query for addStationName {}", insertTopCityQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		//BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(topCities);
		//jdbcTemplate.update(query, parameters, holder);
		//return (holder.getKey() == null) ? 0 : holder.getKey().longValue();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertTopCityQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, topCities.getCityName());
				ps.setString(2, topCities.getDisplayName());
				ps.setString(3, topCities.getStateName());
				ps.setString(4, topCities.getCountry());
				return ps;
			}
		}, holder);
		return (String) holder.getKeys().get("refnumber");
	}
}
