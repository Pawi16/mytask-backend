package com.pawi16.taskapp.taskapp.service;

import com.pawi16.taskapp.taskapp.entity.*;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.IssueException;
import com.pawi16.taskapp.taskapp.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue findIssueById (String id) throws BaseException {
        Optional<Issue> opt = issueRepository.findByIdAndIsDeletedFalse(id);
        if (opt.isEmpty()){
            throw IssueException.getIssueNotFound();
        }
        return opt.get();
    }

    public Issue createIssue (String name, String description, TaskStatus status, boolean isDeleted, LocalDate dueDate, IssueType issueType, boolean isCompleted, PriorityType priorityType, User createdUser, Issue parentIssue, Board board) throws BaseException {
        Issue entity = new Issue();
        entity.setName(name);
        entity.setDescription(description);
        entity.setStatus(status);
        entity.setDeleted(isDeleted);
        entity.setDueDate(dueDate);
        entity.setIssueType(issueType);
        entity.setCompleted(isCompleted);
        entity.setPriority(priorityType);
        entity.setCreatedUser(createdUser);
        entity.setParentIssue(parentIssue);
        entity.setBoard(board);
        issueRepository.save(entity);
        return entity;
    }

    public Issue editIssue (String issueId, String name, String description, TaskStatus status, LocalDate dueDate, boolean isCompleted, PriorityType priority, Issue parentIssue, Board board) throws BaseException {
        Issue issue = findIssueById(issueId);
        if(name != null){
            issue.setName(name);
        }
        if(description != null){
            issue.setDescription(description);
        }
        if(status != null){
            issue.setStatus(status);
        }
        if(dueDate != null){
            issue.setDueDate(dueDate);
        }
        issue.setCompleted(isCompleted);
        if(priority != null){
            issue.setPriority(priority);
        }
        if(parentIssue != null){
            issue.setParentIssue(parentIssue);
        }
        if(board != null){
            issue.setBoard(board);
        }

        issueRepository.save(issue);
        return issue;
    }

    public void updateIssueBoardRecursive(Issue issue, Board newBoard) {
        if (issue.isDeleted()) {
            return;
        }
        issue.setBoard(newBoard);
        issueRepository.save(issue);

        if (issue.getChildIssues() != null && !issue.getChildIssues().isEmpty()) {
            for (Issue childIssue : issue.getChildIssues()) {
                if (!childIssue.isDeleted()) {
                    updateIssueBoardRecursive(childIssue, newBoard);
                }
            }
        }
    }

    public void softDeleteIssueRecursive(Issue issue){
        issue.setDeleted(true);
        issueRepository.save(issue);

        if (issue.getChildIssues() != null && !issue.getChildIssues().isEmpty()) {
            for(Issue childIssue : issue.getChildIssues()) {
                if (!childIssue.isDeleted()) {
                    softDeleteIssueRecursive(childIssue);
                }
            }
        }
    }


}
