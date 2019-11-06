package com.sba.search;

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
@SpringBootApplication(scanBasePackages = {"com.sba.security", "com.sba.search"})
@EnableJpaRepositories(basePackages ={"com.sba.search.repo"})
@EntityScan(basePackages ={"com.sba.search.entity"})
public class SearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}
}
