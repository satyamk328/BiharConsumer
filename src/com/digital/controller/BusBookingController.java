package com.digital.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.model.BusDetailsObject;
import com.digital.model.BusSeatDetailsObject;
import com.digital.model.vo.CustomerBusTicketVO;
import com.digital.model.vo.SearchTripVO;
import com.digital.service.BusBookingService;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;

import io.swagger.annotations.Api;
/**
 * @author Satyam Kumar
 *
 */
@Api(value = "busBooking")
@RestController
@RequestMapping(value = "/api/v0/bus")
public class BusBookingController {

	private static final Logger log = LoggerFactory.getLogger(BusBookingController.class);

	@Autowired
	private BusBookingService busBookingService;

	@GetMapping(value = "/route/{source}/{destination}/{date}")
	public ResponseEntity<RestResponse<BusDetailsObject>> searchBusRoutDetails(
			@PathVariable(name = "source", required = true) String source,
			@PathVariable(name = "destination", required = true) String destination,
			@PathVariable(name = "date", required = true) String date) {
		log.info("call search searchBusRoutDetails:{},{},{}", source,destination,date);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		BusDetailsObject busDetailsObject = busBookingService.searchBusRoutDetails(source, destination, date);
		if (busDetailsObject.getAvailableRoutes().isEmpty())
			status = new RestStatus<>(HttpStatus.OK.toString(),
					String.format("There are no buses between these two cities. Please try a different date or search with an alternate route."));
		return new ResponseEntity<>(new RestResponse(busDetailsObject, status), HttpStatus.OK);
	}

	@PostMapping(value = "/trip")
	public ResponseEntity<RestResponse<BusSeatDetailsObject>> getSeatAvailability(
			@RequestBody(required=true) SearchTripVO tripVO) {
		log.info("call search getSeatAvailability:{} ", tripVO);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		BusSeatDetailsObject busSeatDetailsAvailability = busBookingService.getSeatAvailability(tripVO);
		if (busSeatDetailsAvailability.getBusSeatDetails() == null) {
			status = new RestStatus<>(HttpStatus.OK.toString(),	"There are no seats available in this bus. Please select a different bus.");
		}
		return new ResponseEntity<>(new RestResponse(busSeatDetailsAvailability, status), HttpStatus.OK);
	}

	@PostMapping(value = "/bookedBusTicket")
	public ResponseEntity<RestResponse<Object>> bookedBusTicket(
			@RequestBody(required = true) CustomerBusTicketVO busTicketVO) {
		log.info("call search bookedBusTicket:{}", busTicketVO);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Bus Ticket booked Successfully");
		CustomerBusTicketVO customerBusTicketVO = busBookingService.bookedBusTicket(busTicketVO);
		if (customerBusTicketVO != null)
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are no seats available in this bus. Please select a different bus.");
		return new ResponseEntity<>(new RestResponse(customerBusTicketVO, status), HttpStatus.OK);
	}

	@GetMapping(value = "/getTicketHistory/{uid}")
	public ResponseEntity<RestResponse<List<CustomerBusTicketVO>>> getBusTicketHistory(
			@PathVariable(name = "uid", required = true) String uid,
			@RequestParam(name = "limit", required = false, defaultValue = "5") int limit) {
		log.info("call search getBusTicketHistory:{}", uid);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Bus Ticket booked Successfully");
		List<CustomerBusTicketVO> customerBusTicketVOs = busBookingService.getHistoryBusTicket(uid, limit);
		if (customerBusTicketVOs != null)
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are no seats available in this bus. Please select a different bus.");
		return new ResponseEntity<>(new RestResponse(customerBusTicketVOs, status), HttpStatus.OK);
	}
}
