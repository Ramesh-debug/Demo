package com.udemy.securitycourse1.controller;

import com.udemy.securitycourse1.DetailsService.DetailsService;
import com.udemy.securitycourse1.entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class RegistrationController {

    @Autowired
    public DetailsService detailsService;
    @PostMapping("/register")
    public ResponseEntity<Details> register(@RequestBody Details details)
    {
        return new ResponseEntity<>(detailsService.addDetails(details), HttpStatus.CREATED);
    }

}
