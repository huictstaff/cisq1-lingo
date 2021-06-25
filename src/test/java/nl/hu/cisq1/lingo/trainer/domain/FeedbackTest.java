package nl.hu.cisq1.lingo.trainer.domain;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeedbackTest {
    //constructor tests
    @Test
    @DisplayName("Error is thrown when the amount of marks and letters are not equal")
    void throwsInvalidFeedbackException() {
        assertThrows(
                InvalidFeedbackException.class,
                () -> new Feedback("woord", List.of(Mark.CORRECT))
        );
    }
    @Test
    @DisplayName("constructor works when parameters are saved properly.")
    void Feedback(){
        Feedback feedback = new Feedback("woord",
                List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertEquals(List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT),feedback.getMarks());
        assertEquals("woord", feedback.getAttempt());
        Feedback feedback1 = new Feedback();
        assertNull(feedback1.getAttempt());
        assertNull(feedback1.getMarks());

    }
    //Mark attempt tests
    @Test
    @DisplayName("Marks are properly made when letters are given the proper mark.")
    void testMarks(){
        assertEquals(Collections.emptyList(),Feedback.markAttempt("", "woord"));
        assertEquals(List.of(Mark.INVALID),
                Feedback.markAttempt("_", "i"));
        assertEquals(List.of(Mark.CORRECT),
                Feedback.markAttempt("i", "i"));
        assertEquals(List.of(Mark.ABSENT),
                Feedback.markAttempt("o", "i"));
        assertEquals(List.of(Mark.PRESENT, Mark.PRESENT),
                Feedback.markAttempt("oa", "ao"));

    }


    //Add marks tests
    @Test
    @DisplayName("Marks have been added if the correct marks from the old marks were added to te new marks.")
    void addMarks() {
        Feedback feedback = new Feedback();
        assertEquals(List.of(Mark.CORRECT),
                feedback.addMarks(List.of(Mark.CORRECT), List.of(Mark.ABSENT)));
        assertEquals(List.of(Mark.CORRECT),
                feedback.addMarks(List.of(Mark.CORRECT), List.of(Mark.CORRECT)));
        assertEquals(List.of(Mark.CORRECT),
                feedback.addMarks(List.of(Mark.CORRECT), List.of(Mark.INVALID)));
        assertEquals(List.of(Mark.CORRECT),
                feedback.addMarks(List.of(Mark.CORRECT), List.of(Mark.PRESENT)));

        assertEquals(List.of(Mark.INVALID),
                feedback.addMarks(List.of(Mark.INVALID), List.of(Mark.INVALID)));
        assertEquals(List.of(Mark.ABSENT),
                feedback.addMarks(List.of(Mark.INVALID), List.of(Mark.ABSENT)));
        assertEquals(List.of(Mark.PRESENT),
                feedback.addMarks(List.of(Mark.INVALID), List.of(Mark.PRESENT)));
        assertEquals(List.of(Mark.CORRECT),
                feedback.addMarks(List.of(Mark.INVALID), List.of(Mark.CORRECT)));

        assertEquals(List.of(Mark.INVALID),
                feedback.addMarks(List.of(Mark.PRESENT), List.of(Mark.INVALID)));
        assertEquals(List.of(Mark.ABSENT),
                feedback.addMarks(List.of(Mark.PRESENT), List.of(Mark.ABSENT)));
        assertEquals(List.of(Mark.PRESENT),
                feedback.addMarks(List.of(Mark.PRESENT), List.of(Mark.PRESENT)));
        assertEquals(List.of(Mark.CORRECT),
                feedback.addMarks(List.of(Mark.PRESENT), List.of(Mark.CORRECT)));

        assertEquals(List.of(Mark.INVALID),
                feedback.addMarks(List.of(Mark.ABSENT), List.of(Mark.INVALID)));
        assertEquals(List.of(Mark.ABSENT),
                feedback.addMarks(List.of(Mark.ABSENT), List.of(Mark.ABSENT)));
        assertEquals(List.of(Mark.PRESENT),
                feedback.addMarks(List.of(Mark.ABSENT), List.of(Mark.PRESENT)));
        assertEquals(List.of(Mark.CORRECT),
                feedback.addMarks(List.of(Mark.ABSENT), List.of(Mark.CORRECT)));
    }

    //giveHint tests
    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Hint is correct if the given marks correspond to the correct symbol")
    void giveHint(String word, String attempt) {
        Feedback feedback = new Feedback("_____", List.of(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID));
        List<Mark> marks = Feedback.markAttempt(attempt, word);
        Hint hint = feedback.giveHint(word, marks);
        for (int i = 0; i < marks.size(); i++) {
            switch (marks.get(i)) {
                case PRESENT -> assertEquals(hint.getHintStrings().get(i), "*");
                case ABSENT -> assertEquals(hint.getHintStrings().get(i), "_");
                case CORRECT -> assertEquals(hint.getHintStrings().get(i), "" + word.charAt(i));
                case INVALID -> assertEquals(hint.getHintStrings().get(i), "#");
                default -> System.err.println("Error Invalid mark.");
            }
        }
    }

    //Guess word tests

    @Test
    void testGiveHint(){
        Feedback feedback = new Feedback("w", List.of(Mark.INVALID));
        assertEquals(new Hint(List.of("w"), List.of(Mark.CORRECT)).toString(),feedback.giveHint("w", List.of(Mark.CORRECT)).toString());
        Feedback feedback2 = new Feedback("w", List.of(Mark.INVALID));
        assertEquals(new Hint(List.of("_"), List.of(Mark.ABSENT)).toString(),feedback2.giveHint("o", List.of(Mark.ABSENT)).toString());
        Feedback feedback3 = new Feedback("w", List.of(Mark.INVALID));
        assertEquals(new Hint(List.of("#"), List.of(Mark.INVALID)).toString(),feedback3.giveHint("_", List.of(Mark.INVALID)).toString());
        Feedback feedback4 = new Feedback("ao", List.of(Mark.INVALID, Mark.INVALID));
        assertEquals(new Hint(List.of("*", "*"), List.of(Mark.PRESENT, Mark.PRESENT)).toString(),feedback4.giveHint("oa", List.of(Mark.PRESENT, Mark.PRESENT)).toString());
    }
    @Test
    @DisplayName("Word is guessed if all letters are correct.")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.getMarks().stream().allMatch(mark -> mark == Mark.CORRECT));
    }

    @Test
    @DisplayName("Word is not guessed if one or more letters are not correct.")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.getMarks().stream().allMatch(mark -> mark == Mark.CORRECT));
    }

    @Test
    @DisplayName("Guess is invalid if one or more characters are invalid.")
    void guessIsInvalid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.INVALID, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.getMarks().stream().anyMatch(mark -> mark == Mark.INVALID));
    }

    @Test
    @DisplayName("Guess is valid if none of the characters are invalid.")
    void guessIsNotInvalid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.getMarks().stream().anyMatch(mark -> mark == Mark.INVALID));
    }

    public static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("woord", "wur_d"),
                Arguments.of("klein", "kul_n")
        );
    }

    @Test
    void testToString() {
        Feedback feedback = new Feedback();
        assertNotNull(feedback.toString());
    }
}