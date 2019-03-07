package com.digital.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.digital.model.BusDetails;
import com.digital.model.CustomerVo;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.UnsupportedEncodingException;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Base64;

/**
 * @author Satyam Kumar
 *
 */
public class CommonUtil {

	public static String encrypt(String data) throws UnsupportedEncodingException {
		return Base64.getEncoder().encodeToString(data.getBytes("utf-8"));
	}

	public static String decrypt(String encryptedData) throws UnsupportedEncodingException {
		byte[] asBytes = Base64.getDecoder().decode(encryptedData);
		return new String(asBytes, "utf-8");
	}

	
	public static ByteArrayInputStream generatePdf(BusDetails bus, CustomerVo customer, int ticketId) {

		Document document = new Document(new Rectangle(350f, 300f), 36f, 72f, 108f, 108f);
		document.setMargins(2, 2, 2, 2);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
			LineSeparator addLine = new LineSeparator();

			Paragraph companyName = new Paragraph("Bus Ticket Booking System", headFont);
			companyName.setAlignment(Element.ALIGN_CENTER);

			Paragraph sourceDestination = new Paragraph("From DELHI to Gaya",
					headFont);
			sourceDestination.setAlignment(Element.ALIGN_CENTER);

			Paragraph busNumber = new Paragraph("Bus Number: " + bus.getBusId());

			Paragraph passengerName = new Paragraph(
					"Passenger Name: SATYAM KUMAR ", headFont);

			Paragraph ticketNumber = new Paragraph("Ticket Number: " + ticketId);
			Paragraph departure = new Paragraph("Departure: 12:30" );
			Paragraph cost = new Paragraph("Travel Cost: € 12000" );

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

		} catch (Exception e) {

		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(encrypt("123"));
	}
}
