package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        // given
        // when
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        // then
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void wordIsNotGuessed() {
        // given
        // when
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));
        // then
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is invalid if any letter is invalid")
    void wordIsNotInvalid() {
        // given
        // when
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.INVALID, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));
        // then
        assertFalse(feedback.isWordValid());
    }

    @Test
    @DisplayName("word is valid if no letters are invalid")
    void wordIsValid() {
        // given
        // when
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));
        // then
        assertTrue(feedback.isWordValid());
    }

    @Test
    @DisplayName("feedback is different if values are different")
    void feedbackIsSame() {
        Feedback feedbackA = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        Feedback feedbackB = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));

        assertEquals(feedbackA, feedbackB);
    }
    @Test
    @DisplayName("feedback is different if values are different")
    void feedbackIsDifferent() {
        Feedback feedbackA = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        Feedback feedbackB = new Feedback(List.of(Mark.CORRECT, Mark.ABSENT));

        assertNotEquals(feedbackA, feedbackB);
    }

    @Test
    @DisplayName("hashcode is generated based on values")
    void hashcodeGeneration() {
        Feedback feedbackA = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));
        Feedback feedbackB = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT));

        assertEquals(feedbackA.hashCode(), feedbackB.hashCode());
    }
}