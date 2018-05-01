package com.micro.auth.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.micro.auth.util.KeyProvider;

@Component
public class PublicKeyProvider {

	@Autowired KeyProvider keyProviderUtil;
	 
	//@Value("${jwt.authserverPath}")
	private String authServerPath;
	
	public String getPublicKey() {
		return keyProviderUtil.getPublicKeyFrmServer("https://localhost:8222");
	}
}
