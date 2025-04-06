package com.pawi16.taskapp.taskapp.mapper;

import com.pawi16.taskapp.taskapp.entity.Board;
import com.pawi16.taskapp.taskapp.model.CreateBoardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    @Mapping(target = "message", ignore = true)
    public CreateBoardResponse boardToCreateBoardResponse (Board board);
}
