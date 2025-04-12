package com.pawi16.taskapp.taskapp.repository;

import com.pawi16.taskapp.taskapp.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue,String> {
    List<Issue> findByBoardIdAndParentIssueIsNullAndIsDeletedFalse(String boardId);

    Optional<Issue> findByIdAndIsDeletedFalse(String s);


}
