package com.digital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;
import com.digital.user.model.Wallet;
import com.digital.user.service.WalletService;
import com.digital.utils.GlobalConstants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Api(value = "wallet")
@Controller
@RequestMapping(value = "/api/v0/wallet")
@Slf4j
public class WalletController {

	@Autowired
	private WalletService walletService;

	@PutMapping(value = "/{addedAmount}")
	public ResponseEntity<RestResponse<Object>> updateWallet(
			@PathVariable(name = "addedAmount", required = true) Double addedAmount,
			@RequestParam(name = "userId", required = true) Long userId) {
		log.info("call addWallet updateWallet:{}, uid:{}", addedAmount, userId);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Wallet Updated Successfully");
		int row = walletService.updateWallet(addedAmount, userId);
		if (row == 0) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), GlobalConstants.ERROR_MESSAGE);
			return new ResponseEntity<>(new RestResponse<>(row, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(row, status), HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}")
	public ResponseEntity<RestResponse<List<Wallet>>> getWalletHistory(
			@PathVariable(name = "userId", required = true) Long userId) {
		log.info("call getWalletHistory userId:{}", userId);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<Wallet> wallets = walletService.getWalletHistory(userId);
		return new ResponseEntity<>(new RestResponse<>(wallets, status), HttpStatus.OK);
	}

	@GetMapping(value = "/customuserbalance/{uid}")
	public ResponseEntity<RestResponse<Wallet>> getWalletDetails(
			@PathVariable(name = "uid", required = true) Long userId) {
		log.info("call getWalletDetails uid:{}", userId);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		Wallet wallets = walletService.getWalletDetails(userId);
		return new ResponseEntity<>(new RestResponse<>(wallets, status), HttpStatus.OK);
	}

}
