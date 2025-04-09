package com.pawi16.taskapp.taskapp.service;

import com.pawi16.taskapp.taskapp.entity.Board;
import com.pawi16.taskapp.taskapp.entity.User;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.BoardException;
import com.pawi16.taskapp.taskapp.repository.BoardRepository;
import com.pawi16.taskapp.taskapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public Board createBoard(String title, String userId) throws BaseException {

        Board entity = new Board();

        //get current user
        Optional<User> opt = userRepository.findById(userId);

        if (opt.isEmpty()) {
            throw BoardException.createUserNotFound();
        }

        User currentUser = opt.get();

        entity.setTitle(title);
        entity.setCreatedUser(currentUser);

        boardRepository.save(entity);

        return entity;
    }

    public Board editBoard(String title, String boardId) throws BoardException {
        //get board
        Optional<Board> opt = boardRepository.findById(boardId);

        if (opt.isEmpty()) {
            throw BoardException.editBoardNotFound();
        }

        Board board = opt.get();
        board.setTitle(title);

        return boardRepository.save(board);

    }

    public List<Board> findAllByCreatedUserId(String id) {
        List<Board> boards = boardRepository.findAllByCreatedUserId(id);
        return boards;
    }

    public Board findBoardById(String id) throws BaseException {
        Optional<Board> opt = boardRepository.findById(id);
        if (opt.isEmpty()){
            //throw create.issue.board.not.found
            throw BoardException.getBoardNotFound();
        }
        return opt.get();
    }
}
