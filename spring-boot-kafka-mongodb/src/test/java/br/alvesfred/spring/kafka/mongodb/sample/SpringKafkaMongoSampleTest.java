package br.alvesfred.spring.kafka.mongodb.sample;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Spring Boot main test
 *
 * @author alvesfred
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@WebAppConfiguration
public class SpringKafkaMongoSampleTest {

	@Ignore
	@Test
	public void contextLoads() {

	}
}
