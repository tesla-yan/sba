package com.sba.training.client;

import com.sba.training.model.UserDtls;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service", fallback = UserServiceFallback.class)
@RibbonClient(value = "user-service")
public interface UserServiceFeignClient {

	@GetMapping("/users/findById/{id}")
	UserDtls findById(
            @PathVariable(value = "id", required = true) Long id);
}

@Component
class UserServiceFallback implements UserServiceFeignClient {

	@Override
	public UserDtls findById(Long userId) {
		return new UserDtls();
	}
}