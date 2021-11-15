package com.vlad.party;

import com.vlad.party.entities.Party;
import com.vlad.party.services.PartyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PartyApplicationTests {

    private PartyService partyService = mock(PartyService.class);

    @Test
    @DisplayName("Should check if the getAll method returns the size of the array")
    void contextLoads() {
        when(partyService.getAllParties()).thenReturn(new ArrayList<>(Arrays.asList(new Party())));
        Assertions.assertEquals(1, partyService.getAllParties().size());
    }

}
