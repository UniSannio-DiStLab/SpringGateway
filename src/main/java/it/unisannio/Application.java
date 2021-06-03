package it.unisannio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// tag::code[]
@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// tag::route-locator[]
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		String accountEndpoint = "http://" + System.getenv("ACCOUNT_HOST") + ":8080/Account-1.0/api-v1/";
		String customerEndpoint = "http://" + System.getenv("CUSTOMER_HOST") + ":8080/Customer-1.0/api-v1/";
		return builder.routes()
			.route(p -> p
				.path("**/accounts/**")
				//.filters(f -> f.rewritePath("api-v1/bank/accounts","api-v1/accounts"))
				.uri(accountEndpoint))
			.route(p -> p
				.path("**/customers/**")
				//.filters(f -> f.rewritePath("api-v1/bank/accounts","api-v1/accounts"))
				.uri(customerEndpoint))
			.build();
	}
	// end::route-locator[]
}