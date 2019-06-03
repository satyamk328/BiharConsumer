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

import com.digital.model.ScheduleBusObject;
import com.digital.model.ScheduleSeatDetails;
import com.digital.model.vo.SearchTripVO;
import com.digital.service.BusScheduleService;
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
public class BusScheduleController {

	@Autowired
	private BusScheduleService busService;

	@GetMapping(value = "/route/{source}/{destination}/{date}")
	public ResponseEntity<RestResponse<ScheduleBusObject>> searchBusScheduletDetails(
			@PathVariable(name = "source", required = true) Long srcCityId,
			@PathVariable(name = "destination", required = true) Long destCityId,
			@PathVariable(name = "date", required = true) String date) {
		log.info("call search searchBusScheduletDetails:{},{},{}", srcCityId, destCityId, date);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		ScheduleBusObject tripDetails = busService.searchBusScheduleDetails(srcCityId, destCityId, date);
		if (tripDetails.getAvailableRoutes().isEmpty())
			status = new RestStatus<>(HttpStatus.OK.toString(), String.format(
					"There are no buses between these two cities. Please try a different date or search with an alternate route."));
		return new ResponseEntity<>(new RestResponse<>(tripDetails, status), HttpStatus.OK);
	}

	@PostMapping(value = "/trip")
	public ResponseEntity<RestResponse<ScheduleSeatDetails>> scheduledBusSheetDetails(@RequestBody(required=true) SearchTripVO tripVO) {
		log.info("call search busSheetDetails:{},{},{},{}", tripVO.getScheduleId(), tripVO.getBusId(), tripVO.getSourceId(), tripVO.getDestinationId());
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		ScheduleSeatDetails scheduleSeatDetails = busService.scheduledBusSeatDetails(tripVO);
		if (scheduleSeatDetails == null)
			status = new RestStatus<>(HttpStatus.OK.toString(), String.format(
					"There are no buses between these two cities. Please try a different date or search with an alternate route."));
		return new ResponseEntity<>(new RestResponse<>(scheduleSeatDetails, status), HttpStatus.OK);
	}
}
