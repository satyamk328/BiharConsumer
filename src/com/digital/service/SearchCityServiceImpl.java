package com.digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.digital.dao.SearchCityDao;
import com.digital.model.TopCities;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Satyam Kumar
 *
 */
@Service
@Slf4j
public class SearchCityServiceImpl implements SearchCityService{
	
	@Autowired
	private SearchCityDao cityDao;
	
	@Override
	@Cacheable("topAllCity")
	public List<TopCities> getAllCities() {
		log.info("call getAllStation()");
		return cityDao.getAllCities();
	}
	
	@Cacheable(value="topAllCityByCity", key="#cityName")
	public List<TopCities> getCityByName(String cityName) {
		log.info("call searchStationByStationName {}",cityName);
		return cityDao.getCityByName(cityName);
	}
	
	@Override
	public long save(TopCities city) {
		log.info("call addStationName [{}]",city);
		return cityDao.addCity(city);
	}

	@Override
	public TopCities getCityById(Long cityId) {
		return cityDao.getCityById(cityId).get(0);
	}

	@Override
	public int delete(Long cityId) {
		log.info("call deleteCity [{}]",cityId);
		return cityDao.deleteCity(cityId);
		
	}
}
