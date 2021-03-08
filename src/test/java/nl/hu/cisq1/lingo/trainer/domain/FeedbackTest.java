package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
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
    void isWordGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if some or all letters are incorrect or present")
    void isWordNotGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Mark.ABSENT, Mark.ABSENT, Mark.CORRECT, Mark.PRESENT, Mark.CORRECT));
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("InvalidFeedbackException is thrown when the length of the guess and amount of marks is not the same")
    void invalidFeedbackIsGiven() {
        var marks = List.of(Mark.ABSENT);
        assertThrows(InvalidFeedbackException.class, () -> new Feedback("woord", marks));
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Give correct feedback")
    void giveHint(String word, String expected, List<Mark> marks) {
        Feedback feedback = new Feedback(word, marks);
        assertEquals(expected, feedback.giveHint("......"));
    }

    static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of("banana", "banana", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT)),
                Arguments.of("banana", "b.....", List.of(Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT)),
                Arguments.of("banana", "b....a", List.of(Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.ABSENT, Mark.CORRECT)),
                Arguments.of("banana", "b.n..a", List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.ABSENT, Mark.CORRECT)),
                Arguments.of("banana", "b.n.na", List.of(Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT)),
                Arguments.of("banana", "ban.na", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.ABSENT, Mark.CORRECT, Mark.CORRECT)),
                Arguments.of("banaan", "bana..", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.PRESENT, Mark.PRESENT))
        );
    }

    @Test
    @DisplayName("Give correct first hint, only shows first letter")
    void createFirstHint() {
        assertEquals("b.....", Feedback.createFirstHint("banana"));
    }

    @Test
    @DisplayName("Get guess")
    void getGuess() {
        Feedback feedback = new Feedback("banana", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertEquals("banana", feedback.getGuess());
    }

    @Test
    @DisplayName("Get marks")
    void getMarkPerLetter() {
        Feedback feedback = new Feedback("banana", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertEquals(List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT), feedback.getMarkPerLetter());
    }

    @Test
    @DisplayName("Equals working correctly")
    void testEquals() {
        Feedback feedback = new Feedback("banana", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        Feedback feedback2 = new Feedback("banana", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT));
        assertEquals(feedback, feedback2);
    }
}
