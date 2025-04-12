package com.pawi16.taskapp.taskapp.service;

import com.pawi16.taskapp.taskapp.entity.User;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.UserException;
import com.pawi16.taskapp.taskapp.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if (userRepository.existsByEmail(email)) {
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

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public User getCurrentUser() throws BaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            throw UserException.getCurrentUserUnauthenticated();
        }

        String userId;
        try {
            userId = (String) auth.getPrincipal();
        } catch (ClassCastException e) {
            throw UserException.getCurrentUserInvalidPrincipalTypeCast();
        }

        return userRepository.findById(userId)
                .orElseThrow(UserException::getCurrentUserUserNotFound);
    }

    public User editProfile(String userId, String firstName, String lastName) throws BaseException {
        if(userId == null){
            throw UserException.editProfileUserIdNull();
        }
        Optional<User> opt = findById(userId);
        if(opt.isEmpty()){
            //throw edit user not file
            throw UserException.editProfileUserNotFound();
        }
        User user = opt.get();

        if(firstName != null){
            user.setFirstName(firstName);
        }
        if(lastName != null){
            user.setLastName(lastName);
        }

        userRepository.save(user);
        return user;

    }
}
