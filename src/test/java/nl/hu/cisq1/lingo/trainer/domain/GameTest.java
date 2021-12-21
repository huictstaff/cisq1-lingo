package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.State;
import nl.hu.cisq1.lingo.trainer.domain.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    void createGame() {
        game = new Game();
    }

    @Test
    @DisplayName("hint is correct for new round")
    void roundHint() {
        game.startNewRound("appel");
        assertEquals(game.getCurrentHint(), "a....");
    }

    @Test
    @DisplayName("word length goes back to 5 after 7")
    void wordUpRound() {
        game.startNewRound("appel");
        game.guess("appel");
        game.startNewRound("appels");
        game.guess("appels");
        game.startNewRound("rotzooi");
        assertEquals(game.getWordLength(), 5);
    }

    @Test
    @DisplayName("game state after losing round")
    void gameStateLosing() {
        game.startNewRound("appel");
        for (int i = 0; i < 5; i++) {
            game.guess("braam");
        }

        assertEquals(game.getStatus(), State.LOST);
    }

    @Test
    @DisplayName("invalid length of word to guess")
    void answerInvalidLength() {
        assertThrows(
                InvalidWordLengthException.class,
                () -> game.startNewRound("appels")
        );
    }

    @Test
    @DisplayName("valid length of word to guess")
    void answerValidLength() {
        assertDoesNotThrow(
                () -> game.startNewRound("appel")
        );
    }

    @Test
    @DisplayName("cannot guess a word when game has no round")
    void gameNoRound() {
        assertThrows(
                RoundNotExistException.class,
                () -> game.guess("appel")
        );
    }

    @Test
    @DisplayName("cannot start a new round while still one active")
    void roundStillActive() {
        game.startNewRound("bomen");

        assertThrows(
                RoundActiveException.class,
                () -> game.startNewRound("appels")
        );
    }

    @Test
    @DisplayName("cannot start new round if last round lost")
    void roundLostNewRound() {
        game.startNewRound("appel");
        for (int i = 0; i < 5; i++) {
            game.guess("braam");
        }

        assertThrows(
                GameOverException.class,
                () -> game.startNewRound("appels")
        );
    }

    @Test
    @DisplayName("cannot guess word if round is done")
    void roundInactiveGuess() {
        game.startNewRound("appel");
        game.guess("appel");

        assertThrows(
                RoundOverException.class,
                () -> game.guess("braam")
        );
    }

    @Test
    @DisplayName("cannot get current word hint if there is no rounds")
    void currentlyNoHint() {
        assertThrows(
                RoundNotExistException.class,
                () -> game.getCurrentHint()
        );
    }

    @ParameterizedTest
    @MethodSource("allScores")
    @DisplayName("all scores are assigned correctly according to attempts")
    void scorePerAttemts(int attemptCount, int expectedScore) {
        game.startNewRound("appel");
        for (int i = 0; i < attemptCount - 1; i++) {
            game.guess("braam");
        }
        game.guess("appel");

        assertEquals(expectedScore, game.getScore());
    }

    static Stream<Arguments> allScores() {
        return Stream.of(
                Arguments.of(1, 25),
                Arguments.of(2, 20),
                Arguments.of(3, 15),
                Arguments.of(4, 10),
                Arguments.of(5, 5)
        );
    }

}