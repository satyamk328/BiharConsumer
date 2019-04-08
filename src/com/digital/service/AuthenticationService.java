package com.digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.AuthenticationDao;
import com.digital.model.Login;
import com.digital.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Service("authenticationService")
@Slf4j
public class AuthenticationService {

	@Autowired
	private AuthenticationDao authenticationDao;

	// @Cacheable("authUser")
	public User loginauthentication(String email) {
		log.info("call authUser()");
		return authenticationDao.loginAuthentication(email);
	}

	public void prepareLogin(Login login, User user) {
		login.setAddress(user.getAddress());
		login.setCity(user.getCity());
		login.setState(user.getState());
		login.setUserName(user.getName());
		login.setUserId(user.getUserId());
		login.setClientHost("localhost");
		login.setClientIp("127.0.0.1");
		login.setSessionId("");
	}
	
	public Long addUser(User user) {
		log.info("call addUser()");
		return authenticationDao.addUser(user);
	}

	public int resetPassword(Long userId, String pass) {
		log.info("call resetPassword()");
		return authenticationDao.resetPassword(userId, pass);
	}

	public Long lockUser(Long userId, Boolean isLock, int attempt) {
		log.info("call lockUser()");
		return authenticationDao.lockUser(userId, isLock, attempt);
	}

	// @Cacheable("userAllData")
	public List<User> getUsers() {
		log.info("call getUsers()");
		return authenticationDao.findAllUser();
	}

	public User getUser(Long userId) {
		return authenticationDao.getUser(userId);
	}
	
	public void addLoginDetail(Login login) {
		authenticationDao.auditing(login);
	}

	public int logOut(Long userId, String ip) {
		log.info("call logOut()");
		return authenticationDao.updateLogOutTime(userId, ip);
	}
}
