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
import org.springframework.web.bind.annotation.RestController;

import com.digital.model.City;
import com.digital.service.CityService;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;
import com.digital.utils.GlobalConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Api(value = "topCity")
@RestController
@RequestMapping(value = "/api/v0/cities")
@Slf4j
public class CityController {

	@Autowired
	private CityService cityService;

	@ApiOperation(value = "Get All City", notes = "This API is used to get all citiess")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request fulfilled/processed successfully"),
			@ApiResponse(code = 400, message = "There is at least one invalid parameter"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Message not found/intended resource not found "),
			@ApiResponse(code = 500, message = "General exceptions "),
			@ApiResponse(code = 504, message = "Gateway timeout error"),
			@ApiResponse(code = 503, message = "Digital Bihar Service is not available") })
	@GetMapping("/")
	public ResponseEntity<RestResponse<List<City>>> getAllCities() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<City> searchStations = cityService.getAllCities();
		log.debug("Data fetched successfully from Top cities table");
		return new ResponseEntity<>(new RestResponse<>(searchStations, status), HttpStatus.OK);
	}

	@ApiOperation(value = "Add City", notes = "This API is used to add citiess")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request fulfilled/processed successfully"),
			@ApiResponse(code = 400, message = "There is at least one invalid parameter"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Message not found/intended resource not found "),
			@ApiResponse(code = 500, message = "General exceptions "),
			@ApiResponse(code = 504, message = "Gateway timeout error"),
			@ApiResponse(code = 503, message = "Digital Bihar Service is not available") })
	@PostMapping("/")
	public ResponseEntity<RestResponse<Object>> addSearchStation(@RequestBody(required = true) City busStop) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Top Cities Station Added Successfully");
		long integer = cityService.save(busStop);
		if (integer == 0) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), GlobalConstants.ERROR_MESSAGE);
			return new ResponseEntity<>(new RestResponse<>(integer, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(integer, status), HttpStatus.OK);
	}

	@ApiOperation(value = "Search by City Name", notes = "This API is used to search by city name")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request fulfilled/processed successfully"),
			@ApiResponse(code = 400, message = "There is at least one invalid parameter"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Message not found/intended resource not found "),
			@ApiResponse(code = 500, message = "General exceptions "),
			@ApiResponse(code = 504, message = "Gateway timeout error"),
			@ApiResponse(code = 503, message = "Digital Bihar Service is not available") })
	@GetMapping("/{stationName}")
	public ResponseEntity<RestResponse<List<City>>> searchStation(
			@PathVariable(name = "stationName", required = true) String stationName) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<City> searchStations = cityService.getCityByName(stationName);
		return new ResponseEntity<>(new RestResponse<>(searchStations, status), HttpStatus.OK);
	}

	@ApiOperation(value = "Det by City id", notes = "This API is used to get by city id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request fulfilled/processed successfully"),
			@ApiResponse(code = 400, message = "There is at least one invalid parameter"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Message not found/intended resource not found "),
			@ApiResponse(code = 500, message = "General exceptions "),
			@ApiResponse(code = 504, message = "Gateway timeout error"),
			@ApiResponse(code = 503, message = "Digital Bihar Service is not available") })
	@GetMapping("/{cityId}")
	public ResponseEntity<RestResponse<City>> getCityById(
			@PathVariable(name = "cityId", required = true) long cityId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Records fetch Successfully");
		City topCities = cityService.getCityById(cityId);
		return new ResponseEntity<>(new RestResponse<>(topCities, status), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete by City id", notes = "This API is used to delete by city id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request fulfilled/processed successfully"),
			@ApiResponse(code = 400, message = "There is at least one invalid parameter"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "Message not found/intended resource not found "),
			@ApiResponse(code = 500, message = "General exceptions "),
			@ApiResponse(code = 504, message = "Gateway timeout error"),
			@ApiResponse(code = 503, message = "Digital Bihar Service is not available") })
	@DeleteMapping("/{cityId}")
	public ResponseEntity<RestResponse<Object>> deleteCity(
			@PathVariable(name = "cityId", required = true) long cityId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Records deleted Successfully");
		int delete = cityService.delete(cityId);
		return new ResponseEntity<>(new RestResponse<>(delete, status), HttpStatus.OK);
	}
}
