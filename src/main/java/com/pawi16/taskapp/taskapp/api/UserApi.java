package com.pawi16.taskapp.taskapp.api;

import com.pawi16.taskapp.taskapp.business.UserBusiness;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.UserException;
import com.pawi16.taskapp.taskapp.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserApi {

    private final UserBusiness userBusiness;

    public UserApi(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) throws BaseException {
        //user register business

        return userBusiness.register(request);

    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) throws BaseException {
        //user login business
        return userBusiness.login(request);
    }

    @GetMapping("/profile/{id}")
    public GetProfileByIdResponse getProfileById(@PathVariable("id") String id) throws BaseException {
        // getProfileById
        return userBusiness.getProfileById(id);
    }
}
