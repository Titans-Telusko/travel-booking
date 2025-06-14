package com.titans.travelbooking;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Log4j2
@SpringBootApplication
public class TravelBookingApplication {

	@Value("${server.port}")
	private String port;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	public static void main(String[] args) {
		SpringApplication.run(TravelBookingApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logAppUrl() {
        log.info("\n\uD83D\uDE80 Application is running at: http://localhost:{}{}\n", port, contextPath);
	}

}
