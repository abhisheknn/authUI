package com.micro.auth.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.micro.auth.pojo.User;

@RestController
public class AuthController {


	@RequestMapping(value="/register",method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody User user){
		return ResponseEntity.ok("success" + user.getUserName() + user.getPassword());
	}
	
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody User user){
		return ResponseEntity.ok("success" + user.getUserName() + user.getPassword());
	}
	
}
