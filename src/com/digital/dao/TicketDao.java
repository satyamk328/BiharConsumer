package com.digital.dao;

import java.text.ParseException;
import java.util.ArrayList;
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

import com.digital.model.BusDepatureTimeDetails;
import com.digital.model.TicketCancellationPolicy;
import com.digital.model.TicketDetails;
import com.digital.model.TicketMaster;
import com.digital.model.vo.SeatDataToOperate;
import com.digital.model.vo.TicketVO;
import com.digital.utils.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Repository("ticketDao")
@Slf4j
public class TicketDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Value("${select_TicketDetails_By_PnrAndPhone}")
	private String selectTicketDetailsBypnrAndPhone;

	@Value("${select_TicketDetails_By_ScheduleAndBusId}")
	private String selectTicketDetailsByScheduleAndBusId;

	@Value("${insert_cancel_ticket_master_from_ticket_master}")
	private String insertCancelTicketMasterFromTicketMaster;

	@Value("${delete_ticket_master_by_scheduleId_busId_seatId}")
	private String deleteTicketMasterByScheduleIdBusIdSeatId;

	@Value("${insert_cancel_ticket_master_from_ticket_master_by_ticketId}")
	private String insertCancelTicketMasterFromTicketMasterByTicketId;

	@Value("${delete_ticket_master_by_ticketId}")
	private String deleteTicketMasterByTicketId;

	@Value("${select_cancellation_policy_by_busId}")
	private String selectCancellationtPolicyByBusId;

	@Value("${select_ticket_by_ticketId}")
	private String selectTicketByTicketId;

	@Value("${select_bus_StartTime_by_scheduleId}")
	private String busStartTimeByScheduleId;

	public List<TicketDetails> getTicketDetails(String pnr, Long phone) {
		log.debug("Running select query for getTicketDetails: {}", selectTicketDetailsBypnrAndPhone);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("pnr", pnr);
		parameters.addValue("phone", phone);
		return jdbcTemplateObject.query(selectTicketDetailsBypnrAndPhone, parameters,
				new BeanPropertyRowMapper<>(TicketDetails.class));
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
		return (details != null && !details.isEmpty()) ? details : new ArrayList<>();
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

				TicketMaster ticket = getTicketByTicketId(ticketId, phoneNumber);

				// If ticket is not found in DB
				if (ticket == null) {
					statusData.put(ticketId, ticketId + " is invalid.");
					continue;
				}

				BusDepatureTimeDetails depatureDetails = getBusStartTimeByScheduleId(ticket.getScheduleId());
				List<TicketCancellationPolicy> cancellationPolicies = getTicketCancellationPolicy(ticket.getBusId());
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
			}
			statusData.put(ticketId, "This ticket " + ticketId + " has been cancelled successfully");
		}
		return statusData;
	}

	public TicketMaster getTicketByTicketId(Long ticketId, Long phoneNumber) {
		log.debug("Running select query for getTicketByTicketId: {}", selectTicketByTicketId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ticketId", ticketId);
		parameters.addValue("phoneNumber", phoneNumber);
		List<TicketMaster> result = jdbcTemplateObject.query(selectTicketByTicketId, parameters,
				new BeanPropertyRowMapper<>(TicketMaster.class));
		return result.isEmpty() ? null : result.get(0);
	}

	public List<TicketCancellationPolicy> getTicketCancellationPolicy(Long busId) {
		log.debug("Running select query for getTicketCancellationPolicy: {}", selectCancellationtPolicyByBusId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("busId", busId);
		return jdbcTemplateObject.query(selectCancellationtPolicyByBusId, parameters,
				new BeanPropertyRowMapper<>(TicketCancellationPolicy.class));
	}

	public BusDepatureTimeDetails getBusStartTimeByScheduleId(Long scheduleId) {
		log.debug("Running select query for getBusStartTimeByScheduleId: {}", busStartTimeByScheduleId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("scheduleId", scheduleId);
		List<BusDepatureTimeDetails> result = jdbcTemplateObject.query(busStartTimeByScheduleId, parameters,
				new BeanPropertyRowMapper<>(BusDepatureTimeDetails.class));
		return result.isEmpty() ? null : result.get(0);
	}
}
