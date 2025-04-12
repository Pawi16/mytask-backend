package com.pawi16.taskapp.taskapp.api;

import com.pawi16.taskapp.taskapp.business.BoardBusiness;
import com.pawi16.taskapp.taskapp.business.IssueBusiness;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardApi {
    private final BoardBusiness boardBusiness;
    private final IssueBusiness issueBusiness;

    public BoardApi(BoardBusiness boardBusiness, IssueBusiness issueBusiness) {
        this.boardBusiness = boardBusiness;
        this.issueBusiness = issueBusiness;
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

    @GetMapping("/{boardId}/issues")
    public List<GetIssuesByBoardIdResponse> getIssuesByBoardId (@PathVariable("boardId") String boardId) throws BaseException {
        //get Issues by board id
        return issueBusiness.getIssuesByBoardId(boardId);
    }

    @DeleteMapping("/{boardId}")
    public DeleteBoardResponse deleteBoardByBoardId (@PathVariable("boardId") String boardId) throws BaseException {
        //delete board by board id
        return boardBusiness.deleteBoardById(boardId);
    }

}
