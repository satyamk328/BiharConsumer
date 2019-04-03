package com.digital.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digital.model.TicketVO;
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

@Service("bookingService")
public class BookingService {

	public Optional<Object> cancelledTikcet(Long phoneNumber, Long ticketNumber) {
		return null;
	}

	private List<TicketVO> create() {
		List<TicketVO> ticketVOs = new ArrayList<>();
		TicketVO ticketVO = new TicketVO();
		ticketVO.setBusId(1L);
		ticketVO.setCustId(12L);
		ticketVO.setTicketId(1L);
		ticketVOs.add(ticketVO);
		return ticketVOs;
	}

	public ByteArrayInputStream citiesReport() {

		List<TicketVO> ticketVOs = create();
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
