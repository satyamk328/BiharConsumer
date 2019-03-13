package com.digital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.TopCities;
import com.digital.model.extrator.TopCityRowMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Repository("topCityDao")
@Slf4j
public class CityDao {

	@Value("${select_top_cities}")
	private String selectAllCityQuery;
	@Value("${select_search_top_cities}")
	private String selectSearchTopCityQuery;
	@Value("${select_search_top_cities_By_Id}")
	private String selectSearchTopCityByIdQuery;
	@Value("${insert_top_city}")
	private String insertTopCityQuery;
	@Value("${delete_top_city}")
	private String deleteCityQuery;
	@Value("${update_top_city}")
	private String updateCityQuery;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

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
	public List<TopCities> getCityById(long id) {
		log.debug("Running insert query for searchStationByStationName {}", selectSearchTopCityByIdQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityId", id);
		return jdbcTemplateObject.query(selectSearchTopCityByIdQuery, parameters, new TopCityRowMapper());
	}
	
	@Transactional
	public long addCity(TopCities topCities) {
		log.debug("Running insert query for addStationName {}", insertTopCityQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(topCities);
		jdbcTemplateObject.update(insertTopCityQuery, parameters, holder);
		return (holder.getKey() == null) ? null : holder.getKey().intValue();
	}

	@Transactional
	public int updateCity(long id,TopCities topCities) {
		log.debug("Running insert query for deleteCity {}", updateCityQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("cityId", id);
        parameters.addValue("cityName", topCities.getCityName());
        parameters.addValue("displayName", topCities.getDisplayName());
        parameters.addValue("stateName", topCities.getStateName());
        parameters.addValue("district", topCities.getDistrict());
        parameters.addValue("country", topCities.getCountry());
        log.debug("Update TopCities configuration for id: %s", id);
		return jdbcTemplateObject.update(deleteCityQuery, parameters);
	}
	
	@Transactional
	public int deleteCity(long id) {
		log.debug("Running insert query for deleteCity {}", deleteCityQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityId", id);
		return jdbcTemplateObject.update(deleteCityQuery,  parameters);
	}
}
