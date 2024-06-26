package com.example.eksamensprojekt.dto;

import com.example.eksamensprojekt.model.Result;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDto {
    private Long id;
    private String resultType;
    private Date date;
    private String resultValue;
    private Long participantId; // Only ID to avoid deep nesting
    private Long disciplineId;  // Only ID to avoid deep nesting

    public ResultDto(Result result) {
        this.id = result.getId();
        this.resultType = result.getResultType();
        this.date = result.getDate();
        this.resultValue = result.getResultValue();
        this.participantId = result.getParticipant() != null ? result.getParticipant().getId() : null;
        this.disciplineId = result.getDiscipline() != null ? result.getDiscipline().getId() : null;
    }

    public Result toModel() {
        Result result = new Result();
        result.setId(this.id);
        result.setResultType(this.resultType);
        result.setDate(this.date);
        result.setResultValue(this.resultValue);
        // Skip participant and discipline to avoid circular reference
        return result;
    }
}
