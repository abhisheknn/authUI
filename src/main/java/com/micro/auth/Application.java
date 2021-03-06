package com.micro.auth;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micro.auth.util.KeyProvider;


@SpringBootApplication
@Controller
public class Application {

	@Autowired
	KeyProvider keyProvider;
	
  @GetMapping("/resource")
  @ResponseBody
  public Map<String, Object> home() {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("id", UUID.randomUUID().toString());
    model.put("content", "Hello World");
    return model;
  }
 
  @RequestMapping("/user")
  @ResponseBody
  public Principal user(Principal user) {
    return user;
  }
 public static void main(String[] args) {
	  ConfigurableApplicationContext context =SpringApplication.run(Application.class, args);
	  //context.getBean(KeyProvider.class).createAndStoreCert("cn=unknown", "servercert");
  }
}


@Component
@ConfigurationProperties("demo")
class ApplicationProperties {
  private String value;

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}
}