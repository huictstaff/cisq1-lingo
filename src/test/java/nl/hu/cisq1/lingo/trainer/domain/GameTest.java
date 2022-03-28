package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exeption.GameException;
import nl.hu.cisq1.lingo.trainer.domain.exeption.TooManyAttemptsException;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.Mark.ABSENT;
import static nl.hu.cisq1.lingo.trainer.domain.Mark.CORRECT;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("game is created with a new round")
    void gameWithFirstRound() {
        Game game = new Game(new Word("BROOD"));
        assertEquals(1, game.getRounds().size());
    }

    @Test
    @DisplayName("if no game is active but you try to do a guess it should go to an exception")
    void guessWithoutActiveGamestate() {
        Game game = new Game(new Word("BROOD"));
        game.setGamestate(Gamestate.FINISHED);
        assertThrows(GameException.class, () -> game.guess("BREIN"));
    }

    @Test
    @DisplayName("succesfully make a guess")
    void firstGuess() {
        Game game = new Game(new Word("BROOD"));
        game.guess("BREIN");
        assertEquals(List.of(CORRECT, CORRECT, ABSENT, ABSENT, ABSENT), game.getRounds().get(game.getRounds().size()-1).getFeedback().get(game.getRounds().size()-1).getMarks());
    }

    @Test
    @DisplayName("succesfully make multiple guesses")
    void multipleGuesses() {
        Game game = new Game(new Word("BROOD"));
        game.guess("BEKER");
        game.guess("BARON");
        game.guess("BROOM");
        game.guess("BROOS");
        game.guess("BROOT");
        assertEquals(List.of(CORRECT, CORRECT, CORRECT, CORRECT, ABSENT), game.getRounds().get(game.getRounds().size()-1).getFeedback().get(4).getMarks());
    }

    @Test
    @DisplayName("after to many guesses the guess is invalid and the round is finished")
    void roundIsFinished() {
        Game game = new Game(new Word("BROOD"));
        game.guess("BEKER");
        game.guess("BARON");
        game.guess("BROOM");
        game.guess("BROOS");
        game.guess("BROOT");
        assertEquals(Gamestate.FINISHED, game.getGamestate());
    }

    @Test
    @DisplayName("test if the score gets added correctly")
    void score() {
        Game game = new Game(new Word("BROOD"));
        game.guess("BEKER");
        game.guess("BROOD");
        assertEquals(20, game.getScore());
        System.out.println(game.getGamestate());
    }

    @Test
    @DisplayName("start a new round")
    void startNewRound() {
        Game game = new Game(new Word("BROOD"));
        game.guess("BROOD");
        assertEquals(Gamestate.WAITING, game.getGamestate());
        game.startNewRound(new Word("TOREN"));
        assertEquals(Gamestate.ACTIVE, game.getGamestate());
        game.guess("TREIN");
        game.guess("TOREN");
        assertEquals(Gamestate.WAITING, game.getGamestate());
        assertEquals(45, game.getScore());
    }

    @Test
    @DisplayName("start a new round while the previous round is still ACTIVE or FINISHED")
    void illegallyStartNewRound() {
        Game game = new Game(new Word("BROOD"));
        assertEquals(Gamestate.ACTIVE, game.getGamestate());
        Word testWord = new Word("TOREN");
        assertThrows(GameException.class, () -> game.startNewRound(testWord));
    }


    @Test
    @DisplayName("to get the length of the previous rounds word, necessary for getting the next word")
    void getWordLength() {
        Game game = new Game(new Word("BROOD"));
        game.guess("BROOD");
        assertEquals(5, game.getWordLength());
        game.startNewRound(new Word("TORENS"));
        game.guess("TRANEN");
        game.guess("TORENS");
        assertEquals(6, game.getWordLength());
    }


    /** TODO multiple rounds */
    /** TODO test if rounds history works */
    /** TODO multiple rounds and catch that same word gets asked multiple times */

}