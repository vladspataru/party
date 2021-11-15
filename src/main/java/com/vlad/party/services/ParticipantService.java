package com.vlad.party.services;

import com.vlad.party.entities.Participant;
import com.vlad.party.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Participant getParticipantById(long id) {
        return participantRepository.findById(id).get();
    }

    public Participant addParticipant(Participant participant) {
        if (participant.getParticipantId() == null) {
            return participantRepository.save(participant);
        }
        return participant;
    }

    public Participant updateParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public void deleteParticipant(long id) {
        participantRepository.deleteById(id);
    }
}
