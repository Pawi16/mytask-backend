package com.pawi16.taskapp.taskapp.repository;

import com.pawi16.taskapp.taskapp.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, String> {

    List<Board> findAllByCreatedUserIdAndIsDeletedFalse(String id);

    Optional<Board> findByIdAndIsDeletedFalse(String s);
}
