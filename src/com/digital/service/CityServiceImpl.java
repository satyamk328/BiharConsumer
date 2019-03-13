package com.digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.digital.dao.CityDao;
import com.digital.model.TopCities;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Satyam Kumar
 *
 */
@Service
@Slf4j
public class CityServiceImpl implements CityService{
	
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
		return cityDao.addCity(object);
	}

	@Override
	public TopCities getCityById(long id) {
		return cityDao.getCityById(id).get(0);
	}

	@Override
	public int delete(Integer id) {
		log.info("call deleteCity [{}]",id);
		return cityDao.deleteCity(id);
		
	}
}
