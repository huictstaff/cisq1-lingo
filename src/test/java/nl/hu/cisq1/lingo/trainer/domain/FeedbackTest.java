package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeedbackTest {
    @Test
    @DisplayName("Error is thrown when the amount of marks and letters are not equal")
    void throwsInvalidFeedbackException() {
        assertThrows(
                InvalidFeedbackException.class,
                () -> new Feedback("woord", List.of(Mark.CORRECT))
        );
    }
    @Test
    @DisplayName("Attempt is marked if it returns a list of marks.")
    void markAttempt() {
        String word = "appel";
        List<Mark> marks = Feedback.markAttempt("al.ql", word);
        assertEquals(List.of(Mark.CORRECT, Mark.PRESENT, Mark.INVALID, Mark.ABSENT, Mark.CORRECT), marks);
    }

    @Test
    @DisplayName("Marks have been added if the correct marks from the old marks were added to te new marks.")
    void addMarks() {
        List<Mark> oldMarks = List.of(Mark.CORRECT, Mark.PRESENT, Mark.INVALID, Mark.ABSENT, Mark.CORRECT);
        List<Mark> currentMarks = List.of(Mark.INVALID, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT);
        //The parameters of feedback are irrelevant here.
        Feedback feedback = new Feedback("appel", List.of(Mark.CORRECT, Mark.PRESENT, Mark.INVALID, Mark.ABSENT, Mark.CORRECT));
        List<Mark> newMarks = feedback.addMarks(oldMarks, currentMarks);
        assertEquals(List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), newMarks);
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Hint is correct if the given marks correspond to the correct symbol")
    void giveHint(String word, List<Mark> marks) {
        Feedback feedback = new Feedback(word, List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
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
                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)),
                Arguments.of("klein", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT))
        );
    }
    @Test
    void setAttempt() {
        Feedback feedback = new Feedback();
        feedback.setAttempt("woord");
        assertEquals(feedback.getAttempt(), "woord");
    }
    @Test
    void setMarks() {
        Feedback feedback = new Feedback();
        feedback.setMarks(List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
        assertEquals(feedback.getMarks(), List.of(Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT,Mark.CORRECT));
    }
    @Test
    void setRound() {
        Feedback feedback = new Feedback();
        feedback.setRound(new Round());
        assertEquals(feedback.getRound().toString(), new Round().toString());
    }
}