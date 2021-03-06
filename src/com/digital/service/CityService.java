package com.digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.digital.dao.CityDao;
import com.digital.model.CityMaster;
import com.digital.model.CityStopMaster;

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
	public List<CityMaster> getAllCities() {
		log.info("call getAllStation()");
		return cityDao.getAllCities();
	}

	@Cacheable(value = "cityDetails.CityName")
	public List<CityMaster> getCityByName(String cityName) {
		log.info("call searchStationByStationName {}", cityName);
		return cityDao.getCityByName(cityName);
	}

	@Cacheable(value = "cityDetails.CityId")
	public CityMaster getCityById(Long cityId) {
		return cityDao.getCityById(cityId);
	}

	public Long save(CityMaster city) {
		log.info("call addStationName [{}]", city);
		List<CityMaster> cities = cityDao.getCityByName(city.getDisplayName());
		if (cities == null || cities.isEmpty()) {
			return cityDao.addCity(city);
		}
		return 0L;
	}

	public int delete(Long cityId) {
		log.info("call deleteCity [{}]", cityId);
		cityDao.deleteCityStop(cityId);
		return cityDao.deleteCity(cityId);
	}

	@Cacheable("subCityById")
	public List<CityStopMaster> getCityStopByCityId(Long cityId) {
		return cityDao.getCityStopByCityId(cityId);
	}

	@Cacheable("subCityByIdAndSubId")
	public List<CityStopMaster> getCityStopDetails(Long cityId, List<String> cityStopIds) {
		return cityDao.getCityStopDetails(cityId, cityStopIds);
	}
}
