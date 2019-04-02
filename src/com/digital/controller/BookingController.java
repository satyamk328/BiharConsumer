package com.digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v0/book")
@Slf4j
public class BookingController {

	@Autowired
	private BookingService bookingService;
}
