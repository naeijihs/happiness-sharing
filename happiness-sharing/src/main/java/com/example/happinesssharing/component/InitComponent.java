package com.example.happinesssharing.component;

import com.example.happinesssharing.entity.User;
import com.example.happinesssharing.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitComponent implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @Override
    public void afterPropertiesSet() throws Exception {
        String username="admin";
        User user=userService.getUserByUsername(username);
        if (user==null){
            User u=new User();
            u.setUsername(username);
            u.setPassword(encoder.encode(username));
            u.setRole(User.Role.ADMIN);
            userService.addUser(u);
        }
    }
}
