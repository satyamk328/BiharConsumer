package com.digital.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository("bookingDao")
@Slf4j
public class BookingDao {

	@Value("${select_user_history}")
	private String selectAllUserQuery;
	
	
	
}
