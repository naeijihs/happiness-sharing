package com.example.happinesssharing.controller;

import com.example.happinesssharing.entity.User;
import com.example.happinesssharing.service.SharerService;
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
public class RegistController {
    @Autowired
    private UserService userService;
    @Autowired
    private SharerService sharerService;
    @Autowired
    private PasswordEncoder encoder;
    @PostMapping("regist")
    public Map regist(@RequestBody User register){
        User user=userService.getUserByUsername(register.getUsername());
        if(user!=null)
            return Map.of("info","用户名已存在");
        else{
            sharerService.addSharer(register);
            return Map.of("info","注册成功");
        }
    }
}
