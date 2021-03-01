package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.enums.Mark;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundState;
import nl.hu.cisq1.lingo.trainer.domain.enums.RoundType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    Round round;
    LingoGame game;

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
    @DisplayName("Check if roundstate changes to lost if round is lost")
    void wordIsNotGuessed() {
        round.wordIsNotGuessed();
        assertEquals(RoundState.LOST, round.getRoundState());
    }

    @Test
    @DisplayName("Check if roundstate changes to won if round is won")
    void wordIsGuessed() {
        round.wordIsGuessed();
        assertEquals(RoundState.WON, round.getRoundState());
    }

    @Test
    @DisplayName("Throws an error when trying to guess when the round is already won")
    void throwErrorWhenRoundIsAlreadyWon() {
        round.wordIsGuessed();
        assertThrows(RuntimeException.class, () -> round.checkIfRoundIsLostOrWon());
    }

    @Test
    @DisplayName("Throws an error when trying to guess when the round is already lost")
    void throwErrorWhenRoundIsAlreadyLost() {
        round.wordIsNotGuessed();
        assertThrows(RuntimeException.class, () -> round.checkIfRoundIsLostOrWon());
    }

    @Test
    void getTries() {
    }

    @Test
    void getAllFeedback() {
        round.makeGuessAndGiveHint("banana", true);
        assertEquals(List.of(new Feedback("banana", List.of(Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT, Mark.CORRECT))), round.getAllFeedback());
    }

    @Test
    @DisplayName("Get roundState correctly")
    void getRoundState() {
        assertEquals(RoundState.ONGOING, round.getRoundState());
    }

    @Test
    @DisplayName("Get roundType correctly")
    void getType() {
        assertEquals(RoundType.FIVELETTERS, round.getType());
    }

    @Test
    @DisplayName("Get word to guess correctly")
    void getWordToGuess() {
        assertEquals("banana", round.getWordToGuess());
    }
}