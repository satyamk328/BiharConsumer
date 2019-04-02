package com.digital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class SearchCityDao {

	@Value("${select_search_cities}")
	private String selectAllCityQuery;
	@Value("${select_search_top_cities}")
	private String selectSearchTopCityQuery;
	@Value("${select_search_top_cities_By_Id}")
	private String selectSearchTopCityByIdQuery;
	@Value("${insert_search_city}")
	private String insertTopCityQuery;
	@Value("${delete_top_city}")
	private String deleteCityQuery;
	@Value("${update_top_city}")
	private String updateCityQuery;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Transactional(readOnly = true)
	public List<TopCities> getAllCities() {
		log.debug("Running insert query for getAllStation {}", selectAllCityQuery);
		return jdbcTemplateObject.query(selectAllCityQuery, new TopCityRowMapper());
	}

	@Transactional
	public List<TopCities> getCityByName(String cityName) {
		log.debug("Running insert query for searchStationByStationName {}", selectSearchTopCityQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityName", cityName);
		return jdbcTemplateObject.query(selectSearchTopCityQuery, parameters, new TopCityRowMapper());
	}

	@Transactional
	public List<TopCities> getCityById(Long cityId) {
		log.debug("Running insert query for searchStationByStationName {}", selectSearchTopCityByIdQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityId", cityId);
		return jdbcTemplateObject.query(selectSearchTopCityByIdQuery, parameters, new TopCityRowMapper());
	}
	
	@Transactional
	public long addCity(TopCities city) {
		log.debug("Running insert query for addStationName {}", insertTopCityQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(city);
		jdbcTemplateObject.update(insertTopCityQuery, parameters, holder);
		return (holder.getKey() == null) ? null : holder.getKey().longValue();
	}

	@Transactional
	public int updateCity(Long cityId,TopCities topCities) {
		log.debug("Running insert query for deleteCity {}", updateCityQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("cityId", cityId);
        parameters.addValue("cityName", topCities.getCityName());
        parameters.addValue("displayName", topCities.getDisplayName());
        parameters.addValue("stateName", topCities.getStateName());
        parameters.addValue("district", topCities.getDistrict());
        parameters.addValue("country", topCities.getCountry());
        log.debug("Update TopCities configuration for id: %s", cityId);
		return jdbcTemplateObject.update(deleteCityQuery, parameters);
	}
	
	@Transactional
	public int deleteCity(Long cityId) {
		log.debug("Running insert query for deleteCity {}", deleteCityQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityId", cityId);
		return jdbcTemplateObject.update(deleteCityQuery,  parameters);
	}
}
