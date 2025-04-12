package com.pawi16.taskapp.taskapp.mapper;

import com.pawi16.taskapp.taskapp.entity.User;
import com.pawi16.taskapp.taskapp.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper{

    RegisterResponse userToRegisterResponse (User user);

    @Mapping(target = "token", ignore = true)
    LoginResponse userToLoginResponse (User user);

    GetProfileByIdResponse userToGetProfileByIdResponse(User user);

    GetMyProfileResponse userToGetMyProfileResponse (User user);

    @Mapping(target = "message", ignore = true)
    EditMyProfileResponse userToEditMyProfileResponse (User user);
}
