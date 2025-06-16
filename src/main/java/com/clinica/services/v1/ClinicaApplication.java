package com.clinica.services.v1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @Info(
				title = "Clínica365 API",
				version = "1.0.0",
				description = "API para gestión de citas y médicos"
		),
		servers = {
				@Server(url = "https://clinica365-services-cumppwkd3q-uc.a.run.app", description = "Cloud Run Producción")
		}
)
@SpringBootApplication
public class ClinicaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClinicaApplication.class, args);
	}
}
