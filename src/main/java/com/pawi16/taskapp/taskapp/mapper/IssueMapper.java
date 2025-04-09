package com.pawi16.taskapp.taskapp.mapper;

import com.pawi16.taskapp.taskapp.entity.Issue;
import com.pawi16.taskapp.taskapp.model.CreateIssueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IssueMapper {

    @Mapping(source = "board.id", target = "boardId")
    @Mapping(source = "parentIssue.id", target = "parentIssueId")
    CreateIssueResponse issueToCreateIssueResponse (Issue issue);
}
