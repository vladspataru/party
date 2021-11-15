package com.vlad.party;

import com.vlad.party.entities.Participant;
import com.vlad.party.entities.Party;
import com.vlad.party.repositories.ParticipantRepository;
import com.vlad.party.repositories.PartyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartyApplication.class, args);
    }

    @Bean
    public CommandLineRunner testApp(PartyRepository partyRepository) {
        return args -> {
            Party party1 = new Party();
            party1.setPartyName("Best party evah");
            Set<Participant> participantSet = new HashSet<>();
            participantSet.add(new Participant("Spataru", "Vlad", 45, Boolean.TRUE, party1));
            participantSet.add(new Participant("A", "Aluna", 45, Boolean.TRUE, party1));
            party1.setParticipants(participantSet);
            partyRepository.save(party1);
        };
    }

}
