package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.digital.user.model.User;
/**
 * @author Satyam Kumar
 *
 */
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getLong("UserId"));
		user.setRoleId(rs.getLong("RoleId"));
		user.setName(rs.getString("Name"));
		user.setEmail(rs.getString("Email"));
		user.setAddress(rs.getString("Address"));
		user.setPhoneNumber(rs.getLong("PhoneNumber"));
		user.setPanNumber(rs.getString("PanNumber"));
		user.setAadharNumber(rs.getString("aadharNumber"));
		user.setPassword(rs.getString("Password"));
		user.setCity(rs.getString("City"));
		user.setState(rs.getString("State"));
		user.setCountry(rs.getString("Country"));
		user.setIsLock(rs.getBoolean("IsLock"));
		user.setIsActive(rs.getBoolean("IsActive"));
		user.setAttempt(rs.getInt("Attempt"));
		user.setCreatedBy(rs.getString("CreatedBy"));
		user.setDateCreated(rs.getDate("DateCreated"));
		user.setModifiedBy(rs.getString("ModifiedBy"));
		user.setDateModified(rs.getDate("DateModified"));
		return user;
	}

}
