package com.sba.technology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCircuitBreaker
@EnableFeignClients("com.sba.**.client")
@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.sba.security", "com.sba.technology"})
@EnableJpaRepositories(basePackages ={"com.sba.technology.repo"})
@EntityScan(basePackages ={"com.sba.technology.entity"})
public class TechnologyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnologyApplication.class, args);
	}
}
