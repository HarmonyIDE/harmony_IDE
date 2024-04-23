package com.goorm.goormIDE.board.service;

import com.goorm.goormIDE.board.dto.BoardDTO;
import com.goorm.goormIDE.board.entity.BoardEntity;
import com.goorm.goormIDE.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardDTO save(BoardDTO boardDTO) {
        BoardEntity entity = BoardEntity.toSaveEntity(boardDTO);
        BoardEntity savedEntity = boardRepository.save(entity);
        return BoardDTO.toBoardDTO(savedEntity);
    }

    @Transactional(readOnly = true)
    public List<BoardDTO> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardDTO findById(Long id) {
        boardRepository.updateHits(id);  // 조회수 증가
        return boardRepository.findById(id)
                .map(BoardDTO::toBoardDTO)
                .orElse(null);
    }

    @Transactional
    public BoardDTO update(BoardDTO boardDTO) {
        // 게시물 ID를 이용하여 엔티티를 가져옴
        Optional<BoardEntity> optionalEntity = boardRepository.findById(boardDTO.getId());
        if (optionalEntity.isPresent()) {
            BoardEntity entity = optionalEntity.get();
            // 받은 DTO로 엔티티를 업데이트
            entity.setBoardWriter(boardDTO.getBoardWriter());
            //entity.setBoardPass(boardDTO.getBoardPass());
            entity.setBoardTitle(boardDTO.getBoardTitle());
            entity.setBoardContents(boardDTO.getBoardContents());
            // 엔티티를 저장하여 업데이트를 완료
            BoardEntity updatedEntity = boardRepository.save(entity);
            return BoardDTO.toBoardDTO(updatedEntity);
        } else {
            // 해당하는 ID의 게시물이 없을 경우 null 반환
            return null;
        }
    }


    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber(); // 현재 페이지 번호
        int size = 3; // 한 페이지에 보여질 게시글 수

        // 페이지 번호가 0부터 시작하므로 실제 DB 조회할 때는 0이 아닌 1을 빼주어야 함
        int adjustedPage = page > 0 ? page - 1 : 0;

        // 페이징 처리 코드 추가
        Page<BoardEntity> boardPage = boardRepository.findAll(PageRequest.of(adjustedPage, size, Sort.by(Sort.Direction.DESC, "id")));

        // 페이징된 게시글 목록을 BoardDTO로 변환
        Page<BoardDTO> boardDTOPage = boardPage.map(boardEntity -> {
            return new BoardDTO(
                    boardEntity.getId(),
                    boardEntity.getBoardWriter(),
                    boardEntity.getBoardTitle(),
                    boardEntity.getBoardHits(),
                    boardEntity.getCreatedTime()
            );
        });

        return boardDTOPage;
    }



}