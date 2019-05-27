package com.digital.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.BusScheduleDao;
import com.digital.dao.TicketDao;
import com.digital.model.RoutedCity;
import com.digital.model.TicketDetails;
import com.digital.model.vo.TicketVO;
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
			tripId = tripId + routedCity.getCityId() + "::";
		}
		tripId = tripId.substring(0, tripId.length() - 2);
		bookTicketVO.setTripId(tripId);

		return busBookingDao.bookTickets(bookTicketVO);
	}

	public int cancelTickets(TicketVO bookTicketVO) {
		return tikcetDao.cancelTickets(bookTicketVO);
	}	
	
	public ByteArrayInputStream citiesReport() {

		//List<TicketVO> ticketVOs = create();
		Document document = new Document(PageSize.A4.rotate(), 36, 36, 54, 36);
		document.setMargins(2, 2, 2, 2);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			
		
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
			LineSeparator addLine = new LineSeparator();
			
			Paragraph companyName = new Paragraph("Bus Ticket Booking System", headFont);
			companyName.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph sourceDestination = new Paragraph("From DELHI to Gaya", headFont);
			sourceDestination.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph busNumber = new Paragraph("Bus Number: 12" );
			
			Paragraph passengerName = new Paragraph("Passenger Name: SATYAM KUMAR ", headFont);

			Paragraph ticketNumber = new Paragraph("Ticket Number: 12");
			Paragraph departure = new Paragraph("Departure: 12:30");
			Paragraph cost = new Paragraph("Travel Cost: € 12000");

			Paragraph footer = new Paragraph("Happy Journey!", headFont);
			footer.setAlignment(Element.ALIGN_CENTER);
			
			

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(companyName);
			document.add(new Chunk(addLine));
			document.add(sourceDestination);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(ticketNumber);
			document.add(busNumber);
			document.add(passengerName);
			document.add(departure);
			document.add(cost);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(new Chunk(addLine));
			document.add(footer);

			document.close();

		} catch (DocumentException ex) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}
