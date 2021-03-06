package com.digital.service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.BusScheduleDao;
import com.digital.dao.TicketDao;
import com.digital.model.CancelTicketMaster;
import com.digital.model.TicketDetails;
import com.digital.model.TripMaster;
import com.digital.model.vo.TicketVO;
import com.digital.user.service.MailService;
import com.digital.utils.CommonUtil;
import com.digital.utils.GlobalConstants;
import com.digital.utils.MailServiceUtils;

@Service
public class TicketService {

	@Autowired
	private BusScheduleDao busBookingDao;
	
	@Autowired
	private TicketDao tikcetDao;
	
	@Autowired
    private MailService emailService;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private SMSWrapperService smsWrapperService;
	
	public List<TicketDetails> getBookTicketList(String pnr, Long phone){
		return tikcetDao.getBookTicketList(pnr, phone);
	}
	

	public List<CancelTicketMaster> getCancelTicketList(String pnr, Long phone){
		return tikcetDao.getCancelTicketList(pnr, phone);
	}
	
	public TicketVO bookTickets(TicketVO bookTicketVO) {
		// Logic to generate tripId
		List<TripMaster> srcCitySeq = busBookingDao.getTripCitiySequanceByCityId(bookTicketVO.getScheduleId(),
				bookTicketVO.getSrcCityId());
		List<TripMaster> destCitySeq = busBookingDao.getTripCitiySequanceByCityId(bookTicketVO.getScheduleId(),
				bookTicketVO.getDestCityId());
		List<TripMaster> routedCities = busBookingDao.getTripCitiesBySrcDescCities(bookTicketVO.getScheduleId(),
				srcCitySeq.get(0).getCitySequance(), destCitySeq.get(0).getCitySequance());

		String tripId = "";
		for (TripMaster routedCity : routedCities) {
			tripId = tripId + routedCity.getCityId() + GlobalConstants.SEPARATOR;
		}
		tripId = tripId.substring(0, tripId.length() - 2);
		bookTicketVO.setTripId(tripId);
        
		String pnr = commonUtil.getPNRNumber(String.valueOf(bookTicketVO.getUserId()), bookTicketVO.getSrcCityId(),
				bookTicketVO.getDestCityId(),
				tikcetDao.totalTicketCont(bookTicketVO.getScheduleId(), bookTicketVO.getBusId()));
		bookTicketVO.setPnr(pnr);
		
		TicketVO row = tikcetDao.bookTickets(bookTicketVO);
		if(row != null) {
			emailService.sendEmail(bookingTicketConfirmHeader(bookTicketVO), bookTicketVO.getEmail(),
					MailServiceUtils.generateBookingTicketMail(bookTicketVO));
			smsWrapperService.sendSMS(bookTicketVO.getPhone(), MailServiceUtils.confirmBookingSMS(bookTicketVO));
		}
		return row;
	}

	public int cancelTickets(TicketVO bookTicketVO) {
		return tikcetDao.cancelTickets(bookTicketVO);
	}	
	
	public Map<Long, String> cancelTickets(String ticketIds, Long phoneNumber) throws ParseException {
		List<String> tickets = Arrays.asList(ticketIds.split(","));
		List<Long> ticketIdsList =  tickets.stream().map(element->Long.parseLong(element)).collect(Collectors.toList());
		return tikcetDao.cancelTickets(ticketIdsList, phoneNumber);
	}
	
	public List<TicketDetails> validateTicket(Long scheduleId, Long busId,List<Long> seatId) {
		return tikcetDao.validateTicket(scheduleId, busId, seatId);
	}
	
	private String bookingTicketConfirmHeader(TicketVO ticketVO) {
		return GlobalConstants.BOOKING_CONFIRMATION_HEADER.replace("${TRAVELNAME}", ticketVO.getTravelName())
				.replace("${DEPARTUREDATE}", ticketVO.getDepartureDate()).replace("${SOURCE}", ticketVO.getSrcCityName())
				.replace("${DESTINATION}", ticketVO.getDestCityName());
	}
}
