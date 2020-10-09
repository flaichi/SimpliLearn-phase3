package com.ecomerce.app.springdemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Annotation to work as controller beans
public class HelloController {
	
	@RequestMapping("/")
	public String helloWord() {
		
		return "Hello to Spring Boot up " ;
	}

}
