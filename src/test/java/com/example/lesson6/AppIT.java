package com.example.lesson6;

import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@EnablePostgresIntegrationTest
class AppIT {

	@Test
	void contextLoads() {
	}

}
