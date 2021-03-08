package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.INVALID;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.ABSENT;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.PRESENT;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.CORRECT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoundTest {
    private static Stream<Arguments> provideFeedbackExamples() {
        return Stream.of(
                Arguments.of("baard", "bergen",  List.of(INVALID, INVALID, INVALID, INVALID, INVALID, INVALID)),
                Arguments.of("baard", "bonje", List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT)),
                Arguments.of("baard", "barst", List.of(CORRECT, CORRECT, PRESENT, ABSENT, ABSENT)),
                Arguments.of("baard", "draad", List.of(ABSENT, PRESENT, CORRECT, PRESENT, CORRECT)),
                Arguments.of("baard", "baard", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT))
        );
    }

    @ParameterizedTest
    @MethodSource("provideFeedbackExamples")
    @DisplayName("attempt must have corresponding feedback")
    void attemptHasFeedback(String wordToGuess, String attempt, List<Mark> result) {
        Round round = new Round(wordToGuess);
        Feedback feedback = round.guessWord(attempt);
        assertEquals(result, feedback.getMarks());
    }

    @Test
    @DisplayName("attempts must be increased after a guess")
    void attemptIsSaved() {
        Round round = new Round("word");
        assertEquals(0, round.getAttempts());
        round.guessWord("wolf");
        assertEquals(1, round.getAttempts());
    }

    @Test
    @DisplayName("word to guess must be saved after round creation")
    void wordIsSaved() {
        Round round = new Round("word");
        assertEquals("word", round.getWordToGuess());
    }
}