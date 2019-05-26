package com.digital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.BusDetails;
import com.digital.model.BusScheduleDetails;
import com.digital.model.RoutedCity;
import com.digital.model.SeatDetails;
import com.digital.model.TicketDetails;
import com.digital.model.extrator.BusDetailsExtractor;
import com.digital.model.extrator.BusTripDetailsExtrator;
import com.digital.model.vo.SeatDataToOperate;
import com.digital.model.vo.TicketVO;
import com.digital.utils.DataUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Repository("busRoutDao")
@Slf4j
public class BusTripDao {

	@Value("${select_trip_by_city}")
	private String selectSearchTripBySrcAndDescDateQuery;

	@Value("${select_TripBy_SchId_BusId_SrcCtyId_DescCtyId}")
	private String selectSearchTripBySchIdBusIdSrcCtyIdDescCtyId;

	

	@Value("${select_businfomation_detail}")
	private String selectBusInfoQuery;
	@Value("${insert_customer_ticket}")
	private String insertCustomerBookTicketQuery;
	@Value("${select_bus_seat_details}")
	private String selectBusSeatDetailsQuery;
	@Value("${select_customer_book_ticket}")
	private String selectCustomerBookTicketQuery;

	/////
	@Value("${select_Bus_Details_By_BusId}")
	private String selectBusDetailsByBusId;

	@Value("${select_SeatDetails_By_LayoutId}")
	private String selectSeatDetailsByLayoutId;

	@Value("${select_TicketDetails_By_ScheduleAndBusId}")
	private String selectTicketDetailsByScheduleAndBusId;

	@Value("${select_TripCities_BySrcDescCities}")
	private String selectTripCitiesBySrcDescCities;

	@Value("${select_TripCitySequence_ByCityId}")
	private String selectTripCitySequenceByCityId;

	@Value("${insert_ticket_master}")
	private String insertTicketMaster;

	/////////////
	@Value("${insert_cancel_ticket_master_from_ticket_master}")
	private String insertCancelTicketMasterFromTicketMaster;

	@Value("${delete_ticket_master_by_scheduleId_busId_seatId}")
	private String deleteTicketMasterByScheduleIdBusIdSeatId;

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
				new BusTripDetailsExtrator());

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
				parameters, new BusTripDetailsExtrator());

		return list != null && !list.isEmpty() ? list.get(0) : null;
	}


	@Transactional
	public synchronized int bookTickets(TicketVO bookTicketVO) {

		log.debug("Running select query for searchTripBySrcDescAndDate: {}", insertTicketMaster);
		// TODO null validation
		// TODO check if any sheet is already booked from list
		for (SeatDataToOperate seatData : bookTicketVO.getSeatDataToOperate()) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("scheduleId", bookTicketVO.getScheduleId());
			parameters.addValue("userId", bookTicketVO.getUserId());
			parameters.addValue("busId", bookTicketVO.getBusId());
			parameters.addValue("tripId", bookTicketVO.getTripId());
			parameters.addValue("busType", bookTicketVO.getBusType());
			parameters.addValue("isAc", bookTicketVO.getIsAC());

			parameters.addValue("PNR", dataUtils.getPNRNumber(String.valueOf(bookTicketVO.getUserId()), 0L, 0L, 0L));
			parameters.addValue("seatType", seatData.getSeatType());
			parameters.addValue("seatNumber", seatData.getSeatNumber());
			parameters.addValue("isLowerBerth", seatData.getIsLowerBerth());
			parameters.addValue("totalFare", seatData.getTotalFare());
			parameters.addValue("seatId", seatData.getSeatId());
			jdbcTemplateObject.update(insertTicketMaster, parameters);
		}
		return 1;

	}

	@Transactional
	public int cancelTickets(TicketVO bookTicketVO) {

		log.debug("Running select query for searchTripBySrcDescAndDate: {}", insertCancelTicketMasterFromTicketMaster);
		log.debug("Running select query for searchTripBySrcDescAndDate: {}", deleteTicketMasterByScheduleIdBusIdSeatId);
		// TODO null validation
		// TODO Logic to check cancellation policies
		for (SeatDataToOperate seatData : bookTicketVO.getSeatDataToOperate()) {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("scheduleId", bookTicketVO.getScheduleId());
			parameters.addValue("busId", bookTicketVO.getBusId());

			parameters.addValue("seatId", seatData.getSeatId());

			try {
				jdbcTemplateObject.update(insertCancelTicketMasterFromTicketMaster, parameters);
				jdbcTemplateObject.update(deleteTicketMasterByScheduleIdBusIdSeatId, parameters);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return 1;

	}

	
	@Transactional(readOnly = true)
	public BusDetails getBusDetailsByBusId(Long busId) {
		log.debug("Running select query for getBusDetailsByBusId: {}", selectBusDetailsByBusId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("busId", busId);
		List<BusDetails> busDetails = jdbcTemplateObject.query(selectBusDetailsByBusId, parameters,	new BusDetailsExtractor());
		return (busDetails != null && !busDetails.isEmpty()) ? busDetails.get(0) : new BusDetails();
	}

	@Transactional(readOnly = true)
	public List<SeatDetails> getSeatDetailsByLayoutId(Long layoutId) {
		log.debug("Running select query for getSeatDetailsByLayoutId: {}", selectSeatDetailsByLayoutId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("layoutId", layoutId);

		return jdbcTemplateObject.query(selectSeatDetailsByLayoutId, parameters,
				new BeanPropertyRowMapper<SeatDetails>(SeatDetails.class));
	}

	@Transactional(readOnly = true)
	public List<TicketDetails> getTicketDetailsByScheduleAndBusId(Long scheduleId, Long busId) {
		log.debug("Running select query for getTicketDetailsByScheduleAndBusId: {}",
				selectTicketDetailsByScheduleAndBusId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("busId", busId);

		return jdbcTemplateObject.query(selectTicketDetailsByScheduleAndBusId, parameters,
				new BeanPropertyRowMapper<TicketDetails>(TicketDetails.class));
	}

	@Transactional(readOnly = true)
	public List<RoutedCity> getTripCitiesBySrcDescCities(Long scheduleId, Integer srcCitySequance,
			Integer destCitySequance) {
		log.debug("Running select query for getTripCitiesBySrcDescCities: {}", selectTripCitiesBySrcDescCities);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("srcCitySequance", srcCitySequance);
		parameters.addValue("destCitySequance", destCitySequance);

		return jdbcTemplateObject.query(selectTripCitiesBySrcDescCities, parameters,
				new BeanPropertyRowMapper<RoutedCity>(RoutedCity.class));
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
