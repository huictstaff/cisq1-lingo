package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HintTest {
    @Test
    @DisplayName("Empty or invalid hints should not generate")
    void emptyHint() {
//        Given
        Hint hint = new Hint();
//        When
        int size = hint.getHint().size();
//        Then
        assertEquals(0, size);
    }

    @Test
    @DisplayName("hint constructor should generate hint successfully")
    void hintConstructor() {
//        Given
        String word = "woord";
        String guess = "worod";
        Feedback feedback = Feedback.initialFeedback(word);
        Hint initialHint = Hint.initialHint(feedback.getMarks(), 'w', 5);
        Validator validator = new Validator(guess, word);
        Feedback feedback1 = new Feedback(guess, validator.validate());
        Hint hint = new Hint(initialHint, feedback1);
//        When
        int size = hint.getHint().size();
//        Then
        assertEquals(guess.length(), size);
    }

    @Test
    @DisplayName("static function should generate a hint with one character")
    void initialHint() {
//        Given
        Feedback feedback = Feedback.initialFeedback("woord");
        Hint hint = Hint.initialHint(feedback.getMarks(), 'w', 5);
//        When
        List<Character> hints = hint.getHint();
//        Then
        assertEquals(hints, List.of('w', '.', '.', '.', '.'));
    }

    @ParameterizedTest
    @MethodSource("invalidInputs")
    @DisplayName("function should return whether or not the hint is invalid")
    void isInvalid(String guess, List<Mark> marks, List<Character> previousHint, boolean isInvalid) {
//        Given
        Hint hint = new Hint(guess, marks, previousHint);
//        When
        boolean result = hint.isInvalid();
//        Then
        assertEquals(isInvalid, result);
    }

    @ParameterizedTest
    @MethodSource("hintLetterInputs")
    @DisplayName("should check if character is a hint character or letter")
    void isHintCharacter(char character, boolean isNotHintChar) {
        assertEquals(isNotHintChar, Hint.isNotHintCharacter(character));
    }

    static Stream<Arguments> invalidInputs() {
        return Stream.of(
                Arguments.of(null, List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT),
                        List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true),

                Arguments.of("woord", null,
                        List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true),

                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT),
                        null, true),

                Arguments.of("word", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT),
                        List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true),

                Arguments.of("wooooord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT),
                        List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true),

                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT),
                        List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true),

                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT),
                        List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), true),

                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT),
                        List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), false)
        );
    }

    static Stream<Arguments> hintLetterInputs() {
        return Stream.of(
                Arguments.of('.', false),
                Arguments.of('a', true)
        );
    }
}