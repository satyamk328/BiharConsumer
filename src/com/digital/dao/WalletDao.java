package com.digital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.model.Wallet;
import com.digital.model.extrator.WalletRowMapper;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Satyam Kumar
 *
 */
@Repository("walletDao")
@Slf4j
public class WalletDao {

	@Value("${select_wallet_details_by_user}")
    private String selectWallletByUserQuery;
	@Value("${select_updated_wallet}")
    private String selectUpdateWalletQuery;
	@Value("${update_wallet}")
    private String updateWalletQuery;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(readOnly = true)
	public List<Wallet> getWalletHistory(String uid) {
		log.debug("Running insert query for getWalletHistory: {}", selectWallletByUserQuery);
		return jdbcTemplate.query(selectWallletByUserQuery, new Object[] { uid }, new WalletRowMapper());
	}

	@Transactional(readOnly = true)
	public Wallet getWalletDetails(String uid) {
		log.debug("Running insert query for getWalletDetails: {}", selectUpdateWalletQuery);
		return jdbcTemplate.queryForObject(selectUpdateWalletQuery, new Object[] { uid }, new WalletRowMapper());
	}

	@Transactional
	public int updateWallet(double addedAmount, String uid) {
		log.debug("Running insert query for updateWallet: {}", updateWalletQuery);
		return jdbcTemplate.update(updateWalletQuery, addedAmount,uid);
	}

}
