package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void init() {
        this.game = new Game("aapje");
    }

    @Test
    @DisplayName("game is started and initial values are set")
    void gameStarted() {
        assertTrue(game.isGoing());
        assertNotNull(game.getCurrentRound());
        assertEquals("aapje", game.getCurrentRound().getWordToGuess());
    }

    @Test
    @DisplayName("after the word is guessed wrong 5 times, the game should end")
    void guessWord() {
        for (int i = 0; i < 5; i++) {
            assertTrue(game.isGoing());
            Feedback f = game.guess("appje");
            assertNotNull(f);
        }
        assertEquals(5, game.getCurrentRound().getTried());
        assertFalse(game.isGoing());
    }

    @Test
    @DisplayName("after the word is illegal to guess, the game should end")
    void illegalGuess() {
        for (int i = 0; i < 5; i++) {
            game.guess("beren");
        }
        assertEquals(5, game.getCurrentRound().getTried());
        assertFalse(game.isGoing());
        game.startNewRound("woord");
        assertNotEquals("woord", game.getCurrentRound().getWordToGuess());
    }

    @Test
    @DisplayName("if the game is not lost, you need to be able to start a new round")
    void newGame() {
        game.guess("aapje");
        game.startNewRound("woord");
        assertEquals("woord", game.getCurrentRound().getWordToGuess());
    }

}