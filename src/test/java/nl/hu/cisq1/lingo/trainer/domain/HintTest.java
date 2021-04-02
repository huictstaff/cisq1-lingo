package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidListSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HintTest {

    @Test
    @DisplayName("constuctor test")
    void constructorTest() {
        assertDoesNotThrow(() -> new Hint(List.of('c', '-', '+', '-', '+', 'i')));
    }

    @Test
    @DisplayName("Different guess and feedback size")
    void differentGuessAndFeedbackSize() {
        Hint hint = new Hint(List.of('h', 'i', 'n', 't'));
        assertThrows(InvalidListSizeException.class, () -> hint.giveHint(List.of(new Guess("guess"), new Guess("guess")), List.of(new Feedback("guess1")), "guess"));
    }

    @ParameterizedTest
    @DisplayName("giveHint will give correct hint with multiple guesses")
    @MethodSource("hintTestCases")
    void giveHint(List<Guess> guesses, List<Feedback> feedbackList, String word, List<Character> expected) {
        Hint hint = new Hint(List.of('-', '-', '-', '-', '-', '-', '-'));
        assertEquals(expected, hint.giveHint(guesses, feedbackList, word));
    }

    static Stream<Arguments> hintTestCases() {
        Feedback feedback = new Feedback("deorlui");
        List<Mark> marks = feedback.toMarkArray(feedback.prepareFeedback("droplul", "deorlui"));
        return Stream.of(
                Arguments.of(
                        List.of(new Guess("deorlui")),
                        List.of(new Feedback(marks, "deorlui")),
                        "droplul",
                        List.of('d', '-', 'o', '+', 'l', 'u', '-')),

                Arguments.of(
                        List.of(new Guess("deorlui"), new Guess("dropliu")),
                        List.of(new Feedback(new Feedback("droplul, deorlui").toMarkArray(feedback.prepareFeedback("droplul", "deorlui")), "deorlui"),
                                new Feedback(new Feedback("droplul, dropliu").toMarkArray(feedback.prepareFeedback("droplul", "dropliu")), "dropliu")),
                        "droplul",
                        List.of('d', 'r', 'o', 'p', 'l', 'u', '+')),

                Arguments.of(
                        List.of(new Guess("deorlui"), new Guess("dropliu"), new Guess("dropilu")),
                        List.of(new Feedback(new Feedback("droplul, deorlui").toMarkArray(feedback.prepareFeedback("droplul", "deorlui")), "deorlui"),
                                new Feedback(new Feedback("droplul, dropliu").toMarkArray(feedback.prepareFeedback("droplul", "dropliu")), "dropliu"),
                                new Feedback(new Feedback("droplul, dropilr").toMarkArray(feedback.prepareFeedback("droplul", "dropilr")), "dropilr")),
                        "droplul",
                        List.of('d', 'r', 'o', 'p', 'l', 'u', '-')),

                Arguments.of(
                        List.of(new Guess("deorlui"), new Guess("dropliu"), new Guess("dropilu")),
                        List.of(new Feedback(new Feedback("droplul, deorlui").toMarkArray(feedback.prepareFeedback("droplul", "deorlui")), "deorlui"),
                                new Feedback(new Feedback("droplul, dropliu").toMarkArray(feedback.prepareFeedback("droplul", "dropliu")), "dropliu"),
                                new Feedback(new Feedback("droplul, dropirl").toMarkArray(feedback.prepareFeedback("droplul", "dropirl")), "dropirl")),
                        "droplul",
                        List.of('d', 'r', 'o', 'p', 'l', 'u', 'l'))
        );
    }
}