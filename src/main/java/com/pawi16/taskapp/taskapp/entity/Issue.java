package com.pawi16.taskapp.taskapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"parentIssue", "childIssues"})
@ToString(exclude = {"parentIssue", "childIssues"})
@Data
@Entity(name = "m_issue")
public class Issue extends BaseEntity {

    @Column(nullable = false, length = 120)
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;



    @Column
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    @Column(nullable = false)
    private boolean isCompleted;

    @Enumerated(EnumType.STRING)
    private PriorityType priority;

    @ManyToOne
    @JoinColumn(name = "m_created_by", nullable = false)
    private User createdUser;

    @ManyToOne
    @JoinColumn(name = "m_parent_issue")
    private Issue parentIssue;

    @ManyToOne
    @JoinColumn(name = "m_board_id")
    private Board board;

    @OneToMany(mappedBy = "parentIssue",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Issue> childIssues;

}

