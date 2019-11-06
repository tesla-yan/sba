package com.sba.security;

import com.sba.security.model.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JsonWebTokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {

	public JsonWebTokenAuthenticationFilter() {
		// Don't throw exceptions if the header is missing
		this.setExceptionIfHeaderMissing(false);
		// This is the request header it will look for
		this.setPrincipalRequestHeader(Constants.HEADER_STRING);
	}

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
}
