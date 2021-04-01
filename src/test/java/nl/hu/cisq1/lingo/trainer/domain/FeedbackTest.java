package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
        private final Feedback emptyFeedback = new Feedback();

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
        assertEquals(feedback.guessIsValid(), expectedValid);
    }

    @Test
    @DisplayName("correct letters should be visible and absent letters should be .'s in hints")
    void hintLetters() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.PRESENT, Mark.ABSENT, Mark.CORRECT, Mark.PRESENT));
        Hint previousHint = Hint.initialHint(Feedback.initialFeedback("woord").getMarks(), 'w', 5);
        assertEquals(feedback.giveHint(previousHint).getHint(), List.of('w', '.', '.', 'r', '.'));
    }

    @Test
    void setId() {
        long id = 2L;
        emptyFeedback.setId(id);
        assertEquals(id, emptyFeedback.getId());
    }

    @Test
    void setGuess() {
        String guess = "worod";
        emptyFeedback.setGuess(guess);
        assertEquals(guess, emptyFeedback.getGuess());
    }

    @Test
    void setMarks() {
        List<Mark> marks = new ArrayList<>();
        emptyFeedback.setMarks(marks);
        assertEquals(marks, emptyFeedback.getMarks());
    }

    @Test
    void setRound() {
        Round round = new Round();
        emptyFeedback.setRound(round);
        assertEquals(round, emptyFeedback.getRound());
    }

    static Stream<Arguments> validWordInputs() {
        return Stream.of(Arguments.of("worod", List.of(Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.PRESENT, Mark.CORRECT), false),
                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true));
    }

    static Stream<Arguments> invalidWordInputs() {
        return Stream.of(Arguments.of("kort", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), false),
                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true),
                Arguments.of("wooooord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), false),
                Arguments.of(null, List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), false),
                Arguments.of("woord", null, false),
                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), false));
    }
}