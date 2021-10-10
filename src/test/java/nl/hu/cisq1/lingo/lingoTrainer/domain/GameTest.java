package nl.hu.cisq1.lingo.lingoTrainer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("Test new game")
    public void testNewGame()
    {
        var game1 = new Game("huis");
        var result = game1.getCurrentRound().guessWord("huis");

        assertTrue(result.isWordGuessed());
    }

    @Test
    void testConstructor() {
        Game actualGame = new Game("Word To Guess");
        Round round = new Round("Word To Guess");
        actualGame.setCurrentRound(round);
        ArrayList<Round> roundList = new ArrayList<Round>();
        actualGame.setRounds(roundList);
        assertSame(round, actualGame.getCurrentRound());
        assertSame(roundList, actualGame.getRounds());
    }

    @Test
    void testConstructor2() {
        Game actualGame = new Game("Word To Guess");
        assertTrue(actualGame.getRounds().isEmpty());
        Round currentRound = actualGame.getCurrentRound();
        assertEquals(0, currentRound.timesGuessed);
        assertEquals("Word To Guess", currentRound.getWordToGuess());
        assertEquals("W,.,.,.,.,.,.,.,.,.,.,.,.", currentRound.getPreviousHint());
    }

    @Test
    void testConstructor3() {
        Game actualGame = new Game("Word To Guess");
        Round round = new Round("Word To Guess");
        actualGame.setCurrentRound(round);
        ArrayList<Round> roundList = new ArrayList<Round>();
        actualGame.setRounds(roundList);
        assertSame(round, actualGame.getCurrentRound());
        assertSame(roundList, actualGame.getRounds());
    }

    @Test
    void testConstructor4() {
        Game actualGame = new Game("Word To Guess");
        assertTrue(actualGame.getRounds().isEmpty());
        Round currentRound = actualGame.getCurrentRound();
        assertEquals(0, currentRound.timesGuessed);
        assertEquals("Word To Guess", currentRound.getWordToGuess());
        assertEquals("W,.,.,.,.,.,.,.,.,.,.,.,.", currentRound.getPreviousHint());
    }

    @Test
    void testNewRound() {
        Game game = new Game("Word To Guess");
        game.newRound("Word To Guess");
        assertEquals(1, game.getRounds().size());
    }

    @Test
    void testNewRound2() {
        Game game = new Game("Word To Guess");
        game.newRound("42");
        Round currentRound = game.getCurrentRound();
        assertEquals(0, currentRound.timesGuessed);
        assertEquals("42", currentRound.getWordToGuess());
        assertEquals("4,.", currentRound.getPreviousHint());
    }

    @Test
    void testNewRound3() {
        Game game = new Game("Word To Guess");
        game.setCurrentRound(null);
        game.setRounds(null);
        game.newRound("Word To Guess");
        Round currentRound = game.getCurrentRound();
        assertEquals(0, currentRound.timesGuessed);
        assertEquals("Word To Guess", currentRound.getWordToGuess());
        assertEquals("W,.,.,.,.,.,.,.,.,.,.,.,.", currentRound.getPreviousHint());
    }

    @Test
    void testNewRound4() {
        Game game = new Game("Word To Guess");
        game.newRound("Word To Guess");
        assertEquals(1, game.getRounds().size());
    }

    @Test
    void testNewRound5() {
        Game game = new Game("Word To Guess");
        game.newRound("42");
        Round currentRound = game.getCurrentRound();
        assertEquals(0, currentRound.timesGuessed);
        assertEquals("42", currentRound.getWordToGuess());
        assertEquals("4,.", currentRound.getPreviousHint());
    }

    @Test
    void testNewRound6() {
        Game game = new Game("Word To Guess");
        game.setCurrentRound(null);
        game.setRounds(null);
        game.newRound("Word To Guess");
        Round currentRound = game.getCurrentRound();
        assertEquals(0, currentRound.timesGuessed);
        assertEquals("Word To Guess", currentRound.getWordToGuess());
        assertEquals("W,.,.,.,.,.,.,.,.,.,.,.,.", currentRound.getPreviousHint());
    }
}

