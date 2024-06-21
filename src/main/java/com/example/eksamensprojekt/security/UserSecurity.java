package com.example.eksamensprojekt.security;

import com.example.eksamensprojekt.model.Discipline;
import com.example.eksamensprojekt.model.Participant;
import com.example.eksamensprojekt.model.Result;
import com.example.eksamensprojekt.repository.DisciplineRepository;
import com.example.eksamensprojekt.repository.ParticipantRepository;
import com.example.eksamensprojekt.repository.ResultRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {

    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;
    private final ResultRepository resultRepository;

    public UserSecurity(ParticipantRepository participantRepository, DisciplineRepository disciplineRepository, ResultRepository resultRepository) {
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
        this.resultRepository = resultRepository;
    }

    public boolean isOwnerParticipant(Long participantId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Participant participant = participantRepository.findById(participantId).orElse(null);
        return participant != null && participant.getUser().getUsername().equals(currentUsername);
    }

    public boolean isOwnerDiscipline(Long disciplineId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Discipline discipline = disciplineRepository.findById(disciplineId).orElse(null);
        return discipline != null && discipline.getUser().getUsername().equals(currentUsername);
    }

    public boolean isOwnerResult(Long resultId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Result result = resultRepository.findById(resultId).orElse(null);
        return result != null && result.getParticipant().getUser().getUsername().equals(currentUsername);
    }
}
