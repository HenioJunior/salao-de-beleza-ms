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
				.route(r -> r.path("/clientes-api/v3/api-docs").and().method(HttpMethod.GET).uri("lb://clientes-api"))
				.route(r -> r.path("/profissionais-api/v3/api-docs").and().method(HttpMethod.GET).uri("lb://profissionais-api"))
				.route(r -> r.path("/servicos-api/v3/api-docs").and().method(HttpMethod.GET).uri("lb://servicos-api"))
				.route(r -> r.path("/agendamentos-api/v3/api-docs").and().method(HttpMethod.GET).uri("lb://agendamentos-api"))
				.route(r -> r.path("/produtos-api/v3/api-docs").and().method(HttpMethod.GET).uri("lb://produtos-api"))
				.build();
	}
}
