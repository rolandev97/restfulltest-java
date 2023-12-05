package com.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.filter.JWTAuthenticationFilter;
import com.api.filter.JWTLoginFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().authorizeRequests()
	                // No need authentication.
	                .antMatchers("/").permitAll() //
	                .antMatchers(HttpMethod.POST, "/login").permitAll() //
	                .antMatchers(HttpMethod.GET, "/login").permitAll() // For Test on Browser
	                // Need authentication.
	                .anyRequest().authenticated()
	                
	                //
	                .and()
	                //
	                // Add Filter 1 - JWTLoginFilter
	                //
	                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                        UsernamePasswordAuthenticationFilter.class)
	                //
	                // Add Filter 2 - JWTAuthenticationFilter
	                //
	                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	        
	    }

	   @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }
	   
	   @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 
	        String password = "133";
	 
	        String encrytedPassword = this.passwordEncoder().encode(password);
	        System.out.println("Encoded password of 133=" + encrytedPassword);
	 
	        auth.inMemoryAuthentication().withUser("tom").password(encrytedPassword).roles("USER");
	 
	    }
}
