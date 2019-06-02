package com.digital.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.digital.model.BusScheduleDetails;
import com.digital.model.RoutedCity;
import com.digital.model.extrator.BusScheduleDetailsExtrator;
import com.digital.model.vo.SeatDataToOperate;
import com.digital.model.vo.TicketVO;
import com.digital.utils.CommonUtil;
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

	@Value("${insert_ticket_master}")
	private String insertTicketMaster;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Autowired
	private DataUtils dataUtils;
	
	@Transactional(readOnly = true)
	public List<BusScheduleDetails> searchTripBySrcDescAndDate(Long srcCityId, Long destCityId, String date) {
		log.debug("Running select query for searchTripBySrcDescAndDate: {}", selectSearchTripBySrcAndDescDateQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("srcCityId", srcCityId);
		parameters.addValue("destCityId", destCityId);
		parameters.addValue("arrivalDate", dataUtils.convertFormat(date));

		return jdbcTemplateObject.query(selectSearchTripBySrcAndDescDateQuery, parameters,
				new BusScheduleDetailsExtrator());

	}

	@Transactional(readOnly = true)
	public BusScheduleDetails scheduledBusDetails(Long scheduleId, Long busId, Long srcCityId, Long destCityId) {
		log.debug("Running select query for searchTripBySrcDescAndDate: {}",
				selectSearchTripBySchIdBusIdSrcCtyIdDescCtyId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("busId", busId);
		parameters.addValue("srcCityId", srcCityId);
		parameters.addValue("destCityId", destCityId);

		List<BusScheduleDetails> list = jdbcTemplateObject.query(selectSearchTripBySchIdBusIdSrcCtyIdDescCtyId,
				parameters, new BusScheduleDetailsExtrator());

		return (list != null && !list.isEmpty()) ? list.get(0) : new BusScheduleDetails();
	}

	@Transactional
	public synchronized int bookTickets(TicketVO bookTicketVO) {

		log.debug("Running select query for searchTripBySrcDescAndDate: {}", insertTicketMaster);
		
		for (SeatDataToOperate seatData : bookTicketVO.getSeatDataToOperate()) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("scheduleId", bookTicketVO.getScheduleId());
			if (StringUtils.isEmpty(bookTicketVO.getUserId()))
				parameters.addValue("userId", seatData.getCustName().substring(0, 4));
			else
				parameters.addValue("userId", bookTicketVO.getUserId());
			parameters.addValue("busId", bookTicketVO.getBusId());
			parameters.addValue("pnr", bookTicketVO.getPnr());
			parameters.addValue("seatId", seatData.getSeatId());
			parameters.addValue("tripId", bookTicketVO.getTripId());
			parameters.addValue("travelName", bookTicketVO.getTravelName());
			parameters.addValue("busType", bookTicketVO.getBusType());
			parameters.addValue("isAc", bookTicketVO.getIsAC());
			parameters.addValue("boadingPoint", bookTicketVO.getBoadingPoint());
			parameters.addValue("droppingPoint", bookTicketVO.getDroppingPoint());
			parameters.addValue("arrivalDate", bookTicketVO.getArrivalDate());
			parameters.addValue("arrivalTime", bookTicketVO.getArrivalTime());
			parameters.addValue("departureDate", bookTicketVO.getDepartureDate());
			parameters.addValue("departureTime", bookTicketVO.getDepartureTime());
			parameters.addValue("totalFare", bookTicketVO.getTotalFare());
			
			parameters.addValue("seatType", seatData.getSeatType());
			parameters.addValue("seatNumber", seatData.getSeatNumber());
			parameters.addValue("seatName", seatData.getSeatNumber());
			parameters.addValue("isLowerBerth", seatData.getIsLowerBerth());
			
			parameters.addValue("customerName", seatData.getCustName());
			parameters.addValue("age", seatData.getAge());
			parameters.addValue("email", bookTicketVO.getEmail());
			parameters.addValue("gender", seatData.getGender());
			parameters.addValue("phoneNumber", bookTicketVO.getPhone());
			parameters.addValue("isLicence", bookTicketVO.getIsLicence());
			parameters.addValue("bookingDate", bookTicketVO.getBookingDate());
					 
			jdbcTemplateObject.update(insertTicketMaster, parameters);
		}
		return 1;
	}

	@Transactional(readOnly = true)
	public List<RoutedCity> getTripCitiesBySrcDescCities(Long scheduleId, Integer srcCitySequance,
			Integer destCitySequance) {
		log.debug("Running select query for getTripCitiesBySrcDescCities: {}", selectTripCitiesBySrcDescCities);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("srcCitySequance", srcCitySequance);
		parameters.addValue("destCitySequance", destCitySequance);

		List<RoutedCity> routedCities = jdbcTemplateObject.query(selectTripCitiesBySrcDescCities, parameters,
				new BeanPropertyRowMapper<>(RoutedCity.class));
		return (routedCities != null && !routedCities.isEmpty()) ? routedCities : new ArrayList<>();
	}

	@Transactional(readOnly = true)
	public List<RoutedCity> getTripCitiySequanceByCityId(Long scheduleId, Long cityId) {
		log.debug("Running select query for getTripCitiySequanceByCityId: {}", selectTripCitySequenceByCityId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("cityId", cityId);

		return jdbcTemplateObject.query(selectTripCitySequenceByCityId, parameters,
				new BeanPropertyRowMapper<RoutedCity>(RoutedCity.class));
	}
}
