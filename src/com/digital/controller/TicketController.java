package com.digital.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@GetMapping(value = "/{mobileNumber}/{ticketNumber}")
	public ResponseEntity<RestResponse<List<TicketDetails>>> bookTicket(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) String ticketNumber) {
		log.info("call print {},{}", mobileNumber, ticketNumber);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch record Successfully");
		List<TicketDetails> details = bookingService.getTicketDetails(ticketNumber, mobileNumber);
		if(details == null || details.isEmpty()) {
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are no ticket available in this pnr and phone. Please enter valid criteria.");
			return new ResponseEntity<>(new RestResponse<>(details, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(details, status), HttpStatus.OK);
	}
	
	@GetMapping(value = "/cancel/{mobileNumber}/{ticketNumber}")
	public ResponseEntity<RestResponse<List<CancelTicketMaster>>> cancelTicket(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) String ticketNumber) {
		log.info("call print {},{}", mobileNumber, ticketNumber);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch record Successfully");
		List<CancelTicketMaster> details = bookingService.getCancelTicketDetails(ticketNumber, mobileNumber);
		if(details == null || details.isEmpty()) {
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
		if (StringUtils.isEmpty(bookTicketVO.getSeatDataToOperate())) {
			status = new RestStatus<>(HttpStatus.BAD_REQUEST.toString(), "Customer details is incurrect");
			new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.BAD_REQUEST);
		}
		int bStatus = bookingService.bookTickets(bookTicketVO);
		if (bStatus != 1) {
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are no seats available in this bus. Please select a different bus.");
		}
		return new ResponseEntity<>(new RestResponse<>(bStatus, status), HttpStatus.OK);
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
