package com.example.eksamensprojekt.controller;

import com.example.eksamensprojekt.dto.ResultDto;
import com.example.eksamensprojekt.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    private final ResultService service;

    public ResultController(ResultService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResultDto> createResult(@RequestBody ResultDto resultDto) {
        ResultDto savedResult = service.createResult(resultDto);
        return ResponseEntity.ok(savedResult);
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwnerResult(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<ResultDto> updateResult(@PathVariable Long id, @RequestBody ResultDto resultDto) {
        ResultDto updatedResult = service.updateResult(id, resultDto);
        return ResponseEntity.ok(updatedResult);
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwnerResult(#id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        service.deleteResult(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResultDto>> getAllResults() {
        List<ResultDto> resultDtos = service.getAllResults();
        return ResponseEntity.ok(resultDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDto> getResultById(@PathVariable Long id) {
        ResultDto result = service.getResultById(id);
        return ResponseEntity.ok(result);
    }
}
