package com.digital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.model.BusScheduleDetails;
import com.digital.model.TripDetails;
import com.digital.model.vo.TicketVO;
import com.digital.model.vo.CustomerBusTicketVO;
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

	@GetMapping(value = "/trip/{scheduleId}/{busId}/{source}/{destination}")
	public ResponseEntity<RestResponse<BusScheduleDetails>> scheduledBusSheetDetails(
			@PathVariable(name = "scheduleId", required = true) Long scheduleId,
			@PathVariable(name = "busId", required = true) Long busId,
			@PathVariable(name = "source", required = true) Long srcCityId,
			@PathVariable(name = "destination", required = true) Long destCityId) {
		log.info("call search busSheetDetails:{},{},{},{}", scheduleId, busId, srcCityId, destCityId);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		BusScheduleDetails tripDetails = busService.scheduledBusSheetDetails(scheduleId, busId, srcCityId, destCityId);
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

	@PostMapping(value = "/bookedBusTicket")
	public ResponseEntity<RestResponse<Object>> bookedBusTicket(
			@RequestBody(required = true) CustomerBusTicketVO busTicketVO) {
		log.info("call search bookedBusTicket:{}", busTicketVO);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Bus Ticket booked Successfully");
		CustomerBusTicketVO customerBusTicketVO = busService.bookedBusTicket(busTicketVO);
		if (customerBusTicketVO != null)
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are no seats available in this bus. Please select a different bus.");
		return new ResponseEntity<>(new RestResponse<>(customerBusTicketVO, status), HttpStatus.OK);
	}

	@GetMapping(value = "/getTicketHistory/{uid}")
	public ResponseEntity<RestResponse<List<CustomerBusTicketVO>>> getBusTicketHistory(
			@PathVariable(name = "uid", required = true) String uid,
			@RequestParam(name = "limit", required = false, defaultValue = "5") int limit) {
		log.info("call search getBusTicketHistory:{}", uid);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Bus Ticket booked Successfully");
		List<CustomerBusTicketVO> customerBusTicketVOs = busService.getHistoryBusTicket(uid, limit);
		if (customerBusTicketVOs != null)
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are no seats available in this bus. Please select a different bus.");
		return new ResponseEntity<>(new RestResponse<>(customerBusTicketVOs, status), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteScheduleDeparture/{busId}")
	public ResponseEntity<RestResponse<Boolean>> deleteScheduleDeparture(@PathVariable(required = true) String busId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(),
				String.format("The bus with the number %s has been deleted", busId));
		boolean flag = busService.deleteScheduleDeparture(busId);
		if (!flag) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					String.format("Unable to delete the bus with bus number %s", busId));
			new ResponseEntity<>(new RestResponse<>(flag, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(flag, status), HttpStatus.OK);
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
