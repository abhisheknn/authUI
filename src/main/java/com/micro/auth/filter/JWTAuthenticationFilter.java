package com.micro.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.micro.auth.constants.AppConstant;
import com.micro.auth.pojo.JwtUserFactory;
import com.micro.auth.pojo.User;
import com.micro.auth.util.JWT;
import com.micro.auth.util.PublicKeyProvider;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	@Autowired JWT jwtUtil;
	@Autowired PublicKeyProvider publiKeyProvider;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String authToken;
		String username;
		final String requestHeader = request.getHeader(AppConstant.AUTHORIZATION);
		 if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			 authToken = requestHeader.substring(7);
			 try {
			 username = jwtUtil.getUsernameFromToken(authToken, publiKeyProvider.getPublicKey());
			 User user= new User();
			 user.setUserName(username);
			 UserDetails userDetails=JwtUserFactory.create(user);
			 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			 SecurityContextHolder.getContext().setAuthentication(authentication);
			}catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            }
			 
		 }else {
			 logger.error("an error occured during getting username from token " + requestHeader);
		 }
		 chain.doFilter(request, response);
	}
}
