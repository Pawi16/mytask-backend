package com.pawi16.taskapp.taskapp.business;

import com.pawi16.taskapp.taskapp.entity.User;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.UserException;
import com.pawi16.taskapp.taskapp.mapper.UserMapper;
import com.pawi16.taskapp.taskapp.model.*;
import com.pawi16.taskapp.taskapp.service.TokenService;
import com.pawi16.taskapp.taskapp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusiness {

    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    public UserBusiness(UserService userService, UserMapper userMapper, TokenService tokenService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
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

    public LoginResponse login(LoginRequest request) throws BaseException {
        //validate
        if(request == null){
            //throw request null exception
            throw UserException.loginRequestNull();
        }

        if(request.getEmail() == null){
            //throw email null exception
            throw UserException.loginEmailNull();
        }

        if(request.getPassword() == null){
            //throw password null exception
            throw UserException.loginPasswordNull();
        }

        //verify
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            //throw user not found exception
            throw UserException.loginUserNotFound();
        }

        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(), user.getPassword())){
            //throw password not match exception
            throw UserException.loginPasswordNotMatch();
        }

        //create token
        String token = tokenService.tokenize(user);

        //map
        LoginResponse loginResponse = userMapper.userToLoginResponse(user);
        loginResponse.setToken(token);

        return loginResponse;

    }

    public GetProfileByIdResponse getProfileById(String id) throws BaseException {
        //validate
        if(id == null || id.trim().isEmpty()){
            throw UserException.getProfileIdNull();
        }

        //verify
        Optional<User> opt = userService.findById(id);
        if(opt.isEmpty()){
            throw UserException.getProfileNotFound();
        }

        User entity = opt.get();

        return userMapper.userToGetProfileByIdResponse(entity);
    }

    public GetMyProfileResponse getMyProfile(Authentication authentication) throws BaseException {
        String id = (String) authentication.getPrincipal();

        Optional<User> opt = userService.findById(id);

        //verify
        if(opt.isEmpty()){
            throw UserException.getProfileNotFound();
        }

        User entity = opt.get();

        return userMapper.userToGetMyProfileResponse(entity);

    }


}
