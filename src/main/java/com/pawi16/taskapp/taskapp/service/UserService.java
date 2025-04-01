package com.pawi16.taskapp.taskapp.service;

import com.pawi16.taskapp.taskapp.entity.User;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.UserException;
import com.pawi16.taskapp.taskapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String email, String password, String firstName, String lastName) throws BaseException {
        //verify
        if (userRepository.existsByEmail(email)){
            throw UserException.createEmailDuplicated();
        }

        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setFirstName(firstName);
        entity.setLastName(lastName);

        userRepository.save(entity);

        return entity;

    }
}
