package com.digital.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.digital.dao.BusScheduleDao;
import com.digital.model.ScheduleMaster;
import com.digital.model.TripMaster;
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

	@Scheduled(fixedRate = 60000) // for every 1 Hrs
	public void run2() {
		System.out.println("I am called by Spring scheduler " + new Date());
	}

	@Scheduled(cron = "0 0 0 * * ?") // every night 12 AM
	public void run() {
		Backupdbtosql();
	}

	@Scheduled(cron = "0 0 12 * * *", zone = "Asia/Kolkata") // 12PM every day
	public void run1() {
		List<ScheduleMaster> scheduleMasters = busScheduleDao.getScheduleIdNotExistInTrip();
		for (ScheduleMaster scheduleMaster : scheduleMasters) {
			List<TripMaster> tripMasters = busScheduleDao.getTripMasterByBusId(scheduleMaster.getBusId());
			for (TripMaster tt : tripMasters) {
				tt.setScheduleId(scheduleMaster.getScheduleId());
				tt.setArrivalDate(scheduleMaster.getDepartureDate());
				int i = busScheduleDao.insertTripMaster(tt);
				if (i > 0)
					log.info("Trip inserted successfully !");
			}
		}

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
