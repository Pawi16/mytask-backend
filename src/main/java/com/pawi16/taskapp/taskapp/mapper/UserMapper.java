package com.pawi16.taskapp.taskapp.mapper;

import com.pawi16.taskapp.taskapp.entity.User;
import com.pawi16.taskapp.taskapp.model.GetMyProfileResponse;
import com.pawi16.taskapp.taskapp.model.GetProfileByIdResponse;
import com.pawi16.taskapp.taskapp.model.LoginResponse;
import com.pawi16.taskapp.taskapp.model.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper{

    RegisterResponse userToRegisterResponse (User user);

    @Mapping(target = "token", ignore = true)
    LoginResponse userToLoginResponse (User user);

    GetProfileByIdResponse userToGetProfileByIdResponse(User user);

    GetMyProfileResponse userToGetMyProfileResponse (User user);
}
