package com.sba.payment.client;

import java.util.List;

import com.sba.payment.model.TrainingDtls;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;

@FeignClient(value = "training-service", fallback = TrainingServiceFallback.class)
@RibbonClient(value = "training-service")
public interface TrainingServiceFeignClient {

	@GetMapping("/trainings/findById/{id}")
	TrainingDtls findById(
            @RequestHeader("Authorization") String authToken,
            @PathVariable(value = "id", required = true) Long id);

	// GET http://localhost:8080/trainings/findByTrainingStatus/1,2,3,4
	@GetMapping("/trainings/findByTrainingStatus/{trainingStatus}")
	List<TrainingDtls> findByTrainingStatus(
            @PathVariable(value = "trainingStatus", required = true) List<String> trainingStatus);
}

@Component
class TrainingServiceFallback implements TrainingServiceFeignClient {

	@Override
	public TrainingDtls findById(String authToken, Long id) {
		return new TrainingDtls();
	}

	@Override
	public List<TrainingDtls> findByTrainingStatus(List<String> trainingStatus) {
		return new ArrayList<TrainingDtls>();
	}

}