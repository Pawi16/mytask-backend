package com.pawi16.taskapp.taskapp.mapper;

import com.pawi16.taskapp.taskapp.entity.User;
import com.pawi16.taskapp.taskapp.model.RegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper{

    RegisterResponse userToRegisterResponse (User user);
}
