package com.example.eksamensprojekt.controller;

import com.example.eksamensprojekt.dto.DisciplineDto;
import com.example.eksamensprojekt.service.DisciplineService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController {

    private final DisciplineService service;

    public DisciplineController(DisciplineService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DisciplineDto> createDiscipline(@RequestBody DisciplineDto disciplineDto) {
        DisciplineDto savedDiscipline = service.createDiscipline(disciplineDto);
        return ResponseEntity.ok(savedDiscipline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplineDto> updateDiscipline(@PathVariable Long id, @RequestBody DisciplineDto disciplineDto) {
        DisciplineDto updatedDiscipline = service.updateDiscipline(id, disciplineDto);
        return ResponseEntity.ok(updatedDiscipline);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        service.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DisciplineDto>> getAllDisciplines() {
        List<DisciplineDto> disciplineDtos = service.getAllDisciplines();
        return ResponseEntity.ok(disciplineDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDto> getDisciplineById(@PathVariable Long id) {
        DisciplineDto discipline = service.getDisciplineById(id);
        return ResponseEntity.ok(discipline);
    }
}
