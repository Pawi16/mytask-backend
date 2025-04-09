package com.pawi16.taskapp.taskapp.repository;

import com.pawi16.taskapp.taskapp.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue,String> {
}
