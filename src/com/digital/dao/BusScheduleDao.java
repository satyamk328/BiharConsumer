package com.digital.dao;

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
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.ScheduleBusDetails;
import com.digital.model.ScheduleMaster;
import com.digital.model.TripMaster;
import com.digital.model.extrator.BusScheduleDetailsExtrator;
import com.digital.utils.DataUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Repository("busTripDao")
@Slf4j
public class BusScheduleDao {

	@Value("${select_trip_by_city}")
	private String selectSearchTripBySrcAndDescDateQuery;

	@Value("${select_TripBy_SchId_BusId_SrcCtyId_DescCtyId}")
	private String selectSearchTripBySchIdBusIdSrcCtyIdDescCtyId;

	@Value("${select_TripCities_BySrcDescCities}")
	private String selectTripCitiesBySrcDescCities;

	@Value("${select_TripCitySequence_ByCityId}")
	private String selectTripCitySequenceByCityId;

	@Value("${select_bus_StartTime_by_scheduleId}")
	private String busStartTimeByScheduleId;

	@Value("${select_scheduleIs_not_exist_in_tripmaster}")
	private String selectScheduleIdNotExistInTrip;

	@Value("${select_latest_Trip_by_SrcCity_and_DestCity}")
	private String selectLatestTripBySrcCityAndDestCity;

	@Value("${select_trip_master_by_scheduleId}")
	private String selectTripMasterByScheduleId;

	@Value("${insert_trip_master}")
	private String insertTripMasterQuery;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Autowired
	private DataUtils dataUtils;

	@Transactional(readOnly = true)
	public List<ScheduleBusDetails> searchTripBySrcDescAndDate(Long srcCityId, Long destCityId, String date) {
		log.debug("Running select query for searchTripBySrcDescAndDate: {}", selectSearchTripBySrcAndDescDateQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("srcCityId", srcCityId);
		parameters.addValue("destCityId", destCityId);
		parameters.addValue("arrivalDate", dataUtils.convertFormat(date));

		return jdbcTemplateObject.query(selectSearchTripBySrcAndDescDateQuery, parameters,
				new BusScheduleDetailsExtrator());
	}

	@Transactional(readOnly = true)
	public ScheduleBusDetails scheduledBusDetails(Long scheduleId, Long busId, Long srcCityId, Long destCityId) {
		log.debug("Running select query for searchTripBySrcDescAndDate: {}",
				selectSearchTripBySchIdBusIdSrcCtyIdDescCtyId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("busId", busId);
		parameters.addValue("srcCityId", srcCityId);
		parameters.addValue("destCityId", destCityId);

		List<ScheduleBusDetails> list = jdbcTemplateObject.query(selectSearchTripBySchIdBusIdSrcCtyIdDescCtyId,
				parameters, new BusScheduleDetailsExtrator());
		return (list != null && !list.isEmpty()) ? list.get(0) : new ScheduleBusDetails();
	}

	@Transactional(readOnly = true)
	public List<TripMaster> getTripCitiesBySrcDescCities(Long scheduleId, Integer srcCitySequance,
			Integer destCitySequance) {
		log.debug("Running select query for getTripCitiesBySrcDescCities: {}", selectTripCitiesBySrcDescCities);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("srcCitySequance", srcCitySequance);
		parameters.addValue("destCitySequance", destCitySequance);

		List<TripMaster> routedCities = jdbcTemplateObject.query(selectTripCitiesBySrcDescCities, parameters,
				new BeanPropertyRowMapper<>(TripMaster.class));
		return (routedCities != null && !routedCities.isEmpty()) ? routedCities : new ArrayList<>();
	}

	@Transactional(readOnly = true)
	public List<TripMaster> getTripCitiySequanceByCityId(Long scheduleId, Long cityId) {
		log.debug("Running select query for getTripCitiySequanceByCityId: {}", selectTripCitySequenceByCityId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("cityId", cityId);

		return jdbcTemplateObject.query(selectTripCitySequenceByCityId, parameters,
				new BeanPropertyRowMapper<>(TripMaster.class));
	}

	@Transactional(readOnly = true)
	public List<ScheduleMaster> getBusStartTimeByScheduleId(Long scheduleId) {
		log.debug("Running select query for getBusStartTimeByScheduleId: {}", busStartTimeByScheduleId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		List<ScheduleMaster> result = jdbcTemplateObject.query(busStartTimeByScheduleId, parameters,
				new BeanPropertyRowMapper<>(ScheduleMaster.class));
		return result;
	}

	@Transactional(readOnly = true)
	public List<ScheduleMaster> getScheduleIdNotExistInTrip() {
		log.debug("Running select query for getBusStartTimeByScheduleId: {}", selectScheduleIdNotExistInTrip);
		return jdbcTemplateObject.query(selectScheduleIdNotExistInTrip,
				new BeanPropertyRowMapper<>(ScheduleMaster.class));
	}

	@Transactional(readOnly = true)
	public Long getLatestTripBySrcCityAndDestCityId(Long srcCityId, Long destCityId) {
		log.debug("Running select query for getTripMasterByBusId: {}", selectLatestTripBySrcCityAndDestCity);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("srcCityId", srcCityId);
		parameters.addValue("destCityId", destCityId);
		return jdbcTemplateObject.queryForObject(selectLatestTripBySrcCityAndDestCity, parameters, Long.class);
	}

	@Transactional(readOnly = true)
	public List<TripMaster> getTripMasterByScheduleId(Long scheduleId) {
		log.debug("Running select query for getTripMasterByBusId: {}", selectTripMasterByScheduleId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		return jdbcTemplateObject.query(selectTripMasterByScheduleId, parameters,
				new BeanPropertyRowMapper<>(TripMaster.class));
	}

	@Transactional
	public int insertTripMaster(TripMaster tripMaster) {
		log.debug("Running select query for getTripMasterByBusId: {}", insertTripMasterQuery);
		final KeyHolder holder = new GeneratedKeyHolder();
		final BeanPropertySqlParameterSource beanParameters = new BeanPropertySqlParameterSource(tripMaster);
		return jdbcTemplateObject.update(insertTripMasterQuery, beanParameters, holder);
	}
}
