package com.salaobeleza.agendamentos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "API Salão de Beleza", version = "1", description = "API para agendamento de serviços"))
public class AgendamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentosApplication.class, args);
	}

}
