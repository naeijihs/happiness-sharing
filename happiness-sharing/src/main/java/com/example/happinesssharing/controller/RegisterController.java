package com.example.happinesssharing.controller;

import com.example.happinesssharing.entity.User;
import com.example.happinesssharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @PostMapping("register")
    public Map register(@RequestBody User register){
        User user=userService.getUserByUsername(register.getUsername());
        if(user!=null)
            return Map.of("info","false");
        else{
            userService.addUser(register);
            return Map.of("info","success");
        }
    }
}
