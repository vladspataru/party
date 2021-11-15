package com.vlad.party.services;

import com.vlad.party.entities.Participant;
import com.vlad.party.entities.Party;
import com.vlad.party.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService {

    private final PartyRepository partyRepository;

    @Autowired
    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }


    public Party getPartyById(long id) {
        return partyRepository.findById(id).get();
    }

    public Party addParty(Party party) {
        if (party.getPartyId() == null) {
            return partyRepository.save(party);
        }
        return party;
    }

    public Party updateParty(Party party) {

        if (party.getPartyId() == null) {
            return null;
        }

        return partyRepository.save(party);

    }

    public void deleteParty(long id) {
        partyRepository.deleteById(id);
    }

    public Party addParticipantToParty(long id, Participant participant) {
        Party party = partyRepository.findById(id).get();
        participant.setParty(party);
        party.addParticipant(participant);
        partyRepository.save(party);

        return party;
    }
}
