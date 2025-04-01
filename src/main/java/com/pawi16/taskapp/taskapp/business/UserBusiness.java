package com.pawi16.taskapp.taskapp.business;

import com.pawi16.taskapp.taskapp.entity.User;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.UserException;
import com.pawi16.taskapp.taskapp.mapper.UserMapper;
import com.pawi16.taskapp.taskapp.model.RegisterRequest;
import com.pawi16.taskapp.taskapp.model.RegisterResponse;
import com.pawi16.taskapp.taskapp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public RegisterResponse register(RegisterRequest request) throws BaseException {
        //validate
        if (request == null) {
            throw UserException.requestNull();
        }

        if (request.getEmail() == null) {
            //throw email null exception
            throw UserException.registerEmailNull();
        }

        if (request.getPassword() == null) {
            //throw password null exception
            throw UserException.registerPasswordNull();
        }

        if (request.getFirstName() == null) {
            //throw firstname null exception
            throw UserException.registerFirstNameNull();
        }

        if (request.getLastName() == null) {
            //throw firstname null exception
            throw UserException.registerLastNameNull();
        }

        User user = userService.createUser(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName());

        return userMapper.userToRegisterResponse(user);


    }
}
