package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Feedback;
import nl.hu.cisq1.lingo.words.domain.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {



    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed()
    {
        // Given - Pre-condition

        // When - Action
        var feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));

        // Then - Post-condition
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if one of the letters in the word are wrong.")
    void wordIsNotGuessed()
    {
        // Given - Pre-condition

        // When - Action
        var feedback = new Feedback("woord", List.of(Mark.INVALID, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));

        // Then - Post-condition
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("guess is valid")
    void guessIsValid()
    {
        // Given - Pre-condition

        // When - Action
        var feedback = new Feedback("woord", List.of(Mark.INVALID, Mark.ABSENT, Mark.PRESENT, Mark.CORRECT, Mark.CORRECT));

        // Then - Post-condition
        assertTrue(feedback.guessIsValid());
    }

    @Test
    @DisplayName("guess is not valid")
    void guessIsNotValid()
    {
        // Given - Pre-condition

        // When - Action
        var feedback = new Feedback("wood", List.of(Mark.INVALID, Mark.ABSENT, Mark.PRESENT, Mark.CORRECT, Mark.CORRECT));

        // Then - Post-condition
        assertFalse(feedback.guessIsValid());
    }

    @Test
    @DisplayName("Test feedback toString")
    void testToString()
    {
        // Given - Pre-condition

        // When - Action
        var feedback1 = new Feedback("wood", List.of(Mark.INVALID, Mark.ABSENT, Mark.PRESENT, Mark.CORRECT, Mark.CORRECT));
        var feedback2 = new Feedback("wood", List.of(Mark.INVALID, Mark.ABSENT, Mark.PRESENT, Mark.CORRECT, Mark.CORRECT));

        // Then - Post-condition
        assertTrue(feedback1.toString().equals(feedback2.toString()));
    }

    @Test
    @DisplayName("Test feedback equals")
    void testFeedbackEquals()
    {
        // Given - Pre-condition

        // When - Action
        var feedback1 = new Feedback("wood", List.of(Mark.INVALID, Mark.ABSENT, Mark.PRESENT, Mark.CORRECT, Mark.CORRECT));
        var feedback2 = new Feedback("wood", List.of(Mark.INVALID, Mark.ABSENT, Mark.PRESENT, Mark.CORRECT, Mark.CORRECT));

        // Then - Post-condition
        assertTrue(feedback1.equals(feedback2));
    }
}