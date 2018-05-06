package com.micro.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.micro.auth.exceptions.JwtAuthenticationEntryPoint;
import com.micro.auth.filter.JWTAuthenticationFilter;

@Configuration 
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private JWTAuthenticationFilter authenticationTokenFilter;
	
@Override
protected void configure(HttpSecurity httpSecurity) throws Exception {
	httpSecurity
	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	.authorizeRequests()
	.anyRequest().authenticated().and()
	.csrf()
    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	
	httpSecurity
    .addFilterBefore(authenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
}

@Override
public void configure(WebSecurity webSecurity) throws Exception {
	webSecurity
	.ignoring()
	.antMatchers("/index.html", "/","/register", "/home", "/login","/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js");
}
}
