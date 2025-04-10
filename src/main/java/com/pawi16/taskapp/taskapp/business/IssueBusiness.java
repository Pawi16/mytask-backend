package com.pawi16.taskapp.taskapp.business;

import com.pawi16.taskapp.taskapp.entity.*;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.IssueException;
import com.pawi16.taskapp.taskapp.mapper.IssueMapper;
import com.pawi16.taskapp.taskapp.model.CreateIssueRequest;
import com.pawi16.taskapp.taskapp.model.CreateIssueResponse;
import com.pawi16.taskapp.taskapp.model.GetIssueByIdResponse;
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

            childType = IssueType.valueOf(request.getIssueType());
            priorityType = PriorityType.valueOf(request.getPriority());

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
        }
        if (issueId.trim().isEmpty()){
            //throw id empty exception
        }

        //call getIssueById service
        Issue issue = issueService.findIssueById(issueId);

        //mapper
        return issueMapper.issueToGetIssueByIdResponse(issue);
    }
}
