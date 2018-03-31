package br.alvesfred.spring.boot.ignite;

import static org.mockito.Mockito.when;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Spring Boot Ignite Mock Test
 *
 * @author alvesfred
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SpringBootIgniteApplicationTest {

	@Mock
	private Ignite ignite;

	@Mock
	IgniteCache<Object, Object> igniteCache;

	@Before
	public void setUp() throws Exception {
		when(ignite.getOrCreateCache("Test")).thenReturn(igniteCache);
	}

	@Test
	public void test01() {
		// TODO
	}
}