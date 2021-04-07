package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    private final Round round = new Round("woord");
    private final Round fullRound = new Round(1L, "woord", State.IN_PROGRESS, 0, new ArrayList<>(), new ArrayList<>(), new Game());

    @Test
    @DisplayName("guessing should return a list of characters with hints")
    void guess() {
        assertEquals(5, round.guess("woord").size());
    }

    @ParameterizedTest
    @MethodSource(value = "correctIncorrectGuessInput")
    @DisplayName("round should be finished if the guess is correct")
    void isFinished(String guess, String word) {
        Round round = new Round(word);
        round.guess(guess);
        assertEquals(round.isFinished(), guess.equals(word));
    }

    @Test
    @DisplayName("guessing 5 times should change the state to LOST")
    void lostState() {
        this.round.setAttempts(4);
        this.round.guess("worod");
        assertEquals(State.LOST, this.round.getState());
    }

    @Test
    void getWordToGuess() {
        assertEquals("woord", this.round.getWordToGuess());
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
    void getAllHints() {
        assertFalse(this.round.getAllHints().isEmpty());
    }

    @Test
    @DisplayName("setting an id should update the id")
    void setId() {
        Long id = 5L;

        this.fullRound.setId(id);

        assertEquals(id, this.fullRound.getId());
    }

    @Test
    @DisplayName("word to guess should be updated when function is called")
    void setWordToGuess() {
        String newWord = "worod";
        this.fullRound.setWordToGuess(newWord);
        assertEquals(this.fullRound.getWordToGuess(), newWord);
    }

    @Test
    @DisplayName("setting the state should update the state")
    void setState() {
        State newState = State.GUESSED;
        this.fullRound.setState(newState);
        assertEquals(this.fullRound.getState(), newState);
    }

    @Test
    @DisplayName("setting attempts should update attempts")
    void setAttempts() {
        int amountOfAttempts = 5;
        this.fullRound.setAttempts(amountOfAttempts);
        assertEquals(this.fullRound.getAttempts(), amountOfAttempts);
    }

    @Test
    @DisplayName("setting feedback should update feedback list")
    void setFeedback() {
        List<Feedback> feedback = new ArrayList<>();
        Validator validator = new Validator("worod", round.getWordToGuess());
        feedback.add(new Feedback("worod", validator.validate()));
        this.fullRound.setAllFeedback(feedback);
        assertEquals(this.fullRound.getAllFeedback(), feedback);
    }

    @Test
    void setAllHints() {
        List<Hint> hints = new ArrayList<>();
        this.fullRound.setAllHints(hints);
        assertEquals(hints, this.fullRound.getAllHints());
    }

    @Test
    void setGame() {
        Game game = new Game();
        List<Round> rounds = List.of(this.fullRound);
        game.setRounds(rounds);

        this.fullRound.setGame(game);

        assertEquals(game, this.fullRound.getGame());
    }

    static Stream<Arguments> correctIncorrectGuessInput() {
        return Stream.of(Arguments.of("woord", "woord"),
                Arguments.of("woord", "worod"));
    }
}