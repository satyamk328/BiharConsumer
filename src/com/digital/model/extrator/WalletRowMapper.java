package com.digital.model.extrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.digital.model.Wallet;
/**
 * @author Satyam Kumar
 *
 */
public class WalletRowMapper implements RowMapper<Wallet> {
	
	@Override
	public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
		Wallet wallet = new Wallet();
		wallet.setId(rs.getString("id"));
		wallet.setUserId(rs.getString("userId"));
		wallet.setPreviousBalance(rs.getDouble("previousBalance"));
		wallet.setCurrentBalance(rs.getDouble("currentBalance"));
		wallet.setAddedBalance(rs.getDouble("addedBalance"));
		wallet.setPreviousBalance(rs.getDouble("previousBalance"));
		wallet.setCreatedBy(rs.getString("createdby"));
		wallet.setCreatedOn(rs.getDate("createdon"));
		wallet.setModifyBy(rs.getString("modifyby"));
		wallet.setModifyOn(rs.getDate("modifyon"));
		return wallet;
	}

}
