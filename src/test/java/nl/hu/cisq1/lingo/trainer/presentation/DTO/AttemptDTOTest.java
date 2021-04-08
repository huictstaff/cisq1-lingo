package nl.hu.cisq1.lingo.trainer.presentation.DTO;

import org.aspectj.apache.bcel.generic.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttemptDTOTest {

    @Test
    void setAttempt() {
        AttemptDTO attemptDTO = new AttemptDTO("woord");
        attemptDTO.setAttempt("appel");
        assertEquals("appel", attemptDTO.getAttempt());
    }

    @Test
    void testToString() {
        AttemptDTO attemptDTO = new AttemptDTO("woord");
        assertNotNull(attemptDTO.toString());
    }
}