package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.digital.model.TopCities;
/**
 * @author Satyam Kumar
 *
 */
public class TopCityRowMapper implements RowMapper<TopCities> {
	
	@Override
	public TopCities mapRow(ResultSet rs, int rowNum) throws SQLException {
		TopCities user = new TopCities();
		user.setCityid(rs.getInt("cityid"));
		user.setDisplayName(rs.getString("displayname"));
		user.setCityName(rs.getString("cityname"));
		user.setStateName(rs.getString("statename"));
		return user;
	}
}
