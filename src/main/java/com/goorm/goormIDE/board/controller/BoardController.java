package com.goorm.goormIDE.board.controller;

import com.goorm.goormIDE.board.dto.BoardDTO;
import com.goorm.goormIDE.board.service.BoardService;
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

    @PostMapping("/save")
    public ResponseEntity<BoardDTO> save(@RequestBody BoardDTO boardDTO) {
        BoardDTO savedBoard = boardService.save(boardDTO);
        return ResponseEntity.ok(savedBoard);
    }

    @GetMapping("/")
    public ResponseEntity<List<BoardDTO>> findAll() {
        List<BoardDTO> boardDTOList = boardService.findAll();
        return ResponseEntity.ok(boardDTOList);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<BoardDTO> getPostById(@PathVariable Long id) {
        BoardDTO boardDTO = boardService.findById(id);
        if (boardDTO != null) {
            return ResponseEntity.ok(boardDTO);
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