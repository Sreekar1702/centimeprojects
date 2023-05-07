package com.example.mainapp.controller;

import javax.validation.Valid;

import com.example.mainapp.util.APIServiceInvocationHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mainapp.constants.StringConstants;
import com.example.mainapp.model.User;

@RestController
@RequestMapping("/")
@Log4j2
public class MainRestController {
	@Autowired
	private APIServiceInvocationHelper apiService;

	@GetMapping("/healthCheck")
	public ResponseEntity<String> getHealthcheck() {
		log.info("Health check validation started");
		return new ResponseEntity<>(StringConstants.SERVICE_UP,HttpStatus.OK);
	}

	@PostMapping("/concatResponse")
	public ResponseEntity<String> concatResponse(@Valid @RequestBody User user) {
		log.info("Invoking services to concat responses for {}",user.toString());
		String resultUserName = apiService.sendRequest(user);
		return new ResponseEntity<>(resultUserName, HttpStatus.OK);
	}

}
