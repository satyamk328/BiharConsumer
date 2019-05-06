package com.digital.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.enums.PrevilageType;
import com.digital.model.extrator.UserRowMapper;
import com.digital.user.model.Login;
import com.digital.user.model.User;
import com.digital.user.model.UserRole;
import com.digital.utils.CommonUtil;
import com.digital.utils.DataUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Repository("userDetailsDao")
@Slf4j
public class AuthenticationDao {

	@Value("${select_user_history}")
	private String selectAllUserQuery;
	
	@Value("${select_user_byid}")
	private String selectUserByIdQuery;
	
	@Value("${insert_user_detail}")
	private String insertUserDetailQuery;
	
	@Value("${insert_user_module}")
	private String insertUserModuleQuery;
	
	@Value("${insert_user_login_detail}")
	private String insertUserLoginQuery;
	
	@Value("${update_user_master_Attempt}")
	private String updateUserAttemptQuery;
	
	@Value("${update_user_password}")
	private String resetUserPasswordQuery;
	
	@Value("${select_user_detail_by_phone}")
	private String selectUserDetailsByPhoneQuery;
	
	@Value("${select_user_detail_by_email}")
	private String selectUserDetailsByEmailQuery;
	
	@Value("${upadte_logout_date}")
	private String updateLoginLogoutTimeQuery;
	
	@Autowired
	private DataUtils dataUtils;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Transactional(readOnly = true)
	public List<User> findAllUser() {
		log.debug("Running insert query for addUser: {}", selectAllUserQuery);
		return jdbcTemplateObject.query(selectAllUserQuery, new UserRowMapper());
	}

	public User getUserDetailById(Long userId) {
		log.debug("Running insert query for getUserDetails {}", selectUserByIdQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		List<User> users = jdbcTemplateObject.query(selectUserByIdQuery, parameters, new UserRowMapper());
		return (users != null && !users.isEmpty()) ? users.get(0) : null;
	}
	
	@Transactional
	public Long addUser(User user) {
		log.debug("Running insert query for addUser {}", insertUserDetailQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
		jdbcTemplateObject.update(insertUserDetailQuery, parameters, holder);
		Long userId = (holder.getKey() == null) ? null : holder.getKey().longValue();
		
		UserRole role = new UserRole();
		role.setRoleName(PrevilageType.CUSTOMER.name());
		role.setUserId(userId);
		
		BeanPropertySqlParameterSource parameters1 = new BeanPropertySqlParameterSource(role);
		log.debug("Running insert query for addUser {}", insertUserModuleQuery);
		KeyHolder holder1 = new GeneratedKeyHolder();
		jdbcTemplateObject.update(insertUserModuleQuery, parameters1, holder1);
		return userId;
	}

	@Transactional
	public Long auditing(Login login) {
		log.debug("Running insert query for auditing: {}", insertUserLoginQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(login);
		jdbcTemplateObject.update(insertUserLoginQuery, parameters, holder);
		return (holder.getKey() == null) ? null : holder.getKey().longValue();
	}

	@Transactional
	public long lockUser(Long userId, Boolean isLock, int attempt) {
		log.debug("Running upadte query for lockUser {}", updateUserAttemptQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		parameters.addValue("isLock", isLock);
		parameters.addValue("attempt", attempt);
		return jdbcTemplateObject.update(updateUserAttemptQuery, parameters);
	}
	
	@Transactional
	public int resetPassword(Long userId, String pass) {
		log.debug("Running reset query for resetPassword {}", resetUserPasswordQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("password", CommonUtil.decrypt(pass));
		parameters.addValue("userId", userId);
		return jdbcTemplateObject.update(resetUserPasswordQuery, parameters);
	}

	@Transactional
	public User loginAuthentication(String email) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		List<User> users = new ArrayList<>();
		if (dataUtils.validatePhoneNumber(email)) {
			log.debug("Running insert query for authUser {}", selectUserDetailsByPhoneQuery);
			parameters.addValue("phone", email);
			users = jdbcTemplateObject.query(selectUserDetailsByPhoneQuery, parameters, new UserRowMapper());
		} else {
			log.debug("Running insert query for authUser {}", selectUserDetailsByEmailQuery);
			parameters.addValue("email", email);
			users = jdbcTemplateObject.query(selectUserDetailsByEmailQuery, parameters, new UserRowMapper());
		}
		return (users != null && !users.isEmpty()) ? users.get(0) : null;
	}

	@Transactional
	public int updateLogOutTime(Long userId, String ip) {
		log.debug("Running insert query for addUser {}", updateLoginLogoutTimeQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cityId", userId);
		parameters.addValue("clientIp", ip);
		return jdbcTemplateObject.update(updateLoginLogoutTimeQuery, parameters);
	}

}
