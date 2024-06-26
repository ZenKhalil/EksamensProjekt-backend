package com.example.eksamensprojekt.service;

import com.example.eksamensprojekt.dto.ResultDto;
import com.example.eksamensprojekt.model.Result;
import com.example.eksamensprojekt.repository.ParticipantRepository;
import com.example.eksamensprojekt.repository.ResultRepository;
import com.example.eksamensprojekt.repository.DisciplineRepository;
import com.example.eksamensprojekt.security.UserSecurity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final ParticipantRepository participantRepository;
    private final DisciplineRepository disciplineRepository;
    private final UserSecurity userSecurity;

    public ResultService(ResultRepository resultRepository, ParticipantRepository participantRepository, DisciplineRepository disciplineRepository, UserSecurity userSecurity) {
        this.resultRepository = resultRepository;
        this.participantRepository = participantRepository;
        this.disciplineRepository = disciplineRepository;
        this.userSecurity = userSecurity;
    }


    public ResultDto createResult(ResultDto resultDto) {
        Result result = resultDto.toModel();
        if (resultDto.getParticipantId() != null) {
            result.setParticipant(participantRepository.findById(resultDto.getParticipantId())
                    .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id " + resultDto.getParticipantId())));
        }
        if (resultDto.getDisciplineId() != null) {
            result.setDiscipline(disciplineRepository.findById(resultDto.getDisciplineId())
                    .orElseThrow(() -> new ResourceNotFoundException("Discipline not found with id " + resultDto.getDisciplineId())));
        }
        Result savedResult = resultRepository.save(result);
        return new ResultDto(savedResult);
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwnerResult(#id)")
    public ResultDto updateResult(Long id, ResultDto resultDto) {
        Optional<Result> existingResultOpt = resultRepository.findById(id);
        if (existingResultOpt.isPresent()) {
            Result existingResult = existingResultOpt.get();
            existingResult.setResultType(resultDto.getResultType());
            existingResult.setDate(resultDto.getDate());
            existingResult.setResultValue(resultDto.getResultValue());
            if (resultDto.getParticipantId() != null) {
                existingResult.setParticipant(participantRepository.findById(resultDto.getParticipantId())
                        .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id " + resultDto.getParticipantId())));
            }
            if (resultDto.getDisciplineId() != null) {
                existingResult.setDiscipline(disciplineRepository.findById(resultDto.getDisciplineId())
                        .orElseThrow(() -> new ResourceNotFoundException("Discipline not found with id " + resultDto.getDisciplineId())));
            }
            Result updatedResult = resultRepository.save(existingResult);
            return new ResultDto(updatedResult);
        } else {
            throw new ResourceNotFoundException("Result not found with id " + id);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwnerResult(#id)")
    public void deleteResult(Long id) {
        if (resultRepository.existsById(id)) {
            resultRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Result not found with id " + id);
        }
    }

    public List<ResultDto> getAllResults() {
        List<Result> results = resultRepository.findAll();
        return results.stream()
                .map(ResultDto::new)
                .collect(Collectors.toList());
    }

    public ResultDto getResultById(Long id) {
        Result result = resultRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Result not found with id " + id));
        return new ResultDto(result);
    }
}
