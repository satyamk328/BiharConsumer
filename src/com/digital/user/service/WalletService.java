package com.digital.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.dao.WalletDao;
import com.digital.user.model.Wallet;
/**
 * @author Satyam Kumar
 *
 */
@Service("walletService")
public class WalletService {

	private static final Logger log = LoggerFactory.getLogger(WalletService.class);

	@Autowired
	private WalletDao walletDao;

	//@Cacheable(value="walletHistory", key="#uid")
	public List<Wallet> getWalletHistory(Long userId) {
		log.info("call getWalletHistory {}", userId);
		return walletDao.getWalletHistory(userId);
	}

	//@Cacheable(value="walletDetails", key="#uid")
	public Wallet getWalletDetails(Long userId) {
		log.info("call getWalletDetails {}", userId);
		return walletDao.getWalletDetails(userId);
	}

	public int updateWallet(double addedAmount, Long userId) {
		log.info("call updateWallet addedAmount: {}, UserId: {}", addedAmount, userId);
		return walletDao.updateWallet(addedAmount, userId);
	}
}
