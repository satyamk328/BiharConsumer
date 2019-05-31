package com.digital.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.BusScheduleDao;
import com.digital.dao.TicketDao;
import com.digital.model.RoutedCity;
import com.digital.model.TicketDetails;
import com.digital.model.vo.TicketVO;
import com.digital.utils.GlobalConstants;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

@Service
public class TicketService {

	@Autowired
	private BusScheduleDao busBookingDao;
	
	@Autowired
	private TicketDao tikcetDao;
	
	public List<TicketDetails> getTicketDetails(String pnr, Long phone){
		return tikcetDao.getTicketDetails(pnr, phone);
	}
	
	public int bookTickets(TicketVO bookTicketVO) {
		// Logic to generate tripId
		List<RoutedCity> srcCitySeq = busBookingDao.getTripCitiySequanceByCityId(bookTicketVO.getScheduleId(),
				bookTicketVO.getSrcCityId());
		List<RoutedCity> destCitySeq = busBookingDao.getTripCitiySequanceByCityId(bookTicketVO.getScheduleId(),
				bookTicketVO.getDestCityId());
		List<RoutedCity> routedCities = busBookingDao.getTripCitiesBySrcDescCities(bookTicketVO.getScheduleId(),
				srcCitySeq.get(0).getCitySequance(), destCitySeq.get(0).getCitySequance());

		String tripId = "";
		for (RoutedCity routedCity : routedCities) {
			tripId = tripId + routedCity.getCityId() + GlobalConstants.SEPARATOR;
		}
		tripId = tripId.substring(0, tripId.length() - 2);
		bookTicketVO.setTripId(tripId);

		return busBookingDao.bookTickets(bookTicketVO);
	}

	public int cancelTickets(TicketVO bookTicketVO) {
		return tikcetDao.cancelTickets(bookTicketVO);
	}	
	
	public Map<Long, String> cancelTickets(String ticketIds, Long phoneNumber) throws ParseException {
		List<String> tickets = Arrays.asList(ticketIds.split(","));
		List<Long> ticketIdsList =  tickets.stream().map(element->Long.parseLong(element)).collect(Collectors.toList());
		
		return tikcetDao.cancelTickets(ticketIdsList, phoneNumber);
	}
}
