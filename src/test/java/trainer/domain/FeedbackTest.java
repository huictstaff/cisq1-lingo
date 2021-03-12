package trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    @Test
    @DisplayName("Word is guessed if all letters are correct.")
    void wordIsGuessed(){
        Feedback feedback = new Feedback(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "woord");
        assertTrue(feedback.isWordGuessed());
    }
    @Test
    @DisplayName("Word is not guessed if one or more letters are not correct.")
    void wordIsNotGuessed(){
        Feedback feedback = new Feedback(List.of(Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "woord");
        assertFalse(feedback.isWordGuessed());
    }
    @Test
    @DisplayName("Guess is invalid if one or more characters are invalid.")
    void guessIsInvalid(){
        Feedback feedback = new Feedback(List.of(Mark.INVALID, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "woord");
        assertTrue(feedback.isGuessInvalid());
    }
    @Test
    @DisplayName("Guess is valid if none of the characters are invalid.")
    void guessIsNotInvalid(){
        Feedback feedback = new Feedback(List.of(Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), "woord");
        assertFalse(feedback.isGuessInvalid());
    }
}