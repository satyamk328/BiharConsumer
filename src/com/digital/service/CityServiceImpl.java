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
public class CityServiceImpl implements CityService{
	
	private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);
	
	@Autowired
	private CityDao cityDao;
	
	@Cacheable("topAllCity")
	@Override
	public List<TopCities> getAll() {
		log.info("call getAllStation()");
		return cityDao.getAllStation();
	}
	
	@Cacheable(value="topAllCityByCity", key="#stationName")
	public List<TopCities> searchStationByStationName(String stationName) {
		log.info("call searchStationByStationName [{}]",stationName);
		return cityDao.searchStationByStationName(stationName);
	}
	
	@Override
	public long save(TopCities object) {
		log.info("call addStationName [{}]",object);
		return cityDao.addStationName(object);
	}

	@Override
	public TopCities get(Integer id) {
		return null;
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public int delete(Integer id) {
		log.info("call deleteCity [{}]",id);
		return cityDao.deleteCity(id);
		
	}
}
