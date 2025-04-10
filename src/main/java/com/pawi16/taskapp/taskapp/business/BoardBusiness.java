package com.pawi16.taskapp.taskapp.business;

import com.pawi16.taskapp.taskapp.entity.Board;
import com.pawi16.taskapp.taskapp.entity.User;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.BoardException;
import com.pawi16.taskapp.taskapp.mapper.BoardMapper;
import com.pawi16.taskapp.taskapp.model.*;
import com.pawi16.taskapp.taskapp.service.BoardService;
import com.pawi16.taskapp.taskapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardBusiness {
    private final UserService userService;
    private final BoardService boardService;
    private final BoardMapper boardMapper;

    public BoardBusiness(BoardService boardService, BoardMapper mapper, UserService userService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.userService = userService;
        this.boardMapper = boardMapper;
    }


    public CreateBoardResponse createBoard(CreateBoardRequest request) throws BaseException {
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

        User currentUser = userService.getCurrentUser();

        Board board = boardService.createBoard(request.getTitle(), currentUser);
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

    public List<GetAllBoardsResponse> getAllBoards (String userId) throws BaseException {
        if(userId == null){
            //throw getAllUserIdNull
            throw BoardException.getAllBoardsUserIdNull();
        }
        if(userId.trim().isEmpty()){
            //throw getAllUserIdEmpty
            throw BoardException.getAllBoardsUserIdEmpty();
        }
        if(userService.findById(userId).isEmpty()){
            //throw getAllUserNotFound
            throw BoardException.getAllBoardsUserNotFound();
        }

        List<Board> boards = boardService.findAllByCreatedUserId(userId);

        //map boards to dto
        List<GetAllBoardsResponse> reponse = boardMapper.boardsToGetAllBoardsResponses(boards);
        //return dto

        return reponse;


    }
}
