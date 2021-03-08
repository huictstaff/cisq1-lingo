package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.RoundState;
import nl.hu.cisq1.lingo.trainer.exception.RoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    private Round round;
    private LingoGame game;

    @BeforeEach
    void setUp() {
        LingoGame game = new LingoGame();
        this.game = game;
        game.newRound("banana");
        this.round = game.getLastRound();
    }

    @Test
    @DisplayName("Make a correct guess") //already fully tested in FeedbackTest
    void makeGuessAndGiveHint() {
        assertEquals("banana", round.makeGuessAndGiveHint("banana", true));
    }

    @Test
    @DisplayName("Make guess throws error because word does not exist")
    void makeGuessAndThrowError() {
        assertThrows(RuntimeException.class, () -> round.makeGuessAndGiveHint("bananq", false));
    }

    @Test
    @DisplayName("Throws an error when trying to guess when the round is already won")
    void throwErrorOnGuessWhenRoundIsAlreadyWon() {
        round.makeGuessAndGiveHint("banana", true);
        assertThrows(RoundException.class, () -> round.makeGuessAndGiveHint("banana", true));
    }

    @Test
    @DisplayName("Throws an error when trying to guess when the round is already lost")
    void throwErrorOnGuessWhenRoundIsAlreadyLost() {
        round.makeGuessAndGiveHint("citrus", true);
        round.makeGuessAndGiveHint("citrus", true);
        round.makeGuessAndGiveHint("citrus", true);
        round.makeGuessAndGiveHint("citrus", true);
        round.makeGuessAndGiveHint("citrus", true);
        assertThrows(RoundException.class, () -> round.makeGuessAndGiveHint("banana", true));
    }

    @Test
    @DisplayName("tries gets upped by one when a guess is made")
    void makeGuessAndUpTries() {
        assertEquals(0, round.getTries());
        round.makeGuessAndGiveHint("banana", true);
        assertEquals(1, round.getTries());
    }

    @Test
    @DisplayName("Make a guess and check if last hint is set") //already fully tested in FeedbackTest
    void makeGuessAndLastHintGetsSet() {
        round.makeGuessAndGiveHint("banana", true);
        assertEquals("banana", round.getLastHint());
    }

    @Test
    @DisplayName("Word is not guessed withing 5 tries so roundState is now LOST")
    void makeGuessAndWordIsNotGuessedWithinFiveTries() {
        round.makeGuessAndGiveHint("citrus", true);
        round.makeGuessAndGiveHint("citrus", true);
        round.makeGuessAndGiveHint("citrus", true);
        round.makeGuessAndGiveHint("citrus", true);
        round.makeGuessAndGiveHint("citrus", true);
        assertEquals(RoundState.LOST, round.getRoundState());
    }

    @Test
    @DisplayName("When wrong guesses are under 5 tries the roundstate is ongoing")
    void makeWrongGuessAndRoundStateShouldBeOngoing() {
        round.makeGuessAndGiveHint("citrus", true);
        assertEquals(RoundState.ONGOING, round.getRoundState());
    }

    @Test
    @DisplayName("Word is guessed and roundstate is now WON")
    void makeGoodGuessAndRoundStateShouldBeWon() {
        round.makeGuessAndGiveHint("banana", true);
        assertEquals(RoundState.WON, round.getRoundState());
    }
}