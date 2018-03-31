package br.alvesfred.spring.boot.ignite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot Ignite Sample App
 *
 * @author alvesfred
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableRetry
public class SpringBootIgniteSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIgniteSampleApplication.class, args);
    }
}
