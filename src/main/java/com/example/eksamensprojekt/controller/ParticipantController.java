package com.example.eksamensprojekt.controller;

import com.example.eksamensprojekt.dto.ParticipantDto;
import com.example.eksamensprojekt.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService service;

    public ParticipantController(ParticipantService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ParticipantDto> createParticipant(@RequestBody ParticipantDto participantDto) {
        ParticipantDto savedParticipant = service.createParticipant(participantDto);
        return ResponseEntity.ok(savedParticipant);
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwnerParticipant(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<ParticipantDto> updateParticipant(@PathVariable Long id, @RequestBody ParticipantDto participantDto) {
        ParticipantDto updatedParticipant = service.updateParticipant(id, participantDto);
        return ResponseEntity.ok(updatedParticipant);
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwnerParticipant(#id)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        service.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ParticipantDto>> getAllParticipants() {
        List<ParticipantDto> participantDtos = service.getAllParticipants();
        return ResponseEntity.ok(participantDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantDto> getParticipantById(@PathVariable Long id) {
        ParticipantDto participant = service.getParticipantById(id);
        return ResponseEntity.ok(participant);
    }
}
