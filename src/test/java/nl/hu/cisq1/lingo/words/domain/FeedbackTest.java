package nl.hu.cisq1.lingo.words.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackTest {
    @Test
    @DisplayName("Word is guessed if all letters correspond with the word.")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }
    @Test
    @DisplayName("Word is not guessed if all letters correspond with the word.")
    void wordIsNotGuessed(){
        Feedback feedback = new Feedback("woord", List.of(Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.ABSENT, Rating.CORRECT));
        assertFalse(feedback.isWordGuessed());
    }
    @Test
    @DisplayName("Word has invalid characters")
    void guessIsInvalid() {
        Feedback feedback = new Feedback("woord", List.of(Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.INVALID, Rating.CORRECT));
        assertTrue(feedback.isWordInvalid());
    }
    @Test
    @DisplayName("Word doesn't have invalid characters.")
    void guessIsNotInvalid(){
        Feedback feedback = new Feedback("woord", List.of(Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT));
        assertFalse(feedback.isWordInvalid());
    }
}