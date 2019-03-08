package com.digital.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
public class CityDao {

	private static final Logger log = LoggerFactory.getLogger(CityDao.class);

	@Value("${select_top_cities}")
	private String selectAllCityQuery;
	@Value("${select_search_top_cities}")
	private String selectSearchTopCityQuery;
	@Value("${insert_top_city}")
	private String insertTopCityQuery;
	@Value("${delete_top_city}")
	private String deleteCityQuery;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<TopCities> getAllStation() {
		log.debug("Running insert query for getAllStation {}", selectAllCityQuery);
		return jdbcTemplate.query(selectAllCityQuery, new TopCityRowMapper());
	}

	@Transactional
	public List<TopCities> searchStationByStationName(String stationName) {
		log.debug("Running insert query for searchStationByStationName {}", selectSearchTopCityQuery);
		return jdbcTemplate.query(selectSearchTopCityQuery, new Object[] { stationName }, new TopCityRowMapper());
	}

	@Transactional
	public String addStationName(TopCities topCities) {
		log.debug("Running insert query for addStationName {}", insertTopCityQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(topCities);
		jdbcTemplate.update(insertTopCityQuery, parameters, holder);
		return (holder.getKey() == null) ? "" : holder.getKey().toString();
	}

	@Transactional
	public boolean deleteCity(String id) {
		log.debug("Running insert query for deleteCity {}", deleteCityQuery);
		int i = jdbcTemplate.update(deleteCityQuery, id);
		return i > 0 ? true : false;
	}
}
