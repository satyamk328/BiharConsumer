package com.digital.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.digital.dao.TopCityDao;
import com.digital.model.TopCities;
/**
 * @author Satyam Kumar
 *
 */
@Service
public class TopCityService {
	
	private static final Logger log = LoggerFactory.getLogger(TopCityService.class);
	
	@Autowired
	private TopCityDao topCityDao;
	
	@Cacheable("topAllCity")
	public List<TopCities> getAllStation() {
		log.info("call getAllStation()");
		return topCityDao.getAllStation();
	}
	
	@Cacheable(value="topAllCityByCity", key="#stationName")
	public List<TopCities> searchStationByStationName(String stationName) {
		log.info("call searchStationByStationName [{}]",stationName);
		return topCityDao.searchStationByStationName(stationName);
	}
	
	public String addStationName(TopCities searchStation) {
		log.info("call addStationName [{}]",searchStation);
		return topCityDao.addStationName(searchStation);
	}
}
