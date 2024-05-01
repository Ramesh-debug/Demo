package com.udemy.securitycourse1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("/account")
    public String account()
    {
        return "this is My account";
    }

    @GetMapping("/balance")
    public String balance()
    {
        return "this is My Balance";
    }

    @GetMapping("/cards")
    public String cards()
    {
        return "this is My card";
    }

    @GetMapping("/contact")
    public String contact()
    {
        return "this is Bank contact";
    }

    @GetMapping("/loans")
    public String loans()
    {
        return "this is my loans";
    }

    @GetMapping("/notices")
    public String notices()
    {
        return "this is Bank notices";
    }

}


