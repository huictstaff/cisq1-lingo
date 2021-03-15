package trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import trainer.domain.exception.InvalidFeedbackException;

import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeedbackTest {
    @Test
    @DisplayName("Error is thrown when the amount of marks and letters are not equal")
    void throwsInvalidFeedbackException() {
        assertThrows(
                InvalidFeedbackException.class,
                () -> new Feedback("woord", List.of(Mark.CORRECT))
        );
    }

    public static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)),
                Arguments.of("klein", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT))
        );
    }


    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Hint is correct if the given marks correspond to the correct symbol")
    // correct symbols are:
    // PRESENT = "*"
    // ABSENT = "_"
    // CORRECT = The letter at that place in the word
    // INVALID = "#"
    void hintGiven(String word, List<Mark> marks) {
        Feedback feedback = new Feedback(word, List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        List<String> hint = feedback.giveHint(word, marks);
        for( int i = 0; i < marks.size(); i++){
            switch (marks.get(i)) {
                case PRESENT:
                    assertEquals(hint.get(i),"*");
                    break;

                case ABSENT:
                    assertEquals(hint.get(i),"_");
                    break;

                case CORRECT:
                    assertEquals(hint.get(i),"" + word.charAt(i));
                    break;
                case INVALID:
                    assertEquals(hint.get(i),"#");
                    break;

                default:
                    System.err.println("Error Invalid mark.");
                    break;
            };
        }
    }

    @Test
    @DisplayName("Word is guessed if all letters are correct.")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if one or more letters are not correct.")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Guess is invalid if one or more characters are invalid.")
    void guessIsInvalid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.INVALID, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.isGuessInvalid());
    }

    @Test
    @DisplayName("Guess is valid if none of the characters are invalid.")
    void guessIsNotInvalid() {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.isGuessInvalid());
    }
}