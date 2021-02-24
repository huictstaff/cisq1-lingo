package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.IllegalRoundArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    @Test
    public void instantiationWithAttempsSurpassingTriesLimit() {
        assertThrows(IllegalRoundArgumentException.class,
                () -> new Round("woord", List.of("woooa", "wooob", "woooc", "woood", "woooe", "wooof")));
    }

    @Test
    public void attempsSurpassTriesLimit() {
        Round round = new Round("woord", List.of("woooa", "wooob", "woooc", "woood", "woooe"))
        assertThrows(IllegalRoundArgumentException.class,
                () -> round.addAttempt("woof"));
    }


    @ParameterizedTest
    @MethodSource("providedLastAttemptsExamples")
    public void getFeedbackOnLastAttempt(Feedback expectedFeedback, String wordToGuess, List<String> attemptsList) {
        Round round = new Round(wordToGuess, attemptsList);

        assertEquals(expectedFeedback, round.getFeedbackOnLastAttempt());
    }

    @Test
    public void getFeedbackOnNoAttempt() {

        Round round = new Round("woord", Collections.<String>emptyList());
        assertThrows(IllegalRoundArgumentException.class,
                () -> round.getFeedbackOnLastAttempt());
    }

}