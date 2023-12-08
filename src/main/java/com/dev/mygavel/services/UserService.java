package com.dev.mygavel.services;

import com.dev.mygavel.dto.UserDto;
import com.dev.mygavel.entities.User;

public interface UserService {
    User register(UserDto userDto);
}
