package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed(){
        Feedback feedback = new Feedback("woord", List.of(Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct));
        assertTrue(feedback.isWordGuessed());
    }
    @Test
    @DisplayName("word is not guessed if all latters are not correct")
    void wordIsNotGuessed(){
        Feedback feedback = new Feedback("woord", List.of(Mark.Absent,Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word guessed is valid")
    void guessIsValid(){
        Feedback feedback = new Feedback("woord", List.of(Mark.Absent,Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct));
        assertTrue(feedback.guessIsValid());
    }

    @Test
    @DisplayName("word guessed is not valid")
    void guessIsNotValid(){
        Feedback feedback = new Feedback("wos", List.of(Mark.Absent,Mark.Correct,Mark.Correct,Mark.Correct,Mark.Correct));
        assertFalse(feedback.guessIsValid());
    }


}