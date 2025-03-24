package com.ajthack.netgeardemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Activate test profile
class NetgearDemoApplicationTests {

	@Test
	void contextLoads() {
		// Test will pass if Spring context loads
	}
}
