package com.digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import com.digital.dao.AmenitiesDao;
import com.digital.dao.BusTripDao;
import com.digital.model.TripDetails;
import com.digital.model.BusScheduleDetails;
import com.digital.model.BusSeatDetails;
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

	@Cacheable("routesDetails")
	public TripDetails searchBusRoutDetails(Long sourceCityId, Long destinationCityId, String date) {
		TripDetails busDetailsObject = new TripDetails();
		List<BusScheduleDetails> filterRoutes = busBookingDao.searchTripBySrcDescAndDate(sourceCityId, destinationCityId, date);
		for(BusScheduleDetails route : filterRoutes) {
			route.setBoardingLocations(busBookingDao.getBusBoadingAndStopingPointDetails(route.getTripid().split("::")[0]));
			route.setDroppingLocations(busBookingDao.getBusBoadingAndStopingPointDetails(route.getTripid().split("::")[1]));
		    route.setCancellationPolicy(busBookingDao.getCancellationPolicy(route.getOperatorId()));
		    route.setAmenities(amenitiesDao.getAmenitiesByBusId(route.getOperatorId()));
		}
		busDetailsObject.setAvailableRoutes(filterRoutes);
		busDetailsObject.setAmenitiesList(amenitiesDao.getAllAmenities());
		List<String> timeList = dataUtils.getTimeList();
		busDetailsObject.setArrivalTimeList(timeList);
		busDetailsObject.setDepartureTimeList(timeList);
		return busDetailsObject;
	}
	
	@Cacheable("tripsDetails")
	public BusSeatDetails getSeatAvailability(SearchTripVO tripVO) {
		BusSeatDetails availability = new BusSeatDetails();
		availability.setBusSeatDetails(busBookingDao.getSeatsDetails(tripVO));
		availability.setBoardingPoints(busBookingDao.getBusBoadingAndStopingPointDetails(tripVO.getTripId().split("::")[0]));
		availability.setDroppingPoints(busBookingDao.getBusBoadingAndStopingPointDetails(tripVO.getTripId().split("::")[1]));
		return availability;
	}
	
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
