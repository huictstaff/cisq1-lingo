package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback f = new Feedback("woord", result);

        assertTrue(f.isWordGuessed());
    }
    @Test
    @DisplayName("Some letters are incorrect")
    void wordIsNotGuessed(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.INVALID, Mark.CORRECT);
        Feedback f = new Feedback("woord", result);

        assertFalse(f.isWordGuessed());
    }

    @Test
    @DisplayName("Guess is valid")
    void guessIsValid(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback f = new Feedback("woord", result);

        assertTrue(f.isGuessValid());
    }

    @Test
    @DisplayName("Guess invalid")
    void guessIsInvalid(){
        List<Mark> result = List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        Feedback f = new Feedback("woo", result);

        assertFalse(f.isGuessValid());
    }

}