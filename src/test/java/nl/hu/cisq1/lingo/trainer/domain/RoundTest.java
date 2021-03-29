package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoundTest {
    private final Round round = new Round(new Word("woord"));

    @Test
    @DisplayName("guessing should return a list of characters with hints")
    void guess() {
        assertEquals(5, round.guess("woord").size());
    }

    @ParameterizedTest
    @MethodSource(value = "correctIncorrectGuessInput")
    @DisplayName("round should be finished if the guess is correct")
    void isFinished(String guess, Word word) {
        Round round = new Round(word);
        round.guess(guess);
        assertEquals(round.isFinished(), guess.equals(word.getValue()));
    }

    @Test
    void getWordToGuess() {
        assertEquals("woord", this.round.getWordToGuess().getValue());
    }

    @Test
    @DisplayName("state should be in progress when creating a new round")
    void getState() {
        Round round = new Round();
        assertEquals(State.IN_PROGRESS, round.getState());
    }

    @Test
    @DisplayName("guessing should increase attempts")
    void getAttempts() {
        int attempts = this.round.getAttempts();
        this.round.guess("worod");
        assertEquals(this.round.getAttempts(), attempts + 1);
    }

    @Test
    @DisplayName("feedback should give a list of feedback items on guesses")
    void getFeedback() {
        this.round.guess("worod");
        assertTrue(this.round.getAllFeedback().size() >= 1);
    }

    @Test
    @DisplayName("word to guess should be updated when function is called")
    void setWordToGuess() {
        Word newWord = new Word("worod");
        this.round.setWordToGuess(newWord);
        assertEquals(this.round.getWordToGuess(), newWord);
    }

    @Test
    @DisplayName("setting the state should update the state")
    void setState() {
        State newState = State.GUESSED;
        this.round.setState(newState);
        assertEquals(this.round.getState(), newState);
    }

    @Test
    @DisplayName("setting attempts should update attempts")
    void setAttempts() {
        int amountOfAttempts = 5;
        this.round.setAttempts(amountOfAttempts);
        assertEquals(this.round.getAttempts(), amountOfAttempts);
    }

    @Test
    @DisplayName("setting feedback should update feedback list")
    void setFeedback() {
        List<Feedback> feedback = new ArrayList<>();
        Validator validator = new Validator("worod", round.getWordToGuess().getValue());
        feedback.add(new Feedback("worod", validator.validate()));
        round.setAllFeedback(feedback);
        assertEquals(round.getAllFeedback(), feedback);
    }

    static Stream<Arguments> correctIncorrectGuessInput() {
        return Stream.of(Arguments.of("woord", "woord"),
                Arguments.of("woord", "worod"));
    }
}