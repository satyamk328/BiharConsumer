package com.digital.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.BusDao;
import com.digital.dao.BusScheduleDao;
import com.digital.dao.CancelPolicyDao;
import com.digital.dao.SeatDao;
import com.digital.dao.TicketDao;
import com.digital.model.BusMaster;
import com.digital.model.ScheduleBusDetails;
import com.digital.model.ScheduleBusObject;
import com.digital.model.ScheduleSeatDetails;
import com.digital.model.SeatMaster;
import com.digital.model.TicketDetails;
import com.digital.model.TripMaster;
import com.digital.model.vo.SearchTripVO;
import com.digital.utils.DataUtils;
import com.digital.utils.GlobalConstants;

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
	private AmenityService amenitiesService;

	@Autowired
	private CancelPolicyDao policyDao;

	@Autowired
	private CityService cityService;

	@Autowired
	private TicketDao bookingDao;

	@Autowired
	private SeatDao seatDao;

	@Autowired
	private BusDao busDao;

	@Autowired
	private DataUtils dataUtils;

	public ScheduleBusObject searchBusScheduleDetails(Long srcCityId, Long destCityId, String date) {
		ScheduleBusObject busDetailsObject = new ScheduleBusObject();
		log.info("call searchBusScheduleDetails {}, {}, {}", srcCityId, destCityId, date);
		List<ScheduleBusDetails> busScheduleDetails = busBookingDao.searchTripBySrcDescAndDate(srcCityId, destCityId,
				date);

		busScheduleDetails.forEach(route -> {
			route.setBoardingLocations(
					route.getSrcStops() != null && !"".equals(route.getSrcStops().trim())
							? cityService.getCityStopDetails(route.getSourceId(),
									Arrays.asList(route.getSrcStops().split(GlobalConstants.SEPARATOR)))
							: cityService.getCityStopByCityId(route.getSourceId()));

			route.setDroppingLocations(
					route.getDestStops() != null && !"".equals(route.getSrcStops().trim())
							? cityService.getCityStopDetails(route.getDestinationId(),
									Arrays.asList(route.getDestStops().split(GlobalConstants.SEPARATOR)))
							: cityService.getCityStopByCityId(route.getDestinationId()));

			route.setCancellationPolicy(
					route.getBusId() != null ? policyDao.getCancelPolicyByBusId(route.getBusId()) : new ArrayList<>());

			route.setAmenities(
					route.getBusId() != null ? amenitiesService.getAmenitiesByBusId(route.getBusId()) : new ArrayList<>());

			route.setBusDetails(
					route.getBusId() != null ? busDao.getBusDetailsByBusId(route.getBusId()) : new BusMaster());
			// populate city name
			route.setDestCityName(cityService.getCityById(route.getDestinationId()).getDisplayName());
			route.setSrcCityName(cityService.getCityById(route.getSourceId()).getDisplayName());

			List<SeatMaster> seatDetails = seatDao.getSeatDetailsByLayoutId(route.getBusDetails().getLayoutId());

			List<TicketDetails> ticketDetails = bookingDao.getTicketDetailsByScheduleAndBusId(route.getScheduleId(),
					route.getBusId());

			// Calculate seat details
			int bookedSeat = 0;
			List<TripMaster> routedCities = busBookingDao.getTripCitiesBySrcDescCities(route.getScheduleId(),
					route.getSrcCitySequance(), route.getDestCitySequance());
			X: for (SeatMaster seat : seatDetails) {
				for (TicketDetails ticketDetail : ticketDetails) {
					if (seat.getSeatId() == ticketDetail.getSeatId()) {
						List<String> tripCitiesIds = Arrays
								.asList(ticketDetail.getTripId().split(GlobalConstants.SEPARATOR));
						for (TripMaster routedCity : routedCities) {
							if (tripCitiesIds.contains(routedCity.getCityId().toString())) {
								if (tripCitiesIds.get(0)
										.equals(routedCities.get(routedCities.size() - 1).getCityId().toString())) {
									seat.setIsBooked(false);
									continue X;
								} else if (tripCitiesIds.get(tripCitiesIds.size() - 1)
										.equals(routedCities.get(0).getCityId().toString())) {
									seat.setIsBooked(false);
									continue X;
								}
								seat.setIsBooked(true);
								seat.setIsReservedForLadies(
										"F".equalsIgnoreCase(ticketDetail.getGender()) ? true : false);
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
		busDetailsObject.setAmenitiesList(amenitiesService.getAllAmenities());
		List<String> timeList = dataUtils.getTimeList();
		busDetailsObject.setArrivalTimeList(timeList);
		busDetailsObject.setDepartureTimeList(timeList);
		return busDetailsObject;
	}

	public ScheduleSeatDetails scheduledBusSeatDetails(SearchTripVO tripVO) {

		ScheduleSeatDetails scheduleSeatDetails = new ScheduleSeatDetails();
		// DB Query also
		ScheduleBusDetails busDetails = busBookingDao.scheduledBusDetails(tripVO.getScheduleId(), tripVO.getBusId(),
				tripVO.getSourceId(), tripVO.getDestinationId());

		scheduleSeatDetails
				.setBoardingPoints(busDetails.getSrcStops() != null && !"".equals(busDetails.getSrcStops().trim())
						? cityService.getCityStopDetails(busDetails.getSourceId(),
								Arrays.asList(busDetails.getSrcStops().split(GlobalConstants.SEPARATOR)))
						: cityService.getCityStopByCityId(busDetails.getSourceId()));

		scheduleSeatDetails
				.setDroppingPoints(busDetails.getDestStops() != null && !"".equals(busDetails.getSrcStops().trim())
						? cityService.getCityStopDetails(busDetails.getDestinationId(),
								Arrays.asList(busDetails.getDestStops().split(GlobalConstants.SEPARATOR)))
						: cityService.getCityStopByCityId(busDetails.getDestinationId()));
		busDetails.setBusDetails(
				busDetails.getBusId() != null ? busDao.getBusDetailsByBusId(busDetails.getBusId()) : new BusMaster());

		List<SeatMaster> seatDetails = seatDao.getSeatDetailsByLayoutId(busDetails.getBusDetails().getLayoutId());
		scheduleSeatDetails.setBusSeatDetails(seatDetails);
		List<TicketDetails> ticketDetails = bookingDao.getTicketDetailsByScheduleAndBusId(busDetails.getScheduleId(),
				busDetails.getBusId());

		List<TripMaster> routedCities = busBookingDao.getTripCitiesBySrcDescCities(busDetails.getScheduleId(),
				busDetails.getSrcCitySequance(), busDetails.getDestCitySequance());
		X: for (SeatMaster seat : seatDetails) {
			populateFare(seat, busDetails);
			for (TicketDetails ticketDetail : ticketDetails) {
				if (seat.getSeatId() == ticketDetail.getSeatId()) {
					List<String> tripCitiesIds = Arrays
							.asList(ticketDetail.getTripId().split(GlobalConstants.SEPARATOR));
					for (TripMaster routedCity : routedCities) {
						if (tripCitiesIds.contains(routedCity.getCityId().toString())) {
							if (tripCitiesIds.get(0)
									.equals(routedCities.get(routedCities.size() - 1).getCityId().toString())) {
								seat.setIsBooked(false);
								continue X;
							} else if (tripCitiesIds.get(tripCitiesIds.size() - 1)
									.equals(routedCities.get(0).getCityId().toString())) {
								seat.setIsBooked(false);
								continue X;
							}
							seat.setIsBooked(true);
							seat.setIsReservedForLadies(
									"F".equalsIgnoreCase(ticketDetail.getGender()) ? true : false);
							continue X;
						}
					}
					
				}
			}
		}
		return scheduleSeatDetails;
	}

	private void populateFare(SeatMaster seat, ScheduleBusDetails busDetails) {
		if (seat.getSeatType().equalsIgnoreCase("SS"))
			seat.setFare(busDetails.getSeaterFare());
		else
			seat.setFare(busDetails.getSleeperFare());
	}
}
