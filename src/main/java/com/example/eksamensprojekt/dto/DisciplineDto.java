package com.example.eksamensprojekt.dto;

import com.example.eksamensprojekt.model.Discipline;
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
public class DisciplineDto {
    private Long id;
    private String name;
    private String resultType;
    private String username;
    private Set<Long> resultIds; // Only IDs to avoid deep nesting

    public DisciplineDto(Discipline discipline) {
        this.id = discipline.getId();
        this.name = discipline.getName();
        this.resultType = discipline.getResultType();
        this.username = discipline.getUser() != null ? discipline.getUser().getUsername() : null;
        this.resultIds = discipline.getResults() != null ? discipline.getResults().stream().map(result -> result.getId()).collect(Collectors.toSet()) : null;
    }

    public Discipline toModel() {
        Discipline discipline = new Discipline();
        discipline.setId(this.id);
        discipline.setName(this.name);
        discipline.setResultType(this.resultType);
        // Skip results to avoid circular reference
        return discipline;
    }
}
