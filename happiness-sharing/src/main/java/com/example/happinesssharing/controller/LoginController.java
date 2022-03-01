package com.example.happinesssharing.controller;

import com.example.happinesssharing.component.EncryptComponent;
import com.example.happinesssharing.component.MyToken;
import com.example.happinesssharing.entity.User;
import com.example.happinesssharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private EncryptComponent encrypt;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @PostMapping("login")
    public Map login(@RequestBody User login, HttpServletResponse response){
        User user= Optional.ofNullable(userService.getUserByUsername(login.getUsername()))
                .filter(u->encoder.matches(login.getPassword(),u.getPassword()))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"您的用户名密码输入有误"));
        MyToken token=new MyToken(user.getId(),user.getRole());
        String auth=encrypt.encryptToken(token);
        response.setHeader("Access-Control-Expose-Headers",MyToken.AUTHORIZATION);
        response.setHeader(MyToken.AUTHORIZATION,auth);
        if(user.getRole() == User.Role.ADMIN)
            return Map.of("role","admin");
        else
            return Map.of("role","sharer");
    }
}
