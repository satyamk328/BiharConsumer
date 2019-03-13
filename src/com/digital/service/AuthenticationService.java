package com.digital.service;

import java.io.UnsupportedEncodingException;
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

	//@Cacheable("authUser")
	public User loginauthentication(User user) throws UnsupportedEncodingException {
		log.info("call authUser()");
		return authenticationDao.loginauthentication(user);
	}

	public User addUser(User user) {
		log.info("call addUser()");
		return authenticationDao.addUser(user);
	}

	//@Cacheable(value="userDetails", key="#email")
	public User getUserDetails(String email) {
		log.info("call forgotPassword()");
		return authenticationDao.getUserDetails(email);
	}

	public int resetPassword(String email, String pass) throws UnsupportedEncodingException {
		log.info("call changePassword()");
		return authenticationDao.resetPassword(email, pass);
	}

	public int lockUser(String userName, boolean isLock, int attempt) {
		log.info("call lockUser()");
		return authenticationDao.lockUser(userName, isLock, attempt);
	}

	//@Cacheable("userAllData")
	public List<User> getUsers() {
		log.info("call getUsers()");
		return authenticationDao.findAllUser();
	}

	public void auditing(Login login) {
		authenticationDao.auditing(login);
	}

	public int logOut(String ip, String uid) {
		log.info("call logOut()");
		return authenticationDao.logOut(ip, uid);
	}
}
