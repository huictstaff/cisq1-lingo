package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class feedbackTest {
    @BeforeEach
    public void init() {
    }

    @Test
    @DisplayName("word is guessed if all letters are correct, This test should be able to determine if a word was guessed or not")
    public void wordIsGuessed(){
        Feedback test = new Feedback("woord", List.of(Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT));
        assertTrue(test.isWordGuessed());
    }

    @Test
    @DisplayName("word is guessed if all letters are correct, This test should be able to determine if a word was incorrect or not")
    public void wordIsNOTGuessed(){
        Feedback test = new Feedback("woord", List.of(Feedback.Mark.INCORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT, Feedback.Mark.CORRECT));
        assertFalse(test.isWordGuessed());
    }
}