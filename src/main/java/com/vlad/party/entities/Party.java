package com.vlad.party.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Party implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyId;

    private Integer entryFee;

    @NotNull
    @NotBlank
    private String partyName;

    @JsonManagedReference
    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    private Set<Participant> participants = new HashSet<>();

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    @Override
    public String toString() {
        return "Party{" +
                "partyId=" + partyId +
                ", entryFee=" + entryFee +
                ", partyName='" + partyName + '\'' +
                '}';
    }
}
