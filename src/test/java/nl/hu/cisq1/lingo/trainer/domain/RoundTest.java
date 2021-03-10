package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.AttemptLimitReachedException;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;


class RoundTest {

    private Round round;

    @BeforeEach
    @DisplayName("init")
    void init() {
        Word wordToGuess = new Word("BAARD");
        round = new Round(wordToGuess);
    }

    @Test
    @DisplayName("provide the start hint")
    void generateFirstHint() {
        // Arrange

        // Act
        String hint = round.giveHint();

        // Assert
        assertEquals("B....", hint);
    }

    @Test
    @DisplayName("provide the hint based on previous feedback")
    void generateHintBasedOnFeedback() {
        // Arrange
        round.guess("BAKEN");

        // Act
        String hint = round.giveHint();

        // Assert
        assertEquals("BA...", hint);
    }

    @Test
    @DisplayName("attempt limit is reached when 5 or more attempts have been done")
    void attemptLimitReached() {
        // Arrange

        // Act
        round.guess("BAKEN");
        round.guess("BAKEN");
        round.guess("BAKEN");
        round.guess("BAKEN");
        round.guess("BAKEN");

        // Assert
        assertTrue(round.attemptLimitReached());

    }

    @Test
    @DisplayName("attempt limit is not reached when there are less then 5 attempts")
    void attemptLimitNotReached() {
        // Arrange

        // Act
        round.guess("BAKEN");
        round.guess("BAKEN");
        round.guess("BAKEN");
        round.guess("BAKEN");

        // Assert
        assertFalse(round.attemptLimitReached());

    }

    @Test
    @DisplayName("guess can not be processed if attempt limit is reached")
    void guessLimitThrowsError() {
        // Arrange

        // Act
        round.guess("BAKEN");
        round.guess("BAKEN");
        round.guess("BAKEN");
        round.guess("BAKEN");
        round.guess("BAKEN");

        // Assert
        assertThrows(AttemptLimitReachedException.class, () -> {
            round.guess("BAKEN");
        });
    }


    @Test
    @DisplayName("feedback is being added to the feedbackHistory")
    void feedbackSaved() {
        // Arrange

        // Act
        round.guess("BAKEN");
        round.guess("BAKEN");

        // Assert
        assertEquals(2,round.getFeedbackHistory().size());
    }


    @Test
    @DisplayName("attempts are counted after every guess")
    void attemptsAreCounted() {
        // Arrange

        // Act
        round.guess("BAKEN");
        round.guess("BAKEN");

        // Assert
        assertEquals(2,round.getAttempts());
    }

    @Test
    @DisplayName("current word length is correctly displayed")
    void CurrentWordLength() {
        // Arrange

        // Act / Assert
        assertEquals(5,round.getCurrentWordLength());
    }

    @ParameterizedTest
    @MethodSource("provideFeedbackExamples")
    @DisplayName("provide the correct Feedback")
    void generateFeedback(String attempt, Feedback feedback) {
        // Arrange

        // Act
        round.guess(attempt);

        // Assert
        assertEquals(feedback,round.getFeedbackHistory().get(0));

    }

    static Stream<Arguments> provideFeedbackExamples() {
        // Input
        String attempt1 = "BERGEN";
        String attempt2 = "BONJE";
        String attempt3 = "BARST";
        String attempt4 = "DRAAD";
        String attempt5 = "BAARD";

        List<Mark> marks1 = List.of(INVALID, INVALID, INVALID, INVALID, INVALID, INVALID);
        List<Mark> marks2 = List.of(CORRECT, ABSENT, ABSENT, ABSENT, ABSENT);
        List<Mark> marks3 = List.of(CORRECT, CORRECT, PRESENT, ABSENT, ABSENT);
        List<Mark> marks4 = List.of(ABSENT, PRESENT, CORRECT, PRESENT, CORRECT);
        List<Mark> marks5 = List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT);

        // Output
        Feedback feedback1 = new Feedback(attempt1, marks1);
        Feedback feedback2 = new Feedback(attempt2, marks2);
        Feedback feedback3 = new Feedback(attempt3, marks3);
        Feedback feedback4 = new Feedback(attempt4, marks4);
        Feedback feedback5 = new Feedback(attempt5, marks5);

        // Do i need to compare the list or just the feedback object?
//        List<Feedback> feedbackHistory1 = List.of(feedback1);
//        List<Feedback> feedbackHistory2 = List.of(feedback1, feedback2);
//        List<Feedback> feedbackHistory3 = List.of(feedback1, feedback2, feedback3);
//        List<Feedback> feedbackHistory4 = List.of(feedback1, feedback2, feedback3, feedback4);
//        List<Feedback> feedbackHistory5 = List.of(feedback1, feedback2, feedback3, feedback4, feedback5);

        return Stream.of(
                Arguments.of(attempt1, feedback1),
                Arguments.of(attempt2, feedback2),
                Arguments.of(attempt3, feedback3),
                Arguments.of(attempt4, feedback4),
                Arguments.of(attempt5, feedback5)
        );
    }

}