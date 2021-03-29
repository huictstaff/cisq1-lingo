package nl.hu.cisq1.lingo.trainer.domain;

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
        System.out.println("hinttestcase " + feedback.prepareFeedback("droplul", "deorlui"));
        return Stream.of(
                Arguments.of(
                        List.of(new Guess("deorlui")),
                        List.of(new Feedback(marks, "deorlui")),
                        "droplul",
                        List.of('d', '-', 'o', '+', 'l', 'u', '-')),

                Arguments.of(
                        List.of(new Guess("deorlui"), new Guess("dropliu")),
                        List.of(new Feedback("deorlui"), new Feedback("dropliu")),
                        "droplul",
                        List.of('d', 'r', 'o', 'p', 'l', 'u', '-'))

        );
    }
}