package com.example.eksamensprojekt.config;

import com.example.eksamensprojekt.model.Participant;
import com.example.eksamensprojekt.model.User;
import com.example.eksamensprojekt.repository.ParticipantRepository;
import com.example.eksamensprojekt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitialization {

    @Bean
    public CommandLineRunner initializeData(ParticipantRepository participantRepository, UserRepository userRepository) {
        return args -> {
            User adminUser = userRepository.findByUsername("admin");
            if (adminUser != null) {
                List<Participant> participants = participantRepository.findAll();
                for (Participant participant : participants) {
                    if (participant.getUser() == null) {
                        participant.setUser(adminUser);
                    }
                }
                participantRepository.saveAll(participants);
            }
        };
    }
}
