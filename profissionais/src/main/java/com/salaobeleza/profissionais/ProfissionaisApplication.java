package com.salaobeleza.profissionais;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Salão de Beleza", version = "1", description = "API para cadastro de profissionais"))
public class ProfissionaisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfissionaisApplication.class, args);
	}

}
