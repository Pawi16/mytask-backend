package com.pawi16.taskapp.taskapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_task")
public class Task extends BaseEntity {

    @Column(nullable = false, length = 120)
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "m_created_by", nullable = false)
    private User createdUser;

    @ManyToOne
    @JoinColumn(name = "m_assigned_to", nullable = false)
    private User assignedUser;
}

