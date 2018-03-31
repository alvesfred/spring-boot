package br.alvesfred.spring.boot.ignite;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring Boot Ignite Integration Test
 *
 * @author alvesfred
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootIgniteSampleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("INTEGRATION_TEST")
public class SpringBootIgniteApplicationIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    private URL base;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void contextLoads() {
        assertTrue(template.getForEntity(base+"/health",String.class).getStatusCode().is2xxSuccessful());
    }
}
