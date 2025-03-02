package com.salaobeleza.gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "API Gateway", version = "1.0", description = "Documentation API Gateway v1.0"))
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(r -> r.path("/cliente/v3/api-docs").and().method(HttpMethod.GET).uri("lb://cliente"))
				.route(r -> r.path("/profissional/v3/api-docs").and().method(HttpMethod.GET).uri("lb://profissional"))
				.route(r -> r.path("/servico/v3/api-docs").and().method(HttpMethod.GET).uri("lb://servico"))
				.route(r -> r.path("/agendamento/v3/api-docs").and().method(HttpMethod.GET).uri("lb://agendamento"))
				.route(r -> r.path("/produto/v3/api-docs").and().method(HttpMethod.GET).uri("lb://produto"))
				.build();
	}
}
