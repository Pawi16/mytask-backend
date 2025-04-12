package com.pawi16.taskapp.taskapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_board")
public class Board extends BaseEntity{
    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "m_created_by", nullable = false)
    private User createdUser;

    @Column(nullable = false)
    private boolean isDeleted;

    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Issue> issues;
}
