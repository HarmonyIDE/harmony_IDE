package com.goorm.goormIDE.board.service;

import com.goorm.goormIDE.board.dto.CommentDTO;
import com.goorm.goormIDE.board.entity.BoardEntity;
import com.goorm.goormIDE.board.entity.CommentEntity;
import com.goorm.goormIDE.board.repository.BoardRepository;
import com.goorm.goormIDE.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity  commentEntity =  CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }

    }

    public List<CommentDTO> findAll(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);
        if (!optionalBoardEntity.isPresent()) {
            return new ArrayList<>(); // 게시글이 존재하지 않을 경우 빈 리스트 반환
        }
        BoardEntity boardEntity = optionalBoardEntity.get();
        List<CommentEntity> commentEntities = commentRepository.findByBoardEntityOrderByIdDesc(boardEntity);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOs.add(commentDTO);
        }
        return commentDTOs;
    }
}