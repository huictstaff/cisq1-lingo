package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @DisplayName("word is guessed if all letters are correct")
    @MethodSource("guessesAndFeedback")
    void wordIsGuessed(String attempt, List<Mark> marks, Mark total) {
        Feedback feedback = new Feedback(attempt, marks);
        assertTrue(feedback.totalMark() == total);
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


    static Stream<Arguments> guessesAndFeedback() {
        return Stream.of(
                Arguments.of("paard", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT), CORRECT),
                Arguments.of("paren", List.of(CORRECT, CORRECT, PRESENT, WRONG, WRONG), WRONG),
                Arguments.of("aejre", List.of(ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL, ILLEGAL), ILLEGAL)
        );
    }

    @Test
    @DisplayName("toString test")
    void toStringCheck() {
        Feedback feedback = new Feedback("arm", List.of(WRONG, WRONG, WRONG));
        assertEquals("Feedback{attempt='arm', marks=[WRONG, WRONG, WRONG]}", feedback.toString());
    }

}