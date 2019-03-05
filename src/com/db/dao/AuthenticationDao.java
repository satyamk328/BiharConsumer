package com.db.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.db.enums.PrevilageType;
import com.db.model.Login;
import com.db.model.User;
import com.db.model.mapper.UserRowMapper;
import com.db.utils.DataUtils;
import com.db.utils.SecurityDigester;
/**
 * @author Satyam Kumar
 *
 */
@Repository("userDetailsDao")
public class AuthenticationDao {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationDao.class);

	@Value("${select_user_history}")
    private String selectAllUserQuery;
	@Value("${insert_user_detail}")
    private String insertUserDetailQuery;
	@Value("${insert_user_module}")
	private String insertUserModuleQuery;
	@Value("${insert_user_login_detail}")
	private String insertUserLoginQuery;
	@Value("${update_user_master_lock}")
	private String updateUserLockQuery;
	@Value("${select_user_detail_by_phone}")
	private String selectUserDetailsByPhoneQuery;
	@Value("${select_user_detail_by_email}")
	private String selectUserDetailsByEmailQuery;
	@Value("${update_user_password}")
	private String updateUserPasswordQuery;
	@Value("${select_auth_user_by_phone}")
	private String selectUserAuthByPhoneQuery;
	@Value("${select_auth_user_by_email}")
	private String selectUserAuthByEmailQuery;
	@Value("${upadte_logintime}")
	private String updateLoginLogoutTimeQuery;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<User> findAllUser() {
		log.debug("Running insert query for addUser: {}", selectAllUserQuery);
		return jdbcTemplate.query(selectAllUserQuery, new UserRowMapper());
	}

	@Transactional
	public User addUser(User user) {
		log.debug("Running insert query for addUser: {}", insertUserDetailQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertUserDetailQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getAddress());
				ps.setString(4, user.getPhoneNumber());
				ps.setString(5, user.getPanNumber());
				ps.setString(6, user.getPassword());
				ps.setString(7, user.getCity());
				ps.setString(8, user.getState());
				return ps;
			}
		}, holder);
		String newUserId = (String) holder.getKeys().get("userid");
		user.setUserId(String.valueOf(newUserId));
		log.debug("Running insert query for addUser {}", insertUserModuleQuery);
		KeyHolder holder1 = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertUserModuleQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, newUserId);
				ps.setString(2, PrevilageType.RETAILER.toString());
				return ps;
			}
		}, holder1);
		return user;
	}

	@Transactional
	public void auditing(Login login) {
		log.debug("Running insert query for auditing: {}", insertUserLoginQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertUserLoginQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, login.getUid());
				ps.setString(2, login.getName());
				ps.setString(3, login.getSessionId());
				ps.setString(4, login.getAddress());
				ps.setString(5, login.getClientIP());
				ps.setString(6, "127.0.0.0");
				return ps;
			}
		}, holder);
	}
		
	@Transactional
	public int lockUser(String userName, boolean isLock, int attempt) {
		log.debug("Running insert query for addUser {}", updateUserLockQuery);
		return jdbcTemplate.update(updateUserLockQuery, userName, isLock, attempt);
	}

	@Transactional(readOnly = true)
	public User getUserDetails(String email) {
		try {
			User user = null;
			if (DataUtils.validatePhoneNumber(email)) {
				log.debug("Running insert query for getUserDetails : {}", selectUserDetailsByPhoneQuery);
				user = jdbcTemplate.queryForObject(selectUserDetailsByPhoneQuery, new Object[] { email }, new UserRowMapper());
			} else {
				log.debug("Running insert query for getUserDetails: {}", selectUserDetailsByEmailQuery);
				user = jdbcTemplate.queryForObject(selectUserDetailsByEmailQuery, new Object[] { email }, new UserRowMapper());
			}
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Transactional
	public int resetPassword(String uid, String pass) throws UnsupportedEncodingException {
		log.debug("Running insert query for getUserDetails {}", updateUserPasswordQuery);
		return jdbcTemplate.update(updateUserPasswordQuery, SecurityDigester.encrypt(pass.trim()), uid);
	}

	@Transactional
	public User authUser(User user) throws UnsupportedEncodingException {
		try {
			if (DataUtils.validatePhoneNumber(user.getEmail())) {
				log.debug("Running insert query for authUser {}", selectUserAuthByPhoneQuery);
				user = jdbcTemplate.queryForObject(selectUserAuthByPhoneQuery,
						new Object[] { user.getEmail(), SecurityDigester.encrypt(user.getPassword()) },
						new UserRowMapper());
			} else {
				log.debug("Running insert query for authUser {}", selectUserAuthByEmailQuery);
				user = jdbcTemplate.queryForObject(selectUserAuthByEmailQuery,
						new Object[] { user.getEmail(), SecurityDigester.encrypt(user.getPassword()) },
						new UserRowMapper());
			}
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Transactional
	public int logOut(String ip, String uid) {
		log.debug("Running insert query for addUser {}", updateLoginLogoutTimeQuery);
		return jdbcTemplate.update(updateLoginLogoutTimeQuery, new Object[] {ip, uid});
	}

	

}
