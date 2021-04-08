package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.RoundIsOverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoundTest {
    @Test
    void setId() {
        Round round = new Round();
        round.setId(Long.parseLong("1"));
        assertEquals(Long.parseLong("1"), round.getId());
    }

    @Test
    void setGame() {
        Round round = new Round();
        round.setGame(new Game());
        assertEquals(new Game().toString(), round.getGame().toString());
    }

    @Test
    void setGuesses() {
        Round round = new Round();
        round.setGuesses(3);
        assertEquals(3,round.getGuesses());
    }

    @Test
    void setWord() {
        Round round = new Round();
        round.setWord("woord");
        assertEquals("woord",round.getWord());
    }

    @Test
    void setWon() {
        Round round = new Round();
        round.setWon(true);
        assertTrue(round.getWon());
    }

    @Test
    void setFeedback() {
        Round round = new Round();
        round.setFeedback(new Feedback());
        assertEquals(new Feedback().toString(),round.getFeedback().toString());
    }
}