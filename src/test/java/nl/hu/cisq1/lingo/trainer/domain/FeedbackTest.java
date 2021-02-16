package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.Mark;
import nl.hu.cisq1.lingo.trainer.exception.InvalidFeedbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    @Test
    @DisplayName("Word is guessed if all letters are correct")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if some or all letters are incorrect")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("InvalidFeedbackException is thrown when the length of the guess and amount of marks is not the same")
    void invalidFeedbackIsGiven() {
        assertThrows(InvalidFeedbackException.class, () -> new Feedback("woord", List.of(Mark.ABSENT)));
    }

    static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("banana", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)),
                Arguments.of("b.....", List.of(Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT)),
                Arguments.of("b....a", List.of(Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.CORRECT)),
                Arguments.of("b.n..a", List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.CORRECT)),
                Arguments.of("b.n.na", List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT)),
                Arguments.of("ban.na", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT))
        );
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Give correct feedback")
    void giveFullFeedback(String expected,List<Mark> marks) {
        Feedback feedback = new Feedback("banana", marks);
        assertEquals(expected, feedback.giveHint());
    }
}
