package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void isWordToGuess ( ) {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }
    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void isWordNotToGuess ( ) {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertFalse(feedback.isWordGuessed());
    }
    @Test
    @DisplayName("word is valid when the word correct and has a same amount letters")
    void isWordValid ( ) {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT,Mark.PRESENT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertTrue(feedback.isWordVlid());
    }
    @Test
    @DisplayName("word is not valid when the word  not correct or has a few or to many amount letters")
    void isWordNotValid ( ) {
        Feedback feedback = new Feedback("woord", List.of(Mark.INVALID,Mark.INVALID,Mark.INVALID,Mark.INVALID,Mark.INVALID));
        assertFalse(feedback.isWordVlid());
    }



}