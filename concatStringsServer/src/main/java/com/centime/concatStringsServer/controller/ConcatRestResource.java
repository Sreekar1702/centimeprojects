package com.centime.concatStringsServer.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.centime.concatStringsServer.model.User;
import javax.validation.Valid;

@RestController
@Log4j2
public class ConcatRestResource {

	@PostMapping("/concatuser")
	public ResponseEntity<String> concatUser(@Valid @RequestBody User user) {
		
		log.info("Concat user details service started....");
		log.info("User Details {}" , user.toString());
		return new ResponseEntity<>(user.getName()+ " " + user.getSurname(), HttpStatus.OK);
	}
}
