package com.example.demo.job;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.scheduling.annotation.Scheduled;

public class Scheduledbackgroundjob {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");

	

	@Scheduled(cron = "0 0 * * *")
	public void performTaskUsingCron() {

		System.out.println("Regular task performed using Cron at "
				+ dateFormat.format(new Date(0)));

	}
}

