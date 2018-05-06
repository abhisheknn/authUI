package com.micro.auth.util;

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
		String result =keyProviderUtil.getPublicKeyFrmServer("http://localhost:8252");
		return result;
	}
}
