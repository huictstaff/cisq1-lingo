package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;

class FeedbackTest {

    //TODO: Make parameterised test.
    @Test
    @DisplayName("feedback create")
    void feedbackCreate() {
        Feedback feedback = new Feedback("hi", List.of(CORRECT, CORRECT));
        assertEquals("hi", feedback.getAttempt());
    }

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        String attempt = "PAARD";
        List<Mark> marks = List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT);
        Feedback feedback = new Feedback(attempt, marks);
        assertTrue(feedback.totalMark() == CORRECT);
    }

    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void wordIsNotGuessed() {
        String attempt = "PAREN";
        List<Mark> marks = List.of(CORRECT, CORRECT, PRESENT, WRONG, WRONG);
        Feedback feedback = new Feedback(attempt, marks);
        assertFalse(feedback.totalMark() == CORRECT);
    }

    @Test
    @DisplayName("word is not illegal if the marks are illegal")
    void wordIsInvalid() {
        String attempt = "AEJRE";
        List<Mark> marks = List.of(ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL);
        Feedback feedback = new Feedback(attempt, marks);
        assertTrue(feedback.totalMark() == ILLEGAL);
    }

    @Test
    @DisplayName("feedback equals other feedback if they have the same properties.")
    void feedbackEquals() {
        Feedback f1 = new Feedback("woord", List.of(WRONG, WRONG, CORRECT));
        Feedback f2 = new Feedback("woord", List.of(WRONG, WRONG, CORRECT));
        Feedback f3 = new Feedback("woord", List.of(CORRECT, WRONG, CORRECT));

        assertTrue(f1.equals(f2));
        assertFalse(f1.equals(f3));
    }

    @Test
    @DisplayName("toString test")
    void toStringCheck() {
        Feedback feedback = new Feedback("arm", List.of(WRONG, WRONG, WRONG));
        assertEquals("Feedback{attempt='arm', marks=[WRONG, WRONG, WRONG]}", feedback.toString());
    }

}