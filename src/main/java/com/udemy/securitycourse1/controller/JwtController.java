package com.udemy.securitycourse1.controller;

import com.udemy.securitycourse1.DetailsService.JwtTokensService;
import com.udemy.securitycourse1.entity.AuthenticateDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    JwtTokensService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> createToken(@RequestBody AuthenticateDetails authenticateDetails)
    {
        return new ResponseEntity<>(jwtService.generateToken(authenticateDetails.getUsername()), HttpStatus.OK);
    }

}
