package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FeedbackTest {
    @ParameterizedTest
    @MethodSource(value = "validWordInputs")
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed(String guess, List<Mark> marks, boolean shouldBeGuessed) {
        Feedback feedback = new Feedback(guess, marks);
        assertEquals(feedback.wordIsGuessed(), shouldBeGuessed);
    }

    @ParameterizedTest
    @MethodSource(value = "invalidWordInputs")
    @DisplayName("word should be valid to be guessed")
    void wordIsValidInGuess(String guess, List<Mark> marks, boolean expectedValid) {
        Feedback feedback = new Feedback(guess, marks);
        assertEquals(feedback.wordIsGuessed(), expectedValid);
    }

    @ParameterizedTest
    @MethodSource(value = "invalidWordInputs")
    @DisplayName("guess is valid if the number of letters is the same")
    void guessIsValid(String guess, List<Mark> marks, boolean expectedValid) {
        Feedback feedback = new Feedback(guess, marks);
        assertEquals(feedback.guessIsValid(), expectedValid);
    }

    @Test
    @DisplayName("correct letters should be visible, present letters should be *'s and absent letters should be .'s in hints")
    void hintLetters() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.PRESENT, Mark.ABSENT, Mark.CORRECT, Mark.PRESENT));
        Hint previousHint = Hint.initialHint(Feedback.initialFeedback("woord").getMarks(), 'w', 5);
        assertEquals(feedback.giveHint(previousHint).getHint(), List.of('w', '.', '.', 'r', '.'));
    }

    @Test
    @DisplayName("guess should be valid in order to get hint")
    void validGuessForHint() {
        Feedback feedback = new Feedback("word", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        Hint previousHint = Hint.initialHint(Feedback.initialFeedback("word").getMarks(), 'w', 4);
        assertEquals(0, feedback.giveHint(previousHint).getHint().size());
    }

    static Stream<Arguments> validWordInputs() {
        return Stream.of(Arguments.of("worod", List.of(Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.PRESENT, Mark.CORRECT), false),
                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true));
    }

    static Stream<Arguments> invalidWordInputs() {
        return Stream.of(Arguments.of("kort", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), false),
                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true),
                Arguments.of("wooooord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), false));
    }
}