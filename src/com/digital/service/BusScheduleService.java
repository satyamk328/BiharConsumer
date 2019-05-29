package com.digital.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.AmenitiesDao;
import com.digital.dao.BusDao;
import com.digital.dao.BusScheduleDao;
import com.digital.dao.CancelPolicyDao;
import com.digital.dao.CityDao;
import com.digital.dao.SeatDao;
import com.digital.dao.TicketDao;
import com.digital.model.BusDetails;
import com.digital.model.BusScheduleDetails;
import com.digital.model.RoutedCity;
import com.digital.model.ScheduleBusDetails;
import com.digital.model.ScheduleSeatDetails;
import com.digital.model.SeatDetails;
import com.digital.model.TicketDetails;
import com.digital.model.vo.SearchTripVO;
import com.digital.utils.DataUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Service
@Slf4j
public class BusScheduleService {

	@Autowired
	private BusScheduleDao busBookingDao;

	@Autowired
	private AmenitiesDao amenitiesDao;

	@Autowired
	private CancelPolicyDao policyDao;

	@Autowired
	private CityDao cityDao;

	@Autowired
	private TicketDao bookingDao;

	@Autowired
	private SeatDao seatDao;

	@Autowired
	private BusDao busDao;

	@Autowired
	private DataUtils dataUtils;

	public ScheduleBusDetails searchBusScheduleDetails(Long srcCityId, Long destCityId, String date) {
		ScheduleBusDetails busDetailsObject = new ScheduleBusDetails();
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
					route.getBusId() != null ? busDao.getBusDetailsByBusId(route.getBusId()) : new BusDetails());
			// populate city name
			route.setDestCityName(cityDao.getCityById(route.getDestinationId()).getDisplayName());
			route.setSrcCityName(cityDao.getCityById(route.getSourceId()).getDisplayName());

			List<SeatDetails> seatDetails = seatDao.getSeatDetailsByLayoutId(route.getBusDetails().getLayoutId());

			List<TicketDetails> ticketDetails = bookingDao.getTicketDetailsByScheduleAndBusId(route.getScheduleId(),
					route.getBusId());

			// Calculate seat details
			int bookedSeat = 0;
			List<RoutedCity> routedCities = busBookingDao.getTripCitiesBySrcDescCities(route.getScheduleId(),
					route.getSrcCitySequance(), route.getDestCitySequance());
			X: for (SeatDetails seat : seatDetails) {
				for (TicketDetails ticketDetail : ticketDetails) {
					if (seat.getSeatId() == ticketDetail.getSeatId()) {
						List<String> tripCitiesIds = Arrays.asList(ticketDetail.getTripId().split("::"));
						int i = -1;
						for (RoutedCity routedCity : routedCities) {
							i++;
							if (i == 0 && tripCitiesIds.get(0)
									.equals(routedCities.get(routedCities.size() - 1).getCityId().toString())) {
								continue;
							} else if (i == (routedCities.size() - 1) && tripCitiesIds.get(tripCitiesIds.size() - 1)
									.equals(routedCities.get(0).getCityId().toString())) {
								continue;
							}
							if (tripCitiesIds.contains(routedCity.getCityId().toString())) {
								seat.setIsBooked(true);
								bookedSeat++;
								continue X;
							}
						}
					}
				}
			}
			route.setTotalSeats(seatDetails.size());
			route.setAvailableSeats(seatDetails.size() - bookedSeat);

		});
		busDetailsObject.setAvailableRoutes(busScheduleDetails);
		busDetailsObject.setAmenitiesList(amenitiesDao.getAllAmenities());
		List<String> timeList = dataUtils.getTimeList();
		busDetailsObject.setArrivalTimeList(timeList);
		busDetailsObject.setDepartureTimeList(timeList);
		return busDetailsObject;
	}

	public ScheduleSeatDetails scheduledBusSeatDetails(SearchTripVO tripVO) {

		ScheduleSeatDetails scheduleSeatDetails = new ScheduleSeatDetails();
		// TODO DB Query also
		BusScheduleDetails busDetails = busBookingDao.scheduledBusDetails(tripVO.getScheduleId(), tripVO.getBusId(),
				tripVO.getSourceId(), tripVO.getDestinationId());

		scheduleSeatDetails.setBoardingPoints(busDetails.getSrcStops() != null ? cityDao.getCityStopDetails(
				busDetails.getSourceId(), Arrays.asList(busDetails.getSrcStops().split("::"))) : new ArrayList<>());

		scheduleSeatDetails.setDroppingPoints(
				busDetails.getDestStops() != null ? cityDao.getCityStopDetails(busDetails.getDestinationId(),
						Arrays.asList(busDetails.getDestStops().split("::"))) : new ArrayList<>());
		busDetails.setBusDetails(
				busDetails.getBusId() != null ? busDao.getBusDetailsByBusId(busDetails.getBusId()) : new BusDetails());

		List<SeatDetails> seatDetails = seatDao.getSeatDetailsByLayoutId(busDetails.getBusDetails().getLayoutId());
		scheduleSeatDetails.setBusSeatDetails(seatDetails);
		List<TicketDetails> ticketDetails = bookingDao.getTicketDetailsByScheduleAndBusId(busDetails.getScheduleId(),
				busDetails.getBusId());

		List<RoutedCity> routedCities = busBookingDao.getTripCitiesBySrcDescCities(busDetails.getScheduleId(),
				busDetails.getSrcCitySequance(), busDetails.getDestCitySequance());
		X: for (SeatDetails seat : seatDetails) {
			for (TicketDetails ticketDetail : ticketDetails) {
				if (seat.getSeatId() == ticketDetail.getSeatId()) {
					List<String> tripCitiesIds = Arrays.asList(ticketDetail.getTripId().split("::"));
					for (RoutedCity routedCity : routedCities) {
						if (tripCitiesIds.contains(routedCity.getCityId().toString())) {
							seat.setIsBooked(true);
							seat.setIsReservedForLadies(
									"Female".equalsIgnoreCase(ticketDetail.getGender()) ? true : false);
							continue X;
						}
					}
				}
			}
		}
		return scheduleSeatDetails;
	}

}
