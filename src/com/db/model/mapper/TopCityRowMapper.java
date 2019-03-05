package com.db.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.db.model.TopCities;
/**
 * @author Satyam Kumar
 *
 */
public class TopCityRowMapper implements RowMapper<TopCities> {
	
	@Override
	public TopCities mapRow(ResultSet rs, int rowNum) throws SQLException {
		TopCities user = new TopCities();
		user.setId(rs.getInt("cityid"));
		user.setDisplayName(rs.getString("displayname"));
		user.setCityName(rs.getString("cityname"));
		user.setStateName(rs.getString("statename"));
		return user;
	}
}
