package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.domain.exception.InvalidFeedbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordisGuessed() {
        Feedback feedback = new Feedback("woord", Arrays.asList(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));

        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if one or more letters are incorrect")
    void wordisNotGuessed() {
        Feedback feedback = new Feedback("woord", Arrays.asList(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.PRESENT));

        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Guess is invalid")
    void guessIsInvalid() {
        Feedback feedback = new Feedback("hetzelfde", Arrays.asList(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID));

        assertTrue(feedback.guessIsInvalid());
    }

    @Test
    @DisplayName("Guess is not invalid")
    void guessIsNotInvalid() {
        Feedback feedback = new Feedback("woord", Arrays.asList(Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.PRESENT));

        assertFalse(feedback.guessIsInvalid());
    }

    @Test
    @DisplayName("Exception: The amount of marks is not the same as the length of the word!")
    void attemptNotSameAsMarks() {
        assertThrows(InvalidFeedbackException.class, ()-> new Feedback("woord", Arrays.asList(Mark.INVALID, Mark.INVALID, Mark.INVALID)));
    }

    @ParameterizedTest(name = "Test #{index} | {0} | {1} | {2} |" )
    @DisplayName("A hint by a guessed word")
    @MethodSource("provideHintExamples")
    void giveHint(String attempt, List<Mark> marks, List<Character> hint) {
        Feedback feedback = new Feedback(attempt, marks);

        assertEquals(feedback.giveHint(), hint);
    }

    private static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("woord", Arrays.asList(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), Arrays.asList('w', 'o', 'o', 'r', 'd')),
                Arguments.of("woord", Arrays.asList(Mark.PRESENT, Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.PRESENT), Arrays.asList('+', '.', 'o', '.', '+')),
                Arguments.of("woord", Arrays.asList(Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID, Mark.INVALID), Arrays.asList('-', '-', '-', '-', '-'))
        );
    }
}