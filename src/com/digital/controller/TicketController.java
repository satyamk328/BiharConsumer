package com.digital.controller;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.http.HttpHeaders.CACHE_CONTROL;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.EXPIRES;
import static org.springframework.http.HttpHeaders.PRAGMA;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.model.CancelTicketMaster;
import com.digital.model.TicketDetails;
import com.digital.model.vo.TicketVO;
import com.digital.service.TicketPdfReport;
import com.digital.service.TicketService;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v0/ticket")
@Slf4j
public class TicketController {

	@Autowired
	private TicketService bookingService;

	@Autowired
	private TicketPdfReport ticketPdfReport;

	/**
	 * this method is use to print all ticket according use
	 * 
	 * @param mobileNumber
	 * @param ticketNumber
	 * @return
	 */
	@GetMapping(value = "/{mobileNumber}/{ticketNumber}")
	public ResponseEntity<RestResponse<List<TicketDetails>>> getBookTicketList(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) String ticketNumber) {
		log.info("call print {},{}", mobileNumber, ticketNumber);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch record Successfully");
		List<TicketDetails> details = bookingService.getBookTicketList(ticketNumber, mobileNumber);
		if (details == null || details.isEmpty()) {
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are no ticket available in this pnr and phone. Please enter valid criteria.");
			return new ResponseEntity<>(new RestResponse<>(details, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(details, status), HttpStatus.OK);
	}

	@GetMapping(value = "/pdfreport/{mobileNumber}/{ticketNumber}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> downloanTicket(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) String ticketNumber,
			@RequestParam(value = "download", required = false, defaultValue = "1") int download) {
		List<TicketDetails> details = bookingService.getBookTicketList(ticketNumber, mobileNumber);

		ByteArrayInputStream bis = ticketPdfReport.ticketReport(details);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
		headers.add(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.add(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT");
		headers.add(ACCESS_CONTROL_ALLOW_HEADERS, CONTENT_TYPE);
		headers.add(CACHE_CONTROL, "no-cache, no-store, must-revalidate");
		headers.add(PRAGMA, "no-cache");
		headers.add(EXPIRES, "0");
		if (download == 1) {
			headers.add(CONTENT_DISPOSITION, "attachment; filename=" + ticketNumber + ".pdf");
		} else {
			headers.add(CONTENT_DISPOSITION, "inline; filename=" + ticketNumber + ".pdf");
		}
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	/**
	 * this method is use to get all cancel ticket
	 * 
	 * @param mobileNumber
	 * @param ticketNumber
	 * @return
	 */
	@GetMapping(value = "/cancel/{mobileNumber}/{ticketNumber}")
	public ResponseEntity<RestResponse<List<CancelTicketMaster>>> cancelTicket(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) String ticketNumber) {
		log.info("call print {},{}", mobileNumber, ticketNumber);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch record Successfully");
		List<CancelTicketMaster> details = bookingService.getCancelTicketList(ticketNumber, mobileNumber);
		if (details == null || details.isEmpty()) {
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are no ticket available in this pnr and phone. Please enter valid criteria.");
			return new ResponseEntity<>(new RestResponse<>(details, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(details, status), HttpStatus.OK);
	}

	@PostMapping(value = "/bookTickets")
	public ResponseEntity<RestResponse<Object>> bookTickets(@RequestBody(required = true) TicketVO bookTicketVO) {
		log.info("call search bookedBusTicket:{}", bookTicketVO);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Bus Ticket booked Successfully");
		TicketVO bStatus = null;
		if (StringUtils.isEmpty(bookTicketVO.getSeatDataToOperate())) {
			status = new RestStatus<>(HttpStatus.BAD_REQUEST.toString(), "Customer details is incurrect");
			new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.BAD_REQUEST);
		}
		
		List<Long> seatIds = bookTicketVO.getSeatDataToOperate().stream().map(urEntity -> urEntity.getSeatId())
				.collect(Collectors.toList());
		List<TicketDetails> ticketDetails = bookingService.validateTicket(bookTicketVO.getScheduleId(),
				bookTicketVO.getBusId(), seatIds);
		
		if (ticketDetails != null && !ticketDetails.isEmpty()) {
			status = new RestStatus<>(HttpStatus.OK.toString(), "Thease seats are already researved for another user.Please choose another seats");
		} else {
			bStatus = bookingService.bookTickets(bookTicketVO);
			if (bStatus != null) {
				status = new RestStatus<>(HttpStatus.OK.toString(),
						"There are no seats available in this bus. Please select a different bus.");
			}
		}
		return new ResponseEntity<>(new RestResponse<>(bStatus, status), HttpStatus.OK);
	}

	@GetMapping(value = "/validateTicket/{scheduleId}/{busId}")
	public ResponseEntity<RestResponse<Object>> validateTicket(
			@PathVariable(name = "scheduleId", required = true) Long scheduleId,
			@PathVariable(name = "busId", required = true) Long busId, @RequestParam(name = "ticketIds") String seatIds)
			throws ParseException {
		log.info("call search cancelTickets:{}", seatIds);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Thease seats are available");
		
		List<String> seatIdList = Arrays.asList(seatIds.split(","));
		List<Long> seatList = seatIdList.stream().map(element -> Long.parseLong(element)).collect(Collectors.toList());
		List<TicketDetails> ticketSatatus = bookingService.validateTicket(scheduleId, busId, seatList);
		
		if (ticketSatatus != null && !ticketSatatus.isEmpty()) {
			status = new RestStatus<>(HttpStatus.OK.toString(), "Thease seats are already researved for another user");
		}
		return new ResponseEntity<>(new RestResponse<>(ticketSatatus, status), HttpStatus.OK);
	}

	@PostMapping(value = "/cancelTickets")
	public ResponseEntity<RestResponse<Object>> cancelTickets(@RequestBody(required = true) TicketVO cancelTickets) {
		log.info("call search bookedBusTicket:{}", cancelTickets);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Bus Ticket Cancelled Successfully");
		int bStatus = bookingService.cancelTickets(cancelTickets);
		if (bStatus != 1) {
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are some issues to cancell ticket please call ADMIN +");
		}
		return new ResponseEntity<>(new RestResponse<>(bStatus, status), HttpStatus.OK);
	}

	@GetMapping(value = "/cancelTickets/{phoneNumber}")
	public ResponseEntity<RestResponse<Object>> cancelTickets(
			@PathVariable(name = "phoneNumber", required = true) Long phoneNumber,
			@RequestParam(name = "ticketIds") String ticketIds) throws ParseException {
		log.info("call search cancelTickets:{}", ticketIds);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Bus Ticket Cancelled Successfully");
		Map<Long, String> ticketSatatus = bookingService.cancelTickets(ticketIds, phoneNumber);
		if (ticketSatatus != null && !ticketSatatus.isEmpty()) {
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are some issues to cancell ticket please call ADMIN +");
		}
		return new ResponseEntity<>(new RestResponse<>(ticketSatatus, status), HttpStatus.OK);
	}
}
