package com.digital.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;
import com.digital.user.model.Login;
import com.digital.user.model.User;
import com.digital.user.service.UserService;
import com.digital.utils.CommonUtil;
import com.digital.utils.GlobalConstants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Api(value = "authentication")
@RestController
@RequestMapping(value = "/api/v0/auth")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private CommonUtil commonUtil;

	@GetMapping(value = "")
	public ResponseEntity<RestResponse<Object>> getAllUser(
			@PathVariable(name = "userId", required = true) Long userId) {
		log.info("call getUserDetail {}", userId);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch record Successfully");
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(new RestResponse<>(users, status), HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}")
	public ResponseEntity<RestResponse<Object>> getUserDetailById(
			@PathVariable(name = "userId", required = true) Long userId) {
		log.info("call getUserDetail {}", userId);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch record Successfully");
		User user = userService.getUserDetailById(userId);
		return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.OK);
	}

	@PostMapping(value = "/createUser")
	public ResponseEntity<RestResponse<Object>> addUser(@RequestBody(required = true) User user) {
		log.info("call registration {}", user);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Registered Successfully");
		if (userService.loginauthentication(user.getEmail()) != null) {
			status = new RestStatus<>(HttpStatus.CONFLICT.toString(),
					"A user with this email address already exist into system!");
		} else if (userService.loginauthentication(String.valueOf(user.getPhoneNumber())) != null) {
			status = new RestStatus<>(HttpStatus.CONFLICT.toString(),
					"A user with this phone number already exist into system!");
		} else {
			Long userId = userService.addUser(user);
			if (userId == null) {
				status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), GlobalConstants.ERROR_MESSAGE);
				return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			user.setUserId(userId);
		}
		return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.OK);
	}

	@PostMapping(value = "/serviceLoginAuth")
	public ResponseEntity<RestResponse<Object>> loginAuthentication(
			@RequestParam(name = "userName", required = true) String userName,
			@RequestParam(name = "password", required = true) String password, HttpServletRequest request) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Login Successfully");
		User user = userService.loginauthentication(userName);
		if (user == null) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Invalid username or password!.");
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (user.isLock()) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					"Your account has been lock. Please contact system administrator!");
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (!user.getPassword().equals(commonUtil.encrypt(password))) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Invalid username or password!.");
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Login login = new Login();
		login.setSessionId(request.getSession().getId());
		userService.prepareLogin(login, user);
		userService.addLoginDetail(login);
		return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.OK);
	}

	@PutMapping(value = "/update/{userId}")
	public ResponseEntity<RestResponse<Object>> getUserDetails(
			@PathVariable(name = "userId", required = true) Long userId, @RequestBody(required = true) User user) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User profile update Successfully");
		if (user.getUserId() == null) {
			status = new RestStatus<>(HttpStatus.BAD_REQUEST.toString(), "Please enter valid UserId!");
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.BAD_REQUEST);
		}
		user.setUserId(userId);
		int i = userService.updateUser(user);
		if (i == 0) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					"User profile cannot update. Please try again leter!");
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.OK);
	}

	@PutMapping(value = "/resetpassword/{userId}")
	public ResponseEntity<RestResponse<Object>> resetPassword(
			@PathVariable(name = "userId", required = true) Long userId,
			@RequestParam(name = "oldPassword", required = true) String oldPassword,
			@RequestParam(name = "newPassword", required = true) String pass) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Forgot change Successfully");
		int i = userService.resetPassword(userId,oldPassword, pass);
		if (i == 0) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), GlobalConstants.ERROR_MESSAGE);
			return new ResponseEntity<>(new RestResponse<>(i, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(i, status), HttpStatus.OK);
	}

	@PutMapping(value = "/logout/{userId}")
	public ResponseEntity<RestResponse<Object>> logOut(@PathVariable(name = "userId", required = true) Long userId,
			@RequestParam(name = "ip", required = false, defaultValue = "127.0.0.0") String ip) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Logout Successfully");
		int i = userService.logOut(userId, ip);
		if (i == 0) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), GlobalConstants.ERROR_MESSAGE);
			return new ResponseEntity<>(new RestResponse<>(true, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(i, status), HttpStatus.OK);
	}

}
