package com.digital.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.digital.model.User;
/**
 * @author Satyam Kumar
 *
 */
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getString("userid"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setAddress(rs.getString("address"));
		user.setPhoneNumber(rs.getString("phonenumber"));
		user.setPanNumber(rs.getString("pannumber"));
		user.setPassword(rs.getString("password"));
		user.setCity(rs.getString("city"));
		user.setState(rs.getString("state"));
		user.setCountry(rs.getString("country"));
		user.setLock(rs.getBoolean("islock"));
		user.setActive(rs.getBoolean("isactive"));
		user.setAttempt(rs.getInt("attempt"));
		user.setCreatedBy(rs.getString("createdby"));
		user.setCreatedOn(rs.getDate("createdon"));
		user.setModifyBy(rs.getString("modifyby"));
		user.setModifyOn(rs.getDate("modifyon"));
		return user;
	}

}
