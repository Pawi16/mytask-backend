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

import java.util.Optional;

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
        if (request.getIssueType() == null) {
            //throw create.issue.type.null
            throw IssueException.createIssueTypeNull();
        }
        if (request.getBoardId() == null) {
            //throw create.issue.board.id.null
            throw IssueException.createBoardIdNull();
        }

        // prepared create argument
        Board board = boardService.findBoardById(request.getBoardId());
        User currentUser = userService.getCurrentUser();
        Issue parentIssue = null;
        IssueType parentType = null;
        if (request.getParentIssueId() != null) {
            parentIssue = issueService.findIssueById(request.getParentIssueId());
            parentType = parentIssue.getIssueType();
        }

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

        //call create issue service
        Issue issue = issueService.createIssue(request.getName(), request.getDescription(), request.getDueDate(), childType, priorityType, currentUser, parentIssue, board);
        return issueMapper.issueToCreateIssueResponse(issue);
    }

    public GetIssueByIdResponse getIssueById(String issueId) throws BaseException {
        //validate
        if (issueId == null){
            //throw id null exception
            throw IssueException.getIssueIdNull();
        }
        if (issueId.trim().isEmpty()){
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
        if (issueId == null){
            //throw id null exception
            throw IssueException.editIssueIdNull();
        }
        if (issueId.trim().isEmpty()){
            //throw id empty exception
            throw IssueException.editIssueIdEmpty();
        }
        System.out.print(request.isCompleted());

        //check type compatibility
        Issue parentIssue = null;
        if(request.getParentIssueId() != null){
            parentIssue = issueService.findIssueById(request.getParentIssueId());
            Issue childIssue = issueService.findIssueById(issueId);
            issueValidator.validateTypeCompatibility(childIssue.getIssueType(), parentIssue.getIssueType());
        }

        Board board = null;
        if(request.getBoardId() != null){
            board = boardService.findBoardById(request.getBoardId());
        }

        TaskStatus status = null;
        PriorityType priorityType = null;
        if (request.getStatus() != null){
            try {
                status = TaskStatus.valueOf(request.getStatus().toUpperCase());

            } catch (IllegalArgumentException e) {
                throw IssueException.editInvalidIssueType();
            }
        }
        if (request.getPriority() != null){
            try {
                priorityType = PriorityType.valueOf(request.getPriority().toUpperCase());

            } catch (IllegalArgumentException e) {
                throw IssueException.editInvalidIssueType();
            }
        }

        //call editIssueById service
        Issue issue = issueService.editIssue(issueId, request.getName(), request.getDescription(), status, request.getDueDate(), request.isCompleted(), priorityType, parentIssue, board);

        //map
        return issueMapper.issueToEditIssueByIdResponse(issue);

    }
}
