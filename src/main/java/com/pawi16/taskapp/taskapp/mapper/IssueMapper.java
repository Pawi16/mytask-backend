package com.pawi16.taskapp.taskapp.mapper;

import com.pawi16.taskapp.taskapp.entity.Issue;
import com.pawi16.taskapp.taskapp.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IssueMapper {

    @Mapping(source = "board.id", target = "boardId")
    @Mapping(source = "parentIssue.id", target = "parentIssueId")
    CreateIssueResponse issueToCreateIssueResponse (Issue issue);

    @Mapping(source = "createdUser.id", target = "createdUserId")
    @Mapping(source = "parentIssue.id", target = "parentIssueId")
    @Mapping(source = "board.id", target = "boardId")
    GetIssueByIdResponse issueToGetIssueByIdResponse (Issue issue);

    @Mapping(source = "parentIssue.id", target = "parentIssueId")
    @Mapping(source = "board.id", target = "boardId")
    @Mapping(source = "updatedAt", target = "updatedAt")
    EditIssueByIdResponse issueToEditIssueByIdResponse (Issue issue);

    @Mapping(source = "id", target = "issueId")
    @Mapping(source = "board.id", target = "boardId")
    MoveIssueToBoardResponse issueToMoveIssueToBoardResponse (Issue issue);

    @Mapping(target = "message", ignore = true)
    @Mapping(target = "issueId", source = "issue.id")
    DeleteIssueResponse issueToDeleteIssueResponse (Issue issue);
}
