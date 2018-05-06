package com.micro.auth.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(), user.getUserName(), user.getEmail(), user.getPassword(), mapToGrantedAuthorities());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
    	List authorities=new ArrayList<>();
    	authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    	return authorities;
    }
}