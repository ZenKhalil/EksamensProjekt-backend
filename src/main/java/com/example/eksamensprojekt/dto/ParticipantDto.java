package com.example.eksamensprojekt.dto;

import com.example.eksamensprojekt.model.Participant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParticipantDto {
    private Long id;
    private String name;
    private String gender;
    private int age;
    private String club;
    private String username;
    private Set<Long> resultIds;
    private Set<String> disciplines;

    public ParticipantDto(Participant participant) {
        this.id = participant.getId();
        this.name = participant.getName();
        this.gender = participant.getGender();
        this.age = participant.getAge();
        this.club = participant.getClub();
        this.username = participant.getUser() != null ? participant.getUser().getUsername() : null;
        this.resultIds = participant.getResults() != null ? participant.getResults().stream().map(result -> result.getId()).collect(Collectors.toSet()) : null;
        this.disciplines = participant.getResults() != null ? participant.getResults().stream().map(result -> result.getDiscipline().getName()).collect(Collectors.toSet()) : null;
    }

    public Participant toModel() {
        Participant participant = new Participant();
        participant.setId(this.id);
        participant.setName(this.name);
        participant.setGender(this.gender);
        participant.setAge(this.age);
        participant.setClub(this.club);
        return participant;
    }
}
