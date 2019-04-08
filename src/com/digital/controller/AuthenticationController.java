package com.digital.controller;

import java.util.List;

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

import com.digital.model.Login;
import com.digital.model.User;
import com.digital.service.AuthenticationService;
import com.digital.service.MailService;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;
import com.digital.utils.CommonUtil;
import com.digital.utils.DataUtils;
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
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;
	@Autowired
	private MailService emailService;
	@Autowired
	private DataUtils dataUtils;

	@GetMapping(value = "/")
	public ResponseEntity<RestResponse<Object>> getAllUser(
			@PathVariable(name = "userId", required = true) Long userId) {
		log.info("call getUserDetail {}", userId);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch record Successfully");
		List<User> users = authService.getAllUsers();
		return new ResponseEntity<>(new RestResponse<>(users, status), HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}")
	public ResponseEntity<RestResponse<Object>> getUserDetailById(
			@PathVariable(name = "userId", required = true) Long userId) {
		log.info("call getUserDetail {}", userId);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch record Successfully");
		User user = authService.getUserDetailById(userId);
		return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<RestResponse<Object>> registration(@RequestBody(required = true) User user) {
		log.info("call registration {}", user);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Registered Successfully");
		if (authService.loginauthentication(user.getEmail()) != null) {
			status = new RestStatus<>(HttpStatus.CONFLICT.toString(),
					"A user with this email address already exist into system!");
		} else if (authService.loginauthentication(String.valueOf(user.getPhoneNumber())) != null) {
			status = new RestStatus<>(HttpStatus.CONFLICT.toString(),
					"A user with this phone number already exist into system!");
		} else {
			Long userId = authService.addUser(user);
			if (userId == null) {
				status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						"User not Registered Successfully");
				return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			user.setUserId(userId);
		}
		return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.OK);
	}

	@PostMapping(value = "/serviceLoginAuth")
	public ResponseEntity<RestResponse<Object>> loginAuthentication(@RequestBody(required = true) User user) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Login Successfully");
		user = authService.loginauthentication(user.getEmail());
		if (user == null) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Invalid username or password!.");
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (user.isLock()) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					"Your account has been lock. Please contact system administrator!");
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (!user.getPassword().equals(CommonUtil.encrypt(user.getPassword()))) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Invalid username or password!.");
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Login login = new Login();
		authService.prepareLogin(login, user);
		authService.addLoginDetail(login);
		return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.OK);
	}

	@GetMapping(value = "/changePassword")
	public ResponseEntity<RestResponse<Object>> getUserDetails(
			@RequestParam(name = "userId", required = true) Long userId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Forgot password Successfully");
		User user = authService.getUserDetailById(userId);
		if (user == null) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					"Invalid Email/password. Please enter valid email!");
			return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String otp = dataUtils.getGenerateOTP();
		emailService.sendEmail(GlobalConstants.OTP_HEADER, user.getEmail(),
				GlobalConstants.OTP_BODY.replaceAll("USER_NAME", user.getName()).replaceAll("OTP_VALUES", otp));
		user.setOtp(otp);
		return new ResponseEntity<>(new RestResponse<>(user, status), HttpStatus.OK);
	}

	@PutMapping(value = "/resetpassword/{userId}")
	public ResponseEntity<RestResponse<Object>> resetPassword(
			@PathVariable(name = "userId", required = true) Long userId,
			@RequestParam(name = "newPassword", required = true) String pass) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Forgot change Successfully");
		int i = authService.resetPassword(userId, pass);
		if (i == 0) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					"Currently this service is unavailable. We regret the inconvenience caused. Please try after some time.");
			return new ResponseEntity<>(new RestResponse<>(i, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(i, status), HttpStatus.OK);
	}

	@PutMapping(value = "/logout/{userId}")
	public ResponseEntity<RestResponse<Object>> logOut(@PathVariable(name = "userId", required = true) Long userId,
			@RequestParam(name = "ip", required = false, defaultValue = "127.0.0.0") String ip) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Logout Successfully");
		int i = authService.logOut(userId, ip);
		if (i == 0) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					"Currently this service is unavailable. We regret the inconvenience caused. Please try after some time.");
			return new ResponseEntity<>(new RestResponse<>(true, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(i, status), HttpStatus.OK);
	}

}
