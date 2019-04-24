package com.digital.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.City;
import com.digital.model.CityStop;
import com.digital.model.extrator.CityRowMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Repository("topCityDao")
@Slf4j
public class CityDao {

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
	@Value("${select_city_stop_by_cityId}")
	private String selectCityStopByCityId;
	@Value("${insert_city_stop}")
	private String insertCityStopQuery;
	@Value("${delete_cityStop_bycityId}")
	private String deleteCityStopQuery;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Transactional(readOnly = true)
	public List<City> getAllCities() {
		log.debug("Running insert query for getAllStation {}", selectAllCityQuery);
		return jdbcTemplateObject.query(selectAllCityQuery, new CityRowMapper());
	}

	@Transactional
	public List<City> getCityByName(String cityName) {
		log.debug("Running insert query for searchStationByStationName {}", selectSearchTopCityQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityName", cityName.toLowerCase());
		return jdbcTemplateObject.query(selectSearchTopCityQuery, parameters, new CityRowMapper());
	}

	@Transactional
	public List<City> getCityById(Long cityId) {
		log.debug("Running insert query for searchStationByStationName {}", selectSearchTopCityByIdQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityId", cityId);
		return jdbcTemplateObject.query(selectSearchTopCityByIdQuery, parameters, new CityRowMapper());
	}

	@Transactional
	public long addCity(City city) {
		log.debug("Running insert query for addStationName {}", insertTopCityQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(city);
		jdbcTemplateObject.update(insertTopCityQuery, parameters, holder);
		return (holder.getKey() == null) ? null : holder.getKey().longValue();
	}

	@Transactional
	public int updateCity(Long cityId, City topCities) {
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
		return jdbcTemplateObject.update(deleteCityQuery, parameters);
	}

	@Transactional(readOnly = true)
	public List<CityStop> getCityStopByCityId(Long cityId) {
		log.debug("Running insert query for getCancellationPolicy {}", selectCityStopByCityId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityId", cityId);
		return jdbcTemplateObject.query(selectCityStopByCityId, parameters, new RowMapper<CityStop>() {
			public CityStop mapRow(ResultSet rs, int rowNum) throws SQLException {
				CityStop cityStop = new CityStop();
				cityStop.setCityStopId(rs.getLong("CityStopId"));
				cityStop.setCityId(rs.getLong("CityId"));
				cityStop.setLocationName(rs.getString("LocationName"));
				cityStop.setLocationAddress(rs.getString("LocationAddress"));
				cityStop.setLandMark(rs.getString("LandMark"));
				cityStop.setLat(rs.getLong("Lat"));
				cityStop.setLng(rs.getLong("Lng"));
				return cityStop;
			}
		});
	}
	
	@Transactional
	public long addCityStop(CityStop cityStop) {
		log.debug("Running insert query for addCityStop {}", insertCityStopQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(cityStop);
		jdbcTemplateObject.update(insertCityStopQuery, parameters, holder);
		return (holder.getKey() == null) ? null : holder.getKey().longValue();
	}
	
	@Transactional
	public int deleteCityStop(Long cityId) {
		log.debug("Running insert query for deleteCityStop {}", deleteCityStopQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityId", cityId);
		return jdbcTemplateObject.update(deleteCityStopQuery, parameters);
	}
}
