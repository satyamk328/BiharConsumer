package com.digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import com.digital.dao.BusDao;
import com.digital.model.BusDetailsObject;
import com.digital.model.BusRouteDetails;
import com.digital.model.BusSeatDetailsObject;
import com.digital.model.vo.CustomerBusTicketVO;
import com.digital.model.vo.SearchTripVO;
import com.digital.utils.DataUtils;
/**
 * @author Satyam Kumar
 *
 */
@Service
public class BusService {

	@Autowired
	private BusDao busBookingDao;
	
	@Autowired
	private DataUtils dataUtils;

	@Cacheable("routesDetails")
	public BusDetailsObject searchBusRoutDetails(String source, String destination, String date) {
		BusDetailsObject busDetailsObject = new BusDetailsObject();
		List<BusRouteDetails> filterRoutes = busBookingDao.searchTriBySrcDescAndDate(source, destination, date);
		for(BusRouteDetails route : filterRoutes) {
			route.setBoardingLocations(busBookingDao.getBusBoadingAndStopingPointDetails(route.getTripid().split("::")[0]));
			route.setDroppingLocations(busBookingDao.getBusBoadingAndStopingPointDetails(route.getTripid().split("::")[1]));
		    route.setCancellationPolicy(busBookingDao.getCancellationPolicy(route.getOperatorId()));
		    route.setAmenities(busBookingDao.getBusFilterAmenitiesByBusId(route.getOperatorId()));
		}
		busDetailsObject.setAvailableRoutes(filterRoutes);
		busDetailsObject.setAmenitiesList(busBookingDao.getAllAmenities());
		List<String> timeList = dataUtils.getTimeList();
		busDetailsObject.setArrivalTimeList(timeList);
		busDetailsObject.setDepartureTimeList(timeList);
		return busDetailsObject;
	}
	
	@Cacheable("tripsDetails")
	public BusSeatDetailsObject getSeatAvailability(SearchTripVO tripVO) {
		BusSeatDetailsObject availability = new BusSeatDetailsObject();
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
