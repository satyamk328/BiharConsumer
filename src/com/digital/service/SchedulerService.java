package com.digital.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.digital.dao.BusScheduleDao;
import com.digital.model.ScheduleMaster;
import com.digital.model.TicketDetails;
import com.digital.model.TripMaster;
import com.digital.utils.CommonUtil;
import com.smattme.MysqlExportService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Component
@Slf4j
public class SchedulerService {

	@Value("${jdbc.username}")
	private String mysqlUserName;
	@Value("${jdbc.password}")
	private String mysqlPassword;
	@Value("${jdbc.driverClassName}")
	private String mysqlDriverName;
	@Value("${backup.driver.path}")
	private String backUpDrirectory;

	@Autowired
	private BusScheduleDao busScheduleDao;

	@Autowired
	private CommonUtil commonUtil;

	@Autowired
	private SMSWrapperService smsWrapperService;

	// @Scheduled(cron = "* * * * * *", zone = "Asia/Kolkata")
	public void fireEverySecond() {
		System.out.println("******* fire on every second **************");
	}

	@Scheduled(cron = "0 * * * * *", zone = "Asia/Kolkata")
	public void fireEveryMinute() {
		System.out.println("******** fire on every minute ****************");
	}

	@Scheduled(cron = "0 0 * * * *", zone = "Asia/Kolkata") // Fires at every hrs every day:
	public void everyHrs() {
		System.out.println("********************* Fires at every hrs every day ****");
		List<ScheduleMaster> scheduleMasters = busScheduleDao.getCurrentScheduleBus();
		scheduleMasters.forEach(scheduleBus -> {
			List<TicketDetails> ticketDetails = busScheduleDao
					.getTripMasterByScheduleIdBusId(scheduleBus.getScheduleId(), scheduleBus.getBusId());
			ticketDetails.forEach(ticket -> {
				String time = ticket.getDepartureTime().contains(":") ? ticket.getDepartureTime().split(":")[0]
						: ticket.getDepartureTime();
				if (time.equalsIgnoreCase(commonUtil.getFourHrsBeforeHrs())) {
					StringBuffer buffer = new StringBuffer("Your Bus ticket have comfirmed at ");
					buffer.append(ticket.getDepartureDate());
					buffer.append(" ");
					buffer.append(ticket.getDepartureTime());
					buffer.append(", Driver Name : ");
					buffer.append(scheduleBus.getDriverName());
					buffer.append("-");
					buffer.append(scheduleBus.getDriverNumber());
					buffer.append("Conductor Name : ");
					buffer.append(scheduleBus.getConductorName());
					buffer.append("-");
					buffer.append(scheduleBus.getConductorNumber());
					smsWrapperService.sendSMS(ticket.getPhoneNumber(), buffer.toString());
				}
			});

		});
	}

	@Scheduled(cron = "0 0 12 * * ?", zone = "Asia/Kolkata") // Fires at 12 PM every day:
	public void everyAfterNoon() {
		System.out.println("********************* Fires at 12 PM every day ****");
	}

	@Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Kolkata") // every night 12 AM
	public void everyMidNight() {
		System.out.println("*********************12 AM every day***");
		List<ScheduleMaster> scheduleMasters = busScheduleDao.getScheduleIdNotExistInTrip();
		scheduleMasters.forEach(scheduleBus -> {
			Long scheduleId = busScheduleDao.getLatestTripBySrcCityAndDestCityId(scheduleBus.getSourceCityId(),
					scheduleBus.getDestinationCityId());
			List<TripMaster> tripMasters = busScheduleDao.getTripMasterByScheduleId(scheduleId);
			tripMasters.forEach(tripMaster -> {
				tripMaster.setScheduleId(scheduleBus.getScheduleId());
				tripMaster.setArrivalDate(scheduleBus.getDepartureDate());
				if (new Integer(tripMaster.getArrivalTime().split(":")[0]) > 12)
					tripMaster.setArrivalDate(commonUtil.getNextDate(scheduleBus.getDepartureDate()));
				int i = busScheduleDao.insertTripMaster(tripMaster);
				if (i > 0)
					log.info("Trip inserted successfully !");
			});
		});
		Backupdbtosql();
	}

	public void Backupdbtosql() {
		Properties properties = new Properties();
		properties.setProperty(MysqlExportService.DB_NAME, "digitalbihar");
		properties.setProperty(MysqlExportService.DB_USERNAME, mysqlUserName);
		properties.setProperty(MysqlExportService.DB_PASSWORD, mysqlPassword);
		properties.setProperty(MysqlExportService.JDBC_DRIVER_NAME, mysqlDriverName);
		properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");

		// set the outputs temp dir
		properties.setProperty(MysqlExportService.TEMP_DIR, new File(backUpDrirectory).getPath());

		MysqlExportService mysqlExportService = new MysqlExportService(properties);
		mysqlExportService.clearTempFiles(false);
		try {
			mysqlExportService.export();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

}
