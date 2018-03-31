package br.alvesfred.spring.kafka.mongodb.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Sample Main Application
 *
 * @author alvesfred
 *
 */
@SpringBootApplication
@EnableSwagger2
public class SpringKafkaMongoSample {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaMongoSample.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("spring-kafka-mongo-sample").apiInfo(apiInfo())
				.select().paths(regex("/.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring REST Sample with Swagger")
				.description("Sample of Application using Spring Boot, Rest, kafka and MongoDB")
				.contact("alvesfred")
				.license("APL V2").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("2.0").build();
	}
}
