package com.digital.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.user.model.Contact;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ContactUsDao {

	@Value("${select_complain_history}")
	private String selectAllComplain;

	@Value("${select_complain_by_id}")
	private String selectComplainByIdQuery;

	@Value("${insert_complain_detail}")
	private String insertComplainDetailQuery;

	@Value("${update_complain_detail}")
	private String updateComplainDetailQuery;

	@Value("${delete_complain_detail}")
	private String deleteComplainDetailQuery;

	@Value("${select_complain_type}")
	private String selectComplainType;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateObject;

	@Transactional(readOnly = true)
	public List<String> getAllType() {
		log.debug("Running select query for getAllDetails: {}", selectComplainType);
		return (List<String>) jdbcTemplateObject.query(selectComplainType, new ResultSetExtractor<List<String>>() {
			@Override
			public List<String> extractData(ResultSet rs) throws SQLException {
				List<String> strings = new ArrayList<>();
				while (rs.next()) {
					strings.add(rs.getString("Type"));
				}
				return strings;
			}
		});
	}

	@Transactional(readOnly = true)
	public List<Contact> getAllDetails() {
		log.debug("Running select query for getAllDetails: {}", selectAllComplain);
		return jdbcTemplateObject.query(selectAllComplain, new BeanPropertyRowMapper<>(Contact.class));
	}

	@Transactional(readOnly = true)
	public Contact getContactDetailsById(Long compId) {
		log.debug("Running select query for getContactDetailsById: {}", selectComplainByIdQuery);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("compId", compId);
		List<Contact> contacts = jdbcTemplateObject.query(selectComplainByIdQuery, parameters,
				new BeanPropertyRowMapper<>(Contact.class));
		return contacts.isEmpty() ? new Contact() : contacts.get(0);
	}

	@Transactional
	public Long addComplain(Contact contact) {
		log.debug("Running insert query for addComplain {}", insertComplainDetailQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(contact);
		jdbcTemplateObject.update(insertComplainDetailQuery, parameters, holder);
		Long compId = (holder.getKey() == null) ? null : holder.getKey().longValue();
		return compId;
	}

	@Transactional
	public int updateComplain(Contact contact) {
		log.debug("Running insert query for updateUser {}", updateComplainDetailQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("senderName", contact.getSenderName());
		parameters.addValue("SenderEmail", contact.getSenderEmail());
		parameters.addValue("ComplainType", contact.getComplainType());
		parameters.addValue("description", contact.getDescription());
		parameters.addValue("channelType", contact.getChannelType());
		parameters.addValue("compId", contact.getComplainId());
		return jdbcTemplateObject.update(updateComplainDetailQuery, parameters);
	}

	@Transactional
	public int deleteComplain(Long compId) {
		log.debug("Running delete query for deleteComplain {}", deleteComplainDetailQuery);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("compId", compId);
		return jdbcTemplateObject.update(deleteComplainDetailQuery, parameters);
	}
}
