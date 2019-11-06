package com.sba.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.sba.security.model.Constants;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Component
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String authToken = null, userName = null;
		UserDetails userDetails = null;
		Authentication auth = null;

		if (authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class) && authentication.getPrincipal() != null) {
			String tokenHeader = (String) authentication.getPrincipal();
			authToken = tokenHeader.replace(Constants.TOKEN_PREFIX, "");
			
			try {
				Claims claims = Jwts.parser().setSigningKey(Constants.SIGNING_KEY).parseClaimsJws(authToken).getBody();
				userName = claims.getSubject();
				Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(Constants.AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
				if (userName != null && authorities != null) {
					userDetails = new User(userName, "", authorities);
				}
				if (userDetails != null)
					auth = new JsonWebTokenAuthentication(userDetails, tokenHeader);
			} catch (IllegalArgumentException e) {
				// logger.error("an error occured during getting username from token", e);
			} catch (ExpiredJwtException e) {
				// req.setAttribute("expired", "Token expired and not valid anymore");
				// logger.warn("the token is expired and not valid anymore", e);
			} catch (SignatureException e) {
				// logger.error("Authentication Failed. Username or Password not valid.");
			}
		} else {
			auth = authentication;
		}
		return auth;
	}

	// @Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
				|| authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
	}

}
