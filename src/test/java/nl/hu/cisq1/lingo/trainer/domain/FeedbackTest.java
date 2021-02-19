package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidHintException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;

class FeedbackTest {
    private static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("....", "word", "wars", List.of(CORRECT, ABSENT, ABSENT, ABSENT), "w..."),
                Arguments.of("w...", "word", "wolf", List.of(CORRECT, CORRECT, ABSENT, ABSENT), "wo.."),
                Arguments.of("wo..", "word", "worm", List.of(CORRECT, CORRECT, CORRECT, ABSENT), "wor."),
                Arguments.of("wor.", "word", "hack", List.of(ABSENT, ABSENT, ABSENT, ABSENT), "wor.")
        );
    }

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("word", List.of(CORRECT, CORRECT, CORRECT, CORRECT));
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("word", List.of(ABSENT, CORRECT, CORRECT, CORRECT));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("exception must be thrown if list of mark is not the same size of the attempt")
    void invalidFeedbackExceptionWorks() {
        assertThrows(InvalidFeedbackException.class, () -> new Feedback("word", List.of(Mark.CORRECT)));
    }

    @Test
    @DisplayName("exception must be thrown if list of mark is not the same size of the attempt")
    void invalidHintExceptionWorks() {
        Feedback feedback = new Feedback("word", List.of(CORRECT, CORRECT, CORRECT, CORRECT));
        assertThrows(InvalidHintException.class, () -> feedback.giveHint("wor", "wor"));
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("an attempt must have a corresponding hint")
    void guessHasHint(String previousHint, String wordToGuess, String attempt, List<Mark> marks, String result) {
        Feedback feedback = new Feedback(attempt, marks);
        assertEquals(result, feedback.giveHint(previousHint, wordToGuess));
    }
}