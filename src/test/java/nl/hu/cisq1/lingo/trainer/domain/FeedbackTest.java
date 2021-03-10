package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FeedbackTest {

    @Test
    @DisplayName("word is guessed when all marks are correct")
    void isWordGuessed() {
        // Arrange
        List<Mark> marks = List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT);
        String attempt = "PAARD";
        Feedback feedback = new Feedback(attempt, marks);

        // Act / Assert
        assertTrue(feedback.isWordGuessed());

    }

    @Test
    @DisplayName("word is not guessed when all marks are not correct")
    void isWordNotGuessed() {
        // Arrange
        List<Mark> marks = List.of(CORRECT, CORRECT, CORRECT, CORRECT, ABSENT);
        String attempt = "PAARS";
        Feedback feedback = new Feedback(attempt, marks);

        // Act / Assert
        assertFalse(feedback.isWordGuessed());

    }

    @Test
    @DisplayName("attempt is valid when none of the marks are invalid")
    void isAttemptValid() {
        // Arrange
        List<Mark> marks = List.of(CORRECT, CORRECT, CORRECT, CORRECT, ABSENT);
        String attempt = "PAARS";
        Feedback feedback = new Feedback(attempt, marks);

        // Act / Assert
        assertTrue(feedback.isAttemptValid());
    }

    @Test
    @DisplayName("attempt is invalid when one/all marks are invalid")
    void isAttemptInvalid() {
        // Arrange
        List<Mark> marks = List.of(INVALID, INVALID, INVALID, INVALID, INVALID, INVALID, INVALID);
        String attempt = "PAARDEN";
        Feedback feedback = new Feedback(attempt, marks);

        // Act / Assert
        assertFalse(feedback.isAttemptValid());
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("provide a correct hint")
    void correctHint(String previousHint, Feedback feedback, String nextHint) {
        // Arrange / Act / Assert
        assertEquals(nextHint, feedback.giveHint(previousHint));
    }

    static Stream<Arguments> provideHintExamples() {
        // Input
        String previousHint = "B....";

        String attempt1 = "BERGEN";
        String attempt2 = "DRAAD";
        String attempt3 = "BAARD";

        List<Mark> marks1 = List.of(INVALID, INVALID, INVALID, INVALID, INVALID, INVALID);
        List<Mark> marks2 = List.of(ABSENT, PRESENT, CORRECT, PRESENT, CORRECT);
        List<Mark> marks3 = List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT);

        Feedback feedback1 = new Feedback(attempt1, marks1);
        Feedback feedback2 = new Feedback(attempt2, marks2);
        Feedback feedback3 = new Feedback(attempt3, marks3);

        // Output
        String newHint1 = "B....";
        String newHint2 = "B.A.D";
        String newHint3 = "BAARD";


        return Stream.of(
                Arguments.of(previousHint, feedback1, newHint1),
                Arguments.of(newHint1, feedback2, newHint2),
                Arguments.of(newHint2, feedback3, newHint3)
        );
    }

}