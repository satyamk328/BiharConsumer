package com.digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.model.BusScheduleDetails;
import com.digital.model.TripDetails;
import com.digital.model.vo.SearchTripVO;
import com.digital.model.vo.TicketVO;
import com.digital.service.BusTripService;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Api(value = "busBooking")
@RestController
@RequestMapping(value = "/api/v0/bus")
@Slf4j
public class BusTripController {

	@Autowired
	private BusTripService busService;

	@GetMapping(value = "/route/{source}/{destination}/{date}")
	public ResponseEntity<RestResponse<TripDetails>> searchBusScheduletDetails(
			@PathVariable(name = "source", required = true) Long srcCityId,
			@PathVariable(name = "destination", required = true) Long destCityId,
			@PathVariable(name = "date", required = true) String date) {
		log.info("call search searchBusScheduletDetails:{},{},{}", srcCityId, destCityId, date);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		TripDetails tripDetails = busService.searchBusScheduleDetails(srcCityId, destCityId, date);
		if (tripDetails.getAvailableRoutes().isEmpty())
			status = new RestStatus<>(HttpStatus.OK.toString(), String.format(
					"There are no buses between these two cities. Please try a different date or search with an alternate route."));
		return new ResponseEntity<>(new RestResponse<>(tripDetails, status), HttpStatus.OK);
	}

	@GetMapping(value = "/trip")
	public ResponseEntity<RestResponse<BusScheduleDetails>> scheduledBusSheetDetails(@RequestBody(required=true) SearchTripVO tripVO) {
		log.info("call search busSheetDetails:{},{},{},{}", tripVO.getScheduleId(), tripVO.getBusId(), tripVO.getSourceId(), tripVO.getDestinationId());
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		BusScheduleDetails tripDetails = busService.scheduledBusSeatDetails(tripVO);
		if (tripDetails == null)
			status = new RestStatus<>(HttpStatus.OK.toString(), String.format(
					"There are no buses between these two cities. Please try a different date or search with an alternate route."));
		return new ResponseEntity<>(new RestResponse<>(tripDetails, status), HttpStatus.OK);
	}

	@PostMapping(value = "/bookTickets")
	public ResponseEntity<RestResponse<Object>> bookTickets(@RequestBody(required = true) TicketVO bookTicketVO) {
		log.info("call search bookedBusTicket:{}", bookTicketVO);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Bus Ticket booked Successfully");
		int bStatus = busService.bookTickets(bookTicketVO);
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
		int bStatus = busService.cancelTickets(cancelTickets);
		if (bStatus != 1) {
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are some issues to cancell ticket please call ADMIN +");
		}
		return new ResponseEntity<>(new RestResponse<>(bStatus, status), HttpStatus.OK);
	}
}
