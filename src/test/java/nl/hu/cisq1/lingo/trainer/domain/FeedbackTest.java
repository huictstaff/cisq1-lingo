package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.InvalidFeedbackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {

    @Test
    @DisplayName("word is guessed if all letters are correct")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("appel", "appel");
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is not guessed if some letters are correct")
    void wordIsNotGuessed() {
        Feedback feedback = new Feedback("appel", "abbel");
        assertFalse(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("word is invalid if guess is bigger than word")
    void wordIsDifferentLength() {
        assertThrows(
                InvalidFeedbackException.class,
                () -> new Feedback("appel", "appels")
        );
    }

    @Test
    @DisplayName("feedback is equal if same values")
    void feedbackIsEqual() {
        assertEquals(new Feedback("appel", "apple"), new Feedback("appel", "apple"));
    }

    @Test
    @DisplayName("feedback isn't equal if different values")
    void feedbackIsNotEqual() {
        assertNotEquals(new Feedback("appel", "apple"), new Feedback("braam", "brood"));
    }

    @Test
    @DisplayName("feedback hashCode is equal if same values")
    void feedbackHashcodeEqual() {
        Feedback f1 = new Feedback("appel", "apple");
        Feedback f2 = new Feedback("appel", "apple");

        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    @DisplayName("feedback toString method contains attempt")
    void feedbackToString() {
        Feedback f1 = new Feedback("appel", "apple");
        assertTrue(f1.toString().contains(f1.getAttempt()));
    }

    @ParameterizedTest
    @DisplayName("the user guessed and received hints")
    @MethodSource("provideHints")
    void giveHint(String answer, String attempt, String previousHint, String expectedHint) {
        Feedback feedback = new Feedback(answer, attempt);
        assertEquals(expectedHint, feedback.giveHint(previousHint));
    }

    private static Stream<Arguments> provideHints() {
        return Stream.of(
                Arguments.of("appel", "aapje", null, "a.p.."),
                Arguments.of("appel", "armen", "a.p..", "a.pe."),
                Arguments.of("appel", "appel", "a.pe.", "appel")
        );
    }

}