package com.skip.challenge.controllers;

import com.skip.challenge.model.User;
import com.skip.challenge.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody User user) {
        service.register(user);
    }

}
