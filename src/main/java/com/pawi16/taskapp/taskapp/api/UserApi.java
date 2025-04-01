package com.pawi16.taskapp.taskapp.api;

import com.pawi16.taskapp.taskapp.business.UserBusiness;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.model.RegisterRequest;
import com.pawi16.taskapp.taskapp.model.RegisterResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserApi {

    private final UserBusiness userBusiness;

    public UserApi(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        //user register service
        RegisterResponse response = null;
        try {
            response = userBusiness.register(request);
        } catch (BaseException e) {
            throw new RuntimeException(e);
        }

        return response;

    }
}
