package com.goorm.goormIDE.board.repository;

import com.goorm.goormIDE.board.entity.BoardEntity;
import com.goorm.goormIDE.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
