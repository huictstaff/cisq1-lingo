package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.*;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("boers", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT));
        assertTrue(feedback.wordIsGuessed());
    }

    @Test
    @DisplayName("word is not guessed if not all letters are correct")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("boers", List.of(ABSENT, ABSENT, ABSENT, CORRECT, CORRECT));
        assertFalse(feedback.wordIsGuessed());
    }

    @Test
    @DisplayName("guess is valid if there are no invalid letters")
    void guessIsInvalid() {
        Feedback feedback = new Feedback("boers", List.of(ABSENT, ABSENT, ABSENT, CORRECT, CORRECT));
        assertTrue(feedback.guessIsValid());
    }

    @Test
    @DisplayName("guess is not valid if there are invalid letters")
    void guessIsNotInvalid() {
        Feedback feedback = new Feedback("haring", List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID));
        assertFalse(feedback.guessIsValid());
    }

    @ParameterizedTest
    @MethodSource("hintInputStream")
    @DisplayName("a guess should result in correct hint")
    void hintIsValid(String word, String attempt, List<Mark> marks, List<String> previousHint, List<String> expectedHint) {
        Feedback feedback = new Feedback(attempt, marks);

        assertEquals(expectedHint, feedback.giveHint(previousHint, word));
    }

    static Stream<Arguments> hintInputStream() {
        return Stream.of(
                Arguments.of("BOOST", "BOTER", List.of(CORRECT, CORRECT, PRESENT, ABSENT, ABSENT), List.of("B",".",".",".","."), List.of("B","O",".",".",".")),
                Arguments.of("BOOST", "SLOOT", List.of(PRESENT, ABSENT, CORRECT, PRESENT, CORRECT), List.of("B","O",".",".","."), List.of("B","O","O",".","T")),
                Arguments.of("KLOOT", "SLUIS", List.of(ABSENT, CORRECT, ABSENT, ABSENT, ABSENT), List.of("K",".",".",".","."), List.of("K","L",".",".",".")),
                Arguments.of("WATER", "WAGEN", List.of(CORRECT, CORRECT, ABSENT, CORRECT, ABSENT), List.of("W",".",".",".","."), List.of("W","A",".","E","."))
        );
    }
}