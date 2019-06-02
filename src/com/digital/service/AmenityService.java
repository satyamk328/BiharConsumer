package com.digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.digital.dao.AmenitiesDao;
import com.digital.model.Amenity;
import com.digital.model.vo.AmenitiesVo;

@Service
public class AmenityService {

	@Autowired
	private AmenitiesDao amenitiesDao;

	@Cacheable("allAmenity")
	public List<Amenity> getAllAmenity() {
		return amenitiesDao.getAllAmenities();
	}

	@Cacheable(value = "amenityDetails.Id", key = "#busId")
	public List<Amenity> getAmenityByBusId(Long busId) {
		return amenitiesDao.getAmenitiesByBusId(busId);
	}

	public Long mapAmenity(AmenitiesVo amenitiesVo) {
		return amenitiesDao.mapAmenity(amenitiesVo);
	}

	public boolean validateAmenity(Long aId,Long busId) {
		List<Amenity> amenities = amenitiesDao.validateAmenityByIdandBusId(aId, busId);
		return amenities != null && !amenities.isEmpty() ? true : false;
	}

	public int deleteMappingAmenity(Long amenityId, Long busId) {
		return amenitiesDao.deleteMappingAmenity(amenityId, busId);
	}
	
	
}
