package com.sba.training.client;

import com.sba.training.model.SkillDtls;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "technology-service", fallback = SkillServiceFallback.class)
@RibbonClient(value = "technology-service")
public interface SkillServiceFeignClient {

	@GetMapping("/skills/findById/{id}")
	SkillDtls findById(
            @PathVariable(value = "id", required = true) Long id);

}

@Component
class SkillServiceFallback implements SkillServiceFeignClient {

	@Override
	public SkillDtls findById(Long skillId) {
		return new SkillDtls();
	}

}
