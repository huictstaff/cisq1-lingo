package nl.hu.cisq1.lingo.trainer.presentation.DTO;

import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundDTOTest {
    private RoundDTO roundDTO;

    @BeforeEach
    void prepTest(){
        roundDTO = new RoundDTO(new Round("woord", new Game()));
    }

    @Test
    void setGuesses() {
        roundDTO.setGuesses(4);
        assertEquals(4, roundDTO.getGuesses());
    }

    @Test
    void setWord() {
        roundDTO.setWord("appel");
        assertEquals("appel", roundDTO.getWord());
    }

    @Test
    void setIsWon() {
        roundDTO.setIsWon(true);
        assertTrue(roundDTO.getIsWon());
    }

    @Test
    void setFeedback() {
        roundDTO.setFeedback(new FeedbackDTO());
        assertEquals(new FeedbackDTO().toString(), roundDTO.getFeedback().toString());
    }

    @Test
    void testToString() {
        assertNotNull(roundDTO.toString());
    }
}