package com.sba.payment;

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
@SpringBootApplication(scanBasePackages = {"com.sba.security", "com.sba.payment"})
@EnableJpaRepositories(basePackages ={"com.sba.payment.repo"})
@EntityScan(basePackages ={"com.sba.payment.entity"})
public class PaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
	}
}
