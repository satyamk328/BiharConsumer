package com.digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.AmenitiesDao;
import com.digital.model.BusAmenity;
import com.digital.model.vo.AmenitiesVo;

@Service
public class AmenityService {

	@Autowired
	private AmenitiesDao amenitiesDao;

	public List<BusAmenity> getAllAmenity() {
		return amenitiesDao.getAllAmenities();
	}

	public List<BusAmenity> getAmenityByBusId(Long busId) {
		return amenitiesDao.getAmenitiesByBusId(busId);
	}

	public Long addAmenity(AmenitiesVo amenitiesVo) {
		return amenitiesDao.addAmenities(amenitiesVo);
	}

	public boolean validateAmenity(Long aId,Long busId) {
		List<BusAmenity> amenities = amenitiesDao.validateAmenityByIdandBusId(aId, busId);
		return amenities != null && !amenities.isEmpty() ? true : false;
	}

	public int deleteAmenity(Long amenityId, Long busId) {
		return amenitiesDao.deleteAmenities(amenityId, busId);
	}
}
