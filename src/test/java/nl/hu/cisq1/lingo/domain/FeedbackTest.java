package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.domain.exception.InvalidFeedbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordisGuessed() {
        Feedback feedback = new Feedback("woord", Arrays.asList(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));

        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if one or more letters are incorrect")
    void wordisNotGuessed() {
        Feedback feedback = new Feedback("woord", Arrays.asList(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));

        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Guess is invalid")
    void guessIsInvalid() {
        Feedback feedback = new Feedback("hetzelfde", Arrays.asList(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID));

        assertTrue(feedback.guessIsInvalid());
    }

    @Test
    @DisplayName("Guess is not invalid")
    void guessIsNotInvalid() {
        Feedback feedback = new Feedback("woord", Arrays.asList(Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.PRESENT));

        assertFalse(feedback.guessIsInvalid());
    }

    @Test
    @DisplayName("Exception: The amount of marks is not the same as the length of the word!")
    void attemptNotSameAsMarks() {
        assertThrows(InvalidFeedbackException.class, ()-> new Feedback("woord", Arrays.asList(Mark.INVALID, Mark.INVALID, Mark.INVALID)));
    }
}