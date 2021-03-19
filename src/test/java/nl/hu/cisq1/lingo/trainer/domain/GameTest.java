package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.exception.IllegalMoveException;
import nl.hu.cisq1.lingo.trainer.exception.RoundPlayingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
      Game game;
      @BeforeEach
        void beforeEach(){
          game = new Game("word");
      }

      @Test
      @DisplayName("can not new round begin when the last not won")
      void startNewRoundNotWonTest(){

          assertThrows( RoundPlayingException.class,
                  ()-> game.startRound("woord"));
      }

    @Test
    @DisplayName("start new round when the last won")
    void startNewRoundWonTest(){
          game.guessWord("word");
          assertDoesNotThrow(() -> game.startRound("woord"));
    }

    @Test
    @DisplayName("start new round  when the last lost")
    void startNewRoundLostTest(){
        game.guessWord("wood");
        game.guessWord("wood");
        game.guessWord("wood");
        game.guessWord("wood");
        game.guessWord("wood");
        assertThrows( RoundPlayingException.class,
                ()-> game.startRound("woord"));
    }

    @Test
    @DisplayName("score test when won from the first time")
    void scoreFirstTimeTest(){
        game.guessWord("word");
        assertEquals(25, game.getScore());
    }

    @Test
    @DisplayName("score test when won from the tweede time")
    void scoreTweedeTimeTest(){
        game.guessWord("wood");
        game.guessWord("word");
        assertEquals(20, game.getScore());
    }

    @Test
    @DisplayName("score is 0 when not yet won")
    void zeroScoreTest(){
        game.guessWord("wood");
        game.guessWord("wold");
        assertEquals(0, game.getScore());
    }

    @Test
    @DisplayName("next word length test")
    void wordLengthTest(){

        assertEquals(5, game.wordLength());
    }

    @Test
    @DisplayName("next word length after new round test")
    void afterNewRoundLengthTest(){
        game.guessWord("word");
        game.startRound("woord");
        assertEquals(6, game.wordLength());
    }

    @Test
    @DisplayName("next word length after word with 7 letters test")
    void lengthAfterSeven(){
        game.guessWord("word");
        game.startRound("woorrdd");
        assertEquals(5, game.wordLength());
    }


}