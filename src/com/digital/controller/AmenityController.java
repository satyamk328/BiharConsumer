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

import com.digital.model.BusAmenity;
import com.digital.model.vo.AmenitiesVo;
import com.digital.service.AmenityService;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v0/amenity")
@Slf4j
public class AmenityController {

	@Autowired
	private AmenityService amenityService;

	@GetMapping("/")
	public ResponseEntity<RestResponse<List<BusAmenity>>> getAllAmenity() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<BusAmenity> busAmenities = amenityService.getAllAmenity();
		log.debug("Data fetched successfully from Top cities table");
		return new ResponseEntity<>(new RestResponse<>(busAmenities, status), HttpStatus.OK);
	}

	@GetMapping("/{busId}")
	public ResponseEntity<RestResponse<List<BusAmenity>>> searchStation(
			@PathVariable(name = "busId", required = true) Long busId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<BusAmenity> busAmenities = amenityService.getAmenityByBusId(busId);
		return new ResponseEntity<>(new RestResponse<>(busAmenities, status), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<RestResponse<Object>> addSearchStation(@RequestBody(required=true) AmenitiesVo amenitiesVo) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Amenity Added Successfully");
		Long row = amenityService.addAmenity(amenitiesVo);
		if (row == null) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),"Amenity not Registered Successfully");
			return new ResponseEntity<>(new RestResponse<>(row, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(row, status), HttpStatus.OK);
	}
	
	@DeleteMapping("/{amenityId}/{busId}")
	public ResponseEntity<RestResponse<Object>> deleteCity(
			@PathVariable(name = "amenityId", required = true) Long amenityId,
			@PathVariable(name = "busId", required = true) Long busId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Records deleted Successfully");
		int delete = amenityService.deleteAmenity(amenityId, busId);
		return new ResponseEntity<>(new RestResponse<>(delete, status), HttpStatus.OK);
	}

}