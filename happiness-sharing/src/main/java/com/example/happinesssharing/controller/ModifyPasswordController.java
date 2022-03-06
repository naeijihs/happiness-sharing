package com.example.happinesssharing.controller;

import com.example.happinesssharing.entity.User;
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
    public Map modifyPassword(@RequestBody User user){
        userService.modifyPassword(user.getPassword());
        return Map.of("info","密码修改成功");
    }
}
