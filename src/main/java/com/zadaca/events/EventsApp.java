package com.zadaca.events;

import com.zadaca.events.domains.User;
import com.zadaca.events.enums.ERole;
import com.zadaca.events.repository.UserRepository;
import com.zadaca.events.services.EventService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info = @Info(title = "EventsAPI",
				version = "1",
				description = "EventInfo")
)
@SecurityScheme(
		name = "Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
)
@SpringBootApplication
public class EventsApp {

	public static void main(String[] args) {
    	SpringApplication.run(EventsApp.class, args);
	}
}
