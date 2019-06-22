package com.digital.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.util.StringUtils;

import com.digital.model.CancelTicketMaster;
import com.digital.model.ScheduleMaster;
import com.digital.model.TicketCancellationPolicy;
import com.digital.model.TicketDetails;
import com.digital.model.vo.SeatDataToOperate;
import com.digital.model.vo.TicketVO;
import com.digital.utils.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Repository("ticketDao")
@Slf4j
public class TicketDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Autowired
	private BusScheduleDao busScheduleDao;

	@Autowired
	private CancelPolicyDao cancelPolicyDao;

	@Value("${select_TicketDetails_By_PnrAndPhone}")
	private String selectTicketDetailsBypnrAndPhone;

	@Value("${select_cancelTicketDetails_By_PnrAndPhone}")
	private String selectCancelTicketDetailsBypnrAndPhone;

	@Value("${select_TicketDetails_By_ScheduleAndBusId}")
	private String selectTicketDetailsByScheduleAndBusId;
	
	@Value("${select_TicketDetails_By_ScheduleAndBusIdANDSeatId}")
	private String selectValidateTicketDetailsByScheduleAndBusIdANDSeatId;

	@Value("${insert_cancel_ticket_master_from_ticket_master}")
	private String insertCancelTicketMasterFromTicketMaster;

	@Value("${delete_ticket_master_by_scheduleId_busId_seatId}")
	private String deleteTicketMasterByScheduleIdBusIdSeatId;

	@Value("${insert_cancel_ticket_master_from_ticket_master_by_ticketId}")
	private String insertCancelTicketMasterFromTicketMasterByTicketId;

	@Value("${delete_ticket_master_by_ticketId}")
	private String deleteTicketMasterByTicketId;

	@Value("${select_ticket_by_ticketId}")
	private String selectTicketByTicketId;

	@Value("${insert_ticket_master}")
	private String insertTicketMaster;

	@Transactional(readOnly = true)
	public List<TicketDetails> getBookTicketList(String pnr, Long phone) {
		log.debug("Running select query for getTicketDetails: {}", selectTicketDetailsBypnrAndPhone);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("pnr", pnr);
		parameters.addValue("phone", phone);
		return jdbcTemplateObject.query(selectTicketDetailsBypnrAndPhone, parameters,
				new BeanPropertyRowMapper<>(TicketDetails.class));
	}

	@Transactional(readOnly = true)
	public List<CancelTicketMaster> getCancelTicketList(String pnr, Long phone) {
		log.debug("Running select query for getTicketDetails: {}", selectCancelTicketDetailsBypnrAndPhone);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("pnr", pnr);
		parameters.addValue("phone", phone);
		return jdbcTemplateObject.query(selectCancelTicketDetailsBypnrAndPhone, parameters,
				new BeanPropertyRowMapper<>(CancelTicketMaster.class));
	}

	@Transactional(readOnly = true)
	public List<TicketDetails> validateTicket(Long scheduleId, Long busId, List<Long> seatId) {
		log.debug("Running select query for getTicketDetailsByScheduleAndBusId: {}",
				selectValidateTicketDetailsByScheduleAndBusIdANDSeatId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("busId", busId);
		parameters.addValue("seatId", seatId);
		List<TicketDetails> details = jdbcTemplateObject.query(selectValidateTicketDetailsByScheduleAndBusIdANDSeatId, parameters,
				new BeanPropertyRowMapper<>(TicketDetails.class));
		return details;
	}
	
	@Transactional(readOnly = true)
	public List<TicketDetails> getTicketDetailsByScheduleAndBusId(Long scheduleId, Long busId) {
		log.debug("Running select query for getTicketDetailsByScheduleAndBusId: {}",
				selectTicketDetailsByScheduleAndBusId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("busId", busId);

		List<TicketDetails> details = jdbcTemplateObject.query(selectTicketDetailsByScheduleAndBusId, parameters,
				new BeanPropertyRowMapper<>(TicketDetails.class));
		return details;
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
	public Integer totalTicketCont(Long scheduleId, Long busId) {
		String sql = "SELECT COUNT(*) FROM ticket_master WHERE ScheduleId =:scheduleId AND BusId =:busId";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		parameters.addValue("busId", busId);
		return this.jdbcTemplateObject.queryForObject(sql, parameters, Integer.class);
	}

	@Transactional
	public Map<Long, String> cancelTickets(List<Long> ticketIds, Long phoneNumber) throws ParseException {
		log.debug("Running select query for searchTripBySrcDescAndDate: {}",
				insertCancelTicketMasterFromTicketMasterByTicketId);
		log.debug("Running select query for searchTripBySrcDescAndDate: {}", deleteTicketMasterByTicketId);

		// TODO null validation
		// TODO Logic to check cancellation policies
		Map<Long, String> statusData = new LinkedHashMap<Long, String>();
		for (Long ticketId : ticketIds) {
			try {
				CancelTicketMaster ticket = getTicketByTicketId(ticketId, phoneNumber);
				// If ticket is not found in DB
				if (ticket == null) {
					statusData.put(ticketId, ticketId + " is invalid.");
					continue;
				}
				List<ScheduleMaster> scheduleMasters = busScheduleDao
						.getBusStartTimeByScheduleId(ticket.getScheduleId());
				ScheduleMaster depatureDetails = scheduleMasters.isEmpty() ? new ScheduleMaster()
						: scheduleMasters.get(0);
				List<TicketCancellationPolicy> cancellationPolicies = cancelPolicyDao
						.getTicketCancellationPolicy(ticket.getBusId());
				Date busSchDateTime = CommonUtil.dateByDateAndTimeString(depatureDetails.getDepartureDate(),
						depatureDetails.getDepartureTime());

				// If try to cancell ticket after bus start time
				if (busSchDateTime.compareTo(new Date()) == -1) {
					statusData.put(ticketId, "This ticket " + ticketId
							+ " can not be cancelled becuse you are trying to cancel ticket after bus start");
					continue;
				}

				Long minutesAfterBooking = 0L;// TODO calculate
				Long minutesBeforeStart = CommonUtil.getMinutesDiff(busSchDateTime, new Date());

				TicketCancellationPolicy policy = CommonUtil.getPolicyToApply(cancellationPolicies, minutesAfterBooking,
						minutesBeforeStart);
				ticket.setPolicyId(policy.getPolicyId());
				ticket.setRefundAmount(CommonUtil.getRefundAmount(policy, ticket.getTotalFare()));

				final KeyHolder holder = new GeneratedKeyHolder();
				final BeanPropertySqlParameterSource beanParameters = new BeanPropertySqlParameterSource(ticket);
				jdbcTemplateObject.update(insertCancelTicketMasterFromTicketMasterByTicketId, beanParameters, holder);

				MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
				sqlParameters.addValue("ticketId", ticketId);
				jdbcTemplateObject.update(deleteTicketMasterByTicketId, sqlParameters);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
			statusData.put(ticketId, "This ticket " + ticketId + " has been cancelled successfully");
		}
		return statusData;
	}

	@Transactional(readOnly = true)
	private CancelTicketMaster getTicketByTicketId(Long ticketId, Long phoneNumber) {
		log.debug("Running select query for getTicketByTicketId: {}", selectTicketByTicketId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ticketId", ticketId);
		parameters.addValue("phoneNumber", phoneNumber);
		List<CancelTicketMaster> result = jdbcTemplateObject.query(selectTicketByTicketId, parameters,
				new BeanPropertyRowMapper<>(CancelTicketMaster.class));
		return result.isEmpty() ? null : result.get(0);
	}

	@Transactional
	public synchronized TicketVO bookTickets(TicketVO bookTicketVO) {

		log.debug("Running select query for searchTripBySrcDescAndDate: {}", insertTicketMaster);
		try {
			for (SeatDataToOperate seatData : bookTicketVO.getSeatDataToOperate()) {
				MapSqlParameterSource parameters = new MapSqlParameterSource();
				parameters.addValue("scheduleId", bookTicketVO.getScheduleId());
				if (StringUtils.isEmpty(bookTicketVO.getUserId()))
					parameters.addValue("userId", "USER");
				else
					parameters.addValue("userId", bookTicketVO.getUserId());
				parameters.addValue("busId", bookTicketVO.getBusId());
				parameters.addValue("pnr", bookTicketVO.getPnr());
				parameters.addValue("seatId", seatData.getSeatId());
				parameters.addValue("tripId", bookTicketVO.getTripId());
				parameters.addValue("srcCityName", bookTicketVO.getSrcCityName());
				parameters.addValue("destCityName", bookTicketVO.getDestCityName());
				parameters.addValue("travelName", bookTicketVO.getTravelName());
				parameters.addValue("busType", bookTicketVO.getBusType());
				parameters.addValue("distance", bookTicketVO.getDistance());
				parameters.addValue("isAc", bookTicketVO.getIsAC());
				parameters.addValue("boadingPoint", bookTicketVO.getBoadingPoint());
				parameters.addValue("droppingPoint", bookTicketVO.getDroppingPoint());
				parameters.addValue("departureDate", bookTicketVO.getDepartureDate());
				parameters.addValue("departureTime", bookTicketVO.getDepartureTime());
				parameters.addValue("arrivalDate", bookTicketVO.getArrivalDate());
				parameters.addValue("arrivalTime", bookTicketVO.getArrivalTime());
				parameters.addValue("seatType", seatData.getSeatType());

				parameters.addValue("totalFare", bookTicketVO.getTotalFare());

				parameters.addValue("seatNumber", seatData.getSeatNumber());
				parameters.addValue("seatName", seatData.getSeatNumber());
				parameters.addValue("isLowerBerth", seatData.getIsLowerBerth());
				parameters.addValue("chartStatus", "CNF");
				parameters.addValue("customerName", seatData.getFName()+" "+seatData.getLName());
				parameters.addValue("age", seatData.getAge());
				parameters.addValue("email", bookTicketVO.getEmail());
				parameters.addValue("gender", seatData.getGender());
				parameters.addValue("phoneNumber", bookTicketVO.getPhone());
				parameters.addValue("isLicence", bookTicketVO.getIsLicence());
				parameters.addValue("bookingDate", bookTicketVO.getBookingDate());

				jdbcTemplateObject.update(insertTicketMaster, parameters);
			}
		} catch (Exception e) {
			return null;
		}
		return bookTicketVO;
	}

}
