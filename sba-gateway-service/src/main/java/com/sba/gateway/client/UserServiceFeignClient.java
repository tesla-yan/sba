package com.sba.gateway.client;

import com.sba.gateway.model.UserDtls;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service", fallback = UserServiceFallback.class)
@RibbonClient(value = "user-service")
public interface UserServiceFeignClient {

	@GetMapping("/users/findByName/{userName}")
	UserDtls findByName(
            @PathVariable(value = "userName", required = true) String userName);

}

@Component
class UserServiceFallback implements UserServiceFeignClient {

	@Override
	public UserDtls findByName(String userName) {
		return new UserDtls();
	}
}