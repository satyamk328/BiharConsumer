package com.digital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.BusCancellationPolicies;
import com.digital.model.BusCityStopLocationsDetails;
import com.digital.model.BusDetails;
import com.digital.model.BusScheduleDetails;
import com.digital.model.BusSeatBookingDetails;
import com.digital.model.BusType;
import com.digital.model.RoutedCities;
import com.digital.model.SeatDetails;
import com.digital.model.TicketDetails;
import com.digital.model.extrator.BusInformationDetailsExtractor;
import com.digital.model.extrator.BusSeatDetailsExtractor;
import com.digital.model.extrator.BusTripDetailsExtrator;
import com.digital.model.extrator.CustomerMapperExtrator;
import com.digital.model.vo.CustomerBusTicketVO;
import com.digital.model.vo.SearchTripVO;
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
	@Value("${select_boadingstopping_details}")
	private String selectBoadingStoppingDetailQuery;
	@Value("${select_bustype}")
	private String selectBusTypeQuery;
	@Value("${select_bus_cancellation}")
	private String selectBusCancellationPolicyQuery;

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

	@Autowired
	private JdbcTemplate jdbcTemplate;

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
	public List<BusCityStopLocationsDetails> getBusBoadingAndStopingPointDetails(String cityId,
			List<String> cityStopIds) {
		log.debug("Running select query for getBusBoadingAndStopingPointDetails: {}", selectBoadingStoppingDetailQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityId", Long.parseLong(cityId));
		parameters.addValue("cityStopIds", cityStopIds);
		return jdbcTemplateObject.query(selectBoadingStoppingDetailQuery, parameters,
				new BeanPropertyRowMapper<BusCityStopLocationsDetails>(BusCityStopLocationsDetails.class));
	}

	@Transactional(readOnly = true)
	public List<BusType> getBusType() {
		log.debug("Running insert query for getBusType {}", selectBusTypeQuery);
		return jdbcTemplate.query(selectBusTypeQuery, new RowMapper<BusType>() {
			public BusType mapRow(ResultSet rs, int rowNum) throws SQLException {
				BusType busType = new BusType();
				busType.setId(rs.getInt("id"));
				busType.setBusType(rs.getString("bustype"));
				return busType;
			}

		});
	}

	@Transactional(readOnly = true)
	public List<BusCancellationPolicies> getCancellationPolicy(Long busId) {
		log.debug("Running insert query for getCancellationPolicy {}", selectBusCancellationPolicyQuery);

		return jdbcTemplate.query(selectBusCancellationPolicyQuery, new Object[] { busId },
				new BeanPropertyRowMapper<BusCancellationPolicies>(BusCancellationPolicies.class));
	}

	@Transactional(readOnly = true)
	public List<BusDetails> getBusDetails(String source, String destination) {
		log.debug("Running insert query for getBusDetails {}", selectBusInfoQuery);
		return jdbcTemplate.query(selectBusInfoQuery, new Object[] { "%" + source + "%", "%" + destination + "%" },
				new BusInformationDetailsExtractor());
	}

	@Transactional(readOnly = true)
	public List<BusSeatBookingDetails> getSeatsDetails(SearchTripVO tripVO) {
		log.debug("Running select query for getSeatsDetails: {}", selectBusSeatDetailsQuery);
		Integer startStop = Integer.parseInt(tripVO.getTripId().split("::")[2]);
		Integer endStop = Integer.parseInt(tripVO.getTripId().split("::")[3]);
		return jdbcTemplate.query(selectBusSeatDetailsQuery,
				new Object[] { startStop, endStop, startStop, endStop, tripVO.getOperatorId().toLowerCase() },
				new BusSeatDetailsExtractor());
	}

	@Transactional
	public CustomerBusTicketVO bookedBusTicket(CustomerBusTicketVO busVO) {
		log.debug("Running insert query for bookedBusTicket: {}", insertCustomerBookTicketQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertCustomerBookTicketQuery,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, busVO.getUserid());
				ps.setString(2, busVO.getBusname());
				ps.setString(3, busVO.getBusnumber());
				ps.setString(4, busVO.getSeatnumber());
				ps.setString(5, busVO.getSrccityname());
				ps.setString(6, busVO.getDestcityname());
				ps.setString(7, busVO.getArrivaldatetime());
				ps.setString(8, busVO.getDeparturedatetime());
				ps.setString(9, busVO.getSeattype());
				ps.setDouble(10, busVO.getTotalfare());
				ps.setString(11, busVO.getCustomername());
				ps.setInt(12, busVO.getAge());
				ps.setString(13, busVO.getEmail());
				ps.setString(13, busVO.getPhonenumber());
				ps.setString(15, busVO.getIdtype());
				ps.setString(16, busVO.getIdnumber());
				ps.setBoolean(17, busVO.isIslicence());
				return ps;
			}
		}, holder);
		return (CustomerBusTicketVO) holder.getKeys();
	}

	public List<CustomerBusTicketVO> getHistoryBusTicket(String uid, int limit) {
		log.debug("Running insert query for getHistoryBusTicket: {}", selectCustomerBookTicketQuery);
		return jdbcTemplate.query(selectCustomerBookTicketQuery, new Object[] { uid.toLowerCase(), limit },
				new CustomerMapperExtrator());
	}

	public Map<String, String> scheduleDeparture(// BusDAO bus
	) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());

		return null;
	}

	public Map<String, String> editScheduleDeparture(// BusDAO busObj, BusDAO oldBusObj
	) {
		return null;
	}

	private List<String> getCityIds(String fromCity, String toCity) {
		List<String> cityIds = new ArrayList<String>();
		String queryForCityIds = "SELECT from_city_id, to_city_id FROM (SELECT cityid as from_city_id FROM top_cities WHERE cityname = ?) as fc, (SELECT cityid as to_city_id FROM top_cities WHERe cityname = ?) as tc";
		SqlRowSet cityIdsRowSet = jdbcTemplate.queryForRowSet(queryForCityIds, fromCity, toCity);

		while (cityIdsRowSet.next()) {
			cityIds.add(cityIdsRowSet.getString("from_city_id"));
			cityIds.add(cityIdsRowSet.getString("to_city_id"));
		}
		return cityIds;
	}

	public boolean deleteScheduleDeparture(String busId) {
		log.debug("Running delete query for deleteScheduleDeparture: {}", selectCustomerBookTicketQuery);
		int i = jdbcTemplate.update("DELETE FROM schedule_departure " + "WHERE bus_id = ?", busId);
		return i > 0 ? true : false;
	}

	/////////////////////////////

	@Transactional(readOnly = true)
	public BusDetails getBusDetailsByBusId(Long busId) {
		log.debug("Running select query for getBusDetailsByBusId: {}", selectBusDetailsByBusId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("busId", busId);
		List<BusDetails> busDetails = null;
		busDetails = jdbcTemplateObject.query(selectBusDetailsByBusId, parameters,
				new BeanPropertyRowMapper<BusDetails>(BusDetails.class));

		return busDetails != null && !busDetails.isEmpty() ? busDetails.get(0) : new BusDetails();
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
	public List<RoutedCities> getTripCitiesBySrcDescCities(Long scheduleId, Integer srcCitySequance,
			Integer destCitySequance) {
		log.debug("Running select query for getTripCitiesBySrcDescCities: {}", selectTripCitiesBySrcDescCities);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("srcCitySequance", srcCitySequance);
		parameters.addValue("destCitySequance", destCitySequance);

		return jdbcTemplateObject.query(selectTripCitiesBySrcDescCities, parameters,
				new BeanPropertyRowMapper<RoutedCities>(RoutedCities.class));
	}
}
