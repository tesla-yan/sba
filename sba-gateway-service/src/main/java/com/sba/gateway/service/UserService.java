package com.sba.gateway.service;

import java.util.HashSet;
import java.util.Set;

import com.sba.gateway.client.UserServiceFeignClient;
import com.sba.gateway.exception.ResourceNotFoundException;
import com.sba.gateway.exception.ServiceUnavailableException;
import com.sba.gateway.model.UserDtls;
import com.sba.gateway.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserService implements UserDetailsService {

	@Autowired
	private UserServiceFeignClient userProxy;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		UserDtls user = userProxy.findByName(userName);
		if (user == null)
			throw new ResourceNotFoundException(Translator.toLocale("error.resource.notfound.userName", userName));
		else if (user != null && user.getUserName().length() == 0)
			throw new ServiceUnavailableException(Translator.toLocale("error.service.unavailable", "user-service"));
		else if (user != null && (user.getConfirmedSignUp().booleanValue() == false
				|| user.getActive().booleanValue() == false || user.getResetPassword().booleanValue() == true))
			throw new UsernameNotFoundException(Translator.toLocale("error.invalid.user"));
		else
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user.getRole()));
	}

	private Set<SimpleGrantedAuthority> getAuthority(String role) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}

}
