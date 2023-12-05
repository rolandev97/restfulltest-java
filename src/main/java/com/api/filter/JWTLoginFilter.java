package com.api.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.api.config.TokenAuthenticationService;
import com.api.entities.Employe;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

	public JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		super( new AntPathRequestMatcher(defaultFilterProcessesUrl));
		setAuthenticationManager(authenticationManager);
		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		/*String username = request.getParameter("username");
        String password = request.getParameter("password");*/
		
		Employe em = new ObjectMapper()
				.readValue(request.getInputStream(), Employe.class);
 
        System.out.printf("JWTLoginFilter.attemptAuthentication: username/password= %s,%s", em.getUsername(), em.getPassword());
        System.out.println();
 
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(em.getUsername(), em.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("JWTLoginFilter.successfulAuthentication:");
		 
        // Write Authorization to Headers of Response.
        TokenAuthenticationService.addAuthentication(response, authResult.getName());
 
        String authorizationString = response.getHeader("Authorization");
 
        System.out.println("Authorization String=" + authorizationString);
	}
	
}
