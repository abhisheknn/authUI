package com.micro.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.micro.auth.constants.AppConstant;
import com.micro.auth.util.JWT;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	@Autowired JWT jwtUtil;
	@Autowired PublicKeyProvider publiKeyProvider;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String authToken;
		String username;
		final String requestHeader = request.getHeader(AppConstant.TOKEN);
		 if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			 authToken = requestHeader.substring(7);
			 try {
			 username = jwtUtil.getUsernameFromToken(authToken, publiKeyProvider.getPublicKey());
			 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.getUserPrincipal(), username);
             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			 SecurityContextHolder.getContext().setAuthentication(authentication);
			}catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            }
			 
		 }
		 chain.doFilter(request, response);
	}
}
