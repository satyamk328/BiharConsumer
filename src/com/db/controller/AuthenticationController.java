package com.db.controller;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.db.model.Login;
import com.db.model.User;
import com.db.service.AuthenticationService;
import com.db.service.MailService;
import com.db.spring.model.RestResponse;
import com.db.spring.model.RestStatus;
import com.db.utils.Constants;
import com.db.utils.DataUtils;
/**
 * @author Satyam Kumar
 *
 */
@RestController
@RequestMapping(value = "/api/v0/auth")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private MailService emailService;

	@PostMapping(value = "/registerUser")
	public ResponseEntity<RestResponse<Object>> registration(@RequestBody(required = true) User user) {
		log.info("call registration {}", user);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Registered Successfully");
		if (authService.getUserDetails(user.getEmail()) == null) {
			status = new RestStatus<>(HttpStatus.CONFLICT.toString(),
					"A user with this email address already exist into system!");
		} else if (authService.getUserDetails(user.getPhoneNumber()) == null) {
			status = new RestStatus<>(HttpStatus.CONFLICT.toString(),
					"A user with this phone number already exist into system!");
		} else {
			user = authService.addUser(user);
			if (user == null) {
				status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						"User not Registered Successfully");
				return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.OK);
	}
	

	@PostMapping(value = "/serviceLoginAuth")
	public ResponseEntity<RestResponse<Object>> authUser(@RequestBody(required = true) User user)
			throws UnsupportedEncodingException {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Login Successfully");
		if (user.getEmail() == null) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Please enter valid Email/Phone");
			return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			user = authService.authUser(user);
			if (user == null) {
				status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
						"Unauthorized User. Please login with your valid credential!");
				return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			Login login = new Login();
			login.setUid(user.getUserId());
			login.setName(user.getName());
			login.setSessionId(null);
			login.setAddress(user.getAddress());
			login.setClientIP("localhost");
			authService.auditing(login);
		}
		return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.OK);
	}

	@GetMapping(value = "/changePassword")
	public ResponseEntity<RestResponse<Object>> getUserDetails(
			@RequestParam(name = "email", required = true) String email) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Forgot password Successfully");
		User user = authService.getUserDetails(email);
		if (user == null) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					"Invalid Email/password. Please enter valid email!");
			return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String otp = DataUtils.getGenerateOTP();
		emailService.sendEmail(Constants.OTP_HEADER, user.getEmail(),
				Constants.OTP_BODY.replaceAll("USER_NAME", user.getName()).replaceAll("OTP_VALUES", otp));
		user.setOtp(otp);
		return new ResponseEntity<>(new RestResponse(user, status), HttpStatus.OK);
	}

	@PutMapping(value = "/resetpassword/{uid}")
	public ResponseEntity<RestResponse<Object>> resetPassword(@PathVariable(name = "uid", required = true) String uid,
			@RequestParam(name = "newPassword", required = true) String pass) throws UnsupportedEncodingException {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Forgot change Successfully");
		int i = authService.resetPassword(uid, pass);
		if (i == 0) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					"Currently this service is unavailable. We regret the inconvenience caused. Please try after some time.");
			return new ResponseEntity<>(new RestResponse(false, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse(true, status), HttpStatus.OK);
	}
	
	@PutMapping(value = "/logout/{uid}")
	public ResponseEntity<RestResponse<Object>> logOut(
			@PathVariable(name = "uid", required = true) String uid,
			@RequestParam(name = "ip", required = false, defaultValue="127.0.0.0") String ip) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "User Logout Successfully");
		int i = authService.logOut(ip, uid);
		if(i == 0 ) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					"Currently this service is unavailable. We regret the inconvenience caused. Please try after some time.");
			return new ResponseEntity<>(new RestResponse(true, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse(true, status), HttpStatus.OK);
	}

}
