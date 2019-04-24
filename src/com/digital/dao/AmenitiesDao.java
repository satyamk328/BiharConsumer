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

import com.digital.model.BusAmenity;
import com.digital.model.vo.AmenitiesVo;

import lombok.extern.slf4j.Slf4j;

@Repository("amenitiesDao")
@Slf4j
public class AmenitiesDao {

	@Value("${select_all_aminities}")
	private String selectAllAminitiesQuery;
	@Value("${select_filter_aminities}")
	private String selectFilterAminitiesQuery;
	@Value("${insert_aminities_query}")
	private String insertAmenitiesQuery;
	@Value("${delete_aminities_query}")
	private String deleteAmenitiesQuery;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Transactional(readOnly = true)
	public List<BusAmenity> getAllAmenities() {
		log.debug("Running insert query for getAllAmenities {}", selectAllAminitiesQuery);
		return jdbcTemplateObject.query(selectAllAminitiesQuery, new BeanPropertyRowMapper<BusAmenity>(BusAmenity.class));
	}

	@Transactional(readOnly = true)
	public List<BusAmenity> getAmenitiesByBusId(Long busId) {
		log.debug("Running insert query for getBusAmenitiesByBusId {}", selectFilterAminitiesQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("busId", busId);
		return jdbcTemplateObject.query(selectFilterAminitiesQuery, parameters,
				new BeanPropertyRowMapper<BusAmenity>(BusAmenity.class));
	}

	@Transactional
	public long addAmenities(AmenitiesVo amenitiesVo) {
		log.debug("Running insert query for addAmenities {}", insertAmenitiesQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(amenitiesVo);
		jdbcTemplateObject.update(insertAmenitiesQuery, parameters, holder);
		return (holder.getKey() == null) ? null : holder.getKey().longValue();
	}

	@Transactional
	public int deleteAmenities(Long amenityId, Long busId) {
		log.debug("Running insert query for deleteCity {}", deleteAmenitiesQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("amenityId", amenityId);
		parameters.addValue("busId", busId);
		return jdbcTemplateObject.update(deleteAmenitiesQuery, parameters);
	}
}
