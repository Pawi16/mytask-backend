package com.pawi16.taskapp.taskapp.api;

import com.pawi16.taskapp.taskapp.business.BoardBusiness;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.model.CreateBoardRequest;
import com.pawi16.taskapp.taskapp.model.CreateBoardResponse;
import com.pawi16.taskapp.taskapp.model.EditBoardRequest;
import com.pawi16.taskapp.taskapp.model.EditBoardResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
        CreateBoardResponse response = boardBusiness.createBoard(request);
        return response;
    }

    @PatchMapping("/{id}")
    public EditBoardResponse editBoard(@PathVariable("id") String id,@RequestBody EditBoardRequest request) throws BaseException {
        //edit board business
        return boardBusiness.editBoard(request, id);
    }



}
