package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
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
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.isWordGuessed());
    }


    @Test
    void guessIsValid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.isGuessValid());
    }

    @Test
    void guessIsNotValid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.INVALID, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.isGuessValid());
    }

    @Test
    void guessLengthDifferentToWordLength() {
        assertThrows(
                InvalidFeedbackException.class,
                () -> new Feedback("woord", List.of(Mark.CORRECT))
        );
    }




    @Test
    void wordViaStaticFactoryIsGuessed() {
        Feedback feedback = Feedback.forCorrect("woord");
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    void wordViaStaticFactoryIsNotGuessed() {
        Feedback feedback = Feedback.forInvalid("woord");
        assertFalse(feedback.isWordGuessed());
    }


    @Test
    void guessViaStaticFactoryIsValid() {
        Feedback feedback = Feedback.forCorrect("woord");
        assertTrue(feedback.isGuessValid());
    }

    @Test
    void guessViaStaticFactoryIsNotValid() {
        Feedback feedback = Feedback.forInvalid("woord");
        assertFalse(feedback.isGuessValid());
    }


}