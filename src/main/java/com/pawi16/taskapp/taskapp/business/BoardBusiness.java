package com.pawi16.taskapp.taskapp.business;

import com.pawi16.taskapp.taskapp.entity.Board;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.BoardException;
import com.pawi16.taskapp.taskapp.mapper.BoardMapper;
import com.pawi16.taskapp.taskapp.model.CreateBoardRequest;
import com.pawi16.taskapp.taskapp.model.CreateBoardResponse;
import com.pawi16.taskapp.taskapp.model.EditBoardRequest;
import com.pawi16.taskapp.taskapp.model.EditBoardResponse;
import com.pawi16.taskapp.taskapp.service.BoardService;
import org.springframework.stereotype.Service;

@Service
public class BoardBusiness {
    private final BoardService boardService;
    private final BoardMapper boardMapper;

    public BoardBusiness(BoardService boardService, BoardMapper mapper, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }


    public CreateBoardResponse createBoard(CreateBoardRequest request, String userId) throws BaseException {
        //validate
        if (request == null) {
            throw BoardException.createRequestNull();
        }
        if (request.getTitle() == null) {
            throw BoardException.createTitleNull();
        }
        if (request.getTitle().trim().isEmpty()) {
            throw BoardException.createTitleEmpty();
        }

        Board board = boardService.createBoard(request.getTitle(), userId);
        CreateBoardResponse response = boardMapper.boardToCreateBoardResponse(board);
        response.setMessage("Create board successfully.");

        return response;
    }

    public EditBoardResponse editBoard(EditBoardRequest request, String boardId) throws BaseException {
        //validate
        if (request == null){
            throw BoardException.editRequestNull();
        }
        if (request.getTitle() == null){
            throw BoardException.editTitleNull();
        }
        if (request.getTitle().trim().isEmpty()){
            throw BoardException.editTitleEmpty();
        }
        if (boardId == null || boardId.trim().isEmpty()){
            throw BoardException.editIdNull();
        }

        Board board = boardService.editBoard(request.getTitle(), boardId);

        EditBoardResponse response = boardMapper.boardToEditBoardResponse(board);
        response.setMessage("edit successfully");
        return response;
    }
}
