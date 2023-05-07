package com.centime.helloService.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class HelloController {
	@GetMapping("/hello")
	public ResponseEntity<String> getHello() {
		log.info("Hello service started....");
		return  ResponseEntity.ok("Hello");
	}
}
