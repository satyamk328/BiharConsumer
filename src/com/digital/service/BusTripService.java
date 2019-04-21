package com.digital.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.digital.dao.AmenitiesDao;
import com.digital.dao.BusTripDao;
import com.digital.model.BusAmenity;
import com.digital.model.BusCancellationPolicies;
import com.digital.model.BusCityStopLocationsDetails;
import com.digital.model.BusScheduleDetails;
import com.digital.model.BusSeatDetails;
import com.digital.model.TripDetails;
import com.digital.model.vo.CustomerBusTicketVO;
import com.digital.model.vo.SearchTripVO;
import com.digital.utils.DataUtils;
/**
 * @author Satyam Kumar
 *
 */
@Service
public class BusTripService {

	@Autowired
	private BusTripDao busBookingDao;
	
	@Autowired
	private AmenitiesDao amenitiesDao;
	
	@Autowired
	private DataUtils dataUtils;

	//@Cacheable("routesDetails")
	public TripDetails searchBusScheduletDetails(Long srcCityId, Long destCityId, String date) {
		TripDetails busDetailsObject = new TripDetails();
		
		List<BusScheduleDetails> busScheduleDetails = busBookingDao.searchTripBySrcDescAndDate(srcCityId, destCityId, date);
		
		for(BusScheduleDetails route : busScheduleDetails) {
			route.setBoardingLocations(route.getSrcStops() != null ? busBookingDao.getBusBoadingAndStopingPointDetails(route.getSrcCity(), Arrays.asList(route.getSrcStops().split("::"))):new ArrayList<BusCityStopLocationsDetails>());
			route.setDroppingLocations(route.getDestStops() != null ? busBookingDao.getBusBoadingAndStopingPointDetails(route.getDestCity(), Arrays.asList(route.getDestStops().split("::"))):new ArrayList<BusCityStopLocationsDetails>());
		    route.setCancellationPolicy(route.getBusId() != null ?busBookingDao.getCancellationPolicy(route.getBusId()) : new ArrayList<BusCancellationPolicies>());
		    route.setAmenities(route.getBusId() != null ? amenitiesDao.getAmenitiesByBusId(route.getBusId()) : new ArrayList<BusAmenity>());
		}
		
		busDetailsObject.setAvailableRoutes(busScheduleDetails);
		busDetailsObject.setAmenitiesList(amenitiesDao.getAllAmenities());
		List<String> timeList = dataUtils.getTimeList();
		busDetailsObject.setArrivalTimeList(timeList);
		busDetailsObject.setDepartureTimeList(timeList);
		return busDetailsObject;
	}
	
	@Cacheable("tripsDetails")
	public BusSeatDetails getSeatAvailability(SearchTripVO tripVO) {
		return null;
		/*
		BusSeatDetails availability = new BusSeatDetails();
		availability.setBusSeatDetails(busBookingDao.getSeatsDetails(tripVO));
		availability.setBoardingPoints(busBookingDao.getBusBoadingAndStopingPointDetails(tripVO.getTripId().split("::")[0]));
		availability.setDroppingPoints(busBookingDao.getBusBoadingAndStopingPointDetails(tripVO.getTripId().split("::")[1]));
		return availability;
	*/}
	
	public CustomerBusTicketVO bookedBusTicket(CustomerBusTicketVO busVO){
		return busBookingDao.bookedBusTicket(busVO);
	}
	
	public List<CustomerBusTicketVO> getHistoryBusTicket(String uid, int limit){
		return busBookingDao.getHistoryBusTicket(uid, limit);
	}

	public boolean deleteScheduleDeparture(String busId) {
		return busBookingDao.deleteScheduleDeparture(busId);
	}
}
