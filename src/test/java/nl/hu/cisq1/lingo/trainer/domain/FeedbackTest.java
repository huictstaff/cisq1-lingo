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
                Arguments.of("w...", "word", "words", List.of(INVALID, INVALID, INVALID, INVALID, INVALID), "w..."),
                Arguments.of("w...", "word", "wars", List.of(CORRECT, ABSENT, CORRECT, ABSENT), "w.r."),
                Arguments.of("w.r.", "word", "wolf", List.of(CORRECT, CORRECT, ABSENT, ABSENT), "wor."),
                Arguments.of("wor.", "word", "worm", List.of(CORRECT, CORRECT, CORRECT, ABSENT), "wor."),
                Arguments.of("wor.", "word", "word", List.of(CORRECT, CORRECT, CORRECT, CORRECT), "word")
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
    @DisplayName("guess is invalid if any mark is invalid")
    void guessIsInvalid() {
        Feedback feedback = new Feedback("word", List.of(INVALID, INVALID, INVALID, INVALID));
        assertTrue(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("attempt must be saved after feedback creation")
    void attemptIsSaved() {
        Feedback feedback = new Feedback("word", List.of(CORRECT, CORRECT, CORRECT, CORRECT));
        assertEquals("word", feedback.getAttempt());
    }

    @Test
    @DisplayName("guess is not invalid if all marks are not invalid")
    void guessIsNotInvalid() {
        Feedback feedback = new Feedback("word", List.of(CORRECT, CORRECT, CORRECT, CORRECT));
        assertFalse(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("exception must be thrown if list of mark is not the same size of the attempt")
    void invalidFeedbackExceptionThrows() {
        assertThrows(InvalidFeedbackException.class, () -> new Feedback("word", List.of(Mark.CORRECT)));
    }

    @Test
    @DisplayName("exception must not be thrown if list of mark is the same size of the attempt")
    void invalidFeedbackExceptionDoesNotThrow() {
        assertDoesNotThrow(() -> new Feedback("word", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)));
    }

    @Test
    @DisplayName("exception must be thrown if previousHint or wordToGuess is not of the same size of the feedback")
    void invalidHintExceptionThrows() {
        Feedback feedback = new Feedback("word", List.of(CORRECT, CORRECT, CORRECT, CORRECT));
        assertThrows(InvalidHintException.class, () -> feedback.generateHint("wor", "wor"));
    }

    @Test
    @DisplayName("exception must not be thrown if previousHint and wordToGuess is of the same size of the feedback")
    void invalidHintExceptionDoesNotThrow() {
        Feedback feedback = new Feedback("word", List.of(CORRECT, CORRECT, CORRECT, CORRECT));
        assertDoesNotThrow(() -> feedback.generateHint("word", "word"));
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("an attempt must have a corresponding hint")
    void attemptHasHint(String previousHint, String wordToGuess, String attempt, List<Mark> marks, String result) {
        Feedback feedback = new Feedback(attempt, marks);
        feedback.generateHint(previousHint, wordToGuess);
        assertEquals(result, feedback.getHint());
    }
}