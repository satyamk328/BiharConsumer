package com.digital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.Amenity;
import com.digital.model.vo.AmenitiesVo;

import lombok.extern.slf4j.Slf4j;

@Repository("amenitiesDao")
@Slf4j
public class AmenitiesDao {

	@Value("${select_all_aminities}")
	private String selectAllAminitiesQuery;
	@Value("${select_filter_aminities}")
	private String selectFilterAminitiesQuery;
	@Value("${select_aminities_by_busid}")
	private String selectAminitiesByBusIdQuery;
	@Value("${insert_aminities_query}")
	private String insertAmenitiesMappingQuery;
	@Value("${delete_aminities_query}")
	private String deleteAmenitiesQuery;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Transactional(readOnly = true)
	public List<Amenity> getAllAmenities() {
		log.debug("Running insert query for getAllAmenities {}", selectAllAminitiesQuery);
		return jdbcTemplateObject.query(selectAllAminitiesQuery, new BeanPropertyRowMapper<Amenity>(Amenity.class));
	}

	@Transactional(readOnly = true)
	public List<Amenity> getAmenitiesByBusId(Long busId) {
		log.debug("Running insert query for getBusAmenitiesByBusId {}", selectFilterAminitiesQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("busId", busId);
		return jdbcTemplateObject.query(selectFilterAminitiesQuery, parameters,
				new BeanPropertyRowMapper<Amenity>(Amenity.class));
	}

	@Transactional
	public long mapAmenity(AmenitiesVo amenitiesVo) {
		log.debug("Running insert query for addAmenities {}", insertAmenitiesMappingQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(amenitiesVo);
		jdbcTemplateObject.update(insertAmenitiesMappingQuery, parameters, holder);
		return (holder.getKey() == null) ? null : holder.getKey().longValue();
	}
	
	@Transactional(readOnly=true)
	public List<Amenity> validateAmenityByIdandBusId(Long amenityId,Long busId) {
		log.debug("Running select query for getAmenityByName {}", selectAminitiesByBusIdQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("amenityId", amenityId);
		parameters.addValue("busId", busId);
		return jdbcTemplateObject.query(selectAminitiesByBusIdQuery, parameters,
				new BeanPropertyRowMapper<Amenity>(Amenity.class));
	}

	@Transactional
	public int deleteMappingAmenity(Long amenityId, Long busId) {
		log.debug("Running insert query for deleteCity {}", deleteAmenitiesQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("amenityId", amenityId);
		parameters.addValue("busId", busId);
		return jdbcTemplateObject.update(deleteAmenitiesQuery, parameters);
	}
}
