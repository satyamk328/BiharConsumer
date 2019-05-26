package com.digital.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.AmenitiesDao;
import com.digital.dao.BusTripDao;
import com.digital.dao.CancelPolicyDao;
import com.digital.dao.CityDao;
import com.digital.model.Amenity;
import com.digital.model.BusDetails;
import com.digital.model.BusScheduleDetails;
import com.digital.model.CancelPolicies;
import com.digital.model.RoutedCity;
import com.digital.model.SeatDetails;
import com.digital.model.TicketDetails;
import com.digital.model.TripDetails;
import com.digital.model.vo.TicketVO;
import com.digital.utils.DataUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Service("busTripService")
@Slf4j
public class BusTripService {

	@Autowired
	private BusTripDao busBookingDao;

	@Autowired
	private AmenitiesDao amenitiesDao;

	@Autowired
	private CancelPolicyDao policyDao;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private DataUtils dataUtils;

	public TripDetails searchBusScheduleDetails(Long srcCityId, Long destCityId, String date) {
		TripDetails busDetailsObject = new TripDetails();
		log.info("call searchBusScheduleDetails {}, {}, {}", srcCityId, destCityId, date);
		List<BusScheduleDetails> busScheduleDetails = busBookingDao.searchTripBySrcDescAndDate(srcCityId, destCityId,
				date);

		busScheduleDetails.forEach(route -> {
			route.setBoardingLocations(route.getSrcStops() != null
					? cityDao.getCityStopDetails(route.getSourceId(), Arrays.asList(route.getSrcStops().split("::")))
					: new ArrayList<>());

			route.setDroppingLocations(route.getDestStops() != null ? cityDao.getCityStopDetails(
					route.getDestinationId(), Arrays.asList(route.getDestStops().split("::"))) : new ArrayList<>());

			route.setCancellationPolicy(
					route.getBusId() != null ? policyDao.getCancelPolicyByBusId(route.getBusId()) : new ArrayList<>());

			route.setAmenities(
					route.getBusId() != null ? amenitiesDao.getAmenitiesByBusId(route.getBusId()) : new ArrayList<>());

			route.setBusDetails(
					route.getBusId() != null ? busBookingDao.getBusDetailsByBusId(route.getBusId()) : new BusDetails());
			// populate city name
			route.setDestCityName(cityDao.getCityById(route.getDestinationId()).getDisplayName());
			route.setSrcCityName(cityDao.getCityById(route.getSourceId()).getDisplayName());
		});
		busDetailsObject.setAvailableRoutes(busScheduleDetails);
		busDetailsObject.setAmenitiesList(amenitiesDao.getAllAmenities());
		List<String> timeList = dataUtils.getTimeList();
		busDetailsObject.setArrivalTimeList(timeList);
		busDetailsObject.setDepartureTimeList(timeList);
		return busDetailsObject;
	}

	public BusScheduleDetails scheduledBusSeatDetails(Long scheduleId, Long busId, Long srcCityId, Long destCityId) {

		// TODO DB Query also
		BusScheduleDetails busDetails = busBookingDao.scheduledBusDetails(scheduleId, busId, srcCityId, destCityId);

		busDetails.setBoardingLocations(busDetails.getSrcStops() != null ? cityDao.getCityStopDetails(
				busDetails.getSourceId(), Arrays.asList(busDetails.getSrcStops().split("::"))) : new ArrayList<>());

		busDetails.setDroppingLocations(
				busDetails.getDestStops() != null ? cityDao.getCityStopDetails(busDetails.getDestinationId(),
						Arrays.asList(busDetails.getDestStops().split("::"))) : new ArrayList<>());

		busDetails.setCancellationPolicy(
				busDetails.getBusId() != null ? policyDao.getCancelPolicyByBusId(busDetails.getBusId())
						: new ArrayList<CancelPolicies>());

		busDetails.setAmenities(busDetails.getBusId() != null ? amenitiesDao.getAmenitiesByBusId(busDetails.getBusId())
				: new ArrayList<Amenity>());

		busDetails
				.setBusDetails(busDetails.getBusId() != null ? busBookingDao.getBusDetailsByBusId(busDetails.getBusId())
						: new BusDetails());
		// TODO validation of null data.
		busDetails.getBusDetails()
				.setSeatDetails(busBookingDao.getSeatDetailsByLayoutId(busDetails.getBusDetails().getLayoutId()));

		List<TicketDetails> ticketDetails = busBookingDao.getTicketDetailsByScheduleAndBusId(busDetails.getScheduleId(),
				busDetails.getBusId());

		// TODO null validations
		// Calculate seat details
		int bookedSeat = 0;
		List<RoutedCity> routedCities = busBookingDao.getTripCitiesBySrcDescCities(busDetails.getScheduleId(),
				busDetails.getSrcCitySequance(), busDetails.getDestCitySequance());
		X: for (SeatDetails seat : busDetails.getBusDetails().getSeatDetails()) {
			for (TicketDetails ticketDetail : ticketDetails) {
				if (seat.getSeatId() == ticketDetail.getSeatId()) {
					List<String> tripCitiesIds = Arrays.asList(ticketDetail.getTripId().split("::"));
					for (RoutedCity routedCity : routedCities) {
						if (tripCitiesIds.contains(routedCity.getCityId().toString())) {
							seat.setIsBooked(true);
							bookedSeat++;
							continue X;
						}
					}
				}
			}
		}
		busDetails.setTotalSeats(busDetails.getBusDetails().getSeatDetails().size());
		busDetails.setAvailableSeats(busDetails.getBusDetails().getSeatDetails().size() - bookedSeat);

		return busDetails;
	}

	public int bookTickets(TicketVO bookTicketVO) {
		// Logic to generate tripId
		List<RoutedCity> srcCitySeq = busBookingDao.getTripCitiySequanceByCityId(bookTicketVO.getScheduleId(),
				bookTicketVO.getSrcCityId());
		List<RoutedCity> destCitySeq = busBookingDao.getTripCitiySequanceByCityId(bookTicketVO.getScheduleId(),
				bookTicketVO.getDestCityId());
		List<RoutedCity> routedCities = busBookingDao.getTripCitiesBySrcDescCities(bookTicketVO.getScheduleId(),
				srcCitySeq.get(0).getCitySequance(), destCitySeq.get(0).getCitySequance());

		String tripId = "";
		for (RoutedCity routedCity : routedCities) {
			tripId = tripId + routedCity.getCityId() + "::";
		}
		tripId = tripId.substring(0, tripId.length() - 2);
		bookTicketVO.setTripId(tripId);

		return busBookingDao.bookTickets(bookTicketVO);
	}

	public int cancelTickets(TicketVO bookTicketVO) {
		return busBookingDao.cancelTickets(bookTicketVO);
	}

}
