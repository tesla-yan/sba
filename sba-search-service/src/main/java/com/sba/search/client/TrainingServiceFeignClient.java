package com.sba.search.client;

import com.sba.search.model.TrainingDtls;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "training-service", fallback = TrainingServiceFallback.class)
@RibbonClient(value = "training-service")
public interface TrainingServiceFeignClient {

	@PostMapping("/trainings/findAvgRating/{mentorId}/{skillId}")
	TrainingDtls findAvgRating(
            @PathVariable(value = "mentorId", required = true) Long mentorId,
            @PathVariable(value = "skillId", required = true) Long skillId);

}

@Component
class TrainingServiceFallback implements TrainingServiceFeignClient {

	@Override
	public TrainingDtls findAvgRating(Long mentorId, Long skillId) {
		return new TrainingDtls();
	}

}