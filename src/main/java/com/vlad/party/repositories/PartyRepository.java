package com.vlad.party.repositories;

import com.vlad.party.entities.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {
}
