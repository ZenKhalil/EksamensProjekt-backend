package com.example.eksamensprojekt.service;

import com.example.eksamensprojekt.dto.ParticipantDto;
import com.example.eksamensprojekt.model.Participant;
import com.example.eksamensprojekt.model.User;
import com.example.eksamensprojekt.repository.ParticipantRepository;
import com.example.eksamensprojekt.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    private final ParticipantRepository repository;
    private final UserRepository userRepository;

    public ParticipantService(ParticipantRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public ParticipantDto createParticipant(ParticipantDto participantDto) {
        Participant participant = participantDto.toModel();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername);
        participant.setUser(user);

        Participant savedParticipant = repository.save(participant);
        return new ParticipantDto(savedParticipant);
    }

    public ParticipantDto updateParticipant(Long id, ParticipantDto participantDto) {
        Optional<Participant> existingParticipantOpt = repository.findById(id);
        if (existingParticipantOpt.isPresent()) {
            Participant existingParticipant = existingParticipantOpt.get();
            existingParticipant.setName(participantDto.getName());
            existingParticipant.setGender(participantDto.getGender());
            existingParticipant.setAge(participantDto.getAge());
            existingParticipant.setClub(participantDto.getClub());
            Participant updatedParticipant = repository.save(existingParticipant);
            return new ParticipantDto(updatedParticipant);
        } else {
            throw new ResourceNotFoundException("Participant not found with id " + id);
        }
    }

    public void deleteParticipant(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Participant not found with id " + id);
        }
    }

    public List<ParticipantDto> getAllParticipants() {
        List<Participant> participants = repository.findAll();
        return participants.stream()
                .map(ParticipantDto::new)
                .collect(Collectors.toList());
    }

    public ParticipantDto getParticipantById(Long id) {
        Participant participant = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Participant not found with id " + id));
        return new ParticipantDto(participant);
    }
}
