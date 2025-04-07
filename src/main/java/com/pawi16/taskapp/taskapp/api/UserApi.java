package com.pawi16.taskapp.taskapp.api;

import com.pawi16.taskapp.taskapp.business.BoardBusiness;
import com.pawi16.taskapp.taskapp.business.UserBusiness;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserApi {

    private final UserBusiness userBusiness;
    private final BoardBusiness boardBusiness;

    public UserApi(UserBusiness userBusiness, BoardBusiness boardBusiness) {
        this.userBusiness = userBusiness;
        this.boardBusiness = boardBusiness;
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

    @GetMapping("/profile/me")
    public GetMyProfileResponse getMyProfile(Authentication authentication) throws BaseException {

        String id = (String) authentication.getPrincipal();
        // getMyProfile business
        return userBusiness.getMyProfile(id);
    }

    @GetMapping("/{userId}/boards")
    public List<GetAllBoardsResponse> getAllBoards (@PathVariable("userId") String userId) throws BaseException {
        // getAllBoard business
        List<GetAllBoardsResponse> response = boardBusiness.getAllBoards(userId);
        return response;
    }
}
