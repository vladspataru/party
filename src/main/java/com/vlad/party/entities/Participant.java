package com.vlad.party.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    private Integer age;

    private Boolean hasAppropriateCostume;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    public Participant(String firstName, String lastName, Integer age, Boolean hasAppropriateCostume, Party party) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hasAppropriateCostume = hasAppropriateCostume;
        this.party = party;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "participantId=" + participantId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", hasAppropriateCostume=" + hasAppropriateCostume +
                '}';
    }
}
