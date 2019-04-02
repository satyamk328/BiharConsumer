package com.digital.service;

import java.util.List;

import com.digital.model.TopCities;

public interface SearchCityService {
	public List<TopCities> getAllCities();

	public long save(TopCities city);

	public TopCities getCityById(Long cityId);

	public int delete(Long cityId);

	public List<TopCities> getCityByName(String cityName);
}
