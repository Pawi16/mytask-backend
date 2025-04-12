package com.pawi16.taskapp.taskapp.business;

import com.pawi16.taskapp.taskapp.entity.*;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.IssueException;
import com.pawi16.taskapp.taskapp.mapper.IssueMapper;
import com.pawi16.taskapp.taskapp.model.*;
import com.pawi16.taskapp.taskapp.service.BoardService;
import com.pawi16.taskapp.taskapp.service.IssueService;
import com.pawi16.taskapp.taskapp.service.UserService;
import com.pawi16.taskapp.taskapp.service.validator.IssueValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class IssueBusiness {
    private final BoardService boardService;
    private final IssueService issueService;
    private final UserService userService;
    private final IssueValidator issueValidator;
    private final IssueMapper issueMapper;

    public IssueBusiness(BoardService boardService, IssueService issueService, UserService userService, IssueValidator issueValidator, IssueMapper issueMapper) {
        this.boardService = boardService;
        this.issueService = issueService;
        this.userService = userService;
        this.issueValidator = issueValidator;
        this.issueMapper = issueMapper;
    }

    public CreateIssueResponse createIssue(CreateIssueRequest request) throws BaseException {
        //validate request
        if (request == null) {
            //throw create.issue.request.null
            throw IssueException.createRequestNull();
        }
        if (request.getName() == null) {
            //throw create.issue.name.null
            throw IssueException.createNameNull();
        }
        if (request.getDueDate() == null) {
            request.setDueDate(LocalDate.now().plusDays(7));
        }
        if (request.getIssueType() == null) {
            //throw create.issue.type.null
            throw IssueException.createIssueTypeNull();
        }
        if (IssueType.valueOf(request.getIssueType().toUpperCase()) == IssueType.EVENT && request.getBoardId() == null) {
            //throw create.issue.board.id.null
            throw IssueException.createBoardIdNull();
        }

        // prepared create argument
        Board board = null;
        if (request.getBoardId() != null){
            board = boardService.findBoardById(request.getBoardId());
        }
        User currentUser = userService.getCurrentUser();
        Issue parentIssue = null;
        IssueType parentType = null;
        if (request.getParentIssueId() != null) {
            parentIssue = issueService.findIssueById(request.getParentIssueId());
            parentType = parentIssue.getIssueType();
        }
        boolean isDeleted = false;
        TaskStatus status = TaskStatus.PLANNING;
        boolean isCompleted = false;

        IssueType childType = null;
        PriorityType priorityType = null;

        //validate sub issue
        try {

            childType = IssueType.valueOf(request.getIssueType().toUpperCase());
            priorityType = PriorityType.valueOf(request.getPriority().toUpperCase());

            // Validate compatibility using the validator
            issueValidator.validateTypeCompatibility(childType, parentType);

        } catch (IllegalArgumentException e) {
            // Handle invalid enum value (e.g., throw a custom exception)
            throw IssueException.createInvalidIssueType();
        }

        //validate parent board and child board if both parent issue and board provided
        if (parentIssue != null && board != null) {
            if (!Objects.equals(request.getBoardId(), parentIssue.getBoard().getId())) {
                // throw parent and child need to be in same board exception.
                throw IssueException.validateParentChildBoardDifferent();
            }
        }
        //user parent board if board id not provided
        else if (parentIssue != null) {
            board = parentIssue.getBoard();
        }


        //call create issue service
        Issue issue = issueService.createIssue(request.getName(), request.getDescription(), status, isDeleted, request.getDueDate(), childType, isCompleted, priorityType, currentUser, parentIssue, board);
        return issueMapper.issueToCreateIssueResponse(issue);
    }

    public GetIssueByIdResponse getIssueById(String issueId) throws BaseException {
        //validate
        if (issueId == null) {
            //throw id null exception
            throw IssueException.getIssueIdNull();
        }
        if (issueId.trim().isEmpty()) {
            //throw id empty exception
            throw IssueException.getIssueIdEmpty();
        }

        //call getIssueById service
        Issue issue = issueService.findIssueById(issueId);

        //mapper
        return issueMapper.issueToGetIssueByIdResponse(issue);
    }

    public EditIssueByIdResponse editIssueById(String issueId, EditIssueByIdRequest request) throws BaseException {
        //validate
        if (issueId == null) {
            //throw id null exception
            throw IssueException.editIssueIdNull();
        }
        if (issueId.trim().isEmpty()) {
            //throw id empty exception
            throw IssueException.editIssueIdEmpty();
        }
        System.out.print(request.isCompleted());

        Issue existingIssue = issueService.findIssueById(issueId);
        Issue parentIssue = existingIssue.getParentIssue();
        Board targetBoard = existingIssue.getBoard();

        // parentIssueId != null case
        if (request.getParentIssueId() != null) {
            // enforce that EVENT cannot have a parent
            if (existingIssue.getIssueType() == IssueType.EVENT) {
                throw IssueException.editEventCannotHaveParent();
            }

            //check type compatibility
            Issue newParentIssue = issueService.findIssueById(request.getParentIssueId());
            issueValidator.validateTypeCompatibility(existingIssue.getIssueType(), parentIssue.getIssueType());
            parentIssue = newParentIssue;

            //which board to use
            if (request.getBoardId() == null) {
                targetBoard = newParentIssue.getBoard();
            } else if (!Objects.equals(request.getBoardId(), newParentIssue.getBoard().getId())) {
                throw IssueException.validateParentChildBoardDifferent();
            } else {
                targetBoard = boardService.findBoardById(request.getBoardId());
            }
        }
        // direct board change only for event
        if (request.getBoardId() != null) {
            if (existingIssue.getIssueType() != IssueType.EVENT) {
                throw IssueException.editDirectBoardChangeNotAllowed();
            }
            targetBoard = boardService.findBoardById(request.getBoardId());
        }

        //run update if board have a change
        if (targetBoard != null && !Objects.equals(targetBoard, existingIssue.getBoard())) {
            issueService.updateIssueBoardRecursive(existingIssue, targetBoard);
        }


        // prepare other parameter
        TaskStatus status = null;
        PriorityType priorityType = null;
        if (request.getStatus() != null) {
            try {
                status = TaskStatus.valueOf(request.getStatus().toUpperCase());

            } catch (IllegalArgumentException e) {
                throw IssueException.editInvalidIssueType();
            }
        }
        if (request.getPriority() != null) {
            try {
                priorityType = PriorityType.valueOf(request.getPriority().toUpperCase());

            } catch (IllegalArgumentException e) {
                throw IssueException.editInvalidIssueType();
            }
        }

        //call editIssueById service
        Issue issue = issueService.editIssue(issueId, request.getName(), request.getDescription(), status, request.getDueDate(), request.isCompleted(), priorityType, parentIssue, targetBoard);

        //map
        return issueMapper.issueToEditIssueByIdResponse(issue);

    }

    public MoveIssueToBoardResponse moveIssueToBoard (String issueId, MoveIssueToBoardRequest request) throws BaseException {
        //validate
        if (request == null) {
            throw IssueException.moveRequestNull();
        }

        // Validate target board ID
        if (request.getTargetBoardId() == null) {
            throw IssueException.moveTargetBoardIdNull();
        }
        if (request.getTargetBoardId().trim().isEmpty()) {
            throw IssueException.moveTargetBoardIdEmpty();
        }

        // Validate issue ID
        if (issueId == null) {
            throw IssueException.moveIssueIdNull();
        }
        if (issueId.trim().isEmpty()) {
            throw IssueException.moveIssueIdEmpty();
        }

        Issue existingIssue = issueService.findIssueById(issueId);
        //check if it epic type or not f not throw type move not allowed
        if (existingIssue.getIssueType() != IssueType.EVENT){
            throw IssueException.editDirectBoardChangeNotAllowed();
        }

        //find target board
        Board targetBoard = boardService.findBoardById(request.getTargetBoardId());

        //check if it in the same board
        if (Objects.equals(existingIssue.getBoard(),targetBoard)){
            //throw can't move to same board
            throw IssueException.moveSameBoardNotAllowed();
        }

        //update issue and child board to be this board
        issueService.updateIssueBoardRecursive(existingIssue, targetBoard);

        existingIssue = issueService.findIssueById(issueId);
        //map
        return issueMapper.issueToMoveIssueToBoardResponse(existingIssue);
    }


}
