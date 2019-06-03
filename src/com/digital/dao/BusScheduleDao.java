package com.digital.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.ScheduleMaster;
import com.digital.model.ScheduleBusDetails;
import com.digital.model.TripDetails;
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
	public List<TripDetails> getTripCitiesBySrcDescCities(Long scheduleId, Integer srcCitySequance,
			Integer destCitySequance) {
		log.debug("Running select query for getTripCitiesBySrcDescCities: {}", selectTripCitiesBySrcDescCities);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("srcCitySequance", srcCitySequance);
		parameters.addValue("destCitySequance", destCitySequance);

		List<TripDetails> routedCities = jdbcTemplateObject.query(selectTripCitiesBySrcDescCities, parameters,
				new BeanPropertyRowMapper<>(TripDetails.class));
		return (routedCities != null && !routedCities.isEmpty()) ? routedCities : new ArrayList<>();
	}

	@Transactional(readOnly = true)
	public List<TripDetails> getTripCitiySequanceByCityId(Long scheduleId, Long cityId) {
		log.debug("Running select query for getTripCitiySequanceByCityId: {}", selectTripCitySequenceByCityId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("cityId", cityId);

		return jdbcTemplateObject.query(selectTripCitySequenceByCityId, parameters,
				new BeanPropertyRowMapper<>(TripDetails.class));
	}
	
	@Transactional(readOnly=true)
	public ScheduleMaster getBusStartTimeByScheduleId(Long scheduleId) {
		log.debug("Running select query for getBusStartTimeByScheduleId: {}", busStartTimeByScheduleId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		List<ScheduleMaster> result = jdbcTemplateObject.query(busStartTimeByScheduleId, parameters,
				new BeanPropertyRowMapper<>(ScheduleMaster.class));
		return result.isEmpty() ? null : result.get(0);
	}
	
	
	@Transactional(readOnly=true)
	public List<Long> getScheduleIdNotExistInTrip() {
		log.debug("Running select query for getBusStartTimeByScheduleId: {}", selectScheduleIdNotExistInTrip);
		List<Long> result = jdbcTemplateObject.query(selectScheduleIdNotExistInTrip, new ResultSetExtractor<List<Long>>() {
			@Override
			public List<Long> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Long> list = new ArrayList<>();
				while (rs.next()) {
					list.add(rs.getLong("ScheduleId"));
				}
				return list;
			}
		});
		return result;
	}
	
	
}
