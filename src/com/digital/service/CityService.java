package com.digital.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.digital.dao.CityDao;
import com.digital.model.TopCities;
/**
 * @author Satyam Kumar
 *
 */
@Service
public class CityService {
	
	private static final Logger log = LoggerFactory.getLogger(CityService.class);
	
	@Autowired
	private CityDao cityDao;
	
	@Cacheable("topAllCity")
	public List<TopCities> getAllStation() {
		log.info("call getAllStation()");
		return cityDao.getAllStation();
	}
	
	@Cacheable(value="topAllCityByCity", key="#stationName")
	public List<TopCities> searchStationByStationName(String stationName) {
		log.info("call searchStationByStationName [{}]",stationName);
		return cityDao.searchStationByStationName(stationName);
	}
	
	public String addStationName(TopCities searchStation) {
		log.info("call addStationName [{}]",searchStation);
		return cityDao.addStationName(searchStation);
	}
}
