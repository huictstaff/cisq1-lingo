package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    private Round round;

    @BeforeEach
    void init() {
        this.round = new Round("aapje");
    }

    @Test
    @DisplayName("round started")
    void roundStarted() {
        assertEquals("aapje", round.getWordToGuess());
    }

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void guessWord() {
        assertEquals(List.of(CORRECT, WRONG, PRESENT, WRONG, WRONG), round.guess("arend").getMarks());
        System.out.println(round.getLastHint().getHintString());
        assertEquals(List.of(CORRECT, PRESENT, CORRECT, PRESENT, WRONG), round.guess("appel").getMarks());
        System.out.println(round.getLastHint().getHintString());
        assertEquals(List.of(ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL), round.guess("beren").getMarks());
        System.out.println(round.getLastHint().getHintString());
        assertEquals(List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT), round.guess("aapje").getMarks());
        System.out.println(round.getLastHint().getHintString());
        assertEquals(4, round.getTried());
        assertEquals("aapje", round.getLastHint().getHintString());
    }

    @Test
    @DisplayName("guess is some illegal value")
    void weirdGuess() {
        List fullyIllegal = List.of(ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL);
        assertEquals(fullyIllegal, round.guess(null).getMarks());
        assertEquals(fullyIllegal, round.guess("").getMarks());
        assertEquals(fullyIllegal, round.guess("da").getMarks());

    }

}