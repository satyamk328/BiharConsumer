package com.digital.service;

import java.util.List;

import com.digital.model.TopCities;

public interface CityService {
	public List<TopCities> getAll();
	public long save(TopCities object);
	public TopCities get(Integer id);
	public int count();
	public int delete(Integer id);
}
