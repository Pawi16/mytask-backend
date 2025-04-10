package com.pawi16.taskapp.taskapp.api;

import com.pawi16.taskapp.taskapp.business.IssueBusiness;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.model.CreateIssueRequest;
import com.pawi16.taskapp.taskapp.model.CreateIssueResponse;
import com.pawi16.taskapp.taskapp.model.GetIssueByIdResponse;
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
        //getIssueDetail service
        return issueBusiness.getIssueById(issueId);
    }
}
