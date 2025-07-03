package com.titans.travelbooking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Titans Tours and Travels",
				version = "v1.0",
				description = "This API is for booking International Tours "
		),
		servers = @Server(
				url = "http://localhost:8084/travels/api/v1/",
				description = "This app is deployed in the following url"
		)
)
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
