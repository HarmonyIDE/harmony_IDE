package com.goorm.goormIDE.board.controller;

import com.goorm.goormIDE.board.dto.BoardDTO;
import com.goorm.goormIDE.board.dto.CommentDTO;
import com.goorm.goormIDE.board.service.BoardService;
import com.goorm.goormIDE.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity<BoardDTO> save(@RequestBody BoardDTO boardDTO) {
        BoardDTO savedBoard = boardService.save(boardDTO);
        return ResponseEntity.ok(savedBoard);

    }
    @RestController
    @RequestMapping("/comment")
    public class CommentController {
        private final CommentService commentService;

        public CommentController(CommentService commentService) {
            this.commentService = commentService;
        }

        @GetMapping("/list/{boardId}")
        public ResponseEntity<List<CommentDTO>> getCommentsByBoardId(@PathVariable Long boardId) {
            List<CommentDTO> comments = commentService.findAll(boardId);
            if (comments.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(comments);
        }
    }


    @GetMapping("/")
    public ResponseEntity<List<BoardDTO>> findAll() {
        List<BoardDTO> boardDTOList = boardService.findAll();
        return ResponseEntity.ok(boardDTOList);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Map<String, Object>> getPostById(@PathVariable Long id) {
        BoardDTO boardDTO = boardService.findById(id);
        List<CommentDTO> commentDTOs = commentService.findAll(id); // 게시글 ID에 해당하는 댓글들을 불러오는 서비스 메소드

        if (boardDTO != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("board", boardDTO);
            response.put("comments", commentDTOs); // 응답에 댓글 목록 추가
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<BoardDTO> update(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        boardDTO.setId(id); // 경로 변수에서 DTO로 ID 설정
        BoardDTO updatedBoard = boardService.update(boardDTO);
        return ResponseEntity.ok(updatedBoard);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/paging")
    public ResponseEntity<Map<String, Object>> paging(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<BoardDTO> boardPage = boardService.paging(pageable);
        Map<String, Object> response = new HashMap<>();
        response.put("posts", boardPage.getContent());
        response.put("currentPage", boardPage.getNumber());
        response.put("totalItems", boardPage.getTotalElements());
        response.put("totalPages", boardPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

}