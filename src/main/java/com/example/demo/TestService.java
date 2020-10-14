package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TestService {

    @Autowired
    UserService userService;

  //  @PostConstruct
    public void test() {
        User user = new User();
        user.setName("testing");
        userService.createUser(user);
        System.out.println(user);
    }

}
