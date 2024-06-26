package com.example.eksamensprojekt.security;

import com.example.eksamensprojekt.model.Discipline;
import com.example.eksamensprojekt.model.Participant;
import com.example.eksamensprojekt.model.Result;
import com.example.eksamensprojekt.repository.DisciplineRepository;
import com.example.eksamensprojekt.repository.ParticipantRepository;
import com.example.eksamensprojekt.repository.ResultRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {

    private final ParticipantRepository participantRepository;
    private final ResultRepository resultRepository;
    private final DisciplineRepository disciplineRepository;

    public UserSecurity(ParticipantRepository participantRepository, ResultRepository resultRepository, DisciplineRepository disciplineRepository) {
        this.participantRepository = participantRepository;
        this.resultRepository = resultRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public boolean isOwnerParticipant(Long participantId) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Participant participant = participantRepository.findById(participantId).orElse(null);
        if (participant == null || participant.getUser() == null) {
            return false;
        }
        boolean isOwner = participant.getUser().getUsername().equals(username);
        System.out.println("Checking ownership for user: " + username + ", isOwner: " + isOwner + ", userId: " + participant.getUser().getId() + ", participantId: " + participantId);
        return isOwner;
    }

    public boolean isOwnerResult(Long resultId) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Result result = resultRepository.findById(resultId).orElse(null);
        if (result == null || result.getParticipant() == null || result.getParticipant().getUser() == null) {
            return false;
        }
        boolean isOwner = result.getParticipant().getUser().getUsername().equals(username);
        System.out.println("Checking ownership for user: " + username + ", isOwner: " + isOwner + ", userId: " + result.getParticipant().getUser().getId() + ", resultId: " + resultId);
        return isOwner;
    }

    public boolean isOwnerDiscipline(Long disciplineId) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Discipline discipline = disciplineRepository.findById(disciplineId).orElse(null);
        if (discipline == null || discipline.getUser() == null) {
            return false;
        }
        boolean isOwner = discipline.getUser().getUsername().equals(username);
        System.out.println("Checking ownership for user: " + username + ", isOwner: " + isOwner + ", userId: " + discipline.getUser().getId() + ", disciplineId: " + disciplineId);
        return isOwner;
    }
}
