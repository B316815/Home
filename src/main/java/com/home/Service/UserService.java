package com.home.Service;

import com.home.paylod.LoginDto;
import com.home.paylod.UserDto;

public interface UserService {

    public UserDto addUser(UserDto userDto);

    String verifyLogin(LoginDto loginDto);
}
