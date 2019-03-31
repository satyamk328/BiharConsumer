package com.digital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Transactional(readOnly = true)
	public List<Wallet> getWalletHistory(Long userId) {
		log.debug("Running insert query for getWalletHistory: {}", selectWallletByUserQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		return jdbcTemplateObject.query(selectWallletByUserQuery, parameters, new WalletRowMapper());
	}

	@Transactional(readOnly = true)
	public Wallet getWalletDetails(Long userId) {
		log.debug("Running insert query for getWalletDetails: {}", selectUpdateWalletQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		return jdbcTemplateObject.queryForObject(selectUpdateWalletQuery, parameters, new WalletRowMapper());
	}

	@Transactional
	public int updateWallet(double addedAmount, Long userId) {
		log.debug("Running insert query for updateWallet: {}", updateWalletQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		parameters.addValue("addedBalance", addedAmount);
		return jdbcTemplateObject.update(updateWalletQuery, parameters);
	}

}
