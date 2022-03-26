package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("boers", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.wordIsGuessed());
    }

    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("boers", List.of(Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.wordIsGuessed());
    }

    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void guessIsInvalid() {
        Feedback feedback = new Feedback("boers", List.of(Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.guessIsValid());
    }

    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void guessIsNotInvalid() {
        Feedback feedback = new Feedback("haring", List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID));
        assertFalse(feedback.guessIsValid());
    }

}