package com.example.happinesssharing.controller;

import com.example.happinesssharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class ModifyPasswordController {
    @Autowired
    private UserService userService;
    @PostMapping("modifyPassword")
    public Map modifyPassword(@RequestBody String password){
        userService.modifyPassword(password);
        return Map.of("info","success");
    }
}
