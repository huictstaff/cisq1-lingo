package nl.hu.cisq1.lingo.trainer.domain.exception;

import nl.hu.cisq1.lingo.trainer.domain.Round;
import nl.hu.cisq1.lingo.trainer.domain.State;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MaximumAttemptsReachedExceptionTest {
    @Test
    @DisplayName("exception should be thrown when guessing is tried after reaching 5 guesses")
    void exceptionTest() {
        Round round = new Round(new Word("woord"));
        round.setAttempts(5);
        assertThrows(MaximumAttemptsReachedException.class, () -> round.guess("woord"));
    }
}