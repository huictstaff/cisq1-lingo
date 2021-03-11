package nl.hu.cisq1.lingo.domain;

import nl.hu.cisq1.lingo.domain.exception.InvalidFeedbackException;
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
    @DisplayName("Word is guessed if all letters correspond with the word.")
    void wordIsGuessed() {
        Feedback feedback = new Feedback("woord", List.of(Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT));
        assertTrue(feedback.isWordGuessed());
    }

    @Test
    @DisplayName("Word is not guessed if all letters correspond with the word.")
    void wordIsNotGuessed() {
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
    void guessIsNotInvalid() {
        Feedback feedback = new Feedback("woord", List.of(Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT));
        assertFalse(feedback.isWordInvalid());
    }

    @Test
    @DisplayName("Feedback throws exception if Rating hasn't same length")
    void feedbackIsNotValid() {
        List<Rating> ratingList = List.of(Rating.CORRECT);
        assertThrows(InvalidFeedbackException.class,
                () -> new Feedback("woord", ratingList)
        );
    }


    static Stream<Arguments> provideHintExamples() {
        return Stream.of(
                Arguments.of(
                        List.of('.','a','.','.','.', 'n'),
                        "banaan",
                        List.of('.','a','n','a','a','n'),
                        List.of(Rating.ABSENT, Rating.CORRECT,Rating.CORRECT,Rating.CORRECT,Rating.CORRECT,Rating.CORRECT)),
                Arguments.of(
                        List.of('.','.','.','.','.', '.'),
                        "banaan",
                        List.of('.','a','.','.','.','n'),
                        List.of(Rating.ABSENT, Rating.CORRECT,Rating.ABSENT,Rating.ABSENT,Rating.ABSENT,Rating.CORRECT)
                )

        );
    }

    @ParameterizedTest
    @MethodSource("provideHintExamples")
    @DisplayName("Test if giveHint returns right hint.")
    void giveHintTest(List<Character> prevHint, String wordToGuess, List<Character> newHint, List<Rating> marks) {
        Feedback feedback = new Feedback(marks);
        assertEquals(newHint, feedback.giveHint(prevHint, wordToGuess));
    }

    static Stream<Arguments> provideRatingExamples() {
        return Stream.of(
                Arguments.of(
                        "banaan",
                        "bonbia",
                        List.of(Rating.CORRECT, Rating.ABSENT,Rating.CORRECT,Rating.PRESENT,Rating.ABSENT,Rating.PRESENT)),
                Arguments.of(
                        "fiets",
                        "fietsen",
                        List.of(Rating.INVALID, Rating.INVALID, Rating.INVALID, Rating.INVALID, Rating.INVALID, Rating.INVALID, Rating.INVALID)),
                Arguments.of(
                        "telefoon",
                        "telefoon",
                        List.of(Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT, Rating.CORRECT))

        );
    }
    @ParameterizedTest
    @MethodSource("provideRatingExamples")
    @DisplayName("Test if giveRating returns right rating.")
    void giveRatingTest(String wordToGuess, String attempt, List<Rating> ratings) {
        assertEquals(ratings, Feedback.generateRatings(attempt, wordToGuess));
    }
}