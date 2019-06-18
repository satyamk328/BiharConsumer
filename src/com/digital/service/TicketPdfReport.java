package com.digital.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.digital.model.TicketDetails;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TicketPdfReport {

	private final static String[] HEADER_ARRAY = { "S.No.", "Name", "Age", "Gender", "Status", "Seat/Berth" };
	private final static String[] FARE_ARRAY = { "Ticket Fare.", "Service Charge", "Travel Insurance Premium",
			"Total Fare" };
	public final static Font SMALL_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	public final static Font HEADER_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD);
	public final static Font NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
	public static final String PDF_EXTENSION = ".pdf";

	public ByteArrayInputStream ticketReport1(List<TicketDetails> tickets) {
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, out);
			document.open();
			addTitlePage(document, tickets);
			addContent(document, tickets);
			addCustomerCare(document);
			document.close();
		} catch (DocumentException ex) {
			log.error("Error occurred: {0}", ex);
		}
		return new ByteArrayInputStream(out.toByteArray());
	}

	/** Helper methods start here **/
	private void addTitlePage(Document document, List<TicketDetails> ticketDetails) throws DocumentException {
		TicketDetails details = null;
		if (ticketDetails != null && !ticketDetails.isEmpty())
			details = ticketDetails.get(0);
		else
			return;
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 3);
		preface.add(new Phrase("Online DigitalBihar Bus Ticket  ",
				new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLDITALIC)));
		addEmptyLine(preface, 2);
		preface.add(new Phrase("Ticket No. : ", SMALL_BOLD));
		preface.add(new Phrase(details.getPnr(), NORMAL_FONT));
		preface.add(new Phrase("  Bus No./Name : ", SMALL_BOLD));
		preface.add(new Phrase(details.getTravelName(), NORMAL_FONT));
		addEmptyLine(preface, 1);
		preface.add(new Phrase("Date & Time of Booking : ", SMALL_BOLD));
		preface.add(new Phrase(details.getBookingDate().toString(), NORMAL_FONT));
		preface.add(new Phrase("  Date of Journey : ", SMALL_BOLD));
		preface.add(new Phrase(details.getDepartureDate(), NORMAL_FONT));
		addEmptyLine(preface, 1);
		preface.add(new Phrase("Scheduled Departure* : ", SMALL_BOLD));
		preface.add(new Phrase(details.getDepartureDate() + " " + details.getDepartureTime(), NORMAL_FONT));
		preface.add(new Phrase("  Scheduled Arrival : ", SMALL_BOLD));
		preface.add(new Phrase(details.getArrivalDate() + " " + details.getArrivalTime(), NORMAL_FONT));
		addEmptyLine(preface, 1);
		preface.add(new Phrase("From :", SMALL_BOLD));
		preface.add(new Phrase(details.getSrcCityName(), NORMAL_FONT));
		preface.add(new Phrase("  To :", SMALL_BOLD));
		preface.add(new Phrase(details.getDestCityName(), NORMAL_FONT));
		addEmptyLine(preface, 1);
		preface.add(new Phrase("Email :", SMALL_BOLD));
		preface.add(new Phrase(details.getEmail(), NORMAL_FONT));
		preface.add(new Phrase("  Phone :", SMALL_BOLD));
		preface.add(new Phrase(details.getPhoneNumber(), NORMAL_FONT));
		document.addSubject("PDF : jjj");
		preface.setAlignment(Element.ALIGN_CENTER);
		document.add(preface);
	}

	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private void addContent(Document document, List<TicketDetails> tickets) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		paragraph.setFont(NORMAL_FONT);
		createReportTable(paragraph, tickets);
		document.add(paragraph);
	}

	private void createReportTable(Paragraph paragraph, List<TicketDetails> dataObjList) {
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		Font font = HEADER_FONT;
		font.setColor(BaseColor.MAGENTA);
		paragraph.add(new Chunk("Passenger Details ", font));
		if (null == dataObjList) {
			paragraph.add(new Chunk("No data to display."));
			return;
		}
		addHeaderInTable(HEADER_ARRAY, table);
		int count = 1;
		for (TicketDetails dataObject : dataObjList) {
			addToTable(table, String.valueOf(count) + ".");
			addToTable(table, dataObject.getCustomerName());
			addToTable(table, String.valueOf(dataObject.getAge()));
			addToTable(table, dataObject.getGender());
			addToTable(table, "CNF");
			addToTable(table, dataObject.getSeatNumber());
			count++;
		}
		paragraph.add(table);
		addEmptyLine(paragraph, 2);
		createFareDetails(paragraph, dataObjList.get(0).getTotalFare());
	}

	private void addToTable(PdfPTable table, String data) {
		table.addCell(new Phrase(data, NORMAL_FONT));
	}

	private void addHeaderInTable(String[] headerArray, PdfPTable table) {
		PdfPCell c1 = null;
		for (String header : headerArray) {
			c1 = new PdfPCell(new Phrase(header, SMALL_BOLD));
			c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
		}
		table.setHeaderRows(1);
	}

	private void createFareDetails(Paragraph paragraph, Double fare) {
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		Font font = HEADER_FONT;
		font.setColor(BaseColor.MAGENTA);
		paragraph.add(new Chunk("Fare Details (Inclusive of GST) ", font));
		if (null == fare) {
			paragraph.add(new Chunk("No data to display."));
			return;
		}
		addHeaderInTable(FARE_ARRAY, table);
		addToTable(table, "Rs. " + String.valueOf(fare));
		addToTable(table, String.format("Rs. 0.00"));
		addToTable(table, String.format("Rs. 0.00"));
		addToTable(table, "Rs. " + String.valueOf(fare));
		paragraph.add(table);
	}

	private void addCustomerCare(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 3);
		Font font = HEADER_FONT;
		font.setColor(BaseColor.MAGENTA);
		preface.add(new Chunk("Customer Care ", font));

		ListItem listItem;
		com.itextpdf.text.List list = new com.itextpdf.text.List(true, 15);
		listItem = new ListItem(
				"For any further assistance, please contact us at 24*7 Hrs.Customer Support at 0755-6610661, 0755-4090600 (Language: Hindi and English) or mail us at care@digitalbihar.com",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL));
		list.add(listItem);
		listItem = new ListItem(
				"For any enquiries or information regarding your transaction with DIGITALBIHAR, do not provide your credit/debit card details by any means to DIGITALBIHAR. All your queries can be replied on the basis of 15 digit DIGITALBIHAR Transaction id/ 10 digit Ticket no./ User id. DIGITALBIHAR does not store the credit/ debit card information in any form during the transaction.",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL));
		list.add(listItem);
		document.add(preface);
		document.add(list);
	}

}
