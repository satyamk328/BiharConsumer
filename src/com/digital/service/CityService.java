package com.digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.digital.dao.CityDao;
import com.digital.model.City;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Service
@Slf4j
public class CityService {

	@Autowired
	private CityDao cityDao;

	@Cacheable("topAllCity")
	public List<City> getAllCities() {
		log.info("call getAllStation()");
		return cityDao.getAllCities();
	}

	@Cacheable(value = "topAllCityByCity", key = "#cityName")
	public List<City> getCityByName(String cityName) {
		log.info("call searchStationByStationName {}", cityName);
		return cityDao.getCityByName(cityName);
	}

	public Long save(City city) {
		log.info("call addStationName [{}]", city);
		List<City> cities = cityDao.getCityByName(city.getDisplayName());
		if (cities == null || cities.isEmpty()) {
			return cityDao.addCity(city);
		}
		return 0L;
	}

	public City getCityById(Long cityId) {
		return cityDao.getCityById(cityId).get(0);
	}

	public int delete(Long cityId) {
		log.info("call deleteCity [{}]", cityId);
		cityDao.deleteCityStop(cityId);
		return cityDao.deleteCity(cityId);
	}
}
