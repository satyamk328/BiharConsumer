package com.digital.service;

import java.util.List;

import com.digital.model.TopCities;

public interface CityService {
	public List<TopCities> getAll();

	public long save(TopCities object);

	public TopCities getCityById(long id);

	public int delete(Integer id);

	public List<TopCities> searchStationByStationName(String stationName);
}
