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

	public List<TicketDetails> getTicketDetails(String pnr, Long phone){
		log.debug("Running select query for getTicketDetails: {}",
				selectTicketDetailsBypnrAndPhone);
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
	public int cancelTickets(List<Long> ticketIds) {
		log.debug("Running select query for searchTripBySrcDescAndDate: {}", insertCancelTicketMasterFromTicketMasterByTicketId);
		log.debug("Running select query for searchTripBySrcDescAndDate: {}", deleteTicketMasterByTicketId);
		
		// TODO null validation
		// TODO Logic to check cancellation policies
		
		for (Long ticketId : ticketIds) {
			TicketMaster ticket = getTicketByTicketId(ticketId);
			List<TicketCancellationPolicy> cancellationPolicies = getTicketCancellationPolicy(ticket.getBusId());
			
			int minutesAfterBooking = 0;// TOD calculate
			int minutesBeforeStart = 0; //TODO calculate
			
			TicketCancellationPolicy policy = CommonUtil.getPolicyToApply(cancellationPolicies, minutesAfterBooking, minutesBeforeStart);
			ticket.setPolicyId(policy.getPolicyId());
			ticket.setRefundAmount(CommonUtil.getRefundAmount(policy, ticket.getTotalFare()));
			
			try {
				final KeyHolder holder = new GeneratedKeyHolder();
				final BeanPropertySqlParameterSource beanParameters = new BeanPropertySqlParameterSource(ticket);
		        jdbcTemplateObject.update(insertCancelTicketMasterFromTicketMasterByTicketId, beanParameters, holder);
				
		        MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
		        sqlParameters.addValue("ticketId", ticketId);
				jdbcTemplateObject.update(deleteTicketMasterByTicketId, sqlParameters);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
	
	public TicketMaster getTicketByTicketId(Long ticketId){
		log.debug("Running select query for getTicketByTicketId: {}",
				selectTicketByTicketId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ticketId", ticketId);
		return jdbcTemplateObject.queryForObject(selectTicketByTicketId, parameters, TicketMaster.class);
	}
	
	public List<TicketCancellationPolicy> getTicketCancellationPolicy(Long busId){
		log.debug("Running select query for getTicketCancellationPolicy: {}",
				selectCancellationtPolicyByBusId);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("busId", busId);
		return jdbcTemplateObject.query(selectCancellationtPolicyByBusId, parameters,
				new BeanPropertyRowMapper<>(TicketCancellationPolicy.class));
	}
}
