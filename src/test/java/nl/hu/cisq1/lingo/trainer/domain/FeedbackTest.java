package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeedbackTest {
    Feedback feedback;

    @BeforeEach
    void setUp() {
        this.feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
    }

    @ParameterizedTest
    @MethodSource(value = "validWordInputs")
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed(String guess, List<Mark> marks, boolean shouldBeGuessed) {
        Feedback feedback = new Feedback(guess, marks);
        assertEquals(feedback.wordIsGuessed(), shouldBeGuessed);
    }

    @ParameterizedTest
    @MethodSource(value = "isValidGuessTestInputs")
    @DisplayName("guess is valid if the number of letters is the same")
    void guessIsValid(String guess, List<Mark> marks, boolean expectedValid) {
        Feedback feedback = new Feedback(guess, marks);
        assertEquals(feedback.guessIsValid(), expectedValid);
    }

    @ParameterizedTest
    @MethodSource(value = "validWordInputs")
    @DisplayName("hint has same length as guess")
    void hintLengthIsCorrect(String guess, List<Mark> marks, boolean guessed) {
        Feedback feedback = new Feedback(guess, marks);
        assertEquals(feedback.giveHint().size(), guess.length());
    }

    static Stream<Arguments> validWordInputs() {
        return Stream.of(Arguments.of("worod", List.of(Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.PRESENT, Mark.CORRECT), false),
                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true));
    }

    static Stream<Arguments> isValidGuessTestInputs() {
        return Stream.of(Arguments.of("kort", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), false),
                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true));
    }

    @Test
    void testToString() {
        assertEquals(this.feedback.toString(), "Feedback(guess=woord, marks=[CORRECT, CORRECT, CORRECT, CORRECT, CORRECT])");
    }

    @Test
    void testEquals() {
        assertEquals(this.feedback, this.feedback);
    }

    @Test
    void canEqual() {
        assertEquals(this.feedback, new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)));
    }

    @Test
    void testHashCode() {
    }
}