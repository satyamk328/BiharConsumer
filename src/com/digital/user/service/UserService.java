package com.digital.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.UserDao;
import com.digital.user.model.Login;
import com.digital.user.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Service("authenticationService")
@Slf4j
public class UserService {

	@Autowired
	private UserDao userDao;


	// @Cacheable("userAllData")
	public List<User> getAllUsers() {
		log.info("call getUsers()");
		return userDao.findAllUser();
	}

	public User getUserDetailById(Long userId) {
		return userDao.getUserDetailById(userId);
	}
	
	// @Cacheable("authUser")
	public User loginauthentication(String email) {
		log.info("call authUser()");
		return userDao.loginAuthentication(email);
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
		return userDao.addUser(user);
	}

	public int resetPassword(Long userId, String pass) {
		log.info("call resetPassword()");
		return userDao.resetPassword(userId, pass);
	}

	public Long lockUser(Long userId, Boolean isLock, int attempt) {
		log.info("call lockUser()");
		return userDao.lockUser(userId, isLock, attempt);
	}

	
	public Long addLoginDetail(Login login) {
		return userDao.auditing(login);
	}

	public int logOut(Long userId, String ip) {
		log.info("call logOut()");
		return userDao.updateLogOutTime(userId, ip);
	}
}
