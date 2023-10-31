package za.co.bmw.crud.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public GroupedOpenApi api() {

		return GroupedOpenApi
				.builder()
				.group("BMW Test project")
				.packagesToScan("za.co.bmw.crud.controller")
				.packagesToExclude("org.springframework.boot")
				.build();

	}
}
