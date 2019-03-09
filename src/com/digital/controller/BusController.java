package com.digital.controller;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.http.HttpHeaders.CACHE_CONTROL;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.EXPIRES;
import static org.springframework.http.HttpHeaders.PRAGMA;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.digital.model.BusDetailsObject;
import com.digital.model.BusSeatDetailsObject;
import com.digital.model.vo.CustomerBusTicketVO;
import com.digital.model.vo.SearchTripVO;
import com.digital.service.BusService;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;
import com.digital.utils.CommonUtil;

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
public class BusController {

	@Autowired
	private BusService busService;

	@GetMapping(value = "/route/{source}/{destination}/{date}")
	public ResponseEntity<RestResponse<BusDetailsObject>> searchBusRoutDetails(
			@PathVariable(name = "source", required = true) String source,
			@PathVariable(name = "destination", required = true) String destination,
			@PathVariable(name = "date", required = true) String date) {
		log.info("call search searchBusRoutDetails:{},{},{}", source, destination, date);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		BusDetailsObject busDetailsObject = busService.searchBusRoutDetails(source, destination, date);
		if (busDetailsObject.getAvailableRoutes().isEmpty())
			status = new RestStatus<>(HttpStatus.OK.toString(), String.format(
					"There are no buses between these two cities. Please try a different date or search with an alternate route."));
		return new ResponseEntity<>(new RestResponse(busDetailsObject, status), HttpStatus.OK);
	}

	@PostMapping(value = "/trip")
	public ResponseEntity<RestResponse<BusSeatDetailsObject>> getSeatAvailability(
			@RequestBody(required = true) SearchTripVO tripVO) {
		log.info("call search getSeatAvailability:{} ", tripVO);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		BusSeatDetailsObject busSeatDetailsAvailability = busService.getSeatAvailability(tripVO);
		if (busSeatDetailsAvailability.getBusSeatDetails() == null) {
			status = new RestStatus<>(HttpStatus.OK.toString(),
					"There are no seats available in this bus. Please select a different bus.");
		}
		return new ResponseEntity<>(new RestResponse(busSeatDetailsAvailability, status), HttpStatus.OK);
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
		return new ResponseEntity<>(new RestResponse(customerBusTicketVO, status), HttpStatus.OK);
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
		return new ResponseEntity<>(new RestResponse(customerBusTicketVOs, status), HttpStatus.OK);
	}

	@GetMapping(value = "generateTicket")
	public HttpEntity<byte[]> generateTicket(@RequestBody(required = false) Object objCombined) throws IOException {
		String name = "customrname";
		int ticketId = 1;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.add(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT");
		headers.add(ACCESS_CONTROL_ALLOW_HEADERS, CONTENT_TYPE);
		headers.add(CACHE_CONTROL, "no-cache, no-store, must-revalidate");
		headers.add(PRAGMA, "no-cache");
		headers.add(EXPIRES, "0");

		headers.add("Content-Disposition", String.format("inline; filename=" + name + "_ticket.pdf"));

		byte[] ticketPdf = null;

		if (ticketId > 0) {

			ticketPdf = CommonUtil.generatePdf(null, null, ticketId);
			headers.setContentLength(ticketPdf.length);
			return new HttpEntity<byte[]>(ticketPdf, headers);
		} else {
			return null;
		}

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
			new ResponseEntity<>(new RestResponse(flag, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse(flag, status), HttpStatus.OK);
	}
}
