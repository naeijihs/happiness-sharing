package com.example.happinesssharing.service;

import com.example.happinesssharing.component.RequestComponent;
import com.example.happinesssharing.entity.User;
import com.example.happinesssharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private PasswordEncoder encoder;
    public User addUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(User.Role.SHARER);
        return userRepository.save(user);
    }
    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }
    public User modifyPassword(String password){
        User user=userRepository.findById(requestComponent.getUid()).orElse(null);
        if(user!=null){
            user.setPassword(encoder.encode(password));
            return userRepository.save(user);
        }
        else
            return null;
    }
}
