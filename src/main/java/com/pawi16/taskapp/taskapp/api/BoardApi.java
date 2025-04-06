package com.pawi16.taskapp.taskapp.api;

import com.pawi16.taskapp.taskapp.business.BoardBusiness;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.model.CreateBoardRequest;
import com.pawi16.taskapp.taskapp.model.CreateBoardResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardApi {
    private final BoardBusiness boardBusiness;

    public BoardApi(BoardBusiness boardBusiness) {
        this.boardBusiness = boardBusiness;
    }

    @PostMapping
    public CreateBoardResponse createBoard(@RequestBody CreateBoardRequest request) throws BaseException {
        //create board
        // get current user id
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CreateBoardResponse response = boardBusiness.createBoard(request, userId);
        return response;
    }

}
