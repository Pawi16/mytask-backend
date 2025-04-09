package com.pawi16.taskapp.taskapp.service;

import com.pawi16.taskapp.taskapp.entity.*;
import com.pawi16.taskapp.taskapp.exception.BaseException;
import com.pawi16.taskapp.taskapp.exception.IssueException;
import com.pawi16.taskapp.taskapp.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue findIssueById (String id) throws BaseException {
        Optional<Issue> opt = issueRepository.findById(id);
        if (opt.isEmpty()){
            throw IssueException.getIssueNotFound();
        }
        return opt.get();
    }

    public Issue createIssue (String name, String description, LocalDate dueDate, IssueType issueType, PriorityType priorityType, User createdUser, Issue parentIssue, Board board) throws BaseException {
        Issue entity = new Issue();
        entity.setName(name);
        entity.setDescription(description);
        entity.setStatus(TaskStatus.PLANNING);
        entity.setDueDate(dueDate);
        entity.setIssueType(issueType);
        entity.setCompleted(false);
        entity.setPriority(priorityType);
        entity.setCreatedUser(createdUser);
        entity.setParentIssue(parentIssue);
        entity.setBoard(board);
        issueRepository.save(entity);
        return entity;
    }


}
