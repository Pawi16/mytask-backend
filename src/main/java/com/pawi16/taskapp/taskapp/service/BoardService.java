package com.pawi16.taskapp.taskapp.service;

import com.pawi16.taskapp.taskapp.entity.Board;
import com.pawi16.taskapp.taskapp.entity.Issue;
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
    private final IssueService issueService;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository, IssueService issueService) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.issueService = issueService;
    }

    public Board createBoard(String title,boolean isDeleted, User createdUser) throws BaseException {

        Board entity = new Board();


        entity.setTitle(title);
        entity.setDeleted(isDeleted);
        entity.setCreatedUser(createdUser);

        boardRepository.save(entity);

        return entity;
    }

    public Board editBoard(String title, String boardId) throws BaseException {
        //get board
        Board board = findBoardById(boardId);

        board.setTitle(title);

        return boardRepository.save(board);

    }

    public List<Board> findAllByCreatedUserId(String id) {
        List<Board> boards = boardRepository.findAllByCreatedUserIdAndIsDeletedFalse(id);
        return boards;
    }

    public Board findBoardById(String id) throws BaseException {
        Optional<Board> opt = boardRepository.findByIdAndIsDeletedFalse(id);
        if (opt.isEmpty()){
            //throw create.issue.board.not.found
            throw BoardException.getBoardNotFound();
        }
        return opt.get();
    }

    public void softDeleteBoard(Board board){
        board.setDeleted(true);
        boardRepository.save(board);

        if (board.getIssues() != null && !board.getIssues().isEmpty()){
            for(Issue childIssue : board.getIssues()){
                issueService.softDeleteIssueRecursive(childIssue);
            }
        }
    }
}
