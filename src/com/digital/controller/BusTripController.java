package com.digital.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.model.BusSeatDetails;
import com.digital.model.TripDetails;
import com.digital.model.vo.CustomerBusTicketVO;
import com.digital.model.vo.SearchTripVO;
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
	public ResponseEntity<RestResponse<TripDetails>> searchBusRoutDetails(
			@PathVariable(name = "source", required = true) Long sourceCityId,
			@PathVariable(name = "destination", required = true) Long destinationCityId,
			@PathVariable(name = "date", required = true) String date) {
		log.info("call search searchBusRoutDetails:{},{},{}", sourceCityId, destinationCityId, date);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		TripDetails tripDetails = busService.searchBusRoutDetails(sourceCityId, destinationCityId, date);
		if (tripDetails.getAvailableRoutes().isEmpty())
			status = new RestStatus<>(HttpStatus.OK.toString(), String.format(
					"There are no buses between these two cities. Please try a different date or search with an alternate route."));
		return new ResponseEntity<>(new RestResponse<>(tripDetails, status), HttpStatus.OK);
	}

	@PostMapping(value = "/trip")
	public ResponseEntity<RestResponse<BusSeatDetails>> getSeatAvailability(
			@RequestBody(required = true) SearchTripVO tripVO) {
		log.info("call search getSeatAvailability:{} ", tripVO);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		BusSeatDetails busSeatDetailsAvailability = busService.getSeatAvailability(tripVO);
		if (busSeatDetailsAvailability.getBusSeatDetails() == null) {
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are no seats available in this bus. Please select a different bus.");
		}
		return new ResponseEntity<>(new RestResponse<>(busSeatDetailsAvailability, status), HttpStatus.OK);
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

	
	@PostMapping(value = "/scheduleDeparture")
	public ResponseEntity<RestResponse<Map<String, String>>> scheduleDeparture(@RequestBody Object bus) {

		// Map<String, String> statusMsg = BusControllerRepo.scheduleDeparture(bus);
		return null;
	}

	@PutMapping(value = "/editScheduleDeparture")
	public ResponseEntity<RestResponse<Boolean>> editScheduleDeparture(@RequestBody Map<String, Object> mapBusObj) {

		// BusDAO busObj = mapBusObj.get("busObj");
		// BusDAO oldBusObj = mapBusObj.get("oldBusObj");
		// Map<String, String> statusMsg =
		// BusControllerRepo.editScheduleDeparture(busObj, oldBusObj);
		return null;
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
}
