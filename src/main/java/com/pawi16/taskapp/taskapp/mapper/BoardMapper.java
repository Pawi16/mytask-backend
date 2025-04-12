package com.pawi16.taskapp.taskapp.mapper;

import com.pawi16.taskapp.taskapp.entity.Board;
import com.pawi16.taskapp.taskapp.entity.Issue;
import com.pawi16.taskapp.taskapp.model.CreateBoardResponse;
import com.pawi16.taskapp.taskapp.model.DeleteBoardResponse;
import com.pawi16.taskapp.taskapp.model.EditBoardResponse;
import com.pawi16.taskapp.taskapp.model.GetAllBoardsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    @Mapping(target = "message", ignore = true)
    public CreateBoardResponse boardToCreateBoardResponse (Board board);

    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "message", ignore = true)
    public EditBoardResponse boardToEditBoardResponse (Board board);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "createdUser.firstName", target = "createUserFirstname")
    @Mapping(expression = "java(board.getIssues() != null ? board.getIssues().size() : 0)", target = "issueCount")
    GetAllBoardsResponse boardToGetAllBoardsResponse(Board board);

    public List<GetAllBoardsResponse> boardsToGetAllBoardsResponses (List<Board> boards) ;

}
