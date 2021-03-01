package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));

        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));

        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is invalid if any letter is invalid")
    void wordIsNotInvalid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.INVALID, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));

        assertFalse(feedback.isWordValid());
    }

    @Test
    @DisplayName("word is valid if no letters are invalid")
    void wordIsValid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));

        assertTrue(feedback.isWordValid());
    }

    @Test
    @DisplayName("feedback is different if values are different")
    void feedbackIsSame() {
        Feedback feedbackA = new Feedback("en", List.of(Mark.CORRECT, Mark.CORRECT));
        Feedback feedbackB = new Feedback("en", List.of(Mark.CORRECT, Mark.CORRECT));

        assertEquals(feedbackA, feedbackB);
    }
    @Test
    @DisplayName("feedback is different if values are different")
    void feedbackIsDifferent() {
        Feedback feedbackA = new Feedback("en", List.of(Mark.CORRECT, Mark.CORRECT));
        Feedback feedbackB = new Feedback("en", List.of(Mark.CORRECT, Mark.ABSENT));

        assertNotEquals(feedbackA, feedbackB);
    }

    @Test
    @DisplayName("hashcode is generated based on values")
    void hashcodeGeneration() {
        Feedback feedbackA = new Feedback("en", List.of(Mark.CORRECT, Mark.CORRECT));
        Feedback feedbackB = new Feedback("en", List.of(Mark.CORRECT, Mark.CORRECT));

        assertEquals(feedbackA.hashCode(), feedbackB.hashCode());
    }

    @Test
    @DisplayName("Feedback throws error if mark length is not equal to word length")
    void markSizeNotEqualWordLength() {
        assertThrows(
                InvalidFeedbackException.class,
                () -> new Feedback("woord", List.of(Mark.CORRECT))
        );
    }

    @Test
    @DisplayName("Feedback doesn't throw error if mark length is equal to word length")
    void markSizeEqualWordLength() {
        assertDoesNotThrow(
                () -> new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT))
        );
    }
}