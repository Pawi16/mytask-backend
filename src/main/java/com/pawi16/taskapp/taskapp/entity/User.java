package com.pawi16.taskapp.taskapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_user")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false, length = 120)
    private String firstName;

    @Column(nullable = false, length = 120)
    private String lastName;

    @Column(nullable = false, length = 120)
    private String password;

    @OneToMany(mappedBy = "createdUser", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Task> createdTask;

    @OneToMany(mappedBy = "assignedUser", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Task> assignedTask;

}
