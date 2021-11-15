package com.vlad.party.controllers;

import com.vlad.party.entities.Participant;
import com.vlad.party.entities.Party;
import com.vlad.party.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class PartyController {

    private final PartyService partyService;

    @Autowired
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @RequestMapping(value = "/parties", method = RequestMethod.GET)
    public ResponseEntity<List<Party>> getAllParties() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CACHE_CONTROL, "max-age=604800");

        return ResponseEntity.ok().headers(httpHeaders).body(partyService.getAllParties());
    }

    @RequestMapping(value = "/parties/{id}", method = RequestMethod.GET)
    public ResponseEntity<Party> getPartyById(@PathVariable long id) {
        return ResponseEntity.ok(partyService.getPartyById(id));
    }

    @RequestMapping(value = "/parties", method = RequestMethod.POST)
    public ResponseEntity<?> createParty(@RequestBody @Valid Party party, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getModel());
        }
        return ResponseEntity.ok().body(partyService.addParty(party));
    }

    //TODO use id in stead
    @RequestMapping(value = "/parties", method = RequestMethod.PUT)
    public ResponseEntity<?> updateParty(@RequestBody @Valid Party party, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getModel());
        }

        return ResponseEntity.ok().body(partyService.updateParty(party));
    }

    @RequestMapping(value = "/parties/{id}/participants", method = RequestMethod.PUT)
    public ResponseEntity<?> addParticipantToParty(@PathVariable long id, @RequestBody @Valid Participant participant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError());
        }

        return ResponseEntity.ok().body(partyService.addParticipantToParty(id, participant));

    }

    @RequestMapping(value = "/parties/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteParty(@PathVariable long id) {
        partyService.deleteParty(id);
        return ResponseEntity.ok("Deleted");
    }

}
