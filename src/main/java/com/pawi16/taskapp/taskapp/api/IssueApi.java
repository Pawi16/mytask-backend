package com.pawi16.taskapp.taskapp.api;

import com.pawi16.taskapp.taskapp.business.IssueBusiness;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.model.CreateIssueRequest;
import com.pawi16.taskapp.taskapp.model.CreateIssueResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
