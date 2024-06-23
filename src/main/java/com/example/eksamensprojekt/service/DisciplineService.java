package com.example.eksamensprojekt.service;

import com.example.eksamensprojekt.dto.DisciplineDto;
import com.example.eksamensprojekt.model.Discipline;
import com.example.eksamensprojekt.model.User;
import com.example.eksamensprojekt.repository.DisciplineRepository;
import com.example.eksamensprojekt.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplineService {

    private final DisciplineRepository repository;
    private final UserRepository userRepository;

    public DisciplineService(DisciplineRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public DisciplineDto createDiscipline(DisciplineDto disciplineDto) {
        Discipline discipline = disciplineDto.toModel();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername);
        discipline.setUser(user);

        Discipline savedDiscipline = repository.save(discipline);
        return new DisciplineDto(savedDiscipline);
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwnerDiscipline(#id)")
    public DisciplineDto updateDiscipline(Long id, DisciplineDto disciplineDto) {
        Optional<Discipline> existingDisciplineOpt = repository.findById(id);
        if (existingDisciplineOpt.isPresent()) {
            Discipline existingDiscipline = existingDisciplineOpt.get();
            existingDiscipline.setName(disciplineDto.getName());
            existingDiscipline.setResultType(disciplineDto.getResultType());
            Discipline updatedDiscipline = repository.save(existingDiscipline);
            return new DisciplineDto(updatedDiscipline);
        } else {
            throw new ResourceNotFoundException("Discipline not found with id " + id);
        }
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isOwnerDiscipline(#id)")
    public void deleteDiscipline(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Discipline not found with id " + id);
        }
    }

    public List<DisciplineDto> getAllDisciplines() {
        List<Discipline> disciplines = repository.findAll();
        return disciplines.stream()
                .map(DisciplineDto::new)
                .collect(Collectors.toList());
    }

    public DisciplineDto getDisciplineById(Long id) {
        Discipline discipline = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discipline not found with id " + id));
        return new DisciplineDto(discipline);
    }
}
