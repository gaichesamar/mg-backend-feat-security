package com.dev.mygavel.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dev.mygavel.dto.UserDto;
import com.dev.mygavel.entities.User;
import com.dev.mygavel.services.UserService;
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User userRegister(@RequestBody UserDto userDto){
        logger.info("register");
        return userService.register(userDto);
    }
}
