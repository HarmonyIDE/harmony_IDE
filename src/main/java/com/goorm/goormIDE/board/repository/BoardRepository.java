package com.goorm.goormIDE.board.repository;

import com.goorm.goormIDE.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Modifying
    @Query("UPDATE BoardEntity b SET b.boardHits = b.boardHits + 1 WHERE b.id = :id")
    void updateHits(@Param("id") Long id);
}
