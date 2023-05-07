package com.centimeapp.database.controller;

import com.centimeapp.database.annotations.LogMethodParam;
import com.centimeapp.database.entity.User;
import com.centimeapp.database.model.NestedOutputWithSubclasses;
import com.centimeapp.database.servicedao.IUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@Log4j2
public class CentimeController {
    @Autowired
    private IUserService iUserService;

    @GetMapping(value = "/getUser/{id}")
    @LogMethodParam
    @ApiOperation("Find user by id")
    public ResponseEntity<User> findByUserId(@PathVariable Integer id){
        log.info("Fetching details by id {}",id);
        return ResponseEntity.ok(iUserService.findById(id));
    }

    @GetMapping(value = "/getAllUsers")
    @LogMethodParam
    @ApiOperation("Fetch list of all users from database")
    public ResponseEntity<List<NestedOutputWithSubclasses> > fetchAllUsers(){
        log.info("fetching all details from database");
        return ResponseEntity.ok(iUserService.findNestedUserDetails());
    }

}
