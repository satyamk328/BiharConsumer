package com.digital.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.AmenitiesDao;
import com.digital.dao.BusTripDao;
import com.digital.model.BusAmenity;
import com.digital.model.BusCancellationPolicies;
import com.digital.model.BusCityStopLocationsDetails;
import com.digital.model.BusDetails;
import com.digital.model.BusScheduleDetails;
import com.digital.model.RoutedCity;
import com.digital.model.SeatDetails;
import com.digital.model.TicketDetails;
import com.digital.model.TripDetails;
import com.digital.model.vo.TicketVO;
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

	public TripDetails searchBusScheduleDetails(Long srcCityId, Long destCityId, String date) {
		TripDetails busDetailsObject = new TripDetails();

		List<BusScheduleDetails> busScheduleDetails = busBookingDao.searchTripBySrcDescAndDate(srcCityId, destCityId,
				date);

		busScheduleDetails.forEach(route -> {
			route.setBoardingLocations(route.getSrcStops() != null
					? busBookingDao.getBusBoadingAndStopingPointDetails(route.getSourceId(),
							Arrays.asList(route.getSrcStops().split("::")))
					: new ArrayList<BusCityStopLocationsDetails>());

			route.setDroppingLocations(route.getDestStops() != null
					? busBookingDao.getBusBoadingAndStopingPointDetails(route.getDestinationId(),
							Arrays.asList(route.getDestStops().split("::")))
					: new ArrayList<BusCityStopLocationsDetails>());

			route.setCancellationPolicy(route.getBusId() != null ? busBookingDao.getCancellationPolicy(route.getBusId())
					: new ArrayList<BusCancellationPolicies>());

			route.setAmenities(route.getBusId() != null ? amenitiesDao.getAmenitiesByBusId(route.getBusId())
					: new ArrayList<BusAmenity>());

			route.setBusDetails(
					route.getBusId() != null ? busBookingDao.getBusDetailsByBusId(route.getBusId()) : new BusDetails());

			/////////////////////////////////
			/*route.getBusDetails()
					.setSeatDetails(busBookingDao.getSeatDetailsByLayoutId(route.getBusDetails().getLayoutId()));

			List<TicketDetails> ticketDetails = busBookingDao.getTicketDetailsByScheduleAndBusId(route.getScheduleId(),
					route.getBusId());

			TODO null validations
			Calculate seat details
			int bookedSeat = 0;
			List<RoutedCity> routedCities = busBookingDao.getTripCitiesBySrcDescCities(route.getScheduleId(),
					route.getSrcCitySequance(), route.getDestCitySequance());
			X: for (SeatDetails seat : route.getBusDetails().getSeatDetails()) {
				for (TicketDetails ticketDetail : ticketDetails) {
					if (seat.getSeatId() == ticketDetail.getSeatId()) {
						List<String> tripCitiesIds = Arrays.asList(ticketDetail.getTripId().split("::"));
						for (RoutedCity routedCity : routedCities) {
							if (tripCitiesIds.contains(routedCity.getCityId().toString())) {
								seat.setIsBooked(1);
								bookedSeat++;
								continue X;
							}
						}
					}
				}
			}
			route.setTotalSeats(route.getBusDetails().getSeatDetails().size());
			route.setAvailableSeats(route.getBusDetails().getSeatDetails().size() - bookedSeat);*/
		});
		///////////////////////////////
		busDetailsObject.setAvailableRoutes(busScheduleDetails);
		busDetailsObject.setAmenitiesList(amenitiesDao.getAllAmenities());
		List<String> timeList = dataUtils.getTimeList();
		busDetailsObject.setArrivalTimeList(timeList);
		busDetailsObject.setDepartureTimeList(timeList);

		return busDetailsObject;
	}

	public BusScheduleDetails scheduledBusSheetDetails(Long scheduleId, Long busId, Long srcCityId, Long destCityId) {

		// TODO DB Query also
		BusScheduleDetails busDetails = busBookingDao.scheduledBusDetails(scheduleId, busId, srcCityId, destCityId);

		busDetails.setBoardingLocations(busDetails.getSrcStops() != null
				? busBookingDao.getBusBoadingAndStopingPointDetails(busDetails.getSourceId(),
						Arrays.asList(busDetails.getSrcStops().split("::")))
				: new ArrayList<BusCityStopLocationsDetails>());

		busDetails.setDroppingLocations(busDetails.getDestStops() != null
				? busBookingDao.getBusBoadingAndStopingPointDetails(busDetails.getDestinationId(),
						Arrays.asList(busDetails.getDestStops().split("::")))
				: new ArrayList<BusCityStopLocationsDetails>());

		busDetails.setCancellationPolicy(
				busDetails.getBusId() != null ? busBookingDao.getCancellationPolicy(busDetails.getBusId())
						: new ArrayList<BusCancellationPolicies>());

		busDetails.setAmenities(busDetails.getBusId() != null ? amenitiesDao.getAmenitiesByBusId(busDetails.getBusId())
				: new ArrayList<BusAmenity>());

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
							seat.setIsBooked(1);
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
