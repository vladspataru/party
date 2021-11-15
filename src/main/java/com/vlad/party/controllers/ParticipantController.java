package com.vlad.party.controllers;

import com.vlad.party.entities.Participant;
import com.vlad.party.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ParticipantController {

    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    //@RequestMapping(value = "/participants", method = RequestMethod.GET)
    @GetMapping("/participants")
    public List<Participant> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    //@RequestMapping(value = "/participants/{id}", method = RequestMethod.GET)
    @GetMapping("/participants/{id}")
    public ResponseEntity<?> getParticipantById(@Valid @PathVariable long id) {
        return ResponseEntity.ok(participantService.getParticipantById(id));
    }

    @RequestMapping(value = "/participants", method = RequestMethod.POST)
    public ResponseEntity<?> createParticipant(@RequestBody @Valid Participant participant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        participantService.addParticipant(participant);

        return ResponseEntity.ok("Saved");
    }

    //TODO use id instead
    @RequestMapping(value = "/participants", method = RequestMethod.PUT)
    public ResponseEntity<?> updateParticipant(@RequestBody @Valid Participant participant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getFieldError());
        }
        return ResponseEntity.ok().body(participantService.updateParticipant(participant));
    }

    @RequestMapping(value = "/participants/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteParticipant(@PathVariable long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.ok("Deleted");
    }


}
