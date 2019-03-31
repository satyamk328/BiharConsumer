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
		wallet.setWalletId(rs.getLong("WalletId"));
		wallet.setUserId(rs.getLong("UserId"));
		wallet.setCurrentBalance(rs.getDouble("CurrentBalance"));
		wallet.setAddedBalance(rs.getDouble("AddedBalance"));
		wallet.setPreviousBalance(rs.getDouble("PreviousBalance"));
		wallet.setCreatedBy(rs.getString("Createdby"));
		wallet.setDateCreated(rs.getDate("DateCreated"));
		wallet.setModifiedBy(rs.getString("ModifiedBy"));
		wallet.setDateModified(rs.getDate("DateModified"));
		return wallet;
	}

}
