package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.RoundAlreadyStartedException;
import nl.hu.cisq1.lingo.trainer.domain.exception.LostGameException;
import nl.hu.cisq1.lingo.trainer.domain.exception.RoundNotStartedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {
    @Test
    @DisplayName("score must be increased when a round is won")
    void scoreIncreased() {
        Game game = new Game();
        assertEquals(0, game.getProgress().getScore());
        game.startNewRound("word");
        game.guessWord("word");
        assertEquals(25, game.getProgress().getScore());
    }

    @Test
    @DisplayName("status must be WAITING when starting a new game")
    void statusWaitingNewGame() {
        Game game = new Game();
        assertEquals(GameStatus.WAITING, game.getGameStatus());
    }

    @Test
    @DisplayName("status must be WAITING when winning a round")
    void statusWaitingRoundWin() {
        Game game = new Game();
        game.startNewRound("word");
        game.guessWord("word");
        assertEquals(GameStatus.WAITING, game.getGameStatus());
    }

    @Test
    @DisplayName("status must be PLAYING when a new game is started")
    void statusPlaying() {
        Game game = new Game();
        game.startNewRound("word");
        assertEquals(GameStatus.PLAYING, game.getGameStatus());
    }

    @Test
    @DisplayName("status must be LOST when the player guessed wrong 5 times")
    void statusLost() {
        Game game = new Game();
        game.startNewRound("word");
        game.guessWord("wolf");
        game.guessWord("wars");
        game.guessWord("wall");
        game.guessWord("weak");
        game.guessWord("warn");
        assertEquals(5, game.getRounds().get(0).getAttempts());
        assertEquals(GameStatus.LOST, game.getGameStatus());
    }

    @Test
    @DisplayName("status must not be LOST when the player guessed wrong 5 times")
    void statusNotLost() {
        Game game = new Game();
        game.startNewRound("word");
        game.guessWord("wolf");
        game.guessWord("wars");
        game.guessWord("wall");
        game.guessWord("weak");
        assertEquals(GameStatus.PLAYING, game.getGameStatus());
    }

    @Test
    @DisplayName("cannot start a new round if game already playing")
    void alreadyPlayingGameExceptionWorks() {
        Game game = new Game();
        game.startNewRound("word");
        assertThrows(RoundAlreadyStartedException.class, () -> game.startNewRound("word"));
    }

    @Test
    @DisplayName("cannot guess the word if game not started yet")
    void notPlayingGameExceptionWorks() {
        Game game = new Game();
        assertThrows(RoundNotStartedException.class, () -> game.guessWord("word"));
    }

    @Test
    @DisplayName("cannot start a new round if game is lost")
    void lostGameExceptionWorksOne() {
        Game game = new Game();
        game.startNewRound("word");
        game.guessWord("wolf");
        game.guessWord("wars");
        game.guessWord("wall");
        game.guessWord("weak");
        game.guessWord("warn");
        assertThrows(LostGameException.class, () -> game.startNewRound("word"));
    }

    @Test
    @DisplayName("cannot guess the word if game is lost")
    void lostGameExceptionWorksTwo() {
        Game game = new Game();
        game.startNewRound("word");
        game.guessWord("wolf");
        game.guessWord("wars");
        game.guessWord("wall");
        game.guessWord("weak");
        game.guessWord("warn");
        assertThrows(LostGameException.class, () -> game.guessWord("word"));
    }

    @Test
    @DisplayName("next word length must be according to the Lingo rules")
    void nextWordLengthIsCorrect() {
        Game game1 = new Game();
        game1.startNewRound("words");
        assertEquals(6, game1.getNextWordLength());
        Game game2 = new Game();
        game2.startNewRound("wordss");
        assertEquals(7, game2.getNextWordLength());
        Game game3 = new Game();
        game3.startNewRound("wordsss");
        assertEquals(5, game3.getNextWordLength());
    }
}