package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;

class FeedbackTest {
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
    void sizeExceptionWorks() {
        assertThrows(InvalidFeedbackException.class, () -> new Feedback("word", List.of(Mark.CORRECT)));
    }
}