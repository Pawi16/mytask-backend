package com.pawi16.taskapp.taskapp.api;

import com.pawi16.taskapp.taskapp.business.IssueBusiness;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issues")
public class IssueApi {

    private final IssueBusiness issueBusiness;

    public IssueApi(IssueBusiness issueBusiness) {
        this.issueBusiness = issueBusiness;
    }

    @PostMapping
    public CreateIssueResponse createIssue(@RequestBody CreateIssueRequest request) throws BaseException {
        //createIssue business
        return issueBusiness.createIssue(request);
    }

    @GetMapping("/{issueId}")
    public GetIssueByIdResponse getIssueById (@PathVariable("issueId") String issueId) throws BaseException {
        //getIssueDetail business
        return issueBusiness.getIssueById(issueId);
    }

    @PatchMapping("/{issueId}")
    public EditIssueByIdResponse editIssueById (@PathVariable("issueId") String issueId, @RequestBody EditIssueByIdRequest request) throws BaseException {
        //editIssueDetail business
//        System.out.println("Received request: " + request);
        return issueBusiness.editIssueById(issueId,request);
    }

    @PatchMapping("/{issueId}/move")
    public MoveIssueToBoardResponse moveIssueToBoard (@PathVariable("issueId") String issueId, @RequestBody MoveIssueToBoardRequest request) throws BaseException {
        //move issue to board business
        return issueBusiness.moveIssueToBoard(issueId, request);
    }

    @DeleteMapping("/{issueId}")
    public DeleteIssueResponse deleteIssueById (@PathVariable("issueId") String issueId) throws BaseException {
        //delete issue business
        return issueBusiness.deleteIssueById(issueId);
    }


}
