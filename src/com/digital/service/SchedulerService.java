package com.digital.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Satyam Kumar
 *
 */
@Component
public class SchedulerService {

	//@Scheduled(fixedRate = 5000) // for every 5 minutes
	public void run2() {
		System.out.println("I am called by Spring scheduler " + new Date());
	}

	@Scheduled(cron = "0 0 0 * * ?") // every night 12 AM
	public void run() {
		System.out.println("I am called by Spring scheduler " + new Date());
	}

	@Scheduled(cron = "0 0 12 * * *") // 12PM every day
	// @Scheduled(fixedRate=5000,zone = "Indian/Maldives")
	public void run1() {
		System.out.println("I am called by Spring scheduler run1 " + new Date());
	}
}
