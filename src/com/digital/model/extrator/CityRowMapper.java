package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.digital.model.City;

/**
 * @author Satyam Kumar
 *
 */
public class CityRowMapper implements RowMapper<City> {

	@Override
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		City user = new City();
		user.setCityId(rs.getLong("CityId"));
		user.setDisplayName(rs.getString("DisplayName"));
		user.setCityName(rs.getString("CityName"));
		user.setStateName(rs.getString("StateName"));
		user.setDistrict(rs.getString("District"));
		user.setCountry(rs.getString("Country"));
		return user;
	}
}
